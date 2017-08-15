package com.yogu.services.order.base.service.param;

/**
 * 更改支付方式service方法的请求参数
 * 
 * @author Hins
 * @date 2015年12月25日 下午2:49:59
 */
public class ChangePayModeParam {

	/**
	 * 订单编号
	 */
	private long orderNo;

	/**
	 * 购买人ID
	 */
	private long uid;

	/**
	 * 支付类型：1-线上支付；2-货到付款
	 */
	private short payType;

	/**
	 * 线上支付方式：1-支付宝；2-微信
	 */
	private short payMode;

	/**
	 * 用户下单所在IP地址
	 */
	private String userIp;

	/**
	 * 使用的优惠券ID
	 */
	private long couponId;

	/**
	 * 服务商类型
	 */
	private short merchantType;

	public long getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(long orderNo) {
		this.orderNo = orderNo;
	}

	public long getUid() {
		return uid;
	}

	public void setUid(long uid) {
		this.uid = uid;
	}

	public short getPayType() {
		return payType;
	}

	public void setPayType(short payType) {
		this.payType = payType;
	}

	public short getPayMode() {
		return payMode;
	}

	public void setPayMode(short payMode) {
		this.payMode = payMode;
	}

	public String getUserIp() {
		return userIp;
	}

	public void setUserIp(String userIp) {
		this.userIp = userIp;
	}

	public long getCouponId() {
		return couponId;
	}

	public void setCouponId(long couponId) {
		this.couponId = couponId;
	}

	public short getMerchantType() {
		return merchantType;
	}

	public void setMerchantType(short merchantType) {
		this.merchantType = merchantType;
	}

}
