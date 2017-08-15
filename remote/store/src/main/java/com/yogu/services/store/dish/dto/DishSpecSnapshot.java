package com.yogu.services.store.dish.dto;

import java.io.Serializable;
import java.util.Date;
import java.math.*;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 美食规格快照表 当主表记录的名字/价格发生变动时 生产快照记录
 * 
 */
public class DishSpecSnapshot implements Serializable {

	private static final long serialVersionUID = -3074457345774272784L;

	/** 规格ID */
	private long specId;

	/** 规格Key */
	private long specKey;

	/** 菜品key */
	private long dishKey;

	/** 门店ID */
	private long storeId;

	/** 规格名称 */
	private String specName;

	/** 货币类型：1-人民币 */
	private short currencyType;

	/** 价格(分) */
	private int price;

	/** 原价(分) 小于等于0表示不显示‘原价’ */
	private int originalPrice;

	/** 每天出售的数量 */
	private int dailyNum;

	/** 规格备注展示的标签 */
	private String supplementLabel = "备注";

	/** 最大可选备注数 若无最大限制 则应该与备注数量相等 */
	private int maxSupplement;

	/** 最小可选备注数 0表示非必选 */
	private int minSupplement;

	/** 创建时间 */
	private Date createTime;

	public void setSpecId(long specId) {
		this.specId = specId;
	}

	public long getSpecId() {
		return specId;
	}

	public void setSpecKey(long specKey) {
		this.specKey = specKey;
	}

	public long getSpecKey() {
		return specKey;
	}

	public void setDishKey(long dishKey) {
		this.dishKey = dishKey;
	}

	public long getDishKey() {
		return dishKey;
	}

	public void setStoreId(long storeId) {
		this.storeId = storeId;
	}

	public long getStoreId() {
		return storeId;
	}

	public void setSpecName(String specName) {
		this.specName = specName;
	}

	public String getSpecName() {
		return specName;
	}

	public void setCurrencyType(short currencyType) {
		this.currencyType = currencyType;
	}

	public short getCurrencyType() {
		return currencyType;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getPrice() {
		return price;
	}

	public void setOriginalPrice(int originalPrice) {
		this.originalPrice = originalPrice;
	}

	public int getOriginalPrice() {
		return originalPrice;
	}

	public void setDailyNum(int dailyNum) {
		this.dailyNum = dailyNum;
	}

	public int getDailyNum() {
		return dailyNum;
	}

	public void setSupplementLabel(String supplementLabel) {
		this.supplementLabel = supplementLabel;
	}

	public String getSupplementLabel() {
		return supplementLabel;
	}

	public void setMaxSupplement(int maxSupplement) {
		this.maxSupplement = maxSupplement;
	}

	public int getMaxSupplement() {
		return maxSupplement;
	}

	public void setMinSupplement(int minSupplement) {
		this.minSupplement = minSupplement;
	}

	public int getMinSupplement() {
		return minSupplement;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
