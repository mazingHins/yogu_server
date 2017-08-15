package com.yogu.services.backend.admin.dto;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;
import java.util.Date;

/**
 * 管理员名单
 * 
 */
public class AdminAccount implements Serializable {

	/**
	 * 用戶账号状态 正常
	 */
	public static final short NOMAL = 1;
	/**
	 * 用户账号状态 冻结
	 */
	public static final short LOCKED = 2;

	private static final long serialVersionUID = -3074457346067420474L;

//	@FormParam("uid")
	/** 用户ID */
	private long uid;

//	@FormParam("realname")
	/** 真实姓名 */
	private String realname = "";

	/** 操作员ID */
	private int operator;

	/** 管理员状态，1-正常2-冻结 */
	private short status = 1;

//	@FormParam("mobileNo")
	/** 移动电话 */
	private String mobileNo = "";

	/** 是否是基本用户，0-不是，1-是不可以删除 */
	private short defaultData = 0;

	/** 修改时间 */
	private Date lastModify;

	/** 创建时间 */
	private Date createTime;

//	@FormParam("roleId")
	/**
	 * 角色ID
	 */
	private int roleId;

	/**
	 * 角色名称
	 */
	private String roleName;

	public void setUid(long uid) {
		this.uid = uid;
	}

	public long getUid() {
		return uid;
	}

	public void setRealname(String realname) {
		this.realname = realname;
	}

	public String getRealname() {
		return realname;
	}

	public void setOperator(int operator) {
		this.operator = operator;
	}

	public int getOperator() {
		return operator;
	}

	public void setStatus(short status) {
		this.status = status;
	}

	public short getStatus() {
		return status;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	public String getMobileNo() {
		return mobileNo;
	}

	public void setDefaultData(short defaultData) {
		this.defaultData = defaultData;
	}

	public short getDefaultData() {
		return defaultData;
	}

	public void setLastModify(Date lastModify) {
		this.lastModify = lastModify;
	}

	public Date getLastModify() {
		return lastModify;
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

	public int getRoleId() {
		return roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
}
