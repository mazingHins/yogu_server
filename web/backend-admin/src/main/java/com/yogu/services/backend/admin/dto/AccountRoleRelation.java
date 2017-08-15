package com.yogu.services.backend.admin.dto;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 管理员与角色对应关系
 * 
 */
public class AccountRoleRelation implements Serializable {

	private static final long serialVersionUID = -3074457346469592537L;

	/** 管理员id */
	private long uid;

	/** 角色id */
	private int roleId;

	public void setUid(long uid) {
		this.uid = uid;
	}

	public long getUid() {
		return uid;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

	public int getRoleId() {
		return roleId;
	}

	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
