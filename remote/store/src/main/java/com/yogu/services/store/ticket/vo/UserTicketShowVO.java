package com.yogu.services.store.ticket.vo;

import java.io.Serializable;
import java.util.Date;

/**
 * 用于页面显示 的ticket VO
 * 
 * @author sky
 *
 */
public class UserTicketShowVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 232142599700194080L;

	private String actName;// 活动名称
	private String actTime;// 活动时间
	private String actAddress;// 活动地址
	private String img;// ticket封面
	private String ticketName;// ticket名称
	private String ticketNo;// ticket编号,码
	private long ticketId;// 票id
	private long ticketRuleId;// 票规则id
	private long orderNo;// 关联订单号
	private String ticketTime;// 票时间

	/** 用户id没被买时为0 */
	private long uid = 0;

	private long actId;

	/** 现价 */
	private long price;

	/** 原价 */
	private long originalPrice;

	/** 票的状态 0：待购买 1：已购买 2：已使用、已核销 3：已过期 4：退款中 5： 已退款 6：购买占用中 */
	private short status;

	/** 创建时间 */
	private Date createTime;

	/** 核销人 */
	private long checkUid;

	/** 购买时间 */
	private Date addTime;

	/** 使用、核销时间 */
	private Date useTime;

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
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

	public long getUid() {
		return uid;
	}

	public void setUid(long uid) {
		this.uid = uid;
	}

	public long getActId() {
		return actId;
	}

	public void setActId(long actId) {
		this.actId = actId;
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

	public long getCheckUid() {
		return checkUid;
	}

	public void setCheckUid(long checkUid) {
		this.checkUid = checkUid;
	}

	public String getTicketTime() {
		return ticketTime;
	}

	public void setTicketTime(String ticketTime) {
		this.ticketTime = ticketTime;
	}

	public String getActName() {
		return actName;
	}

	public void setActName(String actName) {
		this.actName = actName;
	}

	public String getActTime() {
		return actTime;
	}

	public void setActTime(String actTime) {
		this.actTime = actTime;
	}

	public String getActAddress() {
		return actAddress;
	}

	public void setActAddress(String actAddress) {
		this.actAddress = actAddress;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public String getTicketName() {
		return ticketName;
	}

	public void setTicketName(String ticketName) {
		this.ticketName = ticketName;
	}

	public String getTicketNo() {
		return ticketNo;
	}

	public void setTicketNo(String ticketNo) {
		this.ticketNo = ticketNo;
	}

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

	public long getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(long orderNo) {
		this.orderNo = orderNo;
	}

}
