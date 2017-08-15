package com.yogu.services.order.resource.param;

import javax.ws.rs.DefaultValue;
import javax.ws.rs.FormParam;

import com.yogu.commons.validation.constraints.NotBlank;

/**
 * 生成订单并返回支付sdk信息的API接收参数
 * 使用场景: 微信的h5购买页面
 * 
 * @author felix 2016-04-12
 */
public class GetPayParam {
	
	@FormParam("couponId")
	@DefaultValue("0")
	private long couponId;
	
	@FormParam("orderNo")
	private long orderNo;
	
	@FormParam("payType") 
	@DefaultValue("1") 
	private short payType;
	
	@FormParam("payMode")
	@DefaultValue("1")
	private short payMode;

	@FormParam("uid")
	private long uid;

	@FormParam("userIp")
	@NotBlank(message = "用户终端IP不能为空")
	private String userIp;

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

	public long getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(long orderNo) {
		this.orderNo = orderNo;
	}

	public long getCouponId() {
		return couponId;
	}

	public void setCouponId(long couponId) {
		this.couponId = couponId;
	}
	
	public long getUid() {
		return uid;
	}

	public void setUid(long uid) {
		this.uid = uid;
	}
	
	public String getUserIp() {
		return userIp;
	}

	public void setUserIp(String userIp) {
		this.userIp = userIp;
	}
}
