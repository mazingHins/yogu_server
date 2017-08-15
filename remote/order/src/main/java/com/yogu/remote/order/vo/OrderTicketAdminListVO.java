package com.yogu.remote.order.vo;

import java.util.List;

public class OrderTicketAdminListVO {
	/** 活动id */
	private long eventId;

	/** 活动名称 */
	private String eventName;

	private long orderNo;

	private short status;

	// 用户帐号
	private String passport;

	// 用户昵称
	private String nickName;

	private String createTime;

	private long totalFee;

	private String contacts;

	private String phone;

	private List<TicketDetailVO> ticketList;

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

	public long getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(long orderNo) {
		this.orderNo = orderNo;
	}

	public short getStatus() {
		return status;
	}

	public void setStatus(short status) {
		this.status = status;
	}

	public String getPassport() {
		return passport;
	}

	public void setPassport(String passport) {
		this.passport = passport;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public long getTotalFee() {
		return totalFee;
	}

	public void setTotalFee(long totalFee) {
		this.totalFee = totalFee;
	}

	public String getContacts() {
		return contacts;
	}

	public void setContacts(String contacts) {
		this.contacts = contacts;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public List<TicketDetailVO> getTicketList() {
		return ticketList;
	}

	public void setTicketList(List<TicketDetailVO> ticketList) {
		this.ticketList = ticketList;
	}
}
