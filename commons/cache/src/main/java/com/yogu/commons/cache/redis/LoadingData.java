/**
 * 
 */
package com.yogu.commons.cache.redis;

/**
 * 查询缓存：<br>
 * KEY的处理<br>
 * 数据的装载方式<br>
 *
 * @author JFan 2016年2月26日 下午6:43:25
 */
public interface LoadingData<K, V> {

	/**
	 * 将加载数据的原key（比如餐厅ID：11111）转换成redis中存储的key（'Store:11111'） <br>
	 * <br>
	 * 本来应该有两种类型的key：String | byte[]<br>
	 * 先暂定只使用string吧
	 */
	public String toRedisKey(K key);

	/**
	 * 根据key回源，并返回需要缓存的对象
	 */
	public V sourceData(K key);

}
