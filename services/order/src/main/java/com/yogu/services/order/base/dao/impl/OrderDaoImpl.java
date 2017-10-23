package com.yogu.services.order.base.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Named;

import com.yogu.commons.datasource.mybatis.MyBatisDao;
import com.yogu.services.order.base.dao.OrderDao;
import com.yogu.services.order.base.dao.params.UpdateOrderPayPOJO;
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
	
	public int updatePayNoAndSuccess(UpdateOrderPayPOJO pojo) {
		Map<String, Object> map = new HashMap<>(14);
		map.put("orderId", pojo.getOrderId());
		map.put("payMode", pojo.getPayMode());
		map.put("oldStatus", pojo.getOldStatus());
		map.put("newStatus", pojo.getNewStatus());
		map.put("payNo", pojo.getPayNo());
		map.put("updateTime", pojo.getUpdateTime());
		map.put("orderBeginTime", pojo.getOrderBeginTime());
		return super.update("com.yogu.services.order.base.dao.OrderDao.updatePayNoAndSuccess", map);
	}
	
	@Override
	public int updatePayNo(UpdateOrderPayPOJO pojo) {
		Map<String, Object> map = new HashMap<>(10);
		map.put("orderId", pojo.getOrderId());
		map.put("payMode", pojo.getPayMode());
		map.put("oldStatus", pojo.getOldStatus());
		map.put("payNo", pojo.getPayNo());
		map.put("updateTime", pojo.getUpdateTime());
		return super.update("ccom.yogu.services.order.base.dao.OrderDao.updatePayNo", map);
	}

	@Override
	public List<OrderPO> listByUid(long uid, int offset, int pageSize) {
		Map<String, Object> map = new HashMap<>(14);
		map.put("uid", uid);
		map.put("offset", offset);
		map.put("pageSize", pageSize);
		return super.list("com.yogu.services.order.base.dao.OrderDao.listByUid", map);
	}
	

}
