package com.yogu.services.user.resource.param;

import javax.ws.rs.FormParam;

import com.yogu.commons.validation.constraints.Length;
import com.yogu.language.UserMessages;

/**
 * 用户注册时发送站内信的接受参数
 * @author felix
 */
public class SiteMessageParam {
	
	/** 用户ID */
	@FormParam("uid")
	private long uid;
	
	/** 消息类型：1-全平台普通用户 2-全平台商家 */
	@FormParam("type")
	private short type;
	
	/** 目标-链接网址 */
	@FormParam("target")
	@Length(max = 255, message = "链接不能超过255个字符", canEmpty = true, mkey = UserMessages.USER_SITE_MSG_PARAM_TARGET_TOO_LONG)
	private String target;
	
	/** 标题 */
	@FormParam("title")
	@Length(max = 64, message = "标题不能超过64个字符", canEmpty = true, mkey = UserMessages.USER_SITE_MSG_PARAM_TITLE_TOO_LONG)
	private String title;
	
	/** 消息内容(文本格式) */
	@FormParam("content")
	@Length(max = 255, message = "内容不能超过255个字符", canEmpty = true, mkey = UserMessages.USER_SITE_MSG_PARAM_CONTENT_TOO_LONG)
	private String content;
	
	/** 有效期 用于决定消息链接是否可点击等操作 不涉及删除消息 */
	@FormParam("expireTime")
	private long expireTime;

	public long getUid() {
		return uid;
	}

	public void setUid(long uid) {
		this.uid = uid;
	}

	public short getType() {
		return type;
	}

	public void setType(short type) {
		this.type = type;
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

	public long getExpireTime() {
		return expireTime;
	}

	public void setExpireTime(long expireTime) {
		this.expireTime = expireTime;
	}
}
