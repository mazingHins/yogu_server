package com.yogu.remote.order;

import java.util.List;
import java.util.Map;

import javax.inject.Named;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.type.TypeReference;
import com.yogu.CommonConstants;
import com.yogu.commons.utils.HttpClientUtils;
import com.yogu.commons.utils.JsonUtils;
import com.yogu.core.web.RestResult;

/**
 * 通过远程接口获取订单统计相关的数据
 *
 * @date 2016年7月14日 下午3:29:34
 * @author hins
 */
@Named
public class OrderStatisticsRemoteService {
	
	private static final Logger logger = LoggerFactory.getLogger(OrderStatisticsRemoteService.class);

	/**
	 * 获取米星付订单销售排行榜，仅统计已完成、已评论的订单金额<br>
	 * 此方法暂时仅提供给admin管理调用，调用失败，返回null<br>
	 * 暂时只获取2016-01-01之后的数据
	 * 
	 * @param adminId - 管理员id
	 * @author hins
	 * @date 2016年7月14日 下午3:31:24
	 * @return result.object=返回统计的数据:
	 *         month,total,num,avg,按month从小到达排列，total、avg(=total/num)的单位是元
	 */
	public RestResult<List<Map<String, Object>>> adminStatMazingPayOrderByMonth(long adminId){
		
		logger.info("admin#remote#statistics#adminStatMazingPayOrderByMonth | 准备远程查询每个月的销售额 | uid: {}", adminId);
		
		try{
			
			String json = HttpClientUtils.doGet(CommonConstants.ORDER_DOMAIN + "/api/order/mazingPay/statOrderByMonth", 5000);
			RestResult<List<Map<String, Object>>> result = JsonUtils.parseObject(json, new TypeReference<RestResult<List<Map<String, Object>>>>() {
			});
			
			return result;
			
		}catch(Exception e){
			logger.error("admin#remote#statistics#adminStatMazingPayOrderByMonth | 远程查询每个月的销售额Error | adminId: {}, message: {}", adminId, e.getMessage(), e);
		}
		
		return null;
		
	}

	/**
	 * 获取米星付订单每周销售数据，仅统计已完成、已评论的订单金额<br>
	 * 此方法暂时仅提供给admin管理调用，调用失败，返回null<br>
	 * 暂时只获取最近9周的数据！！
	 * 
	 * @param adminId - 管理员id
	 * @author hins
	 * @date 2016年7月14日 下午3:36:00
	 * @return RestResult<List<Map<String,Object>>>
	 */
	public RestResult<List<Map<String, Object>>> adminStatMazingPayOrderByWeek(long adminId) {

		logger.info("admin#remote#statistics#adminStatMazingPayOrderByWeek | 准备远程查询每个月的销售额 | uid: {}", adminId);

		try {

			String json = HttpClientUtils.doGet(CommonConstants.ORDER_DOMAIN + "/api/order/mazingPay/statOrderByWeek", 5000);
			RestResult<List<Map<String, Object>>> result = JsonUtils.parseObject(json, new TypeReference<RestResult<List<Map<String, Object>>>>() {
			});

			return result;

		} catch (Exception e) {
			logger.error("admin#remote#statistics#adminStatMazingPayOrderByWeek | 远程查询每个月的销售额Error | adminId: {}, message: {}", adminId, e.getMessage(), e);
		}

		return null;

	}

	/**
	 * 获取米星付总的优惠券数据<br>
	 * 区分承担方返回<br>
	 * 此方法暂时仅提供给admin管理调用，调用失败，返回null<br>
	 * 暂时只获取2016-01-01之后的数据
	 * 
	 * @param adminId - 管理员id
	 * @author hins
	 * @date 2016年7月14日 下午3:38:27
	 * @return result.object=返回统计的数据:coupon_bear_type, total, discount, num,
	 *         按coupon_bear_type从小到大排列
	 */
	public RestResult<List<Map<String, Object>>> adminStatMazingPayTotalCoupon(long adminId) {

		logger.info("admin#remote#statistics#adminStatMazingPayTotalCoupon | 准备远程查询每个月的销售额 | uid: {}", adminId);

		try {

			String json = HttpClientUtils.doGet(CommonConstants.ORDER_DOMAIN + "/api/order/mazingPay/statTotalCoupon", 5000);
			RestResult<List<Map<String, Object>>> result = JsonUtils.parseObject(json, new TypeReference<RestResult<List<Map<String, Object>>>>() {
			});

			return result;

		} catch (Exception e) {
			logger.error("admin#remote#statistics#adminStatMazingPayTotalCoupon | 远程查询每个月的销售额Error | adminId: {}, message: {}", adminId, e.getMessage(), e);
		}

		return null;

	}

	/**
	 * 获取米星付每月优惠券数据<br>
	 * 区分承担方返回<br>
	 * 此方法暂时仅提供给admin管理调用，调用失败，返回null<br>
	 * 暂时只获取2016-01-01之后的数据，且只返回最近9个月的
	 * 
	 * @param adminId - 管理员id
	 * 
	 * @author hins
	 * @date 2016年7月14日 下午3:40:08
	 * @return result.object=返回统计的数据:month, coupon_bear_type, total, discount,
	 *         num，按month从小到大排列
	 */
	public RestResult<List<Map<String, Object>>> adminStatMazingPayCouponByMonth(long adminId) {

		logger.info("admin#remote#statistics#adminStatMazingPayCouponByMonth | 准备远程查询每个月的销售额 | uid: {}", adminId);

		try {

			String json = HttpClientUtils.doGet(CommonConstants.ORDER_DOMAIN + "/api/order/mazingPay/statCouponByMonth", 5000);
			RestResult<List<Map<String, Object>>> result = JsonUtils.parseObject(json, new TypeReference<RestResult<List<Map<String, Object>>>>() {
			});

			return result;

		} catch (Exception e) {
			logger.error("admin#remote#statistics#adminStatMazingPayCouponByMonth | 远程查询每个月的销售额Error | adminId: {}, message: {}", adminId, e.getMessage(), e);
		}

		return null;

	}
	
	/**
	 * 获取线上支付订单销售排行榜，仅统计已完成、已评论的订单金额<br>
	 * 此方法暂时仅提供给admin管理调用，调用失败，返回null<br>
	 * 暂时只获取2016-01-01之后的数据
	 * 
	 * @param adminId - 管理员id
	 * @author hins
	 * @date 2016年7月14日 下午3:31:24
	 * @return result.object=返回统计的数据:
	 *         month,total,num,avg,按month从小到达排列，total、avg(=total/num)的单位是元
	 */
	public RestResult<List<Map<String, Object>>> adminStatOrderByMonth(long adminId){
		
		logger.info("admin#remote#statistics#adminStatOrderByMonth | 准备远程查询每个月的销售额 | uid: {}", adminId);
		
		try{
			
			String json = HttpClientUtils.doGet(CommonConstants.ORDER_DOMAIN + "/api/order/statOrderByMonth", 5000);
			RestResult<List<Map<String, Object>>> result = JsonUtils.parseObject(json, new TypeReference<RestResult<List<Map<String, Object>>>>() {
			});
			
			return result;
			
		}catch(Exception e){
			logger.error("admin#remote#statistics#adminStatOrderByMonth | 远程查询每个月的销售额Error | adminId: {}, message: {}", adminId, e.getMessage(), e);
		}
		
		return null;
		
	}

	/**
	 * 获取线上支付订单每周销售数据，仅统计已完成、已评论的订单金额<br>
	 * 此方法暂时仅提供给admin管理调用，调用失败，返回null<br>
	 * 暂时只获取最近9周的数据！！
	 * 
	 * @param adminId - 管理员id
	 * @author hins
	 * @date 2016年7月14日 下午3:36:00
	 * @return RestResult<List<Map<String,Object>>>
	 */
	public RestResult<List<Map<String, Object>>> adminStatOrderByWeek(long adminId) {

		logger.info("admin#remote#statistics#adminStatOrderByWeek | 准备远程查询每个月的销售额 | uid: {}", adminId);

		try {

			String json = HttpClientUtils.doGet(CommonConstants.ORDER_DOMAIN + "/api/order/statOrderByWeek", 5000);
			RestResult<List<Map<String, Object>>> result = JsonUtils.parseObject(json, new TypeReference<RestResult<List<Map<String, Object>>>>() {
			});

			return result;

		} catch (Exception e) {
			logger.error("admin#remote#statistics#adminStatOrderByWeek | 远程查询每个月的销售额Error | adminId: {}, message: {}", adminId, e.getMessage(), e);
		}

		return null;

	}

	/**
	 * 获取线上支付总的优惠券数据<br>
	 * 区分承担方返回<br>
	 * 此方法暂时仅提供给admin管理调用，调用失败，返回null<br>
	 * 暂时只获取2016-01-01之后的数据
	 * 
	 * @param adminId - 管理员id
	 * @author hins
	 * @date 2016年7月14日 下午3:38:27
	 * @return result.object=返回统计的数据:coupon_bear_type, total, discount, num,
	 *         按coupon_bear_type从小到大排列
	 */
	public RestResult<List<Map<String, Object>>> adminStatTotalCoupon(long adminId) {

		logger.info("admin#remote#statistics#adminStatTotalCoupon | 准备远程查询每个月的销售额 | uid: {}", adminId);

		try {

			String json = HttpClientUtils.doGet(CommonConstants.ORDER_DOMAIN + "/api/order/statTotalCoupon", 5000);
			RestResult<List<Map<String, Object>>> result = JsonUtils.parseObject(json, new TypeReference<RestResult<List<Map<String, Object>>>>() {
			});

			return result;

		} catch (Exception e) {
			logger.error("admin#remote#statistics#adminStatTotalCoupon | 远程查询每个月的销售额Error | adminId: {}, message: {}", adminId, e.getMessage(), e);
		}

		return null;

	}

	/**
	 * 获取线上支付每月优惠券数据<br>
	 * 区分承担方返回<br>
	 * 此方法暂时仅提供给admin管理调用，调用失败，返回null<br>
	 * 暂时只获取2016-01-01之后的数据，且只返回最近9个月的
	 * 
	 * @param adminId - 管理员id
	 * 
	 * @author hins
	 * @date 2016年7月14日 下午3:40:08
	 * @return result.object=返回统计的数据:month, coupon_bear_type, total, discount,
	 *         num，按month从小到大排列
	 */
	public RestResult<List<Map<String, Object>>> adminStatCouponByMonth(long adminId) {

		logger.info("admin#remote#statistics#adminStatCouponByMonth | 准备远程查询每个月的销售额 | uid: {}", adminId);

		try {

			String json = HttpClientUtils.doGet(CommonConstants.ORDER_DOMAIN + "/api/order/statCouponByMonth", 5000);
			RestResult<List<Map<String, Object>>> result = JsonUtils.parseObject(json, new TypeReference<RestResult<List<Map<String, Object>>>>() {
			});

			return result;

		} catch (Exception e) {
			logger.error("admin#remote#statistics#adminStatCouponByMonth | 远程查询每个月的销售额Error | adminId: {}, message: {}", adminId, e.getMessage(), e);
		}

		return null;

	}

}
