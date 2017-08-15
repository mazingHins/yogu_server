package com.yogu.core.consumer.messageBean;

import java.io.Serializable;

import com.yogu.core.consumer.BussinessType;
import com.yogu.mq.impl.aliyun.CommandMQProducer;

/**
 * 餐厅退回订单的MQ处理的消息bean
 * @author felix
 */
public class StoreReturnOrderBO implements Serializable {

	private static final long serialVersionUID = 3838862843514745008L;

	/** 用户ID, 多个用英文逗号分隔 */
	private String uids;

	public StoreReturnOrderBO() {}
	
	StoreReturnOrderBO(String uids) {
		this.uids = uids;
	}
	
	/**
	 * 创建StoreReturnOrderBean的构造者
	 * @return StoreReturnOrderBean的构造者
	 */
	public static StoreReturnOrderBO.Builder builder() {
		return new Builder();
	}

	public String getUids() {
		return uids;
	}
	
	public static class Builder{
		/** 用户ID, 多个用英文逗号分隔 */
		private String uids;
		
		public Builder setUids(String uids) {
			this.uids = uids;
			return this;
		}
		/**
		 * 构造MQ Bean
		 * @return
		 */
		public StoreReturnOrderBO build(){
			return new StoreReturnOrderBO(uids);
		}
		/**
		 * 发送MQ请求
		 */
		public void request(){
			CommandMQProducer.get().sendJSON(BussinessType.STORE_RETURN_ORDER, new StoreReturnOrderBO(uids));
		}
	}
	
	
}
