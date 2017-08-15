package com.yogu.commons.datasource;

/**
 * 线程安全的DbContextHolder类<br>
 * 动态切换数据源工具类
 */
public class DBContextHolder {

	private static final ThreadLocal<Source> contextHolder = new ThreadLocal<Source>();

	/**
	 * 设置使用何种数据源<br>
	 */
	public static void setSource(Source source) {
		contextHolder.set(source);
	}

	/**
	 * 获取正在使用何种数据源<br>
	 * 一般用于打印日志
	 */
	public static Source getSource() {
		return contextHolder.get();
	}

	/**
	 * clean
	 */
	public static void clearDBType() {
		contextHolder.remove();
	}

}
