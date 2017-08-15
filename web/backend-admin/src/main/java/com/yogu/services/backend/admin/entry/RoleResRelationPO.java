package com.yogu.services.backend.admin.entry;

import java.io.Serializable;


/**
 * 角色与资源对应关系
 * 
 * <pre>
 *     自动生成代码: 表名 mz_role_res_relation, 日期: 2015-12-07
 *     role_id <PK>     int(11)
 *     res_id <PK>      int(11)
 * </pre>
 */
public class RoleResRelationPO implements Serializable {

	private static final long serialVersionUID = -3074457345218319819L;

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

}
