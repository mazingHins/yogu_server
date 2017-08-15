package com.yogu.commons.server.compress.servlet.wrapper.outputstream;

import java.io.IOException;
import java.util.zip.GZIPOutputStream;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

/**
 * 对ServletOutputStream输出流的包装<br>
 * 用于对数据加密
 * 
 * JFan 2015年1月9日 下午3:10:46
 */
public class CompressServletOutputStream extends ServletOutputStream {

    // private HttpServletResponse response = null;
    private ServletOutputStream outStream;
    private GZIPOutputStream out;

    public CompressServletOutputStream(HttpServletResponse response) throws IOException {
        // this.response = response;
        this.outStream = response.getOutputStream();
        this.out = new GZIPOutputStream(this.outStream);
        response.addHeader("Content-Encoding", "gzip");
    }

    /*
     * （非 Javadoc）
     * 
     * @see java.io.OutputStream#write(int)
     */
    public void write(int b) throws IOException {
        this.out.write(b);
    }

    /*
     * （非 Javadoc）
     * 
     * @see java.io.OutputStream#write(byte[])
     */
    public void write(byte[] b) throws IOException {
        this.out.write(b);
    }

    /*
     * （非 Javadoc）
     * 
     * @see java.io.OutputStream#write(byte[], int, int)
     */
    public void write(byte[] b, int off, int len) throws IOException {
        this.out.write(b, off, len);
    }

    /*
     * （非 Javadoc）
     * 
     * @see java.io.OutputStream#close()
     */
    public void close() throws IOException {
        finish();
        this.out.close();
    }

    /*
     * （非 Javadoc）
     * 
     * @see java.io.OutputStream#flush()
     */
    public void flush() throws IOException {
        this.out.flush();
    }

    public void finish() throws IOException {
        this.out.finish();
    }

}
