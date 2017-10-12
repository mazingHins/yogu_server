package com.yogu.core.enums.order;

/**
 * 是否默认选中此优惠券枚举
 * 
 * @author Hins
 * @date 2015年12月23日 下午10:49:37
 */
public enum CouponSelect {
	/**
	 * 是
	 */
	YES((short) 1),

	/**
	 * 否
	 */
	NO((short) 2);

	private short value;

	private CouponSelect(short value) {
		this.value = value;
	}

	public short getValue() {
		return value;
	}
}
