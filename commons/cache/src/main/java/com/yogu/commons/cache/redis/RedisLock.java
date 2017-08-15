package com.yogu.commons.cache.redis;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 使用 redis 实现分布式锁
 * 
 * @author linyi 2014年8月2日
 * @see <a href="http://www.jeffkit.info/2011/07/1000/">http://www.jeffkit.info/2011/07/1000/</a>
 */
public class RedisLock {
	
	private Logger logger = LoggerFactory.getLogger(RedisLock.class);
	
	private Redis redis;

	/**
	 * 锁的key
	 */
	private String key;

	/** 锁的超时时间（秒），过期删除 */
	private int expire = 0;

	// 锁状态标志
	private boolean locked = false;
	
	/**
	 * 其他人锁的 timestamp，仅用于debug
	 */
	private String lockTimestamp = "";

	/**
	 * RedisLock构造函数，默认锁的过期时间是480秒
	 * @param redis - Redis 实例
	 * @param key - 要锁的key
	 */
	public RedisLock(Redis redis, String key) {
		this(redis, key, 8 * 60);
	}
	
	/**
	 * RedisLock构造函数
	 * @param redis - Redis 实例
	 * @param key - 要锁的key
	 * @param expire - 过期时间，单位秒，必须大于0
	 */
	public RedisLock(Redis redis, String key, int expire) {
		if (redis == null || key == null) {
			throw new IllegalArgumentException("redis和key不能为null");
		}
        if (expire <= 0) {
            throw new IllegalArgumentException("expire必须大于0");
        }
		this.redis = redis;
		this.key = getLockKey(key);
		this.expire = expire;
	}

	/**
	 * 尝试获得锁，只尝试一次，如果获得锁成功，返回true，否则返回false。
	 * 如果锁已经被其他线程持有，本操作不会等待锁。
	 * @return 成功返回true
	 */
	public boolean lock() {
		long now = System.currentTimeMillis() / 1000;
		// 保存超时的时间
		String time = (now + expire + 1) + "";
		if (tryLock(time)) {
			// lock success, return
			lockTimestamp = time;
		}
		else {
			// 锁失败，看看 timestamp 是否超时
			String value = redis.get(key);
			if (now > transformValue(value)) {
				// 锁已经超时，尝试 GETSET 操作
				value = redis.getSet(key, time);
				// 返回的时间戳如果仍然是超时的，那就说明，如愿以偿拿到锁，否则是其他进程/线程设置了锁
				if (now > transformValue(value)) {
					this.locked = true;
				}
				else {
					logger.error("GETSET 锁的旧值是：" + value + ", key=" + key);
				}
			}
			else {
				logger.error("GET 锁的当前值是：" + value + ", key=" + key);
			}
			this.lockTimestamp = value;
			
		}
		return this.locked;
	}
	
	/**
	 * 释放已经获得的锁，
	 * 只有在获得锁的情况下才会释放锁。
	 * 本方法不会抛出异常。
	 */
	public void unlock() {
		if (this.locked) {
			try {
				redis.del(key);
                this.locked = false;
			} catch (Exception e) {
				logger.error("EXCEPTION when delete key: ", e);
			}
		}
	}
	
	private long transformValue(String value) {
		if (StringUtils.isNotEmpty(value)) {
			return NumberUtils.toLong(value, 0L);
		}
		return 0L;
	}
	
	/**
	 * 尝试获得锁
	 * @param time - 锁超时的时间（秒）
	 * @return true=成功
	 */
	private boolean tryLock(String time) {
		if (this.locked == false && redis.setnx(key, time) == 1) {
			// redis.expire(key, EXPIRE);
			this.locked = true;
		}
		return this.locked;
	}

	public String getLockTimestamp() {
		return lockTimestamp;
	}
	
	/**
	 * 获得Redis锁的key
	 * @param key - 要锁的key
	 * @return key
	 */
	private static String getLockKey(String key) {
		return "R_lock4_" + key;
	}
	
	/**
	 * 清除key
	 * @param redis - Redis 实例
	 * @param key - 要清除锁的key
	 */
	public static void clearLock(Redis redis, final String key) {
		String tmp = getLockKey(key);
		redis.del(tmp);
	}
}
