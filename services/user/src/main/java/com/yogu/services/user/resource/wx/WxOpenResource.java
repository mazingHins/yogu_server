package com.yogu.services.user.resource.wx;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Named;
import javax.inject.Singleton;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import org.apache.commons.lang3.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.yogu.CommonConstants;
import com.yogu.commons.utils.JsonUtils;
import com.yogu.commons.utils.LogUtil;
import com.yogu.commons.utils.StringUtils;
import com.yogu.commons.utils.encrypt.EncryptUtil;
import com.yogu.commons.validation.constraints.NotEmpty;
import com.yogu.core.web.RestResult;
import com.yogu.core.web.ResultCode;
import com.yogu.core.web.exception.ServiceException;

@Named
@Path("open")
@Singleton
@Produces("application/json;charset=UTF-8")
public class WxOpenResource {

	private static final Logger logger = LoggerFactory.getLogger(WxOpenResource.class);


	@GET
	@Path("v1/wx/config")
	public RestResult<Map<String, Object>> getWxConfig(@QueryParam("url") @NotEmpty(message = "url不能为空") String url) {
		logger.info("api#user#getWxConfig |getWxConfig");

		return new RestResult<>(getWxMpConfig(url));
	}
	
	/**
	 * 微信分享的 config
	 * 
	 * @param url 当前页面完整的地址，包括参数
	 * @return
	 */
	public static Map<String, Object> getWxMpConfig(String url) {

		String noncestr = RandomStringUtils.random(16, CommonConstants.LETTER_NUMBER_CHARS);
		long timestamp = System.currentTimeMillis() / 1000; // 秒

		String appId = "wx41c72c58cb3ad8ce";
		String jsapiTicket = "";

		Map<String, String> amap = ChalyWxAccessTokenReader.readAccessToken();
		jsapiTicket = amap.get(WeixinAccessToken.CL_JSAPI_TICKET);

		if (StringUtils.isBlank(jsapiTicket))
			throw new ServiceException(ResultCode.FAILURE, "系统繁忙， 请稍后再试");

		logger.info("mobile#share#getWxMpConfig | 设置微信分享配置 |url: {}, KF_JSAPI_TICKET: {}", url, LogUtil.hidePassport(jsapiTicket));
		String sourceStr = "jsapi_ticket=" + jsapiTicket + "&noncestr=" + noncestr + "&timestamp=" + timestamp + "&url=" + url;

		String signature = EncryptUtil.getSHA1(sourceStr).toLowerCase();
		String[] jsApiList = new String[] { //
		"checkJsApi", //
				"onMenuShareTimeline", //
				"onMenuShareAppMessage",//
				"onMenuShareQQ",//
				"onMenuShareWeibo", //
				"onMenuShareQZone",//
				"hideAllNonBaseMenuItem",//
				"addCard",//
				"showAllNonBaseMenuItem" };
		Map<String, Object> map = new HashMap<>(8);
		map.put("nonceStr", noncestr);
		map.put("timestamp", timestamp);
		map.put("appId", appId);
		map.put("signature", signature);
		map.put("debug", false);
		map.put("jsApiList", jsApiList);
		return map;
	}
	
	

}

