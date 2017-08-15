/**
 * 
 */
package com.yogu.commons.server.compress.jaxrs;

import java.io.IOException;
import java.io.OutputStream;
import java.lang.annotation.Annotation;
import java.util.zip.DeflaterOutputStream;
import java.util.zip.GZIPOutputStream;
import java.util.zip.InflaterOutputStream;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.Provider;
import javax.ws.rs.ext.WriterInterceptor;
import javax.ws.rs.ext.WriterInterceptorContext;

/**
 * 从header中判断数据是否是压缩的 <br>
 * 如果是，则解压
 * 
 * JFan 2015年1月7日 下午7:44:32
 */
@Provider
@ResponseCompress
public class CompressWriterInterceptor implements WriterInterceptor {

    /*
     * （非 Javadoc）
     * 
     * @see javax.ws.rs.ext.WriterInterceptor#aroundWriteTo(javax.ws.rs.ext.WriterInterceptorContext)
     */
    @Override
    public void aroundWriteTo(WriterInterceptorContext context) throws IOException, WebApplicationException {
        MultivaluedMap<String, Object> headers = context.getHeaders();

        // -- check User Accept Encoding == @ResponseCompress
        // List<Object> acceptEncs = headers.get(HttpHeaders.ACCEPT_ENCODING);
        // ...

        CompressType type = null;
        Annotation[] annos = context.getAnnotations();
        if (null != annos)
            for (Annotation anno : annos)
                if (anno instanceof ResponseCompress) {
                    ResponseCompress rc = (ResponseCompress) anno;
                    type = rc.value();
                    break;
                }
        if (null == type)
            type = CompressType.GZIP;

        final OutputStream out = context.getOutputStream();
        final OutputStream newOut = type.outputStream(out);
        final String ce = type.contentEncoding();

        context.setOutputStream(newOut);
        headers.add(HttpHeaders.CONTENT_ENCODING, ce);
        context.proceed();
    }

    public static enum CompressType {
        GZIP {
            public String contentEncoding() {
                return "gzip";
            }

            public OutputStream outputStream(OutputStream os) throws IOException {
                return new GZIPOutputStream(os);
            }
        }//
        ,
        DEFLATE {
            public String contentEncoding() {
                return "deflate";
            }

            public OutputStream outputStream(OutputStream os) throws IOException {
                return new DeflaterOutputStream(os);
            }
        }//
        ,
        INFLATE {
            public String contentEncoding() {
                return "inflate";
            }

            public OutputStream outputStream(OutputStream os) throws IOException {
                return new InflaterOutputStream(os);
            }
        }//
        ;

        /**
         * 返回一个字符串，用于填充 Content-Encoding
         */
        public String contentEncoding() {
            throw new AbstractMethodError();
        }

        /**
         * 包装一个新的输出流
         */
        public OutputStream outputStream(OutputStream os) throws IOException {
            throw new AbstractMethodError();
        }
    }

}
