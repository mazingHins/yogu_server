package com.yogu.services.order.pay.entry;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 微信支付回调通知记录表
 * 
 */
public class WechatPayNotifyPO implements Serializable {

	private static final long serialVersionUID = -7634980294908820069L;

	/** 通知记录ID */
	private long notifyId;

	/** 支付记录ID */
	private long payId;
	
	/** 支付记录NO，也是请求微信支付的商户订单号 */
	private long payNo;

	/** 微信支付订单号 */
	private String transactionId;
	
	/** 公众账号ID */
	private String appId;

	/** 货币类型，默认CNY */
	private String feeType = "";

	/** 交易金额(分为单位) */
	private long totalFee = 0;

	/** 业务结果，1-成功；2-失败 */
	private short resultCode;

	/** 错误代码 */
	private String errCode;

	/** 错误代码描述 */
	private String errCodeDes;

	/** 用户在商户appid下的唯一标识，加密 */
	private String openid;

	/** 用户付款银行类型，直接微信回调通知结果的值，如ICBC_DEBIT=工商银行（借记卡） */
	private String bankType;

	/** 交易结果状态：1-成功；2-失败 */
	private short status = 0;

	/** 支付完成时间 */
	private Date tradePayTime;

	/** 创建时间 */
	private Date createTime;

	public void setNotifyId(long notifyId) {
		this.notifyId = notifyId;
	}

	public long getNotifyId() {
		return notifyId;
	}

	public void setPayId(long payId) {
		this.payId = payId;
	}

	public long getPayId() {
		return payId;
	}
	
	public long getPayNo() {
		return payNo;
	}

	public void setPayNo(long payNo) {
		this.payNo = payNo;
	}

	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}

	public String getTransactionId() {
		return transactionId;
	}
	
	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public void setFeeType(String feeType) {
		this.feeType = feeType;
	}

	public String getFeeType() {
		return feeType;
	}

	public void setTotalFee(long totalFee) {
		this.totalFee = totalFee;
	}

	public long getTotalFee() {
		return totalFee;
	}

	public short getResultCode() {
		return resultCode;
	}

	public void setResultCode(short resultCode) {
		this.resultCode = resultCode;
	}

	public Date getTradePayTime() {
		return tradePayTime;
	}

	public void setTradePayTime(Date tradePayTime) {
		this.tradePayTime = tradePayTime;
	}

	public void setErrCode(String errCode) {
		this.errCode = errCode;
	}

	public String getErrCode() {
		return errCode;
	}

	public void setErrCodeDes(String errCodeDes) {
		this.errCodeDes = errCodeDes;
	}

	public String getErrCodeDes() {
		return errCodeDes;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}

	public String getOpenid() {
		return openid;
	}

	public void setBankType(String bankType) {
		this.bankType = bankType;
	}

	public String getBankType() {
		return bankType;
	}

	public void setStatus(short status) {
		this.status = status;
	}

	public short getStatus() {
		return status;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}
}
