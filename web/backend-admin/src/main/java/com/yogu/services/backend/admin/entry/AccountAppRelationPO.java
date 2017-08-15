package com.yogu.services.backend.admin.entry;

import java.io.Serializable;


/**
 * 记录管理员对应用系统的访问关系（如果有记录，表示可访问）
 * 
 * <pre>
 *     自动生成代码: 表名 mz_account_app_relation, 日期: 2015-08-03
 *     uid <PK>        bigint(20)
 *     app_id <PK>     int(11)
 * </pre>
 */
public class AccountAppRelationPO implements Serializable {

	private static final long serialVersionUID = -3074457345473533597L;

	/** 管理员id */
	private long uid;

	/** 应用系统id */
	private int appId;

	public void setUid(long uid) {
		this.uid = uid;
	}

	public long getUid() {
		return uid;
	}

	public void setAppId(int appId) {
		this.appId = appId;
	}

	public int getAppId() {
		return appId;
	}

}
