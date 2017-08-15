package com.yogu.commons.server.decompress.servlet.wrapper.request;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import org.apache.http.util.Args;

import com.yogu.commons.server.decompress.servlet.wrapper.HttpAnalyticScheme;
import com.yogu.commons.server.decompress.servlet.wrapper.inputstream.DecompressServletInputStream;
import com.yogu.commons.utils.ArrayUtils;

public class NonHttpServletRequestWrapper extends HttpServletRequestWrapper {

    private DecompressServletInputStream compressServletInputStream;
    private IteratorEnumeration<String> ie;
    private Map<String, String[]> params;

    public NonHttpServletRequestWrapper(HttpServletRequest request) throws IOException {
        super(request);
        int length = request.getContentLength();
        InputStream stream = giveInputStream(request);
        compressServletInputStream = new DecompressServletInputStream(stream, length);

        // analytical
        String enc = request.getCharacterEncoding();
        String queryString = request.getQueryString();
        byte[] data = compressServletInputStream.getData();
        params = new HttpAnalyticScheme().analytical(queryString, data, enc);
    }

    /**
     * 得到一个输入流，默认是不做任何转换<br>
     * <br>
     * 请自行覆盖此方法<br>
     * 如果是压缩的数据，请包装一层后返回
     */
    public InputStream giveInputStream(HttpServletRequest request) throws IOException {
        return request.getInputStream();
    }

    /*
     * （非 Javadoc）
     * 
     * @see javax.servlet.ServletRequestWrapper#getContentLength()
     */
    @Override
    public int getContentLength() {
        return compressServletInputStream.getContentLength();
    }

    /*
     * （非 Javadoc）
     * 
     * @see javax.servlet.ServletRequestWrapper#getInputStream()
     */
    @Override
    public ServletInputStream getInputStream() throws IOException {
        return compressServletInputStream;
    }

    /*
     * （非 Javadoc）
     * 
     * @see javax.servlet.ServletRequestWrapper#getReader()
     */
    @Override
    public BufferedReader getReader() throws IOException {
        return compressServletInputStream.getReader();
    }

    // ####
    // ####
    // ####

    /*
     * （非 Javadoc）
     * 
     * @see javax.servlet.ServletRequestWrapper#getParameter(java.lang.String)
     */
    @Override
    public String getParameter(String name) {
        String[] vs = getParameterValues(name);
        if (ArrayUtils.isNotEmpty(vs))
            return vs[0];
        return null;
    }

    /*
     * （非 Javadoc）
     * 
     * @see javax.servlet.ServletRequestWrapper#getParameterMap()
     */
    @Override
    public Map<String, String[]> getParameterMap() {
        return params;
    }

    /*
     * （非 Javadoc）
     * 
     * @see javax.servlet.ServletRequestWrapper#getParameterNames()
     */
    @Override
    public Enumeration<String> getParameterNames() {
        if (null == ie)
            ie = new IteratorEnumeration<String>(params.keySet().iterator());
        return ie;
    }

    /*
     * （非 Javadoc）
     * 
     * @see javax.servlet.ServletRequestWrapper#getParameterValues(java.lang.String)
     */
    @Override
    public String[] getParameterValues(String name) {
        return params.get(name);
    }

    // ####
    // ####
    // ####

    class IteratorEnumeration<E> implements Enumeration<E> {

        private Iterator<E> it;

        public IteratorEnumeration(Iterator<E> it) {
            Args.notNull(it, "'iterator'");
            this.it = it;
        }

        /*
         * （非 Javadoc）
         * 
         * @see java.util.Enumeration#hasMoreElements()
         */
        @Override
        public boolean hasMoreElements() {
            return it.hasNext();
        }

        /*
         * （非 Javadoc）
         * 
         * @see java.util.Enumeration#nextElement()
         */
        @Override
        public E nextElement() {
            return it.next();
        }

    }

}
