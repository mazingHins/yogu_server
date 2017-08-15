/**
 * 
 */
package com.mazing.test.jmeter.store;

import com.mazing.test.core.ApiReq;
import com.mazing.test.core.HttpRequestBuild;
import com.mazing.test.core.KeyValue;
import com.mazing.test.jmeter.AbstractJavaPerf;
import com.mazing.test.runtime.RuntimeData;

/**
 * 收藏餐厅<br>
 * 压力测试
 * 
 * @author jfan 2016年9月30日 下午6:09:49
 */
public class FavStoreResourcePerTest extends AbstractJavaPerf {

	private ApiReq<String> req;
	private Integer[] action = new Integer[] { 0, 1 };

	public FavStoreResourcePerTest() {
		super(true, false);
		needStoreId();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void initial() {
		req = new HttpRequestBuild().build(storeHost + "/a/v1/fav/store");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected String call(KeyValue<String, String> ut) {
		req.setGet("action", random(action).toString());
		req.setGet("storeId", RuntimeData.giveStoreId());

		req.userToken2Get(ut.getKey(), ut.getValue());
		return req.doGet();
	}

}
