package com.yogu.core.consumer;

/**
 * 定义: 当订单状态变动时推送的消息
 * TODO 将此文件配置移至mz_config
 * @author felix
 *
 */
/**
 * @author felix
 *
 */
public class PushMessage {

	/**
	 * 当商家接单时推送给用户的消息
	 */
	public static final String ORDER_ACCEPTED = "【{0}餐厅】已接收您的订单，美食正在准备中";

	/**
	 * 当商家完成制作时推送给用户的消息
	 */
	public static final String FINISHED_COOKING = "您下单的菜品已经制作完成, 商家正在为您准备配送, 请耐心等候!";

	/**
	 * 当商家配送中推送给用户的消息
	 */
	public static final String DELIVERING = "【{0}餐厅】正在配送您的订单，请准备签收";

	/**
	 * 当商家送达时推送给用户的消息
	 */
	public static final String COMPLETED_DELIVERING = "您下单的菜品已送达, 请您验收!";

	/**
	 * 当商家取消已支付订单时推送给用户的消息模板
	 */
	public static final String STORE_CANCEL_PAID_ORDER = "【{0}餐厅】取消了您的订单，将于1-3个工作日内退款，如有疑问请联系餐厅";

	/**
	 * 商家/管理员退款已完成的订单推送给用户的消息模板
	 */
	public static final String STORE_CANCEL_PAID_FINISH_ORDER = "【{0}餐厅】取消了您的订单{1}，款项将在3个工作日内到账。如有疑问，请联系餐厅。";

	/**
	 * 当商家取消货到付款订单时推送给用户的消息模板
	 */
	public static final String STORE_CANCEL_CASH_ORDER = "【{0}餐厅】取消了您的订单，如有疑问请联系餐厅";

	/**
	 * 当订单在一定时间内未被接单推送给用户的消息
	 */
	public static final String OVER_ACCEPT_TIME = "【{0}餐厅】可能因为忙碌暂时未接您的订单{1}，为不影响用餐，请您和商家联系或者取消订单。";

	/**
	 * 超时未支付, 推送给用户的消息模板
	 */
	public static final String OVER_PAY_TIME = "您在【{0}餐厅】下的订单{1}，30分钟内未完成支付，已自动取消。";

	/**
	 * 用户取消已支付订单后, 推送给用户的消息模板
	 */
	public static final String USER_CANCEL_PAID_ORDER = "您在【{0}餐厅】下的订单{1}，已成功取消，款项将原路退回您的支付账户，预计1-3个工作日内到账，请及时查收。";

	/**
	 * 用户取消已支付订单后, 推送给用户的消息模板
	 */
	public static final String USER_CANCEL_CASH_ORDER = "您在【{0}餐厅】下的订单{1}，已成功取消。";

	/**
	 * 新订单的推送模板, 暂用于支付成功后和货到付款下单后
	 */
	public static final String NEW_ORDER = "您有一笔新订单，请尽快处理。";

	/**
	 * 新订单预定到时提醒的推送模板, 暂用于支付成功后和货到付款下单后
	 */
	public static final String NEW_ORDER_RESERVE_REMIND = "温馨提示：预订单{0}预订今日{1}送达，请及时处理。";

	/**
	 * 退回订单模板(B端)
	 */
	public static final String RETURN_ORDER = "您有一笔退回订单，请尽快处理。";

	/**
	 * 超过预计送达一定时间还未接单或配送后, 通知商家接单/配送的推送模板
	 */
	public static final String OVER_DELIVERY_TIME_NOTIFY_CANCEL = "您的订单{0}还未{1}，{2}后将自动取消{3}，请马上{4}!";

	/**
	 * 超过预计送达一定时间还未接单或配送后, 通知顾问联系商家的推送模板
	 */
	public static final String OVER_DELIVERY_TIME_NOTIFY_CONSULTANT = "{0}商家的订单{1}还未{2}，{3}后将自动取消，请马上联系商家!";

	/**
	 * 超过预计送达一定时间用户还未签收后, 通知用户联系商家的推送模板
	 */
	public static final String OVER_DELIVERY_TIME_NOTIFY_CONFIRM = "您在{0}的订单{1}将在{2}后自动签收，如果还没收到美食，请联系商家。";

	/**
	 * 订单超过预计送达时间2小时自动更新配送的推送模板
	 */
	public static final String AUTO_DELIVER_ORDER = "您的订单{0}因超时已由系统自动配送，为保证用户体验，以后请务必及时配送!";

	/**
	 * 用户自行取消订单时, 推送给商家的消息模板
	 */
	public static final String USER_CANCEL_ORDER_NOTIFY_STORE = "{0}订单#{1}已被自行取消，您可前往退款中/已结束订单查看";

	/**
	 * 管理员取消订单通知用户
	 */
	public static final String ADMIN_CANCEL_ORDER_NOTIFY_USER = "您于【{0}】餐厅下的订单已被米星平台取消，如有疑问请联客服微信mazingapp";

	/**
	 * 管理员取消订单通知商家
	 */
	public static final String ADMIN_CANCEL_ORDER_NOTIFY_STORE = "【{0}】订单#{1}已被米星平台取消，如有疑问请联系客户经理";

	/**
	 * 餐厅未营业通知用户
	 */
	public static final String OVER_BEGIN_TIME_NOT_IN_SERVICE_TIME_NOTIFY = "【{0}餐厅】休息中，暂时无法接单，请等待餐厅营业；如有特殊要求请自行联系餐厅哦";

	/**
	 * 餐厅每日开业报告
	 */
	public static final String STORE_DAILY_REPORT_TEMPLATE = "【米星日报】尊敬的商家，您在米星的餐厅即将营业。您有{0}张新单等待接单，{1}张订单需今天完成。";

	/**
	 * 餐厅每日开业报告
	 */
	public static final String STORE_DAILY_REPORT_TEMPLATE_PENDING_ACCEPT = "【米星日报】尊敬的商家，您在米星的餐厅即将营业。您有{0}张新单等待接单。";

	/**
	 * 餐厅每日开业报告
	 */
	public static final String STORE_DAILY_REPORT_TEMPLATE_PENDING_FINISH = "【米星日报】尊敬的商家，您在米星的餐厅即将营业。{0}张订单需今天完成。";

	/**
	 * 米星付成功
	 */
	public static final String MAZING_PAY_SUCCESS = "收到米星付款项{0}元，请前往核对。";
	
	/**
	 * 秒付成功
	 */
	public static final String QUICK_PAY_SUCCESS = "【{0}】桌号{1}客户已买单，应收金额¥{2}，实收金额¥{3}。";
	
	/**
	 * 秒付成功
	 */
	public static final String QUICK_PAY_SUCCESS_INVOICE_TITLE = "【{0}】桌号{1}客户已买单，应收金额¥{2}，实收金额¥{3}，需开发票：{4}";

	/**
	 * 定时任务取消超时没接单顺丰配送
	 */
	public static final String TIMER_CANCEL_TIMEOUT_SF_EXPRESS = "订单{0}已超过15分钟没有顺丰专送员接单，请选择自行配送。";

	/**
	 * 顺丰接单时，通知商家准备美食
	 */
	public static final String SF_EXPRESS_DISTRIBUTED = "顺丰专送员{0}({1})已接单(订单号{2}),请准备。";

	/**
	 * 超时没开始配送的顺丰订单
	 */
	public static final String OVER_NOT_BEGIN_DELIVERY_EXPRESS_TIME = "您有订单{0}还未请求顺丰专送，请及时点击确认配送。";

	/**
	 * 优惠券过期提醒push 内容
	 */
	public static final String COUPON_EXPIRE_NOTIFY_CONTENT = "您的米星券将在{0}天后过期，点击查看！";

	/**
	 * 用户下单餐厅当前不在营业时间内，用户下单即通知用户
	 */
	public static final String STORE_NOT_IN_BUSINESS_NOTICE_USER = "您的预订单已收到，{0}餐厅当前不在营业时间，可能稍晚接单，不会影响配送，请耐心等待确认或直接联系餐厅";
	
	
}
