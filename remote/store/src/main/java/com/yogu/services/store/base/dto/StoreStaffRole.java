package com.yogu.services.store.base.dto;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.yogu.core.enums.Role;

/**
 * 门店员工的角色表
 * 
 */
public class StoreStaffRole implements Serializable {

	private static final long serialVersionUID = -3074457346312372308L;

	/** 门店id */
	private long storeId;

	/** 用户id */
	private long uid;
	
	/** 员工姓名 **/
	private String name;

	/** 用户的IM ID */
	private long imId;

	/** 工作人员角色（enum Role）；1：店主、2：店长、4：客服、5：接单、9：配送员 */
	private short role;

	/** 创建时间 */
	private Date createTime;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

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

	public void setImId(long imId) {
		this.imId = imId;
	}

	public long getImId() {
		return imId;
	}

	public void setRole(short role) {
		this.role = role;
	}

	public short getRole() {
		return role;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public Role getRoleType() {
		return Role.valueOf(role);
	}

	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
