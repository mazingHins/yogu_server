package com.yogu.services.order.pay.service.impl;

import javax.inject.Inject;
import javax.inject.Named;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.yogu.commons.utils.VOUtil;
import com.yogu.services.order.pay.dao.PayRecordDao;
import com.yogu.services.order.pay.dto.PayRecord;
import com.yogu.services.order.pay.entry.PayRecordPO;
import com.yogu.services.order.pay.service.PayRecordService;

@Named
public class PayRecordServiceImpl implements PayRecordService{
	
	private static final Logger logger = LoggerFactory.getLogger(PayRecordServiceImpl.class);
	
	@Inject
	private PayRecordDao dao;

	@Override
	public void save(PayRecord dto) {
		PayRecordPO po = dto2po(dto);
		dao.save(po);
	}
	
	public PayRecord po2dto(PayRecordPO po) {
		return VOUtil.from(po, PayRecord.class);
	}

	public PayRecordPO dto2po(PayRecord dto) {
		return VOUtil.from(dto, PayRecordPO.class);
	}

}
