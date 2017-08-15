package com.yogu.core.enums.pay;

/**
 * 第三方配送账户类型
 *
 * @date 2016年10月12日 下午12:34:13
 * @author hins
 */
public enum ExpressBalanceType {

	/** 交易中金额账户*/
	DEALING_BALANCE((short)1),
	
	/** 可结算金额账户*/
	SETTLED_BALANCE((short)2);
	
	private short value;

	private ExpressBalanceType(short value) {
		this.value = value;
	}

	public short getValue() {
		return value;
	}
}
