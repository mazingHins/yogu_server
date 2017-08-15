package com.yogu.services.user.resource.param;

import javax.ws.rs.FormParam;

import com.yogu.commons.validation.constraints.Length;
import com.yogu.language.UserMessages;

/** 
 * @author Hins 
 * @version createTime：2015年7月15日 上午11:57:39 
 * 修改用户信息接收参数 
 */
public class UserUpdateParam {

	/**
	 * 用户昵称
	 */
	@FormParam("nickname")
	@Length(max = 40, message = "昵称长度过长", canEmpty = true, mkey = UserMessages.USER_UPDATE_PARAM_NICKNAME_TOO_LONG)
	private String nickname;
	
	/**
	 * 默认城市id
	 */
	@FormParam("cityId")
	private String cityId;
	
	/**
	 * 默认语言id
	 */
	@FormParam("languageId")
	private String languageId;
	
	/**
	 * 默认支付方式：1-支付宝；2-微信支付
	 */
	@FormParam("payMode")
	private Short payMode;
	
	/**
	 * 是否推送：1-是；2-否
	 */
	@FormParam("isPush")
	private Short push;

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getCityId() {
		return cityId;
	}

	public void setCityId(String cityId) {
		this.cityId = cityId;
	}

	public String getLanguageId() {
		return languageId;
	}

	public void setLanguageId(String languageId) {
		this.languageId = languageId;
	}

	public Short getPayMode() {
		return payMode;
	}

	public void setPayMode(Short payMode) {
		this.payMode = payMode;
	}

	public Short getPush() {
		return push;
	}

	public void setPush(Short push) {
		this.push = push;
	}
	
}
