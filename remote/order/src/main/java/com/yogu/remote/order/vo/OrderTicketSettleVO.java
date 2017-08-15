package com.yogu.remote.order.vo;

import java.util.List;

public class OrderTicketSettleVO {

	/** 活动id */
	private long eventId;

	/** 活动名称 */
	private String eventName;

	private String startDay;

	private String endDay;

	private String openHours;

	/** 活动地址 */
	private String address;

	/** 总金额 */
	private long totalFee;

	/**
	 * 手机号码
	 */
	private String mobile;

	/**
	 * 真实姓名
	 */
	private String realName;

	/** 优惠总价格 */
	private long totalDiscountFee;


	/**
	 * 客户端可用优惠券button标题
	 */
	private String optionalCouponTitle;

	/**
	 * 预下单购票信息
	 */
	private List<SettleTicketVO> ticketList;

	public long getEventId() {
		return eventId;
	}

	public void setEventId(long eventId) {
		this.eventId = eventId;
	}

	public String getEventName() {
		return eventName;
	}

	public void setEventName(String eventName) {
		this.eventName = eventName;
	}

	public String getStartDay() {
		return startDay;
	}

	public void setStartDay(String startDay) {
		this.startDay = startDay;
	}

	public String getEndDay() {
		return endDay;
	}

	public void setEndDay(String endDay) {
		this.endDay = endDay;
	}

	public String getOpenHours() {
		return openHours;
	}

	public void setOpenHours(String openHours) {
		this.openHours = openHours;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public long getTotalFee() {
		return totalFee;
	}

	public void setTotalFee(long totalFee) {
		this.totalFee = totalFee;
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

	public long getTotalDiscountFee() {
		return totalDiscountFee;
	}

	public void setTotalDiscountFee(long totalDiscountFee) {
		this.totalDiscountFee = totalDiscountFee;
	}

	public String getOptionalCouponTitle() {
		return optionalCouponTitle;
	}

	public void setOptionalCouponTitle(String optionalCouponTitle) {
		this.optionalCouponTitle = optionalCouponTitle;
	}

	public List<SettleTicketVO> getTicketList() {
		return ticketList;
	}

	public void setTicketList(List<SettleTicketVO> ticketList) {
		this.ticketList = ticketList;
	}
}
