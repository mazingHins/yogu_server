package com.yogu.core.consumer.messageBean;

import java.io.Serializable;

import com.yogu.core.consumer.BussinessType;
import com.yogu.mq.impl.aliyun.CommandMQProducer;

/**
 * 提现处理结果的MQ的消息处理bean
 * @author felix
 */
public class WithdrawResultBO implements Serializable {

	private static final long serialVersionUID = -3485844569441942126L;
	
	/** 用户ID, 多个用英文逗号分隔 */
	private String uids;
	
	/** 提现结果 true-成功, false-失败 */
	private boolean success;
	
	/** 时间 */
	private String date;
	
	/** 数额 */
	private String amount;
	
	/** 提现失败才有原因 */
	private String reason;
	
	public WithdrawResultBO() {}
	
	WithdrawResultBO(String uids, boolean success, String date, String amount, String reason) {
		this.uids = uids;
		this.success = success;
		this.date = date;
		this.amount = amount;
		this.reason = reason;
	}
	
	/**
	 * 创建WithdrawResultBean的构造者
	 * @return WithdrawResultBean的构造者
	 */
	public static WithdrawResultBO.Builder builder() {
		return new Builder();
	}

	public String getUids() {
		return uids;
	}

	public boolean isSuccess() {
		return success;
	}

	public String getDate() {
		return date;
	}

	public String getAmount() {
		return amount;
	}

	public String getReason() {
		return reason;
	}
	
	public static class Builder{
		/** 用户ID, 多个用英文逗号分隔 */
		private String uids;
		
		/** 提现结果 true-成功, false-失败 */
		private boolean success;
		
		/** 时间 */
		private String date;
		
		/** 数额 */
		private String amount;
		
		/** 提现失败才有原因 */
		private String reason;
		
		public Builder setUids(String uids) {
			this.uids = uids;
			return this;
		}

		public Builder setSuccess(boolean success) {
			this.success = success;
			return this;
		}

		public Builder setDate(String date) {
			this.date = date;
			return this;
		}

		public Builder setAmount(String amount) {
			this.amount = amount;
			return this;
		}

		public Builder setReason(String reason) {
			this.reason = reason;
			return this;
		}

		/**
		 * 构造WithdrawResultBean
		 * @return
		 */
		public WithdrawResultBO build(){
			return new WithdrawResultBO(uids, success, date, amount, reason);
		}
		
		/**
		 * 发送MQ请求
		 */
		public void request(){
			CommandMQProducer.get().sendJSON(BussinessType.WITHDRAW_RESULT, new WithdrawResultBO(uids, success, date, amount, reason));
		}
	}
	

}
