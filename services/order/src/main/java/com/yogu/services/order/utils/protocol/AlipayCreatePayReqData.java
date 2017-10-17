package com.yogu.services.order.utils.protocol;


/**
 * 支付宝请求支付api的请求数据
 * 
 * @author Hins
 * @date 2016年2月18日 上午11:51:39
 */
public class AlipayCreatePayReqData {

	// 签约合作者身份ID
	private String partner;

	// 签约卖家支付宝账号
	private String seller_id;

	// 商户网站唯一订单号
	private String out_trade_no;

	// 商品名称
	private String subject;

	// 商品详情
	private String body;

	// 商品金额
	private String total_fee;

	// 服务器异步通知页面路径
	private String notify_url;

	// 服务接口名称， 固定值
	private String service;

	// 支付类型， 固定值
	private String payment_type = "1";

	// 参数编码， 固定值
	private String _input_charset;

	// 设置未付款交易的超时时间
	// 默认30分钟，一旦超时，该笔交易就会自动被关闭。
	// 取值范围：1m～15d。
	// m-分钟，h-小时，d-天，1c-当天（无论交易何时创建，都在0点关闭）。
	// 该参数数值不接受小数点，如1.5h，可转换为90m。
	private String it_b_pay;
	
	private String sign;
	
	public AlipayCreatePayReqData(){
		
	}

	public String getPartner() {
		return partner;
	}

	public void setPartner(String partner) {
		this.partner = partner;
	}

	public String getSeller_id() {
		return seller_id;
	}

	public void setSeller_id(String seller_id) {
		this.seller_id = seller_id;
	}

	public String getOut_trade_no() {
		return out_trade_no;
	}

	public void setOut_trade_no(String out_trade_no) {
		this.out_trade_no = out_trade_no;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public String getTotal_fee() {
		return total_fee;
	}

	public void setTotal_fee(String total_fee) {
		this.total_fee = total_fee;
	}

	public String getNotify_url() {
		return notify_url;
	}

	public void setNotify_url(String notify_url) {
		this.notify_url = notify_url;
	}

	public String getService() {
		return service;
	}

	public void setService(String service) {
		this.service = service;
	}

	public String getPayment_type() {
		return payment_type;
	}

	public void setPayment_type(String payment_type) {
		this.payment_type = payment_type;
	}

	public String get_input_charset() {
		return _input_charset;
	}

	public void set_input_charset(String _input_charset) {
		this._input_charset = _input_charset;
	}

	public String getIt_b_pay() {
		return it_b_pay;
	}

	public void setIt_b_pay(String it_b_pay) {
		this.it_b_pay = it_b_pay;
	}

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}
	
}
