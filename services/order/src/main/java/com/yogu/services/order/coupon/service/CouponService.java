package com.yogu.services.order.coupon.service;

import java.util.List;

import com.yogu.services.order.coupon.dto.Coupon;


/**
 * 优惠券表， 包括生成、绑定用户等各种状态的优惠券 <br>
 * 的操作接口
 * 
 * @author Mazing 2015-12-23
 */
public interface CouponService {

	/**
	 * 保存数据
	 * 
	 * @param dto 对象
	 */
	public void save(Coupon dto);

	/**
	 * 修改数据，以主键更新
	 * 
	 * @param dto - 要更新的数据
	 * @return 更新的行数
	 */
	public int update(Coupon dto);

	/**
	 * 根据主键读取记录
	 */
	public Coupon getById(long couponId);
	
	/**
	 * 分页获取用户的优惠券列表，结果按照过期时间倒序排序
	 * 
	 * @param uid - 用户id
	 * @param pageIndex - 页码
	 * @param pageSize - 每页大小
	 * @return 符合的记录列表，若无，返回empty list
	 */
	public List<Coupon> listUserCouponsByPage(long uid, int pageIndex, int pageSize);
	
	/***
	 * 获取用户可用的优惠卷列表
	 * 
	 * @param uid
	 * @param totalFee
	 * @return
	 */
	public List<Coupon> listEffective(long uid, long totalFee);
	
	/**
	 * 订单使用优惠券---如果可用, 将优惠券状态置为‘使用中’
	 * 
	 * 
	 * @param couponId 使用的优惠券id
	 * @param uid 使用者uid
	 * @param orderNo 订单号
	 * @param totalFee 订单总额
	 * @param goodsFee 商品总价
	 * @return 如果使用成功, 返回success=1,msg="使用成功", 同时返回 finalValue(long),couponName(String),couponType(short),faceValue(long)的值 <br>
	 *         使用不成功： 1：success=0,msg="不能使用的原因" 2：返回异常信息,该种情况属于 对账参数错误,需要服务器内部处理
	 * @author sky 2015-12-25 hins 2016-7-26 将返回对象map转成UseCouponResultVO
	 */
	public Coupon useCoupon(long couponId, long uid, long orderNo, long totalFee, long goodsFee);


	/**
	 * 使优惠券失效(admin 管理后台操作调用)<br>
	 * 
	 * 该接口将 状态为 0 和 1 的优惠券 置为状态 等于4 @see {CouponStatus}
	 * 
	 * @param couponRuleId 优惠券规则id
	 * @author sky 2016-01-06
	 * @return 影响的行数
	 * 
	 */
	public int disableCoupons(long couponRuleId);
	
	
	/**
	 * 新注册用户是否有领取的优惠券 检查及分配<br>
	 * 若有领取的优惠券, 该接口直接将id为couponId的优惠券分配给用户uid
	 * 
	 * @param uid 新注册的用户
	 * @param account 领取的账户md5
	 * @param mobile mobile的对称加密串 @see {@link LogUtil.encrypt}
	 * @author sky 2015-12-30
	 */
	public void newOrder(long uid);
	
	/**
	 * 分页获取指定优惠券规则的未被领取的优惠券列表数据
	 * 
	 * @param couponRuleId 优惠券规则id
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 * @author sky 2015-12-29
	 */
	public List<Coupon> listUnclaimedCouponsByPage(long couponRuleId, int pageIndex, int pageSize);

}
