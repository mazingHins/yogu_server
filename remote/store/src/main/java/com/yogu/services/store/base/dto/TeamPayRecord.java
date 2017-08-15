package com.yogu.services.store.base.dto;

import java.io.Serializable;
import java.util.Date;
import java.math.*;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 团拼购买记录
 * 
 */
public class TeamPayRecord implements Serializable {

	private static final long serialVersionUID = -3074457344010614889L;

	/** 记录id(用作核销码) */
	private long recordId;

	/** 购买id */
	private long buyId;

	/** 用户id */
	private long uid;

	/** 订单编号 */
	private long orderNo;

	/** 是否已核销(1-是 0-否) */
	private short check;

	/** 核销员uid */
	private long checkUid;

	/** 核销时间 */
	private Date checkTime;

	/** 购买时间 */
	private Date createTime;

	/** 过期时间 */
	private Date expireTime;

	public void setRecordId(long recordId) {
		this.recordId = recordId;
	}

	public long getRecordId() {
		return recordId;
	}

	public void setBuyId(long buyId) {
		this.buyId = buyId;
	}

	public long getBuyId() {
		return buyId;
	}

	public void setUid(long uid) {
		this.uid = uid;
	}

	public long getUid() {
		return uid;
	}

	public void setOrderNo(long orderNo) {
		this.orderNo = orderNo;
	}

	public long getOrderNo() {
		return orderNo;
	}

	public void setCheck(short check) {
		this.check = check;
	}

	public short getCheck() {
		return check;
	}

	public void setCheckUid(long checkUid) {
		this.checkUid = checkUid;
	}

	public long getCheckUid() {
		return checkUid;
	}

	public void setCheckTime(Date checkTime) {
		this.checkTime = checkTime;
	}

	public Date getCheckTime() {
		return checkTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setExpireTime(Date expireTime) {
		this.expireTime = expireTime;
	}

	public Date getExpireTime() {
		return expireTime;
	}

	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
