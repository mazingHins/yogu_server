/**
 * 
 */
package com.yogu.commons.cache.memcached;

import java.util.concurrent.TimeoutException;

import net.rubyeye.xmemcached.MemcachedClient;
import net.rubyeye.xmemcached.exception.MemcachedException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.yogu.commons.cache.BaseCacheService;
import com.yogu.commons.cache.CacheExtendService;

/**
 * 自动化缓存辅助类 Memchaned实现 <br>
 *
 * @author JFan 2015年11月2日 下午7:35:49
 */
public class MemcachedCacheExtendServiceImpl implements CacheExtendService {

	private static final Logger logger = LoggerFactory.getLogger(MemcachedCacheExtendServiceImpl.class);

	private final int defExt = BaseCacheService.DEF_EXP;

	/** 是否启用缓存 */
	private boolean cacheExtendEnable;

	/** 默认缓存时间 */
	private int defaultTime;

	private MemcachedClient memcachedClient;

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

		int ext = defExt;
		// 缓存，seconds==null或者小于0则‘永久’
		if (null != seconds) {
			// 等于0则使用 @Cacher配置文件中的默认时间
			if (0 == seconds)
				ext = defaultCacheTime();
			else if (0 < seconds)
				ext = seconds;
		}

		try {
			memcachedClient.set(key, ext, value);
		} catch (TimeoutException | InterruptedException | MemcachedException e) {
			logger.error("anno#cacheExtend#memService | setCache Error | message: {}", e.getMessage(), e);
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
			return (T) memcachedClient.get(key);
		} catch (Exception e) {
			logger.error("anno#cacheExtend#memService | readCache Error | message: {}", e.getMessage(), e);
		}
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void delete(String key) {
		if (cacheExtendEnable)
			try {
				memcachedClient.delete(key);
			} catch (TimeoutException | InterruptedException | MemcachedException e) {
				logger.error("anno#cacheExtend#memService | deleteCache Error | message: {}", e.getMessage(), e);
			}
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
	 * @param memcachedClient 要设置的 memcachedClient
	 */
	public void setMemcachedClient(MemcachedClient memcachedClient) {
		this.memcachedClient = memcachedClient;
	}

}
