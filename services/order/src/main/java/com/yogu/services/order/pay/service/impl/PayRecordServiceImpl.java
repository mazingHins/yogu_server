package com.yogu.services.order.pay.service.impl;

import javax.inject.Inject;
import javax.inject.Named;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.yogu.commons.utils.VOUtil;
import com.yogu.core.enums.pay.NotifyEnum;
import com.yogu.core.enums.pay.NotifyEnum.PayRecordStatus;
import com.yogu.core.web.ParameterUtil;
import com.yogu.core.web.ResultCode;
import com.yogu.core.web.exception.ServiceException;
import com.yogu.language.PayMessages;
import com.yogu.services.order.pay.dao.PayRecordDao;
import com.yogu.services.order.pay.dto.PayRecord;
import com.yogu.services.order.pay.entry.PayRecordPO;
import com.yogu.services.order.pay.service.PayRecordService;
import com.yogu.services.order.pay.service.params.UpdataPayRecordMode;

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
	
	@Override
	public PayRecord getByPayNo(long payNo) {
		PayRecordPO po = dao.getByPayNo(payNo);
		if (null == po)
			return null;
		return po2dto(po);
	}

	@Override
	public PayRecord getByOrderNo(long orderNo) {
		PayRecordPO po = dao.getByOrderNo(orderNo);
		if(null == po){
			return null;
		}
		return po2dto(po);
	}
	
	@Override
	public void changePayMode(long payNo, UpdataPayRecordMode params) {
		logger.info("store#PayRecordServiceImpl#changePayMode | 更换支付方式start | payNo: {}, payMode: {}", payNo, params.getNewPayMode());
		PayRecordPO po = dao.getByPayNo(payNo);
		if (po == null) {
			logger.info("pay#record#changePayMode | 支付记录不存在 | payNo: {}", payNo);
			throw new ServiceException(ResultCode.PARAMETER_ERROR, PayMessages.PAYRECORD_NOTIFYSUCCESS_PAYRECORD_NOTEXIST());
		}
		if (po.getPayStatus() != PayRecordStatus.NOT_PAY.getValue()) {
			logger.error("pay#record#changePayMode | 支付记录状态不等于未支付 | payNo: {}", payNo);
			throw new ServiceException(ResultCode.PARAMETER_ERROR, PayMessages.PAYRECORD_CHANGEHASDISCOUNTTOYES_PAYSTATUS_ERROR());
		}
		// 防止不用支付
		short payStatus = params.getTotalFee() == 0 ? PayRecordStatus.SUCCESS_PAY.getValue() : PayRecordStatus.NOT_PAY.getValue();

		params.setPayId(po.getPayId());
		params.setOldPayMode(po.getPayMode());
		params.setPayStatus(payStatus);
		int updRow = dao.updatePayMode(params);
		if (updRow == 0) {
			logger.error("store#PayRecordServiceImpl#changePayMode | 更新支付记录数据库错误 | payNo: {}", payNo);
			throw new ServiceException(ResultCode.UNKNOWN_ERROR, PayMessages.PAYRECORD_PAYSUCCESS_UPDATEPAYSTATUS_FAILURE());
		}
		
	}
	
	@Override
	public void paySuccess(long payNo, long notifyId) {
		PayRecordPO po = dao.getByPayNo(payNo);
		ParameterUtil.assertNotNull(po, PayMessages.PAYRECORD_NOTIFYSUCCESS_PAYRECORD_NOTEXIST());
		ParameterUtil.assertGreaterThanZero(notifyId, PayMessages.PAYRECORD_PAYSUCCESS_NOTIFYID_ERROR());
		// 若支付记录的回调ID跟notifyId相同，且支付记录的支付状态等于已支付
		// 则表示该支付记录已经处理，直接return。
		if (po.getNotifyId() == notifyId && po.getPayStatus() == NotifyEnum.PayRecordStatus.SUCCESS_PAY.getValue()) {
			return;
		}
		
		int updRow = dao.updatePayStatus(po.getPayId(), NotifyEnum.PayRecordStatus.NOT_PAY.getValue(), NotifyEnum.PayRecordStatus.SUCCESS_PAY.getValue(), notifyId);
		if (updRow == 0) {
			logger.error("store#PayRecordServiceImpl#paySuccess | 更新支付记录数据库错误 | payNo: {}", payNo);
			throw new ServiceException(ResultCode.UNKNOWN_ERROR, PayMessages.PAYRECORD_PAYSUCCESS_UPDATEPAYSTATUS_FAILURE());
		}
	}

	@Override
	public void payFail(long payNo, long notifyId) {
		PayRecordPO po = dao.getByPayNo(payNo);
		ParameterUtil.assertNotNull(po, PayMessages.PAYRECORD_NOTIFYSUCCESS_PAYRECORD_NOTEXIST());
		ParameterUtil.assertGreaterThanZero(notifyId, PayMessages.PAYRECORD_PAYSUCCESS_NOTIFYID_ERROR());
		// 若支付记录的回调ID跟notifyId相同，且支付记录的支付状态等于已支付
		// 则表示该支付记录已经处理，直接return。
		if (po.getNotifyId() == notifyId && po.getPayStatus() == NotifyEnum.PayRecordStatus.FAIL_PAY.getValue()) {
			return;
		}
		int updRow = dao.updatePayStatus(po.getPayId(), NotifyEnum.PayRecordStatus.NOT_PAY.getValue(), NotifyEnum.PayRecordStatus.FAIL_PAY.getValue(), notifyId);
		if (updRow == 0) {
			logger.error("store#PayRecordServiceImpl#payFail | 更新支付记录数据库错误 | payNo: {}", payNo);
			throw new ServiceException(ResultCode.UNKNOWN_ERROR, PayMessages.PAYRECORD_PAYSUCCESS_UPDATEPAYSTATUS_FAILURE());
		}
	}

}
