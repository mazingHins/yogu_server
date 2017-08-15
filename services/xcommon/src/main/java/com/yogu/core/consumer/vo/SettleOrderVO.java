package com.yogu.core.consumer.vo;

/**
 * T+N结算入账对账结果VO
 *
 * @date 2016年6月21日 下午4:26:16
 * @author hins
 */
public class SettleOrderVO {
	
	/**
	 * 门店id
	 */
	private long storeId;
	
	/**
	 * 支付编号
	 */
	private long payNo;
	
	/**
	 * 订单金额，单位分
	 */
	private long orderFee;
	
	/**
	 * 支付金额/应付金额，单位分
	 */
	private long payFee;
	
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
	 * 正常流水-出账
	 */
	private long subtractFee;
	
	/**
	 * 优惠券流水-入账
	 */
	private long couponPushFee;
	
	/**
	 * 优惠券流水-出账
	 */
	private long couponSubtractFee;
	
	/**
	 * 是否商家自行配送，1-是；其他否
	 */
	private short isStoreDelivery;
	
	/**
	 * 用户承担第三方运费
	 */
	private long userDeliveryFee;
	
	/**
	 * 商家承担第三方运费
	 */
	private long storeDeliveryFee;
	
	/**
	 * 用户配送费-出账
	 */
	private long userDeliverySubtractFee;
	
	/**
	 * 用户配送费-入账（一般是不存在的）
	 */
	private long userDeliveryPushFee;
	
	
	/**
	 * 商家配送费-出账
	 */
	private long storeDeliverySubtractFee;
	
	/**
	 * 商家配送费-入账（一般是不存在的）
	 */
	private long storeDeliveryPushFee;
	
	/**
	 * 手续费
	 */
	private long chargesFee;
	
	/**
	 * 补贴费
	 */
	private long subsidyFee;
	
	/**
	 * 线上订单的手续费（提现部分的手续费变成确认收货时候收取）
	 */
	private long orderChargesSubtractFee;
	
	/**
	 * 线上订单的补贴费（提现部分的手续费变成确认收货时候收取）
	 */
	private long orderSubsidyPlusFee;
	
	/**
	 * 米星付的手续费（提现部分的手续费变成确认收货时候收取）
	 */
	private long mazingPayChargesSubtractFee;
	
	/**
	 * 米星付的补贴费（提现部分的手续费变成确认收货时候收取）
	 */
	private long mazingPaySubsidyPlusFee;
	
	public long getOrderFee() {
		return orderFee;
	}

	public void setOrderFee(long orderFee) {
		this.orderFee = orderFee;
	}
	
	public long getPayNo() {
		return payNo;
	}

	public void setPayNo(long payNo) {
		this.payNo = payNo;
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

	public long getSubtractFee() {
		return subtractFee;
	}

	public void setSubtractFee(long subtractFee) {
		this.subtractFee = subtractFee;
	}

	public long getCouponPushFee() {
		return couponPushFee;
	}

	public void setCouponPushFee(long couponPushFee) {
		this.couponPushFee = couponPushFee;
	}

	public long getCouponSubtractFee() {
		return couponSubtractFee;
	}

	public void setCouponSubtractFee(long couponSubtractFee) {
		this.couponSubtractFee = couponSubtractFee;
	}

	public short getIsStoreDelivery() {
		return isStoreDelivery;
	}

	public void setIsStoreDelivery(short isStoreDelivery) {
		this.isStoreDelivery = isStoreDelivery;
	}

	public long getUserDeliveryFee() {
		return userDeliveryFee;
	}

	public void setUserDeliveryFee(long userDeliveryFee) {
		this.userDeliveryFee = userDeliveryFee;
	}

	public long getStoreDeliveryFee() {
		return storeDeliveryFee;
	}

	public void setStoreDeliveryFee(long storeDeliveryFee) {
		this.storeDeliveryFee = storeDeliveryFee;
	}

	public long getUserDeliverySubtractFee() {
		return userDeliverySubtractFee;
	}

	public void setUserDeliverySubtractFee(long userDeliverySubtractFee) {
		this.userDeliverySubtractFee = userDeliverySubtractFee;
	}

	public long getUserDeliveryPushFee() {
		return userDeliveryPushFee;
	}

	public void setUserDeliveryPushFee(long userDeliveryPushFee) {
		this.userDeliveryPushFee = userDeliveryPushFee;
	}

	public long getStoreDeliverySubtractFee() {
		return storeDeliverySubtractFee;
	}

	public void setStoreDeliverySubtractFee(long storeDeliverySubtractFee) {
		this.storeDeliverySubtractFee = storeDeliverySubtractFee;
	}

	public long getStoreDeliveryPushFee() {
		return storeDeliveryPushFee;
	}

	public void setStoreDeliveryPushFee(long storeDeliveryPushFee) {
		this.storeDeliveryPushFee = storeDeliveryPushFee;
	}

	public long getChargesFee() {
		return chargesFee;
	}

	public void setChargesFee(long chargesFee) {
		this.chargesFee = chargesFee;
	}

	public long getSubsidyFee() {
		return subsidyFee;
	}

	public void setSubsidyFee(long subsidyFee) {
		this.subsidyFee = subsidyFee;
	}

	public long getOrderChargesSubtractFee() {
		return orderChargesSubtractFee;
	}

	public void setOrderChargesSubtractFee(long orderChargesSubtractFee) {
		this.orderChargesSubtractFee = orderChargesSubtractFee;
	}

	public long getOrderSubsidyPlusFee() {
		return orderSubsidyPlusFee;
	}

	public void setOrderSubsidyPlusFee(long orderSubsidyPlusFee) {
		this.orderSubsidyPlusFee = orderSubsidyPlusFee;
	}

	public long getMazingPayChargesSubtractFee() {
		return mazingPayChargesSubtractFee;
	}

	public void setMazingPayChargesSubtractFee(long mazingPayChargesSubtractFee) {
		this.mazingPayChargesSubtractFee = mazingPayChargesSubtractFee;
	}

	public long getMazingPaySubsidyPlusFee() {
		return mazingPaySubsidyPlusFee;
	}

	public void setMazingPaySubsidyPlusFee(long mazingPaySubsidyPlusFee) {
		this.mazingPaySubsidyPlusFee = mazingPaySubsidyPlusFee;
	}
	
}
