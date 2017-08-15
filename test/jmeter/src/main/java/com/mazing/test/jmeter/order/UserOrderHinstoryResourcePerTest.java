/**
 * 
 */
package com.mazing.test.jmeter.order;

import com.mazing.test.core.ApiReq;
import com.mazing.test.core.HttpRequestBuild;
import com.mazing.test.core.KeyValue;
import com.mazing.test.jmeter.AbstractJavaPerf;

/**
 * 用户查看历史订单 <br>
 * 压力测试
 *
 * @author JFan 2015年12月22日 下午9:01:30
 */
public class UserOrderHinstoryResourcePerTest extends AbstractJavaPerf {

	private ApiReq<String> req;
	private Integer[] pages = new Integer[] { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 3, 3, 3, 4, 5, 6 };
	private Integer[] sizes = new Integer[] { 20, 20, 20, 20, 20, 20, 20, 20, 20, 21, 21, 21, 22, 23, 30, 40, 50 };

	public UserOrderHinstoryResourcePerTest() {
		super(true, false);// 返回的是obj，有两个字段：total,list
		collectOrderNo();
		collectStoreId();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void initial() {
		req = new HttpRequestBuild().build(orderHost + "/a/v1/order/list/history");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected String call(KeyValue<String, String> ut) {
		req.setGet("pageIndex", random(pages).toString());
		req.setGet("pageSize", random(sizes).toString());

		req.userToken2Get(ut.getKey(), ut.getValue());

		String json = req.doGet();
		return json;
	}

}
