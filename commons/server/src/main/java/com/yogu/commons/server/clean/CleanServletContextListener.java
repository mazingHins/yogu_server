/**
 * 
 */
package com.yogu.commons.server.clean;

import java.util.List;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.yogu.commons.utils.ServiceLoaderUtils;

/**
 * 在web服务关闭时，做一些清理工作 <br>
 * 
 * JFan 2015年2月4日 下午1:36:48
 */
public class CleanServletContextListener implements ServletContextListener {

	/*
	 * （非 Javadoc）
	 * 
	 * @see javax.servlet.ServletContextListener#contextInitialized(javax.servlet.ServletContextEvent)
	 */
	@Override
	public void contextInitialized(ServletContextEvent sce) {
	}

	/*
	 * （非 Javadoc）
	 * 
	 * @see javax.servlet.ServletContextListener#contextDestroyed(javax.servlet.ServletContextEvent)
	 */
	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		List<CleanProvider> cleanList = ServiceLoaderUtils.orderly(CleanProvider.class);
		if (null != cleanList)
			for (CleanProvider clean : cleanList)
				try {
					clean.cleanUp();
				} catch (Exception e) {
					// ignore
				}
	}

}
