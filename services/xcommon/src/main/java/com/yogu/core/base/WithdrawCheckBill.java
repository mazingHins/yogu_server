package com.yogu.core.base;

/**
 * 提现检查清单
 * 
 * @author east
 * @date 2017年4月20日 下午5:16:40
 */
public class WithdrawCheckBill {

	/**
	 * 订单编号
	 */
	private long orderNo;

	/**
	 * 实付金额
	 */
	private long totalFee;

	/**
	 * 优惠券金额
	 */
	private long couponFee;

	/**
	 * 优惠券承担方
	 */
	private short couponBearType;

	/**
	 * 提现金额
	 */
	private long applyAmount;

	public long getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(long orderNo) {
		this.orderNo = orderNo;
	}

	public long getTotalFee() {
		return totalFee;
	}

	public void setTotalFee(long totalFee) {
		this.totalFee = totalFee;
	}

	public long getCouponFee() {
		return couponFee;
	}

	public void setCouponFee(long couponFee) {
		this.couponFee = couponFee;
	}

	public short getCouponBearType() {
		return couponBearType;
	}

	public void setCouponBearType(short couponBearType) {
		this.couponBearType = couponBearType;
	}

	public long getApplyAmount() {
		return applyAmount;
	}

	public void setApplyAmount(long applyAmount) {
		this.applyAmount = applyAmount;
	}

}
