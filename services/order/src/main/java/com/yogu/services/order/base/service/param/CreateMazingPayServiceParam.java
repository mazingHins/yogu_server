package com.yogu.services.order.base.service.param;

import java.io.Serializable;

/**
 * 创建米星支付订单service的参数
 * 
 * @date 2016年6月14日 上午11:57:32
 * @author hins
 */
public class CreateMazingPayServiceParam implements Serializable {

	private static final long serialVersionUID = -1261378271859965610L;

	/**
	 * 终端IP
	 */
	private String userIp;

	/**
	 * 支付方式，1-支付宝；2-微信
	 */
	private short payMode;

	/**
	 * 支付货币类型：1-人民币
	 */
	private short currencyType;

	/**
	 * 餐厅id
	 */
	private long storeId;

	/**
	 * 支付金额，单位分
	 */
	private long payFee;

	/**
	 * 使用的优惠券id
	 */
	private long couponId;

	/**
	 * 下单时-桌号的备注
	 */
	private String userTableNoRemark;

	private String objectId;

	private String returnUrl;

	/**
	 * 发票抬头
	 */
	private String invoiceTitle;

	/**
	 * 服务商类型
	 */
	private short merchantType;

	public String getUserIp() {
		return userIp;
	}

	public void setUserIp(String userIp) {
		this.userIp = userIp;
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

	public long getStoreId() {
		return storeId;
	}

	public void setStoreId(long storeId) {
		this.storeId = storeId;
	}

	public long getPayFee() {
		return payFee;
	}

	public void setPayFee(long payFee) {
		this.payFee = payFee;
	}

	public long getCouponId() {
		return couponId;
	}

	public void setCouponId(long couponId) {
		this.couponId = couponId;
	}

	public String getUserTableNoRemark() {
		return userTableNoRemark;
	}

	public void setUserTableNoRemark(String userTableNoRemark) {
		this.userTableNoRemark = userTableNoRemark;
	}

	public String getObjectId() {
		return objectId;
	}

	public void setObjectId(String objectId) {
		this.objectId = objectId;
	}

	public String getReturnUrl() {
		return returnUrl;
	}

	public void setReturnUrl(String returnUrl) {
		this.returnUrl = returnUrl;
	}

	public String getInvoiceTitle() {
		return invoiceTitle;
	}

	public void setInvoiceTitle(String invoiceTitle) {
		this.invoiceTitle = invoiceTitle;
	}

	public short getMerchantType() {
		return merchantType;
	}

	public void setMerchantType(short merchantType) {
		this.merchantType = merchantType;
	}

}
