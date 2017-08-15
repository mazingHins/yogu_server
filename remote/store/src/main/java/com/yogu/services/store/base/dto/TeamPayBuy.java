package com.yogu.services.store.base.dto;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 团拼购买关联表
 * 
 */
public class TeamPayBuy implements Serializable {

	private static final long serialVersionUID = -3074457343614823208L;

	/** 购买id */
	private long buyId;

	/** 团拼id */
	private long teamPayId;

	/** 商店id */
	private long storeId;

	/** 商品id */
	private long goodsId;

	/** 参团人数 */
	private int members;

	/** 每人参团次数限制 */
	private short memberLimit;

	/** 参团价(单位：分) */
	private int price;

	/**
	 * 团拼完成状态(1-完成 0-未完成)
	 */
	private short status;

	/** 过期方式(1-指定过期时间 2-动态过期时间) */
	private short expireTimeMode;

	/** 过期时间值 */
	private int expireTimeValue;

	/** 团的超时时间(单位：秒) */
	private int overTime;

	/** 活动开始时间 */
	private Date startTime;

	/** 活动结束时间 */
	private Date endTime;

	/** 购买时间 */
	private Date createTime;

	/**
	 * 商品
	 */
	private TeamPayGoods teamPayGoods;

	public void setBuyId(long buyId) {
		this.buyId = buyId;
	}

	public long getBuyId() {
		return buyId;
	}

	public void setTeamPayId(long teamPayId) {
		this.teamPayId = teamPayId;
	}

	public long getTeamPayId() {
		return teamPayId;
	}

	public void setStoreId(long storeId) {
		this.storeId = storeId;
	}

	public long getStoreId() {
		return storeId;
	}

	public void setGoodsId(long goodsId) {
		this.goodsId = goodsId;
	}

	public long getGoodsId() {
		return goodsId;
	}

	public void setMembers(int members) {
		this.members = members;
	}

	public int getMembers() {
		return members;
	}

	public void setMemberLimit(short memberLimit) {
		this.memberLimit = memberLimit;
	}

	public short getMemberLimit() {
		return memberLimit;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getPrice() {
		return price;
	}

	public short getStatus() {
		return status;
	}

	public void setStatus(short status) {
		this.status = status;
	}

	public void setExpireTimeMode(short expireTimeMode) {
		this.expireTimeMode = expireTimeMode;
	}

	public short getExpireTimeMode() {
		return expireTimeMode;
	}

	public void setExpireTimeValue(int expireTimeValue) {
		this.expireTimeValue = expireTimeValue;
	}

	public int getExpireTimeValue() {
		return expireTimeValue;
	}

	public int getOverTime() {
		return overTime;
	}

	public void setOverTime(int overTime) {
		this.overTime = overTime;
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

	public TeamPayGoods getTeamPayGoods() {
		return teamPayGoods;
	}

	public void setTeamPayGoods(TeamPayGoods teamPayGoods) {
		this.teamPayGoods = teamPayGoods;
	}

	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
