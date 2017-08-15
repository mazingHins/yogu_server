/**
 * 
 */
package com.mazing.test.jmeter.user;

import org.apache.commons.lang3.StringUtils;

import com.mazing.test.core.ApiReq;
import com.mazing.test.core.HttpRequestBuild;
import com.mazing.test.core.KeyValue;
import com.mazing.test.core.utils.ParameterUtil;
import com.mazing.test.jmeter.AbstractJavaPerf;

/**
 * 查看用户信息
 * 
 * @author jfan 2016年10月13日 下午4:56:37
 */
public class UserProfileResourcePerTest extends AbstractJavaPerf {

	private ApiReq<String> req;

	public UserProfileResourcePerTest() {
		super(true, false);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void initial() {
		req = new HttpRequestBuild().build(userHost + "/a/v1/user/profile");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected String call(KeyValue<String, String> ut) {
		req.userToken2Get(ut.getKey(), ut.getValue());
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
			String nickname = ParameterUtil.readJsonParam(data, "nickname");
			success = StringUtils.isNotBlank(nickname);
		}
		return success;
	}

}
