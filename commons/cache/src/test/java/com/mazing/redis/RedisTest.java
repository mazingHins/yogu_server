package com.mazing.redis;

import java.util.Random;

import com.yogu.commons.cache.redis.Redis;
import com.yogu.commons.cache.redis.RedisFactory;
import com.yogu.commons.utils.cfg.DesPropertiesEncoder;

public class RedisTest {

	private static int size = 100;// 执行多少次操作 set/get
	private static long sleepTime = 0;// 每次操作之间暂停多少毫秒，小于等于0则不暂停

	public static void main(String[] args) {
		Random r = new Random();

		System.out.println("******************* redis");
		run(giveRedis("redis.mazing.com:6379", false), r.nextInt(100));

		System.out.println("******************* sentinel");
		run(giveRedis("redis.mazing.com:26379", true), r.nextInt(200));
	}

	private static Redis giveRedis(String config, boolean sentinel) {
		int maxActive = 10;
		String server = new DesPropertiesEncoder().encode(config);

		RedisFactory factory = new RedisFactory();
		factory.setMaxTotal(maxActive);

		if (sentinel)
			return factory.initRedisSentinel(server);
		return factory.initRedis(server);
	}

	private static void run(Redis redis, int ri) {
		for (int i = 0; i <= size; i++) {
			String key = "key123_" + i;
			String value = "111111_" + ri;
			String result = redis.set(key, value);
			System.out.println("server: " + redis.getHost(key) + ":" + redis.getPort(key) + " " + result);
			if (!("OK".equals(result)))
				System.out.println("EEEEEE: " + key + " -- " + result);
			sleep();
		}
		System.out.println("set end");

		for (int i = 0; i <= size; i++) {
			String key = "key123_" + i;
			String value = "111111_" + ri;
			String v = redis.get(key);
			System.out.println("key: " + key + ", value: " + v);
			if (!(value.equals(v)))
				System.out.println("XXXXX: " + key);
			sleep();
		}
		System.out.println("get end");
	}

	private static void sleep() {
		try {
			if (0 < sleepTime)
				Thread.sleep(sleepTime);
		} catch (InterruptedException e) {
			// ignore
		}
	}

}
