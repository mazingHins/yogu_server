package com.yogu.services.order.constants;

/**
 * 订单优惠券使用状态值定义类
 * 
 * @author Hins
 * @date 2015年12月23日 下午7:24:58
 */
public class OrderCouponRecordStatus {

	/**
	 * 使用中，处于锁定状态
	 */
	public static final short LOCK = 1;

	/**
	 * 已使用
	 */
	public static final short USED = 2;

	/**
	 * 使用失败，被回滚
	 */
	public static final short FAIL_ROLLBACK = 3;

}
