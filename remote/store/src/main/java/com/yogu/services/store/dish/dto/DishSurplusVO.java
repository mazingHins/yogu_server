package com.yogu.services.store.dish.dto;

import java.io.Serializable;

public class DishSurplusVO implements Serializable {

	private static final long serialVersionUID = 6325437450036390633L;
	
	/**
	 * 美食ID
	 */
	private long dishId;

	/**
	 * 对象ID-为了兼容旧版本，对象ID=美食ID，一并返回
	 */
	private long objectId;

	/**
	 * 剩余库存数量
	 */
	private int surplus;

	/**
	 * 库存不足显示内容
	 */
	private String surplusContent;

	/**
	 * 规格key
	 * 2016/2/23 by ten
	 */
	private long specKey;
	
	public long getDishId() {
		return dishId;
	}

	public void setDishId(long dishId) {
		this.dishId = dishId;
	}

	public long getObjectId() {
		return objectId;
	}

	public void setObjectId(long objectId) {
		this.objectId = objectId;
	}

	public int getSurplus() {
		return surplus;
	}

	public void setSurplus(int surplus) {
		this.surplus = surplus;
	}

	public String getSurplusContent() {
		return surplusContent;
	}

	public void setSurplusContent(String surplusContent) {
		this.surplusContent = surplusContent;
	}

	public long getSpecKey() {
		return specKey;
	}

	public void setSpecKey(long specKey) {
		this.specKey = specKey;
	}
}
