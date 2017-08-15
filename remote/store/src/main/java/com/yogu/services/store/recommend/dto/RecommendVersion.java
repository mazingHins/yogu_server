package com.yogu.services.store.recommend.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * 推荐页的版本控制，包含测试版、正式版、历史版
 * 
 */
public class RecommendVersion implements Serializable {

	private static final long serialVersionUID = -3074457347359009510L;

	/** 主键，版本id */
	private int vid;

	/** 应用于的城市 */
	private String cityCode;

	/** 语言CODE */
	private String langCode;

	/** 版本的状态 1:测试版 2: 正式版 3:历史版 */
	private short status = 0;

	/** 创建时间 */
	private Date createTime;

	/** modifyTime */
	private Date modifyTime;

	/** publishTime */
	private Date publishTime;

	/** 回滚时间 从历史版本回滚至正式版 */
	private Date rollbackTime;

	/** 创建人id */
	private long adminId;

	/** 最终推荐数据 json格式 */
	private String finalData;

	public int getVid() {
		return vid;
	}

	public void setVid(int vid) {
		this.vid = vid;
	}

	public String getCityCode() {
		return cityCode;
	}

	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}

	public String getLangCode() {
		return langCode;
	}

	public void setLangCode(String langCode) {
		this.langCode = langCode;
	}

	public short getStatus() {
		return status;
	}

	public void setStatus(short status) {
		this.status = status;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getModifyTime() {
		return modifyTime;
	}

	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}

	public Date getPublishTime() {
		return publishTime;
	}

	public void setPublishTime(Date publishTime) {
		this.publishTime = publishTime;
	}

	public Date getRollbackTime() {
		return rollbackTime;
	}

	public void setRollbackTime(Date rollbackTime) {
		this.rollbackTime = rollbackTime;
	}

	public long getAdminId() {
		return adminId;
	}

	public void setAdminId(long adminId) {
		this.adminId = adminId;
	}

	public String getFinalData() {
		return finalData;
	}

	public void setFinalData(String finalData) {
		this.finalData = finalData;
	}

}
