package com.yogu.services.order.resource.vo;

/**
 * 商家可选配送员信息VO
 *
 * @date 2016年10月16日 下午6:55:32
 * @author hins
 */
public class StoreDSVO {
	
	/**
	 * 用户ID
	 */
	private long uid;
	
	/**
	 * 昵称
	 */
	private String nickname;
	
	/**
	 * 手机号码
	 */
	private String mobile;
	
	/**
	 * 用户的IMID
	 */
	private long imId;
	
	/**
	 * 是否第三方配送人员，1：是，其他否
	 */
	private short isThirdExpress;
	
	/**
	 * 是否可以点击选择，1:是，其他否
	 */
	private short canSelect;
	
	/**
	 * 配送备注
	 */
	private String deliveryRemark;
	
	/**
	 * 禁选原因提示
	 */
	private String disableMsg;
	
	/**
	 * 是否默认选中，1：是，其他否
	 */
	private short isDefault;
	
	/**
	 * 第三方配送code
	 */
	private long expressCode;

	public long getUid() {
		return uid;
	}

	public void setUid(long uid) {
		this.uid = uid;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public long getImId() {
		return imId;
	}

	public void setImId(long imId) {
		this.imId = imId;
	}

	public short getIsThirdExpress() {
		return isThirdExpress;
	}

	public void setIsThirdExpress(short isThirdExpress) {
		this.isThirdExpress = isThirdExpress;
	}

	public short getCanSelect() {
		return canSelect;
	}

	public void setCanSelect(short canSelect) {
		this.canSelect = canSelect;
	}

	public String getDeliveryRemark() {
		return deliveryRemark;
	}

	public void setDeliveryRemark(String deliveryRemark) {
		this.deliveryRemark = deliveryRemark;
	}

	public String getDisableMsg() {
		return disableMsg;
	}

	public void setDisableMsg(String disableMsg) {
		this.disableMsg = disableMsg;
	}

	public short getIsDefault() {
		return isDefault;
	}

	public void setIsDefault(short isDefault) {
		this.isDefault = isDefault;
	}

	public long getExpressCode() {
		return expressCode;
	}

	public void setExpressCode(long expressCode) {
		this.expressCode = expressCode;
	}
	
}
