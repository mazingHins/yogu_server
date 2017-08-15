package com.yogu.core.consumer;

/**
 * 定义: 推送自定义参数params的action值定义
 *
 * @date 2016年7月1日 下午1:18:25
 * @author hins
 */
public class PushParamAction {
	
	/**
	 * 米星付成功，前往B端米星付列表
	 */
	public static final String MAZING_PAY_SUCCESS_B = "bMazingPaySuccess";
	
	/**
	 * 超时没开始顺丰配送
	 */
	public static final String TIMEOUT_NOT_DELIVERY_B = "bTimeOutNotDelivery";
	
	/**
	 * 顺丰配送单被取消
	 */
	public static final String CANCEL_SF_EXPRESS_B = "bCancelSfExpress";


	/**
	 * 前往C端站内信
	 */
	public static final String SYS_MSG_C = "cSysMsg";
	
	/**
	 * 前往C端优惠券列表
	 */
	public static final String COUPONS_C = "cCoupons";
	
	/**
	 * 前往C端订单详情
	 */
	public static final String ORDER_DETAIL_C = "cOrderDetail";
	
	/**
	 * 前往C端进行中订单
	 */
	public static final String INPROGRESS_ORDERS_C = "cInprogressOrders";

	
}
