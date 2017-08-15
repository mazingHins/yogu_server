/**
 * 
 */
package com.mazing.test.runtime;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;

/**
 * 收集http返回数据 中的内容
 * 
 * @author jfan 2016年10月13日 下午3:40:38
 */
public abstract class CollectData {

	/**
	 * 收集HTTP返回的数据
	 * 
	 * @param data http返回的json数据
	 */
	public abstract void collect(String data);

	protected void collectCityCode(List<String> list) {
		if (CollectionUtils.isEmpty(list))
			return;
		String[] arr = strListToArr(list);
		RuntimeData.cityCodes = merge(RuntimeData.cityCodes, arr);
	}

	protected void collectLangCode(List<String> list) {
		if (CollectionUtils.isEmpty(list))
			return;
		String[] arr = strListToArr(list);
		RuntimeData.langCodes = merge(RuntimeData.langCodes, arr);
	}

	protected void collectStoreId(List<Integer> list) {
		if (CollectionUtils.isEmpty(list))
			return;
		String[] arr = strListToArr(list);
		RuntimeData.storeIds = merge(RuntimeData.storeIds, arr);
	}

	protected void collectDishKey(List<Integer> list) {
		if (CollectionUtils.isEmpty(list))
			return;
		String[] arr = strListToArr(list);
		RuntimeData.dishKeys = merge(RuntimeData.dishKeys, arr);
	}

	protected void collectNewsBid(List<Integer> list) {
		if (CollectionUtils.isEmpty(list))
			return;
		String[] arr = strListToArr(list);
		RuntimeData.newsBids = merge(RuntimeData.newsBids, arr);
	}

	// ####

	/** List< String> --> String[] */
	protected String[] strListToArr(List<?> list) {
		if (CollectionUtils.isEmpty(list))
			return null;
		String[] arr = new String[list.size()];
		int index = 0;
		for (Object str : list)
			arr[index++] = str.toString();
		return arr;
	}

	/** 合并并去重 */
	protected <T> T[] merge(T[] v1, T[] v2) {
		if (null == v1)
			return v2;
		if (null == v2)
			return v1;
		Set<T> set = new HashSet<>();
		for (T v : v1)
			set.add(v);
		for (T v : v2)
			set.add(v);
		T[] newArr = Arrays.copyOf(v1, set.size());
		int index = 0;
		for (T v : set)
			newArr[index++] = v;
		return newArr;
	}

	/** 纯粹地追加 */
	protected <T> T[] append(T[] v1, T[] v2) {
		if (null == v1)
			return v2;
		if (null == v2)
			return v1;
		int index = v1.length;
		T[] newArr = Arrays.copyOf(v1, index + v2.length);
		for (T v : v2)
			newArr[index++] = v;
		return newArr;
	}

}
