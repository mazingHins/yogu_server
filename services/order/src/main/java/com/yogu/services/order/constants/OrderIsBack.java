package com.yogu.services.order.constants;

/**
 * 定义订单是否被退回的
 * 
 * @author felix
 *
 */
public enum OrderIsBack {
	YES((short) 1),

	NO((short) 0);

	private short value;

	private OrderIsBack(short value) {
		this.value = value;
	}

	public short getValue() {
		return this.value;
	}

	public OrderIsBack valueOf(short value) {
		switch (value) {
		case 1:
			return YES;
		default:
			return NO;
		}
	}
}
