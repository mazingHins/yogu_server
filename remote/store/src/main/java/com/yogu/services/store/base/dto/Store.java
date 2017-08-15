package com.yogu.services.store.base.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.yogu.services.store.express.dto.SfFeeConfig;
import com.yogu.services.store.express.dto.StoreSf;

/**
 * 门店信息（此处登记的是审批通过的门店，如果门店正在审批是不会出现在此表中，有后台记录表记录整个申请、审批的过程）
 * 
 */
public class Store implements Serializable {

	private static final long serialVersionUID = -3074457344585851464L;

	/** 店铺ID */
	private long storeId;

	/** 商户的持有人（对应“用户中心”的用户表ID） */
	private long uid;

	/** 门店名称（可限定频率地修改，例如100天修改一次） */
	private String storeName;

	/** add by kimmy 门店英文名称（可限定频率地修改，例如100天修改一次） */
	private String storeNameEn;

	/** 最近一次修改门店名称的时间（初始为创建时间） */
	private Date storeNameUpdateTime;

	/** logo小图标路径，门店收藏列表中也使用此图（相对路径） */
	private String logoPath;

	/** 瀑布流图片路径（门店管理中单独设置） */
	private String topicImg;

	/**
	 * 门店状态；1-正常营业; 2-休业; 3-结业; 4-审核中(筹备中); 5-即将开业、
	 * 20：冻结（不展示、无法下单等）可结算、21：冻结（不展示、无法下单等）不可结算；；；20以内的状态（不含）门店自行维护，其他状态由平台管理。
	 */
	private short status;

	/** 星级，10表示1星，20为2星，如此类推；该评级由用户打分并计算平均值，由系统定时任务每天自行计算。 */
	private int star;

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

	/**
	 * 门店营业时间，用于显示当前时间是否正在营业；用json格式存储，具体格式如下：Map<Integer int[]>，外层的map
	 * key为1~7分别表示星期天~星期六，内层的int[]为长度2的数组表示：起、止时间的分钟数；（例如11:00-14:00，记录为[660
	 * 840]）
	 */
	private String openHours;

	/**
	 * 门店配送时间（用json格式存储）具体格式如下：Map<Integer int[]>，外层的map
	 * key为1~7分别表示星期天~星期六，内层的int[]为长度2的数组表示：起、止时间的分钟数；（例如11:00-14:00，记录为[660
	 * 840]）
	 */
	private String serviceHours;

	/** 营业类型：1-常规配送餐厅；2-预定类餐厅 */
	private short bizType = 0;

	/** 预定类餐厅需要提前下单天数 */
	private short advBookingDays = 0;

	/** 截止接单时间（分钟数） */
	private short acceptOrderDeadline = 0;

	/** 门店介绍（出现在瀑布流介绍，mini blog介绍，以及收藏的介绍） */
	private String content;

	/** 是否支持现金支付, 1支持, 其他不支持 */
	private short supportCash;

	/** 货币类型：1-人民币 */
	private short currencyType;

	/** 允许提前多少天预定（小于等于0表示不接受预定，只限当天下单当天配送） */
	private int advanceReserveDays;

	/** 下单时显示用餐人数；>0为显示且最大值为当前值 */
	private short orderMealNum = 0;

	/**
	 * 订单特征；商家自定义，订单中冗余用于展示；JSON格式：{name: "" value: [""
	 * ""]}；name为展示的内容（例如：辣度选择），value为表示可选内容的字符串数组（["小辣" "正常" "多辣"]）
	 */
	private String orderFeatureContent;

	/** 被喜欢的数量 */
	private int favCount = 0;

	/** _count` int(11) NOT NULL DEFAULT 0 COMMENT 评论数量 */
	private int commentCount = 0;

	/** 国家code */
	private String countryCode;

	/** 省/州code */
	private String provinceCode;

	/** 城市code */
	private String cityCode;

	/** 地级区域code */
	private String districtCode;

	/** 客户经理名称 */
	private String csmName;

	/** 客户经理联系方式 */
	private String csmPhone;

	/** 美食顺序修改操作号（每次更新美食排序，都会增加此值） */
	private int dishSequenceOperation = 0;

	/** 分组顺序修改操作号（每次更新分组排序，都会增加此值） */
	private int groupSequenceOperation = 0;

	/** 创建时间 */
	private Date createTime;

	/** 是否支持用户自己提货 0:否 1:是 */
	private short selfPick;

	/** 商家对用户自己提货的补充说明 */
	private String pickComment = "";

	/** 门店的默认美食分组 */
	private long defaultDishGroup;

	/** 是否支持米星支付, 1：是 0：否 **/
	private short supportMazingPay = 0; // add by sky 2016/06/14

	/** 是否开通顺丰配送， 1：是 0：否 */
	private short isSupportSf = 0;

	/** 餐厅口味偏好类型， 如西餐，糕点，面包 */
	private short cuisineType = 0;

	/** 米星-顺丰 关联实体信息 **/
	private StoreSf storeSf = new StoreSf();

	/** 顺丰配送 费用基础信息列表 **/
	private List<SfFeeConfig> sfFeeConfigList = new ArrayList<>();

	/** 商家对用户的温馨提示, 中文 **/
	private String warmTipZh = "";

	/** 商家对用户的温馨提示, 英文 **/
	private String warmTipEn = "";

	/**
	 * 餐厅备注
	 */
	private String remark;

	/**
	 * 推荐首页显示的餐厅logo， 若有配置值，则用该值替换logo值
	 */
	private String indexShowImg;

	public String getStoreNameEn() {
		return storeNameEn;
	}

	public void setStoreNameEn(String storeNameEn) {
		this.storeNameEn = storeNameEn;
	}

	public String getIndexShowImg() {
		return indexShowImg;
	}

	public void setIndexShowImg(String indexShowImg) {
		this.indexShowImg = indexShowImg;
	}

	public String getWarmTipEn() {
		return warmTipEn;
	}

	public void setWarmTipEn(String warmTipEn) {
		this.warmTipEn = warmTipEn;
	}

	public String getWarmTipZh() {
		return warmTipZh;
	}

	public void setWarmTipZh(String warmTipZh) {
		this.warmTipZh = warmTipZh;
	}

	public List<SfFeeConfig> getSfFeeConfigList() {
		return sfFeeConfigList;
	}

	public void setSfFeeConfigList(List<SfFeeConfig> sfFeeConfigList) {
		this.sfFeeConfigList = sfFeeConfigList;
	}

	public StoreSf getStoreSf() {
		return storeSf;
	}

	public void setStoreSf(StoreSf storeSf) {
		this.storeSf = storeSf;
	}

	public short getIsSupportSf() {
		return isSupportSf;
	}

	public void setIsSupportSf(short isSupportSf) {
		this.isSupportSf = isSupportSf;
	}

	public short getCuisineType() {
		return cuisineType;
	}

	public void setCuisineType(short cuisineType) {
		this.cuisineType = cuisineType;
	}

	public short getSupportMazingPay() {
		return supportMazingPay;
	}

	public void setSupportMazingPay(short supportMazingPay) {
		this.supportMazingPay = supportMazingPay;
	}

	public long getDefaultDishGroup() {
		return defaultDishGroup;
	}

	public void setDefaultDishGroup(long defaultDishGroup) {
		this.defaultDishGroup = defaultDishGroup;
	}

	public void setStoreId(long storeId) {
		this.storeId = storeId;
	}

	public long getStoreId() {
		return storeId;
	}

	public void setUid(long uid) {
		this.uid = uid;
	}

	public long getUid() {
		return uid;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	public String getStoreName() {
		return storeName;
	}

	public void setStoreNameUpdateTime(Date storeNameUpdateTime) {
		this.storeNameUpdateTime = storeNameUpdateTime;
	}

	public Date getStoreNameUpdateTime() {
		return storeNameUpdateTime;
	}

	public void setLogoPath(String logoPath) {
		this.logoPath = logoPath;
	}

	public String getLogoPath() {
		return logoPath;
	}

	public void setTopicImg(String topicImg) {
		this.topicImg = topicImg;
	}

	public String getTopicImg() {
		return topicImg;
	}

	public void setStatus(short status) {
		this.status = status;
	}

	public short getStatus() {
		return status;
	}

	public void setStar(int star) {
		this.star = star;
	}

	public int getStar() {
		return star;
	}

	public void setLat(double lat) {
		this.lat = lat;
	}

	public double getLat() {
		return lat;
	}

	public void setLng(double lng) {
		this.lng = lng;
	}

	public double getLng() {
		return lng;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	public String getZip() {
		return zip;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getAddress() {
		return address;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPhone() {
		return phone;
	}

	public void setOpenHours(String openHours) {
		this.openHours = openHours;
	}

	public String getOpenHours() {
		return openHours;
	}

	public void setServiceHours(String serviceHours) {
		this.serviceHours = serviceHours;
	}

	public String getServiceHours() {
		return serviceHours;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getContent() {
		return content;
	}

	public void setCurrencyType(short currencyType) {
		this.currencyType = currencyType;
	}

	public short getCurrencyType() {
		return currencyType;
	}

	public void setAdvanceReserveDays(int advanceReserveDays) {
		this.advanceReserveDays = advanceReserveDays;
	}

	public int getAdvanceReserveDays() {
		return advanceReserveDays;
	}

	public void setOrderMealNum(short orderMealNum) {
		this.orderMealNum = orderMealNum;
	}

	public short getOrderMealNum() {
		return orderMealNum;
	}

	public void setOrderFeatureContent(String orderFeatureContent) {
		this.orderFeatureContent = orderFeatureContent;
	}

	public String getOrderFeatureContent() {
		return orderFeatureContent;
	}

	public void setFavCount(int favCount) {
		this.favCount = favCount;
	}

	public int getFavCount() {
		return favCount;
	}

	public void setCommentCount(int commentCount) {
		this.commentCount = commentCount;
	}

	public int getCommentCount() {
		return commentCount;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	public String getCountryCode() {
		return countryCode;
	}

	public void setProvinceCode(String provinceCode) {
		this.provinceCode = provinceCode;
	}

	public String getProvinceCode() {
		return provinceCode;
	}

	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}

	public String getCityCode() {
		return cityCode;
	}

	public void setDistrictCode(String districtCode) {
		this.districtCode = districtCode;
	}

	public String getDistrictCode() {
		return districtCode;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public short getSelfPick() {
		return selfPick;
	}

	public void setSelfPick(short selfPick) {
		this.selfPick = selfPick;
	}

	public String getPickComment() {
		return pickComment;
	}

	public void setPickComment(String pickComment) {
		this.pickComment = pickComment;
	}

	public short getSupportCash() {
		return supportCash;
	}

	public void setSupportCash(short supportCash) {
		this.supportCash = supportCash;
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

	public int getDishSequenceOperation() {
		return dishSequenceOperation;
	}

	public void setDishSequenceOperation(int dishSequenceOperation) {
		this.dishSequenceOperation = dishSequenceOperation;
	}

	public int getGroupSequenceOperation() {
		return groupSequenceOperation;
	}

	public void setGroupSequenceOperation(int groupSequenceOperation) {
		this.groupSequenceOperation = groupSequenceOperation;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
