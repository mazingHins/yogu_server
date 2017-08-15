package com.yogu.core.enums.merchant;

/**
 * 商家操作日志的操作类型
 * 
 * @author felix
 * @date 2015-11-06
 */
public enum MerchantLogType {
	
	/** 默认 */
	DEFAULT((short)0),
	
	/** 添加 */
	INSERT((short)1),
	
	/** 修改 */
	UPDATE((short)2),
	
	/** 删除 */
	DELETE((short)3);
	
	private short value;
	
	private MerchantLogType(short value){
		this.value = value;
	}
	
	public short getValue(){
		return this.value;
	}
}
