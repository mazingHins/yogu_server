/**
 * 
 */
package com.mazing.test.jmeter.store;

import com.mazing.test.core.ApiReq;
import com.mazing.test.core.HttpRequestBuild;
import com.mazing.test.core.KeyValue;
import com.mazing.test.jmeter.AbstractJavaPerf;

/**
 * 查看餐厅收藏列表<br>
 * 压力测试
 *
 * @author JFan 2015年12月17日 下午7:08:52
 */
public class FavStoreListResourcePerTest extends AbstractJavaPerf {

	private ApiReq<String> req;
	private Integer[] pages = new Integer[] { 1, 1, 1, 1, 1, 1, 2, 3, 4, 5 };
	private Integer[] sizes = new Integer[] { 20, 20, 20, 20, 20, 20, 20, 20, 20, 21, 21, 21, 22, 23, 30, 40, 50 };

	public FavStoreListResourcePerTest() {
		super(true, true);
		collectStoreId();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void initial() {
		req = new HttpRequestBuild().build(storeHost + "/a/v1/fav/store/list");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected String call(KeyValue<String, String> ut) {
		req.setGet("pageIndex", random(pages).toString());
		req.setGet("pageSize", random(sizes).toString());

		req.userToken2Get(ut.getKey(), ut.getValue());
		return req.doGet();
	}

}
