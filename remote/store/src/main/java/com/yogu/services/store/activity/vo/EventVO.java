package com.yogu.services.store.activity.vo;

import java.util.Date;

public class EventVO {
	private long eventId;

	private String eventName;

	private long storeId;

	private String address;

	private Date startTime;

	private Date endTime;

	private String langCode;

	private String coverImg;

	// 售前电话
	private String consultPhone;

	// 售后电话
	private String aftersalePhone;

	// 是否能购买
	private short canBuy;

	// 是否能退款
	private short canRefund;

	// 权益
	private String rights;

	// 须知
	private String notice;

	// 介绍
	private String content;

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

	public long getStoreId() {
		return storeId;
	}

	public void setStoreId(long storeId) {
		this.storeId = storeId;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public String getLangCode() {
		return langCode;
	}

	public void setLangCode(String langCode) {
		this.langCode = langCode;
	}

	public String getCoverImg() {
		return coverImg;
	}

	public void setCoverImg(String coverImg) {
		this.coverImg = coverImg;
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

	public short getCanBuy() {
		return canBuy;
	}

	public void setCanBuy(short canBuy) {
		this.canBuy = canBuy;
	}

	public short getCanRefund() {
		return canRefund;
	}

	public void setCanRefund(short canRefund) {
		this.canRefund = canRefund;
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

}
