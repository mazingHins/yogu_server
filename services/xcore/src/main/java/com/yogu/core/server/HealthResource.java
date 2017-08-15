package com.yogu.core.server;

import javax.inject.Named;
import javax.inject.Singleton;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * 用于负载均衡来检测 服务是否存活 <br>
 *
 * @author JFan 2015年11月21日 下午5:57:14
 */
@Named
@Singleton
@Path("open")
@Produces("application/json;charset=UTF-8")
public class HealthResource {

	/**
	 * 用于检测服务是否存活
	 */
	@GET
	@Path("health/status")
	@Produces(MediaType.TEXT_HTML)
	public String serverStatus() {
		return "1";
	}

}
