package com.yogu.services.backend.admin.resources.coupon;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yogu.commons.utils.DateUtils;
import com.yogu.commons.utils.JsonUtils;
import com.yogu.commons.utils.resource.Menu;
import com.yogu.commons.utils.resource.MenuResource;
import com.yogu.core.constant.CouponTypeConstants;
import com.yogu.core.enums.BooleanConstants;
import com.yogu.core.web.ParameterUtil;
import com.yogu.core.web.RestResult;
import com.yogu.core.web.ResultCode;
import com.yogu.core.web.exception.ServiceException;
import com.yogu.remote.order.CouponRemoteService;
import com.yogu.remote.order.vo.UpdCouponRuleForm;
import com.yogu.services.backend.admin.annotation.log.AdminLog;
import com.yogu.services.backend.admin.context.AdminContext;

/**
 * 编辑优惠券
 * 
 * @author ten 2015/12/25.
 */
@Controller
@RequestMapping("/admin/coupon/")
@Menu(name = "新增/编辑优惠券", parent = "优惠券管理", sequence = 2200000)
public class EditCouponResource {

	private static final Logger logger = LoggerFactory.getLogger(EditCouponResource.class);

	@Autowired
	private CouponRemoteService couponRemoteService;

	/**
	 * 编辑优惠券主页，xhtm 仅用于展示页面，ajax 调用 接口获取参数
	 * 
	 * @return
	 */
	@RequestMapping("editCoupon.xhtm")
	@MenuResource("编辑优惠券主页")
	public String index() {
		return ("/coupon/edit_coupon");
	}
	
	private long[] toArray(List<Number> list) {
		long[] array = new long[list.size()];
		int index = 0;
		for (Number id : list) {
			array[index++] = id.longValue();
		}
		return array;
	}

	/**
	 * 保存优惠券，成功返回true
	 * 
	 * @param form 优惠券信息
	 * @return result.success = true 为成功
	 */
	@ResponseBody
	@RequestMapping(value = "saveCoupon.do", method = RequestMethod.POST)
	@MenuResource("保存优惠券")
	@AdminLog
	public RestResult<Integer> saveCoupon(@Valid @ModelAttribute UpdCouponRuleForm form) {
		logger.info("admin#coupon#saveCoupon | 保存优惠券 start | adminId: {}, coupon: {}", AdminContext.getAccountId(),
				JsonUtils.toJSONString(form));
		validCouponForm(form);
		long adminId = AdminContext.getAccountId();
		RestResult<Integer> restResult = couponRemoteService.adminSaveCoupon(form);
		logger.info("admin#coupon#saveCoupon | 保存优惠券 end | code: {}, message: {}, couponRuleId: {}", restResult.getCode(),
				restResult.getMessage(), restResult.getObject());
		return restResult;
	}


	/**
	 * 验证提交的数据是否正确
	 * 
	 * @param form 优惠券数据
	 * @return 如果成功，返回验证成功后的VO
	 */
	private void validCouponForm(UpdCouponRuleForm form) {
		if (!NumberUtils.isNumber(StringUtils.trimToEmpty(form.getEnoughMoneyStr()))) {
			throw new ServiceException(ResultCode.PARAMETER_ERROR, "可用订单金额必须是数字");
		}
		if (!NumberUtils.isNumber(StringUtils.trimToEmpty(form.getRegExpression()))) {
			throw new ServiceException(ResultCode.PARAMETER_ERROR, "面值必须是数字");
		}

		BigDecimal faceValue = new BigDecimal(form.getRegExpression().trim());
		BigDecimal enoughMoney = new BigDecimal(form.getEnoughMoneyStr().trim());
		BigDecimal mostOffer = new BigDecimal(form.getMostOffer());

		if (enoughMoney.compareTo(new BigDecimal("0.01")) < 0) {
			throw new ServiceException(ResultCode.PARAMETER_ERROR, "可用订单金额不能小于0.01元");
		}
		if (form.getCouponType() == CouponTypeConstants.CASH_COUPON) {
			if (faceValue.compareTo(new BigDecimal("0.01")) < 0 || faceValue.compareTo(new BigDecimal("99999")) > 0) {
				throw new ServiceException(ResultCode.PARAMETER_ERROR, "面值的取值范围是0.01~99999元");
			}
			// if (enoughMoney.compareTo(faceValue) < 0) {
			// throw new ServiceException(ResultCode.PARAMETER_ERROR, "可用订单金额不能小于面值");
			// }
		} else if (form.getCouponType() == CouponTypeConstants.DISCOUNT_COUPON) {
			if (faceValue.compareTo(new BigDecimal(10)) < 0 || faceValue.compareTo(new BigDecimal(99)) > 0
					|| !NumberUtils.isDigits(form.getRegExpression())) {
				throw new ServiceException(ResultCode.PARAMETER_ERROR, "折扣的取值范围是10~99的整数，比如75表示七五折");
			}
			// 限制最高优惠不能超过1500元
			if (mostOffer.compareTo(new BigDecimal(1)) < 0 || faceValue.compareTo(new BigDecimal("150000")) > 0) {
				throw new ServiceException(ResultCode.PARAMETER_ERROR, "折扣券必须设置最高优惠金额， 且金额为1~150000的整数");
			}

		}

		String[] array = form.getEffectiveTimeRange().split(" - ");
		if (StringUtils.isBlank(form.getEffectiveTimeRange()) || array.length != 2) {
			throw new ServiceException(ResultCode.PARAMETER_ERROR, "请选择一个生效时间，请勿手动修改");
		}
		SimpleDateFormat sdf = new SimpleDateFormat(DateUtils.YYYY_MM_DD_HH_mm);
		Date startTime = null, endTime = null;
		try {
			startTime = sdf.parse(array[0]);
			endTime = sdf.parse(array[1]);

			if (endTime.getTime() < System.currentTimeMillis()) {
				throw new ServiceException(ResultCode.PARAMETER_ERROR, "结束时间必须在当前时间之后");
			}
		} catch (ParseException e) {
			logger.error("admin#coupon#saveCoupon | 解析优惠券生效时间出错 | effectiveTimeRange: {}", form.getEffectiveTimeRange(), e);
			throw new ServiceException(ResultCode.PARAMETER_ERROR, "请选择一个生效时间，请勿手动修改");
		}

		if (endTime.before(startTime)) {
			Date tmp = startTime;
			startTime = endTime;
			endTime = tmp;
		}

		if (StringUtils.isNotBlank(form.getDescription())) {
			form.setDescription(form.getDescription().trim());
			ParameterUtil.assertMaxLength(form.getDescription(), 1000, "优惠券的说明不能超过1000个字符");
		}
		ParameterUtil.assertGreaterThanZero(form.getGainTotal(), "每人限领数量必须大于0");
		form.setStartTime(array[0]);
		form.setEndTime(array[1]);
//		CouponRuleForm result = VOUtil.from(form, CouponRuleForm.class);
//		result.setStartTime(startTime);
//		result.setEndTime(endTime);
//		// 设置订单金额，元 -> 分，整数
		form.setEnoughMoney(enoughMoney.multiply(new BigDecimal(100)).intValue());
//		return result;
	}
	
	/**
	 * 读取优惠券规则的所有详细内容
	 * 
	 * @param couponRuleId 优惠券规则ID
	 * @param display 是否只用于显示，如果为true，不判断优惠券的规则
	 * @return 返回优惠券规则的详细内容
	 */
	@ResponseBody
	@RequestMapping("getCouponRuleDetail")
	@MenuResource("查询优惠券规则详细内容")
	public RestResult<Map<String, Object>> getCouponRuleDetail(long couponRuleId,
			@RequestParam(value = "display", defaultValue = "false", required = false) boolean display) {
		RestResult<Map<String, Object>> result = couponRemoteService.adminGetCouponRuleDetail(couponRuleId);
		if (!result.isSuccess()) {
			return result;
		}
		Map<String, Object> couponDetail = result.getObject();
		if (couponDetail == null || couponDetail.isEmpty()) {
			return new RestResult<>(ResultCode.REJECTED_OPERATION, "找不到优惠券，请重试或联系管理员");
		}
		// 判断是否已经过期
		if (display == false) {
			Long startTime = (Long) couponDetail.get("startTime");
			Long endTime = (Long) couponDetail.get("endTime");
			Number isEnable = (Number) couponDetail.get("isEnable");
			Number isStop = (Number) couponDetail.get("isStop");
			if (startTime == null || endTime == null || isEnable == null || isStop == null) {
				logger.error("admin#coupon#getCouponRuleDetail | 数据错误 | startTime{}, endTime: {}, isEnable: {}, isStop: {}", startTime,
						endTime, isEnable, isStop);
				return new RestResult<>(ResultCode.REJECTED_OPERATION, "优惠券数据错误，请重试或联系管理员");
			}
			if (isEnable.intValue() != BooleanConstants.TRUE) {
				return new RestResult<>(ResultCode.REJECTED_OPERATION, "优惠券已经废弃，不能编辑");
			}
			long now = System.currentTimeMillis();
			if (now >= startTime) {
				return new RestResult<>(ResultCode.REJECTED_OPERATION, "优惠券有效期已经开始，不能再编辑");
			}
			if (isStop.intValue() == BooleanConstants.TRUE) {
				return new RestResult<>(ResultCode.REJECTED_OPERATION, "优惠券已经设置了停止发放，不能被编辑，请废弃当前优惠券，然后生成新的。");
			}
		}

		return new RestResult<>(couponDetail);
	}

	private List<Long> toList(String str) {
		String[] array = StringUtils.split(str.trim(), ',');
		List<Long> list = new ArrayList<>(array.length);
		for (String s : array) {
			list.add(Long.parseLong(s));
		}
		return list;
	}

}
