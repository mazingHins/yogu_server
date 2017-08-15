/**
 * 
 */
package com.yogu.commons.cache.redis.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.yogu.commons.utils.ArrayUtils;
import com.yogu.commons.utils.SerializeUtil;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.MazingJedisCluster;
import redis.clients.jedis.ShardedJedis;
import redis.clients.util.JedisClusterCRC16;

/**
 * redis分片模式的相关辅助类 <br>
 *
 * @author JFan 2016年2月29日 下午12:11:59
 */
public class RedisHandler {

	private static final ShardedJedisByte shardedByte = new ShardedJedisByte();
	private static final ShardedJedisString shardedString = new ShardedJedisString();
	private static final JedisClusterByte clusterByte = new JedisClusterByte();
	private static final JedisClusterString clusterString = new JedisClusterString();

	/**
	 * 返回一个带有节点信息的异常
	 */
	public static RuntimeException err(byte[] key, ShardedJedis shardedJedis, Exception e) {
		Jedis jedis = shardedJedis.getShard(key);
		return err(jedis, e);
	}

	/**
	 * 返回一个带有节点信息的异常
	 */
	public static RuntimeException err(String key, ShardedJedis shardedJedis, Exception e) {
		Jedis jedis = shardedJedis.getShard(key);
		return err(jedis, e);
	}

	/**
	 * 返回一个带有节点信息的异常
	 */
	public static RuntimeException err(Jedis jedis, Exception e) {
		String host = (null == jedis ? "" : jedis.getClient().getHost());
		return new RuntimeException("节点<" + host + ">发生异常：" + e.getMessage(), e);
	}

	// ####

	/**
	 * 分片模式mget方法取值：<br>
	 * 先将所有key进行落点运算，然后再根据落点进行串行mget<br>
	 * 最后将数据整合后返回
	 */
	public static List<String> mgetByCluster(MazingJedisCluster jedis, String... keys) {
		return mget0(clusterString, jedis, keys);
	}

	/**
	 * 分片模式mget方法取值：<br>
	 * 先将所有key进行落点运算，然后再根据落点进行串行mget<br>
	 * 最后将数据整合后返回
	 */
	public static <V> List<V> mgetByCluster(MazingJedisCluster jedis, byte[]... keys) {
		List<byte[]> result = mget0(clusterByte, jedis, keys);
		return toObj(result);
	}

	//

	/**
	 * 分片模式mget方法取值：<br>
	 * 先将所有key进行落点运算，然后再根据落点进行串行mget<br>
	 * 最后将数据整合后返回
	 */
	public static List<String> mgetBySharding(ShardedJedis jedis, String... keys) {
		return mget0(shardedString, jedis, keys);
	}

	/**
	 * 分片模式mget方法取值：<br>
	 * 先将所有key进行落点运算，然后再根据落点进行串行mget<br>
	 * 最后将数据整合后返回
	 */
	public static <V> List<V> mgetBySharding(ShardedJedis jedis, byte[]... keys) {
		List<byte[]> result = mget0(shardedByte, jedis, keys);
		return toObj(result);
	}

	// ####
	// ## private func
	// ####

	/**
	 * redis分片模式支持mget方式取值<br>
	 * 1：将所有的key分组，落到同一个redis节点的归在同一组<br>
	 * 2：根据分组，依次去redis节点上批量查询数据（mget）<br>
	 * 3：按照入参keys的顺序，依次组装需要返回的数据（找不到要用null填充）<br>
	 * 
	 * @param jk J表示是分片模式，还是集群模式；K表示是byte[]还是String
	 * @param jedis 分片模式|集群模式 的实例对象
	 * @param keys 要查询的key列表（K 只支持byte[] 和 String类型）
	 */
	@SuppressWarnings("unchecked")
	private static <J, K> List<K> mget0(JK<J, K> jk, J jedis, K... keys) {
		// 如果keys为空，则返回空List
		if (ArrayUtils.isEmpty(keys))
			return new ArrayList<>();

		// 记录每一个key的顺序
		Map<K, Integer> keyIndex = new HashMap<>();

		// 落到同一个redis节点的key归集到一起
		// >>>> { node1: [key2, key3], node2: [key1, key4, key5] }
		Map<Object, List<K>> shardMap = new HashMap<>();
		int index = 0;
		for (K key : keys) {
			// 寻找key的落点
			// key只支持 byte[] 和 String两种类型
			Object shardInfo = jk.foothold(jedis, key);

			// 将落点（redis node）塞入到shardMap中
			List<K> list = shardMap.get(shardInfo);
			if (null == list) {// 没有就自动创建
				list = new LinkedList<>();
				shardMap.put(shardInfo, list);
			}
			list.add(key);

			keyIndex.put(key, index);
			index++;
		}

		int length = keys.length;
		Object[] resultArray = new Object[length];
		// System.out.println("分组数：" + shardMap.size());

		// shardMap中有多少个key，就说明会落到几个redis节点上
		// 按顺序每个节点批量获取数据
		for (Entry<Object, List<K>> entry : shardMap.entrySet()) {
			List<K> ks = entry.getValue();
			// 批量查询
			List<K> values = jk.mget(jedis, ks);

			int i = 0;
			for (K body : values) {
				// 找到这个key在入参keys中的位置
				K k = ks.get(i++);
				int resultIndex = keyIndex.get(k);

				try {
					// // 取得实际的对象
					// V value = keyType.result(body);

					// 放入到对应的位置
					resultArray[resultIndex] = body;
				} catch (Exception e) {
					throw new RuntimeException(e.getMessage(), e);
				}
			}
		}

		// array 2 list
		List<K> result = new ArrayList<>(length);
		for (Object obj : resultArray)
			result.add(null == obj ? null : ((K) obj));
		return result;
	}

	// ####
	// ## private static class
	// ####

	private static interface JK<J, K> {

		public Object foothold(J jedis, K key);

		public List<K> mget(J jedis, List<K> keys);

	}

	private static class ShardedJedisByte implements JK<ShardedJedis, byte[]> {

		@Override
		public Object foothold(ShardedJedis jedis, byte[] key) {
			return jedis.getShardInfo(key);
		}

		@Override
		public List<byte[]> mget(ShardedJedis jedis, List<byte[]> keys) {
			byte[] key = keys.get(0);
			Jedis shard = jedis.getShard(key);

			byte[][] ks = keys.toArray(new byte[keys.size()][]);
			return shard.mget(ks);
		}

	}

	private static class ShardedJedisString implements JK<ShardedJedis, String> {

		@Override
		public Object foothold(ShardedJedis jedis, String key) {
			return jedis.getShardInfo(key);
		}

		@Override
		public List<String> mget(ShardedJedis jedis, List<String> keys) {
			String key = keys.get(0);
			Jedis shard = jedis.getShard(key);

			String[] ks = keys.toArray(new String[keys.size()]);
			return shard.mget(ks);
		}

	}

	private static class JedisClusterByte implements JK<MazingJedisCluster, byte[]> {

		@Override
		public Object foothold(MazingJedisCluster jedis, byte[] key) {
			/**
			 * TODO redis集群有16384个槽位，如果根据slot值来区分的话，很难分到同一组里面。 尝试了修改jedis源代码，通过定位jedisPool来分组，可以使同一个node的分到一组里，但是TMD
			 * redis协议不允许（必须是同一个slot）
			 */
			return JedisClusterCRC16.getSlot(key);
			// return jedis.getJedisPoolFromKey(key);
		}

		@Override
		public List<byte[]> mget(MazingJedisCluster jedis, List<byte[]> keys) {
			byte[][] ks = keys.toArray(new byte[keys.size()][]);
			return jedis.mget(ks);
		}

	}

	private static class JedisClusterString implements JK<JedisCluster, String> {

		@Override
		public Object foothold(JedisCluster jedis, String key) {
			return JedisClusterCRC16.getSlot(key);
			// return jedis.getJedisPoolFromKey(key);
		}

		@Override
		public List<String> mget(JedisCluster jedis, List<String> keys) {
			String[] ks = keys.toArray(new String[keys.size()]);
			return jedis.mget(ks);
		}

	}

	//

	@SuppressWarnings("unchecked")
	private static <V> List<V> toObj(List<byte[]> values) {
		if (null == values)
			return null;
		List<V> result = new ArrayList<>();
		for (byte[] body : values) {
			V v = null;
			if (null != body)
				try {
					v = (V) SerializeUtil.unserialize(body);
				} catch (Exception e) {
					throw new RuntimeException(e.getMessage(), e);
				}
			result.add(v);
		}
		return result;
	}

}
