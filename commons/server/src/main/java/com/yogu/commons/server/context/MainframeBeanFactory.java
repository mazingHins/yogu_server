package com.yogu.commons.server.context;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.apache.commons.collections.MapUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactoryUtils;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.NoUniqueBeanDefinitionException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import com.yogu.commons.utils.Args;
import com.yogu.commons.utils.ServiceLoaderUtils.PriorityComparator;

/**
 * 容器（spring）上下文工具 <br>
 * 提供静态方法读取Bean信息<br>
 * <br>
 * 需要再spring的配置中 配置这个类，如果用@的形式，有可能其他地方调用getBean的时候，本来还没初始化
 * 
 * JFan 2014年12月16日 下午1:23:39
 */
// @Named
public class MainframeBeanFactory implements ApplicationContextAware {

	/**
	 * 总配置文件路径   like /data/app/mazing/config/${PROJENV}/
	 */
	public static String configPath;

	private static ApplicationContext applicationContext;

	@PostConstruct
	public void initialCheck() {
		Args.notNull(applicationContext, "'applicationContext'");
	}

	/*
	 * （非 Javadoc）
	 * 
	 * @see org.springframework.context.ApplicationContextAware#setApplicationContext(org.springframework.context.ApplicationContext)
	 */
	public void setApplicationContext(ApplicationContext applicationContext) {
		MainframeBeanFactory.applicationContext = applicationContext;
	}

	/**
	 * 查找指定类的实现，并返回经过@Priority注解排序的第一个实例<br>
	 * 有@Priority注解，优先于没有注解<br>
	 * 注解中排序值，小的在前面
	 */
	public static <T> T getBeanOneOfType(Class<T> type) {
		Map<String, T> map = getBeansOfType(type);
		if (MapUtils.isEmpty(map))
			return null;
		Collection<T> values = map.values();
		List<T> list = new ArrayList<>(values);
		Collections.sort(list, new PriorityComparator());
		return list.get(0);
	}

	/**
	 * Return an instance, which may be shared or independent, of the specified bean.
	 * <p>
	 * This method allows a Spring BeanFactory to be used as a replacement for the Singleton or Prototype design pattern. Callers may retain
	 * references to returned objects in the case of Singleton beans.
	 * <p>
	 * Translates aliases back to the corresponding canonical bean name. Will ask the parent factory if the bean cannot be found in this
	 * factory instance.
	 * 
	 * @param name the name of the bean to retrieve
	 * @return an instance of the bean
	 * @throws NoSuchBeanDefinitionException if there is no bean definition with the specified name
	 * @throws BeansException if the bean could not be obtained
	 */
	public static Object getBean(String name) {
		return applicationContext.getBean(name);
	}

	/**
	 * Return the bean instance that uniquely matches the given object type, if any.
	 * 
	 * @param requiredType type the bean must match; can be an interface or superclass. {@code null} is disallowed.
	 *            <p>
	 *            This method goes into {@link ListableBeanFactory} by-type lookup territory but may also be translated into a conventional
	 *            by-name lookup based on the name of the given type. For more extensive retrieval operations across sets of beans, use
	 *            {@link ListableBeanFactory} and/or {@link BeanFactoryUtils}.
	 * @return an instance of the single bean matching the required type
	 * @throws NoSuchBeanDefinitionException if no bean of the given type was found
	 * @throws NoUniqueBeanDefinitionException if more than one bean of the given type was found
	 * @since 3.0
	 * @see ListableBeanFactory
	 */
	public static <T> T getBean(Class<T> requiredType) {
		return applicationContext.getBean(requiredType);
	}

	/**
	 * Return the bean instances that match the given object type (including subclasses), judging from either bean definitions or the value
	 * of {@code getObjectType} in the case of FactoryBeans.
	 * <p>
	 * <b>NOTE: This method introspects top-level beans only.</b> It does <i>not</i> check nested beans which might match the specified type
	 * as well.
	 * <p>
	 * Does consider objects created by FactoryBeans, which means that FactoryBeans will get initialized. If the object created by the
	 * FactoryBean doesn't match, the raw FactoryBean itself will be matched against the type.
	 * <p>
	 * Does not consider any hierarchy this factory may participate in. Use BeanFactoryUtils' {@code beansOfTypeIncludingAncestors} to
	 * include beans in ancestor factories too.
	 * <p>
	 * Note: Does <i>not</i> ignore singleton beans that have been registered by other means than bean definitions.
	 * <p>
	 * This version of getBeansOfType matches all kinds of beans, be it singletons, prototypes, or FactoryBeans. In most implementations,
	 * the result will be the same as for {@code getBeansOfType(type, true, true)}.
	 * <p>
	 * The Map returned by this method should always return bean names and corresponding bean instances <i>in the order of definition</i> in
	 * the backend configuration, as far as possible.
	 * 
	 * @param type the class or interface to match, or {@code null} for all concrete beans
	 * @return a Map with the matching beans, containing the bean names as keys and the corresponding bean instances as values
	 * @throws BeansException if a bean could not be created
	 * @since 1.1.2
	 * @see FactoryBean#getObjectType
	 * @see BeanFactoryUtils#beansOfTypeIncludingAncestors(ListableBeanFactory, Class)
	 */
	public static <T> Map<String, T> getBeansOfType(Class<T> type) {
		return applicationContext.getBeansOfType(type);
	}

	/**
	 * Find all beans whose {@code Class} has the supplied {@link Annotation} type,
	 * returning a Map of bean names with corresponding bean instances.
	 * @param annotationType the type of annotation to look for
	 * @return a Map with the matching beans, containing the bean names as
	 * keys and the corresponding bean instances as values
	 * @throws BeansException if a bean could not be created
	 * @since 3.0
	 */
	public static Map<String, Object> getBeansWithAnnotation(Class<? extends Annotation> annotationType) throws BeansException {
		return applicationContext.getBeansWithAnnotation(annotationType);
	}

	public void setConfigPath(String configPath) {
		Args.notNull(configPath, "'configPath'");
		MainframeBeanFactory.configPath = configPath;
	}

}