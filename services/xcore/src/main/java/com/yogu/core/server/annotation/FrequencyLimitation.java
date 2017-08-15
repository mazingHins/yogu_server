package com.yogu.core.server.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 标识某个方法要进行频率检查的annotation，可选的检查内容：
 * <p>
 * （1）IP地址<br/>
 * （2）UserToken用户令牌<br/>
 * （3）DID设备号，默认
 * </p>
 * 
 * 
 * @author JFan 2015年7月17日 下午1:22:58
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.TYPE, ElementType.METHOD })
public @interface FrequencyLimitation {

	/**
	 * 限制次数
	 */
	int value() default 1;

	/**
	 * 限制条件“主体”
	 */
	FrequencyKey key() default FrequencyKey.DID;

	/**
	 * 限制条件在多久内发生
	 */
	FrequencyUnit unit() default FrequencyUnit.SECOND;

	// ####
	// ##
	// ####

	/**
	 * 描述多个限制条件之间的 “决定关系”
	 */
	public static enum FrequencyUnit {

		/**
		 * 每秒
		 */
		SECOND(1),

		/**
		 * 两秒
		 */
		SECOND_TWO(2),

		/**
		 * 十秒
		 */
		SECOND_TEN(10),

		/**
		 * 每分钟
		 */
		MINUTE(60),

		/**
		 * 每小时
		 */
		HOUR(3600)

		;

		private int second;

		private FrequencyUnit(int second) {
			this.second = second;
		}

		/**
		 * @return second
		 */
		public int getSecond() {
			return second;
		}

	}

	/**
	 * 描述限制的“条件主体”
	 */
	public static enum FrequencyKey {

		/** IP地址 */
		IP
		/** UserToken */
		, UTOKEN
		/** 设备号 */
		, DID
		/** 手机号 */
		, MOBILE
	}

}
