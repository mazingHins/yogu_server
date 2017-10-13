package com.yogu.services.order.resource.vo.coupon;

import java.util.List;

/**
 * 用户拥有的优惠券列表VO
 * 
 * @author Hins
 * @date 2015年12月23日 下午4:24:02
 */
public class UserCouponVO {

	/** 主键id */
	private long couponId;

	/** 券的显示名 */
	private String couponName = "";
	
	/** 券的显示描述 */
	private List<CouponDescVO> descriptionItems;

	/** 优惠券类型 1：现金抵用券 2：折扣券 3：满减券 */
	private short couponType;

	/** 使用状态 1：未使用 2：使用中 3：已使用 4：已失效 */
	private short showStatus;

	/** 面值显示文本信息 */
	private String faceValueText;
	
	/**
	 * 优惠券能否分享 1：能；其他：否
	 */
	private short canShare;
	
	public List<CouponDescVO> getDescriptionItems() {
		return descriptionItems;
	}

	public void setDescriptionItems(List<CouponDescVO> descriptionItems) {
		this.descriptionItems = descriptionItems;
	}

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

	public short getShowStatus() {
		return showStatus;
	}

	public void setShowStatus(short showStatus) {
		this.showStatus = showStatus;
	}

	public String getFaceValueText() {
		return faceValueText;
	}

	public void setFaceValueText(String faceValueText) {
		this.faceValueText = faceValueText;
	}

	public short getCanShare() {
		return canShare;
	}

	public void setCanShare(short canShare) {
		this.canShare = canShare;
	}
	
	
}
