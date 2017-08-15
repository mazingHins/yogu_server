/**
 * 
 */
package com.yogu.commons.cache.redis.impl;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.yogu.commons.cache.CacheExtendService;
import com.yogu.commons.cache.redis.Redis;
import com.yogu.commons.utils.SerializeUtil;

/**
 * 自动化缓存辅助类 Redis实现 <br>
 *
 * @author JFan 2015年8月19日 下午12:16:39
 */
public class RedisCacheExtendServiceImpl implements CacheExtendService {

	private static final Logger logger = LoggerFactory.getLogger(RedisCacheExtendServiceImpl.class);

	/** 是否启用缓存 */
	private boolean cacheExtendEnable;

	/** 默认缓存时间 */
	private int defaultTime;

	private Redis redis;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isEnable() {
		return cacheExtendEnable;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int defaultCacheTime() {
		return defaultTime;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void set(String key, Object value) {
		set(key, value, null);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void set(String key, Object value, Integer seconds) {
		if (!(cacheExtendEnable))
			return;

		try {
			byte[] cacheValue = null;
			if (null != value)
				cacheValue = SerializeUtil.serialize(value);

			// 缓存，seconds==null或者小于0则永久
			if (null == seconds || seconds < 0)
				redis.set(key.getBytes(), cacheValue);
			else {
				if (0 == seconds)// 等于0：默认缓存时间
					seconds = defaultCacheTime();
				redis.setex(key.getBytes(), seconds, cacheValue);
			}
		} catch (IOException e) {
			logger.error("anno#cacheExtend#redisService | setCache Error | message: {}", e.getMessage(), e);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@SuppressWarnings("unchecked")
	public <T> T get(String key) {
		if (!(cacheExtendEnable))
			return null;

		try {
			byte[] body = redis.get(key.getBytes());
			if (null == body)
				return null;

			Object object = SerializeUtil.unserialize(body);
			return (T) object;
		} catch (Exception e) {
			logger.error("anno#cacheExtend#redisService | readCache Error | message: {}", e.getMessage(), e);
		}
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void delete(String key) {
		if (cacheExtendEnable)
			redis.del(key);
	}

	/**
	 * @param cacheExtendEnable 要设置的 cacheExtendEnable
	 */
	public void setCacheExtendEnable(boolean cacheExtendEnable) {
		this.cacheExtendEnable = cacheExtendEnable;
	}

	/**
	 * @param defaultTime 要设置的 defaultTime
	 */
	public void setDefaultTime(int defaultTime) {
		this.defaultTime = defaultTime;
	}

	/**
	 * @param redis 要设置的 redis
	 */
	public void setRedis(Redis redis) {
		this.redis = redis;
	}

}
