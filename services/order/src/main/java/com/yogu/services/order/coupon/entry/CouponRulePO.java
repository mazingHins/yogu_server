package com.yogu.services.order.coupon.entry;

import java.io.Serializable;
import java.util.Date;

/**
 * 优惠券规则表
 * 
 * <pre>
 *     自动生成代码: 表名 mz_coupon_rule, 日期: 2015-12-23
 *     coupon_rule_id <PK>        int(11)
 *     coupon_name          varchar(24)
 *     description          varchar(1024)
 *     coupon_bear_type     tinyint(4)
 *     coupon_type          tinyint(4)
 *     enough_money         int(11)
 *     reg_expression       varchar(256)
 *     gain_total            int(11)
 *     phone_suffix         varchar(4)
 *     start_time           datetime(19)
 *     end_time             datetime(19)
 *     create_time          datetime(19)
 *     modify_time          datetime(19)
 *     admin_id             bigint(20)
 *     is_enable            tinyint(4)
 *     is_stop              tinyint(4)
 *     verify_rece_phone    tinyint(4)
 *     day_use_total        tinyint(4)
 *     has_include          tinyint(4)
 *     has_exclude          tinyint(4)
 *     approver             bigint(20)
 *     approval_time        datetime(19)
 *     approval_comments    varchar(256)
 *     approval_status      tinyint(4)
 *     apply_reason         varchar(256)
 *     province             varchar(8)
 *     city                 varchar(8)
 *     area                 varchar(8)
 *     include_group_type    tinyint(4)
 *     exclude_group_type    tinyint(4)
 *     can_share             tinyint(4)
 *     can_transfer          tinyint(4)
 *     bag_id         		 bigint(20)
 *     is_dynamic			 tinyint(4)
 *     effect_days			 int(11)
 *     most_offer            int(11)
 *     using_channel        tinyint(4)
 *     show_name       		tinyint(4)
 *     url				    varchar(256))
 * </pre>
 */
public class CouponRulePO implements Serializable {

	private static final long serialVersionUID = -3074457346586670427L;

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


}
