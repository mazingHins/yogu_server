package com.yogu.services.order.base.dto;

import java.io.Serializable;
import java.util.Date;
import java.math.*;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 订单从下单到结束的每个状态的说明记录
 * 
 */
public class OrderTrack implements Serializable {

	private static final long serialVersionUID = -3074457345517087218L;

	/** 轨迹记录ID */
	private long trackId;

	/** 订单id */
	private long orderId;

	/** 订单状态 10-未付款；20-已付款；30-已接单；40-配送中；50-商家确认收货；60-买家确认收货；70-已评论；80-申请退款；90-退款中；100-已退款；110-订单已取消； */
	private short status;

	/** 上一个订单状态 */
	private short pastStatus;

	/** 当前状态消耗时长 */
	private int duration = 0;

	/** 跟踪轨迹说明，json字符串格式 */
	private String content;

	/** 操作人 */
	private long oper;

	/** 创建时间 */
	private Date createTime;

	public void setTrackId(long trackId) {
		this.trackId = trackId;
	}

	public long getTrackId() {
		return trackId;
	}

	public void setOrderId(long orderId) {
		this.orderId = orderId;
	}

	public long getOrderId() {
		return orderId;
	}

	public void setStatus(short status) {
		this.status = status;
	}

	public short getStatus() {
		return status;
	}

	public void setPastStatus(short pastStatus) {
		this.pastStatus = pastStatus;
	}

	public short getPastStatus() {
		return pastStatus;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public int getDuration() {
		return duration;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getContent() {
		return content;
	}

	public void setOper(long oper) {
		this.oper = oper;
	}

	public long getOper() {
		return oper;
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
