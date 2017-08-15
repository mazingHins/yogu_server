package com.yogu.core.enums.merchant;

/**
 * 配送范围：配送费类型枚举定义
 * 
 * @author Hins
 * @date 2015年10月12日 上午10:17:06
 */
public enum RangeMoneyType {

	/**
	 * 免费
	 */
	FREE((short) 1),

	/**
	 * 付费
	 */
	PAY((short) 2),

	/**
	 * 的士费*1（单程）
	 */
	TAXI_FEE((short) 3),

	/**
	 * 的士费*2（往返）
	 */
	TAXI_FEE_DOUBLE((short) 4);
	
	private short value;

	private RangeMoneyType(short value) {
		this.value = value;
	}

	public short getValue() {
		return value;
	}

}
