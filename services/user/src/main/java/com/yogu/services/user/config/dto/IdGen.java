package com.yogu.services.user.config.dto;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * ID生成表，所有数字ID由ID服务统一生成
 * 
 */
public class IdGen implements Serializable {

	private static final long serialVersionUID = -3074457347268139129L;

	/** ID名称，比如user_id */
	private String idName;

	/** 下一个开始的ID */
	private long nextStartId = 1;

	/** 每次取的长度，比如1000 */
	private int fetchLen = 1000;

	/** 描述 */
	private String idDesc;

	public void setIdName(String idName) {
		this.idName = idName;
	}

	public String getIdName() {
		return idName;
	}

	public void setNextStartId(long nextStartId) {
		this.nextStartId = nextStartId;
	}

	public long getNextStartId() {
		return nextStartId;
	}

	public void setFetchLen(int fetchLen) {
		this.fetchLen = fetchLen;
	}

	public int getFetchLen() {
		return fetchLen;
	}

	public void setIdDesc(String idDesc) {
		this.idDesc = idDesc;
	}

	public String getIdDesc() {
		return idDesc;
	}

	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
