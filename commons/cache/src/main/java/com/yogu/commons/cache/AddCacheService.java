package com.yogu.commons.cache;

/**
 * cache服务类接口,支持add协议 <br>
 * <br>
 * 
 * @author JFan - 2014年10月30日 上午11:02:08
 */
public interface AddCacheService extends BaseCacheService {

	/**
	 * Set the cache, the server without
	 */
	public <V> boolean add(String key, V value);

	/**
	 * Set the cache for 'exp' seconds, on the server without
	 */
	public <V> boolean add(String key, V value, int exp);

}
