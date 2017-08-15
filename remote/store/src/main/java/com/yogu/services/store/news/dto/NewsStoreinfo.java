package com.yogu.services.store.news.dto;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 资讯专题下的推荐餐厅详情
 * 
 */
public class NewsStoreinfo implements Serializable {

	private static final long serialVersionUID = -3074457346889902942L;

	/** 餐厅详情id */
	private long sinfoId;

	/** 餐厅id可以为0 */
	private long storeId;

	/** 品牌id */
	private long brandId;

	/** 餐厅名称 */
	private String storeName = "";

	/** 餐厅英文名称 */
	private String storeNameEn = "";

	/** 店铺描述 */
	private String description;

	/** 省份code */
	private String provinceCode;

	/** 城市code */
	private String cityCode;

	/** 区code */
	private String districtCode;

	/** 商圈code */
	private String businessDistrictCode;

	/** 大星评 */
	private int stars;

	/** 餐厅类型(如果多个用英文逗号分割) */
	private String storeType;

	/** 菜系(如果多个用英文逗号分割) */
	private String cuisine;

	/** 餐厅logo */
	private String logo;

	/** 展示图，可以多图，用英文逗号分隔 */
	private String showImgs = "";

	/** 缩略图 */
	private String thumbImg = "";

	/** ID推荐图(如果多个用英文逗号分割) */
	private String idRecommendImg;

	/** 口味评分 */
	private int taste;

	/** 环境评分 */
	private int env;

	/** 性价比评分 */
	private int costEffective;

	/** 点评 */
	private String reviews;

	/** 点评人 */
	private String reviewer = "";

	/** 点评人头像 */
	private String reviewerPic;

	/** 点评点赞数 */
	private int reviewAgreeCount;

	/** 点评时间 */
	private Date reviewTime;

	/** _count` int(11) NOT NULL COMMENT 点评数 */
	private int commentCount;

	/** 想吃人数 */
	private int want;

	/** 吃过人数 */
	private int eaten;

	/** 是否已入驻米星 */
	private short inMazing;

	/** 地址地图缩略 */
	private String mapImg = "";

	/** 餐厅地址 */
	private String address = "";

	/** 联系电话(如果多个用英文逗号分割) */
	private String phone = "";

	/** 人均消费 */
	private String perConsume = "";

	/** 营业时间 */
	private String openHours = "";

	/** 纬度 */
	private double lat = 0;

	/** 经度 */
	private double lng = 0;

	/** 创建时间 */
	private Date createTime;

	/** 更新时间 */
	private Date updateTime;

	/** 推荐菜，可以多个菜名，英文逗号分隔 */
	private String recDishs = "";

	/** 配套设施(如果多个用英文逗号分割) */
	private String supporting;

	/** 是否显示mazing pay 入口 1：是 0：否 */
	private short showMazingPay;

	/** 是否显示在线餐厅 1：是 0：否 */
	private short showOnlineStore;

	/** 佳肴体验 */
	private int delicious;

	/** 服务品质 */
	private int quality;

	/** 装饰布局 */
	private int decoration;

	/** 环境气氛 */
	private int surround;

	/** 价值感受 */
	private int feeling;

	public long getSinfoId() {
		return sinfoId;
	}

	public void setSinfoId(long sinfoId) {
		this.sinfoId = sinfoId;
	}

	public long getStoreId() {
		return storeId;
	}

	public void setStoreId(long storeId) {
		this.storeId = storeId;
	}

	public long getBrandId() {
		return brandId;
	}

	public void setBrandId(long brandId) {
		this.brandId = brandId;
	}

	public String getStoreName() {
		return storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	public String getStoreNameEn() {
		return storeNameEn;
	}

	public void setStoreNameEn(String storeNameEn) {
		this.storeNameEn = storeNameEn;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getProvinceCode() {
		return provinceCode;
	}

	public void setProvinceCode(String provinceCode) {
		this.provinceCode = provinceCode;
	}

	public String getCityCode() {
		return cityCode;
	}

	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}

	public String getDistrictCode() {
		return districtCode;
	}

	public void setDistrictCode(String districtCode) {
		this.districtCode = districtCode;
	}

	public String getBusinessDistrictCode() {
		return businessDistrictCode;
	}

	public void setBusinessDistrictCode(String businessDistrictCode) {
		this.businessDistrictCode = businessDistrictCode;
	}

	public int getStars() {
		return stars;
	}

	public void setStars(int stars) {
		this.stars = stars;
	}

	public String getStoreType() {
		return storeType;
	}

	public void setStoreType(String storeType) {
		this.storeType = storeType;
	}

	public String getCuisine() {
		return cuisine;
	}

	public void setCuisine(String cuisine) {
		this.cuisine = cuisine;
	}

	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	public String getShowImgs() {
		return showImgs;
	}

	public void setShowImgs(String showImgs) {
		this.showImgs = showImgs;
	}

	public String getThumbImg() {
		return thumbImg;
	}

	public void setThumbImg(String thumbImg) {
		this.thumbImg = thumbImg;
	}

	public String getIdRecommendImg() {
		return idRecommendImg;
	}

	public void setIdRecommendImg(String idRecommendImg) {
		this.idRecommendImg = idRecommendImg;
	}

	public int getTaste() {
		return taste;
	}

	public void setTaste(int taste) {
		this.taste = taste;
	}

	public int getEnv() {
		return env;
	}

	public void setEnv(int env) {
		this.env = env;
	}

	public int getCostEffective() {
		return costEffective;
	}

	public void setCostEffective(int costEffective) {
		this.costEffective = costEffective;
	}

	public String getReviews() {
		return reviews;
	}

	public void setReviews(String reviews) {
		this.reviews = reviews;
	}

	public String getReviewer() {
		return reviewer;
	}

	public void setReviewer(String reviewer) {
		this.reviewer = reviewer;
	}

	public String getReviewerPic() {
		return reviewerPic;
	}

	public void setReviewerPic(String reviewerPic) {
		this.reviewerPic = reviewerPic;
	}

	public int getReviewAgreeCount() {
		return reviewAgreeCount;
	}

	public void setReviewAgreeCount(int reviewAgreeCount) {
		this.reviewAgreeCount = reviewAgreeCount;
	}

	public Date getReviewTime() {
		return reviewTime;
	}

	public void setReviewTime(Date reviewTime) {
		this.reviewTime = reviewTime;
	}

	public int getCommentCount() {
		return commentCount;
	}

	public void setCommentCount(int commentCount) {
		this.commentCount = commentCount;
	}

	public int getWant() {
		return want;
	}

	public void setWant(int want) {
		this.want = want;
	}

	public int getEaten() {
		return eaten;
	}

	public void setEaten(int eaten) {
		this.eaten = eaten;
	}

	public short getInMazing() {
		return inMazing;
	}

	public void setInMazing(short inMazing) {
		this.inMazing = inMazing;
	}

	public String getMapImg() {
		return mapImg;
	}

	public void setMapImg(String mapImg) {
		this.mapImg = mapImg;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPerConsume() {
		return perConsume;
	}

	public void setPerConsume(String perConsume) {
		this.perConsume = perConsume;
	}

	public String getOpenHours() {
		return openHours;
	}

	public void setOpenHours(String openHours) {
		this.openHours = openHours;
	}

	public double getLat() {
		return lat;
	}

	public void setLat(double lat) {
		this.lat = lat;
	}

	public double getLng() {
		return lng;
	}

	public void setLng(double lng) {
		this.lng = lng;
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

	public String getRecDishs() {
		return recDishs;
	}

	public void setRecDishs(String recDishs) {
		this.recDishs = recDishs;
	}

	public String getSupporting() {
		return supporting;
	}

	public void setSupporting(String supporting) {
		this.supporting = supporting;
	}

	public short getShowMazingPay() {
		return showMazingPay;
	}

	public void setShowMazingPay(short showMazingPay) {
		this.showMazingPay = showMazingPay;
	}

	public short getShowOnlineStore() {
		return showOnlineStore;
	}

	public void setShowOnlineStore(short showOnlineStore) {
		this.showOnlineStore = showOnlineStore;
	}

	public int getDelicious() {
		return delicious;
	}

	public void setDelicious(int delicious) {
		this.delicious = delicious;
	}

	public int getQuality() {
		return quality;
	}

	public void setQuality(int quality) {
		this.quality = quality;
	}

	public int getDecoration() {
		return decoration;
	}

	public void setDecoration(int decoration) {
		this.decoration = decoration;
	}

	public int getSurround() {
		return surround;
	}

	public void setSurround(int surround) {
		this.surround = surround;
	}

	public int getFeeling() {
		return feeling;
	}

	public void setFeeling(int feeling) {
		this.feeling = feeling;
	}

	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
