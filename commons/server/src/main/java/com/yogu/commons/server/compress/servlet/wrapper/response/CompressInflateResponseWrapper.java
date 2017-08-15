package com.yogu.commons.server.compress.servlet.wrapper.response;

import java.io.IOException;
import java.io.OutputStream;
import java.util.zip.InflaterOutputStream;

import javax.servlet.http.HttpServletResponse;

/**
 * <br>
 * 
 * JFan 2015年1月9日 下午3:20:32
 */
public class CompressInflateResponseWrapper extends NonHttpServletResponseWrapper {

    public CompressInflateResponseWrapper(HttpServletResponse response) throws IOException {
        super(response);
    }

    /*
     * （非 Javadoc）
     * 
     * @see
     * com.vip.commons.server.compress.servlet.wrapper.response.NonHttpServletResponseWrapper#giveOutputStream(javax
     * .servlet.http.HttpServletResponse)
     */
    @Override
    public OutputStream giveOutputStream(HttpServletResponse response) throws IOException {
        return new InflaterOutputStream(response.getOutputStream());
    }

}
