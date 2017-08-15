/**
 * 
 */
package com.yogu.core.server.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 秒数接口接受的时差秒数 <br>
 * 使用在Controller类、方法上 <br>
 * 使用此注解后，接口必须传递t参数（发起时间，格林威治时间秒数），并且与server端时差不能超过指定值<br>
 * 设定秒数时，必须为大于0的数<br>
 * 对比时，是判断正负值（即：比服务端时间提前、延后都接受，含） <br>
 * <br>
 * 
 * 暂时没有用
 * 
 * @author JFan 2015年7月17日 下午2:53:53
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.TYPE, ElementType.METHOD })
public @interface AcceptSeconds {

	/**
	 * 接受的时差（秒数），默认30秒（闭区间）
	 */
	int value() default 30;

}
