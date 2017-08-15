package com.yogu.commons.cache;

/**
 * 自动化缓存辅助类 <br>
 * 主要是而外提供一个 是否开启 该功能的方法
 *
 * @author JFan 2015年8月19日 上午11:05:51
 */
public interface CacheExtendService {

	/**
	 * 是否开启缓存
	 */
	public boolean isEnable();

	/**
	 * 默认缓存时间（秒）
	 */
	public int defaultCacheTime();

	/**
	 * 缓存一个对象，永久缓存<br>
	 * enable=false时，不会缓存数据
	 */
	public void set(String key, Object value);

	/**
	 * 缓存一个对象，指定时间（秒）<br>
	 * enable=false时，不会缓存数据<br>
	 * seconds=null，则永久缓存
	 */
	public void set(String key, Object value, Integer seconds);

	/**
	 * 获取一个已经缓存的对象<br>
	 * enable=false时，永远返回null
	 */
	public <T> T get(String key);

	/**
	 * 清除缓存中的内容<br>
	 * enable=false时，不做任何动作
	 */
	public void delete(String key);

}
