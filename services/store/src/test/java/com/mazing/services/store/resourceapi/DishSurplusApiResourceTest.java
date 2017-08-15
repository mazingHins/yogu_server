package com.mazing.services.store.resourceapi;

import java.util.Map;

import org.junit.Test;

import com.yogu.commons.utils.DateUtils;
import com.yogu.core.test.ApiReq;
import com.yogu.core.test.BaseResourceTest;
import com.yogu.core.web.RestResult;
import com.yogu.core.web.ResultCode;

/**
 * 菜品相关的 本地API 的测试类 <br>
 * 
 * @author JFan 2015年7月21日 上午11:45:23
 */
public class DishSurplusApiResourceTest extends BaseResourceTest {

	public DishSurplusApiResourceTest() {
		host = "http://storeapi.mazing.com";
	}

	/**
	 * 建立菜品库存表
	 */
	@Test
	public void createTable() {
		ApiReq<RestResult<?>> req = build("api/dishSurplus/createTable");
		req.putGet("index", "" + (DateUtils.currentTimeDay() / 100));

		RestResult<?> result = req.doGet();
		assertEquals(ResultCode.SUCCESS, result.getCode());
	}

	/**
	 * 扣减指定哪一天的库存
	 */
	@Test
	public void consumeSurplus() {
		ApiReq<RestResult<?>> req = build("api/dishSpecSurplus/consume");
		req.putGet("day", "" + DateUtils.currentTimeDay());
		req.putGet("specKey", "376");
		req.putGet("specKey", "376");
		req.putGet("specKey", "555");

		RestResult<?> result = req.doGet();
		Map<?, ?> map = assertMap(result);
		assertTrue(map.isEmpty());
	}

	@Test
	public void releaseSurplus() {
		ApiReq<RestResult<?>> req = build("api/dishSpecSurplus/release");
		req.putGet("day", "" + DateUtils.currentTimeDay());
		req.putGet("specKey", "376");
		req.putGet("specKey", "376");
		req.putGet("specKey", "222");

		RestResult<?> result = req.doGet();
		boolean ok = assertObject(result);
		assertTrue(ok);
	}

	@Test
	public void testFindSpecSurplus() {
		ApiReq<RestResult<?>> req = build("api/dishSpecSurplus/listSpecSurplus");
		int day = 20160107;
		req.putGet("day", "" + day);
		req.putGet("storeId", "12701");
		req.putGet("specKey", "375");
		req.putGet("specKey", "191");
		req.putGet("specKey", "100000");

		RestResult<?> result = req.doGet();

		System.out.println(result.getObject());

	}

}
