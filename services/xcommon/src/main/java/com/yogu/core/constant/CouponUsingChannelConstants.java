package com.yogu.core.constant;

/**
 * 优惠券使用渠道 常量类
 * 
 * @author sky 2016-09-06
 *
 */
public class CouponUsingChannelConstants {

	/**
	 * 通用， 没有渠道限制
	 */
	public static final short NONE_LIMIT = 0;

	/**
	 * 线上订餐 使用
	 */
	public static final short ONLINE_ORDER = 1;
	/**
	 * Mazing Pay使用
	 */
	public static final short MAZING_PAY = 2;
	
	/**
	 * Mazing Pay使用
	 */
	public static final short TICKET_PAY = 4;

	/**
	 * 渠道是否满足Mazing Pay使用
	 * 
	 * @param usingChannel 使用渠道
	 * @return
	 */
	public static boolean satisfyMazingPay(short usingChannel) {
		// 通用 or Mazing Pay
		if (usingChannel != CouponUsingChannelConstants.NONE_LIMIT && usingChannel != CouponUsingChannelConstants.MAZING_PAY)
			return false;
		return true;
	}

	/**
	 * 渠道是否满足 线上订餐 使用
	 * 
	 * @param usingChannel 使用渠道
	 * @return
	 */
	public static boolean satisfyOnlineOrder(short usingChannel) {
		// 通用 or 线上支付
		if (usingChannel != CouponUsingChannelConstants.NONE_LIMIT && usingChannel != CouponUsingChannelConstants.ONLINE_ORDER)
			return false;
		return true;
	}
	
	/**
	 * 渠道是否满足购票使用
	 * @param usingChannel
	 * @return    
	 * @author east
	 * @date 2017年3月2日 下午3:01:47
	 */
	public static boolean satisfyTicketOrder(short usingChannel) {
		// 通用
		if (usingChannel == CouponUsingChannelConstants.NONE_LIMIT)
			return true;
		return false;
	}
}
