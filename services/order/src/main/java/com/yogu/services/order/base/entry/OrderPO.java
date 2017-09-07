package com.yogu.services.order.base.entry;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 订单表
 * 
 */
public class OrderPO implements Serializable {

	private static final long serialVersionUID = 2663608258574945938L;

	/** 订单ID */
	private long orderId;

	/** 订单编号 */
	private long orderNo;

	/** 支付系统流水号 */
	private long payNo;

	/** 下单人id */
	private long uid;

	/** 店家id */
	private long storeId;

	/** 支付方式，1-支付宝；2-微信 */
	private short payMode = 0;
	
	/** 价格类型：1-零售价；2-批发价 */
	private short priceType;

	/** 订单金额（应付金额，精确到分） */
	private long totalFee = 0;

	/** 实付金额（精确到分） */
	private long actualFee = 0;

	/** 优惠总价（精确到分） */
	private long discountFee = 0;

	/** 商品总价（精确到分） */
	private long goodsFee;

	/** 是否使用优惠券，1-是；其他否 */
	private short useCoupon = 0;

	/** 送货地址全称 */
	private String fullAddress;

	/** 收货联系人 */
	private String contacts;

	/** 收货联系电话 */
	private String phone;

	/**
	 * 订单状态：10-未付款；15-已付款；20-已接单；25-配送中；30-商家确认收货；35-买家确认收货；40-已评论；
	 * 45-申请退款；50-退款中；55-拒绝退款；60-已退款；65-订单已取消；
	 */
	private short status;

	/** 订单备注 */
	private String remark;

	/** 用户确认收货时间 */
	private Date userConfirmTime;

	/** 商家确认收货时间 */
	private Date storeConfirmTime;

	/** 订单创建时间 */
	private Date createTime;

	/** 订单修改时间默认值为创建时间 */
	private Date updateTime;

	/** 订单完成时间 */
	private Date finishTime;
	
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

	public long getPayNo() {
		return payNo;
	}

	public void setPayNo(long payNo) {
		this.payNo = payNo;
	}

	public long getUid() {
		return uid;
	}

	public void setUid(long uid) {
		this.uid = uid;
	}

	public long getStoreId() {
		return storeId;
	}

	public void setStoreId(long storeId) {
		this.storeId = storeId;
	}

	public short getPayMode() {
		return payMode;
	}

	public void setPayMode(short payMode) {
		this.payMode = payMode;
	}

	public short getPriceType() {
		return priceType;
	}

	public void setPriceType(short priceType) {
		this.priceType = priceType;
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

	public long getDiscountFee() {
		return discountFee;
	}

	public void setDiscountFee(long discountFee) {
		this.discountFee = discountFee;
	}

	public long getGoodsFee() {
		return goodsFee;
	}

	public void setGoodsFee(long goodsFee) {
		this.goodsFee = goodsFee;
	}

	public short getUseCoupon() {
		return useCoupon;
	}

	public void setUseCoupon(short useCoupon) {
		this.useCoupon = useCoupon;
	}

	public String getFullAddress() {
		return fullAddress;
	}

	public void setFullAddress(String fullAddress) {
		this.fullAddress = fullAddress;
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

	public short getStatus() {
		return status;
	}

	public void setStatus(short status) {
		this.status = status;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Date getUserConfirmTime() {
		return userConfirmTime;
	}

	public void setUserConfirmTime(Date userConfirmTime) {
		this.userConfirmTime = userConfirmTime;
	}

	public Date getStoreConfirmTime() {
		return storeConfirmTime;
	}

	public void setStoreConfirmTime(Date storeConfirmTime) {
		this.storeConfirmTime = storeConfirmTime;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public Date getFinishTime() {
		return finishTime;
	}

	public void setFinishTime(Date finishTime) {
		this.finishTime = finishTime;
	}

	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
