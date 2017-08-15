package com.yogu.core.consumer;

/**
 * 定义: 当订单状态变动时推送的消息标题
 * @author felix
 * @date 2015-10-20
 */
public class PushTitle {
	
	/**
	 * 默认推送标题
	 */
	public static final String DEFAULT_TITLE = "米星";
	/**
	 * 超时未支付, 推送给用户的消息标题
	 */
	public static final String OVER_PAY_TIME = "您已成功取消订单";
	
	/**
	 * 用户取消已支付订单后, 推送给用户的消息标题
	 */
	public static final String USER_CANCEL_PAID_ORDER = "您已成功取消订单";
	
	/**
	 * 当商家取消订单时推送给用户的消息标题
	 */
	public static final String STORE_CANCEL_ORDER = "抱歉，商家取消了订单";
	
	/**
	 * 用户取消订单后, 通知商家的标题
	 */
	public static final String USER_CANCEL_ORDER = "用户取消了订单";
	
	/**
	 * 米星付成功推送标题
	 */
	public static final String MAZING_PAY_SUCCESS = "米星付新订单";
	
	/**
	 * 顺丰专送没接单，被取消的推送标题
	 */
	public static final String CANCEL_THIRD_EXPRESS = "顺丰专送未接单";
	
	/**
	 * 顺丰专送接单时,通知商家的推送标题
	 */
	public static final String SF_EXPRESS_DISTRIBUTED = "顺丰专送已接单";
	
	/**
	 * 超时没开始配送的顺丰订单的推送标题
	 */
	public static final String SF_EXPRESS_TIMEOUT_NOT_DELIVERY = "未请求顺丰专送";
	
}
