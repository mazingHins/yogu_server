package com.yogu.services.backend.admin.entry;

import java.io.Serializable;
import java.util.Date;


/**
 * 角色定义
 * 
 * <pre>
 *     自动生成代码: 表名 mz_role, 日期: 2015-08-03
 *     role_id <PK>         int(11)
 *     role_name      varchar(40)
 *     role_type      int(11)
 *     remarks        varchar(200)
 *     operator       int(11)
 *     last_modify    datetime(19)
 *     create_time    datetime(19)
 * </pre>
 */
public class RolePO implements Serializable {

	private static final long serialVersionUID = -3074457346460494611L;

	/** 角色id */
	private int roleId;

	/** 角色名称 */
	private String roleName;

	/** 角色类型 */
	private int roleType = 0;

	/** 备注 */
	private String remarks;

	/** 操作者 */
	private long operator;

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

	public void setOperator(long operator) {
		this.operator = operator;
	}

	public long getOperator() {
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

}
