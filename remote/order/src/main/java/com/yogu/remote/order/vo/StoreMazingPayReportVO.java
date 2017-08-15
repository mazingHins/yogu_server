package com.yogu.remote.order.vo;

import java.io.Serializable;

/**
 * 餐厅米星付订单报表VO
 *
 * @date 2016年8月22日 下午4:31:58
 * @author hins
 */
public class StoreMazingPayReportVO implements Serializable {
	
	private static final long serialVersionUID = -8018848188492054953L;

	/** 完成（支付成功）米星付交易金额（单位分） */
	private long mazingPayFinishOrdersFee = 0;

	/** 完成（支付成功）米星付订单数量 */
	private int mazingPayFinishOrders = 0;
	
	/** 退款米星付交易金额（单位分） */
	private long mazingPayRefundOrdersFee = 0;

	/** 退款米星付交易数量 */
	private int mazingPayRefundOrders = 0;
	
	public long getMazingPayFinishOrdersFee() {
		return mazingPayFinishOrdersFee;
	}

	public void setMazingPayFinishOrdersFee(long mazingPayFinishOrdersFee) {
		this.mazingPayFinishOrdersFee = mazingPayFinishOrdersFee;
	}

	public int getMazingPayFinishOrders() {
		return mazingPayFinishOrders;
	}

	public void setMazingPayFinishOrders(int mazingPayFinishOrders) {
		this.mazingPayFinishOrders = mazingPayFinishOrders;
	}

	public long getMazingPayRefundOrdersFee() {
		return mazingPayRefundOrdersFee;
	}

	public void setMazingPayRefundOrdersFee(long mazingPayRefundOrdersFee) {
		this.mazingPayRefundOrdersFee = mazingPayRefundOrdersFee;
	}

	public int getMazingPayRefundOrders() {
		return mazingPayRefundOrders;
	}

	public void setMazingPayRefundOrders(int mazingPayRefundOrders) {
		this.mazingPayRefundOrders = mazingPayRefundOrders;
	}
	

}
