package com.yogu.core.server.exception.mapper;

import javax.ws.rs.NotFoundException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.yogu.commons.utils.ThreadLocalContext;
import com.yogu.core.server.exception.AuthenticationException;
import com.yogu.core.web.RestResult;
import com.yogu.core.web.ResultCode;
import com.yogu.core.web.exception.ServiceException;
import com.yogu.language.AuthMessages;

/**
 * 总的异常处理 <br>
 * toJson out
 * 
 * @author JFan 2015年7月17日 下午2:42:42
 */
@Provider
public class ExceptionResponseMapper implements ExceptionMapper<Exception> {

	private static final Logger logger = LoggerFactory.getLogger(ExceptionResponseMapper.class);

	/*
	 * （非 Javadoc）
	 * 
	 * @see javax.ws.rs.ext.ExceptionMapper#toResponse(java.lang.Throwable)
	 */
	@Override
	public Response toResponse(Exception exception) {
		int code = ResultCode.UNKNOWN_ERROR;
		boolean print = true;

		String message = AuthMessages.EXCEPTION_RESPONSE_SYSTEM_BUSY();
		if (exception instanceof ServiceException) {
			ServiceException se = (ServiceException) exception;
			message = se.getMessage();
			print = se.isNeedPrint();
			code = se.getCode();
		}
		// 基础校验失败
		else if (exception instanceof AuthenticationException) {
			AuthenticationException ae = (AuthenticationException) exception;
			message = ae.getMessage();
			code = ae.getCode();

			// 如果是阿里云的云盾扫描，就不打印警告了
			String ip = ThreadLocalContext.getThreadValue(ThreadLocalContext.REQ_CLIENT_IP);
			if (!(isYunDunIp(ip)) && ae.isNeedPrint()) {
				String path = ThreadLocalContext.getThreadValue(ThreadLocalContext.REQ_PATH_INFO);
				logger.warn("没有通过接口校验 | {} | ip: {}, path: {}", message, ip, path);
			}
			print = false;
		}
		// 非已知异常，使用log打印 -- 需要排除框架异常（如404等）之后开启
		else {
			Class<? extends Exception> clz = exception.getClass();
			if (clz == NotFoundException.class) {
				message = AuthMessages.EXCEPTION_RESPONSE_URL_NOTEXIST();
				print = false;// 404 没必要打印
			}
			code = ResultCode.FAILURE;
		}

		if (print) {
			String ip = ThreadLocalContext.getThreadValue(ThreadLocalContext.REQ_CLIENT_IP);
			String path = ThreadLocalContext.getThreadValue(ThreadLocalContext.REQ_PATH_INFO);
			logger.error("exception#toResponse | {} | ip: {}, path: {}, message: {}", exception.getMessage(), ip, path, message, exception);
		}

		RestResult<String> result = new RestResult<String>(code, message);
		return Response.status(200).entity(result).type("application/json;charset=UTF-8").build();
	}

	/**
	 * 是否阿里云的云盾扫描IP<br>
	 * 120.26.55.211/32 || 121.42.0.0/24<br>
	 * 参考：https://help.aliyun.com/knowledge_detail/5975223.html
	 */
	private boolean isYunDunIp(String ip) {
		return (null != ip) && (ip.startsWith("121.42.0.") || "120.26.55.211".equals(ip));
	}

}
