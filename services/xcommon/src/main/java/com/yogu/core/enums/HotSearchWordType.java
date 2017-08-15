package com.yogu.core.enums;

/**
 * 热搜词类型
 * 
 * @author jack 2016/05/03
 */
public enum HotSearchWordType {

	/**
	 * 餐厅热搜词
	 */
	STORE_TYPE((short) 1, "餐厅热搜词"),

	/**
	 * 美食热搜词
	 */
	DISH_TYPE((short) 2, "美食热搜词"),

	/**
	 * 指南热搜词
	 */
	GUIDE_TYPE((short) 3, "指南热搜词"),
	
	/**
	 * cblog热搜词
	 */
	CBLOG_TYPE((short) 4, "cblog热搜词");

	private short value;
	private String name;

	private HotSearchWordType(short value, String name) {
		this.value = value;
		this.name = name;
	}

	public static HotSearchWordType get(short value) {
		switch (value) {
		case 1:
			return STORE_TYPE;
		case 2:
			return DISH_TYPE;
		case 3:
			return GUIDE_TYPE;
		case 4:
			return CBLOG_TYPE;
		default:
			return null;
		}
	}

	public short getValue() {
		return value;
	}

	public String getName() {
		return name;
	}

}
