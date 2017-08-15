package com.yogu.services.order.resource.param;

import javax.ws.rs.DefaultValue;
import javax.ws.rs.FormParam;

/**
 * 生成订单并返回支付sdk信息的API接收参数 使用场景: 微信的h5购买页面
 * 
 * @author east
 * @date 2017年3月14日 下午7:15:12
 */
public class TicketPayParam {

	@FormParam("couponId")
	@DefaultValue("0")
	private long couponId;

	@FormParam("orderNo")
	private long orderNo;

	@FormParam("ip")
	private String ip;

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

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

}
