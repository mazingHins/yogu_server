package com.yogu.services.user.base.entry;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户信息冗余表（只有一个表）<br>
 * 以前是用来检查用户昵称是否存在<br>
 * 
 * 后来变成用户信息冗余表，该表只有nickname会变化，其他内容不变
 */
public class UserNicknamePO implements Serializable {

	private static final long serialVersionUID = -3074457346366697540L;

	/** 用户ID */
	private long uid;

	/** 国家区号，比如中国+86 */
	private String countryCode;

	/** 用户的手机号码或者email */
	private String passport;

	/** 昵称 */
	private String nickname;

	/** 注册IP */
	private String registerIp;

	/** 用户注册时的城市码 */
	private String registerCityCode;

	/** 用户注册时的系统语言 **/
	private String registerLang;

	/** 创建时间 */
	private Date createTime;

	public long getUid() {
		return uid;
	}

	public void setUid(long uid) {
		this.uid = uid;
	}

	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	public String getPassport() {
		return passport;
	}

	public void setPassport(String passport) {
		this.passport = passport;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getRegisterIp() {
		return registerIp;
	}

	public void setRegisterIp(String registerIp) {
		this.registerIp = registerIp;
	}

	public String getRegisterCityCode() {
		return registerCityCode;
	}

	public void setRegisterCityCode(String registerCityCode) {
		this.registerCityCode = registerCityCode;
	}

	public String getRegisterLang() {
		return registerLang;
	}

	public void setRegisterLang(String registerLang) {
		this.registerLang = registerLang;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

}
