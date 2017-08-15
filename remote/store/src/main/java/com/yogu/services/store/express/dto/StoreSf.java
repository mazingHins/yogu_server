package com.yogu.services.store.express.dto;

import java.io.Serializable;
import java.util.Date;
import java.math.*;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 餐厅-顺丰关联配置表
 * 
 */
public class StoreSf implements Serializable {

	private static final long serialVersionUID = -3074457345641319108L;

	/** 米星餐厅id */
	private long storeId;

	/** 餐点类型 */
	private short cuisineType;

	/** 该配置的版本id(配置可能发生变化) */
	private int vid = 0;

	/** 配送方需要的店铺编码 */
	private String storeCode = "";

	/** 用户承担费用，单位：分 */
	private int userBear = 0;

	/** 米星承担费用，单位：分 */
	private int mzBear = 0;

	/** 商家承担费用，单位：分 */
	private int merchantBear = 0;

	/** 餐的重量kg */
	private int weight;

	/** 配送距离km */
	private int distance;

	/** 配送时间分钟 */
	private int deliveryTime;

	/** 配送总费用分 */
	private int fee;

	/** 餐点名称描述 */
	private String descrip = "";

	/** 管理员id */
	private long adminId;

	/** 生成时间 */
	private Date recordTime;

	public void setStoreId(long storeId) {
		this.storeId = storeId;
	}

	public long getStoreId() {
		return storeId;
	}

	public void setCuisineType(short cuisineType) {
		this.cuisineType = cuisineType;
	}

	public short getCuisineType() {
		return cuisineType;
	}

	public void setVid(int vid) {
		this.vid = vid;
	}

	public int getVid() {
		return vid;
	}

	public void setStoreCode(String storeCode) {
		this.storeCode = storeCode;
	}

	public String getStoreCode() {
		return storeCode;
	}

	public void setUserBear(int userBear) {
		this.userBear = userBear;
	}

	public int getUserBear() {
		return userBear;
	}

	public void setMzBear(int mzBear) {
		this.mzBear = mzBear;
	}

	public int getMzBear() {
		return mzBear;
	}

	public void setMerchantBear(int merchantBear) {
		this.merchantBear = merchantBear;
	}

	public int getMerchantBear() {
		return merchantBear;
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

	public void setDescrip(String descrip) {
		this.descrip = descrip;
	}

	public String getDescrip() {
		return descrip;
	}

	public void setAdminId(long adminId) {
		this.adminId = adminId;
	}

	public long getAdminId() {
		return adminId;
	}

	public void setRecordTime(Date recordTime) {
		this.recordTime = recordTime;
	}

	public Date getRecordTime() {
		return recordTime;
	}

	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
