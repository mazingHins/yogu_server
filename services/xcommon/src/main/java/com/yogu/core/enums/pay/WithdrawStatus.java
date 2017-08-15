package com.yogu.core.enums.pay;

/** 
 * 提现状态
 * 
 * @author felix
 * */
public enum WithdrawStatus {
	
	/** 提现成功*/
	SUCCESS((short)2),
	
	/** 提现操作中*/
	IN_PROGRESS((short)1),
	
	/** 提现失败*/
	FAILED((short)0);
	
	private short value;
	
	private WithdrawStatus(short value) {
		this.value = value;
	}

	public short getValue() {
		return value;
	}
}
