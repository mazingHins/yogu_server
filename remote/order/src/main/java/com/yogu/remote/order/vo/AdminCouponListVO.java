package com.yogu.remote.order.vo;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 管理后台使用的 VO，优惠券列表
 * 
 * @author ten 2015/12/24.
 */
public class AdminCouponListVO implements Serializable {

	private static final long serialVersionUID = 1;

	/** 优惠券规则主键id */
	private long couponRuleId;
	
	private String encryptCouponRuleId;

	/** 券的显示名称 */
	private String couponName = "";

	/** 券的显示描述 */
	private String description = "";

	/** 券的责任方类型 1:平台型 2:商家性 */
	private short couponBearType;

	/** 优惠券类型 1：现金抵用券 2：折扣券 3：满减券 */
	private short couponType;

	/** 满多少钱(该值大于0表示使用该券订单应该满足的额度) */
	private long enoughMoney = 0;

	/** 规则表达式 (如: faceValue=30;discount=0.5; )目前该字段只存值如：30，0.5 */
	private String regExpression = "";

	/** 有效期开始时间 */
	private Date startTime;

	/** 有效期截止时间 */
	private Date endTime;

	/** 创建时间 */
	private Date createTime;

	/** 是否启用 1:启用 0:不启用 */
	private short isEnable;

	/** 是否终止发放 1: 是 0: 否 */
	private short isStop;

	/** 是否可以分享 1：是 0：否 */
	private short canShare = 0;

	/** 每天限用次数 */
	private short dayUseTotal;

	/** s` varchar(256) NOT NULL DEFAULT COMMENT 审批意见 */
	private String approvalComments = "";

	/** 审批状态 1: 申请中 2: 审批通过 3: 审批不通过 */
	private short approvalStatus = 1;

	/**
	 * 发放总量
	 */
	private int createTotal;

	/**
	 * 使用总量
	 */
	private int useTotal;

	/**
	 * 领取总量
	 */
	private int gainTotal;

	/**
	 * 卡包ID，2016/1/26 by ten
	 */
	private long bagId;

	/**
	 * 卡包名称，2016/1/27 by ten
	 */
	private String bagName;

	/** 有效时间是否动态, 0:否 1:是 */
	private short isDynamic = 0;

	/** 动态有效时间的有效天数,从领取当天开始计数,若为1,则当天有效 */
	private int effectDays = 1;

	/** 是否有被排除使用的范围限制 有的话需要做使用检查 1:有 0:没有 */
	private short hasExclude;

	/** 修改人 */
	private long adminId;

	private String adminName;

	/** 折扣券最高优惠金额 **/
	private long mostOffer = 0;
	
	/** 优惠券使用渠道，0:所有渠道 1:线上订餐 2:mazing pay */
	private short usingChannel = 0;

	public short getUsingChannel() {
		return usingChannel;
	}

	public void setUsingChannel(short usingChannel) {
		this.usingChannel = usingChannel;
	}

	public short getCanShare() {
		return canShare;
	}

	public void setCanShare(short canShare) {
		this.canShare = canShare;
	}

	public long getMostOffer() {
		return mostOffer;
	}

	public void setMostOffer(long mostOffer) {
		this.mostOffer = mostOffer;
	}

	public long getAdminId() {
		return adminId;
	}

	public void setAdminId(long adminId) {
		this.adminId = adminId;
	}

	public String getAdminName() {
		return adminName;
	}

	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}

	public short getHasExclude() {
		return hasExclude;
	}

	public void setHasExclude(short hasExclude) {
		this.hasExclude = hasExclude;
	}

	public short getIsDynamic() {
		return isDynamic;
	}

	public void setIsDynamic(short isDynamic) {
		this.isDynamic = isDynamic;
	}

	public int getEffectDays() {
		return effectDays;
	}

	public void setEffectDays(int effectDays) {
		this.effectDays = effectDays;
	}

	public String getBagName() {
		return bagName;
	}

	public void setBagName(String bagName) {
		this.bagName = bagName;
	}

	public long getCouponRuleId() {
		return couponRuleId;
	}

	public void setCouponRuleId(long couponRuleId) {
		this.couponRuleId = couponRuleId;
	}

	public String getEncryptCouponRuleId() {
		return encryptCouponRuleId;
	}

	public void setEncryptCouponRuleId(String encryptCouponRuleId) {
		this.encryptCouponRuleId = encryptCouponRuleId;
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

	public short getCouponBearType() {
		return couponBearType;
	}

	public void setCouponBearType(short couponBearType) {
		this.couponBearType = couponBearType;
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

	public short getDayUseTotal() {
		return dayUseTotal;
	}

	public void setDayUseTotal(short dayUseTotal) {
		this.dayUseTotal = dayUseTotal;
	}

	public String getApprovalComments() {
		return approvalComments;
	}

	public void setApprovalComments(String approvalComments) {
		this.approvalComments = approvalComments;
	}

	public short getApprovalStatus() {
		return approvalStatus;
	}

	public void setApprovalStatus(short approvalStatus) {
		this.approvalStatus = approvalStatus;
	}

	public int getCreateTotal() {
		return createTotal;
	}

	public void setCreateTotal(int createTotal) {
		this.createTotal = createTotal;
	}

	public int getUseTotal() {
		return useTotal;
	}

	public void setUseTotal(int useTotal) {
		this.useTotal = useTotal;
	}

	public int getGainTotal() {
		return gainTotal;
	}

	public void setGainTotal(int gainTotal) {
		this.gainTotal = gainTotal;
	}

	public long getBagId() {
		return bagId;
	}

	public void setBagId(long bagId) {
		this.bagId = bagId;
	}
	
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
