package com.yogu.core.server.exception.mapper;

import java.lang.annotation.Annotation;
import java.util.Map;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.glassfish.jersey.server.ParamException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.yogu.core.web.RestResult;
import com.yogu.core.web.ResultCode;

/**
 * jersey框架参数异常处理 <br>
 *
 * @author JFan 2015年7月28日 下午3:37:28
 */
@Provider
public class ParamExceptionResponseMapper implements ExceptionMapper<ParamException> {

	private static final Logger logger = LoggerFactory.getLogger(ParamExceptionResponseMapper.class);

	/*
	 * （非 Javadoc）
	 * 
	 * @see javax.ws.rs.ext.ExceptionMapper#toResponse(java.lang.Throwable)
	 */
	@Override
	public Response toResponse(ParamException exception) {
		Throwable cause = exception.getCause();
		String name = exception.getParameterName();
		String defValue = exception.getDefaultStringValue();
		Class<? extends Annotation> type = exception.getParameterType();
		String message = (null != cause ? cause.getMessage() : exception.getMessage());

		String typeName = type.getSimpleName();
//		Map<String, String> error = new HashMap<>();
//		error.put("name", name);
//		error.put("type", typeName);

		RestResult<Map<String, String>> result = new RestResult<>(ResultCode.PARAMETER_ERROR, message);
		logger.error("validator#param | 参数解析失败 | name: {}, type: {}, defValue: {}, message: {}", name, typeName, defValue, message);
		return Response.status(200).entity(result).type("application/json;charset=UTF-8").build();
	}

}
