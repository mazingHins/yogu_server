package com.mazing.services.store.resourceapi;

import java.util.List;
import java.util.Map;

import org.junit.Test;

import com.yogu.core.test.ApiReq;
import com.yogu.core.test.BaseResourceTest;
import com.yogu.core.web.RestResult;
import com.yogu.core.web.ResultCode;

/**
 * 服务配置--API 测试 <br>
 * 
 * @author JFan 2015年7月20日 下午5:17:13
 */
public class StoreServiceRangeApiResourceTest extends BaseResourceTest {

	/**
	 * 获取门店下全部的‘服务配置’信息
	 */
	@Test
	public void getAll() {
		ApiReq<RestResult<?>> req = build("api/store/service/allRange");
		req.putGet("storeId", "100");

		RestResult<?> result = req.doGet();
		List<?> list = assertList(result);
		assertNotNull(list);
	}

	/**
	 * 获取最适合用户“配送目的地”的“服务配置”信息
	 */
	@Test
	public void proximate() {
		ApiReq<RestResult<?>> req = build("api/store/service/effective");
		req.setGet("storeId", "100");// 这个数据需要制造
		req.setGet("distance", "100000");

		// no: 113.267034,23.140841
		req.setGet("lng", "113.267034");
		req.setGet("lat", "23.140841");

		RestResult<?> result = req.doGet();
		assertEquals(ResultCode.SUCCESS, result.getCode());
		assertNull(result.getObject());

		// in: 113.269406,23.149282
		req.setGet("lng", "113.269406");
		req.setGet("lat", "23.149282");

		result = req.doGet();
		Map<?, ?> map = assertMap(result);
		assertNotNull(map);
		assertNotNull(map.get("rangeId"));
	}

}
