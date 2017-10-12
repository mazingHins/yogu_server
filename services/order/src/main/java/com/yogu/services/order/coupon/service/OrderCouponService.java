package com.yogu.services.order.coupon.service;

import java.util.List;

import com.yogu.services.order.resource.vo.SettleCouponVO;
import com.yogu.services.order.resource.vo.UserCouponVO;

/**
 * 订单优惠券相关业务方法
 * 
 * @author Hins
 * @date 2015年12月23日 下午12:08:12
 */
public interface OrderCouponService {
	
	/**
	 * 获取用户的未使用的优惠券列表，按照创建时间顺序排序<br>
	 * 支持分页
	 * 
	 * @author Hins
	 * @date 2015年12月23日 下午5:02:07
	 * 
	 * @param uid - 用户ID
	 * @param pageIndex - 页码（第几页）
	 * @param pageSize - 每页大小
	 * @return 优惠券列表，若无，返回empty
	 */
	List<UserCouponVO> listUserCoupon(long uid, int pageIndex, int pageSize);
	
	/**
	 * 获取用户在下单前（结算界面），未使用的优惠券列表，不分页<br>
	 * 未使用的优惠券包括（满足使用条件，即可使用。没满足使用条件，暂不可使用）<br>
	 * 默认选择优惠金额最大的（前提条件：可使用）。结果按：可使用>到期时间顺序（最近过期的越前）<br>
	 * 一次性最多返回30条优惠券
	 * 
	 * @param uid - 用户id
	 * @param storeId - 结算的餐厅id
	 * @param totalFee - 订单金额
	 * @param receivePhone - 收货人号码，可空(则不查询)
	 * @param dishKeys - 美食key列表，多个用英文逗号分隔，可null
	 * 
	 * @author hins
	 * @date 2016年7月28日 上午11:58:11
	 * @return 可用优惠券列表，若无，返回empty
	 */
	List<SettleCouponVO> listSettleCoupon(long uid, long totalFee);
	
	
}
