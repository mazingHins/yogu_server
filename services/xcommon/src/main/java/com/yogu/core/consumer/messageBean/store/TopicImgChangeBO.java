package com.yogu.core.consumer.messageBean.store;

import java.io.Serializable;

import com.yogu.core.consumer.BussinessType;
import com.yogu.mq.impl.aliyun.CommandMQProducer;

public class TopicImgChangeBO implements Serializable {

	private static final long serialVersionUID = -3186970074635496736L;
	
	private long storeId;
	
	public TopicImgChangeBO() {}
	
	TopicImgChangeBO(long storeId) {
		this.storeId = storeId;
	}

	public long getStoreId() {
		return storeId;
	}
	
	public static TopicImgChangeBO.Builder builder() {
		return new Builder();
	}
	
	public static class Builder{
		private long storeId;
		
		public Builder setStoreId(long storeId) {
			this.storeId = storeId;
			return this;
		}
		
		/**
		 * 构造MQ Bean
		 * @return
		 */
		public TopicImgChangeBO build(){
			return new TopicImgChangeBO(storeId);
		}
		
		/**
		 * 发送MQ请求
		 */
		public void request(){
			CommandMQProducer.get().sendJSON(BussinessType.TOPIC_IMAGE_CHANGE, new TopicImgChangeBO(storeId));
		}
	}
	
}
