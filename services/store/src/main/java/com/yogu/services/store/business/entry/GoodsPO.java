package com.yogu.services.store.business.entry;

import java.io.Serializable;
import java.util.Date;

/**
 * 商品信息
 *
 */
public class GoodsPO implements Serializable {
	
	private static final long serialVersionUID = -6716304276634251745L;

	/** 商品id */
	private long goodsId;
	
	/** 商品key */
	private long goodsKey;
	
	/** 商品名称 */
	private String goodsName;
	
	/** 店铺id */
	private long storeId;
	
	/** 商品详情（商品卡片）中的图片 */
	private String cardImg;
	
	/** 商品介绍，json字符串 */
	private String content;
	
	/** 零售价 */
	private long retailPrice;
	
	/** 批发价价 */
	private long tradePrice;
	
	/** 排序，从小到大 */
	private int sequence;
	
	/** 状态:1-正常;2-下架 */
	private short status;
	
	/** 创建时间 */
	private Date createTime;
	
	/** 更新时间 */
	private Date updateTime;

	public long getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(long goodsId) {
		this.goodsId = goodsId;
	}

	public long getGoodsKey() {
		return goodsKey;
	}

	public void setGoodsKey(long goodsKey) {
		this.goodsKey = goodsKey;
	}

	public String getGoodsName() {
		return goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	public long getStoreId() {
		return storeId;
	}

	public void setStoreId(long storeId) {
		this.storeId = storeId;
	}

	public String getCardImg() {
		return cardImg;
	}

	public void setCardImg(String cardImg) {
		this.cardImg = cardImg;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public long getRetailPrice() {
		return retailPrice;
	}

	public void setRetailPrice(long retailPrice) {
		this.retailPrice = retailPrice;
	}

	public long getTradePrice() {
		return tradePrice;
	}

	public void setTradePrice(long tradePrice) {
		this.tradePrice = tradePrice;
	}

	public int getSequence() {
		return sequence;
	}

	public void setSequence(int sequence) {
		this.sequence = sequence;
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

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	
}
