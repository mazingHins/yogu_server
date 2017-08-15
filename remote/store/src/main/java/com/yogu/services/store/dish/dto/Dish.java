package com.yogu.services.store.dish.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * 门店菜品
 * 
 */
public class Dish implements Serializable {

	private static final long serialVersionUID = 8140498347952867593L;

	/** 菜品ID */
	private long dishId;

	/** 菜品名称 */
	private String dishName;

	/**add by kimmy 2017-01-05  菜品英文名称 */
	private String dishNameEn;

	/** 菜品KEY：只要是同一个菜，不管编辑多少次，这个key都不会变。 */
	private long dishKey;

	/** 门店ID */
	private long storeId;

	/** 门店名称（冗余的，门店名称可能会修改） */
	private String storeName;
	
	/**add by kimmy 2017-01-05  门店英文名称（冗余的，门店名称可能会修改） */
	private String storeNameEn;

	/** 菜品状态；1：正常、2：下架 */
	private short status = 2;

	/** 货币类型：1-人民币 */
	private short currencyType;

	/** 美食品种类型ID */
	private long categoryId;

	/** 推广标签：1-新菜上市；2-厨师推荐；3-店家拿手；4-特价菜 */
	private short promoteTag = 0;

	/** 保质期（常温+小时数） */
	private String expiryTime;

	/** 原价（分）；小于等于0表示不显示‘原价’ */
	private int originalPrice = 0;

	/** 价格（分） */
	private int price;

	/** 每天出售的数量 */
	private int dailyNum;

	/** 买点栏位的详细说明 */
	private String specialContent;

	/** 美食介绍（json格式的字符串：[{title:标题, content:内容, picList:["aa/aa.jpg"]}]） */
	private String content;

	/** 需要提前多久下单（分钟）；小于等于0表示不需要提前下单 */
	private int advanceMinute = 0;

	/** 被喜欢的数量 */
	private int favCount = 0;

	/** 收藏列表中显示的图片 */
	private String favImg;

	/** 菜品详情（菜品卡片）中的图片 */
	private String cardImg;

	/** mini blog中菜品的主图 */
	private String topicImg;
	
	/** 订单详情的图片 */
	private String orderImg = "";

	/** 规格；数量、重量等规格信息 */
	private String spec;

	/** 排序，小的在前 */
	private int sequence = 0;

	/** 创建时间 */
	private Date createTime;
	
	/** 默认菜品规格 */
	private long defaultSpecKey = 0;
	
	/** 规格展示标签 */
	private String specLabel = "规格";
	
	
	
	public String getDishNameEn() {
		return dishNameEn;
	}

	public void setDishNameEn(String dishNameEn) {
		this.dishNameEn = dishNameEn;
	}

	public String getSpecLabel() {
		return specLabel;
	}

	public void setSpecLabel(String specLabel) {
		this.specLabel = specLabel;
	}

	public long getDefaultSpecKey() {
		return defaultSpecKey;
	}

	public void setDefaultSpecKey(long defaultSpecKey) {
		this.defaultSpecKey = defaultSpecKey;
	}

	/**
	 * @return dishId
	 */
	public long getDishId() {
		return dishId;
	}

	/**
	 * @param dishId 要设置的 dishId
	 */
	public void setDishId(long dishId) {
		this.dishId = dishId;
	}

	/**
	 * @return dishName
	 */
	public String getDishName() {
		return dishName;
	}

	/**
	 * @param dishName 要设置的 dishName
	 */
	public void setDishName(String dishName) {
		this.dishName = dishName;
	}

	/**
	 * @return dishKey
	 */
	public long getDishKey() {
		return dishKey;
	}

	/**
	 * @param dishKey 要设置的 dishKey
	 */
	public void setDishKey(long dishKey) {
		this.dishKey = dishKey;
	}

	/**
	 * @return storeId
	 */
	public long getStoreId() {
		return storeId;
	}

	/**
	 * @param storeId 要设置的 storeId
	 */
	public void setStoreId(long storeId) {
		this.storeId = storeId;
	}

	/**
	 * @return storeName
	 */
	public String getStoreName() {
		return storeName;
	}

	/**
	 * @param storeName 要设置的 storeName
	 */
	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	/**
	 * @return status
	 */
	public short getStatus() {
		return status;
	}

	/**
	 * @param status 要设置的 status
	 */
	public void setStatus(short status) {
		this.status = status;
	}

	/**
	 * @return currencyType
	 */
	public short getCurrencyType() {
		return currencyType;
	}

	/**
	 * @param currencyType 要设置的 currencyType
	 */
	public void setCurrencyType(short currencyType) {
		this.currencyType = currencyType;
	}

	/**
	 * @return categoryId
	 */
	public long getCategoryId() {
		return categoryId;
	}

	/**
	 * @param categoryId 要设置的 categoryId
	 */
	public void setCategoryId(long categoryId) {
		this.categoryId = categoryId;
	}

	/**
	 * @return promoteTag
	 */
	public short getPromoteTag() {
		return promoteTag;
	}

	/**
	 * @param promoteTag 要设置的 promoteTag
	 */
	public void setPromoteTag(short promoteTag) {
		this.promoteTag = promoteTag;
	}

	/**
	 * @return expiryTime
	 */
	public String getExpiryTime() {
		return expiryTime;
	}

	/**
	 * @param expiryTime 要设置的 expiryTime
	 */
	public void setExpiryTime(String expiryTime) {
		this.expiryTime = expiryTime;
	}

	/**
	 * @return originalPrice
	 */
	public int getOriginalPrice() {
		return originalPrice;
	}

	/**
	 * @param originalPrice 要设置的 originalPrice
	 */
	public void setOriginalPrice(int originalPrice) {
		this.originalPrice = originalPrice;
	}

	/**
	 * @return price
	 */
	public int getPrice() {
		return price;
	}

	/**
	 * @param price 要设置的 price
	 */
	public void setPrice(int price) {
		this.price = price;
	}

	/**
	 * @return dailyNum
	 */
	public int getDailyNum() {
		return dailyNum;
	}

	/**
	 * @param dailyNum 要设置的 dailyNum
	 */
	public void setDailyNum(int dailyNum) {
		this.dailyNum = dailyNum;
	}

	/**
	 * @return specialContent
	 */
	public String getSpecialContent() {
		return specialContent;
	}

	/**
	 * @param specialContent 要设置的 specialContent
	 */
	public void setSpecialContent(String specialContent) {
		this.specialContent = specialContent;
	}

	/**
	 * @return content
	 */
	public String getContent() {
		return content;
	}

	/**
	 * @param content 要设置的 content
	 */
	public void setContent(String content) {
		this.content = content;
	}

	/**
	 * @return advanceMinute
	 */
	public int getAdvanceMinute() {
		return advanceMinute;
	}

	/**
	 * @param advanceMinute 要设置的 advanceMinute
	 */
	public void setAdvanceMinute(int advanceMinute) {
		this.advanceMinute = advanceMinute;
	}

	/**
	 * @return favCount
	 */
	public int getFavCount() {
		return favCount;
	}

	/**
	 * @param favCount 要设置的 favCount
	 */
	public void setFavCount(int favCount) {
		this.favCount = favCount;
	}

	/**
	 * @return favImg
	 */
	public String getFavImg() {
		return favImg;
	}

	/**
	 * @param favImg 要设置的 favImg
	 */
	public void setFavImg(String favImg) {
		this.favImg = favImg;
	}

	/**
	 * @return cardImg
	 */
	public String getCardImg() {
		return cardImg;
	}

	/**
	 * @param cardImg 要设置的 cardImg
	 */
	public void setCardImg(String cardImg) {
		this.cardImg = cardImg;
	}

	/**
	 * @return topicImg
	 */
	public String getTopicImg() {
		return topicImg;
	}

	/**
	 * @param topicImg 要设置的 topicImg
	 */
	public void setTopicImg(String topicImg) {
		this.topicImg = topicImg;
	}
	
	public String getOrderImg() {
		return orderImg;
	}

	public void setOrderImg(String orderImg) {
		this.orderImg = orderImg;
	}

	/**
	 * @return spec
	 */
	public String getSpec() {
		return spec;
	}

	/**
	 * @param spec 要设置的 spec
	 */
	public void setSpec(String spec) {
		this.spec = spec;
	}

	/**
	 * @return sequence
	 */
	public int getSequence() {
		return sequence;
	}

	/**
	 * @param sequence 要设置的 sequence
	 */
	public void setSequence(int sequence) {
		this.sequence = sequence;
	}

	/**
	 * @return createTime
	 */
	public Date getCreateTime() {
		return createTime;
	}

	/**
	 * @param createTime 要设置的 createTime
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getStoreNameEn() {
		return storeNameEn;
	}

	public void setStoreNameEn(String storeNameEn) {
		this.storeNameEn = storeNameEn;
	}
	
}
