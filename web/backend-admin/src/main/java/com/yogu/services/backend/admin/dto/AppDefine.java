package com.yogu.services.backend.admin.dto;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 应用系统信息
 * 
 */
public class AppDefine implements Serializable {

	private static final long serialVersionUID = -3074457345667849901L;

	/** 流水号 */
	private int appId;

	/** 名称 */
	private String name;

	/** 用于显示的名称 */
	private String displayName;

	/** 系统连接的密钥 */
	private String appKey;

	/** 系统描述 */
	private String summary;

	/** 是否可用1可用，2不可用 */
	private short status = 1;

	/** 切换系统的登录url */
	private String loginUrl;

	/** 排序 */
	private int sequence = 0;

	/** 操作者 */
	private int operator;

	/** 更新时间 */
	private Date lastModify;

	/** 报bug url */
	private String bugReportUrl = "#";

	/** 创建时间 */
	private Date createTime;

	public void setAppId(int appId) {
		this.appId = appId;
	}

	public int getAppId() {
		return appId;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setAppKey(String appKey) {
		this.appKey = appKey;
	}

	public String getAppKey() {
		return appKey;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public String getSummary() {
		return summary;
	}

	public void setStatus(short status) {
		this.status = status;
	}

	public short getStatus() {
		return status;
	}

	public void setLoginUrl(String loginUrl) {
		this.loginUrl = loginUrl;
	}

	public String getLoginUrl() {
		return loginUrl;
	}

	public void setSequence(int sequence) {
		this.sequence = sequence;
	}

	public int getSequence() {
		return sequence;
	}

	public void setOperator(int operator) {
		this.operator = operator;
	}

	public int getOperator() {
		return operator;
	}

	public void setLastModify(Date lastModify) {
		this.lastModify = lastModify;
	}

	public Date getLastModify() {
		return lastModify;
	}

	public void setBugReportUrl(String bugReportUrl) {
		this.bugReportUrl = bugReportUrl;
	}

	public String getBugReportUrl() {
		return bugReportUrl;
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
