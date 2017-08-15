package com.yogu.services.order.base.dao.param;

import java.io.Serializable;
import java.util.Date;

/**
 * 更换支付方式的POJO对象
 * 
 * @author Hins
 * @date 2015年10月23日 下午4:02:53
 */
public class ChangePayTypePOJO implements Serializable {

	private static final long serialVersionUID = -6290700846614322306L;

	/**
	 * 订单ID
	 */
	private long orderId;
	
	/**
	 * 支付编号
	 */
	private long payNo;

	/**
	 * 支付类型：1-货到付款；2-线上支付
	 */
	private short payType;
	
	/** 
	 * 支付方式，1-支付宝；2-微信
	 */
	private short payMode = 0;

	/**
	 * 旧订单状态
	 */
	private short oldStatus;

	/**
	 * 修改后的订单状态
	 */
	private short newStatus;

	/**
	 * 订单开始时间（非必传）
	 */
	private Date orderBeginTime;

	/**
	 * 订单预计送达时间
	 */
	private Date deliveryTime;

	/**
	 * 当天订单序列号
	 */
	private String serialNumber = "";
	
	/**
	 * 当天订单排序号
	 */
	private long sequence = 0;
	
	/**
	 * 实际支付金额（单位分）
	 */
	private long actualFee;
	
	/**
	 * 优惠金额（单位分）
	 */
	private long discountFee;

	/**
	 * 是否使用优惠券
	 */
	private short useCoupon;

	public long getSequence() {
		return sequence;
	}

	public void setSequence(long sequence) {
		this.sequence = sequence;
	}

	public long getOrderId() {
		return orderId;
	}

	public void setOrderId(long orderId) {
		this.orderId = orderId;
	}
	
	public long getPayNo() {
		return payNo;
	}

	public void setPayNo(long payNo) {
		this.payNo = payNo;
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

	public String getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}
	
	public long getActualFee() {
		return actualFee;
	}

	public void setActualFee(long actualFee) {
		this.actualFee = actualFee;
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
	
}
