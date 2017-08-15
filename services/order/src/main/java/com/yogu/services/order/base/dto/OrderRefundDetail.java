package com.yogu.services.order.base.dto;

import java.io.Serializable;
import java.util.Date;
import java.math.*;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * mz_order_refund_detail
 * 
 */
public class OrderRefundDetail implements Serializable {

	private static final long serialVersionUID = -3074457344237755554L;

	/** 退款申请表id */
	private long refundId;

	/** 票的id */
	private long ticketId;

	/** 票的编号 */
	private String ticketNo;

	/** 票规则id */
	private long ticketRuleId;

	/** 退款金额(每张票的价格) */
	private long refundFee;

	/** 用户id */
	private long uid;

	/** 创建时间 */
	private Date createTime;

	public void setRefundId(long refundId) {
		this.refundId = refundId;
	}

	public long getRefundId() {
		return refundId;
	}

	public void setTicketId(long ticketId) {
		this.ticketId = ticketId;
	}

	public long getTicketId() {
		return ticketId;
	}

	public void setTicketNo(String ticketNo) {
		this.ticketNo = ticketNo;
	}

	public String getTicketNo() {
		return ticketNo;
	}

	public void setTicketRuleId(long ticketRuleId) {
		this.ticketRuleId = ticketRuleId;
	}

	public long getTicketRuleId() {
		return ticketRuleId;
	}

	public void setRefundFee(long refundFee) {
		this.refundFee = refundFee;
	}

	public long getRefundFee() {
		return refundFee;
	}

	public void setUid(long uid) {
		this.uid = uid;
	}

	public long getUid() {
		return uid;
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
