package com.yogu.services.order.resource.vo;

import java.util.List;

public class SettleCouponVO {
	
	/** 优惠券id */
	private long couponId;

	/** 券的显示名 */
	private String couponName = "";
	
	/** 券的显示描述 */
	private List<CouponDescVO> descriptionItems;
	
	/** 真正使用的金额（单位分） */
	private long couponFee;
	
	/** 优惠券类型 1：现金抵用券 2：折扣券 3：满减券 */
	private short couponType;

	/** 当前优惠券是否默认选中，1-是，其他否 */
	private short isSelect = 0;
	
	/** 面值显示文本信息 */
	private String faceValueText;
	
	/**
	 * 客户端优惠券button标题( 2015-12-26 新增 )
	 */
	private String couponTitle;

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

	public short getCouponType() {
		return couponType;
	}

	public void setCouponType(short couponType) {
		this.couponType = couponType;
	}

	public short getIsSelect() {
		return isSelect;
	}

	public void setIsSelect(short isSelect) {
		this.isSelect = isSelect;
	}
	
	public String getFaceValueText() {
		return faceValueText;
	}

	public void setFaceValueText(String faceValueText) {
		this.faceValueText = faceValueText;
	}

	public List<CouponDescVO> getDescriptionItems() {
		return descriptionItems;
	}

	public void setDescriptionItems(List<CouponDescVO> descriptionItems) {
		this.descriptionItems = descriptionItems;
	}

	public String getCouponTitle() {
		return couponTitle;
	}

	public void setCouponTitle(String couponTitle) {
		this.couponTitle = couponTitle;
	}

	public long getCouponFee() {
		return couponFee;
	}

	public void setCouponFee(long couponFee) {
		this.couponFee = couponFee;
	}
	
}
