package com.yogu.services.order.utils;

/**
 * 退款申请对象类型
 * 
 * @author Hins
 * @date 2015年9月11日 上午10:44:47
 */
public enum RefundType {

	/**
	 * 用户申请退款
	 */
	USER((short) 1),

	/**
	 * 商家申请退款
	 */
	STORE((short) 2),

	/**
	 * 管理员申请退款
	 */
	ADMIN((short) 3),

	/**
	 * 定时任务申请退款
	 */
	TIMER((short) 4);

	private short value;

	private RefundType(short value) {
		this.value = value;
	}

	public short getValue() {
		return value;
	}

	public static RefundType valueOf(short value) {
		switch (value) {
		case 1:
			return USER;
		case 2:
			return STORE;
		case 3:
			return ADMIN;
		case 4:
			return TIMER;
		default:
			return null;
		}
	}
}
