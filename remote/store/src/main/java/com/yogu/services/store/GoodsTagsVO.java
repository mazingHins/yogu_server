package com.yogu.services.store;

/**
 * 商品tagVO
 * 
 * @author qiujun   
 * @date 2018年1月31日 下午8:59:37
 */
public class GoodsTagsVO {
	
	/** 标签ID */
	private long tagId;

	/** 分类ID */
	private long categoryId;

	/** 分类名称 */
	private String tagName;
	
	/** 排序，从小到大 */
	private int sequence;
	
	private short isCheck = 0;

	public long getTagId() {
		return tagId;
	}

	public void setTagId(long tagId) {
		this.tagId = tagId;
	}

	public long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(long categoryId) {
		this.categoryId = categoryId;
	}

	public String getTagName() {
		return tagName;
	}

	public void setTagName(String tagName) {
		this.tagName = tagName;
	}

	public int getSequence() {
		return sequence;
	}

	public void setSequence(int sequence) {
		this.sequence = sequence;
	}

	public short getIsCheck() {
		return isCheck;
	}

	public void setIsCheck(short isCheck) {
		this.isCheck = isCheck;
	}
	
	
}
