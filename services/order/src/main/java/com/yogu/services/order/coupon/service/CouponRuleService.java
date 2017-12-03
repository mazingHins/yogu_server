package com.yogu.services.order.coupon.service;

import java.util.List;

import com.yogu.remote.order.vo.AdminCouponListVO;
import com.yogu.remote.order.vo.CouponRuleVO;
import com.yogu.services.order.coupon.dto.CouponRule;

/**
 * 优惠券规则表 <br>
 * 的操作接口
 * 
 * @author Mazing 2015-12-23
 */
public interface CouponRuleService {

	/**
	 * 根据主键读取记录
	 */
	public CouponRule getById(long couponRuleId);

	/**
	 * 查询优惠券规则列表，结果按创建时间倒序排列。
	 *
	 * 只允许管理系统调用！
	 *
	 * @param keyword 查询的关键字，可以为null，如果不为空，模糊查询优惠券名称
	 * @param page 第几页，最小值是1
	 * @param pageSize 每页多少条记录，最小值是1
	 * @return 返回符合条件的数据，如果没有数据，返回 empty list
	 */
	List<AdminCouponListVO> queryCouponRules(String keyword, int page, int pageSize);

	/**
	 * 生成优惠券，同时保存优惠券规则、批次（自动生成）、使用范围等信息。 但不会立刻生成优惠券号码（couponCode），优惠券号码异步生成。 保存成功返回生成后的优惠券规则ID，失败则抛出异常。
	 * 
	 * @param couponRuleVO 优惠券的信息，不能为空
	 * @param createTotal 生成数量，1~1000000之间
	 * @throws com.mazing.core.web.exception.ServiceException 参数错误、业务错误抛出此异常
	 * @return 返回生成后的优惠券规则ID
	 * @author ten 2015/12/27
	 */
	long createCoupon(CouponRuleVO couponRuleVO, int createTotal);

	/**
	 * 令优惠券失效，已经领取的优惠券不能使用，未领取的优惠券作废，失败抛出异常，成功不会抛出异常。
	 * 
	 * @param couponRuleId 优惠券规则ID
	 * @throws com.mazing.core.web.exception.ServiceException 参数错误、业务错误抛出此异常
	 * @author ten 2016/1/4
	 */
	void disableCouponRule(long couponRuleId);

	/**
	 * 设置优惠券停止兑换，已经领取的优惠券可以使用，未领取的优惠券不能兑换。 失败抛出异常，成功不会抛出异常
	 * 
	 * @param couponRuleId 优惠券规则ID
	 * @throws com.mazing.core.web.exception.ServiceException 参数错误、业务错误抛出此异常
	 * @author ten 2016/1/4
	 */
	void stopExchange(long couponRuleId);

}
