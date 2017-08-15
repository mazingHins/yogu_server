package com.yogu.core.enums.pay;
/**
 * 门店账户类型
 * 
 * @author Felix 2015-09-17
 */
public enum BalanceType {
	/** 可提现金额账户 */
	WITHDRAW_BALANCE((short)1),
	
	/** 待入账金额账户*/
	PENDING_SETTLED_BALANCE((short)2),
	
	/** 交易中金额账户*/
	DEALING_BALANCE((short)3),
	
	/** 积分账户*/
	POINTS_BALANCE((short)4);
	
	private short value;

	private BalanceType(short value) {
		this.value = value;
	}

	public short getValue() {
		return value;
	}
}
