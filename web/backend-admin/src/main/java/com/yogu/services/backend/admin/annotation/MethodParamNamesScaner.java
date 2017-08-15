package com.yogu.services.backend.admin.annotation;

import org.springframework.core.LocalVariableTableParameterNameDiscoverer;
import org.springframework.core.ParameterNameDiscoverer;

import java.lang.reflect.Method;

/**
 * from: https://gist.github.com/wendal/2011728
 * 通过读取Class文件,获得方法形参名称列表
 * @author wendal(wendal1985@gmail.com)
 * 
 */
public class MethodParamNamesScaner {

	public static String[] getMethodParamNames(Method method) {
		ParameterNameDiscoverer discoverer = new LocalVariableTableParameterNameDiscoverer();
		String[] params = discoverer.getParameterNames(method);
		if (params == null) {
			params = new String[0];
		}
		return params;
	}

}