/**
 * 
 */
package com.mazing.test.jmeter.order;

import com.mazing.test.core.ApiReq;
import com.mazing.test.core.HttpRequestBuild;
import com.mazing.test.core.KeyValue;
import com.mazing.test.jmeter.AbstractJavaPerf;

/**
 * 用户查看进行中的订单列表 <br>
 * 压力测试
 *
 * @author JFan 2015年12月22日 下午9:01:45
 */
public class UserOrderListResourcePerTest extends AbstractJavaPerf {

	private ApiReq<String> req;

	public UserOrderListResourcePerTest() {
		super(true, true);
		collectOrderNo();
		collectStoreId();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void initial() {
		req = new HttpRequestBuild().build(orderHost + "/a/v1/order/list/ongoing");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected String call(KeyValue<String, String> ut) {
		req.userToken2Get(ut.getKey(), ut.getValue());

		return req.doGet();
	}

}
