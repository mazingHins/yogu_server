package com.yogu.core.enums.pay;

/**
 * 定义提现批次的状态
 * 
 * @author felix
 * */
public enum WithdrawBatchStatus {
	/** 提现关闭 */
	CLOSED((short)3),
	
	/** 提现成功 */
	SUCCESS((short)2),
	
	/** 提现操作中 */
	IN_PROGRESS((short)1),
	
	/** 提现失败 */
	FAILED((short)0);
	
	private short value;
	
	private WithdrawBatchStatus(short value) {
		this.value = value;
	}

	public short getValue() {
		return value;
	}
}
