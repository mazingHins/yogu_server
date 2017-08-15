package com.yogu.remote.order.vo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 商家每日订单报表-订单数据VO
 *
 * @date 2016年9月27日 下午5:22:32
 * @author hins
 */
public class StoreOrderStatisticsVO implements Serializable {
	
	private static final long serialVersionUID = -6087502064294552358L;

	/** 商家优惠金额 */
	private long storeCouponFee;
	
	/** 订单列表 */
	private List<OrderStatustics> list;

	public long getStoreCouponFee() {
		return storeCouponFee;
	}

	public void setStoreCouponFee(long storeCouponFee) {
		this.storeCouponFee = storeCouponFee;
	}

	public List<OrderStatustics> getList() {
		return list;
	}

	public void setList(List<OrderStatustics> list) {
		this.list = list;
	}

	public static class OrderStatustics implements Serializable {
		
		private static final long serialVersionUID = -543986834854705995L;

		/** 订单编号 */
		private long orderNo;
		
		/** 订单状态：10-未付款；15-已付款；20-已接单；25-配送中；30-商家确认收货；35-买家确认收货；40-已评论； 45-申请退款；50-退款中；55-拒绝退款；60-已退款；65-订单已取消； */
		private short status;

		/** 订单金额，单位分 */
		private long totalFee;
		
		/** 顾客电话 */
		private String passport;
		
		/** 支付时间 */
		private Date orderBeginTime;
		
		/** 当天订单序列号 */
		private String serialNumber = "";
		
		/** 订单金额，单位元，精确到2位小数。用于h5页面展示 */
		private String totalFeeFormat;
		
		/** 支付时间，格式HH:mm。用于h5页面展示 */
		private String orderBeginTimeFormat;
		
		/** 当天订单排序号 */
		private long sequence;

		public long getOrderNo() {
			return orderNo;
		}

		public void setOrderNo(long orderNo) {
			this.orderNo = orderNo;
		}

		public short getStatus() {
			return status;
		}

		public void setStatus(short status) {
			this.status = status;
		}

		public long getTotalFee() {
			return totalFee;
		}

		public void setTotalFee(long totalFee) {
			this.totalFee = totalFee;
		}

		public String getPassport() {
			return passport;
		}

		public void setPassport(String passport) {
			this.passport = passport;
		}

		public Date getOrderBeginTime() {
			return orderBeginTime;
		}

		public void setOrderBeginTime(Date orderBeginTime) {
			this.orderBeginTime = orderBeginTime;
		}

		public String getSerialNumber() {
			return serialNumber;
		}

		public void setSerialNumber(String serialNumber) {
			this.serialNumber = serialNumber;
		}

		public String getTotalFeeFormat() {
			return totalFeeFormat;
		}

		public void setTotalFeeFormat(String totalFeeFormat) {
			this.totalFeeFormat = totalFeeFormat;
		}

		public String getOrderBeginTimeFormat() {
			return orderBeginTimeFormat;
		}

		public void setOrderBeginTimeFormat(String orderBeginTimeFormat) {
			this.orderBeginTimeFormat = orderBeginTimeFormat;
		}

		public long getSequence() {
			return sequence;
		}

		public void setSequence(long sequence) {
			this.sequence = sequence;
		}
		
	}
	
}



