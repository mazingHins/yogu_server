package com.yogu.services.order.resource.vo.pay;

/**
 * 支付宝退款SDK请求数据VO
 * @author Hins
 * @date 2015年9月14日 下午3:24:09
 */
public class AlipayRefundReqData {
	
	/**
	 * 接口名称
	 */
	private String service;
	
	/**
	 * 合作者身份ID
	 */
	private String partner;
	
	/**
	 * 卖家用户ID
	 */
	private String sellerUserId;
	
	/**
	 * 参数编码字符集
	 */
	private String inputCharset;
	
	/**
	 * 服务器异步通知页面路径
	 */
	private String notifyUrl;
	
	/**
	 * 卖家支付宝账号
	 */
	private String sellerEmail;
	
	/**
	 * 退款请求时间
	 */
	private String refundDate;
	
	/**
	 * 退款批次号
	 */
	private String batchNo;
	
	/**
	 * 总笔数
	 */
	private String batchNum;
	
	/**
	 * 单笔数据集
	 */
	private String detailData;
	
	/**
	 * 签名方式
	 */
	private String signType;
	
	/**
	 * 签名
	 */
	private String sign;

	public String getService() {
		return service;
	}

	public void setService(String service) {
		this.service = service;
	}

	public String getPartner() {
		return partner;
	}

	public void setPartner(String partner) {
		this.partner = partner;
	}

	public String getSellerUserId() {
		return sellerUserId;
	}

	public void setSellerUserId(String sellerUserId) {
		this.sellerUserId = sellerUserId;
	}

	public String getInputCharset() {
		return inputCharset;
	}

	public void setInputCharset(String inputCharset) {
		this.inputCharset = inputCharset;
	}

	public String getNotifyUrl() {
		return notifyUrl;
	}

	public void setNotifyUrl(String notifyUrl) {
		this.notifyUrl = notifyUrl;
	}

	public String getSellerEmail() {
		return sellerEmail;
	}

	public void setSellerEmail(String sellerEmail) {
		this.sellerEmail = sellerEmail;
	}

	public String getRefundDate() {
		return refundDate;
	}

	public void setRefundDate(String refundDate) {
		this.refundDate = refundDate;
	}

	public String getBatchNo() {
		return batchNo;
	}

	public void setBatchNo(String batchNo) {
		this.batchNo = batchNo;
	}

	public String getBatchNum() {
		return batchNum;
	}

	public void setBatchNum(String batchNum) {
		this.batchNum = batchNum;
	}

	public String getDetailData() {
		return detailData;
	}

	public void setDetailData(String detailData) {
		this.detailData = detailData;
	}

	public String getSignType() {
		return signType;
	}

	public void setSignType(String signType) {
		this.signType = signType;
	}

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}
	
}
