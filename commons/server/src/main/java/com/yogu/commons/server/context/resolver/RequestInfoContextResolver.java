/**
 * 
 */
package com.yogu.commons.server.context.resolver;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Context;
import javax.ws.rs.ext.ContextResolver;
import javax.ws.rs.ext.Provider;

import com.yogu.commons.server.context.RequestInfo;

/**
 * <br>
 * 
 * JFan 2014年12月17日 下午4:20:31
 */
@Provider
public class RequestInfoContextResolver implements ContextResolver<RequestInfo> {

	@Context
	private HttpServletRequest request;

	/*
	 * （非 Javadoc）
	 * 
	 * @see javax.ws.rs.ext.ContextResolver#getContext(java.lang.Class)
	 */
	@Override
	public RequestInfo getContext(Class<?> type) {
		if (RequestInfo.class.equals(type)) {
			if (null != request)
				return new RequestInfo(request);
		}
		return null;
	}

}
