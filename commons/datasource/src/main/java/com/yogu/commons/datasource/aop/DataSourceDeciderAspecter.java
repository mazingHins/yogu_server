package com.yogu.commons.datasource.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

import com.yogu.commons.datasource.DBContextHolder;
import com.yogu.commons.datasource.Source;
import com.yogu.commons.datasource.annocation.TheDataSource;

/**
 * AOP拦截，根据@TheDataSource注解决定使用那个数据源（主备）
 */
@Aspect
public class DataSourceDeciderAspecter {

	@Around(value = "@annotation(theDataSource)", argNames = "theDataSource")
	// @Around(value = "execution(* com.vip.xapi.*.service.impl.*.*(..)) && @annotation(com.vip.xapi.datasource.annocation.TheDataSource)")
	public Object setSlaveDataSource(ProceedingJoinPoint joinPoint, TheDataSource tds) throws Throwable {
		Source source = null;
		if (null == tds || (null == (source = tds.value())))
			return joinPoint.proceed();

		// set 目标数据库
		DBContextHolder.setSource(source);
		try {
			return joinPoint.proceed();
		} finally {
			DBContextHolder.clearDBType();
		}
	}

}
