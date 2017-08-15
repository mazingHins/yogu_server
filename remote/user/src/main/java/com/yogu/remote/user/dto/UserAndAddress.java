package com.yogu.remote.user.dto;


/**
 * 用户信息、地址 综合的对象<br>
 * 暂时用于创建订单时，一次性从user域获取的数据
 *
 * @date 2016年10月2日 下午12:39:21
 * @author hins
 */
public class UserAndAddress {
	
	/**
	 * 用户信息
	 */
	private UserProfile userProfile;
	
	/**
	 * 用户地址
	 */
	private UserAddress userAddress;

	public UserProfile getUserProfile() {
		return userProfile;
	}

	public void setUserProfile(UserProfile userProfile) {
		this.userProfile = userProfile;
	}

	public UserAddress getUserAddress() {
		return userAddress;
	}

	public void setUserAddress(UserAddress userAddress) {
		this.userAddress = userAddress;
	}
	
}
