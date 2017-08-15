package com.yogu.core.enums.order;

import com.yogu.CommonConstants;

/**
 * 订单有关的常量 <br>
 * 
 * @author JFan 2015年7月20日 下午5:02:21
 */
public class OrderConstants {

	/** 订单轨迹初始状态 */
	public static final short ORDER_TRACK_INIT_STATUS = 0;

	/** pay支付回调order域名 */
	public static final String PAY_NOTIFY_URL = CommonConstants.ORDER_DOMAIN + "/api/v1/order/pay/notify.do";

	/** pay退款回调order域名 */
	public static final String REFUND_NOTIFY_URL = CommonConstants.ORDER_DOMAIN + "/api/v1/order/refund/notify.do";

	/**
	 * 接单可用时长 180秒(3分钟)
	 */
	public static final int ACCEPT_TIME_LENGTH = 3 * 60;

	/** 自动确认收货：load数据的起始时间（秒） */
	public static final String AUTO_CONFIRM_BEGINTIME = "orderAutoConfirmBegin";

	/** 自动确认收货：超时时间（秒） */
	public static final String AUTO_CONFIRM_SCALE = "orderAutoConfirmScale";

	/**
	 * 成功CODE
	 */
	public static final short RESULT_CODE_SUCCESS = 1;

	/**
	 * 库存不足
	 */
	public static final short RESULT_CODE_UNDER_STOCK = -1;

	/**
	 * 菜品下架
	 */
	public static final short RESULT_CODE_DISH_OFF_SHELF = -2;

	/** 用户地址能否选择的code-不在配送范围 */
	public static final short RESULT_CODE_ADDRESS_OUTSIDE = 10;

	/**
	 * 库存剩余数量=0时候，显示的提示语
	 */
	public static final String SURPLUS_ZERO_CONTENT = "已售馨";

	/**
	 * 库存剩余数量不足，单>0时候，显示的提示语
	 */
	public static final String SURPLUS_NOT_ENOUGH_CONTENT = "仅余{0}份";

	/**
	 * 用户取消付订单-记录取消的原因
	 */
	public static final String USER_CANCEL_NOT_PAY_ORDER_REASON = "自行取消";

	/**
	 * 取消超时的订单-记录取消的原因
	 */
	public static final String TIME_OUT_ORDER_REASON = "超时自动取消";
	
	/**
	 * 管理员取消的订单-记录取消的原因
	 */
	public static final String ADMIN_ORDER_REASON = "米星平台取消";
	
	/**
	 * 订单金额的最大值（100万）
	 */
	public static final int ORDER_FEE_THRESHOLD = 100000000;
	
	/**
	 * 货到付款的支付方式
	 */
	public static short PAY_MODE_CACH = 3;
	
	/**
	 * 数据库默认的支付方式
	 */
	public static short PAY_MODE_DEFAULT = 0;
	
	/**
	 * 客户端可用优惠券button标题-无可用优惠券
	 */
	public static String NOT_USES_COUPON_TITLE = "无可用优惠券";
	
	/**
	 * 客户端可用优惠券button标题-无可用优惠券
	 */
	public static String HAS_USES_COUPON_TITLE = "有可用优惠券";
	
	/**
	 * 标识预下单接口只检测库存信息的标志位
	 */
	public static final short CHECK_SURPLUS_ONLY = 1;
	
	/**
	 * 米星付订单body内容
	 */
	public static final String MAZING_PAY_ORDER_BODY = "米星付";
	
	/**
	 * config表的config_key的值：后台订单报表统计条件-最低订单金额值，单位分
	 */
	public static final String ORDER_STATISTICS_TOTALFEE_MIN_VALUE = "orderStatisticsTotalFeeMinValue";
	
	/**
	 * config表的config_key的值：后台米星付订单报表统计条件-最低订单金额值，单位分
	 */
	public static final String ORDER_MAZINGPAY_STATISTICS_TOTALFEE_MIN_VALUE = "orderMazingPayStatisticsTotalFeeMinValue";

}
