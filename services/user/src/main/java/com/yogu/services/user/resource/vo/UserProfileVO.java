package com.yogu.services.user.resource.vo;

import java.io.Serializable;
import java.util.Date;

public class UserProfileVO implements Serializable {

	private static final long serialVersionUID = 5902136625764931362L;

	/** passport */
	private String passport;

	/** 用户昵称 */
	private String nickname;

	/** 头像地址 */
	private String profilePic;

	/** 用户类型：1-普通用户；2-中间商；3-销售商 */
	private short userType;

	/** 商户类型 */
	private String inviteCode;

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

	public String getInviteCode() {
		return inviteCode;
	}

	public void setInviteCode(String inviteCode) {
		this.inviteCode = inviteCode;
	}

}
