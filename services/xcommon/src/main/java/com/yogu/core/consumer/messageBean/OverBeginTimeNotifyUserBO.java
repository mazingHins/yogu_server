package com.yogu.core.consumer.messageBean;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.yogu.core.consumer.BussinessType;
import com.yogu.mq.impl.aliyun.CommandMQProducer;


/**
 * 订单开始一定时间, 未接单通知用户
 * 
 * @author felix
 */
public class OverBeginTimeNotifyUserBO implements Serializable {

	private static final long serialVersionUID = 2549042596154619036L;
	
	/**
	 * MQ处理参数
	 */
	private List<Map<String, Object>> params;

	public OverBeginTimeNotifyUserBO() {}
	
	OverBeginTimeNotifyUserBO(List<Map<String, Object>> params) {
		this.params = params;
	}
	
	public List<Map<String, Object>> getParams() {
		return params;
	}
	
	public static OverBeginTimeNotifyUserBO.Builder build() {
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
		public OverBeginTimeNotifyUserBO build(){
			return new OverBeginTimeNotifyUserBO(params);
		}
		
		/**
		 * 发送MQ请求
		 */
		public void request(){
			CommandMQProducer.get().sendJSON(BussinessType.OVER_BEGIN_TIME_NOTIFY_USER, new OverBeginTimeNotifyUserBO(params));
		}
		
	}
}
