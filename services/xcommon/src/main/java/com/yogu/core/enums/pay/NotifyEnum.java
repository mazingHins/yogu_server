package com.yogu.core.enums.pay;


/**
 * 回调业务枚举
 * @author Hins
 * @date 2015年8月31日 下午2:43:19
 */
public class NotifyEnum {

	/**
	 * 支付宝交易状态
	 */
	public enum AlipayTradeStatus {
		/**
		 * 交易创建，等待买家付款。
		 */
		WAIT_BUYER_PAY((short) 1), 
		
		/**
		 * 在指定时间段内未支付时关闭的交易；
		 * 在交易成全额退款成功时关闭的交易。
		 */
		TRADE_CLOSED((short) 2), 
		
		/**
		 * 交易成功，且可对该交易做操作，如：多级分润、退款等。
		 */
		TRADE_SUCCESS((short) 3), 
		
		/**
		 * 交易成功且结束，即不可再做任何操作。
		 */
		TRADE_FINISHED((short) 4);
		
		private short value;

		private AlipayTradeStatus(short value) {
			this.value = value;
		}

		public short getValue() {
			return value;
		}
		
		public static AlipayTradeStatus valueOf(int value) {
			switch (value) {
			case 1:
				return WAIT_BUYER_PAY;
			case 2:
				return TRADE_CLOSED;
			case 3:
				return TRADE_SUCCESS;
			case 4:
				return TRADE_FINISHED;
			default:
				return null;
			}
		}

	}
	
	/**
	 * 支付宝回调通知类型
	 */
	public enum AlipayNotifyType {
		
		trade_status_sync((short) 1),
		
		batch_refund_notify((short) 2),
		
		batch_trans_notify((short) 3);
		
		private short value;

		private AlipayNotifyType(short value) {
			this.value = value;
		}

		public short getValue() {
			return value;
		}
	}
	

	
	/**
	 * 支付记录支付状态 
	 */
	public enum PayRecordStatus {
		/**
		 * 未支付
		 */
		NOT_PAY((short) 0), 
		
		/**
		 * 支付成功
		 */
		SUCCESS_PAY((short) 1), 
		
		/**
		 * 支付失败
		 */
		FAIL_PAY((short) 2);
		
		private short value;

		private PayRecordStatus(short value) {
			this.value = value;
		}

		public short getValue() {
			return value;
		}
	}
	
	/**
	 * 业务流转的状态（涉及到支付记录相关科目变更时的状态）
	 *
	 * @date 2016年8月15日 下午2:23:37
	 * @author hins
	 */
	public enum BizStatus {
		
		/**
		 * 默认值
		 */
		DEFAULT((short) 0),
		
		/**
		 * 确认收货，进入待入账
		 */
		CONFIRMED((short) 10), 
		
		/**
		 * 已经结算，进入可提现
		 */
		SETTLE((short) 20), 
		
		/**
		 * 支付被取消，执行退款。
		 */
		REFUND((short) 30);
		
		private short value;

		private BizStatus(short value) {
			this.value = value;
		}

		public short getValue() {
			return value;
		}
		
	}
	
	/**
	 * 第三方配送记录的业务流转状态定义
	 *
	 * @date 2016年10月12日 下午7:18:11
	 * @author hins
	 */
	public enum ExpressBizStatus {
		
		/**
		 * 开始配送
		 */
		DELIVERY((short)10),
		
		/**
		 * 确认收货，可以结算
		 */
		CONFIRMED((short)20),
		
		/**
		 * 取消配送
		 */
		CANCEL_DELIVERY((short)30);
		
		private short value;

		private ExpressBizStatus(short value) {
			this.value = value;
		}

		public short getValue() {
			return value;
		}
		
	}

	
	/**
	 * 支付记录回调状态 
	 */
	public enum NotifyStatus {
		/**
		 * 未回调
		 */
		NOT_NOTIFY((short) 0), 
		
		/**
		 * 回调成功
		 */
		SUCCESS_NOTIFY((short) 1), 
		
		/**
		 * 回调失败
		 */
		FAIL_NOTIFY((short) 2);
		
		private short value;

		private NotifyStatus(short value) {
			this.value = value;
		}

		public short getValue() {
			return value;
		}
	}
	
	/**
	 * 微信支付回调记录状态值定义
	 * 
	 * @author Hins
	 * @date 2016年2月2日 下午3:28:33
	 */
	public enum WechatPayNotifyStatus {
		
		/**
		 * 支付成功
		 */
		SUCCESS_PAY((short) 1), 
		
		/**
		 * 支付失败
		 */
		FAIL_PAY((short) 2);
		
		private short value;

		private WechatPayNotifyStatus(short value) {
			this.value = value;
		}

		public short getValue() {
			return value;
		}

	}

	
}
