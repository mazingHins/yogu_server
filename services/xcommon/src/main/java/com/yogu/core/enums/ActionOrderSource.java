package com.yogu.core.enums;

/**
 * 取消订单的源头
 * 用户, 商家, 定时任务, 管理员
 * @author felix
 */
public enum ActionOrderSource {
	/** 用户 */
	USER((short)1),
	
	/** 商家 */
	STORE((short)2),
	
	/** 定时任务 */
	TIMER((short)3),
	
	/** 管理员 */
	ADMIN((short)4);
	
	private short value;
	
	private ActionOrderSource(short value) {
		this.value = value;
	}
	
	public short getValue() {
		return this.value;
	}
}
