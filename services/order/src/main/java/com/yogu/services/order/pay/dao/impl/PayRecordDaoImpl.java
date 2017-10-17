package com.yogu.services.order.pay.dao.impl;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Named;

import com.yogu.commons.datasource.mybatis.MyBatisDao;
import com.yogu.commons.utils.DateUtils;
import com.yogu.services.order.pay.dao.PayRecordDao;
import com.yogu.services.order.pay.entry.PayRecordPO;
import com.yogu.services.order.pay.service.params.UpdataPayRecordMode;

@Named
public class PayRecordDaoImpl extends MyBatisDao implements PayRecordDao {

	@Override
	public void save(PayRecordPO po) {
		super.insert("com.mazing.services.pay.pay.dao.PayRecordDao.save", po);
	}

	@Override
	public PayRecordPO getById(long payId) {
		return super.get("com.mazing.services.pay.pay.dao.PayRecordDao.getById", payId);
	}

	@Override
	public PayRecordPO getByPayNo(long payNo) {
		return super.get("com.mazing.services.pay.pay.dao.PayRecordDao.getByPayNo", payNo);
	}

	@Override
	public PayRecordPO getByOrderNo(long orderNo) {
		return super.get("com.mazing.services.pay.pay.dao.PayRecordDao.getByOrderNo", orderNo);
	}
	
	@Override
	public int updatePayMode(UpdataPayRecordMode params) {
		Map<String, Object> map = new HashMap<>(16);
		map.put("payId", params.getPayId());
		map.put("oldPayMode", params.getOldPayMode());
		map.put("newPayMode", params.getNewPayMode());
		map.put("useCoupon", params.getUseCoupon());
		map.put("totalFee", params.getTotalFee());
		map.put("goodsFee", params.getGoodsFee());
		map.put("couponFee", params.getCouponFee());
		map.put("orderFee", params.getOrderFee());
		map.put("payStatus", params.getPayStatus());
		map.put("updateTime", DateUtils.getUniformCurrentTimeForThread());
		return super.update("com.mazing.services.pay.pay.dao.PayRecordDao.updatePayMode", map);
	}
	
	@Override
	public int updatePayStatus(long payId, short oldStatus, short newStatus, long notifyId) {
		Map<String, Object> map = new HashMap<>(6);
		map.put("payId", payId);
		map.put("oldStatus", oldStatus);
		map.put("newStatus", newStatus);
		map.put("notifyId", notifyId);
		map.put("updateTime", DateUtils.getUniformCurrentTimeForThread());
		return super.update("com.mazing.services.pay.pay.dao.PayRecordDao.updatePayStatus", map);
	}

}
