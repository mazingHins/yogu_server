/**
 * 
 */
package com.mazing.test.jmeter.store;

import com.mazing.test.core.ApiReq;
import com.mazing.test.core.HttpRequestBuild;
import com.mazing.test.core.KeyValue;
import com.mazing.test.core.utils.ParameterUtil;
import com.mazing.test.jmeter.AbstractJavaPerf;
import com.mazing.test.runtime.RuntimeData;

/**
 * 餐厅mini blog <br>
 * 压力测试
 *
 * @author JFan 2015年12月17日 下午7:08:52
 */
public class StoreMiniblogResourcePerTest extends AbstractJavaPerf {

	private ApiReq<String> req;

	public StoreMiniblogResourcePerTest() {
		super(false, false);
		needStoreId();
		collectDishKey();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void initial() {
		req = new HttpRequestBuild().build(storeHost + "/p/v1/store/details");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected String call(KeyValue<String, String> ut) {
		req.setGet("storeId", RuntimeData.giveStoreId());

		if (null != ut)
			req.userToken2Get(ut.getKey(), ut.getValue());
		else
			req.clearUserToken();

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
