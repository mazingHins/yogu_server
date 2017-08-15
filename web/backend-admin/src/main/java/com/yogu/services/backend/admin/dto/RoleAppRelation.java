package com.yogu.services.backend.admin.dto;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 记录角色对应用系统的访问关系
 * 
 */
public class RoleAppRelation implements Serializable {

	private static final long serialVersionUID = -3074457345308367981L;

	/** 角色id */
	private int roleId;

	/** 应用系统id */
	private int appId;

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

	public int getRoleId() {
		return roleId;
	}

	public void setAppId(int appId) {
		this.appId = appId;
	}

	public int getAppId() {
		return appId;
	}

	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
