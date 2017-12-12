package com.yogu.services.order.resource.api;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;
import javax.validation.Valid;
import javax.ws.rs.BeanParam;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.yogu.commons.cache.redis.Redis;
import com.yogu.commons.utils.DateUtils;
import com.yogu.commons.utils.JsonUtils;
import com.yogu.commons.utils.ThreadLocalContext;
import com.yogu.commons.utils.VOUtil;
import com.yogu.commons.utils.WordCountUtils;
import com.yogu.core.web.ParameterUtil;
import com.yogu.core.web.RestResult;
import com.yogu.core.web.ResultCode;
import com.yogu.core.web.exception.ServiceException;
import com.yogu.remote.order.vo.AdminCouponListVO;
import com.yogu.remote.order.vo.CouponRuleForm;
import com.yogu.remote.order.vo.CouponRuleVO;
import com.yogu.services.order.coupon.dto.CouponRule;
import com.yogu.services.order.coupon.service.CouponRuleService;

/**
 * 优惠券的后台管理接口。 仅提供给后台管理系统调用
 * 
 * @author ten 2015/12/24.
 */
@Named
@Path("api")
@Singleton
@Produces("application/json;charset=UTF-8")
public class AdminCouponController {

	private static final Logger logger = LoggerFactory.getLogger(AdminCouponController.class);

	@Autowired
	private CouponRuleService couponRuleService;

	@Inject
	@Named("redis")
	private Redis redis;


	// 优惠券商家黑名单管理

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
	@GET
	@Path("coupon/admin/queryCouponRules")
	public RestResult<List<AdminCouponListVO>> queryCouponRules(@QueryParam("keyword")String keyword, @QueryParam("page")int page, @QueryParam("pageSize")int pageSize) {
		List<AdminCouponListVO> list = couponRuleService.queryCouponRules(keyword, page, pageSize);
		return new RestResult<>(list);
	}

	/**
	 * 读取优惠券规则的所有详细内容
	 * 
	 * @param couponRuleId 优惠券规则ID
	 * @return 返回优惠券规则的详细内容，失败 result.object 返回null
	 */
	@POST
	@Path("coupon/admin/getCouponRuleDetail")
	public RestResult<CouponRuleVO> getCouponRuleDetail(@QueryParam("couponRuleId")long couponRuleId) {
		CouponRule rule = couponRuleService.getById(couponRuleId);
		if(null == rule){
			return new RestResult<>(ResultCode.FAILURE, "没有找到优惠券规则数据");
		}
		
		return new RestResult<>(VOUtil.from(rule, CouponRuleVO.class));
	}

	/**
	 * 保存优惠券（新增 or 更新）
	 * 
	 * @param form 优惠券数据
	 * @return 返回 result.success=true表示成功, result.object=优惠券规则ID，失败抛出异常
	 * @author ten 2015/12/26
	 */
	@POST
	@Path("coupon/admin/saveCoupon.do")
	public RestResult<Long> saveCoupon(@Valid @BeanParam  CouponRuleForm form) {
		String ip = ThreadLocalContext.getThreadValue(ThreadLocalContext.REQ_CLIENT_IP);
		logger.info("api#coupon#saveCoupon | 保存优惠券 start | coupon: {}, ip: {}", JsonUtils.toJSONString(form), ip);
		validateCouponRuleForm(form);
		CouponRuleVO couponRuleVO = VOUtil.from(form, CouponRuleVO.class);
		long couponRuleId = couponRuleService.createCoupon(couponRuleVO, form.getCreateTotal());
		logger.info("api#coupon#saveCoupon | 保存优惠券成功 end | couponRuleId: {}", couponRuleId);
		return new RestResult<>(ResultCode.SUCCESS, "生成优惠券成功", couponRuleId);
	}

	private void validateCouponRuleForm(CouponRuleForm form) {
		ParameterUtil.assertNotBlank(form.getCouponName(), "优惠券名称不能为空");
		if (WordCountUtils.getWordCount(form.getCouponName()) > 24) {
			throw new ServiceException(ResultCode.PARAMETER_ERROR, "优惠券名称长度是12个中文或24个英文字符");
		}
//		ParameterUtil.assertGreaterThanZero(form.getAdminId(), "管理员ID不能为空");
		ParameterUtil.assertNotNull(form.getStartTimeStr(), "优惠券有效期的开始时间不能为空");
		ParameterUtil.assertNotNull(form.getEndTimeStr(), "优惠券有效期的结束时间不能为空");
		try {
			form.setStartTime(DateUtils.parseString(form.getStartTimeStr(), DateUtils.YYYY_MM_DD_HH_mm));
			form.setEndTime(DateUtils.parseString(form.getEndTimeStr(), DateUtils.YYYY_MM_DD_HH_mm));
		} catch (ParseException e) {
			throw new ServiceException(ResultCode.PARAMETER_ERROR, "时间格式错误");
		}
		ParameterUtil.assertTrue(form.getEndTime().after(form.getStartTime()), "优惠券有效期的结束时间要在开始时间之前");
		ParameterUtil.assertTrue(form.getEndTime().getTime() > System.currentTimeMillis(), "优惠券有效期的结束时间要在当前时间之后");
		ParameterUtil.assertGreaterThanOrEqual(form.getCreateTotal(), 1, "优惠券的发放数量要大于0");
		ParameterUtil.assertTrue(form.getCreateTotal() < 1000000, "优惠券的发放数量不能超过100万");
		ParameterUtil.assertNotBlank(form.getRegExpression(), "优惠券的面值不能为空");
		ParameterUtil.assertTrue(NumberUtils.isDigits(form.getRegExpression()), "优惠券的面值请填写正整数");
		ParameterUtil.assertTrue(form.getEnoughMoney() > 0 && form.getEnoughMoney() <= 99999900, "可用订单金额在1~999999元之间");
		ParameterUtil.assertMaxLength(form.getDescription(), 1000, "优惠券描述不能超过1000个字符 ");
	}

	private List<Long> toList(String str) {
		String[] array = StringUtils.split(str.trim(), ',');
		List<Long> list = new ArrayList<>(array.length);
		for (String s : array) {
			list.add(Long.parseLong(s));
		}
		return list;
	}

	/**
	 * 设置优惠券停止兑换，已经领取的优惠券可以使用，未领取的优惠券不能兑换。
	 * 
	 * @param adminId 管理员ID
	 * @param couponRuleId 优惠券规则ID
	 * @return result.success=true为成功
	 * @author ten 2016/1/4
	 */
	@POST
	@Path("coupon/admin/stopCouponExchange.do")
	public RestResult<Object> stopCouponExchange(@FormParam("adminId")long adminId, @FormParam("couponRuleId")long couponRuleId) {
		String ip = ThreadLocalContext.getThreadValue(ThreadLocalContext.REQ_CLIENT_IP);
		logger.info("admin#coupon#stopCouponExchange | 设置优惠券停止兑换 | adminId: {}, couponRuleId: {}, ip: {}", adminId, couponRuleId, ip);
		ParameterUtil.assertGreaterThanZero(adminId, "管理员ID不能为空");
		ParameterUtil.assertGreaterThanZero(couponRuleId, "优惠券规则ID不能为空");
		couponRuleService.stopExchange(couponRuleId);
		logger.info("admin#coupon#stopCouponExchange | 设置优惠券停止兑换success | adminId: {}, couponRuleId: {}", adminId, couponRuleId);
		return new RestResult<>(ResultCode.SUCCESS, "操作成功");
	}

}
