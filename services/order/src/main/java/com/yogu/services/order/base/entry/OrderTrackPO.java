package com.yogu.services.order.base.entry;

import java.io.Serializable;
import java.util.Date;


/**
 * 订单从下单到结束的每个状态的说明记录
 * 
 * <pre>
 *     自动生成代码: 表名 mz_order_track, 日期: 2015-09-07
 *     track_id <PK>        bigint(20)
 *     order_id       bigint(20)
 *     status         tinyint(4)
 *     past_status    tinyint(4)
 *     duration       int(11)
 *     content        varchar(255)
 *     oper           bigint(20)
 *     create_time    datetime(19)
 * </pre>
 */
public class OrderTrackPO implements Serializable {

	private static final long serialVersionUID = -3074457344090974720L;

	/** 轨迹记录ID */
	private long trackId;

	/** 订单id */
	private long orderId;

	/** 订单状态 10-未付款；20-已付款；30-已接单；40-配送中；50-商家确认收货；60-买家确认收货；70-已评论；80-申请退款；90-退款中；100-已退款；110-订单已取消； */
	private short status;

	/** 上一个订单状态 */
	private short pastStatus;

	/** 当前状态消耗时长 */
	private long duration = 0;

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

	public void setDuration(long duration) {
		this.duration = duration;
	}

	public long getDuration() {
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

}
