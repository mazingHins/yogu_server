package com.yogu.services.user.business.entry;

import java.io.Serializable;
import java.util.Date;


/**
 * 用户站内消息
 * 
 * <pre>
 *     自动生成代码: 表名 mz_user_site_message, 日期: 2015-12-16
 *     message_id <PK>        bigint(20)
 *     system_msg_id    bigint(20)
 *     uid              bigint(20)
 *     type             tinyint(4)
 *     target           varchar(255)
 *     title            varchar(64)
 *     content          varchar(255)
 *     status           tinyint(4)
 *     create_time      datetime(19)
 *     expire_time      datetime(19)
 * </pre>
 */
public class UserSiteMessagePO implements Serializable {

	private static final long serialVersionUID = -3074457344317657788L;

	/** 消息id */
	private long messageId;

	/** 站内信总表ID */
	private long systemMsgId = 0;

	/** 消息接收人id 用户ID */
	private long uid;

	/** 消息类型：1-全平台普通用户 2-全平台商家 */
	private short type = 1;

	/** 目标-链接网址或对象id */
	private String target;

	/** 标题 */
	private String title;

	/** 消息内容（文本格式） */
	private String content;
	
	/** 消息图片地址(相对路径) */
	private String msgPic = "";

	/** 消息状态。1-未读，2-已读 */
	private short status = 1;

	/** 创建时间 */
	private Date createTime;

	/** 有效期 用于决定消息链接是否可点击等操作 不涉及删除消息 */
	private Date expireTime;

	/** target不为空时，是否能分享，1可分享，0不可分享 **/
	private short canShare;
	
	public void setMessageId(long messageId) {
		this.messageId = messageId;
	}

	public long getMessageId() {
		return messageId;
	}

	public void setSystemMsgId(long systemMsgId) {
		this.systemMsgId = systemMsgId;
	}

	public long getSystemMsgId() {
		return systemMsgId;
	}

	public void setUid(long uid) {
		this.uid = uid;
	}

	public long getUid() {
		return uid;
	}

	public void setType(short type) {
		this.type = type;
	}

	public short getType() {
		return type;
	}

	public void setTarget(String target) {
		this.target = target;
	}

	public String getTarget() {
		return target;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getTitle() {
		return title;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getContent() {
		return content;
	}

	public String getMsgPic() {
		return msgPic;
	}

	public void setMsgPic(String msgPic) {
		this.msgPic = msgPic;
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

	public void setExpireTime(Date expireTime) {
		this.expireTime = expireTime;
	}

	public Date getExpireTime() {
		return expireTime;
	}

	public short getCanShare() {
		return canShare;
	}

	public void setCanShare(short canShare) {
		this.canShare = canShare;
	}

}
