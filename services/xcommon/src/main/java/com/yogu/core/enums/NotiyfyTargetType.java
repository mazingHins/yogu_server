package com.yogu.core.enums;

/**
 * 通知的对象类型
 * 
 * @author felix
 */
public enum NotiyfyTargetType {
	USER((short) 1),

	STORE((short) 2),

	CONSULTANT((short) 3);

	private short value;

	private NotiyfyTargetType(short value) {
		this.value = value;
	}

	public short getValue() {
		return this.value;
	}
}
