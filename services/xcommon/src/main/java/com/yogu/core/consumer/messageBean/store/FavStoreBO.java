package com.yogu.core.consumer.messageBean.store;

import java.io.Serializable;

import com.yogu.core.consumer.BussinessType;
import com.yogu.mq.impl.aliyun.CommandMQProducer;

public class FavStoreBO implements Serializable {
	
	private static final long serialVersionUID = 8952098927361320333L;

	/** 操作者ID */
	private long uid;
	
	/** 门店ID */
	private long storeId;
	
	public long getUid() {
		return uid;
	}

	public long getStoreId() {
		return storeId;
	}
	
	public FavStoreBO(){}
	
	FavStoreBO(long uid, long storeId){
		this.uid = uid;
		this.storeId = storeId;
	}

	public static FavStoreBO.Builder builder() {
		return new Builder();
	}
	
	public static class Builder{
		
		/** 操作者ID */
		private long uid;
		
		/** 门店ID */
		private long storeId;

		public Builder setUid(long uid) {
			this.uid = uid;
			return this;
		}

		public Builder setStoreId(long storeId) {
			this.storeId = storeId;
			return this;
		}
		
		/**
		 * 构造MQ Bean
		 * @return
		 */
		public FavStoreBO build(){
			return new FavStoreBO(uid, storeId);
		}
		
		public void request(){
			CommandMQProducer.get().sendJSON(BussinessType.FAV_STORE, new FavStoreBO(uid, storeId));
		}
		
	}
	
}
