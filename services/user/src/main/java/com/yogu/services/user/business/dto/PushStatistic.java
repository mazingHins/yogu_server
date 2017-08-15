package com.yogu.services.user.business.dto;

import java.io.Serializable;
import java.util.Date;
import java.math.*;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * push all 相关数据统计
 * 
 */
public class PushStatistic implements Serializable {

	private static final long serialVersionUID = -3074457345778348893L;

	/** 主键id */
	private long pid;

	/** 推送内容 */
	private String msg;

	/** 推送时间 */
	private Date createTime;

	/** 推送总数 */
	private int total = 0;

	/** 发送成功总数 */
	private int totalSuccess = 0;

	/** 发送的系统类型 1:ios 2:android */
	private short type;

	private String adminName;

	public String getAdminName() {
		return adminName;
	}

	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}

	public void setPid(long pid) {
		this.pid = pid;
	}

	public long getPid() {
		return pid;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getMsg() {
		return msg;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public int getTotal() {
		return total;
	}

	public void setTotalSuccess(int totalSuccess) {
		this.totalSuccess = totalSuccess;
	}

	public int getTotalSuccess() {
		return totalSuccess;
	}

	public void setType(short type) {
		this.type = type;
	}

	public short getType() {
		return type;
	}

	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
