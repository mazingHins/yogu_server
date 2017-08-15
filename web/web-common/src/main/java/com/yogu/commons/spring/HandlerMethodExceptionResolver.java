package com.yogu.commons.spring;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.method.annotation.ExceptionHandlerExceptionResolver;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import com.yogu.core.web.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * 视图与JSON结合异常处理,有@ResponseBody方法异常，输出JSON，无@ResponseBody方法异常，输出错误页面
 * 
 * @author ten 2015/11/16
 * 
 */
public class HandlerMethodExceptionResolver extends ExceptionHandlerExceptionResolver {

	private static final Logger logger = LoggerFactory.getLogger(HandlerMethodExceptionResolver.class);

	private String defaultErrorView;

	public String getDefaultErrorView() {
		return defaultErrorView;
	}

	public void setDefaultErrorView(String defaultErrorView) {
		this.defaultErrorView = defaultErrorView;
	}

	@Override
	public ModelAndView doResolveHandlerMethodException(HttpServletRequest request, HttpServletResponse response, HandlerMethod handlerMethod, Exception exception) {
//		logger.error("exception | 异常 ...");
		if (!(null != exception && exception instanceof ServiceException))
			logger.error("出错", exception);

		if (handlerMethod == null) {
			return null;
		}
		Method method = handlerMethod.getMethod();
		if (method == null) {
			return null;
		}

		super.doResolveHandlerMethodException(request, response, handlerMethod, exception);

		ResponseBody responseBody = method.getAnnotation(ResponseBody.class);
		if (responseBody != null) {
			return handleResponseBody(request, response, exception);
		}

		return handleOther(request, response, exception);
	}

	/**
	 * 异常错误代码
	 */
	private static final int ERROR_CODE = 0;

	/**
	 * 处理异常(ResponseBody)
	 * 
	 * @author chenjianhong
	 * 
	 * @param request
	 * @param response
	 * @param exception
	 * @return
	 */
	private ModelAndView handleResponseBody(HttpServletRequest request, HttpServletResponse response, Exception exception) {
		String message = getThrowableMessage(exception);

		Map<String, Object> attributes = new HashMap<String, Object>();
		int code = ERROR_CODE;
		if (exception instanceof ServiceException) {
			code = ((ServiceException) exception).getCode();
		}
		attributes.put("code", Integer.valueOf(code));
		attributes.put("success", Boolean.FALSE);
		attributes.put("message", message);
		attributes.put("object", null);

		MappingJackson2JsonView view = new MappingJackson2JsonView();
		view.setAttributesMap(attributes);

		ModelAndView model = new ModelAndView();
		model.setView(view);

		return model;
	}

	/**
	 * 处理异常(Other)
	 * 
	 * @author chenjianhong
	 * 
	 * @param request
	 * @param response
	 * @param exception
	 * @return
	 */
	private ModelAndView handleOther(HttpServletRequest request, HttpServletResponse response, Exception exception) {
		String message = getThrowableMessage(exception);

		ModelAndView model = new ModelAndView("forward:/WEB-INF/jsp/exception.jsp");
		model.addObject("message", message);
		return model;
	}

	/**
	 * 获取异常信息(如无信息,则返回'未知错误')
	 * 
	 * @author chenjianhong
	 * 
	 * @param exception
	 * @return
	 */
	private String getThrowableMessage(Throwable exception) {
		if (exception != null) {
			String message = exception.getMessage();
			if (StringUtils.isEmpty(message)) {
				return getThrowableMessage(exception.getCause());
			}
			return message;
		}
		return "未知错误";
	}
}
