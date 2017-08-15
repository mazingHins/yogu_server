package com.yogu.services.order.base.entry;

import java.io.Serializable;
import java.util.Date;


/**
 * mz_order_refund_detail
 * 
 * <pre>
 *     自动生成代码: 表名 mz_order_refund_detail, 日期: 2017-03-07
 *     refund_id <PK>          bigint(20)
 *     ticket_id <PK>          bigint(20)
 *     ticket_no         varchar(32)
 *     ticket_rule_id    bigint(20)
 *     refund_fee        bigint(20)
 *     uid               bigint(20)
 *     create_time       datetime(19)
 * </pre>
 */
public class OrderRefundDetailPO implements Serializable {

	private static final long serialVersionUID = -3074457347651936592L;

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

}
