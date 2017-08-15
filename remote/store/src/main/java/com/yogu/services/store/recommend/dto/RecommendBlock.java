package com.yogu.services.store.recommend.dto;

import java.io.Serializable;
import java.util.Date;

import javax.ws.rs.FormParam;

/**
 * 推荐页中的块
 * 
 */
public class RecommendBlock implements Serializable {

	private static final long serialVersionUID = -3074457343737703605L;

	/** 主键id */
	@FormParam("bid")
	private long bid;

	/** 城市code */
	@FormParam("cityCode")
	private String cityCode;

	/** 语言CODE */
	@FormParam("langCode")
	private String langCode;

	/** 块标题 */
	@FormParam("title")
	private String title;

	/** 块类型 adver: 广告 store :餐厅列表 dish :美食列表 */
	@FormParam("type")
	private String type;

	/** 是否自动填充数据（只在type=store的时候使用到）；1：是、其他：否 */
	@FormParam("autoPush")
	private short autoPush;

	/**
	 * 是否自动排序（只在type=store|dish的时候起作用）；1：是、其他：否<br>
	 * 对设置的item项（餐厅|美食）进行条件性排序<br>
	 * jfan 20160304
	 */
	@FormParam("autoSorting")
	private short autoSorting;

	/**
	 * 点击块标题的动作；（空字符串，表示标题不可点击）<br>
	 * h5：打开html5页面<br>
	 * index：前往瀑布流<br>
	 */
	@FormParam("action")
	private String action = "";

	/**
	 * 打开 html5 页面的地址
	 */
	@FormParam("url")
	private String url = "";

	/**
	 * 是否可分享 1:是 0:否
	 */
	@FormParam("canShare")
	private short canShare;

	/**
	 * 此数据项 附带的参数值<br>
	 * 不同的action，作用不一样
	 */
	@FormParam("actionParams")
	private String actionParams = "";

	/**
	 * 分享时分享块显示的图标（图片相对地址）
	 */
	@FormParam("shareImg")
	private String shareImg = "";

	/**
	 * 分享块显示的标题
	 */
	@FormParam("shareTitle")
	private String shareTitle = "";

	/**
	 * 分享块显示的内容
	 */
	@FormParam("shareContent")
	private String shareContent = "";

	/** 状态 0:未启用 1:启用 */
	private short status = 0;

	/**
	 * 每日显示时段起始分钟数（0表示00:00,1440表示24:00）
	 */
	@FormParam("activeStartMinute")
	private int activeStartMinute;

	/**
	 * 每日显示时段截止分钟数（0表示00:00,1440表示24:00）
	 */
	@FormParam("activeEndMinute")
	private int activeEndMinute;

	/**
	 * 根据用户登录状态判定是否显示；1：登录才显示、0：没有登录才显示、其他：不限定（建议使用-1）
	 */
	@FormParam("activeLoginStatus")
	private short activeLoginStatus = -1;

	/** 排序号 */
	private int sequence;

	/** 创建时间 */
	private Date createTime;

	/** 修改时间 */
	private Date modifyTime;

	/** 创建人id */
	@FormParam("adminId")
	private long adminId;

	public long getBid() {
		return bid;
	}

	public void setBid(long bid) {
		this.bid = bid;
	}

	public String getCityCode() {
		return cityCode;
	}

	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}

	public String getLangCode() {
		return langCode;
	}

	public void setLangCode(String langCode) {
		this.langCode = langCode;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public short getAutoPush() {
		return autoPush;
	}

	public void setAutoPush(short autoPush) {
		this.autoPush = autoPush;
	}

	public short getAutoSorting() {
		return autoSorting;
	}

	public void setAutoSorting(short autoSorting) {
		this.autoSorting = autoSorting;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
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

	public String getActionParams() {
		return actionParams;
	}

	public void setActionParams(String actionParams) {
		this.actionParams = actionParams;
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

	public String getShareContent() {
		return shareContent;
	}

	public void setShareContent(String shareContent) {
		this.shareContent = shareContent;
	}

	public short getStatus() {
		return status;
	}

	public void setStatus(short status) {
		this.status = status;
	}

	public int getActiveStartMinute() {
		return activeStartMinute;
	}

	public void setActiveStartMinute(int activeStartMinute) {
		this.activeStartMinute = activeStartMinute;
	}

	public int getActiveEndMinute() {
		return activeEndMinute;
	}

	public void setActiveEndMinute(int activeEndMinute) {
		this.activeEndMinute = activeEndMinute;
	}

	public short getActiveLoginStatus() {
		return activeLoginStatus;
	}

	public void setActiveLoginStatus(short activeLoginStatus) {
		this.activeLoginStatus = activeLoginStatus;
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

}
