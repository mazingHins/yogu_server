/**
 * 
 */
package com.yogu.commons.server.clean.impl;

import com.yogu.commons.server.clean.CleanProvider;
import com.yogu.commons.utils.HttpClientUtils;

/**
 * 在容器关闭的时候，关闭HttpClientUtils中的连接池
 * 
 * @author jfan 2016年10月11日 下午7:20:13
 */
public class HttpClientConnectionManagerCleanProvider implements CleanProvider {

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void cleanUp() {
		HttpClientUtils.shutdown();
	}

}
