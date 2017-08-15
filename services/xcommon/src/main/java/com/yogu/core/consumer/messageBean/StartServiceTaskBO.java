package com.yogu.core.consumer.messageBean;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.yogu.core.consumer.BussinessType;
import com.yogu.mq.impl.aliyun.CommandMQProducer;

/**
 * 商家每日准备营业前的准备MQ
 * 
 * @author felix 2016-05-26
 */
public class StartServiceTaskBO implements Serializable {

	private static final long serialVersionUID = 2680248001454772358L;
	
	/**
	 * MQ处理参数
	 */
	private List<Map<String, Object>> params;

	public StartServiceTaskBO() {}
	
	StartServiceTaskBO(List<Map<String, Object>> params) {
		this.params = params;
	}
	
	public List<Map<String, Object>> getParams() {
		return params;
	}
	
	public static StartServiceTaskBO.Builder build() {
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
		public StartServiceTaskBO build(){
			return new StartServiceTaskBO(params);
		}
		
		/**
		 * 发送MQ请求
		 */
		public void request(){
			CommandMQProducer.get().sendJSON(BussinessType.STORE_START_SERVICE_PREPARATION, new StartServiceTaskBO(params));
		}
		
	}
}
