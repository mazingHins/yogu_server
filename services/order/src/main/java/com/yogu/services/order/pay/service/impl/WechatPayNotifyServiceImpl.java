package com.yogu.services.order.pay.service.impl;

import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.yogu.commons.utils.VOUtil;
import com.yogu.remote.config.id.IdGenRemoteService;
import com.yogu.services.order.pay.dao.PayRecordDao;
import com.yogu.services.order.pay.dao.WechatPayNotifyDao;
import com.yogu.services.order.pay.dto.WechatPayNotify;
import com.yogu.services.order.pay.entry.PayRecordPO;
import com.yogu.services.order.pay.entry.WechatPayNotifyPO;
import com.yogu.services.order.pay.service.WechatPayNotifyService;

/**
 * WechatPayNotifyService 的实现类
 * 
 * @author JFan 2015-08-06
 */
@Named
public class WechatPayNotifyServiceImpl implements WechatPayNotifyService {
	
	private static final Logger logger = LoggerFactory.getLogger(WechatPayNotifyServiceImpl.class);

	@Inject
	private WechatPayNotifyDao dao;
	
	@Inject
	private PayRecordDao payRecordDao;
	
	@Inject
	private IdGenRemoteService idGenRemoteService;

	@Override
	public void save(WechatPayNotify dto) {
		WechatPayNotifyPO po = dto2po(dto);
		dao.save(po);
	}

	@Override
	public int update(WechatPayNotify dto) {
		WechatPayNotifyPO po = dto2po(dto);
		return dao.update(po);
	}

	@Override
	public WechatPayNotify getById(long notifyId) {
		WechatPayNotifyPO po = dao.getById(notifyId);
		if (null == po)
			return null;
		return po2dto(po);
	}

	// ####
	// ## private func
	// ####

	public WechatPayNotify po2dto(WechatPayNotifyPO po) {
		return VOUtil.from(po, WechatPayNotify.class);
	}

	public WechatPayNotifyPO dto2po(WechatPayNotify dto) {
		return VOUtil.from(dto, WechatPayNotifyPO.class);
	}

	@Override
	public long createNotify(WechatPayNotify dto) {
		long notifyId = idGenRemoteService.getNextPayCallbackId();
		dto.setNotifyId(notifyId);
		dto.setCreateTime(new Date());
		
		WechatPayNotifyPO po = dto2po(dto);
		dao.save(po);
		
		return notifyId;
	}

	@Override
	public List<WechatPayNotify> queryByPayIds(List<Long> payIds) {
		if (payIds.isEmpty())
			return Collections.emptyList();
		List<WechatPayNotifyPO> list = dao.listByPayIds(payIds);
		return VOUtil.fromList(list, WechatPayNotify.class);
	}

	@Override
	public WechatPayNotify getByPayNo(long payNo) {

		logger.info("pay#WechatPayNotifyServiceImpl#getByPayNo | 通过支付记录编号，获取微信支付回调记录start | payNo: {}", payNo);
		if (payNo < 1) {
			return null;
		}
		
		PayRecordPO payRecord = payRecordDao.getByPayNo(payNo);
		if(payRecord == null){
			logger.info("pay#WechatPayNotifyServiceImpl#getByPayNo | 没有支付记录数据 | payNo: {}", payNo);
			return null;
		}

		List<WechatPayNotifyPO> list = dao.listByPayIds(Arrays.asList(payRecord.getPayId()));
		if (list.isEmpty()) {
			logger.info("pay#WechatPayNotifyServiceImpl#getByPayNo | 没有微信支付回调记录数据 | payNo: {}", payNo);
			return null;
		}

		return VOUtil.from(list.get(0), WechatPayNotify.class);
	}

}
