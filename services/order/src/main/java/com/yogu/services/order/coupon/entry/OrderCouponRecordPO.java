package com.yogu.services.order.coupon.entry;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 订单优惠券使用记录表
 * 
 */
public class OrderCouponRecordPO implements Serializable {

	private static final long serialVersionUID = 5675785441445399674L;

	/** 记录ID */
	private long recordId;

	/** 订单id */
	private long orderId;

	/** 使用的优惠券ID */
	private long couponId;

	/** 券的显示名 */
	private String couponName = "";

	/** 优惠券类型  1：现金抵用券  2：折扣券 3：满减券 */
	private short couponType;
	
	/** 真正使用的金额（单位分） */
	private long couponFee;

	/** 使用状态；1-使用中，处于锁定状态；2-已使用；3-使用失败，被回滚 */
	private short useStatus;

	/** 创建时间 */
	private Date createTime;

	public void setRecordId(long recordId) {
		this.recordId = recordId;
	}

	public long getRecordId() {
		return recordId;
	}

	public void setOrderId(long orderId) {
		this.orderId = orderId;
	}

	public long getOrderId() {
		return orderId;
	}
	
	public void setCouponId(long couponId) {
		this.couponId = couponId;
	}

	public long getCouponId() {
		return couponId;
	}

	public void setCouponName(String couponName) {
		this.couponName = couponName;
	}

	public String getCouponName() {
		return couponName;
	}

	public void setCouponType(short couponType) {
		this.couponType = couponType;
	}

	public short getCouponType() {
		return couponType;
	}

	public void setCouponFee(long couponFee) {
		this.couponFee = couponFee;
	}

	public long getCouponFee() {
		return couponFee;
	}

	public void setUseStatus(short useStatus) {
		this.useStatus = useStatus;
	}

	public short getUseStatus() {
		return useStatus;
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
