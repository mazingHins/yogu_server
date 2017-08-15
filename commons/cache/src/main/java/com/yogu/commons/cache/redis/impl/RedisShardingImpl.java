package com.yogu.commons.cache.redis.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.NoSuchElementException;
import java.util.Set;

import javax.annotation.PreDestroy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.yogu.commons.cache.CacheTranscoder;
import com.yogu.commons.cache.redis.LoadingData;
import com.yogu.commons.cache.redis.Redis;
import com.yogu.commons.cache.redis.invoker.ShardingRedisCallback;
import com.yogu.commons.utils.CollectionUtils;
import com.yogu.commons.utils.JsonUtils;

import redis.clients.jedis.BinaryClient.LIST_POSITION;
import redis.clients.jedis.BitPosParams;
import redis.clients.jedis.GeoCoordinate;
import redis.clients.jedis.GeoRadiusResponse;
import redis.clients.jedis.GeoUnit;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPubSub;
import redis.clients.jedis.ScanParams;
import redis.clients.jedis.ScanResult;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.SortingParams;
import redis.clients.jedis.Tuple;
import redis.clients.jedis.exceptions.JedisConnectionException;
import redis.clients.jedis.params.geo.GeoRadiusParam;
import redis.clients.jedis.params.sortedset.ZAddParams;
import redis.clients.jedis.params.sortedset.ZIncrByParams;
import redis.clients.util.Pool;

/**
 * 多个redis实例， 一致性hash实现
 * 
 * @author hemeng
 *
 */
public class RedisShardingImpl implements Redis {

	private static final Logger logger = LoggerFactory.getLogger(RedisShardingImpl.class);

	private Pool<ShardedJedis> shardedJedisPool;
	// private CacheTranscoder transcoder;// 不允许为空，factory去保证

	public RedisShardingImpl(Pool<ShardedJedis> shardedJedisPool, CacheTranscoder transcoder, int maxActive, int initPoolSize) {
		logger.info("redis#init | ShardedJedisPool, maxActive: {}, initPoolSize: {}", maxActive, initPoolSize);
		this.shardedJedisPool = shardedJedisPool;

		if (initPoolSize <= 0)
			return;

		if (initPoolSize > maxActive)
			initPoolSize = maxActive;
		ShardedJedis[] shardedJedisArray = new ShardedJedis[initPoolSize];
		for (int i = 0; i < shardedJedisArray.length; i++)
			shardedJedisArray[i] = this.getResource();
		for (int i = 0; i < shardedJedisArray.length; i++)
			this.returnResource(shardedJedisArray[i]);
	}

	@Override
	public void set(String key, Object value) {
		set(key, value, defTimeout);
	}

	@Override
	public void set(String key, Object value, int seconds) {
		String string = null;
		if (null != value)// value为null，则set null进去
			string = JsonUtils.toJSONString(value);
		setex(key, seconds, string);
	}

	@Override
	public <T> T get(String key, Class<T> clz) {
		String value = get(key);
		if (null == value)// 这里只判断null，如果value是一个空字符串，那应该是set出了问题
			return null;
		return JsonUtils.parseObject(value, clz);
	}

	@Override
	public <K, V> List<V> mget(List<K> keys, LoadingData<K, V> load) {
		List<V> result = new ArrayList<>();
		if (CollectionUtils.isNotEmpty(keys)) {
			int size = keys.size();
			// 先批量去cache中取值
			byte[][] tmp = new byte[size][];
			for (int index = 0; index < size; index++) {
				K key = keys.get(index);
				String redisKey = load.toRedisKey(key);
				tmp[index] = redisKey.getBytes();
			}
			List<V> cacheList = mget(tmp);
			// 检查没有取到的，单独加载
			for (int index = 0; index < size; index++) {
				V value = cacheList.get(index);
				// 没有装载到的
				if (null == value) {
					K key = keys.get(index);
					value = load.sourceData(key);
				}
				result.add(value);
			}
		}
		return result;
	}

	@Override
	public List<String> mget(final String... keys) {
		return execute(new ShardingRedisCallback<List<String>>() {
			@Override
			public List<String> execute(ShardedJedis shardedJedis) {
				return RedisHandler.mgetBySharding(shardedJedis, keys);
			}
		});
	}

	@Override
	public <T> List<T> mget(final byte[]... keys) {
		return execute(new ShardingRedisCallback<List<T>>() {
			@Override
			public List<T> execute(ShardedJedis shardedJedis) {
				return RedisHandler.mgetBySharding(shardedJedis, keys);
			}
		});
	}

	@Override
	public String set(final String key, final String value) {
		/*return execute(key, new ShardingRedisCallback<String>() {
			@Override
			public String execute(ShardedJedis shardedJedis) {
				return shardedJedis.set(key, value);
			}
		});*/
		return setex(key, defTimeout, value);
	}

	@Override
	public String set(final byte[] key, final byte[] value) {
		/*return execute(key, new ShardingRedisCallback<String>() {
			@Override
			public String execute(ShardedJedis shardedJedis) {
				return shardedJedis.set(key, value);
			}
		});*/
		return setex(key, defTimeout, value);
	}

	@Override
	public String get(final String key) {
		return execute(key, new ShardingRedisCallback<String>() {
			@Override
			public String execute(ShardedJedis shardedJedis) {
				return shardedJedis.get(key);
			}
		});
	}

	@Override
	public byte[] get(final byte[] key) {
		return execute(key, new ShardingRedisCallback<byte[]>() {
			@Override
			public byte[] execute(ShardedJedis shardedJedis) {
				return shardedJedis.get(key);
			}
		});
	}

	@Override
	public Boolean exists(final String key) {
		return execute(key, new ShardingRedisCallback<Boolean>() {
			@Override
			public Boolean execute(ShardedJedis shardedJedis) {
				return shardedJedis.exists(key);
			}
		});
	}

	@Override
	public Long persist(final String key) {
		return execute(key, new ShardingRedisCallback<Long>() {
			@Override
			public Long execute(ShardedJedis shardedJedis) {
				return shardedJedis.persist(key);
			}
		});
	}

	@Override
	public String type(final String key) {
		return execute(key, new ShardingRedisCallback<String>() {
			@Override
			public String execute(ShardedJedis shardedJedis) {
				return shardedJedis.type(key);
			}
		});
	}

	@Override
	public Long expire(final String key, final int seconds) {
		return execute(key, new ShardingRedisCallback<Long>() {
			@Override
			public Long execute(ShardedJedis shardedJedis) {
				return shardedJedis.expire(key, seconds);
			}
		});
	}

	@Override
	public Long expireAt(final String key, final long unixTime) {
		return execute(key, new ShardingRedisCallback<Long>() {
			@Override
			public Long execute(ShardedJedis shardedJedis) {
				return shardedJedis.expireAt(key, unixTime);
			}
		});
	}

	@Override
	public Long ttl(final String key) {
		return execute(key, new ShardingRedisCallback<Long>() {
			@Override
			public Long execute(ShardedJedis shardedJedis) {
				return shardedJedis.ttl(key);
			}
		});
	}

	@Override
	public Boolean setbit(final String key, final long offset, final boolean value) {
		return execute(key, new ShardingRedisCallback<Boolean>() {
			@Override
			public Boolean execute(ShardedJedis shardedJedis) {
				return shardedJedis.setbit(key, offset, value);
			}
		});
	}

	@Override
	public Boolean setbit(final String key, final long offset, final String value) {
		return execute(key, new ShardingRedisCallback<Boolean>() {
			@Override
			public Boolean execute(ShardedJedis shardedJedis) {
				return shardedJedis.setbit(key, offset, value);
			}
		});
	}

	@Override
	public Boolean getbit(final String key, final long offset) {
		return execute(key, new ShardingRedisCallback<Boolean>() {
			@Override
			public Boolean execute(ShardedJedis shardedJedis) {
				return shardedJedis.getbit(key, offset);
			}
		});
	}

	@Override
	public Long setrange(final String key, final long offset, final String value) {
		return execute(key, new ShardingRedisCallback<Long>() {
			@Override
			public Long execute(ShardedJedis shardedJedis) {
				return shardedJedis.setrange(key, offset, value);
			}
		});
	}

	@Override
	public String getrange(final String key, final long startOffset, final long endOffset) {
		return execute(key, new ShardingRedisCallback<String>() {
			@Override
			public String execute(ShardedJedis shardedJedis) {
				return shardedJedis.getrange(key, startOffset, endOffset);
			}
		});
	}

	@Override
	public String getSet(final String key, final String value) {
		return execute(key, new ShardingRedisCallback<String>() {
			@Override
			public String execute(ShardedJedis shardedJedis) {
				return shardedJedis.getSet(key, value);
			}
		});
	}

	@Override
	public Long setnx(final String key, final String value) {
		return execute(key, new ShardingRedisCallback<Long>() {
			@Override
			public Long execute(ShardedJedis shardedJedis) {
				return shardedJedis.setnx(key, value);
			}
		});
	}

	@Override
	public String setex(final String key, final int seconds, final String value) {
		return execute(key, new ShardingRedisCallback<String>() {
			@Override
			public String execute(ShardedJedis shardedJedis) {
				return shardedJedis.setex(key, seconds, value);
			}
		});
	}

	@Override
	public String setex(final byte[] key, final int seconds, final byte[] value) {
		return execute(key, new ShardingRedisCallback<String>() {
			@Override
			public String execute(ShardedJedis shardedJedis) {
				return shardedJedis.setex(key, seconds, value);
			}
		});
	}

	@Override
	public Long decrBy(final String key, final long integer) {
		return execute(key, new ShardingRedisCallback<Long>() {
			@Override
			public Long execute(ShardedJedis shardedJedis) {
				return shardedJedis.decrBy(key, integer);
			}
		});
	}

	@Override
	public Long decr(final String key) {
		return execute(key, new ShardingRedisCallback<Long>() {
			@Override
			public Long execute(ShardedJedis shardedJedis) {
				return shardedJedis.decr(key);
			}
		});
	}

	@Override
	public Long incrBy(final String key, final long integer) {
		return execute(key, new ShardingRedisCallback<Long>() {
			@Override
			public Long execute(ShardedJedis shardedJedis) {
				return shardedJedis.incrBy(key, integer);
			}
		});
	}

	@Override
	public Long incr(final String key) {
		return execute(key, new ShardingRedisCallback<Long>() {
			@Override
			public Long execute(ShardedJedis shardedJedis) {
				return shardedJedis.incr(key);
			}
		});
	}

	@Override
	public Long append(final String key, final String value) {
		return execute(key, new ShardingRedisCallback<Long>() {
			@Override
			public Long execute(ShardedJedis shardedJedis) {
				return shardedJedis.append(key, value);
			}
		});
	}

	@Override
	public String substr(final String key, final int start, final int end) {
		return execute(key, new ShardingRedisCallback<String>() {
			@Override
			public String execute(ShardedJedis shardedJedis) {
				return shardedJedis.substr(key, start, end);
			}
		});
	}

	@Override
	public Long hset(final String key, final String field, final String value) {
		return execute(key, new ShardingRedisCallback<Long>() {
			@Override
			public Long execute(ShardedJedis shardedJedis) {
				return shardedJedis.hset(key, field, value);
			}
		});
	}

	@Override
	public String hget(final String key, final String field) {
		return execute(key, new ShardingRedisCallback<String>() {
			@Override
			public String execute(ShardedJedis shardedJedis) {
				return shardedJedis.hget(key, field);
			}
		});
	}

	@Override
	public Long hsetnx(final String key, final String field, final String value) {
		return execute(key, new ShardingRedisCallback<Long>() {
			@Override
			public Long execute(ShardedJedis shardedJedis) {
				return shardedJedis.hsetnx(key, field, value);
			}
		});
	}

	@Override
	public String hmset(final String key, final Map<String, String> hash) {
		return execute(key, new ShardingRedisCallback<String>() {
			@Override
			public String execute(ShardedJedis shardedJedis) {
				return shardedJedis.hmset(key, hash);
			}
		});
	}

	@Override
	public List<String> hmget(final String key, final String... fields) {
		return execute(key, new ShardingRedisCallback<List<String>>() {
			@Override
			public List<String> execute(ShardedJedis shardedJedis) {
				return shardedJedis.hmget(key, fields);
			}
		});
	}

	@Override
	public Long hincrBy(final String key, final String field, final long value) {
		return execute(key, new ShardingRedisCallback<Long>() {
			@Override
			public Long execute(ShardedJedis shardedJedis) {
				return shardedJedis.hincrBy(key, field, value);
			}
		});
	}

	@Override
	public Boolean hexists(final String key, final String field) {
		return execute(key, new ShardingRedisCallback<Boolean>() {
			@Override
			public Boolean execute(ShardedJedis shardedJedis) {
				return shardedJedis.hexists(key, field);
			}
		});
	}

	@Override
	public Long hdel(final String key, final String... fields) {
		return execute(key, new ShardingRedisCallback<Long>() {
			@Override
			public Long execute(ShardedJedis shardedJedis) {
				return shardedJedis.hdel(key, fields);
			}
		});
	}

	@Override
	public Long hlen(final String key) {
		return execute(key, new ShardingRedisCallback<Long>() {
			@Override
			public Long execute(ShardedJedis shardedJedis) {
				return shardedJedis.hlen(key);
			}
		});
	}

	@Override
	public Set<String> hkeys(final String key) {
		return execute(key, new ShardingRedisCallback<Set<String>>() {
			@Override
			public Set<String> execute(ShardedJedis shardedJedis) {
				return shardedJedis.hkeys(key);
			}
		});
	}

	@Override
	public List<String> hvals(final String key) {
		return execute(key, new ShardingRedisCallback<List<String>>() {
			@Override
			public List<String> execute(ShardedJedis shardedJedis) {
				return shardedJedis.hvals(key);
			}
		});
	}

	@Override
	public Map<String, String> hgetAll(final String key) {
		return execute(key, new ShardingRedisCallback<Map<String, String>>() {
			@Override
			public Map<String, String> execute(ShardedJedis shardedJedis) {
				return shardedJedis.hgetAll(key);
			}
		});
	}

	@Override
	public Long rpush(final String key, final String... strings) {
		return execute(key, new ShardingRedisCallback<Long>() {
			@Override
			public Long execute(ShardedJedis shardedJedis) {
				return shardedJedis.rpush(key, strings);
			}
		});
	}

	@Override
	public Long lpush(final String key, final String... strings) {
		return execute(key, new ShardingRedisCallback<Long>() {
			@Override
			public Long execute(ShardedJedis shardedJedis) {
				return shardedJedis.lpush(key, strings);
			}
		});
	}

	@Override
	public Long llen(final String key) {
		return execute(key, new ShardingRedisCallback<Long>() {
			@Override
			public Long execute(ShardedJedis shardedJedis) {
				return shardedJedis.llen(key);
			}
		});
	}

	@Override
	public List<String> lrange(final String key, final long start, final long end) {
		return execute(key, new ShardingRedisCallback<List<String>>() {
			@Override
			public List<String> execute(ShardedJedis shardedJedis) {
				return shardedJedis.lrange(key, start, end);
			}
		});
	}

	@Override
	public String ltrim(final String key, final long start, final long end) {
		return execute(key, new ShardingRedisCallback<String>() {
			@Override
			public String execute(ShardedJedis shardedJedis) {
				return shardedJedis.ltrim(key, start, end);
			}
		});
	}

	@Override
	public String lindex(final String key, final long index) {
		return execute(key, new ShardingRedisCallback<String>() {
			@Override
			public String execute(ShardedJedis shardedJedis) {
				return shardedJedis.lindex(key, index);
			}
		});
	}

	@Override
	public String lset(final String key, final long index, final String value) {
		return execute(key, new ShardingRedisCallback<String>() {
			@Override
			public String execute(ShardedJedis shardedJedis) {
				return shardedJedis.lset(key, index, value);
			}
		});
	}

	@Override
	public Long lrem(final String key, final long count, final String value) {
		return execute(key, new ShardingRedisCallback<Long>() {
			@Override
			public Long execute(ShardedJedis shardedJedis) {
				return shardedJedis.lrem(key, count, value);
			}
		});
	}

	@Override
	public String lpop(final String key) {
		return execute(key, new ShardingRedisCallback<String>() {
			@Override
			public String execute(ShardedJedis shardedJedis) {
				return shardedJedis.lpop(key);
			}
		});
	}

	@Override
	public String rpop(final String key) {
		return execute(key, new ShardingRedisCallback<String>() {
			@Override
			public String execute(ShardedJedis shardedJedis) {
				return shardedJedis.rpop(key);
			}
		});
	}

	@Override
	public Long sadd(final String key, final String... members) {
		return execute(key, new ShardingRedisCallback<Long>() {
			@Override
			public Long execute(ShardedJedis shardedJedis) {
				return shardedJedis.sadd(key, members);
			}
		});
	}

	@Override
	public Set<String> smembers(final String key) {
		return execute(key, new ShardingRedisCallback<Set<String>>() {
			@Override
			public Set<String> execute(ShardedJedis shardedJedis) {
				return shardedJedis.smembers(key);
			}
		});
	}

	@Override
	public Long srem(final String key, final String... members) {
		return execute(key, new ShardingRedisCallback<Long>() {
			@Override
			public Long execute(ShardedJedis shardedJedis) {
				return shardedJedis.srem(key, members);
			}
		});
	}

	@Override
	public String spop(final String key) {
		return execute(key, new ShardingRedisCallback<String>() {
			@Override
			public String execute(ShardedJedis shardedJedis) {
				return shardedJedis.spop(key);
			}
		});
	}

	@Override
	public Long scard(final String key) {
		return execute(key, new ShardingRedisCallback<Long>() {
			@Override
			public Long execute(ShardedJedis shardedJedis) {
				return shardedJedis.scard(key);
			}
		});
	}

	@Override
	public Boolean sismember(final String key, final String member) {
		return execute(key, new ShardingRedisCallback<Boolean>() {
			@Override
			public Boolean execute(ShardedJedis shardedJedis) {
				return shardedJedis.sismember(key, member);
			}
		});
	}

	@Override
	public String srandmember(final String key) {
		return execute(key, new ShardingRedisCallback<String>() {
			@Override
			public String execute(ShardedJedis shardedJedis) {
				return shardedJedis.srandmember(key);
			}
		});
	}

	@Override
	public Long strlen(final String key) {
		return execute(key, new ShardingRedisCallback<Long>() {
			@Override
			public Long execute(ShardedJedis shardedJedis) {
				return shardedJedis.strlen(key);
			}
		});
	}

	@Override
	public Long zadd(final String key, final double score, final String member) {
		return execute(key, new ShardingRedisCallback<Long>() {
			@Override
			public Long execute(ShardedJedis shardedJedis) {
				return shardedJedis.zadd(key, score, member);
			}
		});
	}

	@Override
	public Long zadd(final String key, final Map<String, Double> scoreMembers) {
		return execute(key, new ShardingRedisCallback<Long>() {
			@Override
			public Long execute(ShardedJedis shardedJedis) {
				return shardedJedis.zadd(key, scoreMembers);
			}
		});
	}

	@Override
	public Set<String> zrange(final String key, final long start, final long end) {
		return execute(key, new ShardingRedisCallback<Set<String>>() {
			@Override
			public Set<String> execute(ShardedJedis shardedJedis) {
				return shardedJedis.zrange(key, start, end);
			}
		});
	}

	@Override
	public Long zrem(final String key, final String... members) {
		return execute(key, new ShardingRedisCallback<Long>() {
			@Override
			public Long execute(ShardedJedis shardedJedis) {
				return shardedJedis.zrem(key, members);
			}
		});
	}

	@Override
	public Double zincrby(final String key, final double score, final String member) {
		return execute(key, new ShardingRedisCallback<Double>() {
			@Override
			public Double execute(ShardedJedis shardedJedis) {
				return shardedJedis.zincrby(key, score, member);
			}
		});
	}

	@Override
	public Long zrank(final String key, final String member) {
		return execute(key, new ShardingRedisCallback<Long>() {
			@Override
			public Long execute(ShardedJedis shardedJedis) {
				return shardedJedis.zrank(key, member);
			}
		});
	}

	@Override
	public Long zrevrank(final String key, final String member) {
		return execute(key, new ShardingRedisCallback<Long>() {
			@Override
			public Long execute(ShardedJedis shardedJedis) {
				return shardedJedis.zrevrank(key, member);
			}
		});
	}

	@Override
	public Set<String> zrevrange(final String key, final long start, final long end) {
		return execute(key, new ShardingRedisCallback<Set<String>>() {
			@Override
			public Set<String> execute(ShardedJedis shardedJedis) {
				return shardedJedis.zrevrange(key, start, end);
			}
		});
	}

	@Override
	public Set<Tuple> zrangeWithScores(final String key, final long start, final long end) {
		return execute(key, new ShardingRedisCallback<Set<Tuple>>() {
			@Override
			public Set<Tuple> execute(ShardedJedis shardedJedis) {
				return shardedJedis.zrangeWithScores(key, start, end);
			}
		});
	}

	@Override
	public Set<Tuple> zrevrangeWithScores(final String key, final long start, final long end) {
		return execute(key, new ShardingRedisCallback<Set<Tuple>>() {
			@Override
			public Set<Tuple> execute(ShardedJedis shardedJedis) {
				return shardedJedis.zrevrangeWithScores(key, start, end);
			}
		});
	}

	@Override
	public Long zcard(final String key) {
		return execute(key, new ShardingRedisCallback<Long>() {
			@Override
			public Long execute(ShardedJedis shardedJedis) {
				return shardedJedis.zcard(key);
			}
		});
	}

	@Override
	public Double zscore(final String key, final String member) {
		return execute(key, new ShardingRedisCallback<Double>() {
			@Override
			public Double execute(ShardedJedis shardedJedis) {
				return shardedJedis.zscore(key, member);
			}
		});
	}

	@Override
	public List<String> sort(final String key) {
		return execute(key, new ShardingRedisCallback<List<String>>() {
			@Override
			public List<String> execute(ShardedJedis shardedJedis) {
				return shardedJedis.sort(key);
			}
		});
	}

	@Override
	public List<String> sort(final String key, final SortingParams sortingParameters) {
		return execute(key, new ShardingRedisCallback<List<String>>() {
			@Override
			public List<String> execute(ShardedJedis shardedJedis) {
				return shardedJedis.sort(key, sortingParameters);
			}
		});
	}

	@Override
	public Long zcount(final String key, final double min, final double max) {
		return execute(key, new ShardingRedisCallback<Long>() {
			@Override
			public Long execute(ShardedJedis shardedJedis) {
				return shardedJedis.zcount(key, min, max);
			}
		});
	}

	@Override
	public Long zcount(final String key, final String min, final String max) {
		return execute(key, new ShardingRedisCallback<Long>() {
			@Override
			public Long execute(ShardedJedis shardedJedis) {
				return shardedJedis.zcount(key, min, max);
			}
		});
	}

	@Override
	public Set<String> zrangeByScore(final String key, final double min, final double max) {
		return execute(key, new ShardingRedisCallback<Set<String>>() {
			@Override
			public Set<String> execute(ShardedJedis shardedJedis) {
				return shardedJedis.zrangeByScore(key, min, max);
			}
		});
	}

	@Override
	public Set<String> zrangeByScore(final String key, final String min, final String max) {
		return execute(key, new ShardingRedisCallback<Set<String>>() {
			@Override
			public Set<String> execute(ShardedJedis shardedJedis) {
				return shardedJedis.zrangeByScore(key, min, max);
			}
		});
	}

	@Override
	public Set<String> zrevrangeByScore(final String key, final double max, final double min) {
		return execute(key, new ShardingRedisCallback<Set<String>>() {
			@Override
			public Set<String> execute(ShardedJedis shardedJedis) {
				return shardedJedis.zrevrangeByScore(key, max, min);
			}
		});
	}

	@Override
	public Set<String> zrangeByScore(final String key, final double min, final double max, final int offset, final int count) {
		return execute(key, new ShardingRedisCallback<Set<String>>() {
			@Override
			public Set<String> execute(ShardedJedis shardedJedis) {
				return shardedJedis.zrangeByScore(key, min, max, offset, count);
			}
		});
	}

	@Override
	public Set<String> zrevrangeByScore(final String key, final String max, final String min) {
		return execute(key, new ShardingRedisCallback<Set<String>>() {
			@Override
			public Set<String> execute(ShardedJedis shardedJedis) {
				return shardedJedis.zrevrangeByScore(key, max, min);
			}
		});
	}

	@Override
	public Set<String> zrangeByScore(final String key, final String min, final String max, final int offset, final int count) {
		return execute(key, new ShardingRedisCallback<Set<String>>() {
			@Override
			public Set<String> execute(ShardedJedis shardedJedis) {
				return shardedJedis.zrangeByScore(key, min, max, offset, count);
			}
		});
	}

	@Override
	public Set<String> zrevrangeByScore(final String key, final double max, final double min, final int offset, final int count) {
		return execute(key, new ShardingRedisCallback<Set<String>>() {
			@Override
			public Set<String> execute(ShardedJedis shardedJedis) {
				return shardedJedis.zrevrangeByScore(key, max, min, offset, count);
			}
		});
	}

	@Override
	public Set<Tuple> zrangeByScoreWithScores(final String key, final double min, final double max) {
		return execute(key, new ShardingRedisCallback<Set<Tuple>>() {
			@Override
			public Set<Tuple> execute(ShardedJedis shardedJedis) {
				return shardedJedis.zrangeByScoreWithScores(key, min, max);
			}
		});
	}

	@Override
	public Set<Tuple> zrevrangeByScoreWithScores(final String key, final double max, final double min) {
		return execute(key, new ShardingRedisCallback<Set<Tuple>>() {
			@Override
			public Set<Tuple> execute(ShardedJedis shardedJedis) {
				return shardedJedis.zrevrangeByScoreWithScores(key, max, min);
			}
		});
	}

	@Override
	public Set<Tuple> zrangeByScoreWithScores(final String key, final double min, final double max, final int offset, final int count) {
		return execute(key, new ShardingRedisCallback<Set<Tuple>>() {
			@Override
			public Set<Tuple> execute(ShardedJedis shardedJedis) {
				return shardedJedis.zrangeByScoreWithScores(key, min, max, offset, count);
			}
		});
	}

	@Override
	public Set<String> zrevrangeByScore(final String key, final String max, final String min, final int offset, final int count) {
		return execute(key, new ShardingRedisCallback<Set<String>>() {
			@Override
			public Set<String> execute(ShardedJedis shardedJedis) {
				return shardedJedis.zrevrangeByScore(key, max, min, offset, count);
			}
		});
	}

	@Override
	public Set<Tuple> zrangeByScoreWithScores(final String key, final String min, final String max) {
		return execute(key, new ShardingRedisCallback<Set<Tuple>>() {
			@Override
			public Set<Tuple> execute(ShardedJedis shardedJedis) {
				return shardedJedis.zrangeByScoreWithScores(key, min, max);
			}
		});
	}

	@Override
	public Set<Tuple> zrevrangeByScoreWithScores(final String key, final String max, final String min) {
		return execute(key, new ShardingRedisCallback<Set<Tuple>>() {
			@Override
			public Set<Tuple> execute(ShardedJedis shardedJedis) {
				return shardedJedis.zrevrangeByScoreWithScores(key, max, min);
			}
		});
	}

	@Override
	public Set<Tuple> zrangeByScoreWithScores(final String key, final String min, final String max, final int offset, final int count) {
		return execute(key, new ShardingRedisCallback<Set<Tuple>>() {
			@Override
			public Set<Tuple> execute(ShardedJedis shardedJedis) {
				return shardedJedis.zrangeByScoreWithScores(key, min, max, offset, count);
			}
		});
	}

	@Override
	public Set<Tuple> zrevrangeByScoreWithScores(final String key, final double max, final double min, final int offset, final int count) {
		return execute(key, new ShardingRedisCallback<Set<Tuple>>() {
			@Override
			public Set<Tuple> execute(ShardedJedis shardedJedis) {
				return shardedJedis.zrevrangeByScoreWithScores(key, max, min, offset, count);
			}
		});
	}

	@Override
	public Set<Tuple> zrevrangeByScoreWithScores(final String key, final String max, final String min, final int offset, final int count) {
		return execute(key, new ShardingRedisCallback<Set<Tuple>>() {
			@Override
			public Set<Tuple> execute(ShardedJedis shardedJedis) {
				return shardedJedis.zrevrangeByScoreWithScores(key, max, min, offset, count);
			}
		});
	}

	@Override
	public Long zremrangeByRank(final String key, final long start, final long end) {
		return execute(key, new ShardingRedisCallback<Long>() {
			@Override
			public Long execute(ShardedJedis shardedJedis) {
				return shardedJedis.zremrangeByRank(key, start, end);
			}
		});
	}

	@Override
	public Long zremrangeByScore(final String key, final double start, final double end) {
		return execute(key, new ShardingRedisCallback<Long>() {
			@Override
			public Long execute(ShardedJedis shardedJedis) {
				return shardedJedis.zremrangeByScore(key, start, end);
			}
		});
	}

	@Override
	public Long zremrangeByScore(final String key, final String start, final String end) {
		return execute(key, new ShardingRedisCallback<Long>() {
			@Override
			public Long execute(ShardedJedis shardedJedis) {
				return shardedJedis.zremrangeByScore(key, start, end);
			}
		});
	}

	@Override
	public Long linsert(final String key, final LIST_POSITION where, final String pivot, final String value) {
		return execute(key, new ShardingRedisCallback<Long>() {
			@Override
			public Long execute(ShardedJedis shardedJedis) {
				return shardedJedis.linsert(key, where, pivot, value);
			}
		});
	}

	@Override
	public Long lpushx(final String key, final String... string) {
		return execute(key, new ShardingRedisCallback<Long>() {
			@Override
			public Long execute(ShardedJedis shardedJedis) {
				return shardedJedis.lpushx(key, string);
			}
		});
	}

	@Override
	public Long rpushx(final String key, final String... string) {
		return execute(key, new ShardingRedisCallback<Long>() {
			@Override
			public Long execute(ShardedJedis shardedJedis) {
				return shardedJedis.lpushx(key, string);
			}
		});
	}

	@Override
	public List<String> blpop(final String arg) {
		return execute(new ShardingRedisCallback<List<String>>() {
			@Override
			public List<String> execute(ShardedJedis shardedJedis) {
				return shardedJedis.blpop(arg);
			}
		});
	}

	@Override
	public List<String> brpop(final String arg) {
		return execute(new ShardingRedisCallback<List<String>>() {
			@Override
			public List<String> execute(ShardedJedis shardedJedis) {
				return shardedJedis.brpop(arg);
			}
		});
	}

	@Override
	public Long del(final String key) {
		return execute(key, new ShardingRedisCallback<Long>() {
			@Override
			public Long execute(ShardedJedis shardedJedis) {
				return shardedJedis.del(key);
			}
		});
	}

	@Override
	public Long del(final byte[] key) {
		return execute(key, new ShardingRedisCallback<Long>() {
			@Override
			public Long execute(ShardedJedis shardedJedis) {
				return shardedJedis.del(key);
			}
		});
	}

	@Override
	public String echo(final String string) {
		return execute(new ShardingRedisCallback<String>() {
			@Override
			public String execute(ShardedJedis shardedJedis) {
				return shardedJedis.echo(string);
			}
		});
	}

	@Override
	public Long move(final String key, final int dbIndex) {
		return execute(key, new ShardingRedisCallback<Long>() {
			@Override
			public Long execute(ShardedJedis shardedJedis) {
				return shardedJedis.move(key, dbIndex);
			}
		});
	}

	@Override
	public Long bitcount(final String key) {
		return execute(key, new ShardingRedisCallback<Long>() {
			@Override
			public Long execute(ShardedJedis shardedJedis) {
				return shardedJedis.bitcount(key);
			}
		});
	}

	@Override
	public Long bitcount(final String key, final long start, final long end) {
		return execute(key, new ShardingRedisCallback<Long>() {
			@Override
			public Long execute(ShardedJedis shardedJedis) {
				return shardedJedis.bitcount(key, start, end);
			}
		});
	}

	@Override
	public String getHost(final String key) {
		return execute(key, new ShardingRedisCallback<String>() {
			@Override
			public String execute(ShardedJedis shardedJedis) {
				return shardedJedis.getShard(key).getClient().getHost();
			}
		});
	}

	@Override
	public Integer getPort(final String key) {
		return execute(key, new ShardingRedisCallback<Integer>() {
			@Override
			public Integer execute(ShardedJedis shardedJedis) {
				return shardedJedis.getShard(key).getClient().getPort();
			}
		});
	}

	@Override
	public String flushDB() {
		ShardedJedis shardedJedis = this.shardedJedisPool.getResource();
		Collection<Jedis> list = shardedJedis.getAllShards();
		String result = null;
		try {
			for (Jedis jedis : list) {
				try {
					result = jedis.flushDB();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		} catch (Exception e) {
			// ignore
		} finally {
			// this.shardedJedisPool.returnResource(shardedJedis);
			shardedJedis.close();
		}
		return result;
	}

	@Override
	public Long publish(String channel, String message) {
		throw new IllegalStateException("Not supported");
	}

	@Override
	public void subscribe(JedisPubSub jedisPubSub, String... channels) {
		throw new IllegalStateException("Not supported");
	}

	@Override
	public void psubscribe(JedisPubSub jedisPubSub, String... patterns) {
		throw new IllegalStateException("Not supported");
	}

	@Override
	public String set(final String key, final String value, final String nxxx, final String expx, final long time) {
		return execute(key, new ShardingRedisCallback<String>() {
			@Override
			public String execute(ShardedJedis shardedJedis) {
				return shardedJedis.set(key, value, nxxx, expx, time);
			}
		});
	}

	@Override
	public Long pexpire(final String key, final long milliseconds) {
		return execute(key, new ShardingRedisCallback<Long>() {
			@Override
			public Long execute(ShardedJedis shardedJedis) {
				return shardedJedis.pexpire(key, milliseconds);
			}
		});
	}

	@Override
	public Long pexpireAt(final String key, final long millisecondsTimestamp) {
		return execute(key, new ShardingRedisCallback<Long>() {
			@Override
			public Long execute(ShardedJedis shardedJedis) {
				return shardedJedis.pexpireAt(key, millisecondsTimestamp);
			}
		});
	}

	@Override
	public Double incrByFloat(final String key, final double value) {
		return execute(key, new ShardingRedisCallback<Double>() {
			@Override
			public Double execute(ShardedJedis shardedJedis) {
				return shardedJedis.incrByFloat(key, value);
			}
		});
	}

	@Override
	public Set<String> spop(final String key, final long count) {
		return execute(key, new ShardingRedisCallback<Set<String>>() {
			@Override
			public Set<String> execute(ShardedJedis shardedJedis) {
				return shardedJedis.spop(key, count);
			}
		});
	}

	@Override
	public List<String> srandmember(final String key, final int count) {
		return execute(key, new ShardingRedisCallback<List<String>>() {
			@Override
			public List<String> execute(ShardedJedis shardedJedis) {
				return shardedJedis.srandmember(key, count);
			}
		});
	}

	@Override
	public Long zlexcount(final String key, final String min, final String max) {
		return execute(key, new ShardingRedisCallback<Long>() {
			@Override
			public Long execute(ShardedJedis shardedJedis) {
				return shardedJedis.zlexcount(key, min, max);
			}
		});
	}

	@Override
	public Set<String> zrangeByLex(final String key, final String min, final String max) {
		return execute(key, new ShardingRedisCallback<Set<String>>() {
			@Override
			public Set<String> execute(ShardedJedis shardedJedis) {
				return shardedJedis.zrangeByLex(key, min, max);
			}
		});
	}

	@Override
	public Set<String> zrangeByLex(final String key, final String min, final String max, final int offset, final int count) {
		return execute(key, new ShardingRedisCallback<Set<String>>() {
			@Override
			public Set<String> execute(ShardedJedis shardedJedis) {
				return shardedJedis.zrangeByLex(key, min, max, offset, count);
			}
		});
	}

	@Override
	public Set<String> zrevrangeByLex(final String key, final String max, final String min) {
		return execute(key, new ShardingRedisCallback<Set<String>>() {
			@Override
			public Set<String> execute(ShardedJedis shardedJedis) {
				return shardedJedis.zrevrangeByLex(key, max, min);
			}
		});
	}

	@Override
	public Set<String> zrevrangeByLex(final String key, final String max, final String min, final int offset, final int count) {
		return execute(key, new ShardingRedisCallback<Set<String>>() {
			@Override
			public Set<String> execute(ShardedJedis shardedJedis) {
				return shardedJedis.zrevrangeByLex(key, max, min, offset, count);
			}
		});
	}

	@Override
	public Long zremrangeByLex(final String key, final String min, final String max) {
		return execute(key, new ShardingRedisCallback<Long>() {
			@Override
			public Long execute(ShardedJedis shardedJedis) {
				return shardedJedis.zremrangeByLex(key, min, max);
			}
		});
	}

	@Override
	public List<String> blpop(final int timeout, final String key) {
		return execute(key, new ShardingRedisCallback<List<String>>() {
			@Override
			public List<String> execute(ShardedJedis shardedJedis) {
				return shardedJedis.blpop(timeout, key);
			}
		});
	}

	@Override
	public List<String> brpop(final int timeout, final String key) {
		return execute(key, new ShardingRedisCallback<List<String>>() {
			@Override
			public List<String> execute(ShardedJedis shardedJedis) {
				return shardedJedis.brpop(timeout, key);
			}
		});
	}

	@Override
	@Deprecated
	public ScanResult<Entry<String, String>> hscan(final String key, final int cursor) {
		return execute(key, new ShardingRedisCallback<ScanResult<Entry<String, String>>>() {
			@Override
			public ScanResult<Entry<String, String>> execute(ShardedJedis shardedJedis) {
				return shardedJedis.hscan(key, cursor);
			}
		});
	}

	@Override
	@Deprecated
	public ScanResult<String> sscan(final String key, final int cursor) {
		return execute(key, new ShardingRedisCallback<ScanResult<String>>() {
			@Override
			public ScanResult<String> execute(ShardedJedis shardedJedis) {
				return shardedJedis.sscan(key, cursor);
			}
		});
	}

	@Override
	@Deprecated
	public ScanResult<Tuple> zscan(final String key, final int cursor) {
		return execute(key, new ShardingRedisCallback<ScanResult<Tuple>>() {
			@Override
			public ScanResult<Tuple> execute(ShardedJedis shardedJedis) {
				return shardedJedis.zscan(key, cursor);
			}
		});
	}

	@Override
	public ScanResult<Entry<String, String>> hscan(final String key, final String cursor) {
		return execute(key, new ShardingRedisCallback<ScanResult<Entry<String, String>>>() {
			@Override
			public ScanResult<Entry<String, String>> execute(ShardedJedis shardedJedis) {
				return shardedJedis.hscan(key, cursor);
			}
		});
	}

	@Override
	public ScanResult<String> sscan(final String key, final String cursor) {
		return execute(key, new ShardingRedisCallback<ScanResult<String>>() {
			@Override
			public ScanResult<String> execute(ShardedJedis shardedJedis) {
				return shardedJedis.sscan(key, cursor);
			}
		});
	}

	@Override
	public ScanResult<Tuple> zscan(final String key, final String cursor) {
		return execute(key, new ShardingRedisCallback<ScanResult<Tuple>>() {
			@Override
			public ScanResult<Tuple> execute(ShardedJedis shardedJedis) {
				return shardedJedis.zscan(key, cursor);
			}
		});
	}

	@Override
	public Long pfadd(final String key, final String... elements) {
		return execute(key, new ShardingRedisCallback<Long>() {
			@Override
			public Long execute(ShardedJedis shardedJedis) {
				return shardedJedis.pfadd(key, elements);
			}
		});
	}

	@Override
	public long pfcount(final String key) {
		return execute(key, new ShardingRedisCallback<Long>() {
			@Override
			public Long execute(ShardedJedis shardedJedis) {
				return shardedJedis.pfcount(key);
			}
		});
	}

	@Override
	public String set(final String key, final String value, final String nxxx) {
		return execute(key, new ShardingRedisCallback<String>() {
			@Override
			public String execute(ShardedJedis shardedJedis) {
				return shardedJedis.set(key, value, nxxx);
			}
		});
	}

	@Override
	public Long pttl(final String key) {
		return execute(key, new ShardingRedisCallback<Long>() {
			@Override
			public Long execute(ShardedJedis shardedJedis) {
				return shardedJedis.pttl(key);
			}
		});
	}

	@Override
	public String psetex(final String key, final long milliseconds, final String value) {
		return execute(key, new ShardingRedisCallback<String>() {
			@Override
			public String execute(ShardedJedis shardedJedis) {
				return shardedJedis.psetex(key, milliseconds, value);
			}
		});
	}

	@Override
	public Double hincrByFloat(final String key, final String field, final double value) {
		return execute(key, new ShardingRedisCallback<Double>() {
			@Override
			public Double execute(ShardedJedis shardedJedis) {
				return shardedJedis.hincrByFloat(key, field, value);
			}
		});
	}

	@Override
	public Long zadd(final String key, final double score, final String member, final ZAddParams params) {
		return execute(key, new ShardingRedisCallback<Long>() {
			@Override
			public Long execute(ShardedJedis shardedJedis) {
				return shardedJedis.zadd(key, score, member, params);
			}
		});
	}

	@Override
	public Long zadd(final String key, final Map<String, Double> scoreMembers, final ZAddParams params) {
		return execute(key, new ShardingRedisCallback<Long>() {
			@Override
			public Long execute(ShardedJedis shardedJedis) {
				return shardedJedis.zadd(key, scoreMembers, params);
			}
		});
	}

	@Override
	public Double zincrby(final String key, final double score, final String member, final ZIncrByParams params) {
		return execute(key, new ShardingRedisCallback<Double>() {
			@Override
			public Double execute(ShardedJedis shardedJedis) {
				return shardedJedis.zincrby(key, score, member, params);
			}
		});
	}

	@Override
	public Long bitpos(final String key, final boolean value) {
		return execute(key, new ShardingRedisCallback<Long>() {
			@Override
			public Long execute(ShardedJedis shardedJedis) {
				return shardedJedis.bitpos(key, value);
			}
		});
	}

	@Override
	public Long bitpos(final String key, final boolean value, final BitPosParams params) {
		return execute(key, new ShardingRedisCallback<Long>() {
			@Override
			public Long execute(ShardedJedis shardedJedis) {
				return shardedJedis.bitpos(key, value, params);
			}
		});
	}

	@Override
	public ScanResult<Entry<String, String>> hscan(final String key, final String cursor, final ScanParams params) {
		return execute(key, new ShardingRedisCallback<ScanResult<Entry<String, String>>>() {
			@Override
			public ScanResult<Entry<String, String>> execute(ShardedJedis shardedJedis) {
				return shardedJedis.hscan(key, cursor, params);
			}
		});
	}

	@Override
	public ScanResult<String> sscan(final String key, final String cursor, final ScanParams params) {
		return execute(key, new ShardingRedisCallback<ScanResult<String>>() {
			@Override
			public ScanResult<String> execute(ShardedJedis shardedJedis) {
				return shardedJedis.sscan(key, cursor, params);
			}
		});
	}

	@Override
	public ScanResult<Tuple> zscan(final String key, final String cursor, final ScanParams params) {
		return execute(key, new ShardingRedisCallback<ScanResult<Tuple>>() {
			@Override
			public ScanResult<Tuple> execute(ShardedJedis shardedJedis) {
				return shardedJedis.zscan(key, cursor, params);
			}
		});
	}

	@Override
	public Long geoadd(final String key, final double longitude, final double latitude, final String member) {
		return execute(key, new ShardingRedisCallback<Long>() {
			@Override
			public Long execute(ShardedJedis shardedJedis) {
				return shardedJedis.geoadd(key, longitude, latitude, member);
			}
		});
	}

	@Override
	public Long geoadd(final String key, final Map<String, GeoCoordinate> memberCoordinateMap) {
		return execute(key, new ShardingRedisCallback<Long>() {
			@Override
			public Long execute(ShardedJedis shardedJedis) {
				return shardedJedis.geoadd(key, memberCoordinateMap);
			}
		});
	}

	@Override
	public Double geodist(final String key, final String member1, final String member2) {
		return execute(key, new ShardingRedisCallback<Double>() {
			@Override
			public Double execute(ShardedJedis shardedJedis) {
				return shardedJedis.geodist(key, member1, member2);
			}
		});
	}

	@Override
	public Double geodist(final String key, final String member1, final String member2, final GeoUnit unit) {
		return execute(key, new ShardingRedisCallback<Double>() {
			@Override
			public Double execute(ShardedJedis shardedJedis) {
				return shardedJedis.geodist(key, member1, member2, unit);
			}
		});
	}

	@Override
	public List<String> geohash(final String key, final String... members) {
		return execute(key, new ShardingRedisCallback<List<String>>() {
			@Override
			public List<String> execute(ShardedJedis shardedJedis) {
				return shardedJedis.geohash(key, members);
			}
		});
	}

	@Override
	public List<GeoCoordinate> geopos(final String key, final String... members) {
		return execute(key, new ShardingRedisCallback<List<GeoCoordinate>>() {
			@Override
			public List<GeoCoordinate> execute(ShardedJedis shardedJedis) {
				return shardedJedis.geopos(key, members);
			}
		});
	}

	@Override
	public List<GeoRadiusResponse> georadius(final String key, final double longitude, final double latitude, final double radius,
			final GeoUnit unit) {
		return execute(key, new ShardingRedisCallback<List<GeoRadiusResponse>>() {
			@Override
			public List<GeoRadiusResponse> execute(ShardedJedis shardedJedis) {
				return shardedJedis.georadius(key, longitude, latitude, radius, unit);
			}
		});
	}

	@Override
	public List<GeoRadiusResponse> georadius(final String key, final double longitude, final double latitude, final double radius,
			final GeoUnit unit, final GeoRadiusParam param) {
		return execute(key, new ShardingRedisCallback<List<GeoRadiusResponse>>() {
			@Override
			public List<GeoRadiusResponse> execute(ShardedJedis shardedJedis) {
				return shardedJedis.georadius(key, longitude, latitude, radius, unit, param);
			}
		});
	}

	@Override
	public List<GeoRadiusResponse> georadiusByMember(final String key, final String member, final double radius, final GeoUnit unit) {
		return execute(key, new ShardingRedisCallback<List<GeoRadiusResponse>>() {
			@Override
			public List<GeoRadiusResponse> execute(ShardedJedis shardedJedis) {
				return shardedJedis.georadiusByMember(key, member, radius, unit);
			}
		});
	}

	@Override
	public List<GeoRadiusResponse> georadiusByMember(final String key, final String member, final double radius, final GeoUnit unit,
			final GeoRadiusParam param) {
		return execute(key, new ShardingRedisCallback<List<GeoRadiusResponse>>() {
			@Override
			public List<GeoRadiusResponse> execute(ShardedJedis shardedJedis) {
				return shardedJedis.georadiusByMember(key, member, radius, unit, param);
			}
		});
	}

	@Override
	public List<Long> bitfield(final String key, final String... arguments) {
		return execute(key, new ShardingRedisCallback<List<Long>>() {
			@Override
			public List<Long> execute(ShardedJedis shardedJedis) {
				return shardedJedis.bitfield(key, arguments);
			}
		});
	}

	@PreDestroy
	public void destroy() {
		try {
			shardedJedisPool.destroy();
		} catch (Exception e) {
			// ignore
		}
	}

	private ShardedJedis getResource() {
		long startTime = System.nanoTime();
		try {
			return this.shardedJedisPool.getResource();
		} catch (NoSuchElementException e) {
			throw new RuntimeException("没有更多redis连接实例可以使用", e);
		} catch (JedisConnectionException e) {
			throw new JedisConnectionException("获取redis连接失败", e);
		} finally {
			long endTime = System.nanoTime();
			long time = (endTime - startTime) / 1000L / 1000L; // time 单位:毫秒
			if (time >= getResourceWarnTime) {// 超过警告阀值，才warn
				logger.warn("get resource from shardingRedis cost time: [" + time + "] ms");
			}
		}
	}

	private void returnResource(ShardedJedis jedis) {
		if (jedis != null) {
			// this.shardedJedisPool.returnResource(jedis);
			jedis.close();
		}
	}

	// ## execute commands

	private <T> T execute(String key, ShardingRedisCallback<T> callback) {
		ShardedJedis jedis = this.getResource();
		try {
			return execute(jedis, callback);
		} catch (Exception e) {
			throw RedisHandler.err(key, jedis, e);
		}
	}

	private <T> T execute(byte[] key, ShardingRedisCallback<T> callback) {
		ShardedJedis jedis = this.getResource();
		try {
			return execute(jedis, callback);
		} catch (Exception e) {
			throw RedisHandler.err(key, jedis, e);
		}
	}

	private <T> T execute(ShardingRedisCallback<T> callback) {
		ShardedJedis jedis = this.getResource();
		return execute(jedis, callback);
	}

	private <T> T execute(ShardedJedis jedis, ShardingRedisCallback<T> callback) {
		try {
			return callback.execute(jedis);
		} finally {
			if (jedis != null) {
				jedis.close();
			}
		}
	}

}
