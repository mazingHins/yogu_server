/**
 * 
 */
package com.yogu.core.server.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.yogu.core.server.container.permit.PermitValidateFilter;

/**
 * 访问接口的许可类型 <br>
 * 主要用在IP白名单上 <br>
 * 需要限制访问的API请自行加上此注解（类|方法） <br>
 * <br>
 *
 * @author JFan 2015年8月7日 下午11:40:36
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.TYPE, ElementType.METHOD })
public @interface AcceptPermit {

	/**
	 * 校验器（如果有需要，请自行继承PermitValidateFilter）<br>
	 * 
	 * 目前可选的有：<br>
	 * DomainIpWhitePermit
	 */
	Class<? extends PermitValidateFilter> value();

}
