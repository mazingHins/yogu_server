/**
 * 
 */
package com.yogu.core.server.container.vali;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.List;

import javax.validation.ParameterNameProvider;
import javax.validation.Validation;
import javax.ws.rs.container.ResourceContext;
import javax.ws.rs.core.Context;
import javax.ws.rs.ext.ContextResolver;

import org.glassfish.jersey.server.validation.ValidationConfig;
import org.glassfish.jersey.server.validation.internal.InjectingConstraintValidatorFactory;

/**
 * Bean Validation Resolver <br>
 * 
 * <pre>
 * ########### JSR-303原生支持的验证注解
 * @Null 限制只能为null
 * @NotNull 限制必须不为null
 * @AssertFalse 限制必须为false
 * @AssertTrue 限制必须为true
 * @DecimalMax(value) 限制必须为一个不大于指定值的数字
 * @DecimalMin(value) 限制必须为一个不小于指定值的数字
 * @Digits(integer,fraction) 限制必须为一个小数，且整数部分的位数不能超过integer，小数部分的位数不能超过fraction
 * @Future 限制必须是一个将来的日期
 * @Max(value) 限制必须为一个不大于指定值的数字
 * @Min(value) 限制必须为一个不小于指定值的数字
 * @Past 限制必须是一个过去的日期
 * @Pattern(value) 限制必须符合指定的正则表达式
 * @Size(max,min) 限制字符长度必须在min到max之间
 * 
 * ########### 下面是Hibernate Validation提供的支持
 * @AssertTrue //用于boolean字段，该字段只能为true
 * @AssertFalse//该字段的值只能为false
 * @CreditCardNumber//对信用卡号进行一个大致的验证
 * @DecimalMax//只能小于或等于该值
 * @DecimalMin//只能大于或等于该值
 * @Digits(integer=2,fraction=20)//检查是否是一种数字的整数、分数,小数位数的数字。
 * @Email//检查是否是一个有效的email地址
 * @Future//检查该字段的日期是否是属于将来的日期
 * @Length(min=,max=)//检查所属的字段的长度是否在min和max之间,只能用于字符串
 * @Max//该字段的值只能小于或等于该值
 * @Min//该字段的值只能大于或等于该值
 * @NotNull//不能为null
 * @NotBlank//不能为空，检查时会将空格忽略
 * @NotEmpty//不能为空，这里的空是指空字符串
 * @Null//检查该字段为空
 * @Past//检查该字段的日期是在过去
 * @Size(min=, max=)//检查该字段的size是否在min和max之间，可以是字符串、数组、集合、Map等
 * @URL(protocol=,host,port)//检查是否是一个有效的URL，如果提供了protocol，host等，则该URL还需满足提供的条件
 * @Valid//该注解只要用于字段为一个包含其他对象的集合或map或数组的字段，或该字段直接为一个其他对象的引用， //这样在检查当前对象的同时也会检查该字段所引用的对象
 * </pre>
 * 
 * JFan 2014年12月4日 下午4:23:12
 */
public class ValidationConfigurationContextResolver implements ContextResolver<ValidationConfig> {

	@Context
	private ResourceContext resourceContext;

	/*
	 * （非 Javadoc）
	 * 
	 * @see javax.ws.rs.ext.ContextResolver#getContext(java.lang.Class)
	 */
	@Override
	public ValidationConfig getContext(final Class<?> type) {
		ValidationConfig vc = new ValidationConfig();
		vc.constraintValidatorFactory(resourceContext.getResource(InjectingConstraintValidatorFactory.class));
		vc.parameterNameProvider(new CustomParameterNameProvider());
		return vc;
	}

	/**
	 * See ContactCardTest#testAddInvalidContact.
	 */
	private class CustomParameterNameProvider implements ParameterNameProvider {

		private final ParameterNameProvider nameProvider;

		public CustomParameterNameProvider() {
			// Validation 具体实现，按默认形式装载
			// 需要包含实现jar包
			nameProvider = Validation.byDefaultProvider().configure().getDefaultParameterNameProvider();
		}

		/*
		 * （非 Javadoc）
		 * 
		 * @see javax.validation.ParameterNameProvider#getParameterNames(java.lang.reflect.Constructor)
		 */
		@Override
		public List<String> getParameterNames(final Constructor<?> constructor) {
			return nameProvider.getParameterNames(constructor);
		}

		/*
		 * （非 Javadoc）
		 * 
		 * @see javax.validation.ParameterNameProvider#getParameterNames(java.lang.reflect.Method)
		 */
		@Override
		public List<String> getParameterNames(final Method method) {
			return nameProvider.getParameterNames(method);
		}
	}
}
