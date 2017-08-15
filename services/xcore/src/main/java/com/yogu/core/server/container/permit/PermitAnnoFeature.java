/**
 * 
 */
package com.yogu.core.server.container.permit;

import java.lang.reflect.Method;

import javax.ws.rs.container.DynamicFeature;
import javax.ws.rs.container.ResourceInfo;
import javax.ws.rs.core.FeatureContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.yogu.core.server.annotation.AcceptPermit;

/**
 * 对自定义注解 @AcceptPermit 的支持 <br>
 *
 * @author JFan 2015年8月8日 上午12:21:40
 */
public class PermitAnnoFeature implements DynamicFeature {

	private static final Logger logger = LoggerFactory.getLogger(PermitAnnoFeature.class);

	/*
	 * （非 Javadoc）
	 * 
	 * @see javax.ws.rs.container.DynamicFeature#configure(javax.ws.rs.container.ResourceInfo, javax.ws.rs.core.FeatureContext)
	 */
	@Override
	public void configure(ResourceInfo resourceInfo, FeatureContext context) {
		Class<?> clazz = resourceInfo.getResourceClass();
		Method method = resourceInfo.getResourceMethod();

		AcceptPermit ap = method.getAnnotation(AcceptPermit.class);
		if (null == ap)
			ap = clazz.getAnnotation(AcceptPermit.class);

		if (null != ap) {
			Class<? extends PermitValidateFilter> filter = ap.value();
			if (null != filter) {
				logger.info("core#permit#feature | {} | class.method: {}.{}", filter.getName(), clazz.getName(), method.getName());
				context.register(filter);
			}
		}

	}

}
