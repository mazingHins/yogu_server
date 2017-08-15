/**
 * api
 *
 * http://www.mazing.com
 * Copyright (c) 2008-2014. All Rights Reserved.
 * */

package com.yogu.core.server;

import org.glassfish.jersey.filter.LoggingFilter;
import org.glassfish.jersey.media.multipart.MultiPartFeature;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.spring.scope.RequestContextFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.bridge.SLF4JBridgeHandler;

import com.fasterxml.jackson.jaxrs.json.JacksonJaxbJsonProvider;
import com.yogu.commons.Commons;
import com.yogu.commons.server.mock.MockTogetherFilter;
import com.yogu.commons.utils.HttpClientUtils;
import com.yogu.commons.utils.JsonUtils;
import com.yogu.core.Core;
import com.yogu.core.server.container.auth.SecureAnnoFeature;
import com.yogu.core.server.container.limit.FrequencyAnnoFeature;
import com.yogu.core.server.container.operation.OperationAnnoFeature;
import com.yogu.core.server.container.permit.PermitAnnoFeature;
import com.yogu.core.server.container.vali.ValidationConfigurationContextResolver;
import com.yogu.core.server.exception.mapper.ConstraintViolationExMapper;
import com.yogu.core.server.exception.mapper.ExceptionResponseMapper;
import com.yogu.core.server.exception.mapper.ParamExceptionResponseMapper;
import com.yogu.services.Services;

/**
 * 注册内容到 jersey Application
 */
public class CoreApplicationConfig extends ResourceConfig {

	private static final Logger logger = LoggerFactory.getLogger(CoreApplicationConfig.class);

	/**
	 * Register JAX-RS application components.
	 */
	public CoreApplicationConfig() {
		HttpClientUtils.referer = "http://mazing.com/";
		boolean test = Boolean.parseBoolean(System.getProperty("test"));// BaseResourceTest 中会设置 test=true

		packages(Core.class.getPackage().getName());// jax-rs base packages
		packages(Commons.class.getPackage().getName());// jax-rs commons packages
		packages(Services.class.getPackage().getName());

		register(RequestContextFilter.class);// spring context
		// register(ThreadContextFilter.class);RestForwardFilter 中做了相同的事情
		register(MockTogetherFilter.class);// mock的支持
		if (test) {
			register(LoggingFilter.class);// logger
		}

		register(ValidationConfigurationContextResolver.class);// vali
		register(ConstraintViolationExMapper.class);
		register(ParamExceptionResponseMapper.class);
		register(ExceptionResponseMapper.class);

		// add json provider（二选一）
		// register(FastJsonProvider.class);
		register(new JacksonJaxbJsonProvider(JsonUtils.mapper, JacksonJaxbJsonProvider.BASIC_ANNOTATIONS));

		register(MultiPartFeature.class);// 支持提交附件
		register(SecureAnnoFeature.class);// @SecureAuthenticator suppert
		register(PermitAnnoFeature.class);// @AcceptPermit suppert
		if (!(test)) {
			register(FrequencyAnnoFeature.class);// @FrequencyLimitation suppert
		}
		//Felix start in 2015/9/9
		register(OperationAnnoFeature.class);
		//Felix end

//		// jersey支持jsp
//		property(ServletProperties.FILTER_FORWARD_ON_404, "true");
//		property(ServletProperties.FILTER_STATIC_CONTENT_REGEX, "/(static|include)/.*");
//		register(JspMvcFeature.class);// web
//		property(JspMvcFeature.TEMPLATES_BASE_PATH, "/WEB-INF/jsp");

		// jdk logger (jul) 2 slf4j
		SLF4JBridgeHandler.removeHandlersForRootLogger();
		SLF4JBridgeHandler.install();
		logger.info("Install Jdk-util-logger to slf4j success.");
		// 触发 sms 线程的初始化
		// logger.info("Initialize sms: " + SmsServiceFactory.getInstance().getClass().getName());
	}

}
