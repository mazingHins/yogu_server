package com.yogu.core.consumer.messageBean;

import java.io.Serializable;

import com.yogu.core.consumer.BussinessType;
import com.yogu.mq.impl.aliyun.CommandMQProducer;

/**
 * 定时任务申请退款消息bean
 * 
 * @author Hins
 * @date 2015年12月15日 下午3:56:26
 */
public class TimerApplyRefundBO implements Serializable {

	private static final long serialVersionUID = -8046714806897700439L;
	
	/** 报表 START***/
	/**
	 * 门店ID
	 */
	private long storeId;

	/**
	 * 报表日期yyyyMMdd格式
	 */
	private int serviceTime;

	/**
	 * 交易费用（分）
	 */
	private long orderFee;
	
	/** 报表区域 END***/
	
	/** 申请退款总金额 */
	private String refundFee;
	
	/** 用户ID, 多个用英文逗号分隔 */
	private String uids;
	
	/**
	 * 订单编号
	 */
	private long orderNo;
	
	/** 报表区域 END***/
	
	public TimerApplyRefundBO() {}
	
	TimerApplyRefundBO(long storeId, int serviceTime, long orderFee, String uids, String refundFee, long orderNo) {
		this.storeId = storeId;
		this.serviceTime = serviceTime;
		this.orderFee = orderFee;
		this.uids = uids;
		this.refundFee = refundFee;
		this.orderNo = orderNo;
	}
	
	/**
	 * 创建TimerApplyRefundBO的构造者
	 * @return TimerApplyRefundBO的构造者
	 */
	public static TimerApplyRefundBO.Builder builder() {
		return new Builder();
	}
	
	public long getStoreId() {
		return storeId;
	}

	public int getServiceTime() {
		return serviceTime;
	}

	public long getOrderFee() {
		return orderFee;
	}
	
	public String getUids() {
		return uids;
	}

	public String getRefundFee() {
		return refundFee;
	}
	
	public long getOrderNo() {
		return orderNo;
	}



	public static class Builder{
		/**
		 * 门店ID
		 */
		private long storeId;

		/**
		 * 报表日期yyyyMMdd格式
		 */
		private int serviceTime;

		/**
		 * 交易费用（分）
		 */
		private long orderFee;
		
		/** 用户ID, 多个用英文逗号分隔 */
		private String uids;
		
		/** 申请退款总金额 */
		private String refundFee;
		
		/**
		 * 订单编号
		 */
		private long orderNo;
		
		public Builder setUids(String uids) {
			this.uids = uids;
			return this;
		}

		public Builder setRefundFee(String refundFee) {
			this.refundFee = refundFee;
			return this;
		}
		
		public Builder setStoreId(long storeId) {
			this.storeId = storeId;
			return this;
		}

		public Builder setServiceTime(int serviceTime) {
			this.serviceTime = serviceTime;
			return this;
		}

		public Builder setOrderFee(long orderFee) {
			this.orderFee = orderFee;
			return this;
		}
		
		public Builder setOrderNo(long orderNo) {
			this.orderNo = orderNo;
			return this;
		}

		/**
		 * 构造MQ Bean
		 * @return
		 */
		public TimerApplyRefundBO build(){
			return new TimerApplyRefundBO(storeId, serviceTime, orderFee, uids, refundFee, orderNo);
		}
		/**
		 * 发送MQ请求
		 */
		public void request(){
			CommandMQProducer.get().sendJSON(BussinessType.TIMER_APPLY_REFUND, new TimerApplyRefundBO(storeId, serviceTime, orderFee, uids, refundFee, orderNo));
		}
	}

}
