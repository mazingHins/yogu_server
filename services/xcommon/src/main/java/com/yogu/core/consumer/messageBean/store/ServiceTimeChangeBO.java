package com.yogu.core.consumer.messageBean.store;

import java.io.Serializable;

import com.yogu.core.consumer.BussinessType;
import com.yogu.mq.impl.aliyun.CommandMQProducer;

public class ServiceTimeChangeBO implements Serializable{
	
	private static final long serialVersionUID = 7121300921407138848L;
	
	/**　门店ID */
	private long storeId;

	public ServiceTimeChangeBO() {}
	
	public ServiceTimeChangeBO(long storeId) {
		this.storeId = storeId;
	}

	public long getStoreId() {
		return storeId;
	}
	
	public static ServiceTimeChangeBO.Builder builder() {
		return new Builder();
	}
	
	public static class Builder{
		/**　门店ID */
		private long storeId;


		public Builder setStoreId(long storeId) {
			this.storeId = storeId;
			return this;
		}

		/**
		 * 构造MQ Bean
		 * 
		 * @return
		 */
		public ServiceTimeChangeBO build() {
			return new ServiceTimeChangeBO(storeId);
		}

		/**
		 * 发送MQ请求
		 */
		public void request() {
			CommandMQProducer.get().sendJSON(BussinessType.SERVICE_TIME_CHANGE,
					new ServiceTimeChangeBO(storeId));
		}
	}
	
}
