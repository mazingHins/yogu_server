package com.yogu.core.enums;

public enum CouponStatus {

	/**
	 * 还没被领取
	 */
	UNCLAIMED((short) 0),

	/**
	 * 未使用
	 */
	UNUSE((short) 1),

	/**
	 * 使用中
	 */
	USING((short) 2),

	/**
	 * 已使用
	 */
	USED((short) 3),

	/**
	 * 已失效
	 */
	INVALID((short) 4),

	/**
	 * 已删除
	 */
	DELETED((short) 5),
	
	/**
	 * 没满足可使用的条件，该值不参与数据库的修改（入绑定优惠券/使用优惠券等），只用于确认号码等是否满足条件
	 */
	DID_NOT_MEET_PHONE((short) 101),
	
	/**
	 * 没满足可使用的条件，该值不参与数据库的修改（入绑定优惠券/使用优惠券等），只用于确认最低消费金额等是否满足条件
	 */
	DID_NOT_MEET_MONEY((short) 102);
	

	private short value;

	private CouponStatus(short value) {
		this.value = value;
	}

	/**
	 * @return value
	 */
	public short getValue() {
		return value;
	}
	
	public static CouponStatus from(short status) {
		for (CouponStatus cs : CouponStatus.values()) {
			if (cs.getValue() == status) {
				return cs;
			}
		}
		return null;
	}
}
