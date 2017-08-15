/**
 * 
 */
package com.mazing.test.jmeter.order;

import com.mazing.test.core.ApiReq;
import com.mazing.test.core.HttpRequestBuild;
import com.mazing.test.core.KeyValue;
import com.mazing.test.jmeter.AbstractJavaPerf;
import com.mazing.test.runtime.RuntimeData;

/**
 * 查看用户优惠券列表
 * 
 * @author jfan 2016年10月13日 下午5:01:04
 */
public class UserCouponListResourcePerTest extends AbstractJavaPerf {

	private ApiReq<String> req;
	private Integer[] pages = new Integer[] { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 2, 2, 3, 3, 4, 5, 6, 7 };
	private Integer[] sizes = new Integer[] { 20, 20, 20, 20, 20, 20, 20, 20, 20, 21, 21, 21, 22, 23, 30, 40 };

	public UserCouponListResourcePerTest() {
		super(true, true);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void initial() {
		req = new HttpRequestBuild().build(orderHost + "/a/v1/coupon/list");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected String call(KeyValue<String, String> ut) {
		req.setGet("pageIndex", random(pages).toString());
		req.setGet("pageSize", random(sizes).toString());
		req.userToken2Get(ut.getKey(), ut.getValue());

		// 设置语言
		String lang = RuntimeData.giveLangCode();
		if (null != lang)
			req.setLang(lang);

		return req.doGet();
	}

}
