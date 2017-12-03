package com.yogu.remote.order.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 优惠券规则信息实体,该VO实体包含了 优惠券使用范围相关数据
 * 
 */
public class CouponRuleVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8646259459764553991L;

	/** 优惠券规则主键id */
	private long couponRuleId;

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

	/** 每人最高可领次数 */
	private int gainTotal;

	/** 使用时的手机尾号限制 */
	private String phoneSuffix = "";

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

	/** 是否检查领取时的电话跟收货电话一致 1：需要检查 0：不需要 */
	private short verifyRecePhone;

	/** 每天限用次数 */
	private short dayUseTotal;

	/** 是否有包含范围限制在包含内的表示可以使用 1:有 0:没有 有的话需要做能否使用检查 */
	private short hasInclude;

	/** 是否有被排除使用的范围限制 有的话需要做使用检查 1:有 0:没有 */
	private short hasExclude;

	/** 审批人 */
	private long approver;

	/** 审批时间 */
	private Date approvalTime;

	/** 审批意见 */
	private String approvalComments = "";

	/** 审批状态 1: 申请中 2: 审批通过 3: 审批不通过 */
	private short approvalStatus = 1;

	/** 申请理由 */
	private String applyReason = "";

	/** 适用省份 */
	private String province = "";

	/** 适用城市 */
	private String city = "";

	/** 适用区域 */
	private String area = "";

	/** 包含的 群组类型 1：餐厅 2：美食 3：用户 **/
	private short includeGroupType;

	/** 不包含的 群组类型 1：餐厅 2：美食 3：用户 **/
	private short excludeGroupType;

	/** 所有包含的 成员id **/
	private List<Long> includeMembers = new ArrayList<Long>();

	/** 所有不包含的 成员id **/
	private List<Long> excludeMembers = new ArrayList<Long>();

	/** 是否可以分享 1：是 0：否 */
	private short canShare = 0;

	/** 是否可以转让 1：是 0：否 */
	private short canTransfer = 0;

	/** 卡包主键id */
	private long bagId = 0;

	/** 有效时间是否动态, 0:否 1:是 */
	private short isDynamic = 0;

	/** 动态有效时间的有效天数,从领取当天开始计数,若为1,则当天有效 */
	private int effectDays = 1;

	/** 折扣券最高优惠金额 **/
	private long mostOffer = 0;
	
	/** 优惠券使用渠道，0:所有渠道 1:线上订餐 2:mazing pay */
	private short usingChannel = 0;
	
	// 2016-11-24 add by hins
	/**
	 * 我的优惠券列表页是否显示券名，1：是 0：否
	 */
	private short showName = 0;
	
	/**
	 * 优惠券跳转链接，若配置了链接，则优惠券名是超链接，点击可跳转
	 */
	private String url;
	
	// 2016-11-24 add by hins end

	public short getShowName() {
		return showName;
	}

	public void setShowName(short showName) {
		this.showName = showName;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public short getUsingChannel() {
		return usingChannel;
	}

	public void setUsingChannel(short usingChannel) {
		this.usingChannel = usingChannel;
	}


	public long getMostOffer() {
		return mostOffer;
	}

	public void setMostOffer(long mostOffer) {
		this.mostOffer = mostOffer;
	}

	public int getEffectDays() {
		return effectDays;
	}

	public void setEffectDays(int effectDays) {
		this.effectDays = effectDays;
	}

	public short getIsDynamic() {
		return isDynamic;
	}

	public void setIsDynamic(short isDynamic) {
		this.isDynamic = isDynamic;
	}

	public long getBagId() {
		return bagId;
	}

	public void setBagId(long bagId) {
		this.bagId = bagId;
	}

	public short getCanShare() {
		return canShare;
	}

	public void setCanShare(short canShare) {
		this.canShare = canShare;
	}

	public short getCanTransfer() {
		return canTransfer;
	}

	public void setCanTransfer(short canTransfer) {
		this.canTransfer = canTransfer;
	}

	public short getIncludeGroupType() {
		return includeGroupType;
	}

	public void setIncludeGroupType(short includeGroupType) {
		this.includeGroupType = includeGroupType;
	}

	public short getExcludeGroupType() {
		return excludeGroupType;
	}

	public void setExcludeGroupType(short excludeGroupType) {
		this.excludeGroupType = excludeGroupType;
	}

	public List<Long> getIncludeMembers() {
		return includeMembers;
	}

	public void setIncludeMembers(List<Long> includeMembers) {
		this.includeMembers = includeMembers;
	}

	public List<Long> getExcludeMembers() {
		return excludeMembers;
	}

	public void setExcludeMembers(List<Long> excludeMembers) {
		this.excludeMembers = excludeMembers;
	}

	public void setCouponRuleId(long couponRuleId) {
		this.couponRuleId = couponRuleId;
	}

	public long getCouponRuleId() {
		return couponRuleId;
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

	public void setEnoughMoney(long enoughMoney) {
		this.enoughMoney = enoughMoney;
	}

	public long getEnoughMoney() {
		return enoughMoney;
	}

	public void setRegExpression(String regExpression) {
		this.regExpression = regExpression;
	}

	public String getRegExpression() {
		return regExpression;
	}

	public int getGainTotal() {
		return gainTotal;
	}

	public void setGainTotal(int gainTotal) {
		this.gainTotal = gainTotal;
	}

	public void setPhoneSuffix(String phoneSuffix) {
		this.phoneSuffix = phoneSuffix;
	}

	public String getPhoneSuffix() {
		return phoneSuffix;
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

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}

	public Date getModifyTime() {
		return modifyTime;
	}

	public void setAdminId(long adminId) {
		this.adminId = adminId;
	}

	public long getAdminId() {
		return adminId;
	}

	public void setIsEnable(short isEnable) {
		this.isEnable = isEnable;
	}

	public short getIsEnable() {
		return isEnable;
	}

	public void setIsStop(short isStop) {
		this.isStop = isStop;
	}

	public short getIsStop() {
		return isStop;
	}

	public void setVerifyRecePhone(short verifyRecePhone) {
		this.verifyRecePhone = verifyRecePhone;
	}

	public short getVerifyRecePhone() {
		return verifyRecePhone;
	}

	public void setDayUseTotal(short dayUseTotal) {
		this.dayUseTotal = dayUseTotal;
	}

	public short getDayUseTotal() {
		return dayUseTotal;
	}

	public void setHasInclude(short hasInclude) {
		this.hasInclude = hasInclude;
	}

	public short getHasInclude() {
		return hasInclude;
	}

	public void setHasExclude(short hasExclude) {
		this.hasExclude = hasExclude;
	}

	public short getHasExclude() {
		return hasExclude;
	}

	public void setApprover(long approver) {
		this.approver = approver;
	}

	public long getApprover() {
		return approver;
	}

	public void setApprovalTime(Date approvalTime) {
		this.approvalTime = approvalTime;
	}

	public Date getApprovalTime() {
		return approvalTime;
	}

	public void setApprovalComments(String approvalComments) {
		this.approvalComments = approvalComments;
	}

	public String getApprovalComments() {
		return approvalComments;
	}

	public void setApprovalStatus(short approvalStatus) {
		this.approvalStatus = approvalStatus;
	}

	public short getApprovalStatus() {
		return approvalStatus;
	}

	public void setApplyReason(String applyReason) {
		this.applyReason = applyReason;
	}

	public String getApplyReason() {
		return applyReason;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getProvince() {
		return province;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCity() {
		return city;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getArea() {
		return area;
	}
	

	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
