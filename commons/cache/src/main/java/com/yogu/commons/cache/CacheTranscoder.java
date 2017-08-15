/**
 * 
 */
package com.yogu.commons.cache;

/**
 * 关于cache中，需要缓存的对象序列化与反序列化的处理器 接口 <br>
 * <br>
 * 实现类中请特别注意：多线程的情况，请不要使用成员变量
 *
 * @author JFan 2015年12月17日 下午1:46:52
 */
public interface CacheTranscoder {

	/**
	 * 将对象进行序列化<br>
	 * 实现类中请特别注意：多线程的情况，请不要使用成员变量
	 * 
	 * @param obj 需要序列化的对象
	 * @return 序列化之后的数据
	 */
	public byte[] serialize(Object obj);

	/**
	 * 将数据进行 对象反序列化<br>
	 * 实现类中请特别注意：多线程的情况，请不要使用成员变量
	 * 
	 * @param bs 需要进行反序列化的数据
	 * @return 反序列化得到的对象
	 */
	public Object deserialize(byte[] bs);

}
