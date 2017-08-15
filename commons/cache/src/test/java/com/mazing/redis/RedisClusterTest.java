package com.mazing.redis;

import java.util.List;
import java.util.Random;

import com.yogu.commons.cache.redis.RedisFactory;
import com.yogu.commons.cache.redis.impl.RedisClusterImpl;
import com.yogu.commons.utils.SerializeUtil;
import com.yogu.commons.utils.cfg.DesPropertiesEncoder;

public class RedisClusterTest {

	private static int ri = new Random().nextInt(100);

	public static void main(String[] args) throws Exception {
		dan();
		System.out.println("++++++++++++++++++++++++++++++");
		duo();
	}

	protected static void dan() throws Exception {
		int maxActive = 10;
		String s = "redis.mazing.com:7104";
		String server = new DesPropertiesEncoder().encode(s);

		RedisFactory factory = new RedisFactory();
		factory.setMaxTotal(maxActive);

		RedisClusterImpl redis = (RedisClusterImpl) factory.initRedisCluster(server);// useClusterModel

		System.out.println(redis);

		for (int i = 0; i <= 100; i++) {
			String k = "byte_" + i;
			byte[] key = k.getBytes();
			byte[] value = SerializeUtil.serialize("fffbbbbbbb_" + ri + "_" + i);
			redis.set(key, value);
			// System.out.println("server: " + result);
		}

		byte[][] keys = new byte[][] { "byte_1".getBytes(), "byte_3".getBytes(), "byte_4".getBytes(), "byte_88".getBytes(),
				"byte_2000".getBytes(), "byte_9".getBytes(), "byte_2222".getBytes() };
		List<String> mget2 = redis.mget(keys);
		System.out.println(mget2);

		for (int i = 0; i <= 100; i++) {
			String key = "skey_" + i;
			String value = "aaasssssss_" + ri + "_" + i;
			redis.set(key, value);
			// System.out.println("server: " + redis.getHost(key) + ":" + redis.getPort(key) + " " + result);
		}

		List<String> mget = redis.mget("skey_1", "skey_3", "skey_4", "skey_88", "skey_2000", "skey_9", "skey_2222");
		System.out.println(mget);

	}

	protected static void duo() throws Exception {
		int maxActive = 10;
		String s1 = "redis.mazing.com:7101";
		String s2 = "redis.mazing.com:7102";
		String s3 = "redis.mazing.com:7103";
		String s4 = "redis.mazing.com:7104";
		String s5 = "redis.mazing.com:7105";
		String s6 = "redis.mazing.com:7106";
		String server = new DesPropertiesEncoder().encode(s1 + " # " + s2 + " # " + s3 + " # " + s4 + " # " + s5 + " # " + s6);

		RedisFactory factory = new RedisFactory();
		factory.setMaxTotal(maxActive);

		RedisClusterImpl redis = (RedisClusterImpl) factory.initRedisCluster(server);// useClusterModel

		System.out.println(redis);

		for (int i = 0; i <= 100; i++) {
			String k = "byte_" + i;
			byte[] key = k.getBytes();
			byte[] value = SerializeUtil.serialize("xxxbbbbbbb_" + ri + "_" + i);
			redis.set(key, value);
			// System.out.println("server: " + result);
		}

		byte[][] keys = new byte[][] { "byte_1".getBytes(), "byte_3".getBytes(), "byte_4".getBytes(), "byte_88".getBytes(),
				"byte_2000".getBytes(), "byte_9".getBytes(), "byte_2222".getBytes() };
		List<String> mget2 = redis.mget(keys);
		System.out.println(mget2);

		for (int i = 0; i <= 100; i++) {
			String key = "skey_" + i;
			String value = "bbbsssssss_" + ri + "_" + i;
			redis.set(key, value);
			// System.out.println("server: " + redis.getHost(key) + ":" + redis.getPort(key) + " " + result);
		}

		List<String> mget = redis.mget("skey_1", "skey_3", "skey_4", "skey_88", "skey_2000", "skey_9", "skey_2222");
		System.out.println(mget);
	}

}
