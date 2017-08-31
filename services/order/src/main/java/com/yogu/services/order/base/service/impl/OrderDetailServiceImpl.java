package com.yogu.services.order.base.service.impl;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.yogu.commons.utils.VOUtil;
import com.yogu.core.web.ParameterUtil;
import com.yogu.language.OrderMessages;
import com.yogu.services.order.base.dao.OrderDao;
import com.yogu.services.order.base.dao.OrderDetailDao;
import com.yogu.services.order.base.dto.OrderDetail;
import com.yogu.services.order.base.entry.OrderDetailPO;
import com.yogu.services.order.base.entry.OrderPO;
import com.yogu.services.order.base.service.OrderDetailService;

/**
 * OrderDetailService 的实现类
 * 
 * @author JFan 2015-07-13
 */
@Named
public class OrderDetailServiceImpl implements OrderDetailService {

	private static final Logger logger = LoggerFactory.getLogger(OrderDetailServiceImpl.class);

	@Inject
	private OrderDetailDao dao;

	@Inject
	private OrderDao orderDao;

	@Override
	public void save(OrderDetail dto) {
		OrderDetailPO po = dto2po(dto);
		dao.save(po);
	}

	// ####
	// ## private func
	// ####

	public OrderDetail po2dto(OrderDetailPO po) {
		return VOUtil.from(po, OrderDetail.class);
	}

	public OrderDetailPO dto2po(OrderDetail dto) {
		return VOUtil.from(dto, OrderDetailPO.class);
	}



	@Override
	public List<OrderDetail> listByOrderNo(long orderNo) {
		OrderPO order = orderDao.getByOrderNo(orderNo);
		ParameterUtil.assertNotNull(order, OrderMessages.ORDER_ORDERADMINAPI_ORDERDETAIL_ORDER_NOTEXIST());
		List<OrderDetailPO> list = dao.listByOrderId(order.getOrderId());
		return VOUtil.fromList(list, OrderDetail.class);
	}


	@Override
	public List<OrderDetail> listByOrderId(long orderId) {
		List<OrderDetailPO> list = dao.listByOrderId(orderId);
		return VOUtil.fromList(list, OrderDetail.class);
	}

}
