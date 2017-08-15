/**
 * 
 */
package com.yogu.commons.datasource.annocation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 指定哪些包作为 TypeAliasesPackage <br>
 * 
 * JFan 2014年12月15日 上午11:23:25
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.PACKAGE, ElementType.TYPE })
@Documented
public @interface TheTypeAlias {
}
