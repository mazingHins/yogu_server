package com.yogu.services.order.utils;

/**
 * 优惠券的一些常量
 * 
 * @author ten 2016/1/11.
 */
public class CouponConstants {

	/**
	 * 分享优惠券（sign方式为运算）
	 */
	public static final String FUNC_SHARE = "couponShare";

	/**
	 * 分享卡包（sign方式为运算）
	 */
	public static final String FUNC_BAG_SHARE = "couponBagShare";

	/**
	 * 转让的功能（sign方式为随机串，存储到redis中）
	 */
	public static final String FUNC_TRANSFER = "couponTransfer";

}
