package com.yogu.remote.user.dto;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 用户信息表
 * 
 */
public class UserProfile implements Serializable {

	private static final long serialVersionUID = 240728281369243594L;

	/** uid */
	private long uid;

	/** countryCode */
	private String countryCode;

	/** 城市码 */
	private String cityCode;

	/** 注册IP */
	private String registerIp;

	/** passport */
	private String passport;

	/** 用户昵称 */
	private String nickname;

	/** 用户简介 */
	private String description;

	/** 用户生日 */
	private Date birthday;

	/** 用户性别：0-未设置；1-男；2-女 */
	private short sex = 0;

	/** 用户的IMID */
	private long imId;

	/** 头像地址 */
	private String profilePic;

	/** 用户类型：1-普通用户；2-商家；3-官方用户 */
	private short userType;

	/** 用户会员级别 */
	private short vipLevel;

	/** 积分 */
	private int score;

	/** 关注门店数量（不再使用） */
	private int concernStores = 0;

	/** 用户关注菜品数量（不再使用） */
	private int concernDishes = 0;

	/** 创建时间 */
	private Date createTime;

	/** 最后更新时间 */
	private Date lastUpdateTime;

	/** 用户系统语言代码 **/
	private String lang = "";

	public String getLang() {
		return lang;
	}

	public void setLang(String lang) {
		this.lang = lang;
	}

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

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getNickname() {
		return nickname;
	}

	public void setSex(short sex) {
		this.sex = sex;
	}

	public short getSex() {
		return sex;
	}

	public void setImId(long imId) {
		this.imId = imId;
	}

	public long getImId() {
		return imId;
	}

	public void setProfilePic(String profilePic) {
		this.profilePic = profilePic;
	}

	public String getProfilePic() {
		return profilePic;
	}

	public void setUserType(short userType) {
		this.userType = userType;
	}

	public short getUserType() {
		return userType;
	}

	public void setVipLevel(short vipLevel) {
		this.vipLevel = vipLevel;
	}

	public short getVipLevel() {
		return vipLevel;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public int getScore() {
		return score;
	}

	public void setConcernStores(int concernStores) {
		this.concernStores = concernStores;
	}

	public int getConcernStores() {
		return concernStores;
	}

	public void setConcernDishes(int concernDishes) {
		this.concernDishes = concernDishes;
	}

	public int getConcernDishes() {
		return concernDishes;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setLastUpdateTime(Date lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}

	public Date getLastUpdateTime() {
		return lastUpdateTime;
	}

	public String getCityCode() {
		return cityCode;
	}

	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}

	public String getRegisterIp() {
		return registerIp;
	}

	public void setRegisterIp(String registerIp) {
		this.registerIp = registerIp;
	}

	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
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
