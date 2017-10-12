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


}
