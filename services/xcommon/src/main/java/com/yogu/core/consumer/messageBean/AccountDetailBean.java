package com.yogu.core.consumer.messageBean;

import java.io.Serializable;

/**
 * 每一笔金额变动涉及对账请求的 messageBean
 *
 * @date 2016年6月20日 下午3:18:49
 * @author hins
 */
public class AccountDetailBean implements Serializable {
	
	private static final long serialVersionUID = 2688173101791234736L;

	/**
	 * 对账类型，1-商家接单；2-商家取消订单（退款）；3-用户确认收货（C端）；4-T+N定时任务；5-申请提现；6-提现成功
	 */
	private int detailType;
	
	/**
	 * 订单编号
	 */
	private long orderNo;
	
	/**
	 * 提现id
	 */
	private long withdrawId;
	
	/**
	 * 支付id
	 */
	private long payId;
	
	public AccountDetailBean(){}
	
	AccountDetailBean(int detailType, long orderNo, long payId, long withdrawId) {
		this.detailType = detailType;
		this.orderNo = orderNo;
		this.payId = payId;
		this.withdrawId = withdrawId;
	}
	
	public static AccountDetailBean.Builder builder() {
		return new Builder();
	}

	public int getDetailType() {
		return detailType;
	}

	public void setDetailType(int detailType) {
		this.detailType = detailType;
	}

	public long getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(long orderNo) {
		this.orderNo = orderNo;
	}

	public long getWithdrawId() {
		return withdrawId;
	}

	public void setWithdrawId(long withdrawId) {
		this.withdrawId = withdrawId;
	}
	
	public long getPayId() {
		return payId;
	}

	public void setPayId(long payId) {
		this.payId = payId;
	}
	

	public static class Builder{
		/**
		 * 对账类型，1-商家接单；2-商家取消订单（退款）；3-用户确认收货（C端）；4-T+N定时任务；5-申请提现；6-提现成功
		 */
		private int detailType;
		
		/**
		 * 订单编号
		 */
		private long orderNo;
		
		/**
		 * 提现id
		 */
		private long withdrawId;
		
		/**
		 * 支付id
		 */
		private long payId;
		
		public Builder setDetailType(int detailType) {
			this.detailType = detailType;
			return this;
		}

		public Builder setWithdrawId(long withdrawId) {
			this.withdrawId = withdrawId;
			return this;
		}

		public Builder setOrderNo(long orderNo) {
			this.orderNo = orderNo;
			return this;
		}
		
		public Builder setPayId(long payId) {
			this.payId = payId;
			return this;
		}
		
		/**
		 * 构造MQ Bean
		 * @return
		 */
		public AccountDetailBean build(){
			return new AccountDetailBean(detailType, orderNo, payId, withdrawId);
		}
		
	}
	
}
