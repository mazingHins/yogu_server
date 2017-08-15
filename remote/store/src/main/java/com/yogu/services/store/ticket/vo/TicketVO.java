package com.yogu.services.store.ticket.vo;

import java.io.Serializable;
import java.util.Date;

public class TicketVO implements Serializable {

	private static final long serialVersionUID = -5545406579738039472L;

	/** 票主键id */
	private long ticketId;

	/** ticket规则id */
	private long ticketRuleId;

	/** 用户id没被买时为0 */
	private long uid = 0;

	/** 票码 */
	private String ticketNo;

	/** 票名 */
	private String ticketName;

	/** 英文名 */
	private String ticketEnName = "";

	/** 现价 */
	private long price;

	/** 原价 */
	private long originalPrice;

	/** 票的状态 0：待购买 1=占用中，2=可使用，3=退款中，4=已核销，5=已过期，6=已退款 */
	private short status;

	/** 描述信息 */
	private String description = "";

	/** 是否有效 1:是 0:否 */
	private short isEnable = 0;

	/** 订单号 */
	private long orderNo = 0;

	/** 餐厅id */
	private long storeId = 0;

	/** 活动id */
	private long actId = 0;

	/** 有效起始日期 */
	private Date startDay;

	/** 有效截止日期 */
	private Date endDay;

	/** 一天内可使用开始时间点 */
	private Date startTime;

	/** 一天内可使用截止时间点 */
	private Date endTime;

	/** 创建时间 */
	private Date createTime;

	/** 批次号 */
	private int batchNo;

	/** 转让状态 0:未转让 1:转让中 2:已转让 */
	private short transferStatus = 0;

	/** 第三方票码 */
	private String thirdTicketNo;

	/** 是否第三方票 1：是 0：否 */
	private short isThird = 0;

	/** 第三方票平台代号 */
	private String thirdCode = "";

	/** 核销人 */
	private long checkUid;

	/** 购买时间 */
	private Date addTime;

	/** 使用、核销时间 */
	private Date useTime;

	public long getTicketId() {
		return ticketId;
	}

	public void setTicketId(long ticketId) {
		this.ticketId = ticketId;
	}

	public long getTicketRuleId() {
		return ticketRuleId;
	}

	public void setTicketRuleId(long ticketRuleId) {
		this.ticketRuleId = ticketRuleId;
	}

	public long getUid() {
		return uid;
	}

	public void setUid(long uid) {
		this.uid = uid;
	}

	public String getTicketNo() {
		return ticketNo;
	}

	public void setTicketNo(String ticketNo) {
		this.ticketNo = ticketNo;
	}

	public String getTicketName() {
		return ticketName;
	}

	public void setTicketName(String ticketName) {
		this.ticketName = ticketName;
	}

	public String getTicketEnName() {
		return ticketEnName;
	}

	public void setTicketEnName(String ticketEnName) {
		this.ticketEnName = ticketEnName;
	}

	public long getPrice() {
		return price;
	}

	public void setPrice(long price) {
		this.price = price;
	}

	public long getOriginalPrice() {
		return originalPrice;
	}

	public void setOriginalPrice(long originalPrice) {
		this.originalPrice = originalPrice;
	}

	public short getStatus() {
		return status;
	}

	public void setStatus(short status) {
		this.status = status;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public short getIsEnable() {
		return isEnable;
	}

	public void setIsEnable(short isEnable) {
		this.isEnable = isEnable;
	}

	public long getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(long orderNo) {
		this.orderNo = orderNo;
	}

	public long getStoreId() {
		return storeId;
	}

	public void setStoreId(long storeId) {
		this.storeId = storeId;
	}

	public long getActId() {
		return actId;
	}

	public void setActId(long actId) {
		this.actId = actId;
	}

	public Date getStartDay() {
		return startDay;
	}

	public void setStartDay(Date startDay) {
		this.startDay = startDay;
	}

	public Date getEndDay() {
		return endDay;
	}

	public void setEndDay(Date endDay) {
		this.endDay = endDay;
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

	public int getBatchNo() {
		return batchNo;
	}

	public void setBatchNo(int batchNo) {
		this.batchNo = batchNo;
	}

	public short getTransferStatus() {
		return transferStatus;
	}

	public void setTransferStatus(short transferStatus) {
		this.transferStatus = transferStatus;
	}

	public String getThirdTicketNo() {
		return thirdTicketNo;
	}

	public void setThirdTicketNo(String thirdTicketNo) {
		this.thirdTicketNo = thirdTicketNo;
	}

	public short getIsThird() {
		return isThird;
	}

	public void setIsThird(short isThird) {
		this.isThird = isThird;
	}

	public String getThirdCode() {
		return thirdCode;
	}

	public void setThirdCode(String thirdCode) {
		this.thirdCode = thirdCode;
	}

	public long getCheckUid() {
		return checkUid;
	}

	public void setCheckUid(long checkUid) {
		this.checkUid = checkUid;
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
	
	
}
