/**
 * 
 */
package com.mazing.test.jmeter.order;

import com.mazing.test.core.ApiReq;
import com.mazing.test.core.HttpRequestBuild;
import com.mazing.test.core.KeyValue;
import com.mazing.test.core.utils.ParameterUtil;
import com.mazing.test.jmeter.AbstractJavaPerf;
import com.mazing.test.runtime.RuntimeData;

/**
 * 用户查看订单详情 <br>
 * 压力测试
 *
 * @author JFan 2015年12月22日 下午9:01:25
 */
public class UserOrderDetailResourcePerTest extends AbstractJavaPerf {

	private ApiReq<String> req;

	public UserOrderDetailResourcePerTest() {
		super(true, false);
		needOrderNo();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void initial() {
		req = new HttpRequestBuild().build(orderHost + "/a/v1/order/detail");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected String call(KeyValue<String, String> ut) {
		String orderNo = RuntimeData.randomOrderNoByUT(ut.getKey());
		req.setGet("orderNo", orderNo);

		req.userToken2Get(ut.getKey(), ut.getValue());

		return req.doGet();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected boolean determine(String data) {
		// 检测 code = 1
		boolean success = super.determine(data);
		// 还要检测有内容返回
		if (success) {
			String object = ParameterUtil.readJsonParam(data, "object");
			success = !("{}".equals(object));
		}
		return success;
	}

}
