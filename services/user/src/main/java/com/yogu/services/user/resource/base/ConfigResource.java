package com.yogu.services.user.resource.base;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.yogu.commons.validation.constraints.NotEmpty;
import com.yogu.core.web.RestResult;
import com.yogu.core.web.ResultCode;
import com.yogu.services.user.config.service.IdGenService;

/**
 * 给remote基础配置的接口
 */
@Named
@Singleton
@Path("api")
@Produces("application/json;charset=UTF-8")
public class ConfigResource {
	
	private static final Logger logger = LoggerFactory.getLogger(ConfigResource.class);
	
	@Inject
	private IdGenService idGenService;
	
	/**
	 * 返回下一段可用的自增ID的范围 [start, end]。调用者在获得这段ID后，再自行进行分配。
	 * 
	 * @param idName - ID名称
	 * @return result.object=[start, end]
	 */
	@GET
	@Path(value = "id/getNextId")
	public RestResult<long[]> getNextId(@QueryParam("idName") @NotEmpty(message = "idName不能为空") String idName) {
		long[] id = idGenService.genNextIdRange(idName);
		if (id == null)
			return new RestResult<>(ResultCode.FAILURE, "获取ID失败，有可能是多个客户端一起并发获取，请重试");

		return new RestResult<>(id);
	}


}
