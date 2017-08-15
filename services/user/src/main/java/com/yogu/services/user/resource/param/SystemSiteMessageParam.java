package com.yogu.services.user.resource.param;

import javax.ws.rs.FormParam;

import com.yogu.commons.validation.constraints.Length;
import com.yogu.commons.validation.constraints.NotBlank;
import com.yogu.language.UserMessages;

/**
 * 发送系统站内信的接收参数
 * 
 * @author felix
 * @date 2016-03-03
 */
public class SystemSiteMessageParam {
	/** 消息类型：1-全平台普通用户 2-全平台商家 */
	@FormParam("type")
	private short type;
	
	/** 目标-链接网址 */
	@FormParam("target")
	@Length(max = 255, message = "链接不能超过255个字符", canEmpty = true, mkey = UserMessages.USER_SITE_MSG_PARAM_TARGET_TOO_LONG)
	private String target;
	
	/** 标题 */
	@FormParam("title")
	@NotBlank(message="消息标题不能为空")
	@Length(max = 64, message = "标题不能超过64个字符", canEmpty = true, mkey = UserMessages.USER_SITE_MSG_PARAM_TITLE_TOO_LONG)
	private String title;
	
	/** 消息内容(文本格式) */
	@FormParam("content")
	@NotBlank(message="消息内容不能为空")
	@Length(max = 255, message = "内容不能超过255个字符", canEmpty = true, mkey = UserMessages.USER_SITE_MSG_PARAM_CONTENT_TOO_LONG)
	private String content;
	
	/** 有效期 用于决定消息链接是否可点击等操作 不涉及删除消息 */
	@FormParam("expireTime")
	private long expireTime;
	
	/** 管理员名称 */
	@FormParam("adminName")
	@NotBlank(message = "管理员名称不能为空", mkey = UserMessages.USER_SITE_MSG_PARAM_ADMIN_NAME_CAN_NOT_BE_EMPTY)
	private String adminName;
	
	/** target不为空时，是否能分享，1可分享，0不可分享 **/
	@FormParam("canShare")
	private short canShare;
	
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

	public String getAdminName() {
		return adminName;
	}

	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}

	public short getCanShare() {
		return canShare;
	}

	public void setCanShare(short canShare) {
		this.canShare = canShare;
	}
	
}
