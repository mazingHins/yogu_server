/**
 * 
 */
package com.mazing.test.jmeter.store;

import org.apache.commons.lang3.StringUtils;

import com.mazing.test.core.ApiReq;
import com.mazing.test.core.HttpRequestBuild;
import com.mazing.test.core.KeyValue;
import com.mazing.test.core.utils.ParameterUtil;
import com.mazing.test.jmeter.AbstractJavaPerf;
import com.mazing.test.runtime.RuntimeData;

/**
 * 查看美食详情
 * 
 * @author jfan 2016年10月13日 下午4:45:49
 */
public class DishDetailsResourcePerTest extends AbstractJavaPerf {

	private ApiReq<String> req;

	public DishDetailsResourcePerTest() {
		super(false, false);
		needDishKey();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void initial() {
		req = new HttpRequestBuild().build(storeHost + "/p/v1/dish/details");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected String call(KeyValue<String, String> ut) {
		String dishKey = RuntimeData.giveDishKey();
		req.setGet("dishId", dishKey);

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
			String bid = ParameterUtil.readJsonParam(data, "dishId");
			success = StringUtils.isNotBlank(bid);
		}
		return success;
	}

}
