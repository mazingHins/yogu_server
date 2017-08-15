package com.yogu.services.user.base.entry;

import java.io.Serializable;
import java.util.Date;


/**
 * 账号表
 * 
 * <pre>
 *     自动生成代码: 表名 mz_user, 日期: 2015-07-15
 *     uid <PK>              bigint(20)
 *     country_code    varchar(8)
 *     passport        varchar(64)
 *     password        varchar(64)
 *     source          tinyint(4)
 *     status          tinyint(4)
 *     create_time     datetime(19)
 * </pre>
 */
public class UserPO implements Serializable {

	private static final long serialVersionUID = -3074457346014627836L;

	/** 用户id */
	private long uid;

	/** 国家区号，比如中国+86 */
	private String countryCode;

	/** 用户的手机号码或者email */
	private String passport;

	/** 加密后的密码 */
	private String password;

	/** 账号来源:1-app2-微信注册;3-微博注册 */
	private short source = 1;
	
	/** 状态:1-OK;2-封号 */
	private short status = 1;

	/** 创建时间 */
	private Date createTime;

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

}
