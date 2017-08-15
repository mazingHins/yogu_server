package com.yogu.core.enums.pay;
/**
 *	提现类型 
 * @author east
 * @date 2017年4月19日 下午12:07:26
 */
public enum WithdrawType {
	/**
	 * T+N提现
	 */
	T_N((short)0),
	
	/**
	 * 实时到账
	 */
	REAL_TIME((short)1);

	private short value;

	private WithdrawType(short value) {
		this.value = value;
	}

	public short getValue() {
		return value;
	}
}
