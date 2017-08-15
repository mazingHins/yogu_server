package com.yogu.core.enums;

/**
 * 推送消息的类型, 作用有: 1, 前端选择跳转的页面或URL 2, 前端选择需要刷新信息的页面
 * 
 * @author felix
 */
/**
 * @author felix
 *
 */
public enum MessagePushType {

	/**
	 * 订单状态改变: 商家接单, 完成制作, 配送中, 已送达 <br/>
	 * 1, 客户端跳至订单详情 <br/>
	 * 2, 未定: 底部order tab小红点提示? <br/>
	 */

	/**
	 * 默认, 所有不处理的消息都定义为此值
	 */
	DEFAULT((short) 0),

	/**
	 * 新订单的消息
	 */
	NEW_ORDER((short) 2),

	/**
	 * 退回订单的消息
	 */
	RETURN_ORDER((short) 3),

	/**
	 * 用户取消订单
	 */
	USER_CANCEL_ORDER((short) 4),

	/**
	 * 米星付成功 2016/7/1 hins
	 */
	MAZING_PAY_SUCCESS((short) 10),

	/**
	 * 商家接单的消息类型
	 */
	ACCEPTED_ORDER((short) 20),

	/**
	 * 商家配送中的消息类型
	 */
	DELIVERING_ORDER((short) 25),

	/**
	 * 商家取消线上支付订单的消息类型
	 */
	STORE_CANCEL_ONLINE_ORDER((short) 50),

	/**
	 * 商家取消已完成的线上/米星付订单的消息类型
	 */
	STORE_CANCEL_FINISH_ORDER((short) 55),

	/**
	 * 商家取消现金支付的消息类型
	 */
	STORE_CANCEL_CASH_ORDER((short) 65),

	/**
	 * 第三方配送订单被取消配送
	 */
	CANCEL_THIRD_EXPRESS((short) 70),

	/**
	 * 顺丰接单的消息类型
	 */
	SF_EXPRESS_DISTRIBUTED((short) 80),

	/**
	 * 超时没开始配送的顺丰订单
	 */
	SF_EXPRESS_TIMEOUT_NOT_DELIVERY((short) 85),

	/**
	 * 优惠券过期提醒 消息类型
	 */
	COUPON_EXPIRE_NOTIFY((short) 86),
	
	/**
	 * 4.0 ID 消息内容有更新，发送push通知用户 
	 */
	ID_MESSAGE_UPDATE_NOTIFY((short) 91),

	/**
	 * 4.0 ID 推荐模块有内容更新，发送push通知所有用户 
	 */
	ID_RECOMMEND_UPDATE_NOTIFY((short) 90);

	private short value;

	private MessagePushType(short value) {
		this.value = value;
	}

	public short getValue() {
		return this.value;
	}
}
