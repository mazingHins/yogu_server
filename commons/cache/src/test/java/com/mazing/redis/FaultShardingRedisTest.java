package com.mazing.redis;

import java.util.List;
import java.util.Map;
import java.util.Random;

import com.yogu.commons.cache.redis.Redis;
import com.yogu.commons.cache.redis.RedisFactory;
import com.yogu.commons.utils.JsonUtils;
import com.yogu.commons.utils.cfg.DesPropertiesEncoder;

import redis.clients.jedis.Jedis;

public class FaultShardingRedisTest {

	private static int size = 200;// 执行多少次操作 set/get
	private static long sleepTime = 700;// 每次操作之间暂停多少毫秒，小于等于0则不暂停

	private static String keyPrefix = "ppKey_";
	private static String valuePrefix = "ooValue_";

	public static void main(String[] args) throws Exception {
		// printSentinelShardedInfo("redis.mazing.com:26379", "redis.mazing.com:26380", "redis.mazing.com:26479",
		// "redis.mazing.com:26480");

		// System.out.println("******************* redis");
		// run(giveRedis(false, "redis.mazing.com:6379", "redis.mazing.com:6380"));

		System.out.println("******************* sentinel");
		run(giveRedis(true, "redis.mazing.com:26379", "redis.mazing.com:26380", "redis.mazing.com:26479", "redis.mazing.com:26480"));
	}

	protected static Redis giveRedis(boolean sentinel, String... configs) {
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

	protected static void run(Redis redis) throws Exception {
		System.out.println("flushDB " + redis.flushDB());
		int ri = new Random().nextInt(500);

		for (int i = 0; i <= size; i++) {
			System.out.println("***********************************************************************");
			String key = keyPrefix + i;
			String value = valuePrefix + ri + "_" + i;
			String result = null;

			System.out.println(i);
			try {
				result = redis.set(key, value);
			} catch (Exception e) {
				System.out.println("====set数据发生异常：" + e.getMessage());
			}

			if (!("OK".equals(result)))
				System.out.println("set操作失败：key: " + key + " -- result: " + result);

			// 验证数据
			vali(redis, i, ri);

			sleep();
		}

		List<String> mget = redis.mget(keysss("1", "3", "4", "88", "2000", "9", "2222"));
		System.out.println(mget);
	}

	private static String[] keysss(String... strings) {
		String[] keys = new String[strings.length];
		int index = 0;
		for (String str : strings)
			keys[index++] = (keyPrefix + str);
		return keys;
	}

	private static void vali(Redis redis, int iIndex, int ri) {
		for (int i = 0; i <= iIndex; i++) {
			String key = keyPrefix + i;
			String value = valuePrefix + ri + "_" + i;

			String result = null;
			try {
				result = redis.get(key);
			} catch (Exception e) {
				System.out.println("====验证数据时异常：" + e.getMessage());
			}

			if (!(value.equals(result)))
				System.out.println("验证数据失败：iIndex: " + iIndex + ", key: " + key + ", value: " + value + ", result: " + result);
		}
	}

	private static void sleep() {
		try {
			if (0 < sleepTime)
				Thread.sleep(sleepTime);
		} catch (InterruptedException e) {
			// ignore
		}
	}

	//

	protected static void printSentinelShardedInfo(String... configs) {
		for (String config : configs) {
			String[] tmp = config.split(":");
			String host = tmp[0];
			int port = Integer.parseInt(tmp[1]);
			Jedis jedis = new Jedis(host, port);
			List<Map<String, String>> sentinelMasters = jedis.sentinelMasters();
			System.out.println(config + "\t--\t" + JsonUtils.toJSONString(sentinelMasters));
			for (Map<String, String> map : sentinelMasters)
				System.out.println("\t" + map.get("name") + " " + map.get("ip") + " " + map.get("port"));
			jedis.close();
		}
	}

}
