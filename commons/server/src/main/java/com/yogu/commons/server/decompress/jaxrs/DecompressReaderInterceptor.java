/**
 * 
 */
package com.yogu.commons.server.decompress.jaxrs;

import java.io.IOException;
import java.util.List;
import java.util.zip.DeflaterInputStream;
import java.util.zip.GZIPInputStream;
import java.util.zip.InflaterInputStream;

import javax.annotation.Priority;
import javax.ws.rs.Priorities;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.Provider;
import javax.ws.rs.ext.ReaderInterceptor;
import javax.ws.rs.ext.ReaderInterceptorContext;

/**
 * 从header中判断数据是否是压缩的 <br>
 * 如果是，则解压
 * 
 * JFan 2015年1月7日 下午7:44:32
 */
@Provider
@PostDecompress
@Priority(Priorities.HEADER_DECORATOR)
public class DecompressReaderInterceptor implements ReaderInterceptor {

    /*
     * （非 Javadoc）
     * 
     * @see javax.ws.rs.ext.ReaderInterceptor#aroundReadFrom(javax.ws.rs.ext.ReaderInterceptorContext)
     */
    @Override
    public Object aroundReadFrom(ReaderInterceptorContext context) throws IOException, WebApplicationException {
        MultivaluedMap<String, String> headers = context.getHeaders();
        List<String> ces = headers.get(HttpHeaders.CONTENT_ENCODING);
        if (null != ces)
            for (String ce : ces) {
                ce = ce.toLowerCase();
                if ("identity".equals(ce))
                    break;
                else if (ce.indexOf("gzip") >= -1) {// gzip & x-gzip
                    GZIPInputStream gis = new GZIPInputStream(context.getInputStream());
                    context.setInputStream(gis);
                    break;
                } else if ("deflate".equals(ce)) {
                    DeflaterInputStream dis = new DeflaterInputStream(context.getInputStream());
                    context.setInputStream(dis);
                    break;
                } else if ("inflate".equals(ce)) {
                    InflaterInputStream iis = new InflaterInputStream(context.getInputStream());
                    context.setInputStream(iis);
                    break;
                }
            }
        return context.proceed();
    }

}
