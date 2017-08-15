package com.yogu.services.order.resource.param;

import javax.ws.rs.DefaultValue;
import javax.ws.rs.FormParam;

/**
 * 生成米星支付订单API接收参数
 * @date 2016年6月14日 上午11:28:21
 * @author hins
 */
public class CreateMazingPayParam {
	
	/**
	 * 支付方式，1-支付宝；2-微信
	 */
	@FormParam("payMode")
	@DefaultValue("0")
	private short payMode;

	/**
	 * 支付货币类型：1-人民币
	 */
	@FormParam("currencyType")
	@DefaultValue("0")
	private short currencyType;
	
	/**
	 * 餐厅id
	 */
	@FormParam("storeId")
	@DefaultValue("0")
	private long storeId;
	
	/**
	 * 支付金额，单位分
	 */
	@FormParam("payFee")
	@DefaultValue("0")
	private long payFee;
	
	/**
	 * 使用的优惠券id
	 */
	@FormParam("couponId")
	@DefaultValue("0")
	private long couponId;
	
	/**
	 * 下单时-桌号的备注
	 */
	@FormParam("userTableNoRemark")
	@DefaultValue("")
	private String userTableNoRemark;

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


}
