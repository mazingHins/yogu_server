package com.yogu.services.order.resource.vo.pay;

/**
 * 微信调起支付参数VO
 * 
 * @author Hins
 * @date 2016年1月30日 下午5:14:10
 */
public class WechatPayVO {
	
	/**
	 * 微信公众账号ID
	 */
	private String appid;
	
	/**
	 * 微信商户号
	 */
	private String partnerId;
	
	/**
	 * 预支付交易会话ID
	 */
	private String prepayId;
	
	/**
	 * 扩展字段
	 */
	private String packageStr;
	
	/**
	 * 随机字符串
	 */
	private String noncestr;
	
	/**
	 * 时间戳
	 */
	private String timestamp;
	
	/**
	 * 签名
	 */
	private String sign;

	public String getAppid() {
		return appid;
	}

	public void setAppid(String appid) {
		this.appid = appid;
	}

	public String getPartnerId() {
		return partnerId;
	}

	public void setPartnerId(String partnerId) {
		this.partnerId = partnerId;
	}

	public String getPrepayId() {
		return prepayId;
	}

	public void setPrepayId(String prepayId) {
		this.prepayId = prepayId;
	}

	public String getPackageStr() {
		return packageStr;
	}

	public void setPackageStr(String packageStr) {
		this.packageStr = packageStr;
	}

	public String getNoncestr() {
		return noncestr;
	}

	public void setNoncestr(String noncestr) {
		this.noncestr = noncestr;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

}
