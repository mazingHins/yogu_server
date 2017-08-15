package com.yogu.services.store.recommend.dto;

import java.io.Serializable;
import java.util.Date;

import javax.ws.rs.FormParam;

/**
 * mz_recommend_item
 * 
 */
public class RecommendItem implements Serializable {

	private static final long serialVersionUID = -3074457345453930318L;

	/** 主键id自增长 */
	@FormParam("itemId")
	private int itemId;

	/** 所属块id */
	@FormParam("bid")
	private long bid;

	/** 状态 0:未启用 1:启用 */
	private short status;

	/** 此数据项显示的图片 */
	@FormParam("img")
	private String img = "";

	/** 当前项的所属类型 h5:H5页面 store:餐厅 dish:美食 index:瀑布流 类型决定了打开方式 */
	@FormParam("target")
	private String target;

	/** 所属类型的id 当target=2或3时此值为餐厅id或美食dishKey 其它为0 */
	@FormParam("targetId")
	private long targetId = 0;

	/** 数据项显示的名称上层block中type=餐厅 | 美食，此列有值 */
	@FormParam("name")
	private String name = "";

	/** 简介 上层block中type=餐厅 | 美食，此列有值 */
	@FormParam("description")
	private String description = "";

	/** 数据项的logo图片地址上层block中type=餐厅，此列有值 */
	@FormParam("logo")
	private String logo = "";

	/** 原价（分）；小于等于0表示不显示‘原价’ */
	@FormParam("originalPrice")
	private int originalPrice = 0;

	/** 价格（分） */
	@FormParam("price")
	private int price;

	/** 打开 html5 页面的地址 */
	@FormParam("url")
	private String url = "";

	/** 是否可分享 0:否 1:是 */
	@FormParam("canShare")
	private short canShare = 0;

	/** 此数据项附带的参数值 不同的target 此参数值不同 如 当target为瀑布流index时 params为标签id或id串;当target为H5可分享时params为分享的类型 */
	@FormParam("targetParams")
	private String targetParams = "";

	/** 排序号 */
	private int sequence;

	/** 创建时间 */
	private Date createTime;

	/** modifyTime */
	private Date modifyTime;

	/** 创建人id */
	@FormParam("adminId")
	private long adminId;

	/** 分享时分享块显示的图标 */
	@FormParam("shareImg")
	private String shareImg = "";

	/** 分享数据项给朋友显示的标题 */
	@FormParam("shareTitle")
	private String shareTitle = "";

	/** 分享数据项到朋友圈显示的标题 */
	@FormParam("shareTimelineTitle")
	private String shareTimelineTitle = "";

	/** 分享块显示的内容 */
	@FormParam("shareContent")
	private String shareContent = "";

	public int getItemId() {
		return itemId;
	}

	public void setItemId(int itemId) {
		this.itemId = itemId;
	}

	public long getBid() {
		return bid;
	}

	public void setBid(long bid) {
		this.bid = bid;
	}

	public short getStatus() {
		return status;
	}

	public void setStatus(short status) {
		this.status = status;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public String getTarget() {
		return target;
	}

	public void setTarget(String target) {
		this.target = target;
	}

	public long getTargetId() {
		return targetId;
	}

	public void setTargetId(long targetId) {
		this.targetId = targetId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	public int getOriginalPrice() {
		return originalPrice;
	}

	public void setOriginalPrice(int originalPrice) {
		this.originalPrice = originalPrice;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public short getCanShare() {
		return canShare;
	}

	public void setCanShare(short canShare) {
		this.canShare = canShare;
	}

	public String getTargetParams() {
		return targetParams;
	}

	public void setTargetParams(String targetParams) {
		this.targetParams = targetParams;
	}

	public int getSequence() {
		return sequence;
	}

	public void setSequence(int sequence) {
		this.sequence = sequence;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getModifyTime() {
		return modifyTime;
	}

	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}

	public long getAdminId() {
		return adminId;
	}

	public void setAdminId(long adminId) {
		this.adminId = adminId;
	}

	public String getShareImg() {
		return shareImg;
	}

	public void setShareImg(String shareImg) {
		this.shareImg = shareImg;
	}

	public String getShareTitle() {
		return shareTitle;
	}

	public void setShareTitle(String shareTitle) {
		this.shareTitle = shareTitle;
	}

	public String getShareTimelineTitle() {
		return shareTimelineTitle;
	}

	public void setShareTimelineTitle(String shareTimelineTitle) {
		this.shareTimelineTitle = shareTimelineTitle;
	}

	public String getShareContent() {
		return shareContent;
	}

	public void setShareContent(String shareContent) {
		this.shareContent = shareContent;
	}

}
