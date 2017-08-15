/**
 * 
 */
package com.mazing.test.jmeter.store;

import com.mazing.test.core.ApiReq;
import com.mazing.test.core.HttpRequestBuild;
import com.mazing.test.core.KeyValue;
import com.mazing.test.core.utils.ParameterUtil;
import com.mazing.test.jmeter.AbstractJavaPerf;

/**
 * 瀑布流接口 <br>
 * 压力测试
 *
 * @author JFan 2015年12月17日 下午7:08:52
 */
public class IndexResourcePerTest extends AbstractJavaPerf {

	private Integer[] pages = new Integer[] { 1, 1, 1, 1, 1, 1, 2, 2, 3, 3, 4, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19 };
	private Integer[] sizes = new Integer[] { 20, 20, 20, 20, 20, 20, 20, 20, 20, 21, 21, 21, 22, 23, 30, 40, 50 };
	private ApiReq<String> req;
	private Integer pageSize;

	public IndexResourcePerTest() {
		super(false, true);
		collectStoreId();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void initial() {
		req = new HttpRequestBuild().build(storeHost + "/p/v1/index/list/page");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected String call(KeyValue<String, String> ut) {
		pageSize = random(pages);

		req.setGet("pageIndex", pageSize.toString());
		req.setGet("pageSize", random(sizes).toString());

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
		if (success && (6 > pageSize)) {// 1~5页的数据默认为有数据返回
			String object = ParameterUtil.readJsonParam(data, "object");
			success = !("[]".equals(object));
		}
		return success;
	}

}
