package com.yogu.commons.server.decompress.jaxrs;
///**
// * 
// */
//package com.vip.commons.server.decompress.jaxrs;
//
//import java.io.IOException;
//import java.util.List;
//import java.util.zip.DeflaterInputStream;
//import java.util.zip.GZIPInputStream;
//import java.util.zip.InflaterInputStream;
//
//import javax.ws.rs.container.ContainerRequestContext;
//import javax.ws.rs.container.ContainerRequestFilter;
//import javax.ws.rs.core.MultivaluedMap;
//import javax.ws.rs.ext.Provider;
//
///**
// * <br>
// * 
// * JFan 2015年1月8日 下午7:33:06
// */
//@Provider
//@POSTDecompress
//public class DecompressContainerRequestFilter implements ContainerRequestFilter {
//
//    /*
//     * （非 Javadoc）
//     * 
//     * @see javax.ws.rs.container.ContainerRequestFilter#filter(javax.ws.rs.container.ContainerRequestContext)
//     */
//    @Override
//    public void filter(ContainerRequestContext requestContext) throws IOException {
//        MultivaluedMap<String, String> headers = requestContext.getHeaders();
//        // Content-Encoding 有可能是client声明自己支持的格式，不表示是压缩req数据，建议改用其他header
//        List<String> ces = headers.get("Content-Encoding");
//        if (null != ces)
//            for (String ce : ces) {
//                ce = ce.toLowerCase();
//                if ("identity".equals(ce))
//                    break;
//                else if (ce.indexOf("gzip") >= -1) {// gzip & x-gzip
//                    GZIPInputStream gis = new GZIPInputStream(requestContext.getEntityStream());
//                    requestContext.setEntityStream(gis);
//                    break;
//                } else if ("deflate".equals(ce)) {
//                    DeflaterInputStream dis = new DeflaterInputStream(requestContext.getEntityStream());
//                    requestContext.setEntityStream(dis);
//                    break;
//                } else if ("inflate".equals(ce)) {
//                    InflaterInputStream iis = new InflaterInputStream(requestContext.getEntityStream());
//                    requestContext.setEntityStream(iis);
//                    break;
//                }
//            }
//    }
//
//}
