package com.yogu.core.enums;

/**
 * 推送的自定义
 * 
 * @author felix
 * @date 2016-03-28
 */
public enum PushAction {
	/** 打开瀑布流 */
	WATERFALL("waterfall"),
	
	/** 打开餐厅MiniBlog */
	MINIBLOG("miniBlog"),
	
	/** 在首页打开美食卡片 */
	INDEX_DISH_CARD("indexDishCard"),
	
	/** 在餐厅Blog打开美食卡片 */
	BLOG_DISH_CARD("blogDishCard"),
	
	/** 打开HTML5页面 */
	HTML5("h5"),
	
	/** 默认动作 */
	DEFAULT("");
	
	private String value;
	
	private PushAction(String value) {
		this.value = value;
	}
	
	public String getValue() {
        return value;
    }
	
	public static PushAction parseAction(String action) {
		switch (action) {
		case "waterfall":
			return WATERFALL;
		case "miniBlog":
			return MINIBLOG;
		case "indexDishCard":
			return INDEX_DISH_CARD;
		case "blogDishCard":
			return BLOG_DISH_CARD;
		case "h5":
			return HTML5;
		case "":
			return DEFAULT;
		default:
			return null;
		}
	}
	
	public String getActionName() {
		if ("waterfall".equals(this.value))
			return "跳转瀑布流";
		else if ("miniBlog".equals(this.value)) 
			return "跳转miniblog";
		else if ("indexDishCard".equals(this.value)) 
			return "在首页打开美食卡片";
		else if ("blogDishCard".equals(this.value)) 
			return "在餐厅miniblog打开美食卡片";
		else if ("h5".equals(this.value)) 
			return "跳转到H5";
		else 
			return "无";
		
	}
}
