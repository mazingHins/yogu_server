package com.yogu.services.order.base.dao.params;

import java.util.Date;

/**
 * 维护订单单支付流水，订单状态，支付方式，支付类型，优惠金额，实际支付金额，修改时间的POJO
 * 
 * @author Hins
 * @date 2016年1月6日 下午1:34:56
 */
public class UpdateOrderPayPOJO {

	/**
	 * 订单ID
	 */
	private long orderId;

	/**
	 * 旧的订单状态（查询条件）
	 */
	private short oldStatus;

	/**
	 * 支付编号
	 */
	private long payNo;

	/**
	 * 新的订单状态
	 */
	private short newStatus;

	/**
	 * 支付方式：1-支付宝；2-微信
	 */
	private short payMode;

	/**
	 * 订单修改时间
	 */
	private Date updateTime;

	/**
	 * 订单开始时间
	 */
	private Date orderBeginTime;

	/**
	 * 订单预计送达时间
	 */
	private Date deliveryTime;

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

	public long getPayNo() {
		return payNo;
	}

	public void setPayNo(long payNo) {
		this.payNo = payNo;
	}

	public short getNewStatus() {
		return newStatus;
	}

	public void setNewStatus(short newStatus) {
		this.newStatus = newStatus;
	}

	public short getPayMode() {
		return payMode;
	}

	public void setPayMode(short payMode) {
		this.payMode = payMode;
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

	public Date getDeliveryTime() {
		return deliveryTime;
	}

	public void setDeliveryTime(Date deliveryTime) {
		this.deliveryTime = deliveryTime;
	}

}
