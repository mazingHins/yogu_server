package com.yogu.core.consumer.messageBean;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 批量创建优惠券的优惠券内容载体
 * 
 * @author sky
 *
 */
public class CreateCouponBO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 933599713176677086L;

	/** 主键id */
	private long couponId;

	/** 券规则id */
	private long couponRuleId;

	/** 承担方类型 1：平台型 2：商家型 */
	private short couponBearType;

	/** 优惠券类型 1：现金抵用券 2：折扣券 3：满减券 */
	private short couponType;

	/** 批次号 */
	private int batchNo;

	/** 券编号 全局唯一 */
	private String couponCode;

	/** 用户id 为0时表示还没被领取 */
	private long uid = 0;

	/** 券的显示名 */
	private String couponName = "";

	/** 券的显示描述 */
	private String description = "";

	/** 是否有效 1:是 0:否 */
	private short isEnable;

	/** 优惠券面值（类型为代金券则直接存值如20；类型有折扣券则存入 0.75 X100 =75 的值） */
	private long faceValue;

	/** 0：待领取 1：未使用 2：使用中 3：已使用 4：已失效 5：已删除 */
	private short status;

	/** 来源 */
	private String channel = "";

	/** 使用的订单号 */
	private long orderNo = 0;

	/** 有效期开始时间 */
	private Date startTime;

	/** 有效期截止时间 */
	private Date endTime;

	/** 添加时间 */
	private Date addTime;

	/** 使用时间 */
	private Date useTime;

	/** 手机尾号 */
	private String phoneSuffix = "";

	/** 满多少钱才可以使用 */
	private long enoughMoney = 0;

	private int createTotal = 0;
	
	/** 折扣券最高优惠金额 **/
	private int mostOffer = 0;
	
	/** 优惠券使用渠道，0:所有渠道 1:线上订餐 2:mazing pay */
	private short usingChannel = 0;

	public short getUsingChannel() {
		return usingChannel;
	}

	public void setUsingChannel(short usingChannel) {
		this.usingChannel = usingChannel;
	}


	public int getMostOffer() {
		return mostOffer;
	}

	public void setMostOffer(int mostOffer) {
		this.mostOffer = mostOffer;
	}


	public int getCreateTotal() {
		return createTotal;
	}

	public void setCreateTotal(int createTotal) {
		this.createTotal = createTotal;
	}

	public long getEnoughMoney() {
		return enoughMoney;
	}

	public void setEnoughMoney(long enoughMoney) {
		this.enoughMoney = enoughMoney;
	}

	public void setCouponId(long couponId) {
		this.couponId = couponId;
	}

	public long getCouponId() {
		return couponId;
	}

	public void setCouponRuleId(long couponRuleId) {
		this.couponRuleId = couponRuleId;
	}

	public long getCouponRuleId() {
		return couponRuleId;
	}

	public void setCouponBearType(short couponBearType) {
		this.couponBearType = couponBearType;
	}

	public short getCouponBearType() {
		return couponBearType;
	}

	public void setCouponType(short couponType) {
		this.couponType = couponType;
	}

	public short getCouponType() {
		return couponType;
	}

	public void setBatchNo(int batchNo) {
		this.batchNo = batchNo;
	}

	public int getBatchNo() {
		return batchNo;
	}

	public void setCouponCode(String couponCode) {
		this.couponCode = couponCode;
	}

	public String getCouponCode() {
		return couponCode;
	}

	public void setUid(long uid) {
		this.uid = uid;
	}

	public long getUid() {
		return uid;
	}

	public void setCouponName(String couponName) {
		this.couponName = couponName;
	}

	public String getCouponName() {
		return couponName;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}

	public void setIsEnable(short isEnable) {
		this.isEnable = isEnable;
	}

	public short getIsEnable() {
		return isEnable;
	}

	public void setFaceValue(long faceValue) {
		this.faceValue = faceValue;
	}

	public long getFaceValue() {
		return faceValue;
	}

	public void setStatus(short status) {
		this.status = status;
	}

	public short getStatus() {
		return status;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}

	public String getChannel() {
		return channel;
	}

	public void setOrderNo(long orderNo) {
		this.orderNo = orderNo;
	}

	public long getOrderNo() {
		return orderNo;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}

	public Date getAddTime() {
		return addTime;
	}

	public void setUseTime(Date useTime) {
		this.useTime = useTime;
	}

	public Date getUseTime() {
		return useTime;
	}

	public void setPhoneSuffix(String phoneSuffix) {
		this.phoneSuffix = phoneSuffix;
	}

	public String getPhoneSuffix() {
		return phoneSuffix;
	}

	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
