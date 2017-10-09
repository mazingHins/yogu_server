package com.yogu.services.user.resource.api;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import com.yogu.core.remote.config.Config;
import com.yogu.core.web.RestResult;
import com.yogu.services.user.config.service.ConfigService;

/**
 * 给remote基础配置的接口
 */
@Named
@Singleton
@Path("api")
@Produces("application/json;charset=UTF-8")
public class LocalConfigApiResource {
	
	@Inject
	private ConfigService configService;
	
	/**
	 * 读取所有的系统配置
	 * 
	 * @return 返回所有的系统配置，如果没有数据，result.object=empty list
	 */
	@GET
	@Path("base/allConfigs")
	public RestResult<List<Config>> listAllConfigs() {
		List<Config> list = configService.listAll();
		return new RestResult<>(list);
	}

}
