package com.yogu.core.enums;

/**
 * 用于定义短信通知类型: 1, 新订单通知商家 2, 用户取消网上支付订单 3, 退款成功 4, 商家接收订单 5, 餐厅配送中 6, 3分钟商家未接单通知用户
 * 
 * @author felix
 *
 */
public class SmsConstant {

	/** 领取优惠券或卡包的短信通知模板 */
	public static final String OBTAIN_COUPON_NOTIFY = "couponAndCouponBagNotify";

	/** 短信验证码 */
	public static final String SMS_CODE = "smsCode";

	/** 新订单通知商家 */
	public static final String NEW_ORDER = "newOrder";

	/** 用户取消网上支付订单 */
	public static final String USER_CANCEL_ONLINE_ORDER = "userCancelOnlineOrder";

	/** 退款成功-发送给用户(已经废弃) */
	@Deprecated
	public static final String REFUND_COMPLETED = "refundCompleted";
	
	/** 退款成功-发送给商家 */
	public static final String REFUND_COMPLETED_TO_STORE = "refundCompletedToStore";
	
	/** 退款成功-发送给商家 */
	public static final String REFUND_COMPLETED_TO_USER = "refundCompletedToUser";
	
	/** 商家接收订单 */
	public static final String ACCEPT_ORDER = "accpetOrder";

	/** 餐厅配送中 */
	public static final String DEVIVERING = "delivering";

	/** 3分钟商家未接单通知用户 */
	public static final String OVER_ACCEPT_TIME = "overAcceptTime";

	/** 3分钟商家未接单通知商家 */
	public static final String OVER_ACCEPT_TIME_STORE = "overAcceptTimeStore";

	/** 商家取消订单（退款） */
	public static final String STORE_CANCEL_ORDER = "storeCancelOrder";
	
	/** 商家取消订单 （货到付款）*/
	public static final String STORE_CANCEL_CHSH_ORDER = "storeCancelCashOrder";

	/** 管理员取消订单 */
	public static final String ADMIN_CANCEL_ORDER = "adminCancelOrder";

	/** 提现成功 */
	public static final String WITHDRAW_SUCCESS = "withdrawSuccess";

	/** 提现失败 */
	public static final String WITHDRAW_FAILED = "withdrawFailed";

	/** 超过预计送达一定时间还未接单或配送货到付款订单后, 通知商家接单/配送的短信模板 */
	public static final String OVER_DELIVERY_TIME_NOTIFY_STORE_CASH = "overDeliverTimeNotOperCashOrderNotifyStore";

	/** 超过预计送达一定时间还未接单或配送线上订单后, 通知商家接单/配送的短信模板 */
	public static final String OVER_DELIVERY_TIME_NOTIFY_STORE_ONLINE = "overDeliverTimeNotOperOnlineOrderNotifyStore";

	/** 超过预计送达一定时间还未确认订单, 通知用户签收订单或联系商家 */
	public static final String OVER_DELIVERY_TIME_NOTIFY_USER_CONFIRM = "overDeliverTimeNotConfirmNotifyUser";

	/** 订单自动配送的短信模板 */
	public static final String AUTO_DELIVER_ORDER = "autoDeliverOrder";

	/** 餐厅未营业而超时未接单通知用户 */
	public static final String OVER_BEGIN_TIME_NOT_IN_SERVICE_TIME_NOTIFY = "overBeginTimeNotInServiceTimeNotify";

	// ----------------------- 通知商家顾问

	/** 超过一定时间未接单, 通知商家顾问的短信模板 */
	public static final String OVER_ACCEPT_TIME_NOTIFY_CONSULTANT = "overDeliverTimeNotAcceptNotifyConsultant2";

	/** 超过预计送达一定时间还未接单或配送后, 通知顾问联系商家的短信模板 */
	public static final String OVER_DELIVERY_TIME_NOTIFY_CONSULTANT = "overDeliverTimeNotOperNotifyConsultant";

	/** 超过预计送达一定时间还未配送后, 通知顾问联系商家的短信模板 */
	public static final String OVER_DELIVERY_TIME_NOT_DELIVER_NOTIFY_CONSULTANT = "overDeliverTimeNotDeliverNotifyConsultant2";

	/** 新单通知BD */
	public static final String NEW_ORDER_NOTIFY_BD = "newOrderNotifyBD";

	/** 订单被取消通知BD */
	public static final String CANCEL_ORDER_NOTIFY_BD = "cancelOrderNotifyBD";

	public static final String STORE_ACCEPT_ORDER_NOTIFY_BD = "storeAcceptOrderNotifyBD";

	public static final String STORE_DELIVER_ORDER_NOTIFY_BD = "storeDeliverOrderNotifyBD";

	public static final String STORE_CONFIRM_ARRIVE_NOTIFY_BD = "storeConfirmArriveNotifyBD";

	public static final String USER_CONFIRM_NOTIFY_BD = "userConfirmNotifyBD";

	/** 订单进行到一半通知BD, 已作废 */
	public static final String MID_PROGRESS_NOTIFY_BD = "midOfOrderProgressNotifyBD";

	/** 订单进行到一半通知BD */
	public static final String MID_PROGRESS_NOTIFY_BD_2 = "midOfOrderProgressNotifyBD2";

	/** 刚过预计送达时间还未完成通知BD */
	public static final String OVER_DELIVERY_TIME_NOTIFY_BD = "overDeliveryTimeNotifyBD";
	
	/** 订单评论三星一下通知商家 */
	public static final String ORDER_COMMON_BAD_TO_STORE = "orderCommonBadToStore";
	
	/** 订单评论三星一下通知BA */
	public static final String ORDER_COMMON_BAD_TO_BA = "orderCommonBadToBa";
	
	/** 第三方配送单被取消了，通知商家 */
	public static final String CANCEL_THIRD_EXPRESS_TO_STORE = "cancelThirdExpressToStore";
	
	/** 第三方配送单被取消了，通知客服经理 */
	public static final String CANCEL_THIRD_EXPRESS_TO_CMS = "cancelThirdExpressToCms";
	
	/** 超时没开始配送的顺丰订单，通知商家 */
	public static final String OVER_NOT_BEGIN_DELIVERY_EXPRESS_TO_STORE = "overNotBeginDeliveryExpressToStore";
	
	/** 超时没开始配送的顺丰订单，通知客服经理 */
	public static final String OVER_NOT_BEGIN_DELIVERY_EXPRESS_TO_CMS = "overNotBeginDeliveryExpressToCms";
	
	/**
	 *  顺丰接单时通知商家准备美食	2016-10-27 add by east
	 */
	public static final String SF_EXPRESS_DISTRIBUTED = "sfExpressDistributed";
	
	
	// ----------------------- 其他

	/** 报警短信 */
	public static final String ALARM = "alarm";

	/** 监控短信, 只在短信监控的定时任务中使用 */
	public static final String SMS_MONITORING = "smsMonitoring";
	
	/**
	 * 优惠券过期 短信通知
	 */
	public static final String COUPON_EXPIRE_NOTIFY_SMS = "couponExpireNotifySms";

	/**
	 * 用户下单餐厅当前不在营业时间内，用户下单即通知用户
	 */
	public static final String STORE_NOT_IN_BUSINESS_NOTICE_USER = "storeNotInBusinessNoticeUser";
	
	/**
	 * 购买ticket支付成功
	 */
	public static final String TICKET_ORDER_SUCCESS = "orderTicketSuccess";
	
	/**
	 * 票退款成功
	 */
	public static final String TICKET_REFUND_SUCCESS = "ticketRefundSuccess";
	
	/**
	 * 票退款失败
	 */
	public static final String TICKET_REFUND_FAILED = "ticketRefundFailed";
	
	/**
	 * 票退款失败
	 */
	public static final String TICKET_CHECKOUT_SUCCESS = "ticketCheckoutSuccess";
	
}
