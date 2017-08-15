package com.yogu.services.user.resource.vo;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户选择的标签配置VO
 *
 * @date 2016年12月22日 下午4:10:00
 * @author hins
 */
public class UserCustomizeVO implements Serializable {

	
	private static final long serialVersionUID = 6156039086143867139L;

	/** 标签配置ID */
	private int customizeId;
	
	/** 该标签对应的城市code */
	private String cityCode;

	/** 配置名 例如 "重口味" 等 */
	private String name;

	/** 是否已经选择 ；1-是；其他否 */
	private short useStatus;
	
	/** 创建时间 */
	private Date createTime;

	public int getCustomizeId() {
		return customizeId;
	}

	public void setCustomizeId(int customizeId) {
		this.customizeId = customizeId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCityCode() {
		return cityCode;
	}

	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}
	
	public short getUseStatus() {
		return useStatus;
	}

	public void setUseStatus(short useStatus) {
		this.useStatus = useStatus;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
}
