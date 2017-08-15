package com.yogu.commons.server.compress.servlet.wrapper.response;

import java.io.IOException;
import java.io.OutputStream;
import java.util.zip.DeflaterOutputStream;

import javax.servlet.http.HttpServletResponse;

/**
 * <br>
 * 
 * JFan 2015年1月9日 下午3:20:42
 */
public class CompressDeflaterResponseWrapper extends NonHttpServletResponseWrapper {

    public CompressDeflaterResponseWrapper(HttpServletResponse response) throws IOException {
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
        return new DeflaterOutputStream(response.getOutputStream());
    }

}
