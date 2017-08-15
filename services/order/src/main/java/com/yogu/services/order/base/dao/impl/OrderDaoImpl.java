package com.yogu.services.order.base.dao.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Named;

import com.yogu.commons.datasource.mybatis.MyBatisDao;
import com.yogu.commons.utils.DateUtils;
import com.yogu.commons.utils.StringUtils;
import com.yogu.core.enums.order.OrderStatus;
import com.yogu.services.order.base.dao.OrderDao;
import com.yogu.services.order.base.dao.param.ChangePayTypePOJO;
import com.yogu.services.order.base.dao.param.UpdateOrderPayPOJO;
import com.yogu.services.order.base.dao.param.UpdateOrderToUserConfirmByMazingPayPOJO;
import com.yogu.services.order.base.entry.OrderPO;
import com.yogu.services.order.base.entry.TinyOrderPO;
import com.yogu.services.order.constants.OrderSearchCategory;

@Named
public class OrderDaoImpl extends MyBatisDao implements OrderDao {

	@Override
	public void save(OrderPO po) {
		super.insert("com.mazing.services.order.base.dao.OrderDao.save", po);
	}

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
	public List<OrderPO> listUserHistory(long uid, List<Short> status, int offset, int pageSize) {
		Map<String, Object> map = new HashMap<>(4);
		map.put("uid", uid);
		map.put("status", status);
		map.put("offset", offset);
		map.put("pageSize", pageSize);
		return super.list("com.mazing.services.order.base.dao.OrderDao.listUserHistory", map);
	}

	@Override
	public List<OrderPO> listUserOngoing(long uid, List<Short> status, Long storeId) {
		Map<String, Object> map = new HashMap<>(4);
		map.put("uid", uid);
		map.put("status", status);
		map.put("storeId", storeId);
		return super.list("com.mazing.services.order.base.dao.OrderDao.listUserOngoing", map);
	}

	@Override
	public int statUserOrder(long uid, List<Short> status) {
		Map<String, Object> params = new HashMap<>(2);
		params.put("uid", uid);
		params.put("status", status);
		return super.get("com.mazing.services.order.base.dao.OrderDao.statUserOrder", params);
	}

	@Override
	public int updateCommentOrder(long orderId, long commentId, short thisStatus, short status) {
		Map<String, Object> map = new HashMap<>(7);
		map.put("orderId", orderId);
		map.put("commentId", commentId);
		map.put("thisStatus", thisStatus);
		map.put("status", status);
		map.put("updateTime", DateUtils.getUniformCurrentTimeForThread());
		return super.update("com.mazing.services.order.base.dao.OrderDao.updateByParams", map);
	}

	@Override
	public int updateByParams(long orderId, short thisStatus, short status) {
		Map<String, Object> map = new HashMap<>(6);
		map.put("orderId", orderId);
		map.put("thisStatus", thisStatus);
		map.put("status", status);
		map.put("updateTime", DateUtils.getUniformCurrentTimeForThread());
		return super.update("com.mazing.services.order.base.dao.OrderDao.updateByParams", map);
	}

	@Override
	public int updateStatusAndDeliverId(long orderId, short thisStatus, short status, long deliverId, short isStoreExpress) {
		Map<String, Object> map = new HashMap<>(8);
		map.put("orderId", orderId);
		map.put("thisStatus", thisStatus);
		map.put("status", status);
		map.put("updateTime", DateUtils.getUniformCurrentTimeForThread());
		map.put("deliverId", deliverId);
		map.put("isStoreExpress", isStoreExpress);
		return super.update("com.mazing.services.order.base.dao.OrderDao.updateStatusAndDeliverId", map);
	}
	
	@Override
	public int updateStatus(long orderId, short thisStatus, short status) {
		Map<String, Object> map = new HashMap<>(6);
		map.put("orderId", orderId);
		map.put("thisStatus", thisStatus);
		map.put("status", status);
		map.put("updateTime", DateUtils.getUniformCurrentTimeForThread());

		return super.update("com.mazing.services.order.base.dao.OrderDao.updateByParams", map);
	}


	@Override
	public int payOrder(ChangePayTypePOJO po) {
		Map<String, Object> map = new HashMap<>(8);
		map.put("orderId", po.getOrderId());
		map.put("payNo", po.getPayNo());
		map.put("oldStatus", po.getOldStatus());
		map.put("newStatus", po.getNewStatus());
		map.put("deliveryTime", po.getDeliveryTime());
		map.put("serialNumber", po.getSerialNumber());
		map.put("sequence", po.getSequence());
		map.put("orderBeginTime", po.getOrderBeginTime());
		map.put("updateTime", po.getOrderBeginTime());
		return super.update("com.mazing.services.order.base.dao.OrderDao.updatePayOrder", map);
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
	public List<OrderPO> listOrderByStoreId(long storeId, Short status, int offset, int pageSize) {
		Map<String, Object> map = new HashMap<>(4);
		map.put("storeId", storeId);
		map.put("status", status);
		map.put("offset", offset);
		map.put("pageSize", pageSize);
		return super.list("com.mazing.services.order.base.dao.OrderDao.listOrderByStoreId", map);
	}

	@Override
	public List<OrderPO> listOrdersByStoreIdAndPickTypeWithStatus(long storeId, Short pickType, Short status, int offset, int pageSize,
			String orderBy) {

		Map<String, Object> map = new HashMap<>(6);
		map.put("storeId", storeId);
		map.put("pickType", pickType);
		map.put("status", status);
		map.put("offset", offset);
		map.put("pageSize", pageSize);
		map.put("orderBy", orderBy);

		return super.list("com.mazing.services.order.base.dao.OrderDao.listOrdersByStoreIdAndPickTypeWithStatus", map);
	}

	@Override
	public int addPrint(long orderId) {
		return super.update("com.mazing.services.order.base.dao.OrderDao.addPrint", orderId);
	}

	@Override
	public int statMaxSerial(long storeId, int serviceDay) {
		Map<String, Object> map = new HashMap<>(2);
		map.put("storeId", storeId);
		map.put("serviceDay", serviceDay);
		return super.get("com.mazing.services.order.base.dao.OrderDao.statMaxSerial", map);
	}

	@Override
	public int updateStoreConfirmTime(long orderId, Date time, Date finishTime) {
		Map<String, Object> map = new HashMap<>(3);
		map.put("orderId", orderId);
		map.put("time", time);
		if (finishTime != null)
			map.put("finishTime", finishTime);
		return super.update("com.mazing.services.order.base.dao.OrderDao.updateStoreConfirmTime", map);
	}

	@Override
	public int updateRejectStatus(long orderId, short thisStatus, short status, String rejectComment) {
		Map<String, Object> map = new HashMap<>(5);
		map.put("orderId", orderId);
		map.put("thisStatus", thisStatus);
		map.put("status", status);
		map.put("rejectComment", rejectComment);
		map.put("updateTime", DateUtils.getUniformCurrentTimeForThread());
		return super.update("com.mazing.services.order.base.dao.OrderDao.updateRejectStatus", map);
	}

	@Override
	public int statStoreFinishOrder(long storeId, List<Short> status) {
		Map<String, Object> params = new HashMap<>(2);
		params.put("storeId", storeId);
		params.put("status", status);
		return super.get("com.mazing.services.order.base.dao.OrderDao.statStoreFinishOrder", params);
	}

	@Override
	public List<OrderPO> listStoreFinish(long storeId, List<Short> status, List<Short> payType, int offset, int pageSize) {
		Map<String, Object> map = new HashMap<>(6);
		map.put("storeId", storeId);
		map.put("status", status);
		map.put("payType", payType);
		map.put("offset", offset);
		map.put("pageSize", pageSize);
		return super.list("com.mazing.services.order.base.dao.OrderDao.listStoreFinish", map);
	}

	@Override
	public List<OrderPO> listStoreFinishByPayType(long storeId, List<Short> status, short payType, int offset, int pageSize) {
		Map<String, Object> map = new HashMap<>(5);
		map.put("storeId", storeId);
		map.put("status", status);
		map.put("payType", payType);
		map.put("offset", offset);
		map.put("pageSize", pageSize);
		return super.list("com.mazing.services.order.base.dao.OrderDao.listStoreFinishByPayType", map);
	}
	
	@Override
	public List<OrderPO> listSuccessOrderedUids(int offset, int pageSize) {
		Map<String, Object> map = new HashMap<>(3);
		map.put("offset", offset);
		map.put("pageSize", pageSize);
		return super.list("com.mazing.services.order.base.dao.OrderDao.listSuccessOrderedUids", map);
	}

	@Override
	public List<OrderPO> listByField(long storeId, String content, OrderSearchCategory category) {
		Map<String, Object> map = new HashMap<>(8);
		map.put("storeId", storeId);
		map.put("content", content);
		map.put("category", category.toString());
		map.put("status", OrderStatus.NON_PAYMENT.getValue());
		return super.list("com.mazing.services.order.base.dao.OrderDao.listByField", map);
	}

	@Override
	public int updatePayTypeByCash(ChangePayTypePOJO po) {
		Map<String, Object> map = new HashMap<>(14);
		map.put("orderId", po.getOrderId());
		map.put("payType", po.getPayType());
		map.put("payMode", po.getPayMode());
		map.put("oldStatus", po.getOldStatus());
		map.put("newStatus", po.getNewStatus());
		map.put("deliveryTime", po.getDeliveryTime());
		map.put("serialNumber", po.getSerialNumber());
		map.put("sequence", po.getSequence());
		map.put("orderBeginTime", po.getOrderBeginTime());
		map.put("updateTime", po.getOrderBeginTime());
		
		map.put("discountFee", po.getDiscountFee());
		map.put("useCoupon", po.getUseCoupon());
		map.put("actualFee", po.getActualFee());
		
		return super.update("com.mazing.services.order.base.dao.OrderDao.updatePayTypeByCash", map);
	}

	@Override
	public int updatePayNo(UpdateOrderPayPOJO pojo) {
		Map<String, Object> map = new HashMap<>(10);
		map.put("orderId", pojo.getOrderId());
		map.put("payType", pojo.getPayType());
		map.put("payMode", pojo.getPayMode());
		map.put("oldStatus", pojo.getOldStatus());
		map.put("payNo", pojo.getPayNo());
		map.put("discountFee", pojo.getDiscountFee());
		map.put("useCoupon", pojo.getUseCoupon());
		map.put("updateTime", pojo.getUpdateTime());
		map.put("actualFee", pojo.getActualFee());
		return super.update("com.mazing.services.order.base.dao.OrderDao.updatePayNo", map);
	}
	
	public int updatePayNoAndSuccess(UpdateOrderPayPOJO pojo) {
		Map<String, Object> map = new HashMap<>(14);
		map.put("orderId", pojo.getOrderId());
		map.put("payType", pojo.getPayType());
		map.put("payMode", pojo.getPayMode());
		map.put("oldStatus", pojo.getOldStatus());
		map.put("newStatus", pojo.getNewStatus());
		map.put("payNo", pojo.getPayNo());
		map.put("discountFee", pojo.getDiscountFee());
		map.put("useCoupon", pojo.getUseCoupon());
		map.put("updateTime", pojo.getUpdateTime());
		map.put("orderBeginTime", pojo.getOrderBeginTime());
		map.put("deliveryTime", pojo.getDeliveryTime());
		map.put("serialNumber", pojo.getSerialNumber());
		map.put("sequence", pojo.getSequence());
		map.put("actualFee", pojo.getActualFee());
		return super.update("com.mazing.services.order.base.dao.OrderDao.updatePayNoAndSuccess", map);
	}
	@Override
	public List<OrderPO> listByStatusAndPayTypeBeforeTime(short status, int offset, int pageSize, Date startTime, Date endTime) {
		Map<String, Object> map = new HashMap<>(8);
		map.put("status", status);
		map.put("offset", offset);
		map.put("pageSize", pageSize);
		map.put("startTime", startTime);
		map.put("endTime", endTime);
		return super.list("com.mazing.services.order.base.dao.OrderDao.listByStatusAndPayModeBeforeTime", map);
	}

	@Override
	public int statCountByStatusAndPayTypeBeforeTime(short status, Date startTime, Date endTime) {
		Map<String, Object> map = new HashMap<>(6);
		map.put("status", status);
		map.put("startTime", startTime);
		map.put("endTime", endTime);
		return super.get("com.mazing.services.order.base.dao.OrderDao.statCountByStatusAndPayModeBeforeTime", map);
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
	public int updateStoreReturn(long orderId, short prevStatus, short curStatus, Date backTime, Date storeConfirmTime) {
		Map<String, Object> map = new HashMap<String, Object>(6);
		map.put("orderId", orderId);
		map.put("prevStatus", prevStatus);
		map.put("curStatus", curStatus);
		map.put("backTime", backTime);
		map.put("storeConfirmTime", storeConfirmTime);
		return super.update("com.mazing.services.order.base.dao.OrderDao.updateStoreReturn", map);
	}

	@Override
	public int updateUserConfirm(long orderId, short thisStatus, short status, Date time, Date finishTime) {
		Map<String, Object> map = new HashMap<String, Object>(5);
		map.put("orderId", orderId);
		map.put("thisStatus", thisStatus);
		map.put("status", status);
		map.put("time", time);
		if (finishTime != null)
			map.put("finishTime", finishTime);
		return super.update("com.mazing.services.order.base.dao.OrderDao.updateUserConfirm", map);

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
	public List<OrderPO> listOrdersByStoreIdAndPickTypeWithStatus2(long storeId, Short pickType, Short status, long sequence, int pageSize,
			String orderBy) {
		Map<String, Object> map = new HashMap<>(6);
		map.put("storeId", storeId);
		map.put("pickType", pickType);
		map.put("status", status);
		map.put("sequence", sequence);
		map.put("pageSize", pageSize);
		map.put("orderBy", orderBy);

		return super.list("com.mazing.services.order.base.dao.OrderDao.listOrdersByStoreIdAndPickTypeWithStatus2", map);
	}

	@Override
	public List<OrderPO> listOnlyStoreDelivery(Long deliverId, long storeId, Short pickType, long status, long sequence, int pageSize) {
		Map<String, Object> map = new HashMap<>(6);
		map.put("storeId", storeId);
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
	public List<OrderPO> listPendingAcceptOrders2(long storeId, short status, Date orderBeginTime, int pageSize) {
		Map<String, Object> map = new HashMap<>(4);
		map.put("storeId", storeId);
		map.put("status", status);
		map.put("orderBeginTime", orderBeginTime);
		map.put("pageSize", pageSize);
		return super.list("com.mazing.services.order.base.dao.OrderDao.listPendingAcceptOrders2", map);
	}

	@Override
	public List<OrderPO> listStoreRefundOrders(long storeId, List<Short> refundTypes,
			List<Short> status, Date refundCreateTime, int pageSize) {
		Map<String, Object> map = new HashMap<>(6);
		map.put("storeId", storeId);
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
	public List<OrderPO> listStatusAndTimeOut(Date start, Date end, short status, List<Short> list) {
		Map<String, Object> map = new HashMap<>(3);
		map.put("start", start);
		map.put("end", end);
		map.put("status", status);
		map.put("list", list);
		return super.list("com.mazing.services.order.base.dao.OrderDao.listStatusAndTimeOut", map);
	}

	@Override
	public List<OrderPO> listByStatusAndBeginTimeOut(Date start, Date end, short status) {
		Map<String, Object> map = new HashMap<>(3);
		map.put("start", start);
		map.put("end", end);
		map.put("status", status);
		return super.list("com.mazing.services.order.base.dao.OrderDao.listByStatusAndBeginTimeOut", map);
	}
	
	@Override
	public int updateRefundSunccess(long orderId, short thisStatus, short status) {
		Map<String, Object> map = new HashMap<>(5);
		map.put("orderId", orderId);
		map.put("thisStatus", thisStatus);
		map.put("status", status);
		Date now = new Date();
		map.put("updateTime", now);
		map.put("finishTime", now);
		return super.update("com.mazing.services.order.base.dao.OrderDao.updateRefundSunccess", map);
	}

	@Override
	public OrderPO getIfHasOrderInStatus(long storeId, List<Short> status) {
		Map<String, Object> map = new HashMap<>(2);
		map.put("storeId", storeId);
		map.put("status", status);
		return super.get("com.mazing.services.order.base.dao.OrderDao.getIfHasOrderInStatus", map);
	}

	@Override
	public int updateAutoConfirm(long orderId, short thisStatus, short status, Date time, short sysConfirm) {
		Map<String, Object> map = new HashMap<>(5);
		map.put("orderId", orderId);
		map.put("thisStatus", thisStatus);
		map.put("status", status);
		map.put("time", time);
		map.put("sysConfirm", sysConfirm);
		return super.update("com.mazing.services.order.base.dao.OrderDao.updateAutoConfirm", map);
	}

	@Override
	public List<OrderPO> listIsBackPendingAcceptOrders(long storeId, short status) {
		Map<String, Object> map = new HashMap<>(2);
		map.put("storeId", storeId);
		map.put("status", status);
		return super.list("com.mazing.services.order.base.dao.OrderDao.listIsBackPendingAcceptOrders", map);
	}
	
	@Override
	public List<OrderPO> listByBeginTimeAndStatus(List<Long> storeIds, Date begin, Date end, List<Short> status, List<Short> payType) {
		Map<String, Object> map = new HashMap<>(6);
		map.put("storeIds", storeIds);
		map.put("begin", begin);
		map.put("end", end);
		map.put("status", status);
		map.put("payType", payType);
		return super.list("com.mazing.services.order.base.dao.OrderDao.listByBeginTimeAndStatus", map);
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
	public List<OrderPO> listNewPendingAcceptOrders(long storeId, short status, Date newOrderBeginTime, int pageSize) {
		Map<String, Object> map = new HashMap<>(4);
		map.put("storeId", storeId);
		map.put("status", status);
		map.put("newOrderBeginTime", newOrderBeginTime);
		map.put("pageSize", pageSize);
		return super.list("com.mazing.services.order.base.dao.OrderDao.listNewPendingAcceptOrders", map);
	}

	@Override
	public int updateToCancel(long orderId, short thisStatus, short status, short cancelSource, String rejectRemark) {
		Map<String, Object> map = new HashMap<>(6);
		map.put("orderId", orderId);
		map.put("thisStatus", thisStatus);
		map.put("status", status);
		if (StringUtils.isNotBlank(rejectRemark))
			map.put("rejectRemark", rejectRemark);
		map.put("cancelSource", cancelSource);
		Date now = DateUtils.getUniformCurrentTimeForThread();
		map.put("updateTime", now);
		map.put("finishTime", now);
		return super.update("com.mazing.services.order.base.dao.OrderDao.updateToCancel", map);
	}

	@Override
	public List<OrderPO> listOnlyStoreConfirm(Long deliverId, long storeId, Short pickType, long status, long sequence, int pageSize) {
		Map<String, Object> map = new HashMap<>(6);
		map.put("storeId", storeId);
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
	public List<OrderPO> listUserOngoingByImId(long imId, List<Short> status, long storeId, Long orderNo) {
		Map<String, Object> map = new HashMap<>(4);
		map.put("orderNo", orderNo);
		map.put("imId", imId);
		map.put("status", status);
		map.put("storeId", storeId);
		return super.list("com.mazing.services.order.base.dao.OrderDao.listUserOngoingByImId", map);
	}

	@Override
	@Deprecated
	public List<OrderPO> listByPage(int offset, int pageSize) {
		Map<String, Object> map = new HashMap<>(2);
		map.put("offset", offset);
		map.put("pageSize", pageSize);
		return super.list("com.mazing.services.order.base.dao.OrderDao.listByPage", map);
	}
	
	@Override
	public List<OrderPO> listByServiceDay(int serviceDay) {
		Map<String, Object> map = new HashMap<>(2);
		map.put("serviceDay", serviceDay);
		return super.list("com.mazing.services.order.base.dao.OrderDao.listByServiceDay", map);
	}

	@Override
	@Deprecated
	public void updateImid(long orderId, long imId) {
		Map<String, Object> map = new HashMap<>(2);
		map.put("orderId", orderId);
		map.put("imId", imId);
		super.update("com.mazing.services.order.base.dao.OrderDao.updateImid", map);
	}

	@Override
	public int updateToRefund(long orderId, short thisStatus, short newStatus, String rejectRemark) {
		Map<String, Object> map = new HashMap<>(6);
		map.put("orderId", orderId);
		map.put("thisStatus", thisStatus);
		map.put("newStatus", newStatus);
		if (StringUtils.isNotBlank(rejectRemark)) {
			map.put("rejectRemark", rejectRemark);
		}
		map.put("updateTime", DateUtils.getUniformCurrentTimeForThread());
		return super.update("com.mazing.services.order.base.dao.OrderDao.updateToRefund", map);
	}

	@Override
	public List<OrderPO> listOverMidDeliveryTime(List<Short> status, Date time, int ahead) {
		Map<String, Object> map = new HashMap<>(4);
		map.put("status", status);
		map.put("time", time);
		map.put("ahead", ahead);
		return super.list("com.mazing.services.order.base.dao.OrderDao.listOverMidDeliveryTime", map);
	}

	@Override
	public List<OrderPO> listInStatusAndTimeOut(Date start, Date end, List<Short> status) {
		Map<String, Object> map = new HashMap<>(4);
		map.put("start", start);
		map.put("end", end);
		map.put("status", status);
		return super.list("com.mazing.services.order.base.dao.OrderDao.listInStatusAndTimeOut", map);
	}

	@Override
	public List<OrderPO> listShouldHasDeliveredOrders(List<Short> status, Date time) {
		Map<String, Object> map = new HashMap<>(2);
		map.put("status", status);
		map.put("time", time);
		return super.list("com.mazing.services.order.base.dao.OrderDao.listShouldHasDeliveredOrders", map);
	}

	@Override
	public List<Long> listSuccessOrderIds(long uid) {
		
		Map<String, Object> map = new HashMap<>(2);
		map.put("uid", uid);
		return super.list("com.mazing.services.order.base.dao.OrderDao.listSuccessOrderIds", map);
	}

	@Override
	public int updateUserConfirmByMazingPay(UpdateOrderToUserConfirmByMazingPayPOJO pojo) {
		Map<String, Object> map = new HashMap<>(8);
		map.put("orderId", pojo.getOrderId());
		map.put("oldStatus", pojo.getOldStatus());
		map.put("newStatus", pojo.getNewStatus());
		map.put("serialNumber", pojo.getSerialNumber());
		map.put("sequence", pojo.getSequence());
		map.put("orderBeginTime", pojo.getOrderBeginTime());
		map.put("updateTime", pojo.getUpdateTime());
		map.put("userConfirmTime", pojo.getUserConfirmTime());
		map.put("finishTime", pojo.getFinishTime());
		return super.update("com.mazing.services.order.base.dao.OrderDao.updateUserConfirmByMazingPay", map);
	}

	@Override
	public List<OrderPO> listByStatusAndFinishTime(List<Short> status, List<Short> payType, Date finishTimeBegin, Date finishTimeEnd) {
		Map<String, Object> map = new HashMap<>(4);
		map.put("status", status);
		map.put("payType", payType);
		map.put("finishTimeBegin", finishTimeBegin);
		map.put("finishTimeEnd", finishTimeEnd);
		return super.list("com.mazing.services.order.base.dao.OrderDao.listByStatusAndFinishTime", map);
	}
	@Override
	public int updateStoreRemark(long storeId, long orderId, String newStoreRemark, String oldStoreRemark) {
		Map<String, Object> map = new HashMap<>(7);
		map.put("storeId", storeId);
		map.put("orderId", orderId);
		map.put("newStoreRemark", newStoreRemark);
		map.put("oldStoreRemark", oldStoreRemark);
		map.put("updateTime", DateUtils.getUniformCurrentTimeForThread());
		return super.update("com.mazing.services.order.base.dao.OrderDao.updateStoreRemark", map);
	}

	@Override
	public long statSettlingTotalFee(long storeId, short payType, Date start, Date end, List<Short> status) {
		Map<String, Object> map = new HashMap<String, Object>(8);
		map.put("storeId", storeId);
		map.put("start", start);
		map.put("end", end);
		map.put("payType", payType);
		map.put("status", status);
		
		return super.get("com.mazing.services.order.base.dao.OrderDao.statSettlingTotalFee", map);
	}

	@Override
	public long statSettlingActualFee(long storeId, List<Short> status, Date start, Date end, short payType) {
		Map<String, Object> map = new HashMap<>(8);
		map.put("status", status);
		map.put("storeId", storeId);
		map.put("start", start);
		map.put("end", end);
		map.put("payType", payType);
		return super.get("com.mazing.services.order.base.dao.OrderDao.statSettlingActualFee", map);
	}

	@Override
	public long statSettlingDiscountFee(long storeId, List<Short> status, Date start, Date end, short payType, short couponBearType) {
		Map<String, Object> map = new HashMap<>(8);
		map.put("status", status);
		map.put("storeId", storeId);
		map.put("start", start);
		map.put("end", end);
		map.put("payType", payType);
		map.put("couponBearType", couponBearType);
		return super.get("com.mazing.services.order.base.dao.OrderDao.statSettlingDiscountFee", map);
	}
	
	@Override
	public long statSettlingUserDeliveryFee(long storeId, List<Short> status, Date start, Date end, short payType, List<Short> expressStatus) {
		Map<String, Object> map = new HashMap<>(8);
		map.put("status", status);
		map.put("storeId", storeId);
		map.put("start", start);
		map.put("end", end);
		map.put("payType", payType);
		map.put("expressStatus", expressStatus);
		return super.get("com.mazing.services.order.base.dao.OrderDao.statSettlingUserDeliveryFee", map);
	}
	
	@Override
	public long statSettlingStoreDeliveryFee(long storeId, List<Short> status, Date start, Date end, short payType, List<Short> expressStatus) {
		Map<String, Object> map = new HashMap<>(8);
		map.put("status", status);
		map.put("storeId", storeId);
		map.put("start", start);
		map.put("end", end);
		map.put("payType", payType);
		map.put("expressStatus", expressStatus);
		return super.get("com.mazing.services.order.base.dao.OrderDao.statSettlingStoreDeliveryFee", map);
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
	public List<Map<String, Object>> statCouponFeeInStoreOrderBegin(List<Long> storeIds, List<Short> status, Date start, Date end, short payType, short couponBearType) {
		Map<String, Object> map = new HashMap<>(8);
		map.put("status", status);
		map.put("storeIds", storeIds);
		map.put("start", start);
		map.put("end", end);
		map.put("payType", payType);
		map.put("couponBearType", couponBearType);
		return super.list("com.mazing.services.order.base.dao.OrderDao.statCouponFeeInStoreOrderBegin", map);
	}
	
	@Override
	public long sumDealingUserDeliveryFee(long storeId, List<Short> status, Date start, Date end, short payType, List<Short> expressStatus) {
		Map<String, Object> map = new HashMap<>(8);
		map.put("status", status);
		map.put("storeId", storeId);
		map.put("start", start);
		map.put("end", end);
		map.put("payType", payType);
		map.put("expressStatus", expressStatus);
		return super.get("com.mazing.services.order.base.dao.OrderDao.sumDealingUserDeliveryFee", map);
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
	public List<OrderPO> listDeliveryTimeAndExpress(Date beginTime, Date endTime, short isThirdExpress, List<Short> orderStatus) {
		Map<String, Object> map = new HashMap<>(6);
		map.put("beginTime", beginTime);
		map.put("endTime", endTime);
		map.put("isThirdExpress", isThirdExpress);
		map.put("orderStatus", orderStatus);
		return super.list("com.mazing.services.order.base.dao.OrderDao.listDeliveryTimeAndExpress", map);
	}
	
	@Override
	public int updateToAccept(long orderId, short oldStatus, short newStatus, Date acceptTime) {
		Map<String, Object> map = new HashMap<>(6);
		map.put("orderId", orderId);
		map.put("oldStatus", oldStatus);
		map.put("newStatus", newStatus);
		map.put("updateTime", acceptTime);
		map.put("acceptTime", acceptTime);
		return super.update("com.mazing.services.order.base.dao.OrderDao.updateToAccept", map);
	}

}
