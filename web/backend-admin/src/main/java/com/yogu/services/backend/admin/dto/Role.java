package com.yogu.services.backend.admin.dto;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 角色定义
 * 
 */
public class Role implements Serializable {

	private static final long serialVersionUID = -3074457345957759807L;

	/** 角色id */
	private int roleId;

	/** 角色名称 */
	private String roleName;

	/** 角色类型 */
	private int roleType = 0;

	/** 备注 */
	private String remarks;

	/** 操作者 */
	private int operator;

	/** 操作时间 */
	private Date lastModify;

	/** 创建时间 */
	private Date createTime;

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

	public int getRoleId() {
		return roleId;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleType(int roleType) {
		this.roleType = roleType;
	}

	public int getRoleType() {
		return roleType;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setOperator(int operator) {
		this.operator = operator;
	}

	public int getOperator() {
		return operator;
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

}
