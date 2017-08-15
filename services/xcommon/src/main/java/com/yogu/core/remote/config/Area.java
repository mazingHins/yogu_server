package com.yogu.core.remote.config;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 国家、省/州、市、地区的定义，树形结构
 * 
 */
public class Area implements Serializable {

	private static final long serialVersionUID = -3074457347674591725L;

	/** 地点code */
	private String code;

	/** 当前地理名称 */
	private String name;

	/** 父级code */
	private String pcode;

	/** 层级关系，从1开始 */
	private int level;

	/** 纬度（城市、区域的中心点） */
	private double lat = 0;

	/** 经度（城市、区域的中心点） */
	private double lng = 0;

	/** 创建时间 */
	private Date createTime;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPcode() {
		return pcode;
	}

	public void setPcode(String pcode) {
		this.pcode = pcode;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public double getLat() {
		return lat;
	}

	public void setLat(double lat) {
		this.lat = lat;
	}

	public double getLng() {
		return lng;
	}

	public void setLng(double lng) {
		this.lng = lng;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
