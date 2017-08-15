package com.yogu.services.store.ticket.vo;

import java.util.Date;

import javax.ws.rs.DefaultValue;
import javax.ws.rs.FormParam;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.format.annotation.DateTimeFormat;

import com.yogu.commons.utils.DateUtils;

/**
 * 后台提交的 ticket 表单对象
 * 
 * @author sky
 *
 */
public class TicketRuleForm {

	/** ticket规则id */
	private long ticketRuleId;

	/** 票名 */
	@FormParam("ticketName")
	private String ticketName = "";// 门票中文名

	/** 英文名 */
	@FormParam("ticketEnName")
	private String ticketEnName = "";// 门片英文名

	/** 现价，单位分 */
	@FormParam("price")
	@DefaultValue("0")
	private long price = 0;// 现价

	/** 原价，单位分 */
	@FormParam("originalPrice")
	@DefaultValue("0")
	private long originalPrice = 0;// 原价

	/** 单次限购数量 为0表示没有限制 */
	@FormParam("limitBuyCount")
	@DefaultValue("0")
	private int limitBuyCount = 0;// 限购数量

	/**
	 * 创建数量
	 */
	@FormParam("createTotal")
	@DefaultValue("0")
	private int createTotal;// 库存

	/** 票的图片信息 */
	@FormParam("img")
	private String img;

	/** 是否可分享 1:是 0:否 */
	@FormParam("canShare")
	private short canShare = 0;

	/** 是否可转让赠送 1:是 0:否 */
	@FormParam("canTransfer")
	private short canTransfer = 0;

	/** 是否为第三方平台的票 1:是 0:否 */
	private short isThird = 0;

	/** 管理员id */
	@FormParam("adminId")
	private long adminId;// 管理员id

	/** thirdCode */
	private String thirdCode;

	/** 有效开始日期 */
	@FormParam("startDay")
	@DateTimeFormat(pattern = DateUtils.YYYY_MM_DD_HH_mm_ss)
	private String start;// 活动 开始日期， 包含开始时间点

	/** 截止有效期日期 */
	@FormParam("endDay")
	@DateTimeFormat(pattern = DateUtils.YYYY_MM_DD_HH_mm_ss)
	private String end;// 活动结束日期, 包含结束时间点

	private Date startDay;
	
	private Date endDay;

	public String getStart() {
		return start;
	}

	public void setStart(String start) {
		this.start = start;
	}

	public String getEnd() {
		return end;
	}

	public void setEnd(String end) {
		this.end = end;
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

	public String getTicketEnName() {
		return ticketEnName;
	}

	public void setTicketEnName(String ticketEnName) {
		this.ticketEnName = ticketEnName;
	}

	public int getCreateTotal() {
		return createTotal;
	}

	public void setCreateTotal(int createTotal) {
		this.createTotal = createTotal;
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

	public int getLimitBuyCount() {
		return limitBuyCount;
	}

	public void setLimitBuyCount(int limitBuyCount) {
		this.limitBuyCount = limitBuyCount;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
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

	public short getIsThird() {
		return isThird;
	}

	public void setIsThird(short isThird) {
		this.isThird = isThird;
	}

	public long getAdminId() {
		return adminId;
	}

	public void setAdminId(long adminId) {
		this.adminId = adminId;
	}

	public String getThirdCode() {
		return thirdCode;
	}

	public void setThirdCode(String thirdCode) {
		this.thirdCode = thirdCode;
	}

	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
