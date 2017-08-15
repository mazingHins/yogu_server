package com.mazing.redis;

import java.util.List;
import java.util.Random;

import com.yogu.commons.cache.redis.Redis;
import com.yogu.commons.cache.redis.RedisFactory;
import com.yogu.commons.utils.SerializeUtil;
import com.yogu.commons.utils.cfg.DesPropertiesEncoder;

public class ShardingRedisTest {

	public static void main(String[] args) throws Exception {
		Random r = new Random();

		System.out.println("******************* redis");
		run(giveRedis(false, "redis.mazing.com:6379", "redis.mazing.com:6380"), r.nextInt(100));

		System.out.println("******************* sentinel");
		run(giveRedis(true, "redis.mazing.com:26379", "redis.mazing.com:26380"), r.nextInt(200));
	}

	private static Redis giveRedis(boolean sentinel, String... configs) {
		int maxActive = 10;
		StringBuilder sb = new StringBuilder();
		boolean one = true;
		for (String config : configs) {
			if (!(one))
				sb.append(" # ");
			sb.append(config);
			one = false;
		}
		String server = new DesPropertiesEncoder().encode(sb.toString());

		RedisFactory factory = new RedisFactory();
		factory.setMaxTotal(maxActive);

		if (sentinel)
			return factory.initRedisSentinel(server);
		return factory.initRedis(server);
	}

	public static void run(Redis redis, int ri) throws Exception {
		System.out.println("flushDB " + redis.flushDB());

		for (int i = 0; i <= 100; i++) {
			String k = "byteeee_" + i;
			byte[] key = k.getBytes();
			byte[] value = SerializeUtil.serialize("abbbbbbb_" + ri + "_" + i);
			// System.out.println(redis.getHost(k) + " : " + redis.getPort(k));
			String result = redis.set(key, value);
			if (!("OK".equals(result)))
				System.out.println("XXXXXXXXXXX key: " + key + " -- result: " + result);
			System.out.println("server: " + redis.getHost(k) + ":" + redis.getPort(k) + " " + result);
		}

		byte[][] keys = new byte[][] { "byteeee_1".getBytes(), "byteeee_3".getBytes(), "byteeee_4".getBytes(), "byteeee_88".getBytes(),
				"byteeee_2000".getBytes(), "byteeee_9".getBytes(), "byteeee_2222".getBytes() };
		List<String> mget2 = redis.mget(keys);
		System.out.println(mget2);

		for (int i = 0; i <= 100; i++) {
			String key = "keyyyy_" + i;
			String value = "bsssssss_" + ri + "_" + i;
			String result = redis.set(key, value);
			if (!("OK".equals(result)))
				System.out.println("YYYYYYYYYYY key: " + key + " -- result: " + result);
			System.out.println("server: " + redis.getHost(key) + ":" + redis.getPort(key) + " " + result);
		}

		List<String> mget = redis.mget("keyyyy_1", "keyyyy_3", "keyyyy_4", "keyyyy_88", "keyyyy_2000", "keyyyy_9", "keyyyy_2222");
		System.out.println(mget);
	}

}
