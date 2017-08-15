package com.yogu.core.enums.order;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 订单状态相关的工具类。<br>
 * 如不同查询情况下的订单状态管理
 * 
 * @author Hins
 * @date 2015年9月18日 上午10:11:33
 */
public enum OrderStatusUtils {

	INSTANCE;

	/**
	 * 用户正在进行中的订单状态
	 */
	private List<Short> userOngoingList;
	private List<Short> userOngoingListByH5;// 与APP比较，不包含 待评论 的订单

	/**
	 * 用户的历史订单状态
	 */
	private List<Short> usertHistoryList;

	/**
	 * 用户未评论的订单状态
	 */
	private List<Short> userNotCommentList;

	/**
	 * 商家已结束的订单状态
	 */
	private List<Short> storeFinishList;

	/**
	 * 米星付商家已结束的订单状态
	 */
	private List<Short> mazingPayStoreFinishList;

	/**
	 * 用户正在进行中订单轨迹查询条件的订单状态
	 */
	private List<Short> userOngoingTrackList;

	/**
	 * 商家配送中列表的订单状态查询条件
	 */
	private List<Short> storeDeliveryList;

	/**
	 * 统计: 现金支付订单的查询条件
	 */
	private List<Short> statisticsCashOrderList;

	/**
	 * 统计: 线上支付订单的查询条件
	 */
	private List<Short> statisticsOnlineOrderList;

	/**
	 * 统计: 一天内完成线上支付或使用现金支付的订单总金额
	 */
	private List<Short> statisticsSumTotalFeeList;

	/**
	 * 统计: 一天内选择线上支付, 但未支付或取消支付的查询条件
	 */
	private List<Short> statisticsNotPayList;

	/**
	 * 统计报表-完成的订单状态查询条件
	 */
	private List<Short> statisticsFinishList;

	/**
	 * 统计报表-成功取消的订单数
	 */
	private List<Short> statisticsSuccessCancelList;

	/**
	 * 对账: 交易中的状态列表
	 */
	private List<Short> accountingDealingList;

	/**
	 * 对账: 完成订单的状态列表
	 */
	private List<Short> accountingSettlingList;

	/**
	 * 商家已结束的订单状态
	 */
	private Set<Short> storeFinishSet;

	/**
	 * 满足退款定义的的订单状态（包括退款申请，退款中，退款失败，退款完成）
	 */
	private Set<Short> refundConditionSet;

	/**
	 * IM进行中订单的状态
	 */
	private List<Short> imOrderList;

	/**
	 * 商家正在进行中的订单
	 */
	private List<Short> storeInProgressOrderList;

	/**
	 * 商家报表统计的订单状态
	 */
	private List<Short> storePayReportOrderList;

	/**
	 * 商家报表统计线上支付优惠费用时的订单状态
	 */
	private List<Short> storeOrderReportCouponList;

	private List<Short> orderTicketList;

	OrderStatusUtils() {
		userOngoingList = new ArrayList<Short>(6);
		userOngoingList.add(OrderStatus.NON_PAYMENT.getValue());
		userOngoingList.add(OrderStatus.PENDING_ACCEPT.getValue());
		userOngoingList.add(OrderStatus.ACCEPT_ORDER.getValue());
		userOngoingList.add(OrderStatus.FINISH_COOKING.getValue());
		userOngoingList.add(OrderStatus.DELIVERY.getValue());
		userOngoingList.add(OrderStatus.CONFIRM_RECEIPT_USER.getValue());

		userOngoingListByH5 = new ArrayList<Short>(6);
		userOngoingListByH5.add(OrderStatus.NON_PAYMENT.getValue());
		userOngoingListByH5.add(OrderStatus.PENDING_ACCEPT.getValue());
		userOngoingListByH5.add(OrderStatus.ACCEPT_ORDER.getValue());
		userOngoingListByH5.add(OrderStatus.FINISH_COOKING.getValue());
		userOngoingListByH5.add(OrderStatus.DELIVERY.getValue());
		// userOngoingListByH5.add(OrderStatus.CONFIRM_RECEIPT_USER.getValue());//
		// H5 查看，不用显示 待评论 的

		userOngoingTrackList = new ArrayList<Short>(5);
		userOngoingTrackList.add(OrderConstants.ORDER_TRACK_INIT_STATUS);
		userOngoingTrackList.add(OrderStatus.NON_PAYMENT.getValue());
		userOngoingTrackList.add(OrderStatus.PENDING_ACCEPT.getValue());
		userOngoingTrackList.add(OrderStatus.FINISH_COOKING.getValue());
		userOngoingTrackList.add(OrderStatus.DELIVERY.getValue());

		usertHistoryList = new ArrayList<Short>(6);
		usertHistoryList.add(OrderStatus.HAS_COMMENT.getValue());
		usertHistoryList.add(OrderStatus.REFUND_APPLY.getValue());
		usertHistoryList.add(OrderStatus.REFUND.getValue());
		usertHistoryList.add(OrderStatus.REFUND_SUCCESS.getValue());
		usertHistoryList.add(OrderStatus.REFUND_REFUSE.getValue());
		usertHistoryList.add(OrderStatus.CANCEL.getValue());

		userNotCommentList = new ArrayList<Short>(5);
		userNotCommentList.add(OrderStatus.PENDING_ACCEPT.getValue());
		userNotCommentList.add(OrderStatus.ACCEPT_ORDER.getValue());
		userNotCommentList.add(OrderStatus.FINISH_COOKING.getValue());
		userNotCommentList.add(OrderStatus.DELIVERY.getValue());
		userNotCommentList.add(OrderStatus.CONFIRM_RECEIPT_USER.getValue());

		storeFinishList = new ArrayList<Short>(5);
		storeFinishList.add(OrderStatus.CONFIRM_RECEIPT_USER.getValue());
		storeFinishList.add(OrderStatus.HAS_COMMENT.getValue());
		storeFinishList.add(OrderStatus.REFUND_SUCCESS.getValue());
		storeFinishList.add(OrderStatus.REFUND_REFUSE.getValue());
		storeFinishList.add(OrderStatus.CANCEL.getValue());

		mazingPayStoreFinishList = new ArrayList<>(7);
		mazingPayStoreFinishList.add(OrderStatus.CONFIRM_RECEIPT_USER.getValue());
		mazingPayStoreFinishList.add(OrderStatus.HAS_COMMENT.getValue());
		mazingPayStoreFinishList.add(OrderStatus.REFUND_APPLY.getValue());
		mazingPayStoreFinishList.add(OrderStatus.REFUND.getValue());
		mazingPayStoreFinishList.add(OrderStatus.REFUND_SUCCESS.getValue());
		mazingPayStoreFinishList.add(OrderStatus.REFUND_REFUSE.getValue());
		mazingPayStoreFinishList.add(OrderStatus.CANCEL.getValue());

		storeDeliveryList = new ArrayList<Short>(1);
		storeDeliveryList.add(OrderStatus.DELIVERY.getValue());

		statisticsCashOrderList = Arrays.asList(OrderStatus.PENDING_ACCEPT.getValue(),
				OrderStatus.ACCEPT_ORDER.getValue(), OrderStatus.FINISH_COOKING.getValue(),
				OrderStatus.DELIVERY.getValue(), OrderStatus.CONFIRM_RECEIPT_USER.getValue(),
				OrderStatus.HAS_COMMENT.getValue(), OrderStatus.CANCEL.getValue());

		statisticsOnlineOrderList = Arrays.asList(OrderStatus.PENDING_ACCEPT.getValue(),
				OrderStatus.ACCEPT_ORDER.getValue(), OrderStatus.FINISH_COOKING.getValue(),
				OrderStatus.DELIVERY.getValue(), OrderStatus.CONFIRM_RECEIPT_USER.getValue(),
				OrderStatus.HAS_COMMENT.getValue(), OrderStatus.REFUND.getValue(), OrderStatus.REFUND_APPLY.getValue(),
				OrderStatus.REFUND_REFUSE.getValue(), OrderStatus.REFUND_SUCCESS.getValue());

		statisticsSumTotalFeeList = Arrays.asList(OrderStatus.PENDING_ACCEPT.getValue(),
				OrderStatus.ACCEPT_ORDER.getValue(), OrderStatus.FINISH_COOKING.getValue(),
				OrderStatus.DELIVERY.getValue(), OrderStatus.CONFIRM_RECEIPT_USER.getValue(),
				OrderStatus.HAS_COMMENT.getValue(), OrderStatus.REFUND.getValue(), OrderStatus.REFUND_APPLY.getValue(),
				OrderStatus.REFUND_REFUSE.getValue(), OrderStatus.REFUND_SUCCESS.getValue(),
				OrderStatus.CANCEL.getValue());

		statisticsNotPayList = Arrays.asList(OrderStatus.NON_PAYMENT.getValue(), OrderStatus.CANCEL.getValue());

		statisticsSuccessCancelList = Arrays.asList(OrderStatus.REFUND_SUCCESS.getValue(),
				OrderStatus.CANCEL.getValue());

		statisticsFinishList = new ArrayList<Short>(2);
		statisticsFinishList.add(OrderStatus.CONFIRM_RECEIPT_USER.getValue());
		statisticsFinishList.add(OrderStatus.HAS_COMMENT.getValue());

		accountingDealingList = Arrays.asList(OrderStatus.ACCEPT_ORDER.getValue(),
				OrderStatus.FINISH_COOKING.getValue(), OrderStatus.DELIVERY.getValue());

		accountingSettlingList = Arrays.asList(OrderStatus.CONFIRM_RECEIPT_USER.getValue(),
				OrderStatus.HAS_COMMENT.getValue());

		storeFinishSet = new HashSet<Short>(6);
		storeFinishSet.add(OrderStatus.CONFIRM_RECEIPT_USER.getValue());
		storeFinishSet.add(OrderStatus.HAS_COMMENT.getValue());
		storeFinishSet.add(OrderStatus.REFUND_SUCCESS.getValue());
		storeFinishSet.add(OrderStatus.REFUND_REFUSE.getValue());
		storeFinishSet.add(OrderStatus.CANCEL.getValue());

		refundConditionSet = new HashSet<Short>(6);
		refundConditionSet.add(OrderStatus.REFUND_APPLY.getValue());
		refundConditionSet.add(OrderStatus.REFUND.getValue());
		refundConditionSet.add(OrderStatus.REFUND_REFUSE.getValue());
		refundConditionSet.add(OrderStatus.REFUND_SUCCESS.getValue());

		imOrderList = Arrays.asList(OrderStatus.PENDING_ACCEPT.getValue(), OrderStatus.ACCEPT_ORDER.getValue(),
				OrderStatus.FINISH_COOKING.getValue(), OrderStatus.DELIVERY.getValue());

		storeInProgressOrderList = Arrays.asList(OrderStatus.PENDING_ACCEPT.getValue(),
				OrderStatus.ACCEPT_ORDER.getValue(), OrderStatus.FINISH_COOKING.getValue());

		storePayReportOrderList = Arrays.asList(OrderStatus.PENDING_ACCEPT.getValue(),
				OrderStatus.ACCEPT_ORDER.getValue(), OrderStatus.FINISH_COOKING.getValue(),
				OrderStatus.DELIVERY.getValue(), OrderStatus.CONFIRM_RECEIPT_USER.getValue(),
				OrderStatus.HAS_COMMENT.getValue(), OrderStatus.REFUND.getValue(), OrderStatus.REFUND_APPLY.getValue(),
				OrderStatus.REFUND_REFUSE.getValue(), OrderStatus.REFUND_SUCCESS.getValue());

		storeOrderReportCouponList = Arrays.asList(OrderStatus.PENDING_ACCEPT.getValue(),
				OrderStatus.ACCEPT_ORDER.getValue(), OrderStatus.FINISH_COOKING.getValue(),
				OrderStatus.DELIVERY.getValue(), OrderStatus.CONFIRM_RECEIPT_USER.getValue(),
				OrderStatus.HAS_COMMENT.getValue());
		
		orderTicketList = Arrays.asList(OrderStatus.CANCEL.getValue(), OrderStatus.PENDING_ACCEPT.getValue(), OrderStatus.REFUND.getValue(),
				OrderStatus.REFUND_APPLY.getValue(), OrderStatus.REFUND_REFUSE.getValue());
	}

	public List<Short> getUserOngoingList() {
		return userOngoingList;
	}

	public List<Short> getUserOngoingListByH5() {
		return userOngoingListByH5;
	}

	public List<Short> getUsertHistoryList() {
		return usertHistoryList;
	}

	public List<Short> getUserNotCommentList() {
		return userNotCommentList;
	}

	public List<Short> getStoreFinishList() {
		return storeFinishList;
	}

	public List<Short> getUserOngoingTrackList() {
		return userOngoingTrackList;
	}

	public List<Short> getStoreDeliveryList() {
		return storeDeliveryList;
	}

	public List<Short> getStatisticsCashOrderList() {
		return statisticsCashOrderList;
	}

	public List<Short> getStatisticsOnlineOrderList() {
		return statisticsOnlineOrderList;
	}

	public List<Short> getStatisticsSumTotalFeeList() {
		return statisticsSumTotalFeeList;
	}

	public List<Short> getStatisticsFinishList() {
		return statisticsFinishList;
	}

	public List<Short> getStatisticsNotPayList() {
		return statisticsNotPayList;
	}

	public List<Short> getStatisticsSuccessCancelList() {
		return statisticsSuccessCancelList;
	}

	public List<Short> getAccountingDealingList() {
		return accountingDealingList;
	}

	public List<Short> getAccountingSettlingList() {
		return accountingSettlingList;
	}

	public Set<Short> getStoreFinishSet() {
		return storeFinishSet;
	}

	public List<Short> getMazingPayStoreFinishList() {
		return mazingPayStoreFinishList;
	}

	public Set<Short> getRefundSet() {
		return refundConditionSet;
	}

	public List<Short> getImOrderList() {
		return imOrderList;
	}

	public List<Short> getStoreInProgressOrderList() {
		return storeInProgressOrderList;
	}

	public List<Short> getStorePayReportOrderList() {
		return storePayReportOrderList;
	}

	public List<Short> getStoreOrderReportCouponList() {
		return storeOrderReportCouponList;
	}

	public List<Short> getOrderTicketList() {
		return orderTicketList;
	}

}
