package com.yogu.services.store.express.dto;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 顺丰餐点类型的配送费用相关信息
 * 
 */
public class SfFeeConfig implements Serializable {

	private static final long serialVersionUID = -3074457347251958333L;

	/** 餐点类型 */
	private short cuisineType;

	/** 餐的重量 */
	private int weight;

	/** 配送距离 */
	private int distance;

	/** 配送时间分钟 */
	private int deliveryTime;

	/** 配送费用分 */
	private int fee;

	/** 该类型对应的店铺编码(目前4种费用，对应4种编码，所有餐厅都是这4种编码) */
	private String storeCode;

	/** 餐点名称描述 */
	private String descrip = "";

	public void setCuisineType(short cuisineType) {
		this.cuisineType = cuisineType;
	}

	public short getCuisineType() {
		return cuisineType;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

	public int getWeight() {
		return weight;
	}

	public void setDistance(int distance) {
		this.distance = distance;
	}

	public int getDistance() {
		return distance;
	}

	public void setDeliveryTime(int deliveryTime) {
		this.deliveryTime = deliveryTime;
	}

	public int getDeliveryTime() {
		return deliveryTime;
	}

	public void setFee(int fee) {
		this.fee = fee;
	}

	public int getFee() {
		return fee;
	}

	public void setStoreCode(String storeCode) {
		this.storeCode = storeCode;
	}

	public String getStoreCode() {
		return storeCode;
	}

	public void setDescrip(String descrip) {
		this.descrip = descrip;
	}

	public String getDescrip() {
		return descrip;
	}

	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
