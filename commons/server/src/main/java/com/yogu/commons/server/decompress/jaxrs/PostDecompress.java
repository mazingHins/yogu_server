package com.yogu.commons.server.decompress.jaxrs;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.ws.rs.HttpMethod;
import javax.ws.rs.NameBinding;

/**
 * 
 * 声明这个请求上来的数据（可能）是压缩的 <br>
 * 用在资源方法上 (POST)
 * 
 * JFan 2015年1月7日 下午7:53:53
 */
@NameBinding
@HttpMethod(HttpMethod.POST)
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.TYPE, ElementType.METHOD })
public @interface PostDecompress {
}
