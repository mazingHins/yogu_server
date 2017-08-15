package com.yogu.remote.user.dto;

import java.io.Serializable;
import java.util.Date;

public class UserAddress implements Serializable {
	
	private static final long serialVersionUID = -4755098685444279782L;
	
	/** 地址id */
	private long addressId;

	/** 用户id */
	private long uid;

	/** 送餐地址全称（直接保存app的传值） */
	private String fullAddress = "";

	/** 收货人名称 */
	private String contacts;

	/** 联系电话 */
	private String phone;

	/** 状态：1-默认地址；2-非默认 */
	private short status = 2;

	/** 创建时间 */
	private Date createTime;

	public long getAddressId() {
		return addressId;
	}

	public void setAddressId(long addressId) {
		this.addressId = addressId;
	}

	public long getUid() {
		return uid;
	}

	public void setUid(long uid) {
		this.uid = uid;
	}

	public String getFullAddress() {
		return fullAddress;
	}

	public void setFullAddress(String fullAddress) {
		this.fullAddress = fullAddress;
	}

	public String getContacts() {
		return contacts;
	}

	public void setContacts(String contacts) {
		this.contacts = contacts;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
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
	
}
