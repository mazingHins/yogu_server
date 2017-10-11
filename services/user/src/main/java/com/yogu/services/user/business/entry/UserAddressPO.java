package com.yogu.services.user.business.entry;

import java.io.Serializable;
import java.util.Date;


/**
 * 用户收货地址
 * 
 * <pre>
 *     自动生成代码: 表名 mz_user_address, 日期: 2015-08-24
 *     address_id <PK>        bigint(20)
 *     uid              bigint(20)
 *     country_code     varchar(16)
 *     province_code    varchar(16)
 *     city_code        varchar(16)
 *     district_code    varchar(16)
 *     lat              double(22,31)
 *     lng              double(22,31)
 *     name             varchar(40)
 *     full_address        varchar(128)
 *     detail           varchar(40)
 *     contacts         varchar(24)
 *     phone            varchar(24)
 *     remark           varchar(40)
 *     status           tinyint(4)
 *     create_time      datetime(19)
 * </pre>
 */
public class UserAddressPO implements Serializable {

	private static final long serialVersionUID = -3074457345243033334L;

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

	public void setAddressId(long addressId) {
		this.addressId = addressId;
	}

	public long getAddressId() {
		return addressId;
	}

	public void setUid(long uid) {
		this.uid = uid;
	}

	public long getUid() {
		return uid;
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

	public void setStatus(short status) {
		this.status = status;
	}

	public short getStatus() {
		return status;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getCreateTime() {
		return createTime;
	}

}
