package com.yogu.remote.order.vo;

import java.io.Serializable;

/**
 * 订单报表详情VO
 * 
 * @author Hins
 * @date 2015年12月16日 下午3:20:19
 */
public class StoreReportDetailVO implements Serializable {

	private static final long serialVersionUID = 2689750106960866593L;
	
	/**
	 * 进行中订单数
	 */
	private int dealingOrders = 0;

	/**
	 * 进行中订单金额
	 */
	private long dealingOrdersFee = 0;

	/** 当日订单（支付完毕/货到付款）交易金额（分） */
	private long createOrdersFee = 0;

	/** 下单成功（支付完毕/货到付款）的总数量 */
	private int createOrders = 0;

	/** 取消订单总数量，退款订单不统计 */
	private int cancelOrders = 0;

	/** 取消订单交易金额 */
	private long cancelOrdersFee = 0;

	/** 退款订单总数量 */
	private short refundOrders = 0;

	/** 退款订单总数量（分） */
	private long refundOrdersFee = 0;

	/** 当日总交易金额（分） */
	private long finishOrdersFee;

	/** 完成订单线上支付交易金额 */
	private long onlineFinishOrdersFee = 0;

	/** 完成订单现金支付交易金额 */
	private long cashFinishOrdersFee = 0;

	/** 完成订单总数量 */
	private int finishOrders = 0;
	
	public long getCreateOrdersFee() {
		return createOrdersFee;
	}

	public void setCreateOrdersFee(long createOrdersFee) {
		this.createOrdersFee = createOrdersFee;
	}

	public int getCreateOrders() {
		return createOrders;
	}

	public void setCreateOrders(int createOrders) {
		this.createOrders = createOrders;
	}

	public int getDealingOrders() {
		return dealingOrders;
	}

	public void setDealingOrders(int dealingOrders) {
		this.dealingOrders = dealingOrders;
	}

	public long getDealingOrdersFee() {
		return dealingOrdersFee;
	}

	public void setDealingOrdersFee(long dealingOrdersFee) {
		this.dealingOrdersFee = dealingOrdersFee;
	}

	public int getCancelOrders() {
		return cancelOrders;
	}

	public void setCancelOrders(int cancelOrders) {
		this.cancelOrders = cancelOrders;
	}

	public long getCancelOrdersFee() {
		return cancelOrdersFee;
	}

	public void setCancelOrdersFee(long cancelOrdersFee) {
		this.cancelOrdersFee = cancelOrdersFee;
	}

	public short getRefundOrders() {
		return refundOrders;
	}

	public void setRefundOrders(short refundOrders) {
		this.refundOrders = refundOrders;
	}

	public long getRefundOrdersFee() {
		return refundOrdersFee;
	}

	public void setRefundOrdersFee(long refundOrdersFee) {
		this.refundOrdersFee = refundOrdersFee;
	}

	public long getFinishOrdersFee() {
		return finishOrdersFee;
	}

	public void setFinishOrdersFee(long finishOrdersFee) {
		this.finishOrdersFee = finishOrdersFee;
	}

	public long getOnlineFinishOrdersFee() {
		return onlineFinishOrdersFee;
	}

	public void setOnlineFinishOrdersFee(long onlineFinishOrdersFee) {
		this.onlineFinishOrdersFee = onlineFinishOrdersFee;
	}

	public long getCashFinishOrdersFee() {
		return cashFinishOrdersFee;
	}

	public void setCashFinishOrdersFee(long cashFinishOrdersFee) {
		this.cashFinishOrdersFee = cashFinishOrdersFee;
	}

	public int getFinishOrders() {
		return finishOrders;
	}

	public void setFinishOrders(int finishOrders) {
		this.finishOrders = finishOrders;
	}

}
