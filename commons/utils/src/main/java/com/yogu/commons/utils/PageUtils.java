/**
 * 
 */
package com.yogu.commons.utils;

/**
 * 分页有关的工具处理接口 <br>
 * 
 * @author JFan 2015年7月20日 上午10:21:57
 */
public final class PageUtils {

	/**
	 * 计算数据总共有多少页
	 * 
	 * @param count 总记录数
	 * @param pageSize 每页条数
	 */
	public static int pageCount(int count, int pageSize) {
		if (0 >= count)
			return 1;
		int count1 = count / pageSize;
		int count2 = count % pageSize;
		return (0 >= count2 ? count1 : count1 + 1);
	}

	/**
	 * 起始页小于‘限定页值’时，使用‘限定页值’
	 * 
	 * @param pageIndex 起始页
	 * @param pageLimit 限定页值
	 */
	public static int limitIndex(Integer pageIndex, int pageLimit) {
		if (null == pageIndex)
			return pageLimit;
		return (pageLimit > pageIndex ? pageLimit : pageIndex);
	}

	/**
	 * 限制每页大小（上小限）在指定的范围内（闭区间）
	 * @param pageSize 每页大小
	 * @param miniPageSize 最小值，pageSize 小于这个值的则取此值
	 * @param maxPageSize 最大值，pageSize 大于等于这个值则取此值
	 */
	public static int limitSize(Integer pageSize, int miniPageSize, int maxPageSize) {
		return (null == pageSize || miniPageSize >= pageSize ? miniPageSize : // 最少
				(maxPageSize < pageSize ? maxPageSize : pageSize));// 最多;
	}

	/**
	 * 计算起始偏移量下标（0开始）
	 * 
	 * @param pageIndex 第几页（1开始，小于1则默认第一页）
	 * @param pageSize 每页条数（必须大于1）
	 */
	public static int offset(int pageIndex, int pageSize) {
		int index = limitIndex(pageIndex, 1);
		return offset0(index, pageSize);
	}

	/**
	 * 计算起始偏移量下标（0开始）
	 * 
	 * @param pageIndex 第几页（1开始，不接受小于1）
	 * @param pageSize 每页条数（必须大于1）
	 */
	private static int offset0(int pageIndex, int pageSize) {
		Args.check(1 <= pageIndex, "Begin from the first page.");
		Args.check(1 <= pageSize, "The page number should be greater than 0.");
		return ((pageIndex - 1) * pageSize);
	}

}
