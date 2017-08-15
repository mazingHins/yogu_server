package com.yogu.services.order.base.dto.vo;

import java.io.Serializable;
import java.util.Date;

/**
 * 评论列表中的评论信息
 * 
 * @author Hins createTime：2015年7月22日 下午6:47:55
 */
public class CommentContentVO implements Serializable {

	private static final long serialVersionUID = 1L;

	/** _id` bigint(20) NOT NULL COMMENT 点评ID */
	private long commentId;

	/** 点评用户ID */
	private long uid;

	/** 门店ID */
	private long storeId;

	/** 评论文字内容 */
	private String content;

	/** 赞同数量 */
	private int agreeCount;

	/** 创建时间 */
	private Date createTime;

	/** 评论人昵称 */
	private String nickname;

	/** 评论人头像 */
	private String profilePic;

	/** 官方回复 */
	private String reply;

	/** 用户性别：0-未设置；1-男；2-女 */
	private short sex;

	/**
	 * @return the nickname
	 */
	public String getNickname() {
		return nickname;
	}

	/**
	 * @param nickname the nickname to set
	 */
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	/**
	 * @return the profilePic
	 */
	public String getProfilePic() {
		return profilePic;
	}

	/**
	 * @param profilePic the profilePic to set
	 */
	public void setProfilePic(String profilePic) {
		this.profilePic = profilePic;
	}

	/**
	 * @return the reply
	 */
	public String getReply() {
		return reply;
	}

	/**
	 * @param reply the reply to set
	 */
	public void setReply(String reply) {
		this.reply = reply;
	}

	public long getCommentId() {
		return commentId;
	}

	public void setCommentId(long commentId) {
		this.commentId = commentId;
	}

	public long getUid() {
		return uid;
	}

	public void setUid(long uid) {
		this.uid = uid;
	}

	public long getStoreId() {
		return storeId;
	}

	public void setStoreId(long storeId) {
		this.storeId = storeId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getAgreeCount() {
		return agreeCount;
	}

	public void setAgreeCount(int agreeCount) {
		this.agreeCount = agreeCount;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public short getSex() {
		return sex;
	}

	public void setSex(short sex) {
		this.sex = sex;
	}

}
