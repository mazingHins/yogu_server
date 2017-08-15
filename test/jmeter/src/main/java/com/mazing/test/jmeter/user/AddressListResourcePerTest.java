/**
 * 
 */
package com.mazing.test.jmeter.user;

import com.mazing.test.core.ApiReq;
import com.mazing.test.core.HttpRequestBuild;
import com.mazing.test.core.KeyValue;
import com.mazing.test.jmeter.AbstractJavaPerf;

/**
 * 查看用户收获地址列表
 * 
 * @author jfan 2016年10月13日 下午4:56:37
 */
public class AddressListResourcePerTest extends AbstractJavaPerf {

	private ApiReq<String> req;

	public AddressListResourcePerTest() {
		super(true, true);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void initial() {
		req = new HttpRequestBuild().build(userHost + "/a/v1/user/address/list");
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
