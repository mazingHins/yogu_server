package com.yogu.core.enums.merchant;

public enum BusinessStatus {

	/**
	 * 未开张
	 */
	NOT_OPEN((short) 1),

	/**
	 * 即将开张
	 */
	COMING_SOON((short) 2),

	/**
	 * 营业时间中
	 */
	IN_BUSINESS((short) 11),

	/**
	 * 营业时间外
	 */
	OUT_BUSINESS((short) 12),

	/**
	 * 休业
	 */
	IN_REST((short) 21),
	
	/**
	 * 结业
	 */
	CLOSED((short) 22);

	private short value;

	private BusinessStatus(short value) {
		this.value = value;
	}

	/**
	 * @return value
	 */
	public short getValue() {
		return value;
	}
}
