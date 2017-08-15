package com.yogu.core.consumer.messageBean;

import java.io.Serializable;

import com.yogu.core.consumer.BussinessType;
import com.yogu.mq.impl.aliyun.CommandMQProducer;

/**
 * 自动配送订单的MQ处理的消息bean
 * @author felix
 */
public class AutoDeliverOrderBO implements Serializable {

	private static final long serialVersionUID = -8551798157450802022L;
	
	/** 用户ID, 多个用英文逗号分隔 */
	private String uids;
	
	/** 订单号 */
	private long orderNo;
	
	public AutoDeliverOrderBO() {}
	
	AutoDeliverOrderBO(String uids, long orderNo) {
		this.uids = uids;
		this.orderNo = orderNo;
	}
	
	public String getUids() {
		return uids;
	}

	public long getOrderNo() {
		return orderNo;
	}
	
	
	
	
	/**
	 * 创建AcceptOrderBean的构造者
	 * @return AcceptOrderBean的构造者
	 */
	public static AutoDeliverOrderBO.Builder builder() {
		return new Builder();
	}

	
	public static class Builder{
		/** 用户ID, 多个用英文逗号分隔 */
		private String uids;
		
		/** 订单号 */
		private long orderNo;
		
		
		public Builder setUids(String uids) {
			this.uids = uids;
			return this;
		}

		public Builder setOrderNo(long orderNo) {
			this.orderNo = orderNo;
			return this;
		}
		
		/**
		 * 构造MQ Bean
		 * @return
		 */
		public AutoDeliverOrderBO build(){
			return new AutoDeliverOrderBO(uids, orderNo);
		}
		
		/**
		 * 发送MQ请求
		 */
		public void request(){
			CommandMQProducer.get().sendJSON(BussinessType.AUTO_DELIVER_ORDER, new AutoDeliverOrderBO(uids, orderNo));
		}
	}
}
