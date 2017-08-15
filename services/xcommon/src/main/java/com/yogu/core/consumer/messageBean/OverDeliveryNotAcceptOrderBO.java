package com.yogu.core.consumer.messageBean;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.yogu.core.consumer.BussinessType;
import com.yogu.mq.impl.aliyun.CommandMQProducer;

/**
 * 超过预计送达时间未接单的MQ处理的消息bean
 * 
 * @author felix
 * @date 2015-12-11
 */
public class OverDeliveryNotAcceptOrderBO implements Serializable {

	private static final long serialVersionUID = -6340144044221860850L;
	
	/**
	 * MQ处理参数
	 */
	private List<Map<String, Object>> params;
	
	public OverDeliveryNotAcceptOrderBO() {}
	
	OverDeliveryNotAcceptOrderBO(List<Map<String, Object>> params) {
		this.params = params;
	}

	public List<Map<String, Object>> getParams() {
		return params;
	}
	
	public static OverDeliveryNotAcceptOrderBO.Builder build() {
		return new Builder();
	}
	
	public static class Builder {
		/**
		 * MQ处理参数
		 */
		private List<Map<String, Object>> params;

		public Builder setParams(List<Map<String, Object>> params) {
			this.params = params;
			return this;
		}
		
		/**
		 * 构造MQ Bean
		 * @return
		 */
		public OverDeliveryNotAcceptOrderBO build(){
			return new OverDeliveryNotAcceptOrderBO(params);
		}
		
		/**
		 * 发送MQ请求
		 */
		public void request(){
			CommandMQProducer.get().sendJSON(BussinessType.OVER_DELIVERY_NOT_ACCEPT_ORDER, new OverDeliveryNotAcceptOrderBO(params));
		}
		
	}
	
}
