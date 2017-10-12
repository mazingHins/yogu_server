package com.yogu.services.order.resource.vo;

import java.io.Serializable;
import java.util.List;

/**
 * 订单相关的优惠券
 * 
 * @author Hins
 * @date 2015年12月23日 下午6:51:00
 */
public class OrderCouponVO implements Serializable {

	private static final long serialVersionUID = -9220479149724760592L;

	/** 优惠券id */
	private long couponId;

	/** 券的显示名 */
	private String couponName = "";

	/** 券的显示描述 */
	private List<CouponDescVO> descriptionItems;

	/** 优惠券类型 1：现金抵用券 2：折扣券 3：满减券 */
	private short couponType;

	/** 真正优惠的价格（单位分） */
	private long couponFee;

	/** 使用状态 1：未使用 2：使用中 3：已使用 4：已失效 */
	private short showStatus;

	/** 当前订单是否占用此优惠券，1-是，其他-否 */
	private short isOccupy = 0;

	/** 当前优惠券是否默认选中，1-是，其他否 */
	private short isSelect = 0;
	
	/**
	 * 优惠券可支持的支付方式
	 */
	private String supportPayMode;
	
	/**
	 * 面值显示文本信息
	 */
	private String faceValueText;
	
	/**
	 * 客户端优惠券button标题( 2015-12-26 新增 )
	 */
	private String couponTitle;
	
	/** 折扣券最高优惠金额 **/
	private long mostOffer = 0;

	public long getCouponId() {
		return couponId;
	}

	public void setCouponId(long couponId) {
		this.couponId = couponId;
	}

	public String getCouponName() {
		return couponName;
	}

	public void setCouponName(String couponName) {
		this.couponName = couponName;
	}

	public List<CouponDescVO> getDescriptionItems() {
		return descriptionItems;
	}

	public void setDescriptionItems(List<CouponDescVO> descriptionItems) {
		this.descriptionItems = descriptionItems;
	}

	public short getCouponType() {
		return couponType;
	}

	public void setCouponType(short couponType) {
		this.couponType = couponType;
	}

	public long getCouponFee() {
		return couponFee;
	}

	public void setCouponFee(long couponFee) {
		this.couponFee = couponFee;
	}

	public short getShowStatus() {
		return showStatus;
	}

	public void setShowStatus(short showStatus) {
		this.showStatus = showStatus;
	}

	public short getIsOccupy() {
		return isOccupy;
	}

	public void setIsOccupy(short isOccupy) {
		this.isOccupy = isOccupy;
	}

	public short getIsSelect() {
		return isSelect;
	}

	public void setIsSelect(short isSelect) {
		this.isSelect = isSelect;
	}

	public String getSupportPayMode() {
		return supportPayMode;
	}

	public void setSupportPayMode(String supportPayMode) {
		this.supportPayMode = supportPayMode;
	}

	public String getFaceValueText() {
		return faceValueText;
	}

	public void setFaceValueText(String faceValueText) {
		this.faceValueText = faceValueText;
	}

	public String getCouponTitle() {
		return couponTitle;
	}

	public void setCouponTitle(String couponTitle) {
		this.couponTitle = couponTitle;
	}

	public long getMostOffer() {
		return mostOffer;
	}

	public void setMostOffer(long mostOffer) {
		this.mostOffer = mostOffer;
	}
	
}
