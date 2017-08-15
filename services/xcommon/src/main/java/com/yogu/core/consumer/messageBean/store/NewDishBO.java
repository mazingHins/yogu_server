package com.yogu.core.consumer.messageBean.store;

import java.io.Serializable;

import com.yogu.core.consumer.BussinessType;
import com.yogu.mq.impl.aliyun.CommandMQProducer;

public class NewDishBO implements Serializable{
	
	private static final long serialVersionUID = -7037200607290329308L;

	/** 门店ID */
	private long storeId;
	
	/** 菜品名称 */
	private String dishName;
	
	public NewDishBO() {}
	
	NewDishBO(long storeId, String dishName) {
		this.dishName = dishName;
		this.storeId = storeId;
	}

	public long getStoreId() {
		return storeId;
	}

	public String getDishName() {
		return dishName;
	}
	
	public static NewDishBO.Builder builder() {
		return new Builder();
	}
	
	public static class Builder{
		
		/** 门店ID */
		private long storeId;
		
		/** 菜品名称 */
		private String dishName;

		public Builder setStoreId(long storeId) {
			this.storeId = storeId;
			return this;
		}

		public Builder setDishName(String dishName) {
			this.dishName = dishName;
			return this;
		}
		
		/**
		 * 构造MQ Bean
		 * @return
		 */
		public NewDishBO build(){
			return new NewDishBO(storeId, dishName);
		}
		
		/**
		 * 发送MQ请求
		 */
		public void request(){
			CommandMQProducer.get().sendJSON(BussinessType.NEW_DISH, new NewDishBO(storeId, dishName));
		}
	}
}
