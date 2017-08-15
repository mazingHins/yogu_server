package com.yogu.core.consumer.messageBean;

import java.io.Serializable;

import com.yogu.core.consumer.BussinessType;
import com.yogu.mq.impl.aliyun.CommandMQProducer;

/**
 * 用户下单餐厅当前不在营业时间内，用户下单即通知用户
 * 
 * @author east
 * @date 2016年11月24日
 */
public class StoreNotInBusinessNoticeUserBO implements Serializable {

	private static final long serialVersionUID = -2067627327952040949L;

	/** 公用区域 START ***/
	/** 用户ID, 多个用英文逗号分隔 */
	private String uids;

	/** 餐厅名称 */
	private String storeName;

	/** 餐厅英文名称 */
	private String storeNameEn;

	public StoreNotInBusinessNoticeUserBO() {
	};

	public StoreNotInBusinessNoticeUserBO(String uids, String storeName, String storeNameEn) {
		this.uids = uids;
		this.storeName = storeName;
		this.storeNameEn = storeNameEn;
	}

	public String getUids() {
		return uids;
	}

	public String getStoreName() {
		return storeName;
	}

	public String getStoreNameEn() {
		return storeNameEn;
	}

	public static StoreNotInBusinessNoticeUserBO.Builder builder() {
		return new Builder();
	}

	public static class Builder {
		private String uids;

		/** 餐厅名称 */
		private String storeName;
		
		/** 餐厅名称 */
		private String storeNameEn;

		public Builder setUids(String uids) {
			this.uids = uids;
			return this;
		}

		public Builder setStoreName(String storeName) {
			this.storeName = storeName;
			return this;
		}
		
		public Builder setStoreNameEn(String storeNameEn) {
			this.storeNameEn = storeNameEn;
			return this;
		}

		/**
		 * 构造MQ Bean
		 * 
		 * @return
		 */
		public StoreNotInBusinessNoticeUserBO build() {
			return new StoreNotInBusinessNoticeUserBO(uids, storeName, storeNameEn);
		}

		/**
		 * 发送MQ请求
		 */
		public void request() {
			CommandMQProducer.get().sendJSON(BussinessType.STORE_NOT_IN_BUSINESS_NOTICE_USER,
					new StoreNotInBusinessNoticeUserBO(uids, storeName, storeNameEn));
		}
	}

}
