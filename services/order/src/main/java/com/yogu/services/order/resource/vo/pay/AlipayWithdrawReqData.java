package com.yogu.services.order.resource.vo.pay;

/**
 * 支付宝提现SDK请求数据VO
 * @author felix
 * @date 2015/9/21
 * */
public class AlipayWithdrawReqData {
	/**
	 * 接口名称
	 */
	private String service;
	
	/**
	 * 合作者身份ID
	 */
	private String partner;
	
	/**
	 * 参数编码字符集
	 */
	private String inputCharset;
	
	/**
	 * 签名方式
	 */
	private String signType;
	
	/**
	 * 签名
	 */
	private String sign;
	
	/**
	 * 服务器异步通知页面路径
	 */
	private String notifyUrl;
	
	/**
	 * 付款方支付宝账户名(米星公司名)
	 * eg. mazing
	 */
	private String accountName;
	
	/**
	 * 单笔数据集
	 */
	private String detailData;
	
	/**
	 * 退款批次号
	 */
	private String batchNo;
	
	/**
	 * 总笔数
	 */
	private String batchNum;
	
	/**
	 * 付款总金额
	 */
	private String batchFee;
	
	/**
	 * 付款方支付宝账户
	 * eg. 123456@123.com
	 */
	private String email;
	
	/**
	 * 支付时间
	 * eg. YYYYMMDD
	 * */
	private String payDate;

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

	public String getInputCharset() {
		return inputCharset;
	}

	public void setInputCharset(String inputCharset) {
		this.inputCharset = inputCharset;
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

	public String getNotifyUrl() {
		return notifyUrl;
	}

	public void setNotifyUrl(String notifyUrl) {
		this.notifyUrl = notifyUrl;
	}

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public String getDetailData() {
		return detailData;
	}

	public void setDetailData(String detailData) {
		this.detailData = detailData;
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

	public String getBatchFee() {
		return batchFee;
	}

	public void setBatchFee(String batchFee) {
		this.batchFee = batchFee;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPayDate() {
		return payDate;
	}

	public void setPayDate(String payDate) {
		this.payDate = payDate;
	}
}
