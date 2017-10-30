package com.yogu.services.order.pay.service.params;

public class PayReqParams {
	

	/**
	 * 内部平台订单号
	 */
	private long orderNo;

	/**
	 * 支付费用(单位分)
	 */
	private long totalFee;

	/**
	 * 支付方式：1-支付宝；2-微信
	 */
	private short payMode;

	/**
	 * 支付货币：1-人民币
	 */
	private short currencyType;

	/**
	 * 购买用户ID
	 */
	private long buyerUid;

	/**
	 * 餐厅店主ID
	 */
	private long storeUid;

	/**
	 * 餐厅ID
	 */
	private long sellerUid;

	/**
	 * 终端IP
	 */
	private String userIp;

	/**
	 * 商品名称
	 */
	private String subject;

	/**
	 * 商品详情
	 */
	private String body;

	/**
	 * 使用优惠券优惠金额
	 */
	private long couponFee;

	/**
	 * 客户端版本，取值参照IosTargetConstants
	 */
	private String target;

	/**
	 * 操作系统:取值参照PushUtil.SysType
	 */
	private short sysType;

	/**
	 * 商家价格，单位分
	 */
	private long goodsFee;

	/**
	 * 订单总价，单位分
	 */
	private long orderFee;

	public long getTotalFee() {
		return totalFee;
	}

	public void setTotalFee(long totalFee) {
		this.totalFee = totalFee;
	}

	public short getPayMode() {
		return payMode;
	}

	public void setPayMode(short payMode) {
		this.payMode = payMode;
	}

	public short getCurrencyType() {
		return currencyType;
	}

	public void setCurrencyType(short currencyType) {
		this.currencyType = currencyType;
	}

	public long getBuyerUid() {
		return buyerUid;
	}

	public void setBuyerUid(long buyerUid) {
		this.buyerUid = buyerUid;
	}

	public long getSellerUid() {
		return sellerUid;
	}

	public void setSellerUid(long sellerUid) {
		this.sellerUid = sellerUid;
	}

	public String getUserIp() {
		return userIp;
	}

	public void setUserIp(String userIp) {
		this.userIp = userIp;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public long getCouponFee() {
		return couponFee;
	}

	public void setCouponFee(long couponFee) {
		this.couponFee = couponFee;
	}

	public String getTarget() {
		return target;
	}

	public void setTarget(String target) {
		this.target = target;
	}

	public short getSysType() {
		return sysType;
	}

	public void setSysType(short sysType) {
		this.sysType = sysType;
	}

	public long getGoodsFee() {
		return goodsFee;
	}

	public void setGoodsFee(long goodsFee) {
		this.goodsFee = goodsFee;
	}

	public long getOrderFee() {
		return orderFee;
	}

	public void setOrderFee(long orderFee) {
		this.orderFee = orderFee;
	}

	public long getStoreUid() {
		return storeUid;
	}

	public void setStoreUid(long storeUid) {
		this.storeUid = storeUid;
	}

	public long getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(long orderNo) {
		this.orderNo = orderNo;
	}


}
