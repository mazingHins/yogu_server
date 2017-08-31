package com.yogu.services.order.pay.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.yogu.commons.utils.VOUtil;
import com.yogu.core.web.PayErrorCode;
import com.yogu.core.web.exception.ServiceException;
import com.yogu.language.PayMessages;
import com.yogu.remote.config.id.IdGenRemoteService;
import com.yogu.services.order.pay.dao.AlipayPayNotifyDao;
import com.yogu.services.order.pay.dto.AlipayPayNotify;
import com.yogu.services.order.pay.entry.AlipayPayNotifyPO;
import com.yogu.services.order.pay.service.AlipayPayNotifyService;

/**
 * AlipayPayNotifyService 的实现类
 * 
 * @author JFan 2015-08-06
 */
@Named
public class AlipayPayNotifyServiceImpl implements AlipayPayNotifyService {

	private static final Logger logger = LoggerFactory.getLogger(AlipayPayNotifyServiceImpl.class);

	@Inject
	private AlipayPayNotifyDao alipayPayNotifyDao;

	@Inject
	private IdGenRemoteService idGenRemoteService;

	@Override
	public AlipayPayNotify getById(long notifyId) {
		AlipayPayNotifyPO po = alipayPayNotifyDao.getById(notifyId);
		if (null == po)
			return null;
		return po2dto(po);
	}

	// ####
	// ## private func
	// ####

	public AlipayPayNotify po2dto(AlipayPayNotifyPO po) {
		return VOUtil.from(po, AlipayPayNotify.class);
	}

	public AlipayPayNotifyPO dto2po(AlipayPayNotify dto) {
		return VOUtil.from(dto, AlipayPayNotifyPO.class);
	}

	@Override
	public long successNotify(AlipayPayNotify notify) {
		AlipayPayNotifyPO po = alipayPayNotifyDao.getByPayId(notify.getPayId());
		if (po == null) {
			long notifyId = idGenRemoteService.getNextPayCallbackId();
			notify.setNotifyId(notifyId);
			notify.setCreateTime(new Date());
			alipayPayNotifyDao.save(VOUtil.from(notify, AlipayPayNotifyPO.class));
			return notifyId;
		} else {
			notify.setNotifyId(po.getNotifyId());
			alipayPayNotifyDao.update(VOUtil.from(notify, AlipayPayNotifyPO.class));
			return notify.getNotifyId();
		}
	}

	@Override
	public long createNotify(AlipayPayNotify notify) {
		AlipayPayNotifyPO po = alipayPayNotifyDao.getByPayId(notify.getPayId());
		if (po != null) {
			logger.info("pay#alipayPayNotifyService#createNotify | notify record is exist | payNo: {}", notify.getPayNo());
			throw new ServiceException(PayErrorCode.PAY_RECORD_IS_EXIST, PayMessages.ALIPAYPAYNOTIFY_CREATENOTIFY_ALIPAYNOTIFY_EXIST());
		}
		long notifyId = idGenRemoteService.getNextPayCallbackId();
		notify.setNotifyId(notifyId);
		notify.setCreateTime(new Date());
		alipayPayNotifyDao.save(VOUtil.from(notify, AlipayPayNotifyPO.class));
		logger.info("pay#alipay#createNotify | 创建支付宝回调记录成功 | notifyId: {}, payId: {}, traceNo: {}",
				notifyId, notify.getPayId(), notify.getTradeNo());
		return notifyId;
	}

	@Override
	public List<AlipayPayNotify> listByPayIds(List<Long> payIds) {
		if (payIds.isEmpty())
			return new ArrayList<AlipayPayNotify>(0);
		List<AlipayPayNotifyPO> list = alipayPayNotifyDao.listByPayIds(payIds);
		return VOUtil.fromList(list, AlipayPayNotify.class);
	}

	@Override
	public AlipayPayNotify getByTradeNo(String tradeNo) {
		AlipayPayNotifyPO po = alipayPayNotifyDao.getByTradeNo(tradeNo);
		if (null == po)
			return null;
		return po2dto(po);
	}

}
