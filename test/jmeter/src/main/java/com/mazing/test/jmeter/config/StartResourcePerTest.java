/**
 * 
 */
package com.mazing.test.jmeter.config;

import org.apache.commons.lang3.StringUtils;

import com.mazing.test.core.ApiReq;
import com.mazing.test.core.HttpRequestBuild;
import com.mazing.test.core.KeyValue;
import com.mazing.test.core.utils.ParameterUtil;
import com.mazing.test.jmeter.AbstractJavaPerf;
import com.mazing.test.runtime.RuntimeData;
import com.mazing.test.runtime.impl.CityCodeCollect;
import com.mazing.test.runtime.impl.LangCodeCollect;

/**
 * 首页布局接口测试
 * 
 * @author jfan 2016年10月13日 下午2:39:47
 */
public class StartResourcePerTest extends AbstractJavaPerf {

	private ApiReq<String> req;

	public StartResourcePerTest() {
		super(false, false);
		collectByClass(2, CityCodeCollect.class);
		collectByClass(2, LangCodeCollect.class);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void initial() {
		req = new HttpRequestBuild().build(configHost + "/p/v1/config/app/start");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected String call(KeyValue<String, String> ut) {
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
			String sysTime = ParameterUtil.readJsonParam(data, "sysTime");
			success = StringUtils.isNotBlank(sysTime);
		}
		return success;
	}

}
