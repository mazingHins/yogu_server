package com.yogu.core.consumer;

/**
 * 消费者 消费的 业务类型 常量定义(所有的消费者 业务类型都在这里定义 MQ参数中的 subExpression)
 * 
 * @author sky
 *
 */
public class BussinessType {

	/**
	 * consumer消费多个业务类型, 多个业务类型间的连接符
	 */
	public static final String multi_bizType_joiner = "||";

	/**
	 * 门店有新订单(短信提醒商家)
	 */
	public static final String STORE_NEW_ORDER_NOTIFY = "store_have_new_orders";

	/**
	 * 用户退款成功(短信提醒用户)
	 */
	public static final String USER_REFUND_SUCCESS = "user_refund_success";

	/**
	 * 消息推送
	 */
	public static final String MESSAGE_PUSH = "message_push";

	/**
	 * 测试MQ功能
	 */
	public static final String TEST_MQ_FUNC = "test_mq_function";

	/**
	 * 报警信息提醒
	 */
	public static final String ALARM_SEND = "alarm_send";

	/**
	 * 每一笔金额变动的对账
	 */
	public static final String ACCOUNT_DETAIL = "account_detail";

	/**
	 * Ten 的测试
	 * 
	 * @author ten 2015/10/10
	 */
	public static final String TEST_MQ_TEN = "test-ten";

	/**
	 * 普通短信通知
	 */
	public static final String SMS_SEND = "sms_send";

	/**
	 * 通用类型
	 */
	public static final String COMMON_SIGNAL = "common_signal";

	/**
	 * 商家日志
	 */
	public static final String MERCHANT_LOG = "merchant_log";

	/**
	 * 新订单
	 */
	public static final String NEW_ORDER = "new_order";

	/**
	 * 取消订单
	 */
	public static final String CANCEL_ORDER = "cancel_order";

	/**
	 * 管理员在后台取消订单
	 */
	public static final String ADMIN_CANCEL_ORDER = "admin_cancel_order";

	/**
	 * 定时任务取消超时未支付订单
	 */
	public static final String CANCEL_NOT_PAY_ORDER = "cancel_not_pay_order";

	/**
	 * 超时未接单提醒
	 */
	public static final String OVER_ACCEPT_TIME_NOTIFY = "over_accept_time_notify";

	/**
	 * 接单
	 */
	public static final String ACCEPT_ORDER = "accept_order";

	/**
	 * 配送/开始发货
	 */
	public static final String DELIVER_ORDER = "deliver_order";

	/**
	 * 餐厅取消订单
	 */
	public static final String STORE_CANCEL_ORDER = "store_cancel_order";

	/**
	 * 餐厅退回订单
	 */
	public static final String STORE_RETURN_ORDER = "store_return_order";

	/**
	 * 用户申请退款
	 */
	public static final String USER_APPLY_REFUND = "user_apply_refund";

	/**
	 * 商家申请退款
	 */
	public static final String STORE_APPLY_REFUND = "store_apply_refund";

	/**
	 * 商家申请退款(对已完成的订单)
	 */
	public static final String STORE_APPLY_REFUND_FINISH = "store_apply_refund_finish";

	/**
	 * 退款成功
	 */
	public static final String REFUND_SUCCESS = "refund_success";

	/**
	 * 提现结果处理
	 */
	public static final String WITHDRAW_RESULT = "withdraw_result";

	/**
	 * 确认收货
	 */
	public static final String CONFIRM_RECEIPT = "confirm_receipt";

	/**
	 * 定时任务申请退款
	 */
	public static final String TIMER_APPLY_REFUND = "timer_apply_refund";

	/**
	 * 超过预计送达时间未接单
	 */
	public static final String OVER_DELIVERY_NOT_ACCEPT_ORDER = "over_delivery_not_accept_order";

	/**
	 * 超过预计送达时间(5分钟)未接单
	 */
	public static final String OVER_DELIVERY_NOT_ACCEPT_ORDER_5_MIN = "over_delivery_not_accept_order_5_min";

	/**
	 * 超过订单开始时间未接单 (通知用户)
	 */
	public static final String OVER_BEGIN_TIME_NOTIFY_USER = "over_begin_time_notify_user";

	/**
	 * 超过预计送达时间一定时间未配送通知商家
	 */
	public static final String OVER_DELIVERY_NOT_DELIVER_ORDER = "over_delivery_not_deliver_order";

	/**
	 * 超过预计送达时间一定时间未接单通知用户
	 */
	public static final String OVER_DELIVERY_NOT_CONFIRM = "over_delivery_not_confirm";

	/**
	 * 批量生成优惠券
	 */
	public static final String BATCH_CREATE_COUPONS = "batch_create_coupons";

	/**
	 * 优惠券领取检查及分配优惠券
	 */
	public static final String COUPON_CHECK_AND_ASSIGN = "coupon_check_and_assign";

	/**
	 * 重新load指定的优惠券到redis缓存中
	 */
	public static final String LOAD_COUPONS_TO_CACHE = "load_coupons_to_cache";

	/**
	 * 优惠券过期 推送提醒
	 */
	public static final String COUPON_EXPIRE_NOTIFY_PUSH = "coupon_expire_notify_push";
	/**
	 * 优惠券过期 短信提醒
	 */
	public static final String COUPON_EXPIRE_NOTIFY_SMS = "coupon_expire_notify_sms";

	/**
	 * 通知用户 优惠券已经 分配到用户的账户
	 */
	public static final String NOTIFY_USER_COUPON_HAS_ASSIGNED = "notify_user_coupon_has_assigned";

	/**
	 * 优惠券被转让出去的通知
	 */
	public static final String NOTIFY_COUPON_HAS_BEEN_TRANSFERED = "notify_coupon_has_been_transfered";

	/**
	 * 订单已支付（货到付款/支付完成）
	 */
	public static final String PENDING_ORDER = "pending_order";

	/**
	 * 订单自动配送
	 */
	public static final String AUTO_DELIVER_ORDER = "auto_deliver_order";

	/**
	 * 商家确认送达
	 */
	public static final String STORE_CONFIRM_DELIVERED = "store_confirm_delivered";

	/**
	 * 进行中订单, 定时通知的任务
	 */
	public static final String ONGOING_ORDER_AUTO_NOTIFY = "ongoing_order_auto_notify";

	/**
	 * 用户对订单进行评论
	 */
	public static final String USER_COMMENT_ORDER = "userCommentOrder";

	// /**
	// * 超过预计送达时间一定时间未配送通知客户经理 (只有电话号码)
	// */
	// public static final String OVER_DELIVERY_NOT_DELIVER_ODER_CONSULT = "over_delivery_not_deliver_order_consult";

	/**
	 * 删除餐厅tag
	 */
	public static final String DELETE_STORE_TAG = "delete_store_tag";

	/**
	 * 修改客服 2016/2/4 by ten
	 */
	public static final String CHANGE_CUSTOMER_SERVICE_OFFICER = "change_customer_service_officer";

	/**
	 * 领取优惠券或卡包 2016/2/27 by felix
	 */
	public static final String OBTAIN_COUPON_OR_BAG = "obtain_coupon_or_bag";

	/**
	 * 新菜品上架 2016/5/3 by felix
	 */
	public static final String NEW_DISH = "new_dish_onshelf";

	/**
	 * 改变配送范围 2016/5/3 by felix
	 */
	public static final String SERVICE_RANGE_CHANGE = "service_range_change";

	/**
	 * 改变营业时间 2016/5/3 by felix
	 */
	public static final String SERVICE_TIME_CHANGE = "service_time_change";

	/**
	 * 改变封面图片 2016/5/3 by felix
	 */
	public static final String TOPIC_IMAGE_CHANGE = "topic_image_change";

	/**
	 * 商家准备营业前的准备工作 (现在有工作报告) felix 2016-05-26
	 */
	public static final String STORE_START_SERVICE_PREPARATION = "store_start_service_preparation";

	/**
	 * 米星付成功 2016/7/1 hins
	 */
	public static final String MAZING_PAY_SUCCESS = "mazing_pay_success";

	/**
	 * 定时任务自动取消超时没接单的顺丰配送单 2016-10-20 add by hins
	 */
	public static final String TIMER_CANCEL_TIMEOUT_SF_EXPRESS = "timer_cancel_timeout_sf_express";

	/**
	 * 顺丰接单时通知商家准备美食 2016-10-26 add by east
	 */
	public static final String SF_EXPRESS_DISTRIBUTED = "sf_express_distributed";

	/**
	 * 超时没开始配送的顺丰订单 2016/10/28 hins
	 */
	public static final String OVER_NOT_BEGIN_DELIVERY_EXPRESS_TIME = "over_not_begin_delivery_express_time";

	/**
	 * 用户下单餐厅当前不在营业时间内，用户下单即通知用户 2016-11-23 east
	 */
	public static final String STORE_NOT_IN_BUSINESS_NOTICE_USER = "storeNotInBusinessNoticeUser";
	/**
	 * 新订单预定到时提醒
	 */
	public static final String NEW_ORDER_RESERVE_REMIND = "new_order_reserve_remind";
	/**
	 * 解锁使用中的优惠券 add by Sky 2017-01-14
	 */
	public static final String COUPON_UNLOCK = "unlock_coupon";
	/**
	 * 收藏餐厅
	 */
	public static final String FAV_STORE = "fav_store";
	/**
	 * 取消收藏餐厅
	 */
	public static final String CANCEL_FAV_STORE = "cancel_fav_store";

	/**
	 * 用户使用了标签进行随变
	 */
	public static final String SELECT_CUSTOMIZE_TAG_POOL = "select_customize_tag_pool";

	/**
	 * 查看cblog
	 */
	public static final String VIEW_CBLOG = "view_cblog";

	/**
	 * 购买ticket支付成功
	 */
	public static final String TICKET_ORDER_SUCCESS = "order_ticket_success";
	/**
	 * 票退款，包括申请退款、退款成功
	 */
	public static final String TICKET_REFUND = "ticket_refund";

	/**
	 * 票核销成功
	 */
	public static final String TICKET_CHECKOUT_SUCCESS = "ticket_checkout_success";
	
	/**
	 * 票过期通知
	 */
	public static final String TICKET_EXPIRE_NOTIFY = "ticket_expire_notify";
	
	/**
	 * 团购未成功
	 */
	public static final String TEAM_NOT_SUCCESS = "teamNotSuccess";

}
