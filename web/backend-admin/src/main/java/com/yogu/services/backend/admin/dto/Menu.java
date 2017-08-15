package com.yogu.services.backend.admin.dto;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 菜单
 * 
 */
public class Menu implements Serializable {

	private static final long serialVersionUID = -3074457347199792494L;

	/** 菜单ID */
	private int menuId;

	/** 父菜单id */
	private int parentMenuId = 0;

	/** 应用系统id */
	private int appId;

	/** 名称 */
	private String menuName;

	/** 资源 */
	private String url;

	/** 排序id号 */
	private int sequence = 0;

	/** 操作者 */
	private int operator;

	/** 更新时间 */
	private Date lastModify;

	/** 菜单是否隐藏，true隐藏false不隐藏 */
	private short hide = 0;

	/** 创建时间 */
	private Date createTime;

	public void setMenuId(int menuId) {
		this.menuId = menuId;
	}

	public int getMenuId() {
		return menuId;
	}

	public void setParentMenuId(int parentMenuId) {
		this.parentMenuId = parentMenuId;
	}

	public int getParentMenuId() {
		return parentMenuId;
	}

	public void setAppId(int appId) {
		this.appId = appId;
	}

	public int getAppId() {
		return appId;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

	public String getMenuName() {
		return menuName;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUrl() {
		return url;
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

	public void setHide(short hide) {
		this.hide = hide;
	}

	public short getHide() {
		return hide;
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
