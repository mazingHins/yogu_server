package com.yogu.services.order.utils.protocol;

/**
 * 微信api返回数据的基类
 * 
 * @author Hins
 * @date 2016年2月17日 上午10:52:38
 */
public class WechatBaseResData {

	// 协议层
	private String return_code = "";	//	返回状态码 SUCCESS/FAIL
	private String return_msg = "";		// 返回信息

	// 协议返回的具体数据（以下字段在return_code 为SUCCESS 的时候有返回）
	private String appid = "";		//	应用ID
	private String mch_id = "";		//	商户号
	private String nonce_str = "";	//	随机字符串
	private String sign = "";			//	签名
	private String result_code = "";	//	业务结果
	private String err_code = "";		//	错误代码
	private String err_code_des = "";	//	错误代码描述
	private String device_info = "";	//	设备号

	public String getReturn_code() {
		return return_code;
	}

	public void setReturn_code(String return_code) {
		this.return_code = return_code;
	}

	public String getReturn_msg() {
		return return_msg;
	}

	public void setReturn_msg(String return_msg) {
		this.return_msg = return_msg;
	}

	public String getAppid() {
		return appid;
	}

	public void setAppid(String appid) {
		this.appid = appid;
	}

	public String getMch_id() {
		return mch_id;
	}

	public void setMch_id(String mch_id) {
		this.mch_id = mch_id;
	}

	public String getNonce_str() {
		return nonce_str;
	}

	public void setNonce_str(String nonce_str) {
		this.nonce_str = nonce_str;
	}

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	public String getResult_code() {
		return result_code;
	}

	public void setResult_code(String result_code) {
		this.result_code = result_code;
	}

	public String getErr_code() {
		return err_code;
	}

	public void setErr_code(String err_code) {
		this.err_code = err_code;
	}

	public String getErr_code_des() {
		return err_code_des;
	}

	public void setErr_code_des(String err_code_des) {
		this.err_code_des = err_code_des;
	}

	public String getDevice_info() {
		return device_info;
	}

	public void setDevice_info(String device_info) {
		this.device_info = device_info;
	}

}
