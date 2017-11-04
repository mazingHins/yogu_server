package com.yogu.services.store.resource.params;

import javax.ws.rs.DefaultValue;
import javax.ws.rs.FormParam;

public class GoodsParam {
	
	/** 商品id */
	@FormParam("goodsId")
	@DefaultValue("0")
	private long goodsId;
	
	/** 商品key */
	@FormParam("goodsKey")
	@DefaultValue("0")
	private long goodsKey;
	
	/** 商品名称 */
	@FormParam("goodsName")
	private String goodsName;
	
	/** 商品详情（商品卡片）中的图片 */
	@FormParam("cardImg")
	private String cardImg;
	
	/** 商品介绍，json字符串 */
	@FormParam("content")
	@DefaultValue("")
	private String content;
	
	/** 零售价 */
	@FormParam("retailPrice")
	private long retailPrice;
	
	/** 批发价 */
	@FormParam("tradePrice")
	private long tradePrice;

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
	
}
