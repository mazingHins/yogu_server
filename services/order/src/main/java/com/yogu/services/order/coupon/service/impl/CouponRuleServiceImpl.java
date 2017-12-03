package com.yogu.services.order.coupon.service.impl;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.yogu.commons.utils.CollectionUtils;
import com.yogu.commons.utils.PageUtils;
import com.yogu.commons.utils.VOUtil;
import com.yogu.commons.utils.WordCountUtils;
import com.yogu.core.constant.CouponTypeConstants;
import com.yogu.core.enums.BooleanConstants;
import com.yogu.core.web.ParameterUtil;
import com.yogu.core.web.ResultCode;
import com.yogu.core.web.exception.ServiceException;
import com.yogu.remote.config.id.IdGenRemoteService;
import com.yogu.remote.order.vo.AdminCouponListVO;
import com.yogu.remote.order.vo.CouponRuleVO;
import com.yogu.services.order.coupon.dao.CouponRuleDao;
import com.yogu.services.order.coupon.dto.CouponRule;
import com.yogu.services.order.coupon.entry.CouponRulePO;
import com.yogu.services.order.coupon.service.CouponRuleService;
import com.yogu.services.order.coupon.service.CouponService;

/**
 * CouponRuleService 的实现类
 * 
 * @author Mazing 2015-12-23
 */
@Service
public class CouponRuleServiceImpl implements CouponRuleService {

	private static final Logger logger = LoggerFactory.getLogger(CouponRuleServiceImpl.class);
	@Autowired
	private CouponRuleDao dao;

	@Autowired
	private IdGenRemoteService idGenRemoteService;

	@Autowired
	private CouponService couponService;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public CouponRule getById(long couponRuleId) {
		CouponRulePO po = dao.getById(couponRuleId);
		return po2dto(po);
	}

	// ####
	// ## private func
	// ####

	public CouponRule po2dto(CouponRulePO po) {
		if (null == po)
			return null;
		return VOUtil.from(po, CouponRule.class);
	}

	public CouponRulePO dto2po(CouponRule dto) {
		if (null == dto)
			return null;
		return VOUtil.from(dto, CouponRulePO.class);
	}


	@Override
	public List<AdminCouponListVO> queryCouponRules(final String keyword, int page, int pageSize) {
		if (pageSize < 1)
			pageSize = 1;
		int offset = PageUtils.offset(page, pageSize);
		String tmp = formatQueryKeyword(keyword);
		List<CouponRulePO> list = dao.queryCouponRules(tmp, offset, pageSize);
		return toAdminCouponListVO(list);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public long createCoupon(CouponRuleVO couponRuleVO, int createTotal) {
		// 生成优惠券
		// 2015/12/27 ten

		// 1. 校验参数
		validateCouponRuleForm(couponRuleVO, createTotal);

		// 2. 保存 CouponRule
		CouponRulePO rulePO = toCouponRulePO(couponRuleVO);
		dao.save(rulePO);

		long couponRuleId = rulePO.getCouponRuleId();
		couponRuleVO.setCouponRuleId(couponRuleId);
		
		// 生成优惠券列表
		
		return rulePO.getCouponRuleId();
	}


	/**
	 * 转换成 CouponRulePO
	 * 
	 * @param couponRuleVO
	 */
	private CouponRulePO toCouponRulePO(CouponRuleVO couponRuleVO) {
		Date now = new Date();
		long couponRuleId = idGenRemoteService.getNextCouponPublicId();
		CouponRulePO rulePO = VOUtil.from(couponRuleVO, CouponRulePO.class);
		rulePO.setCouponRuleId(couponRuleId);
		rulePO.setCreateTime(now);
		rulePO.setModifyTime(now);
		rulePO.setIsEnable(BooleanConstants.TRUE);
		rulePO.setIsStop(BooleanConstants.FALSE);
		return rulePO;
	}

	/**
	 * 校验参数
	 * 
	 * @param couponRuleVO 优惠券数据
	 * @param createTotal 发放总量
	 */
	private void validateCouponRuleForm(CouponRuleVO couponRuleVO, int createTotal) {
		ParameterUtil.assertNotBlank(couponRuleVO.getCouponName(), "优惠券名称不能为空");
		if (WordCountUtils.getWordCount(couponRuleVO.getCouponName()) > 20) {
			throw new ServiceException(ResultCode.PARAMETER_ERROR, "优惠券名称长度是10个中文或20个英文字符");
		}
		ParameterUtil.assertNotNull(couponRuleVO.getStartTime(), "优惠券有效期的开始时间不能为空");
		ParameterUtil.assertNotNull(couponRuleVO.getEndTime(), "优惠券有效期的结束时间不能为空");
		ParameterUtil.assertTrue(couponRuleVO.getEndTime().after(couponRuleVO.getStartTime()), "优惠券有效期的结束时间要在开始时间之前");
		ParameterUtil.assertTrue(couponRuleVO.getEndTime().getTime() > System.currentTimeMillis(), "优惠券有效期的结束时间要在当前时间之后");
		ParameterUtil.assertGreaterThanOrEqual(createTotal, 1, "优惠券的发放数量要大于0");
		ParameterUtil.assertTrue(createTotal < 1000000, "优惠券的发放数量不能超过100万");
		ParameterUtil.assertNotBlank(couponRuleVO.getRegExpression(), "优惠券的面值不能为空");
		ParameterUtil.assertTrue(NumberUtils.isDigits(couponRuleVO.getRegExpression()), "优惠券的面值请填写正整数");
		ParameterUtil.assertTrue(couponRuleVO.getEnoughMoney() > 0 && couponRuleVO.getEnoughMoney() <= 99999900, "可用订单金额在1~999999元之间");
		ParameterUtil.assertMaxLength(couponRuleVO.getDescription(), 1000, "优惠券描述不能超过1000个字符 ");
		ParameterUtil.assertGreaterThanOrEqual(couponRuleVO.getDayUseTotal(), 0, "每天限用次数不能小于0");
		ParameterUtil.assertGreaterThanOrEqual(couponRuleVO.getGainTotal(), 0, "每人限领数量不能小于0");

		if (couponRuleVO.getCouponType() == CouponTypeConstants.DISCOUNT_COUPON) {
			ParameterUtil.assertGreaterThanOrEqual(couponRuleVO.getMostOffer(), 1, "折扣券的最高优惠金额必须大于0");

			if (couponRuleVO.getMostOffer() > 150000)
				throw new ServiceException(ResultCode.PARAMETER_ERROR, "折扣券最高优惠金额不能超过1500元");
		}

	}


	@Override
	public void disableCouponRule(long couponRuleId) {
		CouponRulePO po = dao.getById(couponRuleId);
		ParameterUtil.assertNotNull(po, "优惠券规则不存在");
		logger.info("couponRuleService#disableCouponRule | 设置优惠券失效 | couponRuleId: {}", couponRuleId);
		if (po.getIsEnable() == BooleanConstants.TRUE) {
			int rows = dao.updateEnable(couponRuleId, BooleanConstants.FALSE, po.getIsEnable());
			if (rows <= 0)
				throw new ServiceException(ResultCode.UNKNOWN_ERROR, "设置优惠券失效失败，请联系管理员");

			// 所有状态为0和1的优惠券都置为状态4
			int row = couponService.disableCoupons(couponRuleId);
			if (row <= 0)
				throw new ServiceException(ResultCode.UNKNOWN_ERROR, "设置优惠券失效失败，请联系管理员");

			logger.info("couponRuleService#disableCouponRule | 设置优惠券失效成功 | couponRuleId: {}", couponRuleId);
		} else {
			logger.info("couponRuleService#disableCouponRule | 设置优惠券失效，优惠已经失效，不再设置 | couponRuleId: {}", couponRuleId);
		}
	}

	@Override
	public void stopExchange(long couponRuleId) {
		CouponRulePO po = dao.getById(couponRuleId);
		ParameterUtil.assertNotNull(po, "优惠券规则不存在");
		logger.info("couponRuleService#disableCouponRule | 设置优惠券停止兑换 | couponRuleId: {}", couponRuleId);
		if (po.getIsStop() == BooleanConstants.FALSE) {
			int rows = dao.updateStop(couponRuleId, BooleanConstants.TRUE, po.getIsStop());
			if (rows <= 0)
				throw new ServiceException(ResultCode.UNKNOWN_ERROR, "设置优惠券停止兑换失败，请联系管理员");

			logger.info("couponRuleService#disableCouponRule | 设置优惠券停止兑换成功 | couponRuleId: {}", couponRuleId);
		} else {
			logger.info("couponRuleService#disableCouponRule | 设置优惠券停止兑换，优惠已经停止兑换，不再设置 | couponRuleId: {}", couponRuleId);
		}
	}

	/**
	 * 处理一下查询的关键字
	 * 
	 * @param keyword
	 * @return
	 * @author ten 2016/1/26
	 */
	private String formatQueryKeyword(String keyword) {
		String tmp = keyword;
		if (StringUtils.isBlank(keyword)) {
			tmp = null;
		} else {
			// 注：由于记录数不多，并且用户比较少，使用 like 不影响性能
			tmp = "%" + tmp.trim() + "%";
		}
		return tmp;
	}

	/**
	 * 把 CouponRulePO 列表转换为 AdminCouponListVO 列表
	 * 
	 * @param list
	 * @return
	 * @author ten 2016/1/26
	 */
	private List<AdminCouponListVO> toAdminCouponListVO(List<CouponRulePO> list) {
		List<AdminCouponListVO> result = new ArrayList<>(list.size());
		for (CouponRulePO po : list) {
			AdminCouponListVO vo = VOUtil.from(po, AdminCouponListVO.class);

			int createTotal = 0, useTotal = 0, gainTotal = 0;

			vo.setCreateTotal(createTotal);
			vo.setUseTotal(useTotal);
			vo.setGainTotal(gainTotal);
			result.add(vo);

		}
		return result;
	}



}
