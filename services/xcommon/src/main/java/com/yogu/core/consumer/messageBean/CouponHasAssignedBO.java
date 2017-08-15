package com.yogu.core.consumer.messageBean;

import java.io.Serializable;
import java.util.Date;

public class CouponHasAssignedBO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3847253184980599212L;

	private long uid; // 用户id
	private String target;// 链接 (若无则传空串"")
	private String title;// 站内信标题
	private String content;// 站内信内容
	private Date expireTime;// 站内信过期时间

	public long getUid() {
		return uid;
	}

	public void setUid(long uid) {
		this.uid = uid;
	}

	public String getTarget() {
		return target;
	}

	public void setTarget(String target) {
		this.target = target;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getExpireTime() {
		return expireTime;
	}

	public void setExpireTime(Date expireTime) {
		this.expireTime = expireTime;
	}

}
