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
 * 首页布局接口测试
 * 
 * @author jfan 2016年10月13日 下午2:39:47
 */
public class LayoutResourcePerTest extends AbstractJavaPerf {

	private ApiReq<String> req;

	public LayoutResourcePerTest() {
		super(false, true);
		needCityCode();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void initial() {
		req = new HttpRequestBuild().build(storeHost + "/p/v1/index/layout");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected String call(KeyValue<String, String> ut) {
		String cityCode = RuntimeData.giveCityCode();
		req.setGet("cityId", cityCode);

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

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected boolean determine(String data) {
		// 检测 code = 1
		boolean success = super.determine(data);
		// 还要检测有内容返回
		if (success) {
			String bid = ParameterUtil.readJsonParam(data, "bid");
			success = StringUtils.isNotBlank(bid);
		}
		return success;
	}

}
