package com.yogu.services.store.dish.dto;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 菜品分组表
 * 
 */
public class DishGroup implements Serializable {

	private static final long serialVersionUID = -3074457344814913585L;

	/** 菜品分组ID */
	private long groupId;

	/** 菜品分组名 */
	private String groupName;

	/** 分组排序值 */
	private int sequence;

	/** 门店ID */
	private long storeId;

	/** 是否默认分组(全部) 1 - 是 其他 - 不是; 同时可用于决定组名能否被更改 */
	private short isDefault;

	/** 是否进入餐厅默认展示 1 - 是 其他 - 不是 */
	private short defaultShow;

	/** 创建时间 */
	private Date createTime;

	public void setGroupId(long groupId) {
		this.groupId = groupId;
	}

	public long getGroupId() {
		return groupId;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setSequence(int sequence) {
		this.sequence = sequence;
	}

	public int getSequence() {
		return sequence;
	}

	public void setStoreId(long storeId) {
		this.storeId = storeId;
	}

	public long getStoreId() {
		return storeId;
	}

	public void setIsDefault(short isDefault) {
		this.isDefault = isDefault;
	}

	public short getIsDefault() {
		return isDefault;
	}

	public void setDefaultShow(short defaultShow) {
		this.defaultShow = defaultShow;
	}

	public short getDefaultShow() {
		return defaultShow;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
