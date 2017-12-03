package com.yogu.remote.order;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Named;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.type.TypeReference;
import com.yogu.CommonConstants;
import com.yogu.commons.utils.DateUtils;
import com.yogu.commons.utils.HttpClientUtils;
import com.yogu.commons.utils.JsonUtils;
import com.yogu.core.web.RestResult;
import com.yogu.core.web.ResultCode;
import com.yogu.core.web.encrypt.StaticKeyHelper;
import com.yogu.core.web.exception.ServiceException;
import com.yogu.remote.order.vo.AdminCouponListVO;
import com.yogu.remote.order.vo.CouponRuleForm;
import com.yogu.remote.order.vo.CouponVO;

/**
 * 优惠券系统 对内部系统提供的远程service接口
 * 
 * @author sky 2015/12/23
 *
 */
@Named
public class CouponRemoteService {
	private static final Logger logger = LoggerFactory.getLogger(CouponRemoteService.class);
	// 访问接口 timeout 时间（ms）
	private static final int API_DEFAULT_TIMEOUT = 5000;


	/**
	 * 优惠券兑换
	 * 
	 * @param uid 兑换的用户id
	 * @param couponCode 使用兑换的券码
	 * @param phone 兑换者手机号码(密文)
	 * @return 返回兑换的结果信息,成功返回success=1, msg=兑换成功; 失败返回success=0,msg='兑换失败的原因'
	 * @author sky 2015-12-23
	 */
	public Map<String, Object> exchangeCoupon(long uid, String couponCode, String phone) {
		Map<String, String> params = new HashMap<>(3);

		params.put("uid", uid + "");
		params.put("couponCode", couponCode + "");
		params.put("phone", StaticKeyHelper.encryptKey(phone));

		try {
			String json = HttpClientUtils.doPost(CommonConstants.ORDER_DOMAIN + "/api/coupon/userCoupon/exchange", API_DEFAULT_TIMEOUT,
					params);
			RestResult<Map<String, Object>> result = JsonUtils.parseObject(json, new TypeReference<RestResult<Map<String, Object>>>() {
			});
			return result.getObject();
		} catch (Exception e) {
			logger.error("remote#coupon#exchangeCoupon | 远程调用 兑换优惠券接口发生错误 | uid: {}, couponCode: {}", uid, couponCode, e);
			throw new ServiceException(ResultCode.UNKNOWN_ERROR, e.getMessage());
		}
	}


	/**
	 * 读取券信息
	 * 
	 * @param couponId 券id
	 */
	public CouponVO getCoupon(long couponId) {
		try {
			String json = HttpClientUtils.doGet(CommonConstants.ORDER_DOMAIN + "/api/coupon/getCoupon?couponId=" + couponId);
			RestResult<CouponVO> result = JsonUtils.parseObject(json, new TypeReference<RestResult<CouponVO>>() {
			});
			return result.getObject();
		} catch (Exception e) {
			logger.error("remote#coupon#getCoupon | 远程获取优惠券信息发生错误 | couponId: {}", couponId, e);
			throw new ServiceException(ResultCode.UNKNOWN_ERROR, e.getMessage());
		}
	}


	/**
	 * 领取优惠券接口
	 * 
	 * @param uid 用户id, 用户没有注册时为null
	 * @param phoneSuffix 手机尾号
	 * @param couponRuleId 被领取的优惠券的规则id
	 * @param account 领取账户（手机号码or邮箱的md5）
	 * @return 如果领取成功, 返回success=1,msg="领取成功"; 领取不成功： success=0,msg="不能领取的原因"
	 * @author sky 2015-12-29
	 */
	public Map<String, Object> obtainCoupon(Long uid, String phoneSuffix, long couponRuleId, String account, String mobile) {
		Map<String, String> params = new HashMap<>(5);

		if (uid != null && uid > 0)
			params.put("uid", uid + "");

		params.put("phoneSuffix", phoneSuffix);
		params.put("couponRuleId", couponRuleId + "");
		params.put("account", account);
		params.put("mobile", mobile);

		try {
			String json = HttpClientUtils.doPost(CommonConstants.ORDER_DOMAIN + "/api/coupon/userCoupon/obtainCoupon",
					API_DEFAULT_TIMEOUT, params);
			RestResult<Map<String, Object>> result = JsonUtils.parseObject(json, new TypeReference<RestResult<Map<String, Object>>>() {
			});

			return result.getObject();

		} catch (Exception e) {
			logger.error("remote#coupon#obtainCoupon | 远程调用 领取优惠券 失败 | uid: {}, couponRuleId: {}, account: {}", uid, couponRuleId, account,
					e);
			throw new ServiceException(ResultCode.UNKNOWN_ERROR, e.getMessage());
		}

	}

	

	/**
	 * 查询优惠券规则列表，结果按创建时间倒序排列。
	 *
	 * 只允许管理系统调用！
	 *
	 * @param keyword 查询的关键字，可以为null，如果不为空，模糊查询优惠券名称
	 * @param page 第几页，最小值是1
	 * @param pageSize 每页多少条记录，最小值是1
	 * @return 返回符合条件的数据，如果没有数据，返回 empty list
	 * @author ten 2015/12/24
	 */
	public List<AdminCouponListVO> adminQueryCouponRules(String keyword, int page, int pageSize) {

		try {
			Map<String, String> params = new HashMap<>(4);
			if (StringUtils.isNotBlank(keyword))
				params.put("keyword", keyword);
			params.put("page", "" + page);
			params.put("pageSize", "" + pageSize);
			String json = HttpClientUtils.doGet(CommonConstants.ORDER_DOMAIN + "/api/coupon/admin/queryCouponRules", params);
			RestResult<List<AdminCouponListVO>> result = JsonUtils.parseObject(json,
					new TypeReference<RestResult<List<AdminCouponListVO>>>() {
					});
			if (result.isSuccess())
				return result.getObject();

			logger.error("remote#coupon#adminQueryCouponRules | 远程获取优惠券列表错误 | keyword: {}, code: {}, message: {}", keyword,
					result.getCode(), result.getMessage());
		} catch (Exception e) {
			logger.error("remote#coupon#adminQueryCouponRules | 远程获取优惠券列表错误 | keyword: {}", keyword, e);
			throw new ServiceException(ResultCode.UNKNOWN_ERROR, e.getMessage());
		}
		return Collections.emptyList();
	}

	/**
	 * 令优惠券失效，已经领取的优惠券不能使用，未领取的优惠券作废
	 * 
	 * @param adminId 管理员ID
	 * @param couponRuleId 优惠券规则ID
	 * @return result.success=true为成功
	 * @author ten 2016/1/4
	 */
	public RestResult<Object> adminDisableCouponRule(long adminId, long couponRuleId) {
		try {
			Map<String, String> params = new HashMap<>(2);
			params.put("couponRuleId", couponRuleId + "");
			params.put("adminId", adminId + "");
			String json = HttpClientUtils.doPost(CommonConstants.ORDER_DOMAIN + "/api/coupon/admin/disableCouponRule.do", params);
			RestResult<Object> result = JsonUtils.parseObject(json, new TypeReference<RestResult<Object>>() {
			});
			return result;

		} catch (Exception e) {
			logger.error("remote#coupon#adminDisableCouponRule | 令优惠券失效错误 | adminId: {}, couponRuleId: {}", adminId, couponRuleId, e);
			throw new ServiceException(ResultCode.UNKNOWN_ERROR, e.getMessage());
		}
	}


	/**
	 * 保存优惠券规则
	 * 
	 * @param rule 优惠券规则内容
	 * @return 返回 result.success=true 表示成功, result.object=优惠券规则ID
	 * @author ten 2015/12/26
	 */
	public RestResult<Integer> adminSaveCoupon(CouponRuleForm rule) {
		try {

			Map<String, String> params = toMapString(rule);

			String json = HttpClientUtils.doPost(CommonConstants.ORDER_DOMAIN + "/api/coupon/admin/saveCoupon.do", params);
			RestResult<Integer> result = JsonUtils.parseObject(json, new TypeReference<RestResult<Integer>>() {
			});
			return result;
		} catch (Exception e) {
			logger.error("remote#coupon#adminSaveCoupon | 保存优惠券规则错误 | ruleVO: {}", JsonUtils.toJSONString(rule), e);
			throw new ServiceException(ResultCode.UNKNOWN_ERROR, e.getMessage());
		}
	}

	/**
	 * 把 Object 转成 map，浅层转换
	 * 
	 * @param object
	 * @return
	 * @throws Exception
	 */
	private static Map<String, String> toMapString(Object object) throws Exception {
		Map<String, String> map = new HashMap<>();

		BeanInfo beanInfo = Introspector.getBeanInfo(object.getClass());
		PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
		SimpleDateFormat sdf = new SimpleDateFormat(DateUtils.YYYY_MM_DD_HH_mm_ss);
		for (PropertyDescriptor property : propertyDescriptors) {
			String key = property.getName();
			if (key.compareToIgnoreCase("class") == 0) {
				continue;
			}
			Method getter = property.getReadMethod();
			Object value = (getter != null ? getter.invoke(object) : null);
			if (value != null) {
				if (value instanceof Date) {
					map.put(key, sdf.format((Date) value));
				} else {
					map.put(key, String.valueOf(value));
				}
			}
		}

		return map;
	}

}
