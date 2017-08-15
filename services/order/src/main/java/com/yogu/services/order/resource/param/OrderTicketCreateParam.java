package com.yogu.services.order.resource.param;

import javax.ws.rs.DefaultValue;
import javax.ws.rs.FormParam;

import com.yogu.commons.validation.constraints.NotBlank;

/**
 * 购票预订单创建订单API接收参数
 * 
 * @author east
 * @date 2017年2月27日 下午2:56:43
 */
public class OrderTicketCreateParam {

	/**
	 * 活动id
	 */
	@FormParam("eventId")
	private long eventId;

	/**
	 * 购买信息载体
	 */
	@FormParam("tickInfo")
	private String tickInfo;// 购票信息集合

	/**
	 * 手机号码
	 */
	@FormParam("mobile")
	@NotBlank(message = "手机号码不能为空")
	private String mobile;

	/**
	 * 真实姓名
	 */
	@FormParam("realName")
	@NotBlank(message = "真实姓名不能为空")
	private String realName;

	/**
	 * 优惠券id
	 */
	@FormParam("couponId")
	private long couponId;

	public long getEventId() {
		return eventId;
	}

	public void setEventId(long eventId) {
		this.eventId = eventId;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public long getCouponId() {
		return couponId;
	}

	public void setCouponId(long couponId) {
		this.couponId = couponId;
	}

	public String getTickInfo() {
		return tickInfo;
	}

	public void setTickInfo(String tickInfo) {
		this.tickInfo = tickInfo;
	}

}
