package com.yogu.core.consumer.handler.bean;

import java.io.Serializable;

/**
 * 订单支付完成/货到付款的messageBean
 * 
 * @author Hins
 * @date 2016年1月6日 下午4:44:01
 */
public class PendingOrderBean implements Serializable {
	
	private static final long serialVersionUID = 7313441304599028590L;

	/**
	 * 购买者ID
	 */
	private long uid;

	/**
	 * 订单编号
	 */
	private long orderNo;

	/**
	 * 门店ID
	 */
	private long storeId;

	/**
	 * 支付编号
	 */
	private long payNo;

	/**
	 * 订单应付金额(单位分)
	 */
	private int orderFee;

	/**
	 * 订单商品金额（单位分）
	 */
	private int goodsFee;

	/**
	 * 订单配送费（单位分）
	 */
	private int deliveryFee;

	/**
	 * 支付类型
	 */
	private short payType;
	
	/**
	 * 优惠券ID
	 */
	private int couponId;

	public long getUid() {
		return uid;
	}

	public void setUid(long uid) {
		this.uid = uid;
	}

	public long getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(long orderNo) {
		this.orderNo = orderNo;
	}

	public long getStoreId() {
		return storeId;
	}

	public void setStoreId(long storeId) {
		this.storeId = storeId;
	}

	public long getPayNo() {
		return payNo;
	}

	public void setPayNo(long payNo) {
		this.payNo = payNo;
	}

	public int getOrderFee() {
		return orderFee;
	}

	public void setOrderFee(int orderFee) {
		this.orderFee = orderFee;
	}

	public int getGoodsFee() {
		return goodsFee;
	}

	public void setGoodsFee(int goodsFee) {
		this.goodsFee = goodsFee;
	}

	public int getDeliveryFee() {
		return deliveryFee;
	}

	public void setDeliveryFee(int deliveryFee) {
		this.deliveryFee = deliveryFee;
	}

	public short getPayType() {
		return payType;
	}

	public void setPayType(short payType) {
		this.payType = payType;
	}

	public int getCouponId() {
		return couponId;
	}

	public void setCouponId(int couponId) {
		this.couponId = couponId;
	}
	
}
