package com.yogu.commons.server.compress.jaxrs;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.ws.rs.NameBinding;

import com.yogu.commons.server.compress.jaxrs.CompressWriterInterceptor.CompressType;

/**
 * 对输出流进行压缩 <br>
 * 用在资源方法上
 * 
 * JFan 2015年1月7日 下午7:53:53
 */
@NameBinding
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.TYPE, ElementType.METHOD })
public @interface ResponseCompress {

    public CompressType value() default CompressType.GZIP;

}
