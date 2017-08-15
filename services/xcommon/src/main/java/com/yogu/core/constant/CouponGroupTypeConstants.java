package com.yogu.core.constant;

/**
 * 优惠券 范围群组类型 常量定义
 * 
 * @author sky 2015/12/23
 * 
 *
 */
public class CouponGroupTypeConstants {

	// 1：餐厅 2：美食 3：用户   0：没有范围限制
	/**
	 * 不受限制
	 */
	public static final short UNLIMITED = 0;

	/**
	 * 限制在餐厅 范围
	 */
	public static final short STORE = 1;
	/**
	 * 限制在美食范围
	 */
	public static final short DISH = 2;

	/**
	 * 限制在用户范围
	 */
	public static final short USER = 3;
}
