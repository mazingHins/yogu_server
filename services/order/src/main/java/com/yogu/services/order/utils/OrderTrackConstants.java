package com.yogu.services.order.utils;

/**
 * 订单轨迹相关常量
 * 
 * @author Hins
 * @date 2015年11月17日 下午6:05:17
 */
public class OrderTrackConstants {

	/**
	 * 创建订单成功的订单轨迹说明
	 */
	public static final String CREATE_ORDER_TRACK = "订单号：{0}请尽快支付";
	
	/**
	 * 支付成功/货到付款后，上一步“创建订单”的订单轨迹说明
	 */
	public static final String CREATE_ORDER_TRACK_BY_PAY_SUCCESS = "订单号：{0}";
	
	/**
	 * 支付成功后的订单轨迹说明
	 */
	public static final String PAY_SUCCESS_ORDER_TRACK = "已付款到米星担保账户，请等待商家确认";
	
	/**
	 * 支付失败后的订单轨迹说明
	 */
	public static final String PAY_FAIL_ORDER_TRACK = "订单支付失败";
	
	/**
	 * 货到付款后的订单轨迹说明
	 */
	public static final String CASH_SUCCESS_ORDER_TRACK = "请等待商家确认";
	
	/**
	 * 用户申请退款的订单轨迹说明
	 */
	public static final String USRE_ALIPAY_REFUND_ORDER_TRACK = "申请退款中";
	
	/**
	 * 商家申请退款的订单轨迹说明
	 */
	public static final String STORE_ALIPAY_REFUND_ORDER_TRACK = "申请退款中";
	
	/**
	 * 拒绝退款的订单轨迹说明
	 */
	public static final String REFUSE_REFUND_ORDER_TRACK = "拒绝退款，原因：{0}";
	
	/**
	 * 接单后的订单轨迹说明
	 */
	public static final String ACCEPT_ORDER_TRACK = "美食准备中，请耐心等待";
	
	/**
	 * 完成制作后的订单轨迹说明
	 */
	public static final String FINISH_COOKING_ORDER_TRACK = "商家已完成制作";
	
	/**
	 * 配送中的订单轨迹说明
	 */
	public static final String DELIVERY_ORDER_TRACK = "商家已开始配送";
	
	/**
	 * 商家选择顺丰配送的订单轨迹说明
	 */
	public static final String DELIVERY_ORDER_BY_SF_TRACK = "商家选择顺丰配送，顺丰订单编号：{0}";
	
	/**
	 * 取消顺丰配送的订单轨迹说明
	 */
	public static final String CALCEN_SF_EXPRESS_TRACK = "已取消顺丰专送，原因：";
	
	/**
	 * 商家已确认送达的订单轨迹说明
	 */
	public static final String STORE_CONFIRM_ORDER_TRACK = "商家已确认送达";
	
	/**
	 * 顺丰已确认送达的订单轨迹说明
	 */
	public static final String SF_CONFIRM_ORDER_TRACK = "顺丰已确认送达";
	
	/**
	 * 用户已确认送达的订单轨迹说明
	 */
	public static final String USER_CONFIRM_ORDER_TRACK = "用户已确认送达";
	
	/**
	 * 用户已确认送达的订单轨迹说明-米星付
	 */
	public static final String USER_CONFIRM_ORDER_TRACK_BY_MAZING_PAY = "用户已确认送达";
	
	/**
	 * 退回的订单轨迹说明
	 */
	public static final String RETURN_ORDER_TRACK = "商家已退回订单";
	
	/**
	 * 拒单的订单轨迹说明
	 */
	public static final String REJECT_ORDER_TRACK = "商家已拒绝订单";
	
	/**
	 * 米星退款订单的订单轨迹说明
	 */
	public static final String ADMIN_REFUND_ORDER_TRACK = "米星平台取消该订单，退款将于2日内到账。";
	
	/**
	 * 米星取消订单的订单轨迹说明
	 */
	public static final String ADMIN_CANCEL_ORDER_TRACK = "米星平台取消该订单。";
	
	/**
	 * 定时任务取消订单的订单轨迹说明
	 */
	public static final String TIMER_CANCEL_ORDER_TRACK = "米星平台取消该订单。";
	
	/**
	 * 用户取消订单的订单轨迹说明
	 */
	public static final String USER_CANCEL_ORDER_TRACK = "用户取消该订单。";
	
	/**
	 * 商家取消订单的订单轨迹说明
	 */
	public static final String STORE_CANCEL_ORDER_TRACK = "商家取消该订单。";
	
	
}
