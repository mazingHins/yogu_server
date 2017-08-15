package com.yogu.core.constant;

public class CouponUseStatus {
	/**
	 * 使用成功
	 */
	public static final short SUCCESS = 1;

	/**
	 * 使用中
	 */
	public static final short USING = 2;

	/**
	 * 使用失败,表示回滚
	 */
	public static final short ROLLBACK = 3;
}
