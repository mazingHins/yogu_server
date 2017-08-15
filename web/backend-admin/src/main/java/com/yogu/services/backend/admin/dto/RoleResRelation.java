package com.yogu.services.backend.admin.dto;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 角色与资源对应关系
 * 
 */
public class RoleResRelation implements Serializable {

	private static final long serialVersionUID = -3074457346105046919L;

	/** 角色id */
	private int roleId;

	/** 资源ID */
	private int resId;

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

	public int getRoleId() {
		return roleId;
	}

	public void setResId(int resId) {
		this.resId = resId;
	}

	public int getResId() {
		return resId;
	}

	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
