package com.yogu.core.enums;

/**
 * 站内信消息类型的定义
 * 
 * @author felix
 * @date 2015-12-16
 */
public enum InternalMessageType {
	/** 普通用户 */
	NORMAL_USER((short)1),
	
	/** 商家用户 */
	STORE_USER((short)2);
	
	private short value;
	
	private InternalMessageType(short value){
		this.value = value;
	}
	
	public short getValue(){
		return this.value;
	}
}
