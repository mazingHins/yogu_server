package com.yogu.services.store.dish.dto;

import java.util.ArrayList;
import java.util.List;

import com.yogu.services.store.ServiceRangeLoad;
import com.yogu.services.store.base.dto.PicVO;
import com.yogu.services.store.base.dto.ServiceRangeVO;
import com.yogu.services.store.dish.dto.DishTag;

/**
 * 菜品VO <br>
 * 
 * <br>
 * JFan - 2015年7月13日 下午5:58:54
 */
public class DishVO implements ServiceRangeLoad {

	/** 剩余数量 */
	private int surplus;
	/** 哪一天的库存 **/
	private int surplusDay;

	/** 已售数量 **/
	private int soldNum;

	/** 用户是否已设置喜欢该菜品 */
	private boolean fav;

	/** 菜品图片列表 */
	private List<PicVO> picList;

	// ####

	/** 是否支持现金支付, 1支持, 其他不支持 */
	private short supportCash;

	/** 门店状态；1：正常、2：休业（例如外出旅游等）、3：结业（不展示、不接单）、4：审核中(新创建的门店)、20：冻结（不展示、无法下单等）可结算、21：冻结（不展示、无法下单等）不可结算；；；20以内的状态（不含）门店自行维护，其他状态由平台管理。 */
	private short storeStatus;

	/** 营业类型：1-常规配送餐厅；2-预定类餐厅 */
	private short bizType = 0;

	/** 用户（坐标）距离目标（门店）的距离，单位：米 */
	private int distance;

	/** 用户（坐标）距离目标（门店）的距离用于展示，可能含单位，由服务端决定 */
	private String distanceStr;

	/** 最匹配用户（坐标）的服务配置信息；有可能为null */
	private ServiceRangeVO service;

	// ####

	/** 菜品ID */
	private long dishId;

	/** 菜品名称 */
	private String dishName;

	/**add by kimmy 2017-01-06 菜品英文名称 */
	private String dishNameEn;

	/** 菜品KEY：只要是同一个菜，不管编辑多少次，这个key都不会变。 */
	private long dishKey;

	/** 门店ID */
	private long storeId;

	/**add by kimmy 2017-01-05 门店名称（冗余的，门店名称可能会修改） */
	private String storeName;

	/** 门店英文名称（冗余的，门店名称可能会修改） */
	private String storeNameEn;

	/** 门店logo */
	private String storeLogoPath;

	/** 菜品状态；1：正常、2：下架 */
	private short status;
	
	/** 彩票状态描述 **/
	private String statusDesc;

	/** 货币类型：1-人民币 */
	private short currencyType;

	/** 美食品种类型ID */
	private long categoryId;

	/** 推广标签：1-新菜上市；2-厨师推荐；3-店家拿手；4-特价菜 */
	private short promoteTag;

	/** 保质期（常温+小时数） */
	private String expiryTime;

	/** 原价（分）；小于等于0表示不显示‘原价’ */
	private int originalPrice;

	/** 价格（分） */
	private int price;

	/** 每天出售的数量 */
	private int dailyNum;

	/** 买点栏位的详细说明 */
	private String specialContent;

	/** 美食介绍（json格式的字符串：[{title:标题, content:内容, picList:["aa/aa.jpg"]}]） */
	private String content;

	/** 需要提前多久下单（分钟）；小于等于0表示不需要提前下单 */
	private int advanceMinute;

	/** 被喜欢的数量 */
	private int favCount;

	/** 收藏列表中显示的图片 */
	private String favImg;

	/** 菜品详情（菜品卡片）中的图片 */
	private String cardImg;

	/** mini blog中菜品的主图 */
	private String topicImg;

	/** 规格；数量、重量等规格信息 */
	private String spec;

	/** 排序，小的在前 */
	private int sequence;

	/** 美食所绑定的TAG信息 */
	private List<DishTag> tagList;

	/** 默认菜品规格 */
	private long defaultSpecKey = 0;

	/** 规格展示标签 */
	private String specLabel = "规格";

	// 菜品规格 ---菜品详情接口新增返回字段 add by sky 2016-02-22
	// --start---
	/** 多规格的价格范围 **/
	private String priceRange;// 如有多个规格, 则$9.99 - $15.99 ; 如只有一个规格, 则展示这个规格的价格, $9.99

	/** 菜品的规格展示方式 **/
	private short displayMode;// 1 - 表示需要展示规格 ; 其他 - 表示按旧版本方式展示(这种情况只出现在单规格且只 有一个或0个备注)

	/** 规格列表 **/
	private List<DishSpecVO> dishSpecList = new ArrayList<DishSpecVO>();// size至少为1
	
	/**
	 * 美食分组id列表, 表示该美食所属的分组(多个)
	 */
	private List<Long> dishGroupIds;

	// --end---

	public String getPriceRange() {
		return priceRange;
	}

	public void setPriceRange(String priceRange) {
		this.priceRange = priceRange;
	}

	public short getDisplayMode() {
		return displayMode;
	}

	public void setDisplayMode(short displayMode) {
		this.displayMode = displayMode;
	}

	public List<DishSpecVO> getDishSpecList() {
		return dishSpecList;
	}

	public void setDishSpecList(List<DishSpecVO> dishSpecList) {
		this.dishSpecList = dishSpecList;
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

	public int getSurplusDay() {
		return surplusDay;
	}

	public void setSurplusDay(int surplusDay) {
		this.surplusDay = surplusDay;
	}

	/**
	 * @return surplus
	 */
	public int getSurplus() {
		return surplus;
	}

	/**
	 * @param surplus 要设置的 surplus
	 */
	public void setSurplus(int surplus) {
		this.surplus = surplus;
	}

	/**
	 * @return soldNum
	 */
	public int getSoldNum() {
		return soldNum;
	}

	/**
	 * @param soldNum 要设置的 soldNum
	 */
	public void setSoldNum(int soldNum) {
		this.soldNum = soldNum;
	}

	/**
	 * @return fav
	 */
	public boolean isFav() {
		return fav;
	}

	/**
	 * @param fav 要设置的 fav
	 */
	public void setFav(boolean fav) {
		this.fav = fav;
	}

	/**
	 * @return picList
	 */
	public List<PicVO> getPicList() {
		return picList;
	}

	/**
	 * @param picList 要设置的 picList
	 */
	public void setPicList(List<PicVO> picList) {
		this.picList = picList;
	}

	/**
	 * @return distance
	 */
	public int getDistance() {
		return distance;
	}

	/**
	 * @param distance 要设置的 distance
	 */
	public void setDistance(int distance) {
		this.distance = distance;
	}

	/**
	 * @return distanceStr
	 */
	public String getDistanceStr() {
		return distanceStr;
	}

	/**
	 * @param distanceStr 要设置的 distanceStr
	 */
	public void setDistanceStr(String distanceStr) {
		this.distanceStr = distanceStr;
	}

	/**
	 * @return service
	 */
	public ServiceRangeVO getService() {
		return service;
	}

	/**
	 * @param service 要设置的 service
	 */
	public void setService(ServiceRangeVO service) {
		this.service = service;
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

	public short getSupportCash() {
		return supportCash;
	}

	public void setSupportCash(short supportCash) {
		this.supportCash = supportCash;
	}

	public String getStoreLogoPath() {
		return storeLogoPath;
	}

	public void setStoreLogoPath(String storeLogoPath) {
		this.storeLogoPath = storeLogoPath;
	}

	public short getStoreStatus() {
		return storeStatus;
	}

	public void setStoreStatus(short storeStatus) {
		this.storeStatus = storeStatus;
	}

	public short getBizType() {
		return bizType;
	}

	public void setBizType(short bizType) {
		this.bizType = bizType;
	}

	/**
	 * @return tagList
	 */
	public List<DishTag> getTagList() {
		return tagList;
	}

	/**
	 * @param tagList 要设置的 tagList
	 */
	public void setTagList(List<DishTag> tagList) {
		this.tagList = tagList;
	}

	public List<Long> getDishGroupIds() {
		return dishGroupIds;
	}

	public void setDishGroupIds(List<Long> dishGroupIds) {
		this.dishGroupIds = dishGroupIds;
	}

	public String getStatusDesc() {
		return statusDesc;
	}

	public void setStatusDesc(String statusDesc) {
		this.statusDesc = statusDesc;
	}

	public String getDishNameEn() {
		return dishNameEn;
	}

	public void setDishNameEn(String dishNameEn) {
		this.dishNameEn = dishNameEn;
	}

	public String getStoreNameEn() {
		return storeNameEn;
	}

	public void setStoreNameEn(String storeNameEn) {
		this.storeNameEn = storeNameEn;
	}

	@Override
	public String toString() {
		return "DishVO [dishName=" + dishName + ", dishNameEn=" + dishNameEn + "]";
	}
	
	
}
