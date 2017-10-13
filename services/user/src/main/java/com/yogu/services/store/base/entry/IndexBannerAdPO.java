package com.yogu.services.store.base.entry;

import java.io.Serializable;
import java.util.Date;

/**
 * 商品标签
 */
public class IndexBannerAdPO implements Serializable {

	private static final long serialVersionUID = 4873047941381832215L;

	/** 标签ID */
	private long adId;

	/** 展示图片地址 */
	private String pic;

	/** 广告类型学，1-商品；2-h5 */
	private short adType;
	
	/** 关联内容json */
	private String content;
	
	/** 状态；1-正常；其他-下架 */
	private short status;
	
	/** 创建时间 */
	private Date createTime;

	public long getAdId() {
		return adId;
	}

	public void setAdId(long adId) {
		this.adId = adId;
	}

	public String getPic() {
		return pic;
	}

	public void setPic(String pic) {
		this.pic = pic;
	}

	public short getAdType() {
		return adType;
	}

	public void setAdType(short adType) {
		this.adType = adType;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	public short getStatus() {
		return status;
	}

	public void setStatus(short status) {
		this.status = status;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
}
