package com.yogu.services.user.resource.vo;

/**
 * 
 * @Description: 提供给用户内部系统的VO
 * @author Hins
 * @date 2015年8月12日 下午4:48:37
 */
public class UserProfileInsideVO {
	
	private long uid;
	
	private String nickname;

	public long getUid() {
		return uid;
	}

	public void setUid(long uid) {
		this.uid = uid;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	
}
