package com.yogu.services.backend.admin.dto;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 权限控制的资源
 * 
 */
public class UrlResource implements Serializable {

	private static final long serialVersionUID = -3074457344941640542L;

	/** 资源ID */
	private int resId;

	/** 父资源ID */
	private int parentResId = 0;

	/** 名称 */
	private String name;

	/** 资源 */
	private String uri;

	/** 操作者 */
	private int operator;

	/** 更新时间 */
	private Date lastModify;

	/** 排序id号 */
	private int sequence = 0;

	/** 应用系统id */
	private int appId;

	/** 资源类型，如1 url2 button 等 */
	private short type = 1;

	/** 创建时间 */
	private Date createTime;

	public void setResId(int resId) {
		this.resId = resId;
	}

	public int getResId() {
		return resId;
	}

	public void setParentResId(int parentResId) {
		this.parentResId = parentResId;
	}

	public int getParentResId() {
		return parentResId;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}

	public String getUri() {
		return uri;
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

	public void setSequence(int sequence) {
		this.sequence = sequence;
	}

	public int getSequence() {
		return sequence;
	}

	public void setAppId(int appId) {
		this.appId = appId;
	}

	public int getAppId() {
		return appId;
	}

	public void setType(short type) {
		this.type = type;
	}

	public short getType() {
		return type;
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
