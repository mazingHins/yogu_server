package com.yogu.services.order.resource.vo.pay;


/**
 * 调用支付宝SDK所需信息
 * @author Hins
 * @date 2015年8月31日 下午4:34:35
 */
public class AlipayPayVO{
	
	/**
	 * 接口名称
	 */
	private String service;
	
	/**
	 * 签名
	 */
	private String sign;
	
	/**
	 * 支付宝合作者id,加密后的
	 */
	private String partner;
	
	/**
	 * 加密方式
	 */
	private String signType;
	
	/**
	 * 回调地址
	 */
	private String notifyUrl;
	
	/**
	 * 卖家支付宝号
	 */
	private String sellerId;
	
	/**
	 * 参数编码字符集
	 */
	private String inputCharset;
	
	/**
	 * 支付类型。默认值为：1（商品购买）。
	 */
	private String paymentType;
	
	/**
	 * 商品详情
	 */
	private String body;
	
	/**
	 * 商品名称
	 */
	private String subject;
	
	/**
	 * 未付款交易的超时时间
	 */
	private String itBPay;
	

	public String getService() {
		return service;
	}

	public void setService(String service) {
		this.service = service;
	}

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	public String getPartner() {
		return partner;
	}

	public void setPartner(String partner) {
		this.partner = partner;
	}

	public String getSignType() {
		return signType;
	}

	public void setSignType(String signType) {
		this.signType = signType;
	}

	public String getNotifyUrl() {
		return notifyUrl;
	}

	public void setNotifyUrl(String notifyUrl) {
		this.notifyUrl = notifyUrl;
	}

	public String getSellerId() {
		return sellerId;
	}

	public void setSellerId(String sellerId) {
		this.sellerId = sellerId;
	}

	public String getInputCharset() {
		return inputCharset;
	}

	public void setInputCharset(String inputCharset) {
		this.inputCharset = inputCharset;
	}

	public String getPaymentType() {
		return paymentType;
	}

	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getItBPay() {
		return itBPay;
	}

	public void setItBPay(String itBPay) {
		this.itBPay = itBPay;
	}
	
}
