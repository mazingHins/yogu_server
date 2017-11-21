package com.yogu.services.backend.admin.resources.order;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yogu.commons.utils.resource.Menu;
import com.yogu.commons.utils.resource.MenuResource;
import com.yogu.core.web.RestResult;
import com.yogu.core.web.ResultCode;
import com.yogu.core.web.exception.ServiceException;
import com.yogu.remote.order.OrderRemoteService;
import com.yogu.remote.user.dto.UserProfile;
import com.yogu.remote.user.provider.UserRemoteService;
import com.yogu.services.backend.admin.context.AdminContext;


/**
 * 显示所有商家的订单，并可以搜索、退款
 * @author ten 2015/9/25.
 */
@Controller
@RequestMapping("/admin/order/")
@Menu(name="所有订单", parent = "订单管理", sequence = 2100000)
public class OrderListResource {


    private static final Logger logger = LoggerFactory.getLogger(OrderListResource.class);
	
	@Autowired
	private OrderRemoteService orderRemoteService;

    @Autowired
    private UserRemoteService userRemoteService;

    /**
     * 所有订单主页，xhtm 仅用于展示页面，ajax 调用 接口获取参数
     * @return
     */
    @RequestMapping("allOrders.xhtm")
    @MenuResource("所有订单主页")
    public String index() {
        return ("/order/all_orders");
    }
    

    /**
     * 查询符合条件的门店列表
     * @param keyword 关键字，可以为null
     * @param page 第几页，最小是1
     * @param pageSize 每页大小，最小是1
     * @return 返回不为空的列表
     */
    @RequestMapping("query")
    @ResponseBody
    @MenuResource("查询订单列表")
    public RestResult<List<Map<String, Object>>> query(String keyword, int page, int pageSize) {

        long adminId = AdminContext.getAccountId();
        long start = System.currentTimeMillis();
        logger.info("admin#order#query | 准备读取数据 | uid: {}, keyword: {},  pageIndex: {}, pageSize: {}",
                adminId, keyword, page, pageSize);

        validate( page, pageSize);

        long orderNo = 0;
        long uid = 0;
        if (StringUtils.isNotBlank(keyword)) {
            keyword = keyword.trim();
            if (NumberUtils.isDigits(keyword)) {
                if (keyword.length() == 11) {
                    uid = NumberUtils.toLong(keyword, 0);
                    String passport = keyword;
                    String countryCode = "86";
                    Map<String, Object> map = userRemoteService.getUidByPassport(countryCode, passport);
                    if (map.containsKey("uid")) {
                        uid = ((Number) map.get("uid")).longValue();
                    }
				} else if (keyword.length() > 11 /* OrderNO.MAX_LEN */) {
                    orderNo = NumberUtils.toLong(keyword, 0);
                }
            }
        }
    	RestResult<List<Map<String, Object>>> result = orderRemoteService.adminQueryOrders(uid,
                orderNo, 0L, page, pageSize);
    	
    	if (result.isSuccess()) {
            readStoreInfo(result.getObject());
        }

        logger.info("admin#store#query | 读取数据结果 | success: {}, time: {}", result.isSuccess(),
                System.currentTimeMillis() - start);
        return result;
    }
    
    /**
     * 读取购买者的昵称、餐厅的名称
     * @param list 订单列表，同 List&lt;Order&gt;
     * @author ten 2016/1/26
     */
    private void readStoreInfo(List<Map<String, Object>> list) {
    	if (list.isEmpty()) {
			return;
		}
    	
        long[] uids = new long[list.size()];
        long[] storeIds = new long[list.size()];
        int index = 0;
        for (Map<String, Object> row : list) {
            long uid = 0, storeId = 0;
            if (row.containsKey("uid")) {
                uid = ((Number) row.get("uid")).longValue();
            }
            if (row.containsKey("storeId")) {
                storeId = ((Number) row.get("storeId")).longValue();
            }
            
            // 将bigdecimal数字转字符串
			row.put("orderNoStr", String.valueOf(row.get("orderNo").toString()));
            
            uids[index] = uid;
            storeIds[index] = storeId;
            index++;
        }
        Map<Long, UserProfile> userProfileMap = userRemoteService.getUserProfileByUids(uids);
        for (Map<String, Object> row : list) {
            String userNickname = "(读取昵称失败)";
            if (row.containsKey("uid")) {
                Long uid = ((Number) row.get("uid")).longValue();
                if (userProfileMap.containsKey(uid)) {
                    UserProfile userProfile = userProfileMap.get(uid);
                    userNickname = userProfile.getNickname() + " (ID/帐号: " + uid + " / " + userProfile.getPassport() + ")";
                }
            }
            row.put("userNickname", userNickname);
        }
    }

    /**
     * 参数校验
     *
     * @param pageIndex 当前页
     * @param pageSize 最大返回查询多少条数据
     */
    private void validate( int pageIndex, int pageSize) {
        if (pageIndex < 1 || pageSize < 1)
            throw new ServiceException(ResultCode.PARAMETER_ERROR, "分页参数错误");
    }
}
