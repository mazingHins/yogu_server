package com.yogu.core.consumer.messageBean.store;

import java.io.Serializable;

import com.yogu.core.consumer.BussinessType;
import com.yogu.mq.impl.aliyun.CommandMQProducer;

public class CancelFavStoreBO implements Serializable {
	
	private static final long serialVersionUID = -937421556967837308L;

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
	
	public CancelFavStoreBO(){}
	
	public CancelFavStoreBO(long uid, long storeId){
		this.uid = uid;
		this.storeId = storeId;
	}

	public static CancelFavStoreBO.Builder builder() {
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
		public CancelFavStoreBO build(){
			return new CancelFavStoreBO(uid, storeId);
		}
		
		public void request(){
			CommandMQProducer.get().sendJSON(BussinessType.CANCEL_FAV_STORE, new CancelFavStoreBO(uid, storeId));
		}
		
	}
	
}
