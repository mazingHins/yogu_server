package com.yogu.services.backend.admin.entry;

import java.io.Serializable;


/**
 * 管理员与角色对应关系
 * 
 * <pre>
 *     自动生成代码: 表名 mz_account_role_relation, 日期: 2015-08-03
 *     uid <PK>         bigint(20)
 *     role_id <PK>     int(11)
 * </pre>
 */
public class AccountRoleRelationPO implements Serializable {

	private static final long serialVersionUID = -3074457347308289337L;

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

}
