package com.yogu.services.order.coupon.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yogu.commons.datasource.annocation.TheDataDao;
import com.yogu.services.order.coupon.entry.CouponRulePO;

/**
 * mz_coupon_rule 表的操作接口
 * 
 * @author Mazing 2015-12-23
 */
@TheDataDao
public interface CouponRuleDao {

	/**
	 * 保存数据
	 * 
	 * @param po 对象
	 */
	public void save(CouponRulePO po);

	// /**
	// * 根据主键删除数据
	// */
	// public int deleteById(@Param("couponRuleId") int couponRuleId);

	/**
	 * 修改数据，以主键更新
	 * 
	 * @param po - 要更新的数据
	 * @return 更新的行数
	 */
	public int update(CouponRulePO po);

	/**
	 * 更新排除群组标识
	 * 
	 * @param couponRuleId
	 * @param hasExclude
	 * @param excludeGroupType
	 * @return
	 * @author sky 2016-06-22
	 */
	public int updateExcludeFlag(@Param("couponRuleId") long couponRuleId, @Param("hasExclude") short hasExclude,
			@Param("excludeGroupType") short excludeGroupType);

	/**
	 * 根据主键读取记录
	 */
	public CouponRulePO getById(@Param("couponRuleId") long couponRuleId);

	/**
	 * 查询全部记录
	 * 
	 * @return 返回所有的数据，如果没有数据，返回emtpy list
	 */
	public List<CouponRulePO> listAll();

	/**
	 * 查询优惠券规则列表，结果按创建时间倒序排列
	 * 
	 * @param keyword 查询的关键字，可以为null，如果不为空，模糊查询优惠券名称
	 * @param offset 从第几条记录开始
	 * @param pageSize 返回多少条记录
	 * @return 返回符合条件的数据，如果没有数据，返回 empty list
	 * @author ten 2015/12/24
	 */
	List<CouponRulePO> queryCouponRules(@Param("keyword") String keyword, @Param("offset") int offset, @Param("pageSize") int pageSize);

	/**
	 * 设置优惠券规则是否可用
	 * 
	 * @param couponRuleId 优惠券规则ID
	 * @param newValue 新的值（0/1）
	 * @param oldValue 旧的值（0/1）
	 * @return 返回更新的行数
	 * @author ten 2016/1/4
	 */
	int updateEnable(@Param("couponRuleId") long couponRuleId, @Param("newValue") short newValue, @Param("oldValue") short oldValue);

	/**
	 * 设置优惠券规则是否停止发放
	 * 
	 * @param couponRuleId 优惠券规则ID
	 * @param newValue 新的值（0/1）
	 * @param oldValue 旧的值（0/1）
	 * @return 返回更新的行数
	 * @author ten 2016/1/4
	 */
	int updateStop(@Param("couponRuleId") long couponRuleId, @Param("newValue") short newValue, @Param("oldValue") short oldValue);

	/**
	 * 更新优惠券规则的卡包id 及 是否可分享状态
	 * 
	 * @param couponRuleId 优惠券规则ID
	 * @param bagId 卡包id
	 * @param shareStatus 是否可分享
	 * @param adminId
	 * @return
	 * @author sky 2016-01-26
	 */
	int updateShareAndBag(@Param("couponRuleId") long couponRuleId, @Param("bagId") long bagId, @Param("shareStatus") short shareStatus,
			@Param("adminId") long adminId);

	/**
	 * 查询优惠券规则列表，仅用于后台管理的卡包生成展示，结果按创建时间倒序排列。 结果排除已经进入其他卡包的优惠券，并且在有效期内，没有作废、没有停止发放的优惠券。
	 * 
	 * @param keyword 查询的关键字，可以为null，如果不为空，模糊查询优惠券名称
	 * @param offset 从第几条记录开始
	 * @param pageSize 返回多少条记录
	 * @return 返回符合条件的数据，如果没有数据，返回 empty list
	 * @author ten 2016/1/26
	 */
	List<CouponRulePO> queryCouponRulesForBag(@Param("keyword") String keyword, @Param("offset") int offset, @Param("pageSize") int pageSize);
}
