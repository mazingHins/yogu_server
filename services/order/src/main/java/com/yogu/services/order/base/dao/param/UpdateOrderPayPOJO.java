package com.yogu.services.order.base.dao.param;

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
	 * 支付类型：1-在线支付；2-货到付款
	 */
	private short payType;

	/**
	 * 支付方式：1-支付宝；2-微信
	 */
	private short payMode;

	/**
	 * 优惠金额
	 */
	private long discountFee;

	/**
	 * 是否使用优惠券
	 */
	private short useCoupon;

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
	 * 订单预计送达时间
	 */
	private Date deliveryTime;

	/**
	 * 实际支付金额（单位分）
	 */
	private long actualFee;

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

	public long getDiscountFee() {
		return discountFee;
	}

	public void setDiscountFee(long discountFee) {
		this.discountFee = discountFee;
	}

	public short getUseCoupon() {
		return useCoupon;
	}

	public void setUseCoupon(short useCoupon) {
		this.useCoupon = useCoupon;
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

	public Date getDeliveryTime() {
		return deliveryTime;
	}

	public void setDeliveryTime(Date deliveryTime) {
		this.deliveryTime = deliveryTime;
	}

	public long getActualFee() {
		return actualFee;
	}

	public void setActualFee(long actualFee) {
		this.actualFee = actualFee;
	}

}
