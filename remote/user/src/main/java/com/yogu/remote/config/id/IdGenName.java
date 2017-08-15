/**
 * 
 */
package com.yogu.remote.config.id;

/**
 * 获取ID的名称（DB配置中应该存在对应的值） <br>
 * 
 * <br>
 * JFan - 2015年6月27日 下午1:38:24
 */
public enum IdGenName {

	/** 公共ID */
	PUBLIC("public_id")

	/** 商家模块下的公共ID */
	, STORE_PUBLIC("store_public_id")
	/** 门店菜品 ID */
	, STORE_DISH("store_dish_id")
	/** 门店 ID */
	, STORE("store_id")

	/** 用户模块下的公共ID */
	, USER_PUBLIC("user_public_id")
	/** 用户模块下的日志类ID */
	, USER_LOG("user_log_id")
	/** 用户ID */
	, USER("user_id")
	/** 用户IM_ID */
	, USER_IM_ID("user_im_id")

	/** 订单模块下的公共ID */
	, ORDER_PUBLIC("order_public_id")
	/** 订单ID */
	, ORDER("order_id")

	/** 支付系统回调ID */
	, PAY_CALLBACK("pay_callback_id")
	/** 支付系统公共ID */
	, PAY_PUBLIC("pay_public_id")
	/** 支付ID */
	, PAY("pay_id")
	
	/** 优惠券系统公告ID */
	, COUPON_PUBLIC("coupon_public_id"),
	
	/** 票系统公共ID */
	TICKET_PUBLIC("ticket_public_id"),
	
	/** 第三方配送ID **/
	EXPRESS("express_id"),
	
	/** 第三方配送模块的公共ID **/
	EXPRESS_PUBLIC("express_public_id"),
	
	/**
	 * 团拼购买记录表
	 */
	TEAM_PAY_RECORD_ID("record_id"),
	
	/**
	 * 团拼购买关联表
	 */
	TEAM_PAY_BUG_ID("buy_id")
	;

	private String name;

	private IdGenName(String name) {
		this.name = name;
	}

	/**
	 * @return name
	 */
	public String getName() {
		return name;
	}

}
