package com.yogu.services.store.resource.vo;

import java.io.Serializable;

/**
 * 首页推荐VO
 * 
 * @author qiujun   
 * @date 2017年9月13日 下午9:24:27
 */
public class IndexRecommendVO implements Serializable {

	private static final long serialVersionUID = 5541307746887399798L;
	
	/**
	 * 商品id
	 */
	private long goodsId;
	
	/**
	 * 商品key
	 */
	private long goodsKey;
	
	/**
	 * 商品名称
	 */
	private String goodsName;
	
	/**
	 * 商品价格，单位分
	 */
	private long price;
	
	/**
	 * 商品对应的商家id
	 */
	private long storeId;
	
	/**
	 * 商品首页中图片地址
	 */
	private String cardImg;

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

	public long getPrice() {
		return price;
	}

	public void setPrice(long price) {
		this.price = price;
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
	
	
	

}
