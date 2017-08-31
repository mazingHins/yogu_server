package com.yogu.remote.user.dto;


import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 用户配置表
 * 
 */
public class UserSetting implements Serializable {

	private static final long serialVersionUID = 7075236367290586561L;

	/** 用户id */
	private long uid;

	/** 是否推送通知，1-是，0-否 */
	private short isPush = 0;

	/** 创建时间 */
	private Date createTime;
	
	public void setUid(long uid) {
		this.uid = uid;
	}

	public long getUid() {
		return uid;
	}

	public short getIsPush() {
		return isPush;
	}

	public void setIsPush(short isPush) {
		this.isPush = isPush;
	}
	
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
