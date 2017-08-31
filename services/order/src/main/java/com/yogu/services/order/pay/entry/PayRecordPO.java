package com.yogu.services.order.pay.entry;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 订单支付记录
 */
public class PayRecordPO implements Serializable {
	
	private static final long serialVersionUID = -5911960862534257772L;

	/** 主键id */
	private long payId;
	
	/** 支付流水号 */
	private long payNo;
	
	/** 内部平台订单号 */
	private long orderNo;
	
	/** 支付方式，1-支付宝；2-微信 */
	private short payMode;
	
	/** 支付方式对应的回调通知ID */
	private long notifyId = 0;
	
	/** 购买用户ID*/
	private long buyerUid = 0;
	
	/** 商家id */
	private long storeId;
	
	/** 实付金额（精确到分） */
	private long totalFee = 0;
	
	/** 订单应付金额，冗余字段，单位分 */
	private long orderFee = 0;
	
	/** 优惠券价格，冗余字段，单位分 */
	private long discountFee = 0;
	
	/** 商品价格，冗余字段，单位分 */
	private long goodsFee = 0;
	
	/** 是否使用优惠券，1-是；其他否 */
	private short useCoupon;
	
	/** 支付状态：1-等待支付；2-支付失败；3-支付成功； */
	private short payStatus;
	
	/** 创建时间 */
	private Date createTime;

	/** 更新时间 */
	private Date updateTime;
	
	/** 完成时间 */
	private Date finishTime;

	public long getPayId() {
		return payId;
	}

	public void setPayId(long payId) {
		this.payId = payId;
	}

	public long getPayNo() {
		return payNo;
	}

	public void setPayNo(long payNo) {
		this.payNo = payNo;
	}

	public long getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(long orderNo) {
		this.orderNo = orderNo;
	}

	public short getPayMode() {
		return payMode;
	}

	public void setPayMode(short payMode) {
		this.payMode = payMode;
	}

	public long getNotifyId() {
		return notifyId;
	}

	public void setNotifyId(long notifyId) {
		this.notifyId = notifyId;
	}

	public long getBuyerUid() {
		return buyerUid;
	}

	public void setBuyerUid(long buyerUid) {
		this.buyerUid = buyerUid;
	}

	public long getStoreId() {
		return storeId;
	}

	public void setStoreId(long storeId) {
		this.storeId = storeId;
	}

	public long getTotalFee() {
		return totalFee;
	}

	public void setTotalFee(long totalFee) {
		this.totalFee = totalFee;
	}

	public long getOrderFee() {
		return orderFee;
	}

	public void setOrderFee(long orderFee) {
		this.orderFee = orderFee;
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

	public short getPayStatus() {
		return payStatus;
	}

	public void setPayStatus(short payStatus) {
		this.payStatus = payStatus;
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
