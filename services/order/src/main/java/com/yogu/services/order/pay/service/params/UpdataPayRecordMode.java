package com.yogu.services.order.pay.service.params;

/**
 * 更换支付记录dao的请求参数封装类<br>
 * 更换支付方式,是否使用优惠券，实际支付金额，用户支付第三方配送费
 *
 * @date 2016年10月10日 下午2:14:41
 * @author hins
 */
public class UpdataPayRecordMode {
	
	/**
	 * 支付记录id
	 */
	private long payId;
	
	/**
	 * 支付方式（旧值，查询条件）
	 */
	private short oldPayMode;
	
	/**
	 * 支付方式（修改值）
	 */
	private short newPayMode;
	
	/**
	 * 是否使用了优惠券
	 */
	private short useCoupon;
	
	/**
	 * 实际支付金额，单位分
	 */
	private int totalFee;
	
	/**
	 * 支付状态
	 */
	private short payStatus;
	
	/**
	 * 订单金额（应付金额），单位分
	 */
	private long orderFee;
	
	/**
	 * 优惠券金额，单位分
	 */
	private long couponFee;

	/**
	 * 商品总价，单位分
	 */
	private long goodsFee;
	
	public long getPayId() {
		return payId;
	}

	public void setPayId(long payId) {
		this.payId = payId;
	}

	public short getOldPayMode() {
		return oldPayMode;
	}

	public void setOldPayMode(short oldPayMode) {
		this.oldPayMode = oldPayMode;
	}

	public short getNewPayMode() {
		return newPayMode;
	}

	public void setNewPayMode(short newPayMode) {
		this.newPayMode = newPayMode;
	}
	
	public short getUseCoupon() {
		return useCoupon;
	}

	public void setUseCoupon(short useCoupon) {
		this.useCoupon = useCoupon;
	}

	public int getTotalFee() {
		return totalFee;
	}

	public void setTotalFee(int totalFee) {
		this.totalFee = totalFee;
	}

	public short getPayStatus() {
		return payStatus;
	}

	public void setPayStatus(short payStatus) {
		this.payStatus = payStatus;
	}

	public long getOrderFee() {
		return orderFee;
	}

	public void setOrderFee(long orderFee) {
		this.orderFee = orderFee;
	}

	public long getCouponFee() {
		return couponFee;
	}

	public void setCouponFee(long couponFee) {
		this.couponFee = couponFee;
	}

	public long getGoodsFee() {
		return goodsFee;
	}

	public void setGoodsFee(long goodsFee) {
		this.goodsFee = goodsFee;
	}

}
