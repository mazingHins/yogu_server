package com.yogu.services.user.config.entry;

import java.io.Serializable;
import java.util.Date;

/**
 * 基础配置表
 * 
 * <pre>
 *     自动生成代码: 表名 mz_config, 日期: 2015-07-13
 *     group_code <PK>       varchar(32)
 *     config_key <PK>       varchar(64)
 *     config_value    varchar(1024)
 *     remarks         varchar(128)
 *     create_time     datetime(19)
 * </pre>
 */
public class ConfigPO implements Serializable {

	private static final long serialVersionUID = -3074457347386443155L;

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

}
