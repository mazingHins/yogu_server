package com.yogu.core.server.container.operation;

import javassist.expr.NewArray;

import javax.ws.rs.container.DynamicFeature;
import javax.ws.rs.container.ResourceInfo;
import javax.ws.rs.core.FeatureContext;

import com.yogu.core.enums.Role;
import com.yogu.core.server.annotation.OperationAuthenticator;
import com.yogu.core.web.ResultCode;
import com.yogu.core.web.exception.ServiceException;


/**
 * 对OperationAuthenticator的支持
 * 
 * @author Felix 2015年9月9日
 */
public class OperationAnnoFeature implements DynamicFeature {

	@Override
	public void configure(ResourceInfo resourceInfo, FeatureContext context) {
		OperationAuthenticator oa = resourceInfo.getResourceMethod().getAnnotation(OperationAuthenticator.class);
		if (null == oa){
			oa = resourceInfo.getResourceClass().getAnnotation(OperationAuthenticator.class);
		}
		
		if (null != oa){
			Role[] type = oa.value();
			context.register(new OperAuthenticatorFilter(type));
		}
	}
}
