package com.yogu.services.backend.admin.dto;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 角色与菜单对应关系
 * 
 */
public class RoleMenuRelation implements Serializable {

	private static final long serialVersionUID = -3074457347478910473L;

	/** 角色id */
	private int roleId;

	/** 菜单ID */
	private int menuId;

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

	public int getRoleId() {
		return roleId;
	}

	public void setMenuId(int menuId) {
		this.menuId = menuId;
	}

	public int getMenuId() {
		return menuId;
	}

	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
