/**
 * 
 */
package com.mazing.test.jmeter.store;

import com.mazing.test.core.ApiReq;
import com.mazing.test.core.HttpRequestBuild;
import com.mazing.test.core.KeyValue;
import com.mazing.test.jmeter.AbstractJavaPerf;
import com.mazing.test.runtime.RuntimeData;
import com.mazing.test.runtime.impl.AllNewsBidCollect;

/**
 * 查看资讯首页信息（资讯块）
 * 
 * @author jfan 2016年10月13日 下午6:01:50
 */
public class NewsResourcePerTest extends AbstractJavaPerf {

	private Integer[] pages = new Integer[] { 1, 1, 1, 1, 1, 1, 1, 1, 2, 2, 2, 3, 3, 3, 4, 4, 5, 6, 7, 8, 9 };
	private ApiReq<String> req;

	public NewsResourcePerTest() {
		super(false, true);
		needCityCode();
		collectByClass(50, AllNewsBidCollect.class);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void initial() {
		req = new HttpRequestBuild().build(storeHost + "/p/v1/news/index");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected String call(KeyValue<String, String> ut) {
		String cityCode = RuntimeData.giveCityCode();
		req.setGet("cityId", cityCode);
		req.setGet("pageIndex", random(pages).toString());

		if (null != ut)
			req.userToken2Get(ut.getKey(), ut.getValue());
		else
			req.clearUserToken();

		// 设置语言
		String lang = RuntimeData.giveLangCode();
		if (null != lang)
			req.setLang(lang);

		return req.doGet();
	}

}
