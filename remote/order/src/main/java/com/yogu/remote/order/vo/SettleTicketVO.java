package com.yogu.remote.order.vo;

import java.util.Date;

public class SettleTicketVO {
	/** 有效开始日期 */
	private Date startDay;

	/** 截止有效期日期 */
	private Date endDay;

	/** 一天内有效开始时间点 */
	private Date startTime;

	/** 一天内有效截止时间点 */
	private Date endTime;

	/** 票规则id */
	private long ticketRuleId;

	/** 票的名称：如 尊贵票、贵宾票 */
	private String ticketName;

	/** 票的数量 */
	private long num;

	/** 票价格 */
	private long price;

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

	public long getTicketRuleId() {
		return ticketRuleId;
	}

	public void setTicketRuleId(long ticketRuleId) {
		this.ticketRuleId = ticketRuleId;
	}

	public String getTicketName() {
		return ticketName;
	}

	public void setTicketName(String ticketName) {
		this.ticketName = ticketName;
	}

	public long getNum() {
		return num;
	}

	public void setNum(long num) {
		this.num = num;
	}

	public long getPrice() {
		return price;
	}

	public void setPrice(long price) {
		this.price = price;
	}

}