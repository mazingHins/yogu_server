package com.yogu.core.remote.config;

import java.io.Serializable;
import java.util.List;


/**
 * 餐厅类别
 * @author felix
 */
public class StoreCategoryVO implements Serializable{
	
	private static final long serialVersionUID = -6311534217406554767L;

	/** 标签种类ID */
	private int categoryId;
	
	/** 标签种类名称 */
	private String categoryName;

	/** 是否在APP上显示；1：显示、其他：不显示 */
	private short appShow;

	/** 标签列表 */
	private List<StoreTagVO> tagList;

	public List<StoreTagVO> getTagList() {
		return tagList;
	}

	public void setTagList(List<StoreTagVO> tagList) {
		this.tagList = tagList;
	}

	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public short getAppShow() {
		return appShow;
	}

	public void setAppShow(short appShow) {
		this.appShow = appShow;
	}

}
