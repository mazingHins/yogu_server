package com.yogu.services.user.resource.vo;

import java.io.Serializable;
import java.util.Date;

public class UserProfileVO implements Serializable {

	private static final long serialVersionUID = 5902136625764931362L;

	/** passport */
	private String passport;

	/** 用户昵称 */
	private String nickname;

	/** 用户简介 */
	private String description;

	/** 用户生日 */
	private Date birthday;

	/** 用户的IMID */
	private long imId;

	/** 头像地址 */
	private String profilePic = "NULL";

	/** 用户类型：1-普通用户；2-商家；3-官方用户 */
	private short userType;

	/** 商户类型 */
	private short merchantStatus;

	/** 默认支付方式，1-支付宝；2-微信支付 */
	private short payMode = 1;

	/** 是否推送通知，0-是，1-否 */
	private short isPush = 0;

	/** 默认城市code */
	private String cityId;

	/** 性别 1-男 2-女 0-未设置 */
	private short sex = 0;

	public short getSex() {
		return sex;
	}

	public void setSex(short sex) {
		this.sex = sex;
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

	public short getUserType() {
		return userType;
	}

	public void setUserType(short userType) {
		this.userType = userType;
	}

	public short getMerchantStatus() {
		return merchantStatus;
	}

	public void setMerchantStatus(short merchantStatus) {
		this.merchantStatus = merchantStatus;
	}

	public short getPayMode() {
		return payMode;
	}

	public void setPayMode(short payMode) {
		this.payMode = payMode;
	}

	public short getIsPush() {
		return isPush;
	}

	public void setIsPush(short isPush) {
		this.isPush = isPush;
	}

	public String getCityId() {
		return cityId;
	}

	public void setCityId(String cityId) {
		this.cityId = cityId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

}
