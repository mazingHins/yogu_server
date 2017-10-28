package com.yogu.services.store;

import java.io.Serializable;

public class GoodsOrderVO implements Serializable {

	private static final long serialVersionUID = 4241621035991115522L;
	
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
	
	/** 价格，单位分 */
	private long price;
	
	/** 菜品购买总价 */
	private long totalFee;
	
	/** 排序，从小到大 */
	private int sequence;

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

	public long getTotalFee() {
		return totalFee;
	}

	public void setTotalFee(long totalFee) {
		this.totalFee = totalFee;
	}

	public long getPrice() {
		return price;
	}

	public void setPrice(long price) {
		this.price = price;
	}

	public int getSequence() {
		return sequence;
	}

	public void setSequence(int sequence) {
		this.sequence = sequence;
	}
	

}
