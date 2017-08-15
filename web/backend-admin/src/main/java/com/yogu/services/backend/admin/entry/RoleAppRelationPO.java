package com.yogu.services.backend.admin.entry;

import java.io.Serializable;


/**
 * 记录角色对应用系统的访问关系
 * 
 * <pre>
 *     自动生成代码: 表名 mz_role_app_relation, 日期: 2015-08-03
 *     role_id <PK>     int(11)
 *     app_id <PK>      int(11)
 * </pre>
 */
public class RoleAppRelationPO implements Serializable {

	private static final long serialVersionUID = -3074457343668599845L;

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

}
