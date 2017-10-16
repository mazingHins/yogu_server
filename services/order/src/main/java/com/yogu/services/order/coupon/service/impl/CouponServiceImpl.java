package com.yogu.services.order.coupon.service.impl;

import java.util.ArrayList;
import java.util.Date;
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
import com.yogu.commons.utils.DateUtils;
import com.yogu.commons.utils.PageUtils;
import com.yogu.commons.utils.VOUtil;
import com.yogu.core.enums.CouponStatus;
import com.yogu.core.web.ResultCode;
import com.yogu.core.web.exception.ServiceException;
import com.yogu.language.ActivityMessages;
import com.yogu.remote.config.id.IdGenRemoteService;
import com.yogu.services.order.coupon.dao.CouponDao;
import com.yogu.services.order.coupon.dto.Coupon;
import com.yogu.services.order.coupon.entry.CouponPO;
import com.yogu.services.order.coupon.service.CouponService;
import com.yogu.services.order.utils.CouponUtils;

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

	@Override
	public List<Coupon> listUserCouponsByPage(long uid, int pageIndex, int pageSize) {
		
		pageSize = PageUtils.limitSize(pageSize, 1, 100);
		int offset = PageUtils.offset(pageIndex, pageSize);
		List<CouponPO> list = dao.listUserCouponsByPage(uid, offset, pageSize);
		return VOUtil.fromList(list, Coupon.class);
	}

	@Override
	public List<Coupon> listEffective(long uid, long totalFee) {
		List<CouponPO> list = dao.listUserCouponsByStatus(uid, CouponStatus.UNUSE.getValue());
		
		List<Coupon> result = new ArrayList<Coupon>(list.size());
		for(CouponPO po : list){
			if (po.getEnoughMoney() > totalFee) {
				continue;
			}
			Coupon coupon = VOUtil.from(po, Coupon.class);
			coupon.setCouponFee(po.getFaceValue());
			result.add(coupon);
		}
		
		return result;
	}

	@Override
	public Coupon useCoupon(long couponId, long uid, long orderNo, long totalFee, long goodsFee) {
		
		// 1. 校验是否可以使用这个优惠劵
		Coupon coupon = validateUse(couponId, uid, orderNo, totalFee);
		
		// 2. 使用优惠卷
		long finalValue = CouponUtils.getFinalValue(coupon.getFaceValue(), totalFee, coupon.getCouponType());	// 最终优惠金额
		int row = dao.updateCouponStatusAndOrderNoAndUseTime(couponId, CouponStatus.USING.getValue(), coupon.getStatus(), orderNo, DateUtils.getUniformCurrentTimeForThread());
		if (row <= 0) { // 锁定失败,请重试
			logger.info("couponService#useCoupon | 使用优惠券时 锁定券失败 | couponId: {}, orderNo: {}, totalFee: {}, finalValue: {}",
					couponId, orderNo, totalFee, finalValue);
			throw new ServiceException(ResultCode.PARAMETER_ERROR, ActivityMessages.COUPONSERVICE_USECOUPON_FAILURE());
		} 
		
		logger.info("couponService#useCoupon | 使用优惠券, 该券已成功锁定 | couponId: {}, orderNo: {}, totalFee: {}, finalValue: {}",
					couponId, orderNo, totalFee, finalValue);
		
		coupon.setCouponFee(finalValue);
		
		return coupon;
	}
	
	private Coupon validateUse(long couponId, long uid, long orderNo, long totalFee){
		
		Coupon coupon = getById(couponId);
		
		if (coupon == null) {
			logger.error("couponService#validateUse | 优惠券订单是否可用检查 , 找不到该优惠券信息 | couponId: {}, uid: {}",couponId, uid);
			throw new ServiceException(ResultCode.PARAMETER_ERROR, ActivityMessages.COUPONSERVICE_ISORDERAVAILABLE_COUPON_NOTEXIST());
		}
		
		// 检查使用者
		long dbUid = coupon.getUid();
		if (dbUid == 0 || dbUid != uid) {
			logger.error("couponService#availableCheck | 该优惠券并不属于使用者 | couponId: {}, uid: {}, dbUid：{}", couponId, uid, dbUid);
			throw new ServiceException(ResultCode.PARAMETER_ERROR, ActivityMessages.COUPONSERVICE_ISORDERAVAILABLE_COUPON_NOTOWNER());
		}
		
		// 检查 是否 在有效使用时间范围内
		Date startTime = coupon.getStartTime();
		Date endTime = coupon.getEndTime();
		boolean inEffectiveTime = DateUtils.nowInRange(startTime, endTime);

		if (!inEffectiveTime) {//
			logger.info("couponService#availableCheck | 优惠券不在有效使用时间范围内, 不可用 | couponId: {}, uid: {}, startTime: {}, endTime: {}", couponId, uid, startTime, endTime);
			throw new ServiceException(ResultCode.PARAMETER_ERROR, ActivityMessages.COUPONSERVICE_ISORDERAVAILABLE_COUPON_TIME_NOTEFFECTIVE());
		}
		
		
		// 检查 优惠券状态 (注意!!! 该处的可用状态包括 '使用中' 和 '未使用', 其中'使用中'状态需要检查是否为同一个订单,不是同一个订单不可使用; '未使用' 需要判断优惠券是否已过期)
		short couponStatus = coupon.getStatus();

		if (CouponStatus.UNUSE.getValue() != couponStatus && CouponStatus.USING.getValue() != couponStatus) {	// 状态即不是‘未使用’ 也不是 ‘使用中’, 即为不可用
			logger.info("couponService#availableCheck | 优惠券为非可用状态,  不可用  | couponId: {}, uid: {}, couponStatus: {}",
					couponId, uid, couponStatus);
			throw new ServiceException(ResultCode.PARAMETER_ERROR, ActivityMessages.COUPONSERVICE_ISORDERAVAILABLE_COUPON_NOTAVAILABLE());
		} else if (CouponStatus.USING.getValue() == couponStatus) {// '使用中' 状态的订单 需要检查是不是 同一个订单来使用, 是的话,当前可用;反之不可用(无需判断是否已失效)
			long usingOrderNo = coupon.getOrderNo();
			if (usingOrderNo != orderNo) {
				logger.info("couponService#availableCheck | 优惠券当前被其它订单使用并锁定,  不可用  | couponId: {}, uid: {},  usingOrderNo: {}, currOrderNo: {}, couponStatus: {}",
					couponId, uid, usingOrderNo, orderNo, couponStatus);
				throw new ServiceException(ResultCode.PARAMETER_ERROR, ActivityMessages.COUPONSERVICE_ISORDERAVAILABLE_COUPON_LOCKED());
			}
		} 
		
		
		// 检查 满多少钱才能使用限制
		long enoughMoney = coupon.getEnoughMoney();

		if (totalFee < enoughMoney) {// 订单总金额少于使用的最低消费金额
			logger.info("couponService#availableCheck | 订单总金额不满足 优惠券使用的最低消费金额, 不可用 | couponId: {}, uid: {}, orderNo: {}, totalFee: {}, enoughMoney: {}",
					couponId, uid, orderNo, totalFee, enoughMoney);
			throw new ServiceException(ResultCode.PARAMETER_ERROR, ActivityMessages.COUPONSERVICE_ISORDERAVAILABLE_ORDER_FEE_NOTSATISFY());
		}
		
		return coupon;
		
	}
		
		

}
