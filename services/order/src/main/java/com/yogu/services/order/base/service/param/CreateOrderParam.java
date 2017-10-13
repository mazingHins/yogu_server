package com.yogu.services.order.base.service.param;

import java.io.Serializable;
import java.util.List;

/**
 * 创建订单service方法的参数
 * @date 2016年6月14日 上午11:57:10
 * @author hins
 */
public class CreateOrderParam implements Serializable {
	
	private static final long serialVersionUID = -7568905062634614378L;

	/**
	 * 终端IP
	 */
	private String userIp;

	/**
	 * 支付类型，1-线上支付；2-货到付款
	 */
	private short payType;
	
	/**
	 * 支付方式，1-支付宝；2-微信
	 */
	private short payMode;
	
	/**
	 * 寄送地址
	 */
	private long addressId;
	
	/**
	 * 订单备注
	 */
	private String remark;
	
	/**
	 * 购买物品的详情，包括规格
	 * 2016/2/23 by ten
	 */
	private List<PurchaseDetail> purchaseDetails;
	
	/**
	 * 优惠券id，0表示不使用
	 */
	private long couponId;
	
	public short getPayType() {
		return payType;
	}

	public void setPayType(short payType) {
		this.payType = payType;
	}

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

	public long getAddressId() {
		return addressId;
	}

	public void setAddressId(long addressId) {
		this.addressId = addressId;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	public List<PurchaseDetail> getPurchaseDetails() {
		return purchaseDetails;
	}

	public void setPurchaseDetails(List<PurchaseDetail> purchaseDetails) {
		this.purchaseDetails = purchaseDetails;
	}

	public long getCouponId() {
		return couponId;
	}

	public void setCouponId(long couponId) {
		this.couponId = couponId;
	}
	
}
