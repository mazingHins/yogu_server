package com.yogu.remote.order;

import com.fasterxml.jackson.core.type.TypeReference;
import com.yogu.CommonConstants;
import com.yogu.commons.utils.HttpClientUtils;
import com.yogu.commons.utils.JsonUtils;
import com.yogu.core.web.RestResult;
import com.yogu.core.web.ResultCode;
import com.yogu.core.web.exception.ServiceException;
import com.yogu.remote.order.vo.RemoteOrderVO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Named;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 后台管理测试所使用的接口
 *
 * @author ten 2015/9/2.
 */
@Named
public class OrderRemoteTestService {

    private static final Logger logger = LoggerFactory.getLogger(OrderRemoteTestService.class);

    /**
     * 返回商家订单，按分页返回
     * @param storeId 用户ID
     * @param page 第几页
     * @param pageSize 每页大小
     * @return 返回不为null的List，如果出错抛出 ServiceException
     */
    public List<RemoteOrderVO> queryOrder(long storeId, int page, int pageSize) {
        try {
            String json = HttpClientUtils.doGet(CommonConstants.ORDER_DOMAIN + "/api/order/list/query?storeId="
                    + storeId + "&page=" + page + "&pageSize=" + pageSize);
            RestResult<List<RemoteOrderVO>> result = JsonUtils.parseObject(json, new TypeReference<RestResult<List<RemoteOrderVO>>>() {
            });
            return result.getObject() == null  ? Collections.<RemoteOrderVO>emptyList() : result.getObject();
        } catch (Exception e) {
            logger.error("remote#order#listOnGoingOrder | 读取商家订单出错 | storeId: {}", storeId, e);
            throw new ServiceException(ResultCode.UNKNOWN_ERROR, e.getMessage());
        }
    }

    /**
     * 测试-用户取消订单，只能由后台管理界面调用
     *
     * @param orderId 订单ID
     * @return true=取消成功
     */
    public RestResult<Object> testCancelOrder(long orderId) {
        try {
            String json = HttpClientUtils.doGet(CommonConstants.ORDER_DOMAIN + "/api/order/test/cancel?orderId="
                    + orderId);
            RestResult<Object> result = JsonUtils.parseObject(json, new TypeReference<RestResult<Object>>() {
            });
            return result;
        } catch (Exception e) {
            logger.error("remote#order#listOnGoingOrder | 测试支付订单出错 | orderId: {}", orderId, e);
            throw new ServiceException(ResultCode.UNKNOWN_ERROR, e.getMessage());
        }
    }

    /**
     * 测试-用户支付订单，只能由后台管理界面调用
     *
     * @param orderId 订单ID
     * @return true=支付成功
     */
    public RestResult<Object> testPayOrder(long orderId) {
        try {
            String json = HttpClientUtils.doGet(CommonConstants.ORDER_DOMAIN + "/api/order/test/pay?orderId="
                    + orderId);
            RestResult<Object> result = JsonUtils.parseObject(json, new TypeReference<RestResult<Object>>() {
            });
            return result;
        } catch (Exception e) {
            logger.error("remote#order#listOnGoingOrder | 测试支付订单出错 | orderId: {}", orderId, e);
            throw new ServiceException(ResultCode.UNKNOWN_ERROR, e.getMessage());
        }
    }

    /**
     * 测试-商家接受订单
     * @param orderId 订单ID
     * @return true=接受订单成功
     */
    public RestResult<Object> testAcceptOrder(long orderId) {
        try {
            String json = HttpClientUtils.doGet(CommonConstants.ORDER_DOMAIN + "/api/order/test/accept?orderId="
                    + orderId);
            RestResult<Object> result = JsonUtils.parseObject(json, new TypeReference<RestResult<Object>>() {
            });
            return result;
        } catch (Exception e) {
            logger.error("remote#order#listOnGoingOrder | 测试接受订单出错 | orderId: {}", orderId, e);
            throw new ServiceException(ResultCode.UNKNOWN_ERROR, e.getMessage());
        }
    }

    /**
     * 测试-商家完成制作
     * @param orderId 订单ID
     * @return true=操作成功
     */
    public RestResult<Object> testFinishCooking(long orderId) {
        try {
            String json = HttpClientUtils.doGet(CommonConstants.ORDER_DOMAIN + "/api/order/test/finishCooking?orderId="
                    + orderId);
            RestResult<Object> result = JsonUtils.parseObject(json, new TypeReference<RestResult<Object>>() {
            });
            return result;
        } catch (Exception e) {
            logger.error("remote#order#listOnGoingOrder | 商家完成制作出错 | orderId: {}", orderId, e);
            throw new ServiceException(ResultCode.UNKNOWN_ERROR, e.getMessage());
        }
    }

    /**
     * 测试-商家开始配送
     * @param orderId 订单ID
     * @param deliveryStaff 配送员名称
     * @param phone 配送员电话
     * @return true=操作成功
     */
    public RestResult<Object> testDeliveryOrder(long orderId, String deliveryStaff, String phone) {
        try {
            Map<String, String> params = new HashMap<>(4);
            params.put("orderId", "" + orderId);
            params.put("deliveryStaff", deliveryStaff);
            params.put("phone", phone);
            String json = HttpClientUtils.doGet(CommonConstants.ORDER_DOMAIN + "/api/order/test/delivery", params);
            RestResult<Object> result = JsonUtils.parseObject(json, new TypeReference<RestResult<Object>>() {
            });
            return result;
        } catch (Exception e) {
            logger.error("remote#order#listOnGoingOrder | 开始配送订单出错 | orderId: {}", orderId, e);
            throw new ServiceException(ResultCode.UNKNOWN_ERROR, e.getMessage());
        }
    }

    /**
     * 测试-商家确认完成
     * @param orderId 订单ID
     * @return true=操作成功
     */
    public RestResult<Object> testStoreConfirm(long orderId) {
        try {
            String json = HttpClientUtils.doGet(CommonConstants.ORDER_DOMAIN + "/api/order/test/storeConfirm?orderId="
                    + orderId);
            RestResult<Object> result = JsonUtils.parseObject(json, new TypeReference<RestResult<Object>>() {
            });
            return result;
        } catch (Exception e) {
            logger.error("remote#order#listOnGoingOrder | 商家确认完成订单出错 | orderId: {}", orderId, e);
            throw new ServiceException(ResultCode.UNKNOWN_ERROR, e.getMessage());
        }
    }

    /**
     * 测试-用户确认完成
     * @param orderId 订单ID
     * @return true=操作成功
     */
    public RestResult<Object> testUserConfirm(long orderId) {
        try {
            String json = HttpClientUtils.doGet(CommonConstants.ORDER_DOMAIN + "/api/order/test/userConfirm?orderId="
                    + orderId);
            RestResult<Object> result = JsonUtils.parseObject(json, new TypeReference<RestResult<Object>>() {
            });
            return result;
        } catch (Exception e) {
            logger.error("remote#order#listOnGoingOrder | 用户确认完成订单出错 | orderId: {}", orderId, e);
            throw new ServiceException(ResultCode.UNKNOWN_ERROR, e.getMessage());
        }
    }
}
