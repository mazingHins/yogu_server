package com.yogu.services.store.news.dto;

import java.io.Serializable;
import java.util.Date;

import javax.ws.rs.FormParam;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 资讯首页专题数据块
 * 
 */
public class NewsBlock implements Serializable {

	private static final long serialVersionUID = -3074457343598312625L;

	/** 主键id */
	@FormParam("bid")
	private long bid;

	/** 城市code */
	@FormParam("cityCode")
	private String cityCode = "020";

	/** 块类型 */
	@FormParam("type")
	private String type = "";

	/** 点击后的跳转动作 */
	@FormParam("action")
	private String action = "";

	/** 数据块展示图 */
	@FormParam("img")
	private String img = "";

	/** 数据块标题 */
	@FormParam("title")
	private String title = "";

	/** 作者 */
	@FormParam("author")
	private String author = "";

	/** 作者 头像*/
	@FormParam("authorPic")
	private String authorPic = "";

	/** 发布时间 */
	// @FormParam("pubTime")
	private Date pubTime;

	/** 发布时间的长整形 */
	@FormParam("pubTime")
	private long pubTimeLong;

	/** 阅读数 */
	@FormParam("readCount")
	private int readCount = 0;

	/** 跳转url */
	@FormParam("url")
	private String url = "";

	/** 是否可分享 */
	@FormParam("canShare")
	private short canShare = 1;

	/** 是否可用 0：不可用 1：可用 */
	@FormParam("status")
	private short status = 1;

	/** 排序号 */
	@FormParam("sequence")
	private int sequence = 1;

	/** 创建时间 */
	// @FormParam("createTime")
	private Date createTime;

	/** 更新时间 */
	// @FormParam("updateTime")
	private Date updateTime;

	/** 管理员id */
	@FormParam("adminId")
	private long adminId = 0;

	/** 分享时的图标 */
	@FormParam("shareImg")
	private String shareImg = "";

	/** 分享数据项给朋友显示的标题 */
	@FormParam("shareTitle")
	private String shareTitle = "";

	/** 分享数据项到朋友圈显示的标题 */
	@FormParam("shareTimelineTitle")
	private String shareTimelineTitle = "";

	/** 分享专题显示的内容 */
	@FormParam("shareContent")
	private String shareContent = "";

	/** 是否显示发布时间 0：否 1：是 **/
	@FormParam("showPub")
	private short showPub = 0;

	/** 语言CODE */
	@FormParam("langCode")
	private String langCode = "zh";

	public String getLangCode() {
		return langCode;
	}

	public void setLangCode(String langCode) {
		this.langCode = langCode;
	}

	public short getShowPub() {
		return showPub;
	}

	public void setShowPub(short showPub) {
		this.showPub = showPub;
	}

	public void setBid(long bid) {
		this.bid = bid;
	}

	public long getBid() {
		return bid;
	}

	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}

	public String getCityCode() {
		return cityCode;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getType() {
		return type;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public String getAction() {
		return action;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public String getImg() {
		return img;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getTitle() {
		return title;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getAuthor() {
		return author;
	}

	public Date getPubTime() {
		return pubTime;
	}

	public void setPubTime(Date pubTime) {
		this.pubTime = pubTime;
	}

	public long getPubTimeLong() {
		return pubTimeLong;
	}

	public void setPubTimeLong(long pubTimeLong) {
		this.pubTimeLong = pubTimeLong;
	}

	public void setReadCount(int readCount) {
		this.readCount = readCount;
	}

	public int getReadCount() {
		return readCount;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUrl() {
		return url;
	}

	public void setCanShare(short canShare) {
		this.canShare = canShare;
	}

	public short getCanShare() {
		return canShare;
	}

	public void setStatus(short status) {
		this.status = status;
	}

	public short getStatus() {
		return status;
	}

	public void setSequence(int sequence) {
		this.sequence = sequence;
	}

	public int getSequence() {
		return sequence;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setAdminId(long adminId) {
		this.adminId = adminId;
	}

	public long getAdminId() {
		return adminId;
	}

	public void setShareImg(String shareImg) {
		this.shareImg = shareImg;
	}

	public String getShareImg() {
		return shareImg;
	}

	public void setShareTitle(String shareTitle) {
		this.shareTitle = shareTitle;
	}

	public String getShareTitle() {
		return shareTitle;
	}

	public void setShareTimelineTitle(String shareTimelineTitle) {
		this.shareTimelineTitle = shareTimelineTitle;
	}

	public String getShareTimelineTitle() {
		return shareTimelineTitle;
	}

	public void setShareContent(String shareContent) {
		this.shareContent = shareContent;
	}

	public String getShareContent() {
		return shareContent;
	}

	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

	public String getAuthorPic() {
		return authorPic;
	}

	public void setAuthorPic(String authorPic) {
		this.authorPic = authorPic;
	}
}
