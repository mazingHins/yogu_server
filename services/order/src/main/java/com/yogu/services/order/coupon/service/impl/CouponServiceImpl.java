package com.yogu.services.order.coupon.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;
import javax.inject.Inject;

import org.apache.commons.lang3.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yogu.commons.cache.redis.Redis;
import com.yogu.commons.cache.redis.RedisLock;
import com.yogu.commons.utils.DateUtils;
import com.yogu.commons.utils.JsonUtils;
import com.yogu.commons.utils.PageUtils;
import com.yogu.commons.utils.StringUtils;
import com.yogu.commons.utils.VOUtil;
import com.yogu.core.enums.BooleanConstants;
import com.yogu.core.enums.CouponStatus;
import com.yogu.core.remote.config.ConfigGroupConstants;
import com.yogu.core.remote.config.ConfigRemoteService;
import com.yogu.core.web.ResultCode;
import com.yogu.core.web.exception.ServiceException;
import com.yogu.language.ActivityMessages;
import com.yogu.remote.config.id.IdGenRemoteService;
import com.yogu.services.order.OrderCacheKey;
import com.yogu.services.order.coupon.dao.CouponDao;
import com.yogu.services.order.coupon.dto.Coupon;
import com.yogu.services.order.coupon.dto.CouponRule;
import com.yogu.services.order.coupon.entry.CouponPO;
import com.yogu.services.order.coupon.service.CouponRuleService;
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
	
	private static final String FROM_CHAR = "3456789ABCDEFGHIJKLMNPQRSTUVWXY";// 去除了0,1, 2, o, z

	private static final int CODE_LENGTH = 10;

	@Inject
	private CouponDao dao;

	@Autowired
	private IdGenRemoteService idGenRemoteService;
	
	@Autowired
	private CouponRuleService couponRuleService;
	
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
		
	@Override
	public int disableCoupons(long couponRuleId) {
		// 将规则为couponRuleId 的优惠券置为失效
		logger.info("couponService#disableCoupons | 使优惠券失效 | couponRuleId: {}", couponRuleId);

		short toStatus = CouponStatus.INVALID.getValue();

		int row = dao.disableCoupons(couponRuleId, toStatus);
		logger.info("couponService#disableCoupons | 优惠券失效  操作结束 | couponRuleId: {}, rows: {}", couponRuleId, row);

		if (row <= 0)
			return 0;
		else
			return row;

	}

	private long obtain(long uid, long couponRuleId) {
		// 领取优惠券, 可能领不到
		logger.info("couponService#obtainCoupon | 领取优惠券 | uid: {}, couponRuleId: {}", uid, couponRuleId);
		if (uid <1) {
			return 0;
		}

		String lockKey = OrderCacheKey.getCouponObtainLockKey(uid, couponRuleId);
		RedisLock lock = new RedisLock(redis, lockKey, 5);
		// 防并发
		if (!lock.lock()) {
			return 0;
		}
		// 前置 券规则检查
		CouponRule rule = couponRuleService.getById(couponRuleId);
		if (rule == null) {
			logger.error("couponService#obtainCoupon | 领取的优惠券 规则不存在 , 不能再领取  | uid: {},  couponRuleId: {}", uid, couponRuleId);
			return 0;
		}
		short isStop = rule.getIsStop();
		if (BooleanConstants.TRUE == isStop) {
			logger.info("couponService#obtainCoupon | 该优惠券已经终止发放, 领取失败 | uid: {},  couponRuleId: {}", uid, couponRuleId);
			return 0;
		}

		short isEnable = rule.getIsEnable();
		if (BooleanConstants.FALSE == isEnable) {
			logger.info("couponService#obtainCoupon | 该优惠券已经失效 , 领取失败 | uid: {},  couponRuleId: {}", uid, couponRuleId);
			return 0;
		}

		long now = System.currentTimeMillis();
		Date endTime = rule.getEndTime();
		if (null != endTime && now > endTime.getTime()) {// 是否过期
			logger.info("couponService#obtainCoupon | 优惠券已过期 |  uid: {},  couponRuleId: {},  endTime: {}", uid, couponRuleId, endTime);
			return 0;
		}
		
		return createCoupon(rule);
	}
	
	private long createCoupon(CouponRule rule) {

		logger.info("couponService#batchCreateCoupons | 开始批量创建优惠券 | coupon: {}", JsonUtils.toJSONString(rule));

		// 批量生成优惠券
		Date now = new Date();
		CouponPO coupon = new CouponPO();
		coupon.setAddTime(now);

		long couponRuleId = rule.getCouponRuleId();

		coupon.setCouponName(rule.getCouponName());
		coupon.setCouponRuleId(rule.getCouponRuleId());
		coupon.setCouponType(rule.getCouponType());
		coupon.setEndTime(rule.getEndTime());
		coupon.setEnoughMoney(rule.getEnoughMoney());

		coupon.setOrderNo(0);
		coupon.setStartTime(rule.getStartTime());
		coupon.setStatus(CouponStatus.UNCLAIMED.getValue());
		coupon.setUid(0);
		coupon.setMostOffer(rule.getMostOffer());
		coupon.setFaceValue(Long.valueOf(rule.getRegExpression()));

		long couponId = idGenRemoteService.getNextCouponPublicId();
		coupon.setCouponId(couponId);
		String couponCode = generateCouponCode();
		coupon.setCouponCode(couponCode);
		coupon.setCreateTime(DateUtils.getUniformCurrentTimeForThread());
		coupon.setUpdateTime(DateUtils.getUniformCurrentTimeForThread());
		try {
			dao.save(coupon);
			logger.info("couponService#batchCreateCoupons | 创建优惠券成功 | couponRuleId: {},couponId: {}", couponRuleId, couponId);
		} catch (Exception e) {
			logger.error("couponService#batchCreateCoupons | 批量生成优惠券, 保存优惠券出错, 生成的couponId主键重复 | couponRuleId: {},couponId: {} msg: {}",
					couponRuleId, couponId, e.getMessage(), e);
			throw new RuntimeException("优惠券主键couponId 发生 重复");
		}
		
		return couponId;
	}
	
	/**
	 * 生成8位 优惠券码, 可能发生重复
	 * 
	 * @return
	 */
	private static String generateCouponCode() {
		// 2016/1/5 by ten
		// 至少保证：1. 优惠券号码在本次生成中不重复；
		// 2. 优惠券号码在本次生成中不连续；
		return RandomStringUtils.random(CODE_LENGTH, FROM_CHAR);
	}

	@Override
	public List<Coupon> listUnclaimedCouponsByPage(long couponRuleId, int pageIndex, int pageSize) {
		// 获取所有未领取的优惠券信息数据

		logger.info("couponService#listUnclaimedCouponsByPage | 分页获取所有未领取的优惠券信息 | couponRuleId: {}", couponRuleId);

		short queryStatus = CouponStatus.UNCLAIMED.getValue();
		int offset = PageUtils.offset(pageIndex, pageSize);
		List<CouponPO> list = dao.listCouponsByRuleIdAndStatus(couponRuleId, queryStatus, offset, pageSize);

		List<Coupon> result = VOUtil.fromList(list, Coupon.class);

		logger.info("couponService#listUnclaimedCouponsByPage | 分页获取所有未领取的优惠券信息结束 | result: {}", result.size());

		return result;
	}

	@Override
	public void newOrder(long uid) {
		logger.info("couponService#givingGift | 派发礼包给新注册的用户 | uid: {}", uid);
		String couponRuleIdStr = StringUtils.trimToEmpty(
				ConfigRemoteService.getConfig(ConfigGroupConstants.COUPON_GIFT, ConfigGroupConstants.GIFT_OF_COUPON));

		long couponRuleId = 0;
		if (StringUtils.isNotBlank(couponRuleIdStr)) {
			couponRuleId = Long.valueOf(couponRuleIdStr);
			if (couponRuleId > 0) {
				long couponId = obtain(uid, couponRuleId);
				logger.info("couponService#givingGift | 完成礼包类型 [优惠券] 派发给新注册的用户 | uid: {}, couponRuleId: {}, couponId: {}",
						uid, couponRuleId, couponId);
				return;
			}
		}

		logger.info("couponService#givingGift | 用户新注册, 但没有任何可派发的礼包 | uid: {}, couponRuleId: {} ", uid, couponRuleIdStr);
	}
		

}
