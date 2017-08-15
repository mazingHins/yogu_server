package com.yogu.commons.server.compress.servlet.wrapper.response;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;

import com.yogu.commons.server.compress.servlet.wrapper.outputstream.CompressServletOutputStream;

public class NonHttpServletResponseWrapper extends HttpServletResponseWrapper {

    private CompressServletOutputStream outStream = null;
    // private HttpServletResponse response = null;
    private PrintWriter writer = null;

    public NonHttpServletResponseWrapper(HttpServletResponse response) throws IOException {
        super(response);
        // this.response = response;
        this.outStream = new CompressServletOutputStream(response);
    }

    /**
     * 得到一个输出流，默认是不做任何转换<br>
     * <br>
     * 请自行覆盖此方法<br>
     * 如果是需要压缩数据，请包装一层后返回
     */
    public OutputStream giveOutputStream(HttpServletResponse response) throws IOException {
        return response.getOutputStream();
    }

    /*
     * （非 Javadoc）
     * 
     * @see javax.servlet.ServletResponseWrapper#getOutputStream()
     */
    public ServletOutputStream getOutputStream() throws IOException {
        return this.outStream;
    }

    /*
     * （非 Javadoc）
     * 
     * @see javax.servlet.ServletResponseWrapper#getWriter()
     */
    public PrintWriter getWriter() throws IOException {
        if (this.writer == null)
            this.writer = new PrintWriter(getOutputStream());
        return this.writer;
    }

}
