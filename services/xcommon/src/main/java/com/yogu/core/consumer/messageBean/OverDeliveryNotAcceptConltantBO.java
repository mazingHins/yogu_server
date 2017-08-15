package com.yogu.core.consumer.messageBean;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.yogu.core.consumer.BussinessType;
import com.yogu.mq.impl.aliyun.CommandMQProducer;

/**
 * 超过预计送达时间一定时间(暂时是5分钟)未接单的MQ处理的消息bean
 * 
 * @author felix
 */
public class OverDeliveryNotAcceptConltantBO implements Serializable {

	private static final long serialVersionUID = -4120645761491928590L;
	
	/**
	 * MQ处理参数
	 */
	private List<Map<String, Object>> params;
	
	public OverDeliveryNotAcceptConltantBO() {}
	
	OverDeliveryNotAcceptConltantBO(List<Map<String, Object>> params) {
		this.params = params;
	}

	public List<Map<String, Object>> getParams() {
		return params;
	}
	
	public static OverDeliveryNotAcceptConltantBO.Builder build() {
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
		public OverDeliveryNotAcceptConltantBO build(){
			return new OverDeliveryNotAcceptConltantBO(params);
		}
		
		/**
		 * 发送MQ请求
		 */
		public void request(){
			CommandMQProducer.get().sendJSON(BussinessType.OVER_DELIVERY_NOT_ACCEPT_ORDER_5_MIN, new OverDeliveryNotAcceptConltantBO(params));
		}
		
	}
	
}
