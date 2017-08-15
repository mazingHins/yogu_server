package com.yogu.services.backend.admin.entry;

import java.io.Serializable;


/**
 * 角色与菜单对应关系
 * 
 * <pre>
 *     自动生成代码: 表名 mz_role_menu_relation, 日期: 2015-08-03
 *     role_id <PK>     int(11)
 *     menu_id <PK>     int(11)
 * </pre>
 */
public class RoleMenuRelationPO implements Serializable {

	private static final long serialVersionUID = -3074457344015712009L;

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

}
