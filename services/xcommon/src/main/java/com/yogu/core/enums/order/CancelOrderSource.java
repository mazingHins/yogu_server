package com.yogu.core.enums.order;

/**
 * 取消订单方法调用者的定义
 * 
 * @author Hins
 * @date 2016年1月15日 下午5:19:42
 */
public enum CancelOrderSource {
	/**
	 * 用户
	 */
	USER((short) 1),

	/**
	 * 商家
	 */
	STORE((short) 2),

	/**
	 * 管理员
	 */
	ADMIN((short) 3),

	/**
	 * 定时任务
	 */
	TIMER((short) 4);

	private short value;

	private CancelOrderSource(short value) {
		this.value = value;
	}

	public short getValue() {
		return value;
	}
}
