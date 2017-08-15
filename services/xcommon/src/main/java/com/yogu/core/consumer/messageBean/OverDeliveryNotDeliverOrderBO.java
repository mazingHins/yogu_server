package com.yogu.core.consumer.messageBean;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.yogu.core.consumer.BussinessType;
import com.yogu.mq.impl.aliyun.CommandMQProducer;

/**
 * 超过预计送达时间未配送的MQ处理的消息bean
 * 
 * @author felix
 * @date 2015-12-11
 */
public class OverDeliveryNotDeliverOrderBO implements Serializable{

	private static final long serialVersionUID = 6978032720344701076L;
	
	/**
	 * MQ处理参数
	 */
	private List<Map<String, Object>> params;
	
	public OverDeliveryNotDeliverOrderBO() {}
	
	OverDeliveryNotDeliverOrderBO(List<Map<String, Object>> params) {
		this.params = params;
	}

	public List<Map<String, Object>> getParams() {
		return params;
	}
	
	public static OverDeliveryNotDeliverOrderBO.Builder build() {
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
		public OverDeliveryNotDeliverOrderBO build(){
			return new OverDeliveryNotDeliverOrderBO(params);
		}
		
		/**
		 * 发送MQ请求
		 */
		public void request(){
			CommandMQProducer.get().sendJSON(BussinessType.OVER_DELIVERY_NOT_DELIVER_ORDER, new OverDeliveryNotDeliverOrderBO(params));
		}
		
	}
	
}
