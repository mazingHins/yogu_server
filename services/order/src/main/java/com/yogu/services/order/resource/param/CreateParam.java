package com.yogu.services.order.resource.param;

import javax.ws.rs.DefaultValue;
import javax.ws.rs.FormParam;

/**
 * 生成订单API接收参数
 * @author Hins
 * @date 2015年7月31日 下午3:29:03
 */
public class CreateParam {
	
	/**
	 * 支付方式，1-支付宝；2-微信
	 */
	@FormParam("payMode")
	@DefaultValue("0")
	private short payMode;

	/**
	 * 寄送地址
	 */
	@FormParam("addressId")
	private long addressId;

	/**
	 * 购买详情，包含规格的内容
	 * 2016/2/23 by ten
	 */
	@FormParam("purchaseDetail")
	private String purchaseDetail;
	
	/**
	 * 优惠券id，0表示没使用
	 */
	@FormParam("couponId")
	private long couponId;
	
	/**
	 * 订单备注
	 */
	@FormParam("remark")
	@DefaultValue("")
	private String remark;

	// end
	
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

	public String getPurchaseDetail() {
		return purchaseDetail;
	}

	public void setPurchaseDetail(String purchaseDetail) {
		this.purchaseDetail = purchaseDetail;
	}

	public long getCouponId() {
		return couponId;
	}

	public void setCouponId(long couponId) {
		this.couponId = couponId;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	
}
