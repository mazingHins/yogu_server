package com.yogu.services.order.utils.protocol;

/**
 * 微信统一下单api接口返回数据
 * 
 * @author Hins
 * @date 2016年2月17日 上午10:35:29
 */
public class WechatUnifiedOrderResData extends WechatBaseResData {

	// 业务返回的具体数据（以下字段在return_code 和result_code 都为SUCCESS 的时候有返回）
	private String trade_type = "";		//	交易类型，取值范围：JSAPI，NATIVE，APP。我们是APP
	private String prepay_id = "";		//	预支付交易会话标识
	private String code_url = "";		

	public String getTrade_type() {
		return trade_type;
	}

	public void setTrade_type(String trade_type) {
		this.trade_type = trade_type;
	}

	public String getPrepay_id() {
		return prepay_id;
	}

	public void setPrepay_id(String prepay_id) {
		this.prepay_id = prepay_id;
	}

	public String getCode_url() {
		return code_url;
	}

	public void setCode_url(String code_url) {
		this.code_url = code_url;
	}

}
