/**
 * 
 */
package com.yogu.commons.server.decompress.servlet;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.HttpHeaders;

import com.yogu.commons.server.decompress.servlet.wrapper.request.DecompressDeflaterRequestWrapper;
import com.yogu.commons.server.decompress.servlet.wrapper.request.DecompressGzipRequestWrapper;
import com.yogu.commons.server.decompress.servlet.wrapper.request.DecompressInflateRequestWrapper;
import com.yogu.commons.utils.StringUtils;

/**
 * 从header中判断数据是否是压缩的 <br>
 * 如果是，则解压<br>
 * Servlet实现
 * 
 * JFan 2015年1月7日 下午12:19:21
 * 
 * @deprecated 建议使用 @DecompressReaderInterceptor
 */
public class DecompressFilter implements Filter {

    private String headerName = HttpHeaders.CONTENT_ENCODING;

    /*
     * （非 Javadoc）
     * 
     * @see javax.servlet.Filter#init(javax.servlet.FilterConfig)
     */
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        String tmp = filterConfig.getInitParameter("headerName");
        tmp = StringUtils.trimToNull(tmp);
        if (null != tmp)
            headerName = tmp;
    }

    /*
     * （非 Javadoc）
     * 
     * @see javax.servlet.Filter#doFilter(javax.servlet.ServletRequest, javax.servlet.ServletResponse,
     * javax.servlet.FilterChain)
     */
    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException,
            ServletException {
        HttpServletRequest request = giveHttpServletRequest(req);

        chain.doFilter(request, resp);
    }

	/**
	 * 根据req对象中的header信息，决定使用 用解压的RequestWrapper进行包装
	 */
	protected HttpServletRequest giveHttpServletRequest(ServletRequest req) throws IOException {
		HttpServletRequest request = (HttpServletRequest) req;
		String ce = request.getHeader(headerName); // gzip|x-gzip|deflate|inflate
		if (ce != null) {
			ce = ce.toLowerCase();
			// identity break

			if (ce.indexOf("gzip") >= 0) // gzip & x-gzip
				request = new DecompressGzipRequestWrapper(request);
			else if ("deflate".equals(ce))// deflate
				request = new DecompressDeflaterRequestWrapper(request);
			else if ("inflate".equals(ce))// inflate
				request = new DecompressInflateRequestWrapper(request);
		}
		return request;
	}

    /*
     * （非 Javadoc）
     * 
     * @see javax.servlet.Filter#destroy()
     */
    @Override
    public void destroy() {
    }

}
