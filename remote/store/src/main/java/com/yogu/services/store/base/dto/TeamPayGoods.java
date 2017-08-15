package com.yogu.services.store.base.dto;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 团拼商品表
 * 
 */
public class TeamPayGoods implements Serializable {

	private static final long serialVersionUID = -3074457343652551751L;

	/** 物品id */
	private long goodsId;

	/** 商品图片 */
	private String image;

	/** 中文名称 */
	private String nameCn;

	/** 英文名称 */
	private String nameEn;

	/**
	 * 可卖数量
	 */
	private int total;

	/** 商品状态(1-上架 0-下架) */
	private short status;

	/** 录入时间 */
	private Date createTime;

	/** 最后修改时间 */
	private Date updateTime;

	public void setGoodsId(long goodsId) {
		this.goodsId = goodsId;
	}

	public long getGoodsId() {
		return goodsId;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getImage() {
		return image;
	}

	public void setNameCn(String nameCn) {
		this.nameCn = nameCn;
	}

	public String getNameCn() {
		return nameCn;
	}

	public void setNameEn(String nameEn) {
		this.nameEn = nameEn;
	}

	public String getNameEn() {
		return nameEn;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public void setStatus(short status) {
		this.status = status;
	}

	public short getStatus() {
		return status;
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
