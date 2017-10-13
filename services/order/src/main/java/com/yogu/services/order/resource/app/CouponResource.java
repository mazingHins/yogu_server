package com.yogu.services.order.resource.app;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.yogu.commons.utils.PageUtils;
import com.yogu.core.web.RestResult;
import com.yogu.core.web.context.SecurityContext;
import com.yogu.services.order.coupon.service.OrderCouponService;
import com.yogu.services.order.resource.vo.coupon.UserCouponVO;

/**
 * 我的-优惠券相关接口
 * 
 * @author Hins
 * @date 2015年12月23日 上午11:37:13
 */
@Named
@Path("a")
@Singleton
@Produces("application/json;charset=UTF-8")
public class CouponResource {

	private static final Logger logger = LoggerFactory.getLogger(CouponResource.class);

	private static final int MAX_PAGE_SIZE = 50;// 每页最多条数

	private static final int MIN_PAGE_SIZE = 10;// 每页最少条数

	@Inject
	private OrderCouponService orderCouponService;
	

	/**
	 * 用户查看我的优惠券列表<br>
	 * 接口支持分页，若无指定每页大小，默认10条，最大支持50条<br>
	 * 若用户无优惠券列表，object返回empty
	 * 
	 * @author Hins
	 * @date 2015年12月23日 上午11:59:53
	 * 
	 * @param pageIndex - 第几页
	 * @param pageSize - 每页大小
	 * @return
	 */
	@GET
	@Path("v1/coupon/list")
	public RestResult<List<UserCouponVO>> listUserCoupon(@QueryParam("pageIndex") int pageIndex// 第几页（1开始），小于1则默认第一页
			, @QueryParam("pageSize") Integer pageSize) {
		long uid = SecurityContext.getUid();
		logger.info("order#coupon#listSelfCoupon | 查看我的优惠券列表start | uid: {}, pageIndex: {}, pageSize: {}", uid, pageIndex, pageSize);
		int index = PageUtils.limitIndex(pageIndex, 1);
		int size = PageUtils.limitSize(pageSize, MIN_PAGE_SIZE, MAX_PAGE_SIZE); // 最少5条，最多30
		return new RestResult<List<UserCouponVO>>(orderCouponService.listUserCoupon(uid, index, size));
	}

	
}
