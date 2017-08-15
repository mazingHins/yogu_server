package com.yogu.core.enums;

/**
 * 站内信状态
 * @author felix
 *
 */
public enum InternalMessageStatus {
	/** 已读 */
	READ((short)2),
	
	/** 未读 */
	UN_READ((short)1);
	
	private short value;
	
	private InternalMessageStatus(short value){
		this.value = value;
	}
	
	public short getValue(){
		return this.value;
	}
}
