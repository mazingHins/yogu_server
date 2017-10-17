package com.yogu.services.order.utils.protocol;

/**
 * 申请退款api返回数据
 * 
 * @author Hins
 * @date 2016年2月3日 下午4:59:33
 */
public class WechatApplyRefundResData extends WechatBaseResData {

	// 协议返回的具体数据（以下字段在return_code 为SUCCESS 的时候有返回）
	private String sub_mch_id = "";

	private String transaction_id = "";		//	微信订单号
	private String out_trade_no = "";		//	商户订单号
	private String out_refund_no = "";		//	商户退款单号
	private String refund_id = "";			//	微信退款单号
	private String refund_fee = "";			//	退款金额，单位分
	private String coupon_refund_fee = "";	//	代金券或立减优惠退款金额，单位分
	private String fee_type = "";			//	订单金额货币种类,CNY

	public String getSub_mch_id() {
		return sub_mch_id;
	}

	public void setSub_mch_id(String sub_mch_id) {
		this.sub_mch_id = sub_mch_id;
	}

	public String getTransaction_id() {
		return transaction_id;
	}

	public void setTransaction_id(String transaction_id) {
		this.transaction_id = transaction_id;
	}

	public String getOut_trade_no() {
		return out_trade_no;
	}

	public void setOut_trade_no(String out_trade_no) {
		this.out_trade_no = out_trade_no;
	}

	public String getOut_refund_no() {
		return out_refund_no;
	}

	public void setOut_refund_no(String out_refund_no) {
		this.out_refund_no = out_refund_no;
	}

	public String getRefund_id() {
		return refund_id;
	}

	public void setRefund_id(String refund_id) {
		this.refund_id = refund_id;
	}

	public String getRefund_fee() {
		return refund_fee;
	}

	public void setRefund_fee(String refund_fee) {
		this.refund_fee = refund_fee;
	}

	public String getCoupon_refund_fee() {
		return coupon_refund_fee;
	}

	public void setCoupon_refund_fee(String coupon_refund_fee) {
		this.coupon_refund_fee = coupon_refund_fee;
	}

	public String getFee_type() {
		return fee_type;
	}

	public void setFee_type(String fee_type) {
		this.fee_type = fee_type;
	}

	
}
