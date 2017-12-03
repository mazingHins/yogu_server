package com.yogu.services.order.coupon.service;

import java.util.List;

import javax.annotation.Resource;
import javax.inject.Inject;
import javax.inject.Singleton;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.yogu.commons.cache.redis.Redis;
import com.yogu.commons.cache.redis.RedisLock;
import com.yogu.services.order.OrderCacheKey;
import com.yogu.services.order.coupon.dto.Coupon;

/**
 * 分配未领取的优惠券 service处理类
 * 
 * @author sky 2015-12-29
 * 
 */
@Service
@Singleton
public class CouponCodeCacheService {
	private static final Logger logger = LoggerFactory.getLogger(CouponCodeCacheService.class);

	@Inject
	private CouponService couponService;

	@Resource(name = "redis")
	private Redis redis;

	/**
	 * 获取一个可用的优惠券 couponCode , 该方法也用于兑换成功时,被兑换的code的剔除
	 * 
	 * @param couponRuleId 从哪个规则获取
	 * @param couponCode 优惠券码,用于兑换时调用-->领取时该值为空, 兑换时该值不为空
	 * 
	 * @return 获取成功返回优惠券couponCode值; 获取失败返回null; 兑换接口无需返回值,操作完直接返回了null
	 * @author sky 2015-12-29
	 */
	public String getOne(long couponRuleId, String couponCode) {

		logger.info("couponCodeCacheService#getOne | 获取一个可用的优惠券码 | couponRuleId: {}, couponCode: {}", couponRuleId, couponCode);
		String key = OrderCacheKey.getCouponCacheKey(couponRuleId);

		if (StringUtils.isNotBlank(couponCode)) {
			// 有 couponCode的为兑换操作

			logger.info("couponCodeCacheService#getOne | 兑换操作, 从缓存中剔除被兑换掉的优惠券码 | couponRuleId: {}, couponCode: {}", couponRuleId,
					couponCode);
			// 从缓存中剔除
			redis.srem(key, couponCode);

			return null;

		} else {
			// 没有couponCode的为 领取操作

			String code = redis.spop(key);

			logger.info("couponCodeCacheService#getOne | 领取操作, 从缓存中获得未领取的优惠券码 | couponRuleId: {}, code: {}", couponRuleId, code);

			if (StringUtils.isBlank(code)) {

				// 获取是否reload过的标识
				String reloadKey = OrderCacheKey.getLoadSercordFlagKey(couponRuleId);

				if (StringUtils.isBlank(redis.get(reloadKey))) {// 没有加载过第二次

					RedisLock lock = new RedisLock(redis, key + "_lock", 5);

					if (lock.lock()) {
						// reload 一次, 仅重新load一次
						logger.info("couponCodeCacheService#cacheCheck | 执行第二次加载优惠券至缓存  | couponRuleId: {}", couponRuleId);

						// 重新加载一次
						loadUnclaimedCoupons2Cache(couponRuleId);

						// 设置reload 标识
						redis.set(reloadKey, "hasReload");

						// 再获取一次
						code = redis.spop(key);

						if (StringUtils.isBlank(code))

							return null;
						else
							return code;
					} else {
						// 运气不好,被你碰到了
						return null;
					}

				} else {
					// reload过 , 却依然为空, 那是真没了
					return null;
				}

			} else
				return code;
		}

	}

	/**
	 * 加载对应优惠券规则的未领取的优惠券到redis缓存
	 * 
	 * @param couponRuleId 优惠券规则id
	 * 
	 */
	public void loadUnclaimedCoupons2Cache(long couponRuleId) {

		// cache key
		String key = OrderCacheKey.getCouponCacheKey(couponRuleId);
		// one page size
		final int PAGE_SIZE = 2000;

		int page = 1;
		int total = 0;
		logger.info("couponCodeCacheService#loadUnclaimedCoupons2Cache | 加载数据到内存 start | couponRuleId: {}", couponRuleId);
		while (true) {

			// get by page
			List<Coupon> coupons = couponService.listUnclaimedCouponsByPage(couponRuleId, page, PAGE_SIZE);

			if (coupons != null && coupons.size() > 0) {
				String[] members = new String[coupons.size()];

				for (int j = 0; j < coupons.size(); j++) {
					// code
					members[j] = coupons.get(j).getCouponCode();
				}
				redis.sadd(key, members);

				total += coupons.size();
				if (coupons.size() < PAGE_SIZE)
					break;
			} else {
				break;
			}

			page++;
		}

		logger.info("couponCodeCacheService#loadUnclaimedCoupons2Cache | 加载数据到内存 end | couponRuleId: {}, count: {}", couponRuleId, total);

	}

}
