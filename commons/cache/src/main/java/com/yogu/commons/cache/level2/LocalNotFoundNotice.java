package com.yogu.commons.cache.level2;

/**
 * <b>这是一个被动的通知，即 主调调用get查询时，才会触发‘条件’检查</b><br>
 * <br>
 * 
 * 在两层缓存服务中，如果<br>
 * 1：本地缓存中取不到数据(==null) <br>
 * 2：集中式缓存中却有数据(!=null) <br>
 * <br>
 * 则触发此调用（上面两点必须都满足）,异步的调用 <br>
 * 
 * @author JFan - 2014年11月21日 下午5:19:44
 */
public interface LocalNotFoundNotice {

	/**
	 * 通知方法
	 * 
	 * @param key 对应的key
	 * @param value amass中的值
	 */
	public void notice(String key, Object value);

}
