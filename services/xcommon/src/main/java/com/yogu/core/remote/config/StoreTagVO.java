package com.yogu.core.remote.config;

import java.io.Serializable;

/**
 * 餐厅标签
 * @author felix
 */
public class StoreTagVO implements Serializable{
	private static final long serialVersionUID = -2168970738659265129L;

	/** 餐厅标签ID */
	private int tagId;
	
	/** 标签名 例如 "午餐下午茶晚餐宵夜" 等 */
	private String tagName;

	public int getTagId() {
		return tagId;
	}

	public void setTagId(int tagId) {
		this.tagId = tagId;
	}

	public String getTagName() {
		return tagName;
	}

	public void setTagName(String tagName) {
		this.tagName = tagName;
	}
}
