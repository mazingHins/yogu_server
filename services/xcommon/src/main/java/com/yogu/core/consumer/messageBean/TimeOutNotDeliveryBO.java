package com.yogu.core.consumer.messageBean;

import java.io.Serializable;

import com.yogu.core.consumer.BussinessType;
import com.yogu.core.consumer.messageBean.TimerCancelSfExpressBO.Builder;
import com.yogu.mq.impl.aliyun.CommandMQProducer;

/**
 * 超时没开始配送第三方订单的MQ 处理 消息bean
 *
 * @date 2016年10月28日 上午11:52:44
 * @author hins
 */
public class TimeOutNotDeliveryBO implements Serializable {

	private static final long serialVersionUID = -5236426487882055647L;

	/**
	 * 订单编号
	 */
	private long orderNo;
	
	/**
	 * 订单所属的餐厅id
	 */
	private long storeId;
	
	public TimeOutNotDeliveryBO(){
		
	}
	
	public TimeOutNotDeliveryBO(long orderNo, long storeId){
		this.orderNo = orderNo;
		this.storeId = storeId;
	}
	
	public long getOrderNo() {
		return orderNo;
	}

	public long getStoreId() {
		return storeId;
	}
	
	public static TimeOutNotDeliveryBO.Builder builder() {
		return new Builder();
	}

	public static class Builder {
		
		/**
		 * 订单编号
		 */
		private long orderNo;
		
		/**
		 * 订单所属的餐厅id
		 */
		private long storeId;

		public Builder setOrderNo(long orderNo) {
			this.orderNo = orderNo;
			return this;
		}

		public Builder setStoreId(long storeId) {
			this.storeId = storeId;
			return this;
		}
		
		public TimeOutNotDeliveryBO build(){
			return new TimeOutNotDeliveryBO(orderNo, storeId);
		}
		
		/**
		 * 发送MQ请求
		 */
		public void request(){
			CommandMQProducer.get().sendJSON(BussinessType.OVER_NOT_BEGIN_DELIVERY_EXPRESS_TIME, new TimeOutNotDeliveryBO(orderNo, storeId));
		}
		
	}
	
}
