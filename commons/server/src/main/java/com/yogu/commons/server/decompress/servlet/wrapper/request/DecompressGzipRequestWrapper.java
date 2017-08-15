package com.yogu.commons.server.decompress.servlet.wrapper.request;

import java.io.IOException;
import java.io.InputStream;
import java.util.zip.GZIPInputStream;

import javax.servlet.http.HttpServletRequest;

public class DecompressGzipRequestWrapper extends NonHttpServletRequestWrapper {

    public DecompressGzipRequestWrapper(HttpServletRequest request) throws IOException {
        super(request);
    }

    /*
     * （非 Javadoc）
     * 
     * @see
     * com.vip.commons.server.compress.wrapper.request.NonHttpServletRequestWrapper#giveInputStream(javax.servlet.http
     * .HttpServletRequest)
     */
    @Override
    public InputStream giveInputStream(HttpServletRequest request) throws IOException {
        return new GZIPInputStream(request.getInputStream());
    }

}
