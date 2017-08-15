package com.yogu.core.consumer.vo;

/**
 * 商家接单对账结果VO
 *
 * @date 2016年6月20日 下午4:41:01
 * @author hins
 */
public class StoreAcceptOrderVO {
	
	/**
	 * 门店id
	 */
	private long storeId;
	
	/**
	 * 订单金额，单位分
	 */
	private long orderFee;
	
	/**
	 * 支付金额/应付金额，单位分
	 */
	private long payFee;
	
	/**
	 * 支付类型（因为非线上支付的订单，不需要对账）
	 */
	private short payType;
	
	/**
	 * 商家类型-“使用中”的优惠券金额，单位分
	 */
	private long useStoreCouponFee;
	
	/**
	 * 平台类型-“使用中”的优惠券金额，单位分
	 */
	private long usePlatformCouponFee;
	
	/**
	 * 正常流水-入账
	 */
	private long pushFee;
	
	/**
	 * 优惠券流水-入账
	 */
	private long couponPushFee;

	/**
	 * 支付编号
	 */
	private long payNo;
	
	/**
	 * 用户承担第三方运费
	 */
	private long userDeliveryFee;
	
	/**
	 * 用户配送费-出账
	 */
	private long userDeliverySubtractFee;
	
	/**
	 * 是否使用了优惠券，1：是，其他否
	 */
	private short useCoupon;
	
	/**
	 * 是否是第三方配送，1：是，其他否
	 */
	private short isThirdExpress;
	
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

	public long getStoreId() {
		return storeId;
	}

	public void setStoreId(long storeId) {
		this.storeId = storeId;
	}

	public long getPushFee() {
		return pushFee;
	}

	public void setPushFee(long pushFee) {
		this.pushFee = pushFee;
	}

	public long getCouponPushFee() {
		return couponPushFee;
	}

	public void setCouponPushFee(long couponPushFee) {
		this.couponPushFee = couponPushFee;
	}

	public short getPayType() {
		return payType;
	}

	public void setPayType(short payType) {
		this.payType = payType;
	}

	public long getPayNo() {
		return payNo;
	}

	public void setPayNo(long payNo) {
		this.payNo = payNo;
	}

	public long getUserDeliveryFee() {
		return userDeliveryFee;
	}

	public void setUserDeliveryFee(long userDeliveryFee) {
		this.userDeliveryFee = userDeliveryFee;
	}

	public long getUserDeliverySubtractFee() {
		return userDeliverySubtractFee;
	}

	public void setUserDeliverySubtractFee(long userDeliverySubtractFee) {
		this.userDeliverySubtractFee = userDeliverySubtractFee;
	}

	public short getUseCoupon() {
		return useCoupon;
	}

	public void setUseCoupon(short useCoupon) {
		this.useCoupon = useCoupon;
	}

	public short getIsThirdExpress() {
		return isThirdExpress;
	}

	public void setIsThirdExpress(short isThirdExpress) {
		this.isThirdExpress = isThirdExpress;
	}

}
