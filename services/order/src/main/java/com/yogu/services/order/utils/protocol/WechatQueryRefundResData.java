package com.yogu.services.order.utils.protocol;

/**
 * 查询退款api接口返回数据
 * 
 * @author Hins
 * @date 2016年2月15日 下午4:07:06
 */
public class WechatQueryRefundResData extends WechatBaseResData {

	private String transaction_id = "";		//	微信订单号
	private String out_trade_no = "";		//	商户订单号
	private int total_fee = 0;				//	订单总金额，单位分
	private String fee_type = "";			//	订单金额货币种类，CNY

	private String refund_status_0 = "";	//	退款状态SUCCESS—退款成功;FAIL—退款失败;PROCESSING—退款处理中;CHANGE—转入代发
	private String refund_recv_accout_0 = "";	//	退款入账账户，1）退回银行卡：2）退回支付用户零钱:

	// 2017-01-09 add by hins 。增加多一些返回内容，以后微信退款相关逻辑要优化
	private String out_refund_no_0 = "";	// 商户退款单号
	private String refund_id_0 = "";	// 微信退款编号
	private String refund_fee_0 = "";	// 退款金额
	
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

	public int getTotal_fee() {
		return total_fee;
	}

	public void setTotal_fee(int total_fee) {
		this.total_fee = total_fee;
	}

	public String getFee_type() {
		return fee_type;
	}

	public void setFee_type(String fee_type) {
		this.fee_type = fee_type;
	}

	public String getRefund_status_0() {
		return refund_status_0;
	}

	public void setRefund_status_0(String refund_status_0) {
		this.refund_status_0 = refund_status_0;
	}

	public String getRefund_recv_accout_0() {
		return refund_recv_accout_0;
	}

	public void setRefund_recv_accout_0(String refund_recv_accout_0) {
		this.refund_recv_accout_0 = refund_recv_accout_0;
	}

	public String getRefund_id_0() {
		return refund_id_0;
	}

	public void setRefund_id_0(String refund_id_0) {
		this.refund_id_0 = refund_id_0;
	}

	public String getRefund_fee_0() {
		return refund_fee_0;
	}

	public void setRefund_fee_0(String refund_fee_0) {
		this.refund_fee_0 = refund_fee_0;
	}

	public String getOut_refund_no_0() {
		return out_refund_no_0;
	}

	public void setOut_refund_no_0(String out_refund_no_0) {
		this.out_refund_no_0 = out_refund_no_0;
	}
	
}
