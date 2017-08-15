package com.yogu.core.consumer.messageBean;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.yogu.core.consumer.BussinessType;
import com.yogu.mq.impl.aliyun.CommandMQProducer;

/**
 * 超预计送达时间一定时间用户未接单的MQ处理的消息bean
 * 
 * @author felix
 * @date 2015-12-11
 */
public class OverDeliveryNotConfirmBO implements Serializable {

	private static final long serialVersionUID = 5990695127285924652L;
	
	/**
	 * MQ处理参数
	 */
	private List<Map<String, Object>> params;
	
	public OverDeliveryNotConfirmBO() {}
	
	OverDeliveryNotConfirmBO(List<Map<String, Object>> params) {
		this.params = params;
	}

	public List<Map<String, Object>> getParams() {
		return params;
	}
	
	public static OverDeliveryNotConfirmBO.Builder build() {
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
		public OverDeliveryNotConfirmBO build(){
			return new OverDeliveryNotConfirmBO(params);
		}
		
		/**
		 * 发送MQ请求
		 */
		public void request(){
			CommandMQProducer.get().sendJSON(BussinessType.OVER_DELIVERY_NOT_CONFIRM, new OverDeliveryNotConfirmBO(params));
		}
		
	}
}
