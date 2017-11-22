package com.yogu.services.backend.admin.resources.order;



import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.mail.Store;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yogu.commons.utils.CollectionUtils;
import com.yogu.commons.utils.IpAddressUtils;
import com.yogu.commons.utils.resource.Menu;
import com.yogu.commons.utils.resource.MenuResource;
import com.yogu.core.web.RestResult;
import com.yogu.core.web.ResultCode;
import com.yogu.remote.order.OrderRemoteService;
import com.yogu.remote.store.GoodsRemoteService;
import com.yogu.remote.store.StoreRemoteService;
import com.yogu.remote.user.dto.UserProfile;
import com.yogu.remote.user.provider.UserRemoteService;
import com.yogu.services.store.Goods;

/**
 * 订单详情
 * @author ben
 */
@Menu(name="订单详情", parent = "订单管理", sequence = 2200000)
@Controller
@RequestMapping("/admin/order/")
public class OrderDetailResource {
    private static final Logger logger = LoggerFactory.getLogger(OrderDetailResource.class);


    @Autowired
    private OrderRemoteService orderRemoteService;

    @Autowired
    private GoodsRemoteService goodsRemoteService;

    @Autowired
    private UserRemoteService userRemoteService;

    /**
     * 增加管理员主页，xhtml 仅用于展示页面，ajax 调用 .do 接口获取参数
     * @return
     */
    @MenuResource("订单详情主页")
    @RequestMapping("orderDetail.xhtm")
    public String index() {
        return ("/order/order_detail");
    }

    /**
     * 读取订单详情
     * @param orderNo 订单号
     * @return 订单详情
     */
    @RequestMapping("orderDetail")
    @MenuResource("订单详情")
    @ResponseBody
    public RestResult<Map<String, Object>> listOrderTrack(
            long orderNo, HttpServletRequest request) {

        Map<String, Object> map = orderRemoteService.adminGetOrderDetail(orderNo);
        if (map.size() > 0) {
            // 读取配送员名称
            String deliverName = "";
            if (map.containsKey("order")) {
                Map<String, Object> order = (Map<String, Object>) map.get("order");
                long uid = ((Number) order.get("uid")).longValue();
                long payNo = ((Number) order.get("payNo")).longValue();
                
                order.put("deliverName", deliverName);
                order.put("userNickname", getNickname(uid));
                // 将bigdecimal数字转字符串
                order.put("orderNoStr", String.valueOf(orderNo));
                order.put("payNoStr", String.valueOf(payNo));
                
                readDishesName(map);
            }
            logger.info("admin#store#storeDetail | 读取订单详情 | ip: {}", IpAddressUtils.getClientIpAddr(request));
            return new RestResult<>(map);
        }

        return new RestResult<>(ResultCode.FAILURE, "读取订单信息错误，请重试，订单号=" + orderNo);
    }

    private String getNickname(long uid) {
        UserProfile userProfile = userRemoteService.getUserProfileByUid(uid);
        return (userProfile == null ? "(读取昵称失败)" : userProfile.getNickname() + ", 米星帐号: " + userProfile.getPassport());
    }

    /**
     * 读取菜的名称，用于展示
     * @param map
     */
    private void readDishesName(Map<String, Object> map) {
        if (map.containsKey("orderDetails")) {
            List<Map<String, Object>> dishes = (List<Map<String, Object>>) map.get("orderDetails");
            if(dishes == null || dishes.isEmpty()){	// 米星付不需要展示订单详情 2016/7/12 add by hins
            	return;
            }
            StringBuffer buf = new StringBuffer(dishes.size() * 10);
            for (Map<String, Object> row : dishes) {
                // 这里保存的是id
                long dishId = ((Number)row.get("goodsId")).longValue();
                if (buf.length() > 0)
                    buf.append(',');
                buf.append(dishId);
                row.put("goodsName", "(找不到菜名)");
            }
            List<Goods> list = goodsRemoteService.listGoodsTrackByIds(buf.toString());
            Map<Long, String> dishNameMap = new HashMap<>(list.size() * 4 / 3);
            for (Goods dish : list) {
                dishNameMap.put(dish.getGoodsId(), dish.getGoodsName());
            }
            for (Map<String, Object> row : dishes) {
                Long dishId = ((Number)row.get("goodsId")).longValue();
                if (dishNameMap.containsKey(dishId)) {
                    row.put("goodsName", dishNameMap.get(dishId));
                }
            }
        }
    }
}
