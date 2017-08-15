package com.yogu.commons.server.decompress.servlet.wrapper.inputstream;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.servlet.ServletInputStream;

/**
 * 对ServletInputStream输入流的包装<br>
 * 用于请求数据解密
 * 
 * JFan 2015年1月7日 下午12:29:27
 */
public class DecompressServletInputStream extends ServletInputStream {

    private final byte[] EMPTY_DATA = new byte[0];
    private static final int BUFFER_SIZE = 2048;

    // 已经解包后的数据
    private ByteArrayInputStream input;
    private BufferedReader reader;
    private byte[] data;

    /**
     * takes in the actual input stream that we should be buffering
     */
    public DecompressServletInputStream(InputStream stream, int length) throws IOException {
        data = (length == 0) ? EMPTY_DATA : toBytes(stream, length);
        input = new ByteArrayInputStream(data);
    }

    /*
     * （非 Javadoc）
     * 
     * @see java.io.InputStream#read()
     */
    @Override
    public int read() throws IOException {
        return input.read();
    }

    /*
     * （非 Javadoc）
     * 
     * @see java.io.InputStream#read(byte[], int, int)
     */
    @Override
    public int read(byte[] buf, int off, int len) {
        return input.read(buf, off, len);
    }

    /*
     * （非 Javadoc）
     * 
     * @see java.io.InputStream#read(byte[])
     */
    @Override
    public int read(byte[] buf) throws IOException {
        return input.read(buf);
    }

    /*
     * （非 Javadoc）
     * 
     * @see java.io.InputStream#available()
     */
    @Override
    public int available() {
        return input.available();
    }

    public byte[] getData() {
        return data;
    }

    public int getContentLength() {
        return data.length;
    }

    public BufferedReader getReader() throws IOException {
        if (null == reader)
            reader = new BufferedReader(new InputStreamReader(input));
        return reader;
    }

    private byte[] toBytes(InputStream is, int bufferSize) throws IOException {
        int bs = (bufferSize <= 0) ? BUFFER_SIZE : bufferSize;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();

        int read;
        byte[] buffer = new byte[bs];
        while (-1 != (read = is.read(buffer)))
            bos.write(buffer, 0, read);

        return bos.toByteArray();
    }

}
