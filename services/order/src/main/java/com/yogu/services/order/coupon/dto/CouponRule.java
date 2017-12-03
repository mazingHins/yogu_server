package com.yogu.services.order.coupon.dto;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 优惠券规则表
 * 
 */
public class CouponRule implements Serializable {

	private static final long serialVersionUID = -3074457345574242807L;

	/** 优惠券规则主键id */
	private long couponRuleId;

	/** 券的显示名称 */
	private String couponName = "";

	/** 券的显示描述 */
	private String description = "";

	/** 优惠券类型 1：现金抵用券 2：折扣券 3：满减券 */
	private short couponType;

	/** 满多少钱(该值大于0表示使用该券订单应该满足的额度) */
	private long enoughMoney = 0;

	/** 规则表达式 (如: faceValue=30;discount=0.5; )目前该字段只存值如：30，0.5 */
	private String regExpression = "";

	/** 每人最高可领次数 */
	private int gainTotal;

	/** 有效期开始时间 */
	private Date startTime;

	/** 有效期截止时间 */
	private Date endTime;

	/** 创建时间 */
	private Date createTime;

	/** 修改时间 */
	private Date modifyTime;

	/** 修改人 */
	private long adminId;

	/** 是否启用 1:启用 0:不启用 */
	private short isEnable;

	/** 是否终止发放 1: 是 0: 否 */
	private short isStop;

	/** 折扣券最高优惠金额 **/
	private long mostOffer = 0;
	

	public long getCouponRuleId() {
		return couponRuleId;
	}


	public void setCouponRuleId(long couponRuleId) {
		this.couponRuleId = couponRuleId;
	}


	public String getCouponName() {
		return couponName;
	}


	public void setCouponName(String couponName) {
		this.couponName = couponName;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public short getCouponType() {
		return couponType;
	}


	public void setCouponType(short couponType) {
		this.couponType = couponType;
	}


	public long getEnoughMoney() {
		return enoughMoney;
	}


	public void setEnoughMoney(long enoughMoney) {
		this.enoughMoney = enoughMoney;
	}


	public String getRegExpression() {
		return regExpression;
	}


	public void setRegExpression(String regExpression) {
		this.regExpression = regExpression;
	}


	public int getGainTotal() {
		return gainTotal;
	}


	public void setGainTotal(int gainTotal) {
		this.gainTotal = gainTotal;
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


	public Date getCreateTime() {
		return createTime;
	}


	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}


	public Date getModifyTime() {
		return modifyTime;
	}


	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}


	public long getAdminId() {
		return adminId;
	}


	public void setAdminId(long adminId) {
		this.adminId = adminId;
	}


	public short getIsEnable() {
		return isEnable;
	}


	public void setIsEnable(short isEnable) {
		this.isEnable = isEnable;
	}


	public short getIsStop() {
		return isStop;
	}


	public void setIsStop(short isStop) {
		this.isStop = isStop;
	}


	public long getMostOffer() {
		return mostOffer;
	}


	public void setMostOffer(long mostOffer) {
		this.mostOffer = mostOffer;
	}


	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
