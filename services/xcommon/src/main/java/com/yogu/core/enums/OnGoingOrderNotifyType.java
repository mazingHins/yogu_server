package com.yogu.core.enums;

/**
 * 进行中订单的通知类型
 * @author felix
 */
public enum OnGoingOrderNotifyType {
	/** 进行到一半 (订单开始时间 ~ 预计送达时间) */
	MID_OF_PROGRESS((short)1),
	
	/** 刚好到预计送达时间 */
	JUST_HIT_DELIVERY_TIME((short)2),
	
	/** 刚好到应该开始配送的时间 */
	SHOULD_HAS_ORDER_DELIVERED((short)3);
	
	private short value;

	private OnGoingOrderNotifyType(short value) {
		this.value = value;
	}

	public short getValue() {
		return this.value;
	}
}
