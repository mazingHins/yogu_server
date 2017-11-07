package com.yogu.remote.user.vo;

import com.yogu.remote.user.dto.UserProfile;

public class UserProfileVO extends UserProfile {
	
	private static final long serialVersionUID = -321256336615121004L;

	/** 昵称 */
	private String nickname;
	
	/** 推广码 */
	private String inviteCode;

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getInviteCode() {
		return inviteCode;
	}

	public void setInviteCode(String inviteCode) {
		this.inviteCode = inviteCode;
	}
	
}
