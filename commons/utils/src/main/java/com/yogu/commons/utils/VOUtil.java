package com.yogu.commons.utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;

/**
 * VO、PO对象相互转换工具
 */
public class VOUtil {

	/**
	 * 把 source 转换成指定对象 T，T必须有一个不带参数的构造函数。
	 * 
	 * @param source - 源数据，不能为null
	 * @param clazz - 目标数据的class，不能为null
	 * @throws RuntimeException 如果无法实例化 clazz、复制属性错误，抛出异常
	 */
	public static <T> T from(Object source, Class<T> clazz) {
		if (null == source)
			return null;
		T result;
		try {
			result = clazz.newInstance();
		} catch (Exception e) {
			throw new RuntimeException("newInstance ERROR, clz: " + clazz, e);
		}

		return from(source, result);
	}

	/**
	 * 把 source 转换成指定对象 T，T必须有一个不带参数的构造函数。
	 * 
	 * @param source - 源数据，不能为null
	 * @param result - 目标数据的对象，不能为null
	 * @throws RuntimeException 如果复制属性错误，抛出异常
	 */
	public static <T> T from(Object source, T result) {
		if (null == source || null == result)
			return result;
		try {
			BeanUtils.copyProperties(result, source);
//			BeanCopier create = BeanCopier.create(source.getClass(), clazz, false);
//			create.copy(source, result, null);
		} catch (Exception e) {
			throw new RuntimeException("copyProperties ERROR, obj: " + source.getClass() + " clz: " + result.getClass(), e);
		}
		return result;
	}

	/**
	 * 把 source 列表转换成指定对象 T 的列表，T必须有一个不带参数的构造函数。
	 * 
	 * @param list - 要转换的列表，不能为空
	 * @param clazz - 目标数据的class，不能为null
	 * @throws RuntimeException 如果无法实例化 clazz、复制属性错误，抛出异常
	 */
	public static <T> List<T> fromList(Collection<? extends Object> list, Class<T> clazz) {
		List<T> result = new ArrayList<T>();
		if (null != list)
			for (Object source : list)
				result.add(from(source, clazz));
		return result;
	}

}
