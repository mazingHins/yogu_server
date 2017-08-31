package com.yogu.services.user.base.entry;

import java.io.Serializable;
import java.util.Date;


/**
 * 用户邀请码表
 * 
 */
public class UserInvitePO implements Serializable {

	private static final long serialVersionUID = -8996858568383464158L;

	/** 用户id */
	private long uid;

	/** 国家区号，比如中国+86 */
	private String inviteCode;

	/** 状态:1-OK;2-封号 */
	private short status = 1;

	/** 创建时间 */
	private Date createTime;

	public void setUid(long uid) {
		this.uid = uid;
	}

	public long getUid() {
		return uid;
	}
	
	public String getInviteCode() {
		return inviteCode;
	}

	public void setInviteCode(String inviteCode) {
		this.inviteCode = inviteCode;
	}

	public void setStatus(short status) {
		this.status = status;
	}

	public short getStatus() {
		return status;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getCreateTime() {
		return createTime;
	}

}
