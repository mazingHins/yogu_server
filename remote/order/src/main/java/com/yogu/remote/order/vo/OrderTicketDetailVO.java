package com.yogu.remote.order.vo;

import java.util.List;

public class OrderTicketDetailVO {

	/** 活动id */
	private long eventId;

	/** 活动名称 */
	private String eventName;

	private String startDay;

	private String endDay;

	private String openHours;

	/** 活动地址 */
	private String address;

	private List<TicketDetailVO> ticketList;

	// 购票权益
	private String rights;

	// 活动须知
	private String notice;

	// 活动介绍
	private String content;

	private List<OrderDetail> orderDetailList;

	// 订单编号
	private long orderNo;

	// 总金额
	private long totalFee;

	// 已付金额
	private long actualFee;

	// 优惠金额
	private long totalDiscountFee;

	// 下单人手机
	private String mobile;

	private String createTime;

	/** 资讯电话(联系商家) */
	private String consultPhone;

	// 售后电话
	private String aftersalePhone;

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

	public List<TicketDetailVO> getTicketList() {
		return ticketList;
	}

	public void setTicketList(List<TicketDetailVO> ticketList) {
		this.ticketList = ticketList;
	}

	public String getRights() {
		return rights;
	}

	public void setRights(String rights) {
		this.rights = rights;
	}

	public String getNotice() {
		return notice;
	}

	public void setNotice(String notice) {
		this.notice = notice;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public List<OrderDetail> getOrderDetailList() {
		return orderDetailList;
	}

	public void setOrderDetailList(List<OrderDetail> orderDetailList) {
		this.orderDetailList = orderDetailList;
	}

	public long getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(long orderNo) {
		this.orderNo = orderNo;
	}

	public long getTotalFee() {
		return totalFee;
	}

	public void setTotalFee(long totalFee) {
		this.totalFee = totalFee;
	}

	public long getActualFee() {
		return actualFee;
	}

	public void setActualFee(long actualFee) {
		this.actualFee = actualFee;
	}

	public long getTotalDiscountFee() {
		return totalDiscountFee;
	}

	public void setTotalDiscountFee(long totalDiscountFee) {
		this.totalDiscountFee = totalDiscountFee;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getConsultPhone() {
		return consultPhone;
	}

	public void setConsultPhone(String consultPhone) {
		this.consultPhone = consultPhone;
	}

	public String getAftersalePhone() {
		return aftersalePhone;
	}

	public void setAftersalePhone(String aftersalePhone) {
		this.aftersalePhone = aftersalePhone;
	}

	public class OrderDetail {
		private String ticketName;

		private long price;

		private long num;

		public String getTicketName() {
			return ticketName;
		}

		public void setTicketName(String ticketName) {
			this.ticketName = ticketName;
		}

		public long getPrice() {
			return price;
		}

		public void setPrice(long price) {
			this.price = price;
		}

		public long getNum() {
			return num;
		}

		public void setNum(long num) {
			this.num = num;
		}

	}

}
