package com.yogu.services.store.audit.dto;

import java.io.Serializable;
import java.util.Date;
import java.math.*;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 认证状态表，用于判断该门店是否所有认证信息都已审核通过，开店时插入一条数据，审核通过后删除
 * 
 */
public class StoreAuditStatus implements Serializable {

	private static final long serialVersionUID = -3074457347395675030L;

	/** 门店id */
	private long storeId;

	/** 商户的持有人（对应“用户中心”的用户表ID） */
	private long uid;

	/** 门店名称 */
	private String storeName = "";

	/** 创建时间 */
	private Date createTime;

	public void setStoreId(long storeId) {
		this.storeId = storeId;
	}

	public long getStoreId() {
		return storeId;
	}

	public void setUid(long uid) {
		this.uid = uid;
	}

	public long getUid() {
		return uid;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	public String getStoreName() {
		return storeName;
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
