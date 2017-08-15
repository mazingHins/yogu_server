/**
 * 
 */
package com.yogu.commons.server.compress.servlet;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.HttpHeaders;

import com.yogu.commons.server.compress.servlet.wrapper.outputstream.CompressServletOutputStream;
import com.yogu.commons.server.compress.servlet.wrapper.response.CompressDeflaterResponseWrapper;
import com.yogu.commons.server.compress.servlet.wrapper.response.CompressGzipResponseWrapper;
import com.yogu.commons.server.compress.servlet.wrapper.response.CompressInflateResponseWrapper;
import com.yogu.commons.server.compress.servlet.wrapper.response.NonHttpServletResponseWrapper;

/**
 * 判断用户提交的header信息，是否支持压缩<br>
 * 如果支持则进行压缩
 * 
 * JFan 2015年1月9日 下午3:17:18
 * 
 * @deprecated 建议使用 @CompressWriterInterceptor
 */
public class CompressFilter implements Filter {

    private String headerName = HttpHeaders.ACCEPT_ENCODING;

    /*
     * （非 Javadoc）
     * 
     * @see javax.servlet.Filter#init(javax.servlet.FilterConfig)
     */
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
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
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;
        String ae = request.getHeader(headerName); // gzip|x-gzip|deflate|inflate
        if (ae != null) {
            ae = ae.toLowerCase();
            // identity break

            if (ae.indexOf("gzip") >= 0) // gzip & x-gzip
                response = new CompressGzipResponseWrapper(response);
            else if ("deflate".equals(ae))// deflate
                response = new CompressDeflaterResponseWrapper(response);
            else if ("inflate".equals(ae))// inflate
                response = new CompressInflateResponseWrapper(response);
        }

        chain.doFilter(request, resp);

        if (response instanceof NonHttpServletResponseWrapper)
            ((CompressServletOutputStream) response.getOutputStream()).finish();
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
