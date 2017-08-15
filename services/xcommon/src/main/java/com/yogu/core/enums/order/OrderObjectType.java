package com.yogu.core.enums.order;

public enum OrderObjectType {

	CAN_XING_JIAN((short) 1);

	private short value;

	private OrderObjectType(short value) {
		this.value = value;
	}

	public short getValue() {
		return value;
	}
}
