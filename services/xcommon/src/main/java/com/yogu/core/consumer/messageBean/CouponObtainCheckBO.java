package com.yogu.core.consumer.messageBean;

import java.io.Serializable;

/**
 * 优惠券领取检查的消息bean
 * 
 * @author sky 2015-12-30
 *
 */
public class CouponObtainCheckBO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5703212151013853196L;
	private long uid;
	private String account;
	private String phoneSuffix;// 手机尾号
	private String mobile;// 手机号码的对称加密,加密方法参看消息发送方

	public String getPhoneSuffix() {
		return phoneSuffix;
	}

	public void setPhoneSuffix(String phoneSuffix) {
		this.phoneSuffix = phoneSuffix;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public long getUid() {
		return uid;
	}

	public void setUid(long uid) {
		this.uid = uid;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

}
