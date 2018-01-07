package com.yogu.services.order.coupon.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.yogu.commons.utils.DateUtils;
import com.yogu.commons.utils.StringUtils;
import com.yogu.commons.utils.VOUtil;
import com.yogu.core.enums.order.CouponSelect;
import com.yogu.core.utils.ComputeUtils;
import com.yogu.core.web.ParameterUtil;
import com.yogu.language.OrderMessages;
import com.yogu.remote.user.dto.UserProfile;
import com.yogu.remote.user.provider.UserRemoteService;
import com.yogu.services.order.coupon.dto.Coupon;
import com.yogu.services.order.coupon.service.CouponService;
import com.yogu.services.order.coupon.service.OrderCouponService;
import com.yogu.services.order.resource.vo.coupon.CouponDescVO;
import com.yogu.services.order.resource.vo.coupon.UserCouponVO;
import com.yogu.services.order.resource.vo.order.SettleCouponVO;
import com.yogu.services.order.utils.CouponUtils;

/**
 * OrderCouponService的实现类
 * 
 * @author Hins
 * @date 2015年12月23日 下午5:04:42
 */
@Named
public class OrderCouponServiceImpl implements OrderCouponService {
	
	private static final Logger logger = LoggerFactory.getLogger(OrderCouponServiceImpl.class);
	
	@Inject
	private CouponService couponService;
	
	@Inject
	private UserRemoteService userRemoteService;

	@Override
	public List<UserCouponVO> listUserCoupon(long uid, int pageIndex, int pageSize) {
		UserProfile user = userRemoteService.getUserProfileByUid(uid);
		ParameterUtil.assertNotNull(user, OrderMessages.ORDER_COUPON_LISTUSERCOUPON_USER_NOTEXIST());
		
		// 1. 获取用户的优惠券列表
		List<Coupon> items = couponService.listUserCouponsByPage(uid, pageIndex, pageSize);
		if (items == null || items.isEmpty()) {
			return Collections.emptyList();
		}
		
		
		// 2. coupon域的优惠券转成UserCouponVO对象
		List<UserCouponVO> result = couponRemoteToUserCouponVO(user, items);

		return result;
		
	}
	
	/**
	 * 将从couponRemoteService方法查询到的优惠券列表，转成UserCouponVO对象
	 * 
	 * @param items - 优惠券列表
	 * @author hins
	 * @date 2016年6月15日 下午3:00:44
	 * @return 优惠券VO列表，若无，返回empty list
	 */
	private List<UserCouponVO> couponRemoteToUserCouponVO(UserProfile user, List<Coupon> items) {
		List<UserCouponVO> result = new ArrayList<UserCouponVO>(items.size());

		// 优惠券转成UserCouponVO对象，执行转换面值显示文本和优惠券说明列表CouponDesc
		for (Coupon coupon : items) {
			UserCouponVO vo = VOUtil.from(coupon, UserCouponVO.class);
			List<CouponDescVO> formatDescription = formatDescription(coupon);
			vo.setDescriptionItems(formatDescription);
			vo.setFaceValueText(CouponUtils.formatFaceValue(coupon.getEnoughMoney(), coupon.getCouponType(), coupon.getFaceValue()));
			vo.setShowStatus(CouponUtils.swiftCouponStatus(coupon.getStatus(), coupon.getStartTime(), coupon.getEndTime()));
			result.add(vo);
		}
		return result;
	}
	
	/**
	 * 根据优惠券满免金额、使用限制条件，适用对象，得到优惠券说明列表<br>
	 * 若满免金额>0，则将满免的CouponDescVO对象装载到结果<br>
	 * 若使用时的手机尾号限制不为空，则将限制条件的CouponDescVO对象装载到结果<br>
	 * 若包含的群组条件包括（餐厅/美食），则获取对应的适用对象ID列表（对象可以是餐厅/美食），调用远程方法批量查询 并将结果装载到CouponItemDescVO返回
	 * 
	 * @author Hins
	 * @date 2015年12月23日 下午5:49:08
	 * 
	 * @param rule - 使用规则VO
	 * @return 若无说明，返回empty
	 */
	private List<CouponDescVO> formatDescription(Coupon coupon) {
		List<CouponDescVO> result = new ArrayList<CouponDescVO>(4);

		// 加载顺序 适用对象>有效期>使用条件>限制手机号码

		// 2. 加载有效期，兼容只有开始时间/结束时间的情况
		formatDescriptionValidity(coupon, result);

		// 3. 判断是否有满免限制。 2016/7/18 add by hins 内容：满免限制后面新增最高优惠金额（就算没有满免，也加在此信息后）
		if (coupon.getEnoughMoney() > 0 ) {
			logger.info("order#coupon#formatDescription | 优惠券满足消费满免条件 |  enoughMoney: {}", coupon.getEnoughMoney());
			CouponDescVO desc = new CouponDescVO();

			String descStr = coupon.getEnoughMoney() > 0 ? OrderMessages.ORDER_COUPON_DESC_ORDER_MINIMUM(ComputeUtils.fenToYuanInt(coupon
					.getEnoughMoney())) : null;

			desc.setDesc(descStr);
			desc.setType(CouponDescVO.Type.DEFAULT.getValue());
			result.add(desc);
		}


		return result;
	}
	
	/**
	 * 加载优惠券有效期的说明<br>
	 * 支持：指定了开始时间和结束时间(也兼容都是同一天)，只指定了开始时间/结束时间，没有指定开始时间和结束时间（则有效期说明为kong）
	 * 
	 * @param coupon - 优惠券信息
	 * @param result - 优惠券说明列表
	 * @author hins
	 * @date 2016年7月18日 上午10:24:24
	 * @return void
	 */
	private void formatDescriptionValidity(Coupon coupon, List<CouponDescVO> result) {
		Date startTime = coupon.getStartTime(); // 有效期开始时间
		Date endTime = coupon.getEndTime(); // 有效期开始时间

		String startTimeStr = startTime == null ? "" : DateUtils.formatDate(startTime, DateUtils.YYYY_MM_DD);
		String endTimeStr = endTime == null ? "" : DateUtils.formatDate(endTime, DateUtils.YYYY_MM_DD);

		logger.info("order#coupon#formatDescription | 优惠券有效期条件 | couponId: {}, startTime: {}, endTime: {}", coupon.getCouponId(),
				startTimeStr, endTimeStr);

		if (startTime == null && endTime == null) {
			return;
		}

		String validityDesc = null; // 有效期的显示问题

		// 有指定开始时间和结束时间
		if (startTime != null && endTime != null) {

			// 兼容开始时间和结束时间是同一天
			validityDesc = startTimeStr.equals(endTimeStr) ? OrderMessages.ORDER_COUPON_DESC_VALID_ONEDAY_ONLY(startTimeStr)
					: OrderMessages.ORDER_COUPON_DESC_VALID_PERIOD() + " " + DateUtils.formatDate(startTime, DateUtils.YYYY_MM_DD) + "-"
							+ DateUtils.formatDate(endTime, DateUtils.YYYY_MM_DD);

		}

		// 有指定开始时间，没指定结束时间
		if (StringUtils.isBlank(validityDesc) && startTime != null) {
			validityDesc = OrderMessages.ORDER_COUPON_DESC_VALID_PERIOD_TO_FOREVER(DateUtils.formatDate(startTime, DateUtils.YYYY_MM_DD));
		}

		// 有指定结束时间，没指定开始时间
		if (StringUtils.isBlank(validityDesc) && endTime != null) {
			validityDesc = OrderMessages.ORDER_COUPON_DESC_VALID_NOW_TO_PERIOD(DateUtils.formatDate(endTime, DateUtils.YYYY_MM_DD));
		}

		if (StringUtils.isNotBlank(validityDesc)) {
			CouponDescVO desc = new CouponDescVO();
			desc.setDesc(validityDesc);
			desc.setType(CouponDescVO.Type.DEFAULT.getValue());
			result.add(desc);
		}
	}

	@Override
	public List<SettleCouponVO> listSettleCoupon(long uid, long totalFee) {
		logger.info("order#service#listSettleCoupon | 获取结算时，用户没使用（可用、没满足使用条件）的的优惠券列表start | uid: {}, totalFee: {}", uid, totalFee);
		ParameterUtil.assertGreaterThanZero(totalFee, OrderMessages.ORDER_REPORT_ADDCREATEORDERS_CREATEFEE_MIN());

		UserProfile user = userRemoteService.getUserProfileByUid(uid);
		ParameterUtil.assertNotNull(user, OrderMessages.ORDER_COUPON_LISTUSERCOUPON_USER_NOTEXIST());
		
		// 1. 获取用户所有可用的优惠券信息（包含未用）
		List<Coupon> items = couponService.listEffective(uid, totalFee);
		if (items == null || items.isEmpty()) {
			logger.info("order#service#loadSettleCoupon | 无可用的优惠券列表  | uid: {}, totalFee: {}", uid,  totalFee);
			return Collections.emptyList();
		}
		
		
		// 2. coupon域的优惠券转成OrderCouponVO对象
		List<SettleCouponVO> result = couponRemoteToSettleCouponVO(uid, items);

		// 最多返回30条
		return result.size() > 30 ? result.subList(0, 30) : result;
	}
	
	/**
	 * 将从couponRemoteService方法查询到的优惠券列表，转成OrderCouponVO对象<br>
	 * 该方法适用于获取结算（预下单）优惠券列表情况，因为此方法会区分（满足使用条件，即可使用。没满足使用条件，暂不可使用）<br>
	 * 同时方法一并排序好（排序方式：可使用>到期时间顺序（最近过期的越前）），如果有可使用优惠券，默认选择优惠金额最大的
	 * 
	 * @param uid - 用户id
	 * @param passport - 下单人通信证
	 * @param items - 优惠券列表
	 * @author hins
	 * @date 2016年7月28日 下午2:09:10
	 * @return 优惠券VO列表，若无，返回empty list
	 */
	private List<SettleCouponVO> couponRemoteToSettleCouponVO(long uid, List<Coupon> items) {

		Coupon maxCoupon = items.get(0); // 优惠金额最大
		List<SettleCouponVO> result = new ArrayList<SettleCouponVO>(items.size());
		for (Coupon coupon : items) {
			// 2016-01-14 modify by hins 内容：实际优惠金额<=0元，不返回
			long couponFee = coupon.getFaceValue();
			if (couponFee <= 0)
				continue;
			
			SettleCouponVO vo = initSettleCoupon(coupon);
			result.add(vo);

			// 只有可用的才纳入条件
			if (couponFee > maxCoupon.getFaceValue())
				maxCoupon = coupon;

		}

		// 可使用的优惠金额最大设为默认
		for (SettleCouponVO vo : result) {
			if (vo.getCouponId() == maxCoupon.getCouponId()) {
				vo.setIsSelect(CouponSelect.YES.getValue());
				break;
			}
		}

		return result;
	}

	/**
	 * 初始化预下单可用优惠券VO
	 * 
	 * @param coupon - 优惠券对象
	 * @param rule - 优惠券规则对象
	 * @param userPassport - 用户手机号
	 * @author hins
	 * @date 2016年7月28日 下午6:25:10
	 * @return SettleCouponVO
	 */
	private SettleCouponVO initSettleCoupon(Coupon coupon) {
		SettleCouponVO vo = VOUtil.from(coupon, SettleCouponVO.class);
		// 计算券的显示描述
		vo.setDescriptionItems(formatDescription(coupon));
		// 转换券的优惠金额，例如将1000分转成10元 而不是10.00元
		long couponFee = vo.getCouponFee(); // 实际优惠金额
		// modify by hins 2016-01-11 内容：使用优惠券的时候，如果是 折扣券， 显示的是 xx元 变更为 xx折， 与我的优惠券列表中 看到的一致
		vo.setFaceValueText(CouponUtils.formatFaceValue(coupon.getEnoughMoney(), coupon.getCouponType(), coupon.getFaceValue()));
		// modify by hins end
		vo.setCouponTitle("¥" + ComputeUtils.fenToYuanInt(couponFee));
		return vo;
	}
	
	
	
}

