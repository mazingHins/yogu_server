package com.yogu.commons.utils;

import java.lang.reflect.Field;
import java.util.*;

/**
 * 列表集合工具类
 * 
 * @author chenjianhong
 * @time 2014年11月27日 下午7:06:46
 */
public class ListUtils {
	/**
	 * 集合转换成列表
	 * 
	 * @param set
	 * @return
	 */
	public static <T> List<T> toList(Set<T> set) {
		if (set == null) {
			return null;
		}

		if (set.size() == 0) {
			return new ArrayList<T>();
		}

		List<T> list = new ArrayList<T>();

		Iterator<T> iterator = set.iterator();
		while (iterator.hasNext()) {
			T next = iterator.next();
			list.add(next);
		}
		return list;
	}

	/**
	 * 列表转换成集合
	 * 
	 * @param list
	 * @return
	 */
	public static <T> Set<T> toSet(List<T> list) {
		if (list == null) {
			return null;
		}

		if (list.size() == 0) {
			return new HashSet<T>();
		}

		Set<T> set = new HashSet<T>();

		for (T element : list) {
			set.add(element);
		}
		return set;
	}

	/**
	 * List是否为空
	 * 
	 * @param list
	 * 
	 * @return 若空返回true
	 */
	public static boolean isEmpty(List<?> list) {
		if (list == null || list.isEmpty()) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * List是否非空
	 * 
	 * @param list
	 * 
	 * @return 若非空返回true
	 */
	public static boolean isNotEmpty(List<?> list) {
		return !isEmpty(list);
	}

	/**
	 * 当list==null赋予默认值ArrayList
	 * 
	 * @param list
	 * @return
	 */
	public static <T> List<T> defaultList(List<T> list) {
		if (list == null) {
			return new ArrayList<T>();
		}
		return list;
	}

	public static <T> List<T> getPropertyList(List<? extends Object> list, Class<T> clazz, String fieldName) {
		List<T> result = new ArrayList<T>();
		Field field = null;

		for (int i = 0; i < list.size(); i++) {
			if (null == field) {
				try {
					field = list.get(i).getClass().getDeclaredField(fieldName);
				} catch (Exception e) {
					throw new RuntimeException("getDeclaredField ERROR, field:" + fieldName, e);
				}
			}

			field.setAccessible(true);
			try {
				if (field.get(list.get(i)).getClass().equals(clazz)) {
					result.add((T) field.get(list.get(i)));
				}
			} catch (Exception e) {
				throw new RuntimeException("UNKNOWN ERROR, field:" + field, e);
			}
		}
		return result;
	}

	/**
	 * 将list的某个字段抽取出来生成set
	 * 
	 * @param list 待处理的list
	 * @param clazz 抽取字段的类型
	 * @param fieldName 抽取的字段名
	 * @return
	 */
	public static <T> Set<T> getPropertySet(List<? extends Object> list, Class<T> clazz, String fieldName) {
		Set<T> result = new HashSet<T>();
		Field field = null;

		for (int i = 0; i < list.size(); i++) {
			if (null == field) {
				try {
					field = list.get(i).getClass().getDeclaredField(fieldName);
				} catch (Exception e) {
					throw new RuntimeException("getDeclaredField ERROR, field:" + fieldName, e);
				}
			}

			field.setAccessible(true);
			try {
				if (field.get(list.get(i)).getClass().equals(clazz)) {
					result.add((T) field.get(list.get(i)));
				}
			} catch (Exception e) {
				throw new RuntimeException("UNKNOWN ERROR, field:" + field, e);
			}
		}
		return result;
	}

	/**
	 * 去除list中的重复数据，保持原有顺序
	 * 
	 * @param list
	 * @return
	 */
	public static <T> List<T> removeDuplicate(List<T> list) {
		Set<T> set = new HashSet<T>();
		List<T> newList = new LinkedList<>();
		for (Iterator<T> iter = list.iterator(); iter.hasNext();) {
			T element = iter.next();
			if (set.add(element))
				newList.add(element);
		}
		return newList;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static int[] removeDuplicate(int[] array) {

		Set set = new HashSet();
		List newList = new LinkedList();
		for (int i = 0; i < array.length; i++) {
			if (set.add(array[i]))
				newList.add(array[i]);
		}

		int aa[] = new int[newList.size()];

		for (int i = 0; i < newList.size(); i++) {
			aa[i] = (int) newList.get(i);
		}

		return aa;
	}

	/**
	 * 将list按一定大小分段
	 * 
	 * @param list
	 * @param pageSize
	 * @return
	 */
	public static <T> List<List<T>> splitList(List<T> list, int pageSize) {

		int listSize = list.size(); // list的大小
		int page = (listSize + (pageSize - 1)) / pageSize; // 页数

		List<List<T>> listArray = new ArrayList<List<T>>(); // 创建list数组 ,用来保存分割后的list
		for (int i = 0; i < page; i++) { // 按照数组大小遍历
			List<T> subList = new ArrayList<T>(); // 数组每一位放入一个分割后的list
			for (int j = 0; j < listSize; j++) { // 遍历待分割的list
				int pageIndex = ((j + 1) + (pageSize - 1)) / pageSize; // 当前记录的页码(第几页)
				if (pageIndex == (i + 1)) { // 当前记录的页码等于要放入的页码时
					subList.add(list.get(j)); // 放入list中的元素到分割后的list(subList)
				}

				if ((j + 1) == ((j + 1) * pageSize)) { // 当放满一页时退出当前循环
					break;
				}
			}
			listArray.add(subList); // 将分割后的list放入对应的数组的位中
		}
		return listArray;
	}

	public static void main(String[] args) {
		// ListUtils.Person p1 = new ListUtils.Person("a", "b");
		// ListUtils.Person p2 = new ListUtils.Person("c", "d");
		// List<Person> persons = new ArrayList<ListUtils.Person>();
		// persons.add(p1);
		// persons.add(p2);
		//
		// Set<String> set = ListUtils.getPropertySet(persons, String.class, "a");
		// for (String tmp : set) {
		// System.out.println(tmp);
		// }
		//
		// List<Long> list = new ArrayList<Long>();
		// list.add(1L);
		// list.add(2L);
		// list.add(3L);
		// System.out.println(list2Str(list));

		List list = new ArrayList<>();
		list.add(11);
		list.add(12);
		list.add(13);
		list.add(14);
		list.add(15);
		list.add(16);
		list.add(17);
		list.add(18);
		list.add(19);
		list.add(20);

		List<List<Integer>> result = splitList(list, 3);
		//System.out.println(result);
		// list = removeDuplicate(list);

		
		int aa[] = new int[]{1,2,3,1,4,5,1,6,7};
		
		System.out.println(Arrays.toString(removeDuplicate(aa)));
	}

	public static class Person {
		public Person(String a, String b) {
			this.a = a;
			this.b = b;
		}

		public String a;
		public String b;
	}

}
