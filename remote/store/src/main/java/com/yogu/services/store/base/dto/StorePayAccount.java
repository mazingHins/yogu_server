package com.yogu.services.store.base.dto;

import java.io.Serializable;
import java.util.Date;
import java.math.*;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 商家支付帐号记录表
 * 
 */
public class StorePayAccount implements Serializable {

	private static final long serialVersionUID = -3074457347333587599L;

	/** 餐厅拥有者用户id */
	private long uid;

	/** 支付方式(1-支付宝 2-微信) */
	private short payMode;

	/** 第三方appId */
	private String appId;

	/** 第三方商户id */
	private String mchId;

	/** 是否开通(1-是 0-否) */
	private short isOpen;

	/** 录入时间 */
	private Date createTime;

	/** 最后修改时间 */
	private Date updateTime;

	public void setUid(long uid) {
		this.uid = uid;
	}

	public long getUid() {
		return uid;
	}

	public void setPayMode(short payMode) {
		this.payMode = payMode;
	}

	public short getPayMode() {
		return payMode;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public String getAppId() {
		return appId;
	}

	public void setMchId(String mchId) {
		this.mchId = mchId;
	}

	public String getMchId() {
		return mchId;
	}

	public void setIsOpen(short isOpen) {
		this.isOpen = isOpen;
	}

	public short getIsOpen() {
		return isOpen;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
