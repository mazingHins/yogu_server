/**
 * 
 */
package com.yogu.commons.core;

import java.io.Serializable;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * 提供KV形式的贮存对象，但是V允许出现多次 <br>
 *
 * @author JFan 2015年7月31日 下午7:01:01
 */
public class KeyValues<K, V> implements Serializable {

	private static final long serialVersionUID = -480427877071994289L;

	private K key;
	private LinkedList<V> values;

	public KeyValues() {
	}

	public KeyValues(K key, List<V> values) {
		initValues();
		this.values.addAll(values);
		this.key = key;
	}

	public KeyValues(K key, V[] values) {
		initValues();
		if (null != values)
			for (V value : values)
				this.values.add(value);
		this.key = key;
	}

	public void addValue(V value) {
		initValues();
		values.add(value);
	}

	private void initValues() {
		if (null == values)
			values = new LinkedList<>();
	}

	// ####
	// ## set/get func
	// ####

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
	 * @return values
	 */
	public LinkedList<V> getValues() {
		return values;
	}

	/**
	 * @param values 要设置的 values
	 */
	public void setValues(LinkedList<V> values) {
		this.values = values;
	}

	@Override
	public String toString() {
		String vs = (null == values ? "null" : Arrays.toString(values.toArray()));
		return "KeyValues [key=" + key + ", values=" + vs + "]";
	}

}
