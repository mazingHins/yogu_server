package com.yogu.remote.store.test.param;

import javax.ws.rs.DefaultValue;
import javax.ws.rs.FormParam;

/**
 * @Description: 添加菜品API接收参数
 * @author Hins
 * @date 2015年8月18日 下午3:07:29
 */
public class AddDishParam {

	/**
	 * 美食ID
	 */
	@FormParam("dishId")
	private long dishId;

	/**
	 * 门店ID
	 */
	@FormParam("storeId")
	private long storeId;

	/**
	 * 菜品名称
	 */
	@FormParam("dishName")
	@DefaultValue("")
	private String dishName;

	/**
	 * 规格；数量、重量等规格信息
	 */
	@FormParam("spec")
	@DefaultValue("")
	private String spec;

	/**
	 * 保质期(常温+小时数)
	 */
	@FormParam("expiryTime")
	private String expiryTime;
	
	/** 原价（分）；小于等于0表示不显示‘原价’ */
	@FormParam("originalPrice")
	private int originalPrice = 0;

	public int getOriginalPrice() {
		return originalPrice;
	}

	public void setOriginalPrice(int originalPrice) {
		this.originalPrice = originalPrice;
	}

	/**
	 * 卖点栏位的详细说明
	 */
	@FormParam("specialContent")
	@DefaultValue("")
	private String specialContent;

	/**
	 * 每日供应量
	 */
	@FormParam("dailyNum")
	private int dailyNum = 0;

	/**
	 * 价格（分）
	 */
	@FormParam("price")
	private int price = 0;

	/**
	 * 美食介绍（json格式字符串[{title:标题，content:内容}]）
	 */
	@FormParam("content")
	@DefaultValue("")
	private String content;

	/**
	 * 推广标签
	 */
	@FormParam("promoteTag")
	@DefaultValue("0")
	private short promoteTag;

	/**
	 * 货币类型：1-人民币
	 */
	@FormParam("currencyType")
	@DefaultValue("1")
	private short currencyType;

	/**
	 * 美食分类ID
	 */
	@FormParam("categoryId")
	private long categoryId;

	/**
	 * mini blog中菜品的主图
	 */
	@FormParam("topicImg")
	@DefaultValue("")
	private String topicImg;

	/**
	 * 收藏列表中显示的图片
	 */
	@FormParam("favImg")
	@DefaultValue("")
	private String favImg;

	/**
	 * 美食图片列表文件地址，多个用英文逗号分隔
	 */
	@FormParam("path")
	@DefaultValue("")
	private String path;

	/**
	 * 优惠方案，暂时只有原价
	 */
	@FormParam("saleTips")
	@DefaultValue("")
	private String saleTips;

	/**
	 * 提前需要下单时间
	 */
	@FormParam("advanceMinute")
	@DefaultValue("0")
	private int advanceMinute;

	public long getStoreId() {
		return storeId;
	}

	public void setStoreId(long storeId) {
		this.storeId = storeId;
	}

	public String getDishName() {
		return dishName;
	}

	public void setDishName(String dishName) {
		this.dishName = dishName;
	}

	public String getSpec() {
		return spec;
	}

	public void setSpec(String spec) {
		this.spec = spec;
	}

	public String getExpiryTime() {
		return expiryTime;
	}

	public void setExpiryTime(String expiryTime) {
		this.expiryTime = expiryTime;
	}

	public String getSpecialContent() {
		return specialContent;
	}

	public void setSpecialContent(String specialContent) {
		this.specialContent = specialContent;
	}

	public int getDailyNum() {
		return dailyNum;
	}

	public void setDailyNum(int dailyNum) {
		this.dailyNum = dailyNum;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public short getPromoteTag() {
		return promoteTag;
	}

	public void setPromoteTag(short promoteTag) {
		this.promoteTag = promoteTag;
	}

	public long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(long categoryId) {
		this.categoryId = categoryId;
	}

	public short getCurrencyType() {
		return currencyType;
	}

	public void setCurrencyType(short currencyType) {
		this.currencyType = currencyType;
	}

	public String getTopicImg() {
		return topicImg;
	}

	public String getSaleTips() {
		return saleTips;
	}

	public void setSaleTips(String saleTips) {
		this.saleTips = saleTips;
	}

	public int getAdvanceMinute() {
		return advanceMinute;
	}

	public void setAdvanceMinute(int advanceMinute) {
		this.advanceMinute = advanceMinute;
	}

	public void setTopicImg(String topicImg) {
		this.topicImg = topicImg;
	}

	public String getFavImg() {
		return favImg;
	}

	public void setFavImg(String favImg) {
		this.favImg = favImg;
	}

	public long getDishId() {
		return dishId;
	}

	public void setDishId(long dishId) {
		this.dishId = dishId;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

}


