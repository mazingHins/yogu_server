package com.yogu.services.order.base.entry;

import java.io.Serializable;
import java.util.Date;


/**
 * 已发送超时未接单短信通知订单记录表
 * 
 * <pre>
 *     自动生成代码: 表名 mz_over_accept_notified_order, 日期: 2015-10-19
 *     order_no <PK>         bigint(20)
 *     notify_times    tinyint(4)
 *     create_time     datetime(19)
 * </pre>
 */
public class OverAcceptNotifiedOrderPO implements Serializable {

	private static final long serialVersionUID = -3074457345581582568L;

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

}
