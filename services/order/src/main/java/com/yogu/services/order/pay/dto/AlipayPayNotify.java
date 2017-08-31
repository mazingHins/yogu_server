package com.yogu.services.order.pay.dto;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 支付宝支付回调通知记录表
 * 
 */
public class AlipayPayNotify implements Serializable {

	private static final long serialVersionUID = 2757267573890874847L;

	/** 通知记录ID */
	private long notifyId;

	/** 支付编号 */
	private long payNo;

	/** 支付记录ID */
	private long payId;

	/** 支付宝交易号 */
	private String tradeNo;

	/** 交易状态：1-创建交易，等待买家支付；2-在指定时间段内未支付时关闭的交易，在交易完成全额退款成功时关闭的交易；3-交易成功，且可对该交易做操作；4-交易成功且结束，即不可再做任何操作 */
	private short tradeStatus;

	/** 买家支付宝用户号(加密) */
	private String buyerId;

	/** 买家支付宝账号(加密) */
	private String buyerEmail;

	/** 支付货币类型：1-人民币 */
	private short currencyType;

	/** 交易金额(分为单位) */
	private long totalFee;

	/** 交易创建时间 */
	private Date tradeCreateTime;

	/** 交易支付时间 */
	private Date tradePayTime;

	/** 通知类型 */
	private short notifyType;

	/** 通知校验ID */
	private String checkId;

	/** 商品描述 */
	private String body;

	/** 备注(如失败原因等) */
	private String remark;

	/** 创建时间 */
	private Date createTime;

	public void setNotifyId(long notifyId) {
		this.notifyId = notifyId;
	}

	public long getNotifyId() {
		return notifyId;
	}

	public void setPayNo(long payNo) {
		this.payNo = payNo;
	}

	public long getPayNo() {
		return payNo;
	}

	public long getPayId() {
		return payId;
	}

	public void setPayId(long payId) {
		this.payId = payId;
	}

	public void setTradeNo(String tradeNo) {
		this.tradeNo = tradeNo;
	}

	public String getTradeNo() {
		return tradeNo;
	}

	public void setTradeStatus(short tradeStatus) {
		this.tradeStatus = tradeStatus;
	}

	public short getTradeStatus() {
		return tradeStatus;
	}

	public void setBuyerId(String buyerId) {
		this.buyerId = buyerId;
	}

	public String getBuyerId() {
		return buyerId;
	}

	public void setBuyerEmail(String buyerEmail) {
		this.buyerEmail = buyerEmail;
	}

	public String getBuyerEmail() {
		return buyerEmail;
	}

	public void setCurrencyType(short currencyType) {
		this.currencyType = currencyType;
	}

	public short getCurrencyType() {
		return currencyType;
	}

	public void setTotalFee(long totalFee) {
		this.totalFee = totalFee;
	}

	public long getTotalFee() {
		return totalFee;
	}

	public Date getTradeCreateTime() {
		return tradeCreateTime;
	}

	public void setTradeCreateTime(Date tradeCreateTime) {
		this.tradeCreateTime = tradeCreateTime;
	}

	public Date getTradePayTime() {
		return tradePayTime;
	}

	public void setTradePayTime(Date tradePayTime) {
		this.tradePayTime = tradePayTime;
	}

	public void setNotifyType(short notifyType) {
		this.notifyType = notifyType;
	}

	public short getNotifyType() {
		return notifyType;
	}

	public void setCheckId(String checkId) {
		this.checkId = checkId;
	}

	public String getCheckId() {
		return checkId;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public String getBody() {
		return body;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getRemark() {
		return remark;
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
