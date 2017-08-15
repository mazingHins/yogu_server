package com.yogu.commons.server.forward;

import java.io.IOException;
import java.util.UUID;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.MDC;
import org.springframework.web.filter.OncePerRequestFilter;

import com.yogu.commons.utils.HttpClientUtils;
import com.yogu.commons.utils.ThreadLocalContext;

/**
 * 增加logger traceId
 * 
 * @author tendy 2014/2/13
 */
public class LoggerMDCFilter extends OncePerRequestFilter {

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		// 优先使用request param中的traceId
		String traceId = request.getParameter(HttpClientUtils.MDC_KEY);// /api/接口会通过 param来设置这个参数

		// 读取HTTP头 trace-id
		if (StringUtils.isBlank(traceId))
			traceId = request.getHeader(HttpClientUtils.MDC_KEY);

		// 都没找到才自己生成
		if (StringUtils.isBlank(traceId))
			traceId = StringUtils.remove(UUID.randomUUID().toString(), '-');

		// 塞到MDC上下文中
		MDC.put("traceId", traceId);
		// 塞到ThreadLocalContext ‘private’中
		ThreadLocalContext.putThreadValue(HttpClientUtils.MDC_KEY, traceId);

		try {
			filterChain.doFilter(request, response);
		} finally {
			// ThreadLocalContext ‘private’ 中的数据，由其他地方清理
			MDC.clear();
		}
	}

}
