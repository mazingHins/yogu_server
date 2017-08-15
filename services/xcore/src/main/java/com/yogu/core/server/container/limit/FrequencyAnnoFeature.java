/**
 * 
 */
package com.yogu.core.server.container.limit;

import javax.ws.rs.container.DynamicFeature;
import javax.ws.rs.container.ResourceInfo;
import javax.ws.rs.core.FeatureContext;

import com.yogu.core.server.annotation.FrequencyLimitation;
import com.yogu.core.server.annotation.FrequencyLimitation.FrequencyKey;

/**
 * 对@FrequencyLimitation的支持 <br>
 * 动态设定filter检测
 * 
 * @author JFan 2015年7月17日 下午2:23:10
 */
public class FrequencyAnnoFeature implements DynamicFeature {

	/*
	 * （非 Javadoc）
	 * 
	 * @see javax.ws.rs.container.DynamicFeature#configure(javax.ws.rs.container.ResourceInfo, javax.ws.rs.core.FeatureContext)
	 */
	@Override
	public void configure(ResourceInfo resourceInfo, FeatureContext context) {
		FrequencyLimitation fl = resourceInfo.getResourceMethod().getAnnotation(FrequencyLimitation.class);
		if (null == fl)
			fl = resourceInfo.getResourceClass().getAnnotation(FrequencyLimitation.class);

		if (null != fl) {
			int num = fl.value();
			FrequencyKey key = fl.key();
			int second = fl.unit().getSecond();
			// context.register(AmassFrequencyFilter.class);

			String className = resourceInfo.getResourceClass().getName();
			String methodName = resourceInfo.getResourceMethod().getName();

			String visitFlag = className + ":" + methodName;// 组合成访问对象方法的标识

			context.register(new AmassFrequencyFilter(num, second, key, visitFlag));
		}
	}

}
