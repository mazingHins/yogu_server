package com.yogu.services.user.business.entry;

import java.io.Serializable;
import java.util.Date;


/**
 * 反馈图片列表
 * 
 * <pre>
 *     自动生成代码: 表名 mz_user_feedback_pic, 日期: 2015-07-24
 *     pid <PK>             bigint(20)
 *     feedback_id    bigint(20)
 *     path           varchar(128)
 *     effective      tinyint(4)
 *     sequence       int(11)
 *     create_time    datetime(19)
 * </pre>
 */
public class UserFeedbackPicPO implements Serializable {

	private static final long serialVersionUID = -3074457343710795340L;

	/** 图片ID */
	private long pid;

	/** 反馈ID */
	private long feedbackId;

	/** 图片地址URL */
	private String path;

	/** 是否启用；1：启用、其他：不启用 */
	private short effective;

	/** 排序值，大的排在前面 */
	private int sequence = 0;

	/** 创建时间 */
	private Date createTime;

	public void setPid(long pid) {
		this.pid = pid;
	}

	public long getPid() {
		return pid;
	}

	public void setFeedbackId(long feedbackId) {
		this.feedbackId = feedbackId;
	}

	public long getFeedbackId() {
		return feedbackId;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getPath() {
		return path;
	}

	public void setEffective(short effective) {
		this.effective = effective;
	}

	public short getEffective() {
		return effective;
	}

	public void setSequence(int sequence) {
		this.sequence = sequence;
	}

	public int getSequence() {
		return sequence;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getCreateTime() {
		return createTime;
	}

}
