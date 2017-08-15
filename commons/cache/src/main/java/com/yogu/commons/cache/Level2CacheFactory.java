/**
 * 
 */
package com.yogu.commons.cache;

import java.util.concurrent.ExecutorService;

import com.yogu.commons.cache.level2.ExpLimit;
import com.yogu.commons.cache.level2.LocalNotFoundNotice;
import com.yogu.commons.cache.level2.impl.Level2CacheServiceImpl;
import com.yogu.commons.utils.Args;

/**
 * <br>
 * <br>
 * 
 * @author JFan - 2014年11月25日 上午11:20:56
 */
public final class Level2CacheFactory {

	/**
	 * 得到一个具备‘两层缓存功能’的缓存实例对象<br>
	 * 使用默认的本地时效器{@link org.jfan.an.cache.level2.impl.DefExpLimit}<br>
	 * 外层可强制转成 Level2CacheService 来使用<br>
	 * <br>
	 * 
	 * @param localCache 本地缓存实现
	 * @param amassCache 集中式缓存实现
	 */
	public static final BaseCacheService newLevel2Cached(BaseCacheService localCache, BaseCacheService amassCache) {
		return newLevel2Cached(localCache, amassCache, null, null, null);
	}

	/**
	 * 得到一个具备‘两层缓存功能’的缓存实例对象<br>
	 * 本地时效，使用指定的 时效器 来获取<br>
	 * 外层可强制转成 Level2CacheService 来使用<br>
	 * <br>
	 * 
	 * @param localCache 本地缓存实现
	 * @param amassCache 集中式缓存实现
	 * @param expLimit 本地缓存时效器
	 */
	public static final BaseCacheService newLevel2Cached(BaseCacheService localCache, BaseCacheService amassCache, ExpLimit expLimit) {
		return newLevel2Cached(localCache, amassCache, expLimit, null, null);
	}

	/**
	 * 得到一个具备‘两层缓存功能’的缓存实例对象<br>
	 * 本地时效，使用指定的 时效器 来获取<br>
	 * 同时具备‘条件’通知（被动式的）<br>
	 * 外层可强制转成 Level2CacheService 来使用<br>
	 * <br>
	 * 
	 * @param localCache 本地缓存实现
	 * @param amassCache 集中式缓存实现
	 * @param expLimit 本地缓存时效器
	 * @param notice 本地缓存失效回调通知
	 */
	public static final BaseCacheService newLevel2Cached(BaseCacheService localCache, BaseCacheService amassCache, ExpLimit expLimit, LocalNotFoundNotice notice) {
		return newLevel2Cached(localCache, amassCache, expLimit, notice, null);
	}

	/**
	 * 得到一个具备‘两层缓存功能’的缓存实例对象<br>
	 * 本地时效，使用指定的 时效器 来获取<br>
	 * 同时具备‘条件’通知（被动式的），通知方法的执行，在指定的线程池服务中执行<br>
	 * 外层可强制转成 Level2CacheService 来使用<br>
	 * <br>
	 * 
	 * @param localCache 本地缓存实现
	 * @param amassCache 集中式缓存实现
	 * @param expLimit 本地缓存时效器
	 * @param notice 本地缓存失效回调通知
	 * @param executorService 执行通知的线程池
	 */
	public static final BaseCacheService newLevel2Cached(BaseCacheService localCache, BaseCacheService amassCache, ExpLimit expLimit, LocalNotFoundNotice notice, ExecutorService executorService) {
		Args.notNull(localCache, "'localCache'");
		Args.notNull(amassCache, "'amassCache'");
		if (null != localCache && null != amassCache)
			Args.check(!(localCache.getClass().equals(amassCache.getClass())), "'localCache' and 'amassCache' cannot be the same source");

		Level2CacheServiceImpl service = new Level2CacheServiceImpl();
		service.setLocalCache(localCache);
		service.setAmassCache(amassCache);
		service.setExpLimit(expLimit);
		service.setNotice(notice);
		service.setExecutorService(executorService);
		return service;
	}

}
