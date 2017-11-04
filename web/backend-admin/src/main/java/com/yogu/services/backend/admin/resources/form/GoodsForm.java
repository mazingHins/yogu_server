package com.yogu.services.backend.admin.resources.form;

public class GoodsForm {
	
	/** 商品key */
	private long goodsKey;
	
	/** 商品名称 */
	private String goodsName;
	
	/** 商品详情（商品卡片）中的图片 */
	private String cardImg;
	
	/** 商品介绍，json字符串 */
	private String content;
	
	/** 零售价 */
	private long retailPrice;
	
	/** 批发价 */
	private long tradePrice;
	
	/** 排序，从小到大 */
	private int sequence;
	
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

	public int getSequence() {
		return sequence;
	}

	public void setSequence(int sequence) {
		this.sequence = sequence;
	}

}
