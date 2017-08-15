package com.yogu.commons.datasource.mybatis;

import static org.springframework.util.StringUtils.tokenizeToStringArray;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.context.ConfigurableApplicationContext;

import com.yogu.commons.datasource.annocation.TheTypeAlias;
import com.yogu.commons.datasource.utils.LoadPackageClasses;
import com.yogu.commons.utils.CollectionUtils;
import com.yogu.commons.utils.StringUtils;

/**
 * 扩展SqlSessionFactoryBean类 <br>
 * 使具备：依据Anno进行扫描 TypeAlias 的功能<br>
 * 
 * JFan 2014年12月15日 下午12:48:15
 */
public class SqlSessionFactoryBean extends org.mybatis.spring.SqlSessionFactoryBean {

	private String typeAliasAnnoBasicPackage;

	/*
	 * （非 Javadoc）
	 * 
	 * @see org.mybatis.spring.SqlSessionFactoryBean#buildSqlSessionFactory()
	 */
	@Override
	protected SqlSessionFactory buildSqlSessionFactory() throws IOException {
		if (StringUtils.isNotEmpty(typeAliasAnnoBasicPackage)) {
			String[] typeHandlersPackageArray = tokenizeToStringArray(typeAliasAnnoBasicPackage, ConfigurableApplicationContext.CONFIG_LOCATION_DELIMITERS);
			try {
				LoadPackageClasses lpc = new LoadPackageClasses(typeHandlersPackageArray, TheTypeAlias.class);
				Set<Class<?>> classSet = lpc.getClassSet();

				Set<String> paks = new HashSet<String>();
				Set<Class<?>> clzs = new HashSet<Class<?>>();

				if (CollectionUtils.isNotEmpty(classSet))
					for (Class<?> type : classSet) {
						if (type.isAnonymousClass() || type.isMemberClass())// 匿名类 | 基本类
							continue;
						if (type.isInterface()) {// 判断是否 Package
							if ("package-info".equals(type.getSimpleName()))
								paks.add(type.getPackage().getName());
						} else {
							clzs.add(type);
						}
					}

				// setTypeAliases -- 会覆盖之前set进去的内容
				if (CollectionUtils.isNotEmpty(clzs))
					setTypeAliases(clzs.toArray(new Class<?>[clzs.size()]));
				// setTypeAliasesPackage -- 会覆盖之前set进去的内容
				if (CollectionUtils.isNotEmpty(paks)) {
					char c = ConfigurableApplicationContext.CONFIG_LOCATION_DELIMITERS.toCharArray()[0];
					StringBuilder sb = new StringBuilder();
					boolean notOne = false;
					for (String pak : paks) {
						if (notOne)
							sb.append(c);
						sb.append(pak);
						notOne = true;
					}
					setTypeAliasesPackage(sb.toString());
				}
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
		return super.buildSqlSessionFactory();
	}

	/**
	 * @param typeAliasAnnoBasicPackage 要设置的 typeAliasAnnoBasicPackage
	 */
	public void setTypeAliasAnnoBasicPackage(String typeAliasAnnoBasicPackage) {
		this.typeAliasAnnoBasicPackage = typeAliasAnnoBasicPackage;
	}

}
