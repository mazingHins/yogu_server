package com.yogu.core.enums;
/**
 * 发送通知的对象, 在MQ场景会用到
 * @author felix
 */
public enum NotifyTarget {
	USER((short)1),
	
	STORE((short)2);
	
	private short value;
	
	private NotifyTarget(short value) {
		this.value = value;
	}
	
	public short getValue() {
		return this.value;
	}
}
