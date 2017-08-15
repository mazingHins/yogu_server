package com.yogu.services.order.base.dto;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 购票订单明细表
 * 
 */
public class OrderTicketDetail implements Serializable {

	private static final long serialVersionUID = -3074457345462639364L;

	/** 订单id */
	private long orderId;

	/** 订单编号 */
	private long orderNo;

	/**
	 * 票状态(冗余)
	 */
	private short ticketStatus;

	/** 票id */
	private long ticketId;

	/** 票规则id */
	private long ticketRuleId;

	/**
	 * 票名称
	 */
	private String ticketName;

	/**
	 * 票英文名称
	 */
	private String ticketEnName;

	/**
	 * 用户id 冗余
	 */
	private long uid;

	/**
	 * 活动id
	 */
	private long eventId;

	/** 每张票的价格（精确到分） */
	private long price;

	/**
	 * 座位
	 */
	private int seat;

	/** 创建时间 */
	private Date createTime;

	public long getOrderId() {
		return orderId;
	}

	public void setOrderId(long orderId) {
		this.orderId = orderId;
	}

	public long getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(long orderNo) {
		this.orderNo = orderNo;
	}

	public short getTicketStatus() {
		return ticketStatus;
	}

	public void setTicketStatus(short ticketStatus) {
		this.ticketStatus = ticketStatus;
	}

	public long getTicketId() {
		return ticketId;
	}

	public void setTicketId(long ticketId) {
		this.ticketId = ticketId;
	}

	public long getTicketRuleId() {
		return ticketRuleId;
	}

	public void setTicketRuleId(long ticketRuleId) {
		this.ticketRuleId = ticketRuleId;
	}

	public String getTicketName() {
		return ticketName;
	}

	public void setTicketName(String ticketName) {
		this.ticketName = ticketName;
	}

	public String getTicketEnName() {
		return ticketEnName;
	}

	public void setTicketEnName(String ticketEnName) {
		this.ticketEnName = ticketEnName;
	}

	public long getEventId() {
		return eventId;
	}

	public void setEventId(long eventId) {
		this.eventId = eventId;
	}

	public long getPrice() {
		return price;
	}

	public void setPrice(long price) {
		this.price = price;
	}

	public int getSeat() {
		return seat;
	}

	public void setSeat(int seat) {
		this.seat = seat;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public long getUid() {
		return uid;
	}

	public void setUid(long uid) {
		this.uid = uid;
	}

	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
