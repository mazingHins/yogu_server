package com.yogu.services.user.base.entry;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 用户信息表
 * 
 * <pre>
 *     自动生成代码: 表名 mz_user_profile, 日期: 2015-07-28
 *     uid <PK>                  bigint(20)
 *     country_code        varchar(8)
 *     passport            varchar(64)
 *     nickname            varchar(40)
 *     description         varchar(100)
 *     birthday            datetime(19)
 *     sex                 tinyint(4)
 *     im_id               bigint(20)
 *     profile_pic         varchar(128)
 *     user_type           tinyint(4)
 *     vip_level           smallint(6)
 *     score               int(11)
 *     concern_stores      int(11)
 *     concern_dishes      int(11)
 *     create_time         datetime(19)
 *     last_update_time    datetime(19)
 *     lang                varchar(8)
 * </pre>
 */
public class UserProfilePO implements Serializable {

	private static final long serialVersionUID = -3074457343538405663L;

	/** uid */
	private long uid;

	/** countryCode */
	private String countryCode;

	/** 注册IP */
	private String registerIp;

	/** passport */
	private String passport;

	/** 用户昵称 */
	private String nickname;

	/** 头像地址 */
	private String profilePic;

	/** 用户类型：1-普通用户；2-批发商； */
	private short userType;
	
	/** 受邀推荐人id */
	private long inviteUid;

	/** 创建时间 */
	private Date createTime;

	/** 最后更新时间 */
	private Date lastUpdateTime;
	

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

	public String getRegisterIp() {
		return registerIp;
	}

	public void setRegisterIp(String registerIp) {
		this.registerIp = registerIp;
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

	public String getProfilePic() {
		return profilePic;
	}

	public void setProfilePic(String profilePic) {
		this.profilePic = profilePic;
	}

	public short getUserType() {
		return userType;
	}

	public void setUserType(short userType) {
		this.userType = userType;
	}

	public long getInviteUid() {
		return inviteUid;
	}

	public void setInviteUid(long inviteUid) {
		this.inviteUid = inviteUid;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getLastUpdateTime() {
		return lastUpdateTime;
	}

	public void setLastUpdateTime(Date lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}

	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}
}
