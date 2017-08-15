package com.yogu.core.consumer.messageBean;

import java.io.Serializable;

import com.yogu.core.consumer.BussinessType;
import com.yogu.mq.impl.aliyun.CommandMQProducer;

public class RefundSuccessBO implements Serializable {

	private static final long serialVersionUID = -4708562498990155238L;
	
	/** 用户ID, 多个用英文逗号分隔 */
	private String uids;
	
	/** 订单所属的商家id */
	private long storeId;
	
	/** 订单编号 */
	private long orderNo;
	
	/** 退款金额，单位分 */
	private long refundFee;
	
	/** 退款申请时的订单状态 */
	private short refundOrderStatus;
	
	public RefundSuccessBO() {}
	
	RefundSuccessBO(String uids, long storeId, long orderNo, long refundFee, short refundOrderStatus) {
		this.uids = uids;
		this.storeId = storeId;
		this.orderNo = orderNo;
		this.refundFee = refundFee;
		this.refundOrderStatus = refundOrderStatus;
	}
	
	/**
	 * 创建RefundSuccessBean的构造者
	 * @return RefundSuccessBean的构造者
	 */
	public static RefundSuccessBO.Builder builder() {
		return new Builder();
	}
	

	public String getUids() {
		return uids;
	}
	
	public long getStoreId() {
		return storeId;
	}
	
	public long getOrderNo() {
		return orderNo;
	}

	public long getRefundFee() {
		return refundFee;
	}
	
	public short getRefundOrderStatus() {
		return refundOrderStatus;
	}

	public static class Builder{
		/** 用户ID, 多个用英文逗号分隔 */
		private String uids;
		
		/** 订单所属的商家id */
		private long storeId;
		
		/** 订单编号 */
		private long orderNo;
		
		/** 退款金额，单位分 */
		private long refundFee;
		
		/** 退款申请时的订单状态 */
		private short refundOrderStatus;
		
		public Builder setUids(String uids) {
			this.uids = uids;
			return this;
		}
		
		public Builder setStoreId(long storeId) {
			this.storeId = storeId;
			return this;
		}
		
		public Builder setOrderNo(long orderNo) {
			this.orderNo = orderNo;
			return this;
		}

		public Builder setRefundFee(long refundFee) {
			this.refundFee = refundFee;
			return this;
		}
		
		public Builder setRefundOrderStatus(short refundOrderStatus) {
			this.refundOrderStatus = refundOrderStatus;
			return this;
		}

		/**
		 * 构造MQ Bean
		 * @return
		 */
		public RefundSuccessBO build(){
			return new RefundSuccessBO(uids, storeId, orderNo, refundFee, refundOrderStatus);
		}
		
		/**
		 * 发送MQ请求
		 */
		public void request(){
			CommandMQProducer.get().sendJSON(BussinessType.REFUND_SUCCESS, new RefundSuccessBO(uids, storeId, orderNo, refundFee, refundOrderStatus));
		}
	}
	
}
