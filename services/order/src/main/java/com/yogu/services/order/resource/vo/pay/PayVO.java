package com.yogu.services.order.resource.vo.pay;

import java.io.Serializable;

public class PayVO implements Serializable {

	private static final long serialVersionUID = 7520755900075614726L;

	/**
	 * 支付操作流水号
	 */
	private long payNo;

	/**
	 * 支付方式：1-支付宝；2-微信
	 */
	private short payMode;

	/**
	 * 支付宝SDK所需信息
	 */
	private AlipayPayVO alipay;

	/**
	 * 新版支付宝SDK所需信息
	 */
	private String newAlipay;

	/**
	 * 微信支付SDK所需信息
	 */
	private WechatPayVO wechat;

	/**
	 * 订单金额
	 */
	private long totalFee;

	/**
	 * 支付结果：1-支付成功（订单金额=100元，用户使用了>=100元的抵用优惠券，表示不需要跳转到支付宝/微信支付，订单已支付成功）
	 */
	private short payCode;

	/**
	 * 订单编号 2016/7/1 hins 米星付要返回（因为要直接跳转到订单详情）
	 */
	private long orderNo;

	public long getPayNo() {
		return payNo;
	}

	public void setPayNo(long payNo) {
		this.payNo = payNo;
	}

	public short getPayMode() {
		return payMode;
	}

	public void setPayMode(short payMode) {
		this.payMode = payMode;
	}

	public AlipayPayVO getAlipay() {
		return alipay;
	}

	public void setAlipay(AlipayPayVO alipay) {
		this.alipay = alipay;
	}

	public String getNewAlipay() {
		return newAlipay;
	}

	public void setNewAlipay(String newAlipay) {
		this.newAlipay = newAlipay;
	}

	public WechatPayVO getWechat() {
		return wechat;
	}

	public void setWechat(WechatPayVO wechat) {
		this.wechat = wechat;
	}

	public long getTotalFee() {
		return totalFee;
	}

	public void setTotalFee(long totalFee) {
		this.totalFee = totalFee;
	}

	public short getPayCode() {
		return payCode;
	}

	public void setPayCode(short payCode) {
		this.payCode = payCode;
	}

	public long getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(long orderNo) {
		this.orderNo = orderNo;
	}

}
