package com.yogu.core.enums;

/**
 * 价格格式化类型
 * 
 * @author felix
 */
public enum PriceFormatType {
	/** 输出一个范围 */
	RANGE((short)1),
	
	/** 输出最低价+一个'起' */
	SINGLE((short)2);
	
	private short value;
	
	private PriceFormatType(short value) {
		this.value = value;
	}
	
	public short getValue() {
		return this.value;
	}
}
