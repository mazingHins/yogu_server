package com.yogu.services.order.base.dao.param;

import java.util.Date;

public class UpdateOrderToUserConfirmByMazingPayPOJO {
	
	/**
	 * 订单ID
	 */
	private long orderId;

	/**
	 * 旧的订单状态（查询条件）
	 */
	private short oldStatus;

	/**
	 * 新的订单状态
	 */
	private short newStatus;

	/**
	 * 当天订单序列号
	 */
	private String serialNumber;

	/**
	 * 订单的排序号
	 */
	private long sequence;

	/**
	 * 订单修改时间
	 */
	private Date updateTime;

	/**
	 * 订单开始时间
	 */
	private Date orderBeginTime;
	
	/**
	 * 订单完成时间
	 */
	private Date finishTime;
	
	/** 用户确认收货时间 */
	private Date userConfirmTime;

	public long getOrderId() {
		return orderId;
	}

	public void setOrderId(long orderId) {
		this.orderId = orderId;
	}

	public short getOldStatus() {
		return oldStatus;
	}

	public void setOldStatus(short oldStatus) {
		this.oldStatus = oldStatus;
	}

	public short getNewStatus() {
		return newStatus;
	}

	public void setNewStatus(short newStatus) {
		this.newStatus = newStatus;
	}

	public String getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}

	public long getSequence() {
		return sequence;
	}

	public void setSequence(long sequence) {
		this.sequence = sequence;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public Date getOrderBeginTime() {
		return orderBeginTime;
	}

	public void setOrderBeginTime(Date orderBeginTime) {
		this.orderBeginTime = orderBeginTime;
	}

	public Date getFinishTime() {
		return finishTime;
	}

	public void setFinishTime(Date finishTime) {
		this.finishTime = finishTime;
	}

	public Date getUserConfirmTime() {
		return userConfirmTime;
	}

	public void setUserConfirmTime(Date userConfirmTime) {
		this.userConfirmTime = userConfirmTime;
	}
	

}
