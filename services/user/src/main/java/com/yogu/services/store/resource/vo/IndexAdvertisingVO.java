package com.yogu.services.store.resource.vo;

import java.io.Serializable;

/**
 * app首页广告信息
 * 
 * @author qiujun   
 * @date 2017年9月13日 下午9:20:42
 */
public class IndexAdvertisingVO implements Serializable {

	private static final long serialVersionUID = -1716770017839918049L;
	
	/**
	 * 广告id
	 */
	private long adId;
	
	/**
	 * 展示图片地址
	 */
	private String pic;
	
	/**
	 * 广告类型：1-跳转到商品详情；2-h5url
	 */
	private short adType;
	
	/**
	 * 商品id
	 */
	private long goodsKey;
	
	/**
	 * 跳转的url链接
	 */
	private String linkUrl;

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

	public long getGoodsKey() {
		return goodsKey;
	}

	public void setGoodsKey(long goodsKey) {
		this.goodsKey = goodsKey;
	}

	public String getLinkUrl() {
		return linkUrl;
	}

	public void setLinkUrl(String linkUrl) {
		this.linkUrl = linkUrl;
	}
	
}
