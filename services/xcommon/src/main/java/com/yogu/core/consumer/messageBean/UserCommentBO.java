package com.yogu.core.consumer.messageBean;

import java.io.Serializable;

import com.yogu.core.consumer.BussinessType;
import com.yogu.mq.impl.aliyun.CommandMQProducer;

/**
 * 用户评论的mq的消息处理bean
 * @author felix
 */
public class UserCommentBO implements Serializable{

	private static final long serialVersionUID = 4790876199322708343L;
	
	private long orderNo;

	public long getOrderNo() {
		return orderNo;
	}
	
	public UserCommentBO() {}
	
	UserCommentBO(long orderNo) {
		this.orderNo = orderNo;
	}
	
	public static UserCommentBO.Builder builder() {
		return new Builder();
	}
	
	public static class Builder{
		
		private long orderNo;
		
		public Builder setOrderNo(long orderNo) {
			this.orderNo = orderNo;
			return this;
		}
		
		/**
		 * 构造MQ Bean
		 * @return
		 */
		public UserCommentBO build(){
			return new UserCommentBO(orderNo);
		}
		
		/**
		 * 发送MQ请求
		 */
		public void request(){
			CommandMQProducer.get().sendJSON(BussinessType.USER_COMMENT_ORDER, new UserCommentBO(orderNo));
		}
	}
}
