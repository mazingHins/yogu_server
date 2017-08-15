/**
 * 
 */
package com.yogu.services.store;

/**
 * 新首页，数据项的目标类型 <br>
 *
 * @author JFan 2016年1月19日 下午9:18:32
 */
public enum RecommendItemTarget {

	HTML5("html5", "h5")//
	, STORE("餐厅", "store")//
	, DISH("美食", "dish")//
	, INDEX("瀑布流", "index")//
	;

	private String name;
	private String value;

	private RecommendItemTarget(String name, String value) {
		this.name = name;
		this.value = value;
	}

	public String getName() {
		return name;
	}

	public String getValue() {
		return value;
	}

	// ####

	/**
	 * 根据value获取枚举对象
	 */
	public static RecommendItemTarget giveByValue(String value) {
		for (RecommendItemTarget target : values())
			if (target.value.equals(value))
				return target;
		return null;
	}

	/**
	 * 根据名称获取枚举对象
	 */
	public static RecommendItemTarget giveByName(String name) {
		for (RecommendItemTarget target : values())
			if (target.name.equals(name))
				return target;
		return null;
	}

}
