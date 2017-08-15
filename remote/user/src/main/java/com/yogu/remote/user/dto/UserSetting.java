package com.yogu.remote.user.dto;


import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 用户配置表
 * 
 */
public class UserSetting implements Serializable {

	private static final long serialVersionUID = 7075236367290586561L;

	/** 用户id */
	private long uid;

	/** 默认城市Code */
	private String defaultCityCode;

	/** 默认语言id */
	private String defaultLanguageId;

	/** 默认支付方式，1-支付宝；2-微信支付 */
	private short defaultPayMode = 1;

	/** 是否推送通知，1-是，0-否 */
	private short isPush = 0;

	/** 创建时间 */
	private Date createTime;
	
	/**
	 * 默认是否推送 ，1-是；2-否
	 */
	public enum Push{
		yes((short)1), no((short)2);
		private short value;

		private Push(short value) {
			this.value = value;
		}

		public short getValue() {
			return value;
		} 
	}

	public void setUid(long uid) {
		this.uid = uid;
	}

	public long getUid() {
		return uid;
	}

	public String getDefaultCityCode() {
		return defaultCityCode;
	}

	public void setDefaultCityCode(String defaultCityCode) {
		this.defaultCityCode = defaultCityCode;
	}

	public void setDefaultLanguageId(String defaultLanguageId) {
		this.defaultLanguageId = defaultLanguageId;
	}

	public String getDefaultLanguageId() {
		return defaultLanguageId;
	}

	public void setDefaultPayMode(Short defaultPayMode) {
		this.defaultPayMode = defaultPayMode == null? 0:defaultPayMode;
	}

	public short getDefaultPayMode() {
		return defaultPayMode;
	}

	public short getIsPush() {
		return isPush;
	}

	public void setIsPush(short isPush) {
		this.isPush = isPush;
	}

	public void setDefaultPayMode(short defaultPayMode) {
		this.defaultPayMode = defaultPayMode;
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
