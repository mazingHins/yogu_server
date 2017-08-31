package com.yogu.services.store.business.entry;

import java.io.Serializable;
import java.util.Date;

/**
 * 店铺信息
 *
 */
public class StorePO implements Serializable {

	private static final long serialVersionUID = -4789801549822198924L;

	/** 店铺id */
	private long storeId;
	
	/** 店铺名称 */
	private String storeName;
	
	/** 店铺持有人id */
	private long uid;
	
	/** 门店地址 */
	private String phone;
	
	/** 门店电话，可能多个，英文逗号隔开 */
	private String fullAddress;
	
	/** 状态:1-正常;2-下架 */
	private short status;
	
	/** 创建时间 */
	private Date createTime;
	
	/** 更新时间 */
	private Date updateTime;

	public long getStoreId() {
		return storeId;
	}

	public void setStoreId(long storeId) {
		this.storeId = storeId;
	}

	public String getStoreName() {
		return storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	public long getUid() {
		return uid;
	}

	public void setUid(long uid) {
		this.uid = uid;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getFullAddress() {
		return fullAddress;
	}

	public void setFullAddress(String fullAddress) {
		this.fullAddress = fullAddress;
	}

	public short getStatus() {
		return status;
	}

	public void setStatus(short status) {
		this.status = status;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	
}
