package com.yogu.services.store.resource.vo.store;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.yogu.services.store.base.dto.PicVO;
import com.yogu.services.store.base.dto.ServiceRangeVO;
import com.yogu.services.store.dish.dto.DishVO;
import com.yogu.services.store.ext.StoreServiceRangeLoad;

/**
 * 门店VO <br>
 * 
 * <br>
 * JFan - 2015年7月13日 下午5:59:11
 */
public class StoreVO implements StoreServiceRangeLoad {

	/** 用户是否已搜藏该门店 */
	private boolean fav;

	/** 门店的图片列表 */
	private List<PicVO> picList;

	/** 门店下的菜品列表 */
	private List<DishVO> dishList;

	// ####

	/** 用户（坐标）距离目标（门店）的距离，单位：米 */
	private int distance;

	/** 用户（坐标）距离目标（门店）的距离用于展示，可能含单位，由服务端决定 */
	private String distanceStr;

	/** 最匹配用户（坐标）的服务配置信息；有可能为null */
	private ServiceRangeVO service;

	// ####

	/** 店铺ID */
	private long storeId;

	/** 门店名称（可限定频率地修改，例如100天修改一次） */
	private String storeName;
	
	/** add by kimmy 2017-01-04  门店英文名称（可限定频率地修改，例如100天修改一次） */
	private String storeNameEn;

	/** 最近一次修改门店名称的时间（初始为创建时间） */
	private Date storeNameUpdateTime;

	/** logo小图标路径，门店收藏列表中也使用此图（相对路径） */
	private String logoPath;

	/** 瀑布流图片路径 */
	private String topicImg;

	/** 星级，10表示1星，20为2星，如此类推；该评级由用户打分并计算平均值，由系统定时任务每天自行计算。 */
	private int star;

	/** 是否支持用户自己提货 0:否 1:是 */
	private short selfPick;

	/** 纬度 */
	private double lat = 0;

	/** 经度 */
	private double lng = 0;

	/** 邮编 */
	private String zip;

	/** 门店地址（出货地） */
	private String address;

	/** 门店电话，可能多个，英文逗号隔开 */
	private String phone;

	/** 新版客服电话号码，可能多个，英文逗号隔开 */
	private String phones;// add by sky 2016-01-30

	/**
	 * 门店营业时间，用于显示当前时间是否正在营业；<br>
	 * 用json格式存储，具体格式如下：Map<Integer int[]>，外层的map key为1~7分别表示星期天~星期六，内层的int[]为长度2的数组表示：起、止时间的分钟数；（例如11:00-14:00，记录为[660 840]）
	 */
	private String openHours;

	/**
	 * 门店配送时间（用json格式存储）具体格式如下：Map<Integer int[]>，外层的map key为1~7分别表示星期天~星期六，内层的int[]为长度2的数组表示：起、止时间的分钟数；（例如11:00-14:00，记录为[660 840]）
	 */
	private String serviceHours;

	/** 营业类型：1-常规配送餐厅；2-预定类餐厅 */
	private short bizType = 1;

	/** 预定类餐厅需要提前下单天数 */
	private short advBookingDays = 0;

	/** 截止接单时间（分钟数） */
	private short acceptOrderDeadline = 0;

	/** 门店介绍（出现在瀑布流介绍，mini blog介绍，以及收藏的介绍） */
	private String content;

	/** 是否支持现金支付, 1支持, 其他不支持 */
	private short supportCash;

	/** 客户经理名称 */
	private String csmName;

	/** 客户经理联系方式 */
	private String csmPhone;

	/** 商家 顶部 提示语 */
	private String topMsg;

	/**
	 * 门店状态；1：正常、2：休业（例如外出旅游等）、3：结业（不展示、不接单）、20：冻结（不展示、无法下单等）可结算、21：冻结（不展示、无法下单等）不可结算；；； 20以内的状态（不含）门店自行维护，其他状态由平台管理。
	 */
	private short status;

	/** 货币类型：1-人民币 */
	private short currencyType;

	/** 允许提前多少天预定（小于等于0表示不接受预定，只限当天下单当天配送） */
	private int advanceReserveDays;

	/** 下单时显示用餐人数；>0为显示且最大值为当前值 */
	private short orderMealNum = 0;

	/** 订单特征；商家自定义，订单中冗余用于展示；JSON格式：{name: "" value: ["" ""]}；name为展示的内容（例如：辣度选择），value为表示可选内容的字符串数组（["小辣" "正常" "多辣"]） */
	private String orderFeatureContent;

	/** 被喜欢的数量 */
	private int favCount = 0;

	/** 评论数量 */
	private int commentCount = 0;

	/** 创建时间 */
	private Date createTime;

	/**
	 * 底部按钮提示内容
	 */
	private String bottomMsg;

	/**
	 * 餐厅营业状态：1-未开张；2-即将开张；11-营业时间内；12-非营业时间；21-休业；22-结业
	 */
	private short businessStatus;

	/**
	 * 餐厅营业时间说明
	 */
	private String businessMsg;

	/**
	 * 美食分组信息
	 */
	private List<DishGroupVO> dishGroups;

	/**
	 * 资讯推荐 餐厅详情id
	 */
	private long sinfoId;// 2016/05/05 by sky

	/** 是否支持米星支付, 1：是 0：否 **/
	private short supportMazingPay = 0; // add by sky 2016/06/14

	/** 是否有营业相关证件照 1：是 0：否 **/
	private short hasLicense = 0;// add by sky 2016-09-09

	/** 各类营业证件照 列表 **/
	private List<LicenseVO> licenseList = new ArrayList<>();

	/**
	 * 温馨提示， 目前主要用于需要特殊取餐的提醒，比如uber送餐，需要下楼取餐
	 */
	private String warmTip = "";

	
	
	public String getStoreNameEn() {
		return storeNameEn;
	}

	public void setStoreNameEn(String storeNameEn) {
		this.storeNameEn = storeNameEn;
	}

	public String getWarmTip() {
		return warmTip;
	}

	public void setWarmTip(String warmTip) {
		this.warmTip = warmTip;
	}

	public List<LicenseVO> getLicenseList() {
		return licenseList;
	}

	public void setLicenseList(List<LicenseVO> licenseList) {
		this.licenseList = licenseList;
	}

	public short getHasLicense() {
		return hasLicense;
	}

	public void setHasLicense(short hasLicense) {
		this.hasLicense = hasLicense;
	}

	public short getSupportMazingPay() {
		return supportMazingPay;
	}

	public void setSupportMazingPay(short supportMazingPay) {
		this.supportMazingPay = supportMazingPay;
	}

	public long getSinfoId() {
		return sinfoId;
	}

	public void setSinfoId(long sinfoId) {
		this.sinfoId = sinfoId;
	}

	public String getPhones() {
		return phones;
	}

	public void setPhones(String phones) {
		this.phones = phones;
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
	 * @return dishList
	 */
	public List<DishVO> getDishList() {
		return dishList;
	}

	/**
	 * @param dishList 要设置的 dishList
	 */
	public void setDishList(List<DishVO> dishList) {
		this.dishList = dishList;
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

	public short getSelfPick() {
		return selfPick;
	}

	public void setSelfPick(short selfPick) {
		this.selfPick = selfPick;
	}

	/**
	 * @param service 要设置的 service
	 */
	public void setService(ServiceRangeVO service) {
		this.service = service;
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
	 * @return storeNameUpdateTime
	 */
	public Date getStoreNameUpdateTime() {
		return storeNameUpdateTime;
	}

	/**
	 * @param storeNameUpdateTime 要设置的 storeNameUpdateTime
	 */
	public void setStoreNameUpdateTime(Date storeNameUpdateTime) {
		this.storeNameUpdateTime = storeNameUpdateTime;
	}

	/**
	 * @return logoPath
	 */
	public String getLogoPath() {
		return logoPath;
	}

	/**
	 * @param logoPath 要设置的 logoPath
	 */
	public void setLogoPath(String logoPath) {
		this.logoPath = logoPath;
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
	 * @return star
	 */
	public int getStar() {
		return star;
	}

	/**
	 * @param star 要设置的 star
	 */
	public void setStar(int star) {
		this.star = star;
	}

	/**
	 * @return lat
	 */
	public double getLat() {
		return lat;
	}

	/**
	 * @param lat 要设置的 lat
	 */
	public void setLat(double lat) {
		this.lat = lat;
	}

	/**
	 * @return lng
	 */
	public double getLng() {
		return lng;
	}

	/**
	 * @param lng 要设置的 lng
	 */
	public void setLng(double lng) {
		this.lng = lng;
	}

	/**
	 * @return zip
	 */
	public String getZip() {
		return zip;
	}

	/**
	 * @param zip 要设置的 zip
	 */
	public void setZip(String zip) {
		this.zip = zip;
	}

	/**
	 * @return address
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * @param address 要设置的 address
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * @return phone
	 */
	public String getPhone() {
		return phone;
	}

	/**
	 * @param phone 要设置的 phone
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}

	/**
	 * @return openHours
	 */
	public String getOpenHours() {
		return openHours;
	}

	/**
	 * @param openHours 要设置的 openHours
	 */
	public void setOpenHours(String openHours) {
		this.openHours = openHours;
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
	 * @return advanceReserveDays
	 */
	public int getAdvanceReserveDays() {
		return advanceReserveDays;
	}

	/**
	 * @param advanceReserveDays 要设置的 advanceReserveDays
	 */
	public void setAdvanceReserveDays(int advanceReserveDays) {
		this.advanceReserveDays = advanceReserveDays;
	}

	/**
	 * @return orderMealNum
	 */
	public short getOrderMealNum() {
		return orderMealNum;
	}

	/**
	 * @param orderMealNum 要设置的 orderMealNum
	 */
	public void setOrderMealNum(short orderMealNum) {
		this.orderMealNum = orderMealNum;
	}

	/**
	 * @return orderFeatureContent
	 */
	public String getOrderFeatureContent() {
		return orderFeatureContent;
	}

	/**
	 * @param orderFeatureContent 要设置的 orderFeatureContent
	 */
	public void setOrderFeatureContent(String orderFeatureContent) {
		this.orderFeatureContent = orderFeatureContent;
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
	 * @return commentCount
	 */
	public int getCommentCount() {
		return commentCount;
	}

	/**
	 * @param commentCount 要设置的 commentCount
	 */
	public void setCommentCount(int commentCount) {
		this.commentCount = commentCount;
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

	public String getServiceHours() {
		return serviceHours;
	}

	public void setServiceHours(String serviceHours) {
		this.serviceHours = serviceHours;
	}

	public short getStatus() {
		return status;
	}

	public void setStatus(short status) {
		this.status = status;
	}

	public short getBizType() {
		return bizType;
	}

	public void setBizType(short bizType) {
		this.bizType = bizType;
	}

	public short getAdvBookingDays() {
		return advBookingDays;
	}

	public void setAdvBookingDays(short advBookingDays) {
		this.advBookingDays = advBookingDays;
	}

	public short getAcceptOrderDeadline() {
		return acceptOrderDeadline;
	}

	public void setAcceptOrderDeadline(short acceptOrderDeadline) {
		this.acceptOrderDeadline = acceptOrderDeadline;
	}

	public String getTopMsg() {
		return topMsg;
	}

	public void setTopMsg(String topMsg) {
		this.topMsg = topMsg;
	}

	public short getSupportCash() {
		return supportCash;
	}

	public void setSupportCash(short supportCash) {
		this.supportCash = supportCash;
	}

	public String getCsmName() {
		return csmName;
	}

	public void setCsmName(String csmName) {
		this.csmName = csmName;
	}

	public String getCsmPhone() {
		return csmPhone;
	}

	public void setCsmPhone(String csmPhone) {
		this.csmPhone = csmPhone;
	}

	public String getBottomMsg() {
		return bottomMsg;
	}

	public void setBottomMsg(String bottomMsg) {
		this.bottomMsg = bottomMsg;
	}

	public short getBusinessStatus() {
		return businessStatus;
	}

	public void setBusinessStatus(short businessStatus) {
		this.businessStatus = businessStatus;
	}

	public String getBusinessMsg() {
		return businessMsg;
	}

	public void setBusinessMsg(String businessMsg) {
		this.businessMsg = businessMsg;
	}

	public List<DishGroupVO> getDishGroups() {
		return dishGroups;
	}

	public void setDishGroups(List<DishGroupVO> dishGroups) {
		this.dishGroups = dishGroups;
	}

}
