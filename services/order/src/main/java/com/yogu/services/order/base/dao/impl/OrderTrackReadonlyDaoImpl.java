package com.yogu.services.order.base.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Named;

import com.yogu.commons.datasource.mybatis.MyBatisReadonlyDao;
import com.yogu.services.order.base.dao.OrderTrackReadonlyDao;
import com.yogu.services.order.base.entry.OrderPO;
import com.yogu.services.order.base.entry.OrderTrackPO;

@Named
public class OrderTrackReadonlyDaoImpl extends MyBatisReadonlyDao implements OrderTrackReadonlyDao {

	@Override
	public OrderTrackPO getById(long trackId) {
		return super.get("com.mazing.services.order.base.dao.OrderTrackDao.getById", trackId);
	}
	
	@Override
	public OrderTrackPO getTrackByOrderIdAndStatus(long orderId, List<Short> status) {
		Map<String, Object> map = new HashMap<>(2);
		map.put("orderId", orderId);
		map.put("status", status);
		return super.get("com.mazing.services.order.base.dao.OrderTrackDao.getTrackByOrderIdAndStatus", map);
	}

	@Override
	public OrderTrackPO getLastTrack(long orderId) {
		return super.get("com.mazing.services.order.base.dao.OrderTrackDao.getLastTrack", orderId);
	}

	@Override
	public List<OrderTrackPO> listInByOrderId(long orderId, List<Short> status) {
		Map<String, Object> map = new HashMap<>(2);
		map.put("orderId", orderId);
		map.put("status", status);
		return super.list("com.mazing.services.order.base.dao.OrderTrackDao.listInByOrderId", map);
	}

	@Override
	public List<OrderTrackPO> listByOrderIdAndPastStatus(long orderId, List<Short> status) {
		Map<String, Object> map = new HashMap<>(2);
		map.put("orderId", orderId);
		map.put("status", status);
		return super.list("com.mazing.services.order.base.dao.OrderTrackDao.listByOrderIdAndPastStatus", map);
	}
	@Override
	public List<OrderPO> listOrderByUid(long uid) {
		Map<String, Object> map = new HashMap<>(2);
		map.put("uid", uid);
		return super.list("com.mazing.services.order.base.dao.OrderDao.listOrderByUid", map);
	}

}
