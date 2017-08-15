package com.yogu.remote.user.dto;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 账号表
 * 
 */
public class User implements Serializable {

	private static final long serialVersionUID = -1143879854437642930L;

	/** 用户id */
	private long uid;

	/** 国家区号，比如中国+86 */
	private String countryCode;

	/** 用户的手机号码或者email */
	private String passport;

	/* 头像  add by june 2017-01-13 */
	private String profilePic = "";

	/** 加密后的密码 */
	private String password;

	/** 账号来源:1-米星2-微信注册;3-微博注册 */
	private short source = 1;

	/** 状态:1-OK;2-封号 */
	private short status = 1;

	/** 创建时间 */
	private Date createTime;

	/**
	 * 昵称/用户名
	 */
	private String nickname;

	/**
	 * 用户所在城市
	 */
	private String cityCode;

	/** 用户的IMID */
	private long imId;

	public void setUid(long uid) {
		this.uid = uid;
	}

	public long getUid() {
		return uid;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	public String getCountryCode() {
		return countryCode;
	}

	public void setPassport(String passport) {
		this.passport = passport;
	}

	public String getPassport() {
		return passport;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPassword() {
		return password;
	}

	public void setSource(short source) {
		this.source = source;
	}

	public short getSource() {
		return source;
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

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getCityCode() {
		return cityCode;
	}

	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}

	public long getImId() {
		return imId;
	}

	public void setImId(long imId) {
		this.imId = imId;
	}

	public String getProfilePic() {
		return profilePic;
	}

	public void setProfilePic(String profilePic) {
		this.profilePic = profilePic;
	}

	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}
}
