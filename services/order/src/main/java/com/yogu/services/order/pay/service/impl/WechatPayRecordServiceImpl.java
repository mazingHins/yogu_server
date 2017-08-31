package com.yogu.services.order.pay.service.impl;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import com.yogu.commons.utils.VOUtil;
import com.yogu.services.order.pay.dao.WechatPayRecordDao;
import com.yogu.services.order.pay.dto.WechatPayRecord;
import com.yogu.services.order.pay.entry.WechatPayRecordPO;
import com.yogu.services.order.pay.service.WechatPayRecordService;

/**
 * WechatPayRecordService 的实现类
 * 
 * @author Mazing 2016-01-30
 */
@Named
public class WechatPayRecordServiceImpl implements WechatPayRecordService {

	@Inject
	private WechatPayRecordDao dao;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void save(WechatPayRecord dto) {
		WechatPayRecordPO po = dto2po(dto);
		dao.save(po);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int update(WechatPayRecord dto) {
		WechatPayRecordPO po = dto2po(dto);
		return dao.update(po);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public WechatPayRecord getById(long payId) {
		WechatPayRecordPO po = dao.getById(payId);
		return po2dto(po);
	}

	// ####
	// ## private func
	// ####

	public WechatPayRecord po2dto(WechatPayRecordPO po) {
		if (null == po)
			return null;
		return VOUtil.from(po, WechatPayRecord.class);
	}

	public WechatPayRecordPO dto2po(WechatPayRecord dto) {
		if (null == dto)
			return null;
		return VOUtil.from(dto, WechatPayRecordPO.class);
	}

}
