package com.yogu.services.order.pay.entry;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 支付优惠记录表
 * 
 */
public class PayDiscountRecordPO implements Serializable {

	private static final long serialVersionUID = 7695485239308508265L;

	/** 记录ID */
	private long recordId;

	/** 支付ID */
	private long payId;

	/** 优惠类型：1-优惠券；2-活动 */
	private short discountType;

	/** 优惠承担方类型 1：平台 2：商家 */
	private short bearType;

	/** 实际支付金额（精确到分） */
	private long totalFee;

	/** 优惠金额 */
	private long discountFee;

	/** 创建时间 */
	private Date createTime;

	public void setRecordId(long recordId) {
		this.recordId = recordId;
	}

	public long getRecordId() {
		return recordId;
	}

	public void setPayId(long payId) {
		this.payId = payId;
	}

	public long getPayId() {
		return payId;
	}

	public void setDiscountType(short discountType) {
		this.discountType = discountType;
	}

	public short getDiscountType() {
		return discountType;
	}

	public void setBearType(short bearType) {
		this.bearType = bearType;
	}

	public short getBearType() {
		return bearType;
	}

	public void setTotalFee(long totalFee) {
		this.totalFee = totalFee;
	}

	public long getTotalFee() {
		return totalFee;
	}

	public void setDiscountFee(long discountFee) {
		this.discountFee = discountFee;
	}

	public long getDiscountFee() {
		return discountFee;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
