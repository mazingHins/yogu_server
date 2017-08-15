package com.yogu.services.order.base.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Named;

import com.yogu.commons.datasource.mybatis.MyBatisReadonlyDao;
import com.yogu.services.order.base.dao.OrderDetailReadonlyDao;
import com.yogu.services.order.base.entry.OrderDetailPO;

@Named
public class OrderDetailReadonlyDaoImpl extends MyBatisReadonlyDao implements OrderDetailReadonlyDao{

	@Override
	public OrderDetailPO getById(long objectId, long orderId) {
		Map<String, Object> map = new HashMap<>(2);
		map.put("objectId", objectId);
		map.put("orderId", orderId);
		return super.get("com.mazing.services.order.base.dao.OrderDetailDao.getById", map);
	}

	@Override
	public List<OrderDetailPO> listByOrderId(long orderId) {
		Map<String, Object> map = new HashMap<>(2);
		map.put("orderId", orderId);
		return super.list("com.mazing.services.order.base.dao.OrderDetailDao.listByOrderId", map);
	}

}
