package com.yogu.services.backend.admin.resources.coupon;

import java.io.OutputStream;
import java.text.MessageFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.core.type.TypeReference;
import com.yogu.CommonConstants;
import com.yogu.commons.utils.HttpClientUtils;
import com.yogu.commons.utils.JsonUtils;
import com.yogu.commons.utils.PageUtils;
import com.yogu.commons.utils.encrypt.AESUtil;
import com.yogu.commons.utils.qrcode.QRCodeUtils;
import com.yogu.commons.utils.resource.Menu;
import com.yogu.commons.utils.resource.MenuResource;
import com.yogu.core.remote.config.ConfigGroupConstants;
import com.yogu.core.remote.config.ConfigRemoteService;
import com.yogu.core.web.ParameterUtil;
import com.yogu.core.web.RestResult;
import com.yogu.core.web.ResultCode;
import com.yogu.remote.order.CouponRemoteService;
import com.yogu.remote.order.vo.AdminCouponListVO;
import com.yogu.services.backend.admin.annotation.log.AdminLog;
import com.yogu.services.backend.admin.context.AdminContext;
import com.yogu.services.backend.admin.dto.AdminAccount;
import com.yogu.services.backend.admin.service.AdminAccountService;

/**
 * 优惠券列表页
 * 
 * @author ten 2015/12/24.
 */
@Controller
@RequestMapping("/admin/coupon/")
@Menu(name = "优惠券列表", parent = "优惠券管理", sequence = 2100000)
public class CouponListResource {

	private static final Logger logger = LoggerFactory.getLogger(CouponListResource.class);

	@Autowired
	private CouponRemoteService couponRemoteService;

	@Autowired
	private AdminAccountService adminAccountService;

	/**
	 * 优惠券列表主页，xhtm 仅用于展示页面，ajax 调用 接口获取参数
	 * 
	 * @return
	 */
	@RequestMapping("list.xhtm")
	@MenuResource("优惠券列表主页")
	public ModelAndView index() {
		long adminId = AdminContext.getAccountId();
		Map<String, Object> model = new HashMap<>();
		model.put("adminId", adminId);
		return new ModelAndView("/coupon/list_coupon", model);
	}

	/**
	 * 查询优惠券列表
	 * 
	 * @param keyword 关键字
	 * @param page 第几页
	 * @param pageSize 每页数据大小
	 * @return
	 */
	@ResponseBody
	@MenuResource("查询优惠券列表")
	@RequestMapping("queryCouponRules")
	public RestResult<List<AdminCouponListVO>> queryCouponRules(String keyword, int page, int pageSize) {
		List<AdminCouponListVO> list = couponRemoteService.adminQueryCouponRules(keyword, page, pageSize);

		Map<Long, String> adminNameMap = new HashMap<>();

		if (CollectionUtils.isNotEmpty(list)) {
			for (AdminCouponListVO vo : list) {
				// 把 adminId 转换为 adminName,
				long adminId = vo.getAdminId();
				String name = adminNameMap.get(adminId);

				if (StringUtils.isBlank(name)) {
					AdminAccount account = adminAccountService.getById(adminId);
					if (account == null) {
						name = "[未知]";
					} else {
						name = account.getRealname();
					}
					adminNameMap.put(adminId, name);
				}

				vo.setAdminName(name);
			}
		}

		return new RestResult<>(list);
	}

	/**
	 * 令优惠券失效，已经领取的优惠券可以使用，未领取的优惠券作废
	 * 
	 * @param couponRuleId 优惠券规则ID
	 * @return result.success=true为成功
	 */
	@ResponseBody
	@RequestMapping(value = "disableCouponRule.do", method = RequestMethod.POST)
	@AdminLog
	@MenuResource("令优惠券失效")
	public RestResult<Object> disableCouponRule(long couponRuleId) {
		long adminId = AdminContext.getAccountId();
		logger.info("admin#coupon#disableCouponRule | 令优惠券失效 | adminId: {}, couponRuleId: {}", adminId, couponRuleId);
		RestResult<Object> result = couponRemoteService.adminDisableCouponRule(adminId, couponRuleId);
		logger.info("admin#coupon#disableCouponRule | 令优惠券失效结果 | adminId: {}, couponRuleId: {}, code: {}, message: {}", adminId,
				couponRuleId, result.getCode(), result.getMessage());
		return result;
	}

	public static void main(String[] args) throws Exception {
		String key = "!4i$G-po|YU^&dx?";

		System.out.println(AESUtil.encrypt(key, "30001").toLowerCase());

	}

}
