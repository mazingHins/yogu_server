/**
 * 
 */
package com.yogu.commons.utils;

import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ServiceLoader;

import javax.annotation.Priority;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.core.annotation.AnnotationUtils;

/**
 * 提供ServiceLoader类的包装方法 <br>
 * 目前只包装排序
 *
 * @author JFan 2016年1月22日 下午3:53:51
 */
public class ServiceLoaderUtils {

	/**
	 * 通过ServiceLoader的形式装在指定的class实例列表<br>
	 * 顺序有jdk默认（读取到的顺序）<br>
	 * <br>
	 * <strong>注意：每次都会得到新的实例</strong>
	 */
	public static <S> Iterator<S> iterator(Class<S> service) {
		ServiceLoader<S> load = ServiceLoader.load(service);
		Iterator<S> iterator = load.iterator();
		return iterator;
	}

	/**
	 * 获取指定类实例列表中的第一个 <br>
	 * 顺序参考 {@link ServiceLoaderUtils#iterator(Class)}<br>
	 * <br>
	 * <strong>注意：每次都会得到新的实例</strong>
	 */
	public static <S> S iteratorOne(Class<S> service) {
		Iterator<S> iterator = iterator(service);
		if (null == iterator || !(iterator.hasNext()))
			return null;
		return iterator.next();
	}

	/**
	 * 通过ServiceLoader的形式装在指定的class实例列表<br>
	 * 返回一个有序的集合 @Priority 小到大<br>
	 * 如果没指定 @Priority 则排序值为 Integer.MAX_VALUE<br>
	 * <br>
	 * <strong>注意：每次都会得到新的实例</strong>
	 */
	public static <S> List<S> orderly(Class<S> service) {
		Iterator<S> iterator = iterator(service);
		List<S> list = new LinkedList<S>();
		// 2 list
		if (null != iterator)
			while (iterator.hasNext())
				list.add(iterator.next());
		// sort
		Collections.sort(list, new PriorityComparator());
		return list;
	}

	/**
	 * 获取指定类实例列表中的第一个 <br>
	 * 顺序参考 {@link ServiceLoaderUtils#orderly(Class)}<br>
	 * <br>
	 * <strong>注意：每次都会得到新的实例</strong>
	 */
	public static <S> S orderlyOne(Class<S> service) {
		List<S> list = orderly(service);
		if (CollectionUtils.isEmpty(list))
			return null;
		return list.get(0);
	}

	// ####
	// ##
	// ####

	/**
	 * 根据@Priority进行排序（升序） <br>
	 * 如果没有@Priority注解，则默认Integer.MAX_VALUE<br>
	 * <br>
	 * <strong>注意：每次都会得到新的实例</strong>
	 *
	 * @author JFan 2016年1月22日 下午4:06:40
	 */
	public static class PriorityComparator implements Comparator<Object> {

		/**
		 * {@inheritDoc}
		 */
		@Override
		public int compare(Object o1, Object o2) {
			// 取得排序注解
			Priority p1 = null, p2 = null;
			if (null != o1) {
				p1 = AnnotationUtils.findAnnotation(o1.getClass(), Priority.class);
			}
			if (null != o2) {
				p2 = AnnotationUtils.findAnnotation(o2.getClass(), Priority.class);
			}
			// 取排序值
			int i1 = (null != p1 ? p1.value() : Integer.MAX_VALUE);
			int i2 = (null != p2 ? p2.value() : Integer.MAX_VALUE);
			// 比较
			return i1 - i2;
		}

	}

}
