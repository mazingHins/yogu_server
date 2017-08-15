package com.yogu.services.backend.admin.dto;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 记录管理员对应用系统的访问关系（如果有记录，表示可访问）
 * 
 */
public class AccountAppRelation implements Serializable {

	private static final long serialVersionUID = -3074457345426739445L;

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

	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
