package com.yogu.services.user.resource.param;

import javax.ws.rs.DefaultValue;
import javax.ws.rs.FormParam;

import com.yogu.commons.validation.constraints.Length;
import com.yogu.commons.validation.constraints.NotEmpty;
import com.yogu.language.UserMessages;

/**
 * 
 * @Description: 新增/修改地址接收参数
 * @author Hins
 * @date 2015年8月13日 下午2:46:34
 */
public class AddressParam {

	/**
	 * 地址ID 若是新增 收货地址为0
	 */
	@FormParam("addressId")
	private long addressId;

	/**
	 * 收货人
	 */
	@FormParam("contacts")
	@NotEmpty(message = "收货人不能为空", mkey = UserMessages.USER_ADDRESS_CONTACTS_CAN_NOT_BE_EMPTY)
	@Length(max = 24, message = "收货人内容过长", mkey = UserMessages.USER_ADDRESS_CONTACTS_TOO_LONG)
	private String contacts;

	/**
	 * 是否默认收货地址
	 */
	@FormParam("status")
	private short status;

	/**
	 * 联系电话
	 */
	@FormParam("phone")
	@NotEmpty(message = "联系电话不能为空", mkey = UserMessages.USER_ADDRESS_PHONE_CAN_NOT_BE_EMPTY)
	@Length(max = 24, message = "联系电话内容过长", mkey = UserMessages.USER_ADDRESS_PHONE_TOO_LONG)
	private String phone;

	/**
	 * 地址全称（直接保存app的传值）
	 */
	@FormParam("fullAddress")
	@DefaultValue("")
	@Length(max = 128, message = "地址全称过长", mkey = UserMessages.USER_ADDRESS_FULL_ADDRESS_TOO_LONG)
	private String fullAddress;

	public long getAddressId() {
		return addressId;
	}

	public void setAddressId(long addressId) {
		this.addressId = addressId;
	}

	public String getContacts() {
		return contacts;
	}

	public void setContacts(String contacts) {
		this.contacts = contacts;
	}

	public short getStatus() {
		return status;
	}

	public void setStatus(short status) {
		this.status = status;
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

}
