package com.yogu.core.consumer.messageBean;

import java.io.Serializable;

import com.yogu.core.consumer.BussinessType;
import com.yogu.core.enums.NotifyTarget;
import com.yogu.mq.impl.aliyun.CommandMQProducer;

/**
 * 超时为接单的提醒 MQ 消息bean
 * 
 * @author felix
 */
public class OverAcceptTimeNotifyBO implements Serializable {

	private static final long serialVersionUID = 8592899915611593585L;

	/** 公用区域 START ***/

	/** C端用户ID, 多个用英文逗号分隔 */
	private String clientUids;

	/** B端用户ID, 多个用英文逗号分隔 */
	private String storeUids;

	/** 餐厅名字 */
	private String storeName;

	/** 餐厅英文名字 */
	private String storeNameEn;

	/** 订单号 */
	private long orderNo;

	/** 通知对象 */
	private short target;

	/** 公用区域 END ***/

	/** 短信区域 START ***/
	/** 短信区域 END ***/

	/** 推送区域 START ***/
	/** 推送区域 END ***/

	public OverAcceptTimeNotifyBO() {
	}

	OverAcceptTimeNotifyBO(String clientUids, String storeUids, String storeName, long orderNo, short target,
			String storeNameEn) {
		this.clientUids = clientUids;
		this.storeUids = storeUids;
		this.storeName = storeName;
		this.orderNo = orderNo;
		this.target = target;
		this.storeNameEn = storeNameEn;
	}

	/**
	 * 创建OverAcceptTimeNotifyBean的构造者
	 * 
	 * @return OverAcceptTimeNotifyBean的构造者
	 */
	public static OverAcceptTimeNotifyBO.Builder builder() {
		return new Builder();
	}

	public String getClientUids() {
		return clientUids;
	}

	public String getStoreUids() {
		return storeUids;
	}

	public String getStoreName() {
		return storeName;
	}

	public long getOrderNo() {
		return orderNo;
	}

	public short getTarget() {
		return target;
	}

	public String getStoreNameEn() {
		return storeNameEn;
	}

	public static class Builder {
		/** C端用户ID, 多个用英文逗号分隔 */
		private String clientUids;

		/** B端用户ID, 多个用英文逗号分隔 */
		private String storeUids;

		/** 餐厅名字 */
		private String storeName;

		/** 餐厅英文名字 */
		private String storeNameEn;

		/** 订单号 */
		private long orderNo;

		/** 通知对象 */
		private short target;

		public Builder setClientUids(String clientUids) {
			this.clientUids = clientUids;
			return this;
		}

		public Builder setStoreUids(String storeUids) {
			this.storeUids = storeUids;
			return this;
		}

		public Builder setStoreName(String storeName) {
			this.storeName = storeName;
			return this;
		}

		public Builder setOrderNo(long orderNo) {
			this.orderNo = orderNo;
			return this;
		}

		public Builder storeTarget() {
			this.target = NotifyTarget.STORE.getValue();
			return this;
		}

		public Builder userTarget() {
			this.target = NotifyTarget.USER.getValue();
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
		public OverAcceptTimeNotifyBO build() {
			return new OverAcceptTimeNotifyBO(clientUids, storeUids, storeName, orderNo, target, storeNameEn);
		}

		/**
		 * 发送MQ请求
		 */
		public void request() {
			CommandMQProducer.get().sendJSON(BussinessType.OVER_ACCEPT_TIME_NOTIFY,
					new OverAcceptTimeNotifyBO(clientUids, storeUids, storeName, orderNo, target, storeNameEn));
		}
	}
}
