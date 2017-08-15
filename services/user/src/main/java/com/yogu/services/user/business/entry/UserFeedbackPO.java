package com.yogu.services.user.business.entry;

import java.io.Serializable;
import java.util.Date;


/**
 * 用户反馈记录表
 * 
 * <pre>
 *     自动生成代码: 表名 mz_user_feedback, 日期: 2015-07-24
 *     feedback_id <PK>     bigint(20)
 *     uid            bigint(20)
 *     content        varchar(200)
 *     status         tinyint(4)
 *     create_time    datetime(19)
 * </pre>
 */
public class UserFeedbackPO implements Serializable {

	private static final long serialVersionUID = -3074457343888925862L;

	/** 反馈id */
	private long feedbackId;

	/** 用户ID */
	private long uid;

	/** 反馈内容 */
	private String content;

	/** 反馈状态：1-已提交；2-已回复 */
	private short status;

	/** 创建时间 */
	private Date createTime;

	public void setFeedbackId(long feedbackId) {
		this.feedbackId = feedbackId;
	}

	public long getFeedbackId() {
		return feedbackId;
	}

	public void setUid(long uid) {
		this.uid = uid;
	}

	public long getUid() {
		return uid;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getContent() {
		return content;
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
