package com.yogu.commons.datasource.mybatis;

import java.util.Collections;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;

/**
 * MyBatis DAO的基类 <br>
 * 只提供读取的方法
 *
 * @author JFan 2015年11月18日 下午5:19:07
 */
public class MyBatisReadonlyDao extends SqlSessionDaoSupport {

	@Inject
	@Named("myBaitsReadonlySessionFactory")
	public void setSqlSessionFactory0(SqlSessionFactory sqlSessionFactory) {
		super.setSqlSessionFactory(sqlSessionFactory);
	}

	/**
	 * select一条记录
	 * 
	 * @param key - mapper里的key
	 * @param params - 参数
	 * @return
	 */
	public <T> T get(String key, Object params) {
		return (T) getSqlSession().<T> selectOne(key, params);
	}

	/**
	 * select一条记录
	 * 
	 * @param key
	 * @return
	 */
	public <T> T get(String key) {
		return (T) getSqlSession().<T> selectOne(key);
	}

	/**
	 * 返回列表
	 * 
	 * @param key - mapper里的key
	 * @return 不为null的列表
	 */
	public <T> List<T> list(String key) {
		List<T> list = getSqlSession().selectList(key);
		return (list == null ? Collections.<T> emptyList() : list);
	}

	/**
	 * 返回列表
	 * 
	 * @param key - mapper里的key
	 * @param params - 参数
	 * @return 不为null的列表
	 */
	public <T> List<T> list(String key, Object params) {
		List<T> list = getSqlSession().selectList(key, params);
		return (list == null ? Collections.<T> emptyList() : list);
	}

}
