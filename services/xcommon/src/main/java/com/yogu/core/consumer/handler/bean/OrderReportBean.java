package com.yogu.core.consumer.handler.bean;

import java.io.Serializable;

/**
 * 完成订单，商家接单，用户取消订单，订单退款完成时候的 messageBean<br>
 * (producer 中 传递的 message body, consumer 获取该message body做相关业务操作)
 * 
 * @author Hins
 * @date 2015年9月26日 下午3:47:25
 */
public class OrderReportBean implements Serializable {

	private static final long serialVersionUID = 5258953057935753L;

	/**
	 * 报表类型：1-完成订单；2-取消订单；3-订单退款；4-商家接单
	 */
	private short type;

	/**
	 * 门店ID
	 */
	private long storeId;

	/**
	 * 报表日期yyyyMMdd格式
	 */
	private int time;

	/**
	 * 相关费用（分），跟报表类型对应
	 */
	private long orderFee;
	
	/**
	 * 线上支付费用
	 */
	private long onlineFee;
	
	/**
	 * 货到付款费用
	 */
	private long cashFee;

	public enum BeanType {
		/**
		 * 完成订单
		 */
		FINISH_ORDER((short) 1),

		/**
		 * 取消订单
		 */
		CANCEL_ORDER((short) 2),

		/**
		 * 订单退款
		 */
		REFUND_ORDER((short) 3),

		/**
		 * 接单
		 */
		ACCEPT_ORDER((short) 4),
		
		/**
		 * 米星付成功
		 */
		MAZING_PAY_SUCCESS((short) 5),
		
		/**
		 * 米星付退款
		 */
		MAZING_PAY_REFUND((short) 6),
		
		/**
		 * 已完成订单退款
		 */
		REFUND_FINISH_ORDER((short) 7);

		private short value;

		private BeanType(short value) {
			this.value = value;
		}

		public short getValue() {
			return value;
		}
		
		public static BeanType form(short value){
			switch (value) {
			case 1:
				return FINISH_ORDER;
			case 2:
				return CANCEL_ORDER;
			case 3:
				return REFUND_ORDER;
			case 4:
				return ACCEPT_ORDER;
			case 5:
				return MAZING_PAY_SUCCESS;
			case 6:
				return MAZING_PAY_REFUND;
			case 7:
				return REFUND_FINISH_ORDER;
			default:
				return null;
			}
		}

	}

	public short getType() {
		return type;
	}

	public void setType(short type) {
		this.type = type;
	}

	public long getStoreId() {
		return storeId;
	}

	public void setStoreId(long storeId) {
		this.storeId = storeId;
	}

	public int getTime() {
		return time;
	}

	public void setTime(int time) {
		this.time = time;
	}

	public long getOrderFee() {
		return orderFee;
	}

	public void setOrderFee(long orderFee) {
		this.orderFee = orderFee;
	}

	public long getOnlineFee() {
		return onlineFee;
	}

	public void setOnlineFee(long onlineFee) {
		this.onlineFee = onlineFee;
	}

	public long getCashFee() {
		return cashFee;
	}

	public void setCashFee(long cashFee) {
		this.cashFee = cashFee;
	}
	
}
