package com.yogu.core.web;
/** 
 * @author Hins 
 * 
 * @version createTime：2015年7月18日 上午11:06:12 
 * 
 * 订单的错误代码
 */
public class OrderErrorCode {
	
	/**
	 * 菜品,数量参数不相同
	 */
	public static final int DISH_NUM_PARAMETERS_NOT_EQUAL = 3010;
	
	/**
	 * 菜品价格低于最低配送费
	 */
	public static final int MINIMUM_MONEY_NOT_ENOUGH = 3011;
	
	/**
	 * 菜品不存在
	 */
	public static final int DISH_NOT_EXIST = 3012;
	
	/**
	 * 不在配送范围
	 */
	public static final int OUTSIDE_THE_SCOPE = 3013;
	
	/**
	 * 菜品已下架
	 */
	public static final int DISH_HAS_SHELVE = 3014;
	
	/**
	 * 不在配送时间范围
	 */
	public static final int OUTSIDE_THE_SCOPE_OF_DELIVERY_TIME = 3015;
	
	/**
	 * 日期转换失败
	 */
	public static final int SIMPLEDATEFORMAT_PARSE_ERROR = 3020;
	
	/**
	 * 订单归属者非本人
	 */
	public static final int ORDER_BELONG_NOT_HIMSELF = 3021;
	
	/**
	 * 用户收货地址不存在
	 */
	public static final int USER_ADDRESS_NOT_EXIST = 3030;
	
	/**
	 * 调用微信下单接口失败
	 */
	public static final int WECHAT_UNIFIEDORDER_FAIL = 3040;
	
	/**
	 * 支付方式不存在
	 */
	public static final int PAYMODE_NOT_EXIST = 3041;
	
	/**
	 * 门店不存在
	 */
	public static final int STORE_NOT_EXIST = 3042;
	
	/**
	 * 支付方式不正确 (线上支付/现金支付)
	 */
	public static final int PAYTYPE_INCORRECT = 3043;
	
	/**
	 * 订单不存在
	 */
	public static final int ORDER_NOT_EXIST = 3050;
	
	/**
	 * 订单状态不等于已支付
	 */
	public static final int ORDER_STATUS_NOT_EQUALS_PAYMENT = 3051;
	
	/**
	 * 订单不能重复评论
	 */
	public static final int ORDER_CAN_NOT_REPEAT_COMMENT = 3052;
	
	/**
	 * 订单状态不是用户已确认收货
	 */
	public static final int ORDER_STATUS_NOT_USER_CONFIRM_RECEIPT = 3053;
	
	/**
	 * 订单状态不是待付款
	 */
	public static final int ORDER_STATUS_NOT_NON_PAYMENT = 3054;
	
	/**
	 * 订单状态不是申请退款
	 */
	public static final int ORDER_STATUS_NOT_APPLY_REFUND = 3055;
	
	/**
	 * 订单不能重复申请退款
	 */
	public static final int ORDER_CAN_NOT_REPEAT_APPLY_REFUND = 3056;
	
	/**
	 * 订单状态不合法
	 */
	public static final int ORDER_STATUS_ILLEGAL = 3057;
	
	/**
	 * 订单状态错误
	 */
	public static final int ORDER_STATUS_ERROR = 3058;
	
	/**
	 * 评论不存在
	 */
	public static final int COMMENT_NOT_EXIST = 3059;
	
	/**
	 * 菜品库存不足
	 */
	public static final int DISH_UNDERSTOCK = 3060;
	
	/**
	 * 门店未营业
	 */
	public static final int STORE_NOT_IN_BUSSINESS = 3061;
	
	/**
	 * 退款申请不存在
	 */
	public static final int APPLY_REFUND_NOT_EXIST = 3070;
	
	/**
	 * 更新订单评论失败
	 */
	public static final int UPDATE_ORDER_COMMONT_ERROR = 3080;
	
	/**
	 * 获取支付信息接口失败
	 */
	public static final int PAYAPI_GETPAY_ERROR = 3090;
	
	/**
	 * 获取申请退款信息接口失败
	 */
	public static final int PAYAPI_APPLY_REFUND_ERROR = 3091;
	
	/**
	 * 保存订单失败
	 */
	public static final int SAVE_ORDER_FAIL = 3100;
	
	/**
	 * 用户不能重复评分
	 */
	public static final int USER_CAN_NOT_REPEAT_RATED = 3110;
	
	/**
	 * 订单不是在线付款类型
	 */
	public static final int PAY_TYPE_IS_NOT_ONLINE = 3200;
	
	/**
	 * 门店不支持货到付款
	 */
	public static final int NOT_SUPPORT_CASH = 3210;
	
	/**
	 * 订单状态不等于待接单
	 */
	public static final int ORDER_STATUS_NOT_EQUALS_PENDING_ACCEPT = 3250;
	
	/**
	 * 订单金额超过限制
	 */
	public static final int ORDER_TOTAL_FEE_TOO_LONG = 3260;
	
	/**
	 * 重复下单
	 */
	public static final int REPEAT_CREATE_ORDER = 3270;
	
	/**
	 * 退款已经处理
	 */
	public static final int REFUND_HAS_DEAL = 3280;
	
	/**
	 * 优惠券使用失败
	 */
	public static final int USE_COUPON_FAIL = 3290;
	
	/**
	 * 餐厅不支持米星付
	 */
	public static final int STORE_NOT_SUPPORT_MAZING_PAY = 3300;
	
	/**
	 * 餐厅已经对订单添加过备注了
	 */
	public static final int STORE_HAS_ADD_REMARK = 3310;
	
	/**
	 * 订单太早开始第三方配送
	 */
	public static final int THIRD_EXPRESS_TOO_EARLY = 3320;
	
}
