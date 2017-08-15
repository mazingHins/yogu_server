package com.yogu.services.store.dish.dto;

import java.io.Serializable;
import java.util.Date;
import java.math.*;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 规格备注表
 * 
 */
public class SpecSupplement implements Serializable {

	private static final long serialVersionUID = -3074457345045015941L;

	/** 规格备注ID */
	private long supplementId;

	/** 规格备注名称 */
	private String supplementName;

	/** 规格Key */
	private long specKey;

	/** 门店ID */
	private long storeId;

	/** 菜品key */
	private long dishKey;

	/** 创建时间 */
	private Date createTime;

	public void setSupplementId(long supplementId) {
		this.supplementId = supplementId;
	}

	public long getSupplementId() {
		return supplementId;
	}

	public void setSupplementName(String supplementName) {
		this.supplementName = supplementName;
	}

	public String getSupplementName() {
		return supplementName;
	}

	public void setSpecKey(long specKey) {
		this.specKey = specKey;
	}

	public long getSpecKey() {
		return specKey;
	}

	public void setStoreId(long storeId) {
		this.storeId = storeId;
	}

	public long getStoreId() {
		return storeId;
	}

	public void setDishKey(long dishKey) {
		this.dishKey = dishKey;
	}

	public long getDishKey() {
		return dishKey;
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
