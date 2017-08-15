package com.yogu.services.order.base.dao.impl;

import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Named;

import com.yogu.commons.datasource.mybatis.MyBatisReadonlyDao;
import com.yogu.core.enums.order.OrderStatus;
import com.yogu.core.enums.pay.PayType;
import com.yogu.services.order.base.dao.OrderReadonlyDao;
import com.yogu.services.order.base.dto.vo.OrderByDateVO;
import com.yogu.services.order.base.dto.vo.OrderForExcelVO;
import com.yogu.services.order.base.entry.OrderPO;
import com.yogu.services.order.base.entry.TinyOrderPO;
import com.yogu.services.order.constants.OrderSearchCategory;

@Named
public class OrderReadonlyDaoImpl extends MyBatisReadonlyDao implements OrderReadonlyDao {

	@Override
	public OrderPO getById(long orderId) {
		return super.get("com.mazing.services.order.base.dao.OrderDao.getById", orderId);
	}
	
	@Override
	public OrderPO getByOrderNoUid(long uid, long orderNo) {
		Map<String, Object> map = new HashMap<>(3);
		map.put("uid", uid);
		map.put("orderNo", orderNo);
		return super.get("com.mazing.services.order.base.dao.OrderDao.getByOrderNoUid", map);
	}

	@Override
	public OrderPO getByOrderNo(long orderNo) {
		return super.get("com.mazing.services.order.base.dao.OrderDao.getByOrderNo", orderNo);
	}
	
	@Override
	public Map<String, Object> statFinishTotal(Date start, Date end, List<Short> status, List<Short> payType) {
		Map<String, Object> map = new HashMap<>(6);
		map.put("start", start);
		map.put("end", end);
		map.put("status", status);
		map.put("payType", payType);
		return super.get("com.mazing.services.order.base.dao.OrderDao.statFinishTotal", map);
	}
	
	@Override
	public List<OrderPO> listUserOngoing(long uid, List<Short> payType, List<Short> status, Long storeId) {
		Map<String, Object> map = new HashMap<>(4);
		map.put("uid", uid);
		map.put("payType", payType);
		map.put("status", status);
		map.put("storeId", storeId);
		return super.list("com.mazing.services.order.base.dao.OrderDao.listUserOngoing", map);
	}
	
	@Override
	public List<OrderPO> listUserOngoingByPage(long uid, List<Short> payType, List<Short> status, Long storeId, int offset, int pageSize) {
		Map<String, Object> map = new HashMap<>(8);
		map.put("uid", uid);
		map.put("payType", payType);
		map.put("status", status);
		map.put("storeId", storeId);
		map.put("offset", offset);
		map.put("pageSize", pageSize);
		return super.list("com.mazing.services.order.base.dao.OrderDao.listUserOngoingByPage", map);
	}
	
	@Override
	public List<OrderPO> listUserHistory(long uid, List<Short> payType, List<Short> status, int offset, int pageSize) {
		Map<String, Object> map = new HashMap<>(6);
		map.put("uid", uid);
		map.put("payType", payType);
		map.put("status", status);
		map.put("offset", offset);
		map.put("pageSize", pageSize);
		return super.list("com.mazing.services.order.base.dao.OrderDao.listUserHistory", map);
	}
	
	@Override
	public List<OrderPO> listByField(long storeId, String content, OrderSearchCategory category, List<Short> payType) {
		Map<String, Object> map = new HashMap<>(6);
		map.put("storeId", storeId);
		map.put("content", content);
		map.put("category", category.toString());
		map.put("status", OrderStatus.NON_PAYMENT.getValue());
		map.put("payType", payType);
		return super.list("com.mazing.services.order.base.dao.OrderDao.listByField", map);
	}
	
	@Override
	public List<OrderPO> listStoreOngoing2(Long deliverId, long storeId, Short pickType, List<Short> status, long sequence, int pageSize) {
		Map<String, Object> map = new HashMap<>(6);
		map.put("storeId", storeId);
		if (pickType != null)
			map.put("pickType", pickType);
		map.put("status", status);
		map.put("sequence", sequence);
		map.put("pageSize", pageSize);
		if(deliverId !=null)
			map.put("deliverId", deliverId);
		
		return super.list("com.mazing.services.order.base.dao.OrderDao.listStoreOngoing2", map);
	}
	
	@Override
	public List<OrderPO> listStoreOngoingOrdersByDeliverId(Long deliverId, long storeId, Short pickType, List<Short> status, int offset,
			int pageSize) {
		Map<String, Object> map = new HashMap<>(6);
		map.put("storeId", storeId);
		if (pickType != null)
			map.put("pickType", pickType);
		map.put("status", status);
		map.put("offset", offset);
		map.put("pageSize", pageSize);
		
		if(deliverId !=null)
			map.put("deliverId", deliverId);
		
		return super.list("com.mazing.services.order.base.dao.OrderDao.listStoreOngoing", map);
		
	}
	
	@Override
	public List<OrderPO> listStoreOngoing(long storeId, Short pickType, List<Short> status, int offset, int pageSize) {
		Map<String, Object> map = new HashMap<>(5);
		map.put("storeId", storeId);
		if (pickType != null)
			map.put("pickType", pickType);
		map.put("status", status);
		map.put("offset", offset);
		map.put("pageSize", pageSize);
		return super.list("com.mazing.services.order.base.dao.OrderDao.listStoreOngoing", map);
	}
	
	@Override
	public List<OrderPO> listStoreFinishByPayType(long storeId, List<Short> status, short payType, int offset, int pageSize) {
		Map<String, Object> map = new HashMap<>(6);
		map.put("storeId", storeId);
		map.put("status", status);
		map.put("payType", payType);
		map.put("offset", offset);
		map.put("pageSize", pageSize);
		return super.list("com.mazing.services.order.base.dao.OrderDao.listStoreFinishByPayType", map);
	}
	
	@Override
	public List<OrderPO> listStoreFinish(long storeId, List<Short> status, List<Short> payType, int offset, int pageSize) {
		Map<String, Object> map = new HashMap<>(8);
		map.put("storeId", storeId);
		map.put("status", status);
		map.put("payType", payType);
		map.put("offset", offset);
		map.put("pageSize", pageSize);
		// 查询条件新增订单开始时间不能为空（2016/7/8 add by hins 为了防止出现下单（线上单）没支付给取消的订单出现再商家端）
		return super.list("com.mazing.services.order.base.dao.OrderDao.listStoreFinish", map);
	}
	
	@Override
	public List<OrderPO> listOrdersByStoreIdAndPickTypeWithStatus2(long storeId, List<Short> payType, Short pickType, Short status, long sequence, int pageSize,
			String orderBy) {
		Map<String, Object> map = new HashMap<>(8);
		map.put("storeId", storeId);
		map.put("payType", payType);
		map.put("pickType", pickType);
		map.put("status", status);
		map.put("sequence", sequence);
		map.put("pageSize", pageSize);
		map.put("orderBy", orderBy);

		return super.list("com.mazing.services.order.base.dao.OrderDao.listOrdersByStoreIdAndPickTypeWithStatus2", map);
	}
	
	@Override
	public List<OrderPO> listOrdersByStoreIdAndPickTypeWithStatus(long storeId, Short pickType, Short status, int offset, int pageSize,
			String orderBy) {

		Map<String, Object> map = new HashMap<>(8);
		map.put("storeId", storeId);
		map.put("pickType", pickType);
		map.put("status", status);
		map.put("offset", offset);
		map.put("pageSize", pageSize);
		map.put("orderBy", orderBy);

		return super.list("com.mazing.services.order.base.dao.OrderDao.listOrdersByStoreIdAndPickTypeWithStatus", map);
	}
	
	@Override
	public List<OrderPO> listStatusAndTimeOut(Date start, Date end, short status) {
		Map<String, Object> map = new HashMap<>(3);
		map.put("start", start);
		map.put("end", end);
		map.put("status", status);
		return super.list("com.mazing.services.order.base.dao.OrderDao.listStatusAndTimeOut", map);
	}
	
	@Override
	public List<OrderPO> listStoreConfirm(Date beginTime, Date endTime, long status) {
		Map<String, Object> map = new HashMap<>(3);
		map.put("beginTime", beginTime);
		map.put("endTime", endTime);
		map.put("status", status);
		return super.list("com.mazing.services.order.base.dao.OrderDao.listStoreConfirm", map);
	}
	
	@Override
	public List<OrderPO> listOrderByStoreId(long storeId, Short status, int offset, int pageSize) {
		Map<String, Object> map = new HashMap<>(4);
		map.put("storeId", storeId);
		map.put("status", status);
		map.put("offset", offset);
		map.put("pageSize", pageSize);
		return super.list("com.mazing.services.order.base.dao.OrderDao.listOrderByStoreId", map);
	}
	
	@Override
	public List<OrderPO> listByStatusAndPayTypeBeforeTime(short status, short payType, int offset, int pageSize, Date time) {

		Map<String, Object> map = new HashMap<>(6);
		map.put("status", status);
		map.put("payType", payType);
		map.put("offset", offset);
		map.put("pageSize", pageSize);
		map.put("time", time);
		return super.list("com.mazing.services.order.base.dao.OrderDao.listByStatusAndPayModeBeforeTime", map);
	}
	
	@Override
	public List<TinyOrderPO> listByStatusAndCreateTime(short status, Date time) {
		Map<String, Object> map = new HashMap<>(2);
		map.put("status", status);
		map.put("time", time);
		return super.list("com.mazing.services.order.base.dao.OrderDao.listByStatusAndCreateTime", map);
	}
	
	@Override
	public List<OrderPO> listPendingAcceptOrders(long storeId, short status, int offset, int pageSize) {
		Map<String, Object> map = new HashMap<>(4);
		map.put("storeId", storeId);
		map.put("status", status);
		map.put("offset", offset);
		map.put("pageSize", pageSize);
		return super.list("com.mazing.services.order.base.dao.OrderDao.listPendingAcceptOrders", map);
	}
	
	@Override
	public List<OrderPO> listPendingAcceptOrders2(long storeId, short status, Date orderBeginTime, int pageSize) {
		Map<String, Object> map = new HashMap<>(4);
		map.put("storeId", storeId);
		map.put("status", status);
		map.put("orderBeginTime", orderBeginTime);
		map.put("pageSize", pageSize);
		return super.list("com.mazing.services.order.base.dao.OrderDao.listPendingAcceptOrders2", map);
	}
	
	@Override
	public List<OrderPO> listStoreRefundOrders(long storeId, short payType, List<Short> refundTypes,
			List<Short> status, Date refundCreateTime, int pageSize) {
		Map<String, Object> map = new HashMap<>(8);
		map.put("storeId", storeId);
		map.put("payType", payType);
		map.put("refundTypes", refundTypes);
		map.put("status", status);
		map.put("refundCreateTime", refundCreateTime);
		map.put("pageSize", pageSize);
		return super.list("com.mazing.services.order.base.dao.OrderDao.listStoreRefundOrders", map);
	}
	
	@Override
	public List<OrderPO> listPendingAcceptOrders3(long storeId, short status, Date returnOrderBeginTime, Date newOrderBeginTime,
			int pageSize) {
		Map<String, Object> map = new HashMap<>(5);
		map.put("storeId", storeId);
		map.put("status", status);
		map.put("returnOrderBeginTime", returnOrderBeginTime);
		map.put("newOrderBeginTime", newOrderBeginTime);
		map.put("pageSize", pageSize);
		return super.list("com.mazing.services.order.base.dao.OrderDao.listPendingAcceptOrders3", map);
	}
	
	@Override
	public List<OrderPO> listIsBackPendingAcceptOrders(long storeId, short status, List<Short> payType) {
		Map<String, Object> map = new HashMap<>(4);
		map.put("storeId", storeId);
		map.put("status", status);
		map.put("payType", payType);
		return super.list("com.mazing.services.order.base.dao.OrderDao.listIsBackPendingAcceptOrders", map);
	}
	
	@Override
	public List<OrderPO> listNewPendingAcceptOrders(long storeId, short status, List<Short> payType, Date newOrderBeginTime, int pageSize) {
		Map<String, Object> map = new HashMap<>(6);
		map.put("storeId", storeId);
		map.put("status", status);
		map.put("payType", payType);
		map.put("newOrderBeginTime", newOrderBeginTime);
		map.put("pageSize", pageSize);
		return super.list("com.mazing.services.order.base.dao.OrderDao.listNewPendingAcceptOrders", map);
	}
	
	@Override
	public List<OrderPO> listOnlyStoreDelivery(Long deliverId, List<Short> payType, long storeId, Short pickType, long status, long sequence, int pageSize) {
		Map<String, Object> map = new HashMap<>(8);
		map.put("storeId", storeId);
		map.put("payType", payType);
		if (pickType != null)
			map.put("pickType", pickType);
		map.put("status", status);
		map.put("pageSize", pageSize);
		map.put("sequence", sequence);
		if(deliverId !=null)
			map.put("deliverId", deliverId);
		
		return super.list("com.mazing.services.order.base.dao.OrderDao.listOnlyStoreDelivery", map);
	}
	
	@Override
	public List<OrderPO> listOnlyStoreConfirm(Long deliverId, long storeId, List<Short> payType, Short pickType, long status, long sequence, int pageSize) {
		Map<String, Object> map = new HashMap<>(8);
		map.put("storeId", storeId);
		map.put("payType", payType);
		if (pickType != null)
			map.put("pickType", pickType);
		map.put("status", status);
		map.put("sequence", sequence);
		map.put("pageSize", pageSize);
		if(deliverId !=null)
			map.put("deliverId", deliverId);
		
		return super.list("com.mazing.services.order.base.dao.OrderDao.listOnlyStoreConfirm", map);
	}
	
	@Override
	public int statUserOrdersCountByStatus(Short statusEGT, Short statusELT, long uid) {
		Map<String, Object> map = new HashMap<>(4);
		map.put("statusEGT", statusEGT);
		map.put("statusELT", statusELT);
		map.put("uid", uid);
		return super.get("com.mazing.services.order.base.dao.OrderDao.statUserOrdersCountByStatus", map);
	}
	
	@Override
	public int statCountByStatusAndPayTypeBeforeTime(short status, short payType, Date time) {
		Map<String, Object> map = new HashMap<>(4);
		map.put("status", status);
		map.put("payType", payType);
		map.put("time", time);
		return super.get("com.mazing.services.order.base.dao.OrderDao.statCountByStatusAndPayModeBeforeTime", map);
	}
	
	@Override
	public int statStoreFinishOrder(long storeId, List<Short> status, List<Short> payType) {
		Map<String, Object> params = new HashMap<>(4);
		params.put("storeId", storeId);
		params.put("status", status);
		params.put("payType", payType);
		return super.get("com.mazing.services.order.base.dao.OrderDao.statStoreFinishOrder", params);
	}
	
	@Override
	public int statUserOrder(long uid, List<Short> status, List<Short> payType) {
		Map<String, Object> params = new HashMap<>(4);
		params.put("uid", uid);
		params.put("status", status);
		params.put("payType", payType);
		return super.get("com.mazing.services.order.base.dao.OrderDao.statUserOrder", params);
	}
	
	@Override
	public int statMaxSerial(long storeId, int serviceDay) {
		Map<String, Object> map = new HashMap<>(2);
		map.put("storeId", storeId);
		map.put("serviceDay", serviceDay);
		return super.get("com.mazing.services.order.base.dao.OrderDao.statMaxSerial", map);
	}

	
	//-------------------------------hins的代码写在上面，felix的代码写在下面-------------------
	
	@Override
	public int statOrderPayType(Date start, Date end, List<Short> payType, List<Short> status) {
		Map<String, Object> map = new HashMap<String, Object>(4);
		map.put("start", start);
		map.put("end", end);
		map.put("payType", payType);
		map.put("status", status);
		return super.get("com.mazing.services.order.base.dao.OrderDao.statOrderPayType", map);
	}

	@Override
	public long statOrderFeeSum(Date start, Date end, List<Short> payType, List<Short> status) {
		Map<String, Object> map = new HashMap<String, Object>(4);
		map.put("start", start);
		map.put("end", end);
		map.put("payType", payType);
		map.put("status", status);
		return super.get("com.mazing.services.order.base.dao.OrderDao.statOrderFeeSum", map);
	}

	@Override
	public int statOrderWithoutPayType(Date start, Date end) {
		Map<String, Object> map = new HashMap<String, Object>(4);
		map.put("start", start);
		map.put("end", end);
		map.put("payType", PayType.NONE.getValue());
		return super.get("com.mazing.services.order.base.dao.OrderDao.statOrderWithoutPayType", map);
	}


	@Override
	public long statDealingOrderAmount(long storeId, short payType, Date start, Date end, List<Short> status) {
		Map<String, Object> map = new HashMap<String, Object>(8);
		map.put("storeId", storeId);
		map.put("start", start);
		map.put("end", end);
		map.put("payType", payType);
		map.put("status", status);
		
		return super.get("com.mazing.services.order.base.dao.OrderDao.statDealingOrderAmount", map);
	}


	@Override
	public OrderPO getIfHasOrderInStatus(long storeId, List<Short> status) {
		Map<String, Object> map = new HashMap<>(2);
		map.put("storeId", storeId);
		map.put("status", status);
		return super.get("com.mazing.services.order.base.dao.OrderDao.getIfHasOrderInStatus", map);
	}

	@Override
	public List<OrderPO> listAllOrders(long uid, long storeId, List<Short> payType, int offset, int pageSize) {
		Map<String, Object> map = new HashMap<>(6);
		map.put("uid", uid);
		map.put("storeId", storeId);
		map.put("payType", payType);
		map.put("offset", offset);
		map.put("pageSize", pageSize);
		return super.list("com.mazing.services.order.base.dao.OrderDao.listAllOrders", map);
	}

	@Override
	public Map<String, Object> statStoreOrderByCondition(long storeId, Date start, Date end, List<Short> status, short payType) {
		Map<String, Object> map = new HashMap<>(8);
		map.put("storeId", storeId);
		map.put("start", start);
		map.put("end", end);
		map.put("payType", payType);
		map.put("status", status);

		return super.get("com.mazing.services.order.base.dao.OrderDao.statStoreOrderByCondition", map);
	}

	@Override
	public List<OrderPO> listUserOngoingByImId(long imId, List<Short> status, long storeId, Long orderNo) {
		Map<String, Object> map = new HashMap<>(4);
		map.put("orderNo", orderNo);
		map.put("imId", imId);
		map.put("status", status);
		map.put("storeId", storeId);
		return super.list("com.mazing.services.order.base.dao.OrderDao.listUserOngoingByImId", map);
	}

	@Override
	public long sumDealingActualFee(long storeId, List<Short> status, Date start, Date end, short payType) {
		Map<String, Object> map = new HashMap<>(8);
		map.put("status", status);
		map.put("storeId", storeId);
		map.put("start", start);
		map.put("end", end);
		map.put("payType", payType);
		return super.get("com.mazing.services.order.base.dao.OrderDao.sumDealingActualFee", map);
	}

	@Override
	public List<Map<String, Object>> statStoreSaleRank(Date startTime, short payType) {
		// 2016/2/29 by ten
		Map<String, Object> map = new HashMap<>(4);
		map.put("startTime", startTime);
		map.put("payType", payType);
		return super.list("com.mazing.services.order.base.dao.OrderDao.statStoreSaleRank", map);
	}

	@Override
	public List<Map<String, Object>> statDishSaleRank(Date startTime, short payType) {
		// 2016/2/29 by ten
		Map<String, Object> map = new HashMap<>(4);
		map.put("startTime", startTime);
		map.put("payType", payType);
		return super.list("com.mazing.services.order.base.dao.OrderDao.statDishSaleRank", map);
	}

	@Override
	public List<Map<String, Object>> statOrderByMonth(List<Short> payType, int totalFee, String time1, String time2) {
		Map<String, Object> map = new HashMap<String, Object>(6);
		map.put("payType", payType);
		map.put("totalFeeGT", totalFee);
		map.put("time1", time1);
		map.put("time2", time2);
		return super.list("com.mazing.services.order.base.dao.OrderDao.statOrderByMonth", map);
	}
	
	@Override
	public List<Map<String, Object>> statTotalCoupon(List<Short> payType, String endTime) {
		Map<String, Object> map = new HashMap<String, Object>(4);
		map.put("payType", payType);
		map.put("endTime", endTime);
		return super.list("com.mazing.services.order.base.dao.OrderDao.statTotalCoupon", map);
	}
	
	@Override
	public List<Map<String, Object>> statCouponByMonth(List<Short> payType, String time1, String time2) {
		Map<String, Object> map = new HashMap<String, Object>(4);
		map.put("payType", payType);
		map.put("time1", time1);
		map.put("time2", time2);
		return super.list("com.mazing.services.order.base.dao.OrderDao.statCouponByMonth", map);
	}

	@Override
	public List<OrderByDateVO> statOrderByWeek(List<Short> payType, int totalFee, String time1, String time2) {
		Map<String, Object> map = new HashMap<String, Object>(4);
		map.put("payType", payType);
		map.put("time1", time1);
		map.put("time2", time2);
		map.put("totalFeeGT", totalFee);
		return super.list("com.mazing.services.order.base.dao.OrderDao.statOrderByWeek", map);
	}

	@Override
	public List<OrderForExcelVO> listOrderByStoreIdAndTime(long storeId, Date startTime, Date endTime, short payType) {
		Map<String, Object> map = new HashMap<>(4);
		map.put("storeId", storeId);
		map.put("startTime", startTime);
		map.put("endTime", endTime);
		map.put("payType", payType);
		return super.list("com.mazing.services.order.base.dao.OrderDao.listOrderByStoreIdAndTime", map);
	}

	@Override
	public int statUserOngoingOrderCount(long uid, List<Short> payType, List<Short> status) {
		Map<String, Object> map = new HashMap<>(4);
		map.put("uid", uid);
		map.put("payType", payType);
		map.put("status", status);
		return super.get("com.mazing.services.order.base.dao.OrderDao.statUserOngoingOrderCount", map);
	}

	@Override
	public int countStoreOrders(long storeId, short status) {
		Map<String, Object> map = new HashMap<>(4);
		map.put("status", status);
		map.put("storeId", storeId);
		return super.get("com.mazing.services.order.base.dao.OrderDao.countStoreOrders", map);
	}

	@Override
	public int countStoreOrdersByDeliveryTime(long storeId, short[] status, Date startTime, Date endTime) {
		Map<String, Object> map = new HashMap<>(8);
		map.put("status", status);
		map.put("storeId", storeId);
		map.put("startTime", startTime);
		map.put("endTime", endTime);
		return super.get("com.mazing.services.order.base.dao.OrderDao.countStoreOrdersByDeliveryTime", map);
	}

	@Override
	public List<OrderPO> listByOrderIds(List<Long> orderIds) {
		if(orderIds == null || orderIds.isEmpty()){
			return Collections.emptyList();
		}
		Map<String, Object> map = new HashMap<>(2);
		map.put("orderIds", orderIds);
		return super.list("com.mazing.services.order.base.dao.OrderDao.listByOrderIds", map);
	}

	@Override
	public long stateActualFeeByOrderIds(List<Long> orderIds, short payType) {
		Map<String, Object> map = new HashMap<>(4);
		map.put("orderIds", orderIds);
		map.put("payType", payType);
		return super.get("com.mazing.services.order.base.dao.OrderDao.stateActualFeeByOrderIds", map);
	}

	@Override
	public long stateCouponFeeByOrderIds(List<Long> orderIds, short payType, short couponBearType) {
		Map<String, Object> map = new HashMap<>(4);
		map.put("orderIds", orderIds);
		map.put("payType", payType);
		map.put("couponBearType", couponBearType);
		return super.get("com.mazing.services.order.base.dao.OrderDao.stateCouponFeeByOrderIds", map);
	}
	
	@Override
	public List<Map<String, Object>> statSettlingTotalFee(long[] storeId, List<Short> status, Date start, Date end, Short payType) {
		Map<String, Object> map = new HashMap<String, Object>(8);
		map.put("storeId", storeId);
		map.put("start", start);
		map.put("end", end);
		map.put("payType", payType);
		map.put("status", status);
		
		return super.list("com.mazing.services.order.base.dao.OrderDao.statSettlingTotalFee", map);
	}
	
	@Override
	public List<Map<String, Object>> statSettlingActualFee(long[] storeId, List<Short> status, Date start, Date end, Short payType) {
		Map<String, Object> map = new HashMap<>(8);
		map.put("status", status);
		map.put("storeId", storeId);
		map.put("start", start);
		map.put("end", end);
		map.put("payType", payType);
		return super.list("com.mazing.services.order.base.dao.OrderDao.statSettlingActualFee", map);
	}
	
	@Override
	public List<Map<String, Object>> statDealingOrderTotalFee(long[] storeId, short payType, Date start, Date end, List<Short> status) {
		Map<String, Object> map = new HashMap<String, Object>(8);
		map.put("storeId", storeId);
		map.put("start", start);
		map.put("end", end);
		map.put("payType", payType);
		map.put("status", status);
		
		return super.list("com.mazing.services.order.base.dao.OrderDao.statDealingOrderTotalFee", map);
	}
	
	@Override
	public List<Map<String, Object>> statDealingActualFee(long[] storeId, List<Short> status, Date start, Date end, short payType) {
		Map<String, Object> map = new HashMap<>(8);
		map.put("status", status);
		map.put("storeId", storeId);
		map.put("start", start);
		map.put("end", end);
		map.put("payType", payType);
		return super.list("com.mazing.services.order.base.dao.OrderDao.statDealingActualFee", map);
	}
	
	@Override
	public List<Map<String, Object>> statCouponFeeByInStoreOrderBegin(List<Long> storeId, List<Short> status, Date start, Date end, Short payType, short couponBearType) {
		Map<String, Object> map = new HashMap<>(8);
		map.put("storeId", storeId);
		map.put("status", status);
		map.put("start", start);
		map.put("end", end);
		map.put("payType", payType);
		map.put("couponBearType", couponBearType);
		return super.list("com.mazing.services.order.base.dao.OrderDao.statCouponFeeByInStoreOrderBegin", map);
	}
	
	@Override
	public List<Map<String, Object>> statCouponFeeByStoreFinish(List<Long> storeId, List<Short> status, Date start, Date end, Short payType, short couponBearType) {
		Map<String, Object> map = new HashMap<>(8);
		map.put("storeId", storeId);
		map.put("status", status);
		map.put("start", start);
		map.put("end", end);
		map.put("payType", payType);
		map.put("couponBearType", couponBearType);
		return super.list("com.mazing.services.order.base.dao.OrderDao.statCouponFeeByStoreFinish", map);
	}
	
	@Override
	public long sumDealingDiscountFee(long storeId, List<Short> status, Date start, Date end, short payType, short couponBearType) {
		Map<String, Object> map = new HashMap<>(8);
		map.put("status", status);
		map.put("storeId", storeId);
		map.put("start", start);
		map.put("end", end);
		map.put("payType", payType);
		map.put("couponBearType", couponBearType);
		return super.get("com.mazing.services.order.base.dao.OrderDao.sumDealingDiscountFee", map);
	}

	@Override
	public List<OrderPO> listByStoreBeginTimeAndStatus(long storeId, Date begin, Date end, List<Short> status, List<Short> payType){
		Map<String, Object> map = new HashMap<>(6);
		map.put("storeId", storeId);
		map.put("begin", begin);
		map.put("end", end);
		map.put("status", status);
		map.put("payType", payType);
		return super.list("com.mazing.services.order.base.dao.OrderDao.listByStoreBeginTimeAndStatus", map);
	}

	@Override
	public long statCouponFeeByStoreOrderBegin(long storeId, List<Short> status, Date start, Date end, Short payType, short couponBearType) {
		Map<String, Object> map = new HashMap<>(8);
		map.put("storeId", storeId);
		map.put("status", status);
		map.put("start", start);
		map.put("end", end);
		map.put("payType", payType);
		map.put("couponBearType", couponBearType);
		return super.get("com.mazing.services.order.base.dao.OrderDao.statCouponFeeByStoreOrderBegin", map);
	}

}
