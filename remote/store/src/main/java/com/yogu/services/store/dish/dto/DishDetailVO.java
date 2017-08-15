package com.yogu.services.store.dish.dto;

import java.io.Serializable;

/**
 * 结算菜品信息VO
 * 
 * @author Hins
 * @date 2015年7月28日 上午10:48:45
 */
public class DishDetailVO implements Serializable {

	private static final long serialVersionUID = 7655093853661463440L;

	/** 菜品id */
	private long objectId;

	/** 菜品名称 */
	private String name;

	/** 菜品图片 */
	private String topicImg;

	/** 美食具体显示内容（暂时是菜品规格） */
	private String content;

	/** 购买数量 */
	private int number;

	/** 菜品购买单价 */
	private long unitFee;

	/** 菜品购买总价 */
	private long totalFee;

	/** 菜品规格 */
	private String spec;

	/** 菜品状态；1：正常、2：下架 */
	private short status = 2;

	/** 剩余库存数量 */
	private int surplus;

	/** 错误提示语。库存不足/菜品下架 */
	private String errorContent;

	/**
	 * 规格key
	 * 2016/2/23 by ten
	 */
	private long specKey;

	/**
	 * 规格名称
	 * 2016/2/23 by ten
	 */
	private String specName;

	/**
	 * 备注名称
	 * 若无则传空字符串""
	 * 2016/2/23 by ten
	 */
	private String supplementName = "";

	public long getObjectId() {
		return objectId;
	}

	public void setObjectId(long objectId) {
		this.objectId = objectId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTopicImg() {
		return topicImg;
	}

	public void setTopicImg(String topicImg) {
		this.topicImg = topicImg;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public long getUnitFee() {
		return unitFee;
	}

	public void setUnitFee(long unitFee) {
		this.unitFee = unitFee;
	}

	public long getTotalFee() {
		return totalFee;
	}

	public void setTotalFee(long totalFee) {
		this.totalFee = totalFee;
	}

	public String getSpec() {
		return spec;
	}

	public void setSpec(String spec) {
		this.spec = spec;
	}

	public short getStatus() {
		return status;
	}

	public void setStatus(short status) {
		this.status = status;
	}

	public int getSurplus() {
		return surplus;
	}

	public void setSurplus(int surplus) {
		this.surplus = surplus;
	}

	public String getErrorContent() {
		return errorContent;
	}

	public void setErrorContent(String errorContent) {
		this.errorContent = errorContent;
	}

	public String getSpecName() {
		return specName;
	}

	public void setSpecName(String specName) {
		this.specName = specName;
	}

	public String getSupplementName() {
		return supplementName;
	}

	public void setSupplementName(String supplementName) {
		this.supplementName = supplementName;
	}

	public long getSpecKey() {
		return specKey;
	}

	public void setSpecKey(long specKey) {
		this.specKey = specKey;
	}
}
