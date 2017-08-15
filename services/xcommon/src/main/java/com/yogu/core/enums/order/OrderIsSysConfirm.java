package com.yogu.core.enums.order;

/**
 * 定义: 订单是否被定时任务自动确认收货
 */
public enum OrderIsSysConfirm {
	YES((short) 2),

	NO((short) 1);

	private short value;

	private OrderIsSysConfirm(short value) {
		this.value = value;
	}

	public short getValue() {
		return this.value;
	}
}
