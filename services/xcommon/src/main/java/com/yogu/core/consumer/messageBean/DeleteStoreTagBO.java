package com.yogu.core.consumer.messageBean;

import java.io.Serializable;

import com.yogu.core.consumer.BussinessType;
import com.yogu.mq.impl.aliyun.CommandMQProducer;

/**
 * 删除餐厅tag的MQ 消息bean
 * @author felix
 */
public class DeleteStoreTagBO implements Serializable{

	private static final long serialVersionUID = 7094698358236292358L;
	
	/** 标签ID */
	private int tagId;
	
	public int getTagId() {
		return tagId;
	}

	DeleteStoreTagBO() {}
	
	public DeleteStoreTagBO(int tagId) {
		this.tagId = tagId;
	}
	
	public static DeleteStoreTagBO.Builder builder() {
		return new Builder();
	}
	
	public static class Builder{
		/** 标签ID */
		private int tagId;

		public Builder setTagId(int tagId) {
			this.tagId = tagId;
			return this;
		}
		
		/**
		 * 构造MQ Bean
		 * @return
		 */
		public DeleteStoreTagBO build(){
			return new DeleteStoreTagBO(tagId);
		}
		
		/**
		 * 发送MQ请求
		 */
		public void request(){
			CommandMQProducer.get().sendJSON(BussinessType.DELETE_STORE_TAG, new DeleteStoreTagBO(tagId));
		}
	}
}
