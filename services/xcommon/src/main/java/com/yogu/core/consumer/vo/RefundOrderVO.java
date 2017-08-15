package com.yogu.core.consumer.vo;

/**
 * 订单退款对账结果VO
 *
 * @date 2016年6月23日 下午2:40:36
 * @author hins
 */
public class RefundOrderVO {
	
	
	/**
	 * 门店id
	 */
	private long storeId;
	
	/**
	 * 支付类型，参考枚举PayType
	 */
	private short payType;
	
	/**
	 * 订单金额，单位分
	 */
	private long orderFee;
	
	/**
	 * 支付金额/应付金额，单位分
	 */
	private long payFee;
	
	/**
	 * 退款金额，单位分
	 */
	private long refundFee;
	
	/**
	 * 退款申请对象。1-用户申请退款；2-商家申请退款；3-管理员申请退款；4-定时任务申请退款
	 */
	private short refundType;

	/**
	 * 商家类型-“回滚”的优惠券金额，单位分
	 */
	private long useStoreCouponFee;
	
	/**
	 * 平台类型-“回滚”的优惠券金额，单位分
	 */
	private long usePlatformCouponFee;
	
	/**
	 * 优惠券流水-出账
	 */
	private long couponSubtractFee;
	
	/**
	 * 退款的出账流水，单位分
	 */
	private long subtractFee;
	
	/**
	 * 退款编号
	 */
	private long refundNo;
	
	/** 退款时，订单的状态 。
	 * 用来判断没接单时退款，不对账
	 */
	private short orderStatus;
	
	/**
	 * 是否是第三方配送，1：是，其他否
	 */
	private short isThirdExpress;
	
	/**
	 * 用户承担第三方运费
	 */
	private long userDeliveryFee;
	
	/**
	 * 用户配送费-入账（在交易中）
	 */
	private long userDeliveryPush;
	
	public long getStoreId() {
		return storeId;
	}

	public void setStoreId(long storeId) {
		this.storeId = storeId;
	}
	
	public short getPayType() {
		return payType;
	}

	public void setPayType(short payType) {
		this.payType = payType;
	}

	public long getOrderFee() {
		return orderFee;
	}

	public void setOrderFee(long orderFee) {
		this.orderFee = orderFee;
	}

	public long getPayFee() {
		return payFee;
	}

	public void setPayFee(long payFee) {
		this.payFee = payFee;
	}

	public long getRefundFee() {
		return refundFee;
	}

	public void setRefundFee(long refundFee) {
		this.refundFee = refundFee;
	}

	public short getRefundType() {
		return refundType;
	}

	public void setRefundType(short refundType) {
		this.refundType = refundType;
	}

	public long getUseStoreCouponFee() {
		return useStoreCouponFee;
	}

	public void setUseStoreCouponFee(long useStoreCouponFee) {
		this.useStoreCouponFee = useStoreCouponFee;
	}

	public long getUsePlatformCouponFee() {
		return usePlatformCouponFee;
	}

	public void setUsePlatformCouponFee(long usePlatformCouponFee) {
		this.usePlatformCouponFee = usePlatformCouponFee;
	}

	public long getCouponSubtractFee() {
		return couponSubtractFee;
	}

	public void setCouponSubtractFee(long couponSubtractFee) {
		this.couponSubtractFee = couponSubtractFee;
	}

	public long getSubtractFee() {
		return subtractFee;
	}

	public void setSubtractFee(long subtractFee) {
		this.subtractFee = subtractFee;
	}

	public long getRefundNo() {
		return refundNo;
	}

	public void setRefundNo(long refundNo) {
		this.refundNo = refundNo;
	}

	public short getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(short orderStatus) {
		this.orderStatus = orderStatus;
	}

	public long getUserDeliveryFee() {
		return userDeliveryFee;
	}

	public void setUserDeliveryFee(long userDeliveryFee) {
		this.userDeliveryFee = userDeliveryFee;
	}

	public short getIsThirdExpress() {
		return isThirdExpress;
	}

	public void setIsThirdExpress(short isThirdExpress) {
		this.isThirdExpress = isThirdExpress;
	}

	public long getUserDeliveryPush() {
		return userDeliveryPush;
	}

	public void setUserDeliveryPush(long userDeliveryPush) {
		this.userDeliveryPush = userDeliveryPush;
	}
	
}
