package com.yogu.core.enums.merchant;

/**
 * 订单 配送方式 枚举
 * @author sky
 *
 */
public enum OrderPickType {

	/**
	 * 商家配送
	 */
	MERCHANT_DELIVERY((short) 1)
	/**
	 * 用户自提
	 */
	, SELF_PICK((short) 2)
	/**
	 * 快递
	 */
	, EXPRESS_DELIVERY((short) 3);

	private short value;

	private OrderPickType(short value) {
		this.value = value;
	}

	/**
	 * @return value
	 */
	public short getValue() {
		return value;
	}

	public static OrderPickType valueOf(short value) {
		switch (value) {
		case 1:
			return MERCHANT_DELIVERY;
		case 2:
			return SELF_PICK;
		case 3:
			return EXPRESS_DELIVERY;

		default:
			return null;
		}
	}
}
