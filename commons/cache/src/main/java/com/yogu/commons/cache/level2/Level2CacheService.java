/**
 * 
 */
package com.yogu.commons.cache.level2;

import com.yogu.commons.cache.BaseCacheService;

/**
 * 两层缓存接口 <br>
 * <br>
 * 
 * @author JFan - 2014年10月30日 下午12:30:44
 */
public interface Level2CacheService extends BaseCacheService {

	public BaseCacheService getLocalCache();

	public BaseCacheService getAmassCache();

	// ####
	// local

	/**
	 * 向本地缓存中缓存对象
	 */
	public <V> boolean setLocal(String key, V value);

	/**
	 * 向本地缓存中缓存对象
	 */
	public <V> boolean setLocal(String key, V value, int localExp);

	/**
	 * 从本地缓存中读取已经缓存的内容
	 */
	public <V> V getLocal(String key);

	/**
	 * 删除本地缓存中的缓存内容
	 */
	public boolean deleteLocal(String key);

	// ####
	// ## amass

	/**
	 * 向集中缓存（memcached）中缓存对象
	 */
	public <V> boolean setAmass(String key, V value);

	/**
	 * 向集中缓存（memcached）中缓存对象
	 */
	public <V> boolean setAmass(String key, V value, int amassExp);

	/**
	 * 从集中缓存（memcached）中读取已经缓存的内容
	 */
	public <V> V getAmass(String key);

	/**
	 * 删除集中缓存（memcached）中的缓存内容
	 */
	public boolean deleteAmass(String key);

	// ####

	/**
	 * 先从本地缓存读取内容，没有再从集中缓存读取，并自动缓存到本地缓存中
	 */
	public <V> V get(String key, int localExp);

	/**
	 * 设置两层缓存，集中式缓存时效为exp，本地缓存时效来自于timeLimit的运算
	 */
	public <V> boolean set(String key, V value, int exp, ExpLimit expLimit);

	/**
	 * 设置时间转换器
	 */
	public void setExpLimit(ExpLimit expLimit);

	/**
	 * 设置通知器，在本地时效 远端又存在的时候，得到回调通知
	 */
	public void setNotice(LocalNotFoundNotice notice);

}
