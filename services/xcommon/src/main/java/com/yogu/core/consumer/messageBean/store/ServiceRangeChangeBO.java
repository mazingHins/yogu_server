package com.yogu.core.consumer.messageBean.store;

import java.io.Serializable;

import com.yogu.core.consumer.BussinessType;
import com.yogu.core.enums.merchant.ServiceRangeChangeType;
import com.yogu.mq.impl.aliyun.CommandMQProducer;

public class ServiceRangeChangeBO implements Serializable{
	
	private static final long serialVersionUID = 8760981727483047442L;
	
	/** 门店ID */
	private long storeId;
	
	private ServiceRangeChangeType changeType;

	public ServiceRangeChangeBO() {}
	
	ServiceRangeChangeBO(long storeId, ServiceRangeChangeType changeType) {
		this.storeId = storeId;
		this.changeType = changeType;
	}

	public long getStoreId() {
		return storeId;
	}
	
	public ServiceRangeChangeType getChangeType() {
		return changeType;
	}
	
	public static ServiceRangeChangeBO.Builder builder() {
		return new Builder();
	}
	
	public static class Builder{
		/** 门店ID */
		private long storeId;
		
		private ServiceRangeChangeType changeType;

		public Builder setStoreId(long storeId) {
			this.storeId = storeId;
			return this;
		}
		
		public Builder addRange(){
			this.changeType = ServiceRangeChangeType.ADD;
			return this;
		}
		
		public Builder updateRange(){
			this.changeType = ServiceRangeChangeType.UPDATE;
			return this;
		}
		
		public Builder deleteRange(){
			this.changeType = ServiceRangeChangeType.DELETE;
			return this;
		}
		
		/**
		 * 构造MQ Bean
		 * @return
		 */
		public ServiceRangeChangeBO build(){
			return new ServiceRangeChangeBO(storeId, changeType);
		}
		
		/**
		 * 发送MQ请求
		 */
		public void request(){
			CommandMQProducer.get().sendJSON(BussinessType.SERVICE_RANGE_CHANGE, new ServiceRangeChangeBO(storeId, changeType));
		}
	}
}
