/**
 * 
 */
package com.yogu.core.web.auth;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import com.yogu.commons.utils.IpAddressUtils;
import com.yogu.commons.utils.StringUtils;
import com.yogu.core.remote.config.ConfigGroupConstants;
import com.yogu.core.remote.config.ConfigRemoteService;
import com.yogu.core.server.container.permit.PermitValidateFilter;

/**
 * 请求访问的 白名单校验 <br>
 *
 * @author JFan 2015年8月10日 上午11:29:54
 */
public class DomainIpWhitePermit extends PermitValidateFilter {

	/**
	 * IP白名单源内容以及解析之后的列表
	 */
	private String ipWhiteString;
	private Set<String> ipWhiteSet = new HashSet<String>();

	/*
	 * （非 Javadoc）
	 * 
	 * @see com.mazing.core.server.container.permit.PermitValidateFilter#validation(javax.servlet.http.HttpServletRequest)
	 */
	@Override
	public boolean validation(HttpServletRequest request) {
		if (Boolean.parseBoolean(System.getProperty("test")))// BaseResourceTest 中会设置 test=true)
			return true;

		// 读取IP
		// String uip = RequestInfo.getRequestIp(request);// 统一使用ten提供的方法
		String uip = IpAddressUtils.getClientIpAddr(request);
		// 获取白名单配置
		Map<String, String> map = ConfigRemoteService.getConfigMap(ConfigGroupConstants.DOMAIN_IP_WHITE);
		// 解析白名单（并非每次都解析）
		if (null != map) {
			String string = map.get("domainIpWhite");
			string = StringUtils.trimToEmpty(string);

			// 判断是否需要重新生成ipWhiteSet
			if (null == ipWhiteString || !ipWhiteString.equals(string)) {
				// 解析到set中
				Set<String> set = new HashSet<String>();
				for (String tmp : StringUtils.split(string, ",")) {
					tmp = StringUtils.trimToNull(tmp);
					if (null != tmp)
						set.add(tmp);
				}
				ipWhiteSet = set;
				ipWhiteString = string;
			}
		}

		// 检测是否在白名单内
		return ipWhiteSet.contains(uip);
	}

}
