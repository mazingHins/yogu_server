/**
 * 
 */
package com.yogu.services.store;

/**
 * 新首页布局，块类型 <br>
 *
 * @author JFan 2016年1月19日 下午9:13:44
 */
public enum RecommendBlockType {

	ADVER("轮播广告", "adver", null)//
	, STORE("餐厅列表", "store", null)//
	, DISH("美食列表", "dish", null)//

	, ROW1X4("一行四列", "row1x4", 4)// jfan 3-28 add
	, ROWS2X4("两行四列", "rows2x4", 8)// jfan 3-28 add
	;

	/**
	 * 强制要求在首页上显示几个item<br>
	 * 如果设置了数值，则至少设置这么多个item，并且只显示这么多个item<br>
	 * 如果为null，则表示不受这里的限制，最多显示IndexResource.maxItemSize个（6个）<br>
	 * 
	 * jfan 3-28 add
	 */
	private Integer forceShowNum;
	private String name;
	private String value;

	private RecommendBlockType(String name, String value, Integer forceShowNum) {
		this.name = name;
		this.value = value;
		this.forceShowNum = forceShowNum;
	}

	public String getName() {
		return name;
	}

	public String getValue() {
		return value;
	}

	/**
	 * @return forceShowNum
	 */
	public Integer getForceShowNum() {
		return forceShowNum;
	}

	// ####

	/**
	 * 根据value获取枚举对象
	 */
	public static RecommendBlockType giveByValue(String value) {
		for (RecommendBlockType type : values())
			if (type.value.equals(value))
				return type;
		return null;
	}

	/**
	 * 根据名称获取枚举对象
	 */
	public static RecommendBlockType giveByName(String name) {
		for (RecommendBlockType type : values())
			if (type.name.equals(name))
				return type;
		return null;
	}

}
