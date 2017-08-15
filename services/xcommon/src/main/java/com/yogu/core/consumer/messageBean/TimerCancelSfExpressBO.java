package com.yogu.core.consumer.messageBean;

import java.io.Serializable;

import com.yogu.core.consumer.BussinessType;
import com.yogu.core.consumer.messageBean.StoreRefundFinishOrderBO.Builder;
import com.yogu.mq.impl.aliyun.CommandMQProducer;

/**
 * 定时任务自动取消超时没接单的顺丰配送单的MQ 处理 消息bean
 *
 * @date 2016年10月20日 下午4:33:53
 * @author hins
 */
public class TimerCancelSfExpressBO implements Serializable {

	private static final long serialVersionUID = 4243161357057005555L;
	
	/** 用户ID */
	private long uid;
	

	/** 订单号 */
	private long orderNo;

	/** 公用区域 END ***/

	/** 推送区域 START ***/

	/** 支付方式 */
	private short payType;

	/**
	 * 门店ID
	 */
	private long storeId;

	public TimerCancelSfExpressBO(){
		
	}
	
	public TimerCancelSfExpressBO(long uid, long orderNo, short payType, long storeId){
		this.uid = uid;
		this.orderNo = orderNo;
		this.payType = payType;
		this.storeId = storeId;
	}

	public long getUid() {
		return uid;
	}

	public long getOrderNo() {
		return orderNo;
	}

	public short getPayType() {
		return payType;
	}

	public long getStoreId() {
		return storeId;
	}
	
	public static TimerCancelSfExpressBO.Builder builder() {
		return new Builder();
	}
	

	public static class Builder {
		/** 用户ID */
		private long uid;
		

		/** 订单号 */
		private long orderNo;

		/** 公用区域 END ***/

		/** 推送区域 START ***/

		/** 支付方式 */
		private short payType;

		/**
		 * 门店ID
		 */
		private long storeId;

		public Builder setUid(long uid) {
			this.uid = uid;
			return this;
		}

		public Builder setOrderNo(long orderNo) {
			this.orderNo = orderNo;
			return this;
		}

		public Builder setPayType(short payType) {
			this.payType = payType;
			return this;
		}

		public Builder setStoreId(long storeId) {
			this.storeId = storeId;
			return this;
		}
		
		/**
		 * 构造MQ Bean
		 * 
		 * @return
		 */
		public TimerCancelSfExpressBO build() {
			return new TimerCancelSfExpressBO(uid, orderNo, payType, storeId);
		}

		/**
		 * 发送MQ请求
		 */
		public void request() {
			CommandMQProducer.get().sendJSON(BussinessType.TIMER_CANCEL_TIMEOUT_SF_EXPRESS, new TimerCancelSfExpressBO(uid, orderNo, payType, storeId));
		}
		
	}
	
}
