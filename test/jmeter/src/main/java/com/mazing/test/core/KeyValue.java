/**
 * 
 */
package com.mazing.test.core;

import java.io.Serializable;

/**
 * 提供KV形式的贮存对象 <br>
 *
 * @author JFan 2015年7月31日 下午7:00:19
 */
public class KeyValue<K, V> implements Serializable {

	private static final long serialVersionUID = -749100155515612695L;

	private K key;
	private V value;

	@Deprecated
	public KeyValue() {
	}

	public KeyValue(K key, V value) {
		this.value = value;
		this.key = key;
	}

	/**
	 * @return key
	 */
	public K getKey() {
		return key;
	}

	/**
	 * @param key 要设置的 key
	 */
	public void setKey(K key) {
		this.key = key;
	}

	/**
	 * @return value
	 */
	public V getValue() {
		return value;
	}

	/**
	 * @param value 要设置的 value
	 */
	public void setValue(V value) {
		this.value = value;
	}

}
