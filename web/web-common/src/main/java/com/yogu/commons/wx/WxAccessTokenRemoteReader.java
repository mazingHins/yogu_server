package com.yogu.commons.wx;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.type.TypeReference;
import com.yogu.commons.utils.HttpClientUtils;
import com.yogu.commons.utils.JsonUtils;
import com.yogu.commons.utils.StringUtils;
import com.yogu.commons.utils.encrypt.MD5Util;

/**
 * 非米星平台公众号号的 accessToken的远程读取类<br>
 * 
 * 主要用于开饭、茶里 公众号 accessToken的获取， 通过http获取他们服务器上的accessToken
 * 
 * @author sky 2016-06-21
 *
 */
public class WxAccessTokenRemoteReader {

	private static final Logger logger = LoggerFactory.getLogger(WxAccessTokenRemoteReader.class);

	public static final short hoifan = 1;
	public static final short chaly = 2;

	// 开饭accessToken获取参数
	private static String hoifan_merchant_id = "292";
	private static String hoifan_app_key = "20141230292";
	private static String hoifan_app_secret = "ff0ba8d9bc377e65e02068c7f3837951";
	
	
	

	// 茶里 accessToken获取参数
	private static String chaly_merchant_id = "294";
	private static String chaly_app_key = "20150126294";
	private static String chaly_app_secret = "aec508db531e3a6044341d95fc8ae8c4";

	private static final String url = "http://www.hoifanmember.com/api/memberapi/getWechatToken";

	public static String readAccessToken(short type, String appid) {

		long t = System.currentTimeMillis();

		String merchantId = "";
		String appKey = "";
		String appSecret = "";

		if (type == hoifan) {

			merchantId = hoifan_merchant_id;
			appKey = hoifan_app_key;
			appSecret = hoifan_app_secret;

		} else if (type == chaly) {

			merchantId = chaly_merchant_id;
			appKey = chaly_app_key;
			appSecret = chaly_app_secret;

		}

		String result = appid + appSecret;// 源串

		String sign = MD5Util.getMD5String(result).toLowerCase();

		String reqUrl = url + "?" + "sign=" + sign + "&merchant_id=" + merchantId + "&app_key=" + appKey + "&t=" + t;

		String token = "";
		String msg = "";

		try {

			Map<String, String> params = new HashMap<>(2);
			params.put("appid", appid);

			String json = HttpClientUtils.doPost(reqUrl, params);

			if (StringUtils.isNotBlank(json)) {

				Map<String, Object> rs = JsonUtils.parseObject(json.trim(), new TypeReference<Map<String, Object>>() {
				});

				boolean success = (Boolean) rs.get("success");

				msg = (String) rs.get("msg");

				if (success) {

					token = (String) rs.get("token");

					logger.info(
							"OtherWxAccessTokenReader#readAccessToken | 获取的json数据success | type: {}, reqUrl: {}, success: {}, msg: {}, token: {}",
							type, reqUrl, success, msg, token);
				} else {

					logger.error(
							"OtherWxAccessTokenReader#readAccessToken | 获取的json数据failed | type: {}, reqUrl: {}, success: {}, msg: {}, token: {}",
							type, reqUrl, success, msg, token);
				}
			} else {

				logger.error("OtherWxAccessTokenReader#readAccessToken | 获取的json数据为空 | type: {}, reqUrl: {}", type, reqUrl);
			}
		} catch (Exception e) {

			logger.error("OtherWxAccessTokenReader#readAccessToken | 获取token 数据时出错 | type: {}, reqUrl: {}, errorMsg: {}", type, reqUrl,
					e.getMessage(), e);

		}

		return token;
	}

	public static void main(String[] args) {
		short type = hoifan;
		String appid = "wx8d9393cea44b8f70";
		readAccessToken(type, appid);
	}
}
