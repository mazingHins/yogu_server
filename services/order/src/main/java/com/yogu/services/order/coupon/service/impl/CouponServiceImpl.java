package com.yogu.services.order.coupon.service.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;
import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yogu.commons.cache.redis.Redis;
import com.yogu.commons.utils.VOUtil;
import com.yogu.core.enums.CouponStatus;
import com.yogu.remote.config.id.IdGenRemoteService;
import com.yogu.services.order.coupon.dao.CouponDao;
import com.yogu.services.order.coupon.dto.Coupon;
import com.yogu.services.order.coupon.entry.CouponPO;
import com.yogu.services.order.coupon.service.CouponService;

/**
 * CouponService 的实现类
 * 
 * @author Mazing 2015-12-23
 */
@Service
public class CouponServiceImpl implements CouponService {

	private static final Logger logger = LoggerFactory.getLogger(CouponServiceImpl.class);

	/**
	 * 优惠券状态-纳入预下单（结算）条件的集合
	 */
	private static final Set<Short> SETTLE_COUPON_STATUS = new HashSet<>(4);
	static {
		SETTLE_COUPON_STATUS.add(CouponStatus.UNUSE.getValue());
		SETTLE_COUPON_STATUS.add(CouponStatus.DID_NOT_MEET_PHONE.getValue());
		SETTLE_COUPON_STATUS.add(CouponStatus.DID_NOT_MEET_MONEY.getValue());
	}

	@Inject
	private CouponDao dao;


	@Autowired
	private IdGenRemoteService idGenRemoteService;

	@Resource(name = "redis")
	private Redis redis;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void save(Coupon dto) {
		CouponPO po = dto2po(dto);
		dao.save(po);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int update(Coupon dto) {
		CouponPO po = dto2po(dto);
		return dao.update(po);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Coupon getById(long couponId) {
		CouponPO po = dao.getById(couponId);
		return po2dto(po);
	}

	// ####
	// ## private func
	// ####

	public Coupon po2dto(CouponPO po) {
		if (null == po)
			return null;
		return VOUtil.from(po, Coupon.class);
	}

	public CouponPO dto2po(Coupon dto) {
		if (null == dto)
			return null;
		return VOUtil.from(dto, CouponPO.class);
	}

}
