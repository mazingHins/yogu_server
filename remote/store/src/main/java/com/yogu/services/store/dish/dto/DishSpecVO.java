package com.yogu.services.store.dish.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.yogu.services.store.dish.dto.SpecSupplement;

/**
 * 菜品规格 客户端展示VO对象
 * 
 * @author sky 2016/02/22
 *
 */
public class DishSpecVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7966110611050795782L;

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

	/** 剩余数量 **/
	private int surplus;
	/** 已售数量 **/
	private int soldNum;
	
	/** 规格备注的提示信息 **/
	private String supplementComment;

	/** 备注列表 **/
	private List<SpecSupplement> dishSpecSupplementList = new ArrayList<>();// 若无备注, 返回size为0的空数组

	public long getSpecId() {
		return specId;
	}

	public void setSpecId(long specId) {
		this.specId = specId;
	}

	public long getSpecKey() {
		return specKey;
	}

	public void setSpecKey(long specKey) {
		this.specKey = specKey;
	}

	public long getDishKey() {
		return dishKey;
	}

	public void setDishKey(long dishKey) {
		this.dishKey = dishKey;
	}

	public long getStoreId() {
		return storeId;
	}

	public void setStoreId(long storeId) {
		this.storeId = storeId;
	}

	public String getSpecName() {
		return specName;
	}

	public void setSpecName(String specName) {
		this.specName = specName;
	}

	public short getCurrencyType() {
		return currencyType;
	}

	public void setCurrencyType(short currencyType) {
		this.currencyType = currencyType;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getOriginalPrice() {
		return originalPrice;
	}

	public void setOriginalPrice(int originalPrice) {
		this.originalPrice = originalPrice;
	}

	public int getDailyNum() {
		return dailyNum;
	}

	public void setDailyNum(int dailyNum) {
		this.dailyNum = dailyNum;
	}

	public String getSupplementLabel() {
		return supplementLabel;
	}

	public void setSupplementLabel(String supplementLabel) {
		this.supplementLabel = supplementLabel;
	}

	public int getMaxSupplement() {
		return maxSupplement;
	}

	public void setMaxSupplement(int maxSupplement) {
		this.maxSupplement = maxSupplement;
	}

	public int getMinSupplement() {
		return minSupplement;
	}

	public void setMinSupplement(int minSupplement) {
		this.minSupplement = minSupplement;
	}

	public int getSurplus() {
		return surplus;
	}

	public void setSurplus(int surplus) {
		this.surplus = surplus;
	}

	public int getSoldNum() {
		return soldNum;
	}

	public void setSoldNum(int soldNum) {
		this.soldNum = soldNum;
	}

	public List<SpecSupplement> getDishSpecSupplementList() {
		return dishSpecSupplementList;
	}

	public void setDishSpecSupplementList(List<SpecSupplement> dishSpecSupplementList) {
		this.dishSpecSupplementList = dishSpecSupplementList;
	}

	public String getSupplementComment() {
		return supplementComment;
	}

	public void setSupplementComment(String supplementComment) {
		this.supplementComment = supplementComment;
	}

}
