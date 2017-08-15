/**
 * 
 */
package com.mazing.test.jmeter.store;

import com.mazing.test.core.ApiReq;
import com.mazing.test.core.HttpRequestBuild;
import com.mazing.test.core.KeyValue;
import com.mazing.test.jmeter.AbstractJavaPerf;
import com.mazing.test.runtime.Config;
import com.mazing.test.runtime.impl.AllNewsBidCollect;

/**
 * 搜索资讯块
 * 
 * @author jfan 2016年10月13日 下午6:03:09
 */
public class SearchNewsResourcePerTest extends AbstractJavaPerf {

	private ApiReq<String> req;
	private Integer[] pages = new Integer[] { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 3, 3, 3, 4, 5, 6 };

	public SearchNewsResourcePerTest() {
		super(false, true);
		collectByClass(100, AllNewsBidCollect.class);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void initial() {
		Config.giveRandomByOriginal("storeSearch");
		req = new HttpRequestBuild().build(storeHost + "/p/v1/news/block/search");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected String call(KeyValue<String, String> ut) {
		req.setGet("query", Config.giveRandomByOriginal("storeSearch"));
		req.setGet("pageIndex", random(pages).toString());

		if (null != ut)
			req.userToken2Get(ut.getKey(), ut.getValue());
		else
			req.clearUserToken();

		return req.doGet();
	}

}
