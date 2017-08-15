/**
 * 
 */
package com.yogu.services.store;

/**
 * 新首页，数据块的操作类型（点击快标题） <br>
 *
 * @author JFan 2016年1月27日 下午2:07:34
 */
public enum RecommendBlockAction {

	HTML5("html5", "h5")//
	, INDEX("瀑布流", "index")//
	;

	private String name;
	private String value;

	private RecommendBlockAction(String name, String value) {
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
	public static RecommendBlockAction giveByValue(String value) {
		for (RecommendBlockAction target : values())
			if (target.value.equals(value))
				return target;
		return null;
	}

	/**
	 * 根据名称获取枚举对象
	 */
	public static RecommendBlockAction giveByName(String name) {
		for (RecommendBlockAction target : values())
			if (target.name.equals(name))
				return target;
		return null;
	}

}
