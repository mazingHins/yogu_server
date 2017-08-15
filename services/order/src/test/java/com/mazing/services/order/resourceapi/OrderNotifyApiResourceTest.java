package com.mazing.services.order.resourceapi;

import java.util.Map;

import org.junit.Test;

import com.yogu.core.enums.pay.ApplyRefundStatus;
import com.yogu.core.enums.pay.PayStatus;
import com.yogu.core.test.ApiReq;
import com.yogu.core.test.HttpResourceTest;
import com.yogu.core.web.RestResult;

public class OrderNotifyApiResourceTest extends HttpResourceTest {

	public OrderNotifyApiResourceTest() {
		host = "http://orderapi.mazing.com";
	}

	@Test
	public void getPay() {
		ApiReq<RestResult<?>> req = build("api/v1/pay/getPay.do");
		req.putPost("partnerId", "1438937781247");
		req.putPost("tradeNo", "1438943044430");
		req.putPost("totalFee", "367");
		req.putPost("payMode", "1");
		req.putPost("currencyType", "1");
		req.putPost("buyerUid", "367");
		req.putPost("sellerUid", "367");
		req.putPost("userIp", "127.0.0.1");
		req.putPost("notifyUrl", "notifyUrl");

		RestResult<?> result = req.doPost();
		Map<?, ?> map = assertMap(result);
		assertNotNull(map);

	}

	@Test
	public void notifys() {
		// 正确参数
		ApiReq<RestResult<?>> req = build("api/v1/order/pay/notify.do");
		req.putPost("tradeNo", "1606371631647401");
		req.putPost("payNo", "160617752537253401");
		req.putPost("status", PayStatus.TRADE_SUCCESS.toString());
		req.putPost("remark", "remark");

		RestResult<?> result = req.doPost();
		Map<?, ?> map = assertMap(result);
		assertNotNull(map);
	}

	@Test
	public void refundNotify() {
		// 正确参数
		ApiReq<RestResult<?>> req = build("api/v1/order/refund/notify.do");
		req.putPost("refundNo", "130001407");
		req.putPost("status", ApplyRefundStatus.REFUND_SUCCESS.toString());
		req.putPost("remark", "remark");

		RestResult<?> result = req.doPost();
		Map<?, ?> map = assertMap(result);
		assertNotNull(map);
	}
	
	@Test
	public void fixReport() {
		// 正确参数
		ApiReq<RestResult<?>> req = build("api/statistics/order/flow");
//		req.login("86", "13012345601", "abcd1234");
		req.putGet("day", "7");
		req.putGet("storeId", "14601");

		RestResult<?> result = req.doGet();
		Map<?, ?> map = assertMap(result);
		assertNotNull(map);
	}

}
