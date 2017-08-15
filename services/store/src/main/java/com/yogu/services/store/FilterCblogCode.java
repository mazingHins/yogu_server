package com.yogu.services.store;

public enum FilterCblogCode {
	/** 标签筛选餐厅 **/
	TAG("tag"),

	/** 米星服务餐厅 **/
	MAZING("mazing"),

	/** 普通推荐餐厅 **/
	OTHER("other");

	private final String type;

	private FilterCblogCode(final String type) {
		this.type = type;
	}

	public String getType() {
		return type;
	}

	/**
	 * 根据type反查FilterCblogCode对象<br>
	 * 如果没找到，则返回null
	 */
	public static FilterCblogCode giveFilterCblogCode(String type) {
		for (FilterCblogCode filterCblogCode : values())
			if (filterCblogCode.type.equals(type))
				return filterCblogCode;
		return null;
	}
}
