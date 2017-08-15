package com.yogu.services.order.base.dto;

import java.io.Serializable;
import java.util.Date;
import java.math.*;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 已发送超时未接单短信通知订单记录表
 * 
 */
public class OverAcceptNotifiedOrder implements Serializable {

	private static final long serialVersionUID = -3074457344481290250L;

	/** 订单编号 */
	private long orderNo;

	/** 超时未接单短信通知次数 暂时允许最大为1 */
	private short notifyTimes = 0;

	/** 创建时间 */
	private Date createTime;

	public void setOrderNo(long orderNo) {
		this.orderNo = orderNo;
	}

	public long getOrderNo() {
		return orderNo;
	}

	public void setNotifyTimes(short notifyTimes) {
		this.notifyTimes = notifyTimes;
	}

	public short getNotifyTimes() {
		return notifyTimes;
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
