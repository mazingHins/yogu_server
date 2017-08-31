package com.yogu.services.order.coupon.entry;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 优惠券表， 包括生成、绑定用户等各种状态的优惠券
 */
public class CouponPO implements Serializable {
	
	private static final long serialVersionUID = 1439182832377875933L;

	/** 主键id */
	private long couponId;
	
	/** 券的显示名 */
	private String couponName = "";

	/** 优惠券类型：1-减免 */
	private short couponType;
	
	/** 用户id 为0时表示还没被领取 */
	private long uid = 0;
	
	/** 满多少钱才可以使用 */
	private long enoughMoney = 0;
	
	/** 优惠券面值（类型为代金券则直接存值如20；类型有折扣券则存入 0.75 X100 =75 的值） */
	private long faceValue;
	
	/** 使用的订单号 */
	private long orderNo = 0;
	
	/** 0：待领取 1：未使用 2：使用中 3：已使用 4：已失效 5：已删除 */
	private short status;
	
	/** 有效期开始时间 */
	private Date startTime;

	/** 有效期截止时间 */
	private Date endTime;

	/** 添加时间 */
	private Date addTime;

	/** 使用时间 */
	private Date useTime;
	
	/** 创建时间 */
	private Date createTime;

	/** 更新时间 */
	private Date updateTime;

	public long getCouponId() {
		return couponId;
	}

	public void setCouponId(long couponId) {
		this.couponId = couponId;
	}

	public String getCouponName() {
		return couponName;
	}

	public void setCouponName(String couponName) {
		this.couponName = couponName;
	}

	public short getCouponType() {
		return couponType;
	}

	public void setCouponType(short couponType) {
		this.couponType = couponType;
	}

	public long getUid() {
		return uid;
	}

	public void setUid(long uid) {
		this.uid = uid;
	}

	public long getEnoughMoney() {
		return enoughMoney;
	}

	public void setEnoughMoney(long enoughMoney) {
		this.enoughMoney = enoughMoney;
	}

	public long getFaceValue() {
		return faceValue;
	}

	public void setFaceValue(long faceValue) {
		this.faceValue = faceValue;
	}

	public long getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(long orderNo) {
		this.orderNo = orderNo;
	}

	public short getStatus() {
		return status;
	}

	public void setStatus(short status) {
		this.status = status;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public Date getAddTime() {
		return addTime;
	}

	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}

	public Date getUseTime() {
		return useTime;
	}

	public void setUseTime(Date useTime) {
		this.useTime = useTime;
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

	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}
}
