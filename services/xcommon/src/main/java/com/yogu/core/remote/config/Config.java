package com.yogu.core.remote.config;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;
import java.util.Date;

/**
 * 基础配置表
 * 
 */
public class Config implements Serializable {

	private static final long serialVersionUID = -3074457344597393599L;

	/** 配置分组code */
	private String groupCode;

	/** 健 */
	private String configKey;

	/** 值 */
	private String configValue;

	/** 备注 */
	private String remarks;

	/** 创建时间 */
	private Date createTime;

	public void setGroupCode(String groupCode) {
		this.groupCode = groupCode;
	}

	public String getGroupCode() {
		return groupCode;
	}

	public void setConfigKey(String configKey) {
		this.configKey = configKey;
	}

	public String getConfigKey() {
		return configKey;
	}

	public void setConfigValue(String configValue) {
		this.configValue = configValue;
	}

	public String getConfigValue() {
		return configValue;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getRemarks() {
		return remarks;
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
