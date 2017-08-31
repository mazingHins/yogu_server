package com.yogu.services.order.base.dao.impl;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Named;

import com.yogu.commons.datasource.mybatis.MyBatisDao;
import com.yogu.services.order.base.dao.OrderDao;
import com.yogu.services.order.base.entry.OrderPO;

@Named
public class OrderDaoImpl extends MyBatisDao implements OrderDao {

	@Override
	public void save(OrderPO po) {
		super.insert("com.yogu.services.order.base.dao.OrderDao.save", po);
	}

	@Override
	public OrderPO getById(long orderId) {
		return super.get("com.yogu.services.order.base.dao.OrderDao.getById", orderId);
	}

	@Override
	public OrderPO getByOrderNoUid(long uid, long orderNo) {
		Map<String, Object> map = new HashMap<>(3);
		map.put("uid", uid);
		map.put("orderNo", orderNo);
		return super.get("com.yogu.services.order.base.dao.OrderDao.getByOrderNoUid", map);
	}

	@Override
	public OrderPO getByOrderNo(long orderNo) {
		return super.get("com.yogu.services.order.base.dao.OrderDao.getByOrderNo", orderNo);
	}

}
