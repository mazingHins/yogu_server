package com.yogu.core.consumer.messageBean;

import java.io.Serializable;

import com.yogu.core.consumer.BussinessType;
import com.yogu.mq.impl.aliyun.CommandMQProducer;

/**
 * 每一笔交易明细对账的bean
 *
 * @date 2016年6月22日 下午4:06:53
 * @author hins
 */
public class AccountDetailBeanBO implements Serializable {
	
	private static final long serialVersionUID = 5973965418203260208L;

	/**
	 * 对账类型，1-商家接单；2-商家取消订单（退款）；3-用户确认收货（C端）；4-T+N定时任务；5-申请提现；6-提现成功
	 */
	private int detailType;
	
	/**
	 * 订单编号
	 */
	private long orderNo;
	
	/**
	 * 支付编号
	 */
	private long payNo;
	
	/**
	 * 支付id
	 */
	private long payId;
	
	/**
	 * 提现id
	 */
	private long withdrawId;
	
	public AccountDetailBeanBO(){}
	
	AccountDetailBeanBO(int detailType, long orderNo, long payNo, long payId, long withdrawId) {
		this.detailType = detailType;
		this.orderNo = orderNo;
		this.payNo = payNo;
		this.payId = payId;
		this.withdrawId = withdrawId;
	}
	
	public static AccountDetailBeanBO.Builder builder() {
		return new Builder();
	}

	public int getDetailType() {
		return detailType;
	}

	public long getOrderNo() {
		return orderNo;
	}

	public long getPayNo() {
		return payNo;
	}

	public long getPayId() {
		return payId;
	}

	public long getWithdrawId() {
		return withdrawId;
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
		 * 支付编号
		 */
		private long payNo;
		
		/**
		 * 支付id
		 */
		private long payId;
		
		/**
		 * 提现id
		 */
		private long withdrawId;
		
		public Builder setDetailType(int i) {
			this.detailType = i;
			return this;
		}

		public Builder setPayNo(long payNo) {
			this.payNo = payNo;
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
		public AccountDetailBeanBO build(){
			return new AccountDetailBeanBO(detailType, orderNo, payNo, payId, withdrawId);
		}
		
		/**
		 * 发送MQ请求
		 */
		public void request(){
			CommandMQProducer.get().sendJSON(BussinessType.ACCOUNT_DETAIL, new AccountDetailBeanBO(detailType, orderNo, payNo, payId, withdrawId));
		}
		
	}

}
