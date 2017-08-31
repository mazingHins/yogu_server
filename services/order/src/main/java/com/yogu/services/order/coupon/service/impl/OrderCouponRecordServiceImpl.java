package com.yogu.services.order.coupon.service.impl;

import java.util.Collections;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.yogu.alarm.AlarmSender;
import com.yogu.cfg.GlobalSetting;
import com.yogu.commons.utils.OSUtil;
import com.yogu.commons.utils.StringUtils;
import com.yogu.commons.utils.VOUtil;
import com.yogu.core.web.ParameterUtil;
import com.yogu.core.web.ResultCode;
import com.yogu.core.web.exception.ServiceException;
import com.yogu.language.OrderMessages;
import com.yogu.services.order.base.dao.OrderDao;
import com.yogu.services.order.base.entry.OrderPO;
import com.yogu.services.order.constants.OrderCouponRecordStatus;
import com.yogu.services.order.coupon.dao.OrderCouponRecordDao;
import com.yogu.services.order.coupon.dto.OrderCouponRecord;
import com.yogu.services.order.coupon.entry.OrderCouponRecordPO;
import com.yogu.services.order.coupon.service.OrderCouponRecordService;

/**
 * OrderCouponRecordService 的实现类
 * 
 * @author Mazing 2015-12-23
 */
@Named
public class OrderCouponRecordServiceImpl implements OrderCouponRecordService {
	
	private static final Logger logger = LoggerFactory.getLogger(OrderCouponRecordServiceImpl.class);

	@Inject
	private OrderCouponRecordDao dao;
	
	@Inject
	private OrderDao orderDao;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void save(OrderCouponRecord dto) {
		OrderCouponRecordPO po = dto2po(dto);
		dao.save(po);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int update(OrderCouponRecord dto) {
		OrderCouponRecordPO po = dto2po(dto);
		return dao.update(po);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public OrderCouponRecord getById(long recordId) {
		OrderCouponRecordPO po = dao.getById(recordId);
		return po2dto(po);
	}

	// ####
	// ## private func
	// ####

	public OrderCouponRecord po2dto(OrderCouponRecordPO po) {
		if (null == po)
			return null;
		return VOUtil.from(po, OrderCouponRecord.class);
	}

	public OrderCouponRecordPO dto2po(OrderCouponRecord dto) {
		if (null == dto)
			return null;
		return VOUtil.from(dto, OrderCouponRecordPO.class);
	}

	@Override
	public OrderCouponRecord getOrderLockRecord(long orderId) {
		List<OrderCouponRecordPO> items = dao.listByOrderAndStatus(orderId, OrderCouponRecordStatus.LOCK);
		if (items.isEmpty()) {
			return null;
		}

		// 订单有多条正在锁定的优惠记录，发送邮件
		if (items.size() > 1) {
			String message = "orderId:" + orderId + "有" + items.size() + "条处于锁定状态的记录";
			AlarmSender.sendWarn(StringUtils.trimToEmpty(GlobalSetting.getProjenv()), "[" + OSUtil.getLocalIp() + "] 系统异常：",
					"订单优惠券使用记录异常  | 内容： " + message);
		}
		OrderCouponRecordPO record = items.get(0);
		return VOUtil.from(record, OrderCouponRecord.class);
	}

	@Override
	public OrderCouponRecord getOrderUseRecord(long orderId) {
		List<OrderCouponRecordPO> items = dao.listByOrderAndStatus(orderId, OrderCouponRecordStatus.USED);
		if (items.isEmpty()) {
			return null;
		}

		// 订单有多条正在锁定的优惠记录，发送邮件
		if (items.size() > 1) {
			String message = "orderId:" + orderId + "有" + items.size() + "条处于已使用状态的记录";
			AlarmSender.sendWarn(StringUtils.trimToEmpty(GlobalSetting.getProjenv()), "[" + OSUtil.getLocalIp() + "] 系统异常：",
					"订单优惠券使用记录异常  | 内容： " + message);
		}
		OrderCouponRecordPO record = items.get(0);
		return VOUtil.from(record, OrderCouponRecord.class);
	}

	@Override
	public long recordUseSuccess(long orderId) {
		OrderPO order = orderDao.getById(orderId);
		ParameterUtil.assertNotNull(order, OrderMessages.ORDER_ORDERADMINAPI_ORDERDETAIL_ORDER_NOTEXIST());
		
		List<OrderCouponRecordPO> items = dao.listByOrderAndStatus(orderId, OrderCouponRecordStatus.LOCK);
		if (items.isEmpty()) {
			logger.error("order#coupon#service | 没有使用优惠券 | orderNo: {}", order.getOrderNo());
			return 0;
		}

		int updRow = dao.updateUseStatus(orderId, OrderCouponRecordStatus.LOCK, OrderCouponRecordStatus.USED);
		if (updRow <= 0) {
			throw new ServiceException(ResultCode.FAILURE, OrderMessages.ORDER_COUPONRECORD_RECORDUSESUCCESS_FAILURE());
		}
		
		// 调用coupon域，修改优惠券记录（状态，payNo）
		return items.get(0).getCouponId();
	}

	@Override
	public void recordUseFail(OrderCouponRecord dto) {
		if (null == dto) {
			logger.error("order#coupon#recordUseFail | 参数不能为空 ");
			throw new ServiceException(ResultCode.PARAMETER_ERROR, OrderMessages.ORDER_COUPONRECORD_RECORDUSEFAIL_COUPONRECORD_EMPTY());
		}
		
		int rows = dao.updateUseStatus(dto.getOrderId(), dto.getUseStatus(), OrderCouponRecordStatus.FAIL_ROLLBACK);
		if (rows != 1) {
			logger.error("order#coupon#recordUseFail | 数据库更新订单域优惠券使用记录失败 ");
			throw new ServiceException(ResultCode.UNKNOWN_ERROR, OrderMessages.ORDER_COUPONRECORD_RECORDUSEFAIL_FAILURE());
		}
	}
	
	@Override
	public OrderCouponRecord getNewestRecord(long orderId) {
		List<OrderCouponRecordPO> items = dao.listByOrder(orderId);
		if (items.isEmpty()) {
			return null;
		}
		OrderCouponRecordPO record = items.get(0);
		return VOUtil.from(record, OrderCouponRecord.class);
	}

	@Override
	public List<OrderCouponRecord> listByDate(String date) {
		List<OrderCouponRecordPO> items = dao.listByDate(date);
		if (items.isEmpty()) {
			return Collections.emptyList();
		}
		return VOUtil.fromList(items, OrderCouponRecord.class);
	}
}
