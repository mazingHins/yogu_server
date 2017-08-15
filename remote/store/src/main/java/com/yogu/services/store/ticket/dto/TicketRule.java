package com.yogu.services.store.ticket.dto;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * ticket 基础信息表
 * 
 */
public class TicketRule implements Serializable {

	private static final long serialVersionUID = -3074457345869813143L;

	/** ticket规则id */
	private long ticketRuleId;

	/** 票名 */
	private String ticketName = "";

	/** 英文名 */
	private String ticketEnName = "";

	/** 现价，单位分 */
	private long price = 0;

	/** 原价，单位分 */
	private long originalPrice = 0;

	/** 库存剩余 */
	private int surplus = 0;

	/** 已售出数量 */
	private int soldNum = 0;

	/** 单次限购数量 为0表示没有限制 */
	private int limitBuyCount = 0;

	/** 票的图片信息 */
	private String img;

	/** 是否可分享  1:是 0:否 */
	private short canShare = 0;

	/** 是否可转让赠送  1:是 0:否 */
	private short canTransfer = 0;

	/** 是否启用  1:是 0:否 */
	private short isEnable = 1;

	/** 是否终止售卖  1:是 0:否 */
	private short isStop = 0;

	/** 是否为第三方平台的票  1:是 0:否 */
	private short isThird = 0;

	/** 管理员id */
	private long adminId;

	/** thirdCode */
	private String thirdCode = "";

	/** 创建时间 */
	private Date createTime;

	/** 修改时间 */
	private Date updateTime;

	/** 有效开始日期 */
	private Date startDay;

	/** 截止有效期日期 */
	private Date endDay;

	/** 一天内有效开始时间点 */
	private Date startTime;

	/** 一天内有效截止时间点 */
	private Date endTime;

	public void setTicketRuleId(long ticketRuleId) {
		this.ticketRuleId = ticketRuleId;
	}

	public long getTicketRuleId() {
		return ticketRuleId;
	}

	public void setTicketName(String ticketName) {
		this.ticketName = ticketName;
	}

	public String getTicketName() {
		return ticketName;
	}

	public void setTicketEnName(String ticketEnName) {
		this.ticketEnName = ticketEnName;
	}

	public String getTicketEnName() {
		return ticketEnName;
	}

	public void setPrice(long price) {
		this.price = price;
	}

	public long getPrice() {
		return price;
	}

	public void setOriginalPrice(long originalPrice) {
		this.originalPrice = originalPrice;
	}

	public long getOriginalPrice() {
		return originalPrice;
	}

	public void setSurplus(int surplus) {
		this.surplus = surplus;
	}

	public int getSurplus() {
		return surplus;
	}

	public void setSoldNum(int soldNum) {
		this.soldNum = soldNum;
	}

	public int getSoldNum() {
		return soldNum;
	}

	public void setLimitBuyCount(int limitBuyCount) {
		this.limitBuyCount = limitBuyCount;
	}

	public int getLimitBuyCount() {
		return limitBuyCount;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public String getImg() {
		return img;
	}

	public void setCanShare(short canShare) {
		this.canShare = canShare;
	}

	public short getCanShare() {
		return canShare;
	}

	public void setCanTransfer(short canTransfer) {
		this.canTransfer = canTransfer;
	}

	public short getCanTransfer() {
		return canTransfer;
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

	public void setIsThird(short isThird) {
		this.isThird = isThird;
	}

	public short getIsThird() {
		return isThird;
	}

	public void setAdminId(long adminId) {
		this.adminId = adminId;
	}

	public long getAdminId() {
		return adminId;
	}

	public void setThirdCode(String thirdCode) {
		this.thirdCode = thirdCode;
	}

	public String getThirdCode() {
		return thirdCode;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setStartDay(Date startDay) {
		this.startDay = startDay;
	}

	public Date getStartDay() {
		return startDay;
	}

	public void setEndDay(Date endDay) {
		this.endDay = endDay;
	}

	public Date getEndDay() {
		return endDay;
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

	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
