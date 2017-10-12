package com.yogu.core.constant;

/**
 * 优惠券显示状态枚举
 * 
 * @author Hins
 * @date 2015年12月24日 下午3:05:23
 */
public enum CouponShowStatus {

	/**
	 * 可使用
	 */
	UNUSED((short) 1),

	/**
	 * 已使用
	 */
	HAS_USED((short) 2),

	/**
	 * 已过期
	 */
	HAS_OVERDUE((short) 3),

	/**
	 * 已失效
	 */
	INVALID((short) 4),

	/**
	 * 可使用
	 */
	CAN_USE((short) 10),

	/**
	 * 没满足可使用的条件（收货号码）
	 */
	DID_NOT_MEET_PHONE((short) 11),

	/**
	 * 没满足可使用的条件(最低消费金额)
	 */
	DID_NOT_MEET_MONEY((short) 12);

	private short value;

	private CouponShowStatus(short value) {
		this.value = value;
	}

	public short getValue() {
		return value;
	}

}
