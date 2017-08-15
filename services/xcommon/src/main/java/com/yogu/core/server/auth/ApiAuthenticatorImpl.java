/**
 * 
 */
package com.yogu.core.server.auth;

import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.annotation.Priority;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.util.UrlPathHelper;

import com.yogu.cfg.GlobalSetting;
import com.yogu.commons.server.context.MainframeBeanFactory;
import com.yogu.commons.utils.DateUtils;
import com.yogu.commons.utils.IpAddressUtils;
import com.yogu.commons.utils.LogUtil;
import com.yogu.commons.utils.RequestUtils;
import com.yogu.commons.utils.ThreadLocalContext;
import com.yogu.commons.utils.encrypt.HMacSHA1;
import com.yogu.commons.utils.encrypt.MD5Util;
import com.yogu.commons.utils.encrypt.SignUtils;
import com.yogu.core.base.ApiReqinfoType;
import com.yogu.core.base.BaseParams;
import com.yogu.core.base.Point;
import com.yogu.core.remote.config.ConfigGroupConstants;
import com.yogu.core.remote.config.ConfigRemoteService;
import com.yogu.core.server.exception.AuthenticationException;
import com.yogu.core.web.LoginInfoService;
import com.yogu.core.web.ResultCode;
import com.yogu.core.web.UserErrorCode;
import com.yogu.core.web.context.SecurityContext;
import com.yogu.language.AuthMessages;

/**
 * APP请求验签 <br>
 * 此类存在多个实例
 * 
 * <br>
 * 
 * @author JFan 2015年7月17日 上午9:44:12
 */
@Priority(100)
public class ApiAuthenticatorImpl implements Authenticator {

	private static final Logger logger = LoggerFactory.getLogger(ApiAuthenticatorImpl.class);

	// 是否跳过t参数的校验（dev|internal环境才跳过）
	private final boolean skipT = (GlobalSetting.PROJENV_DEV.equals(GlobalSetting.getProjenv()) || GlobalSetting.PROJENV_INTERNAL
			.equals(GlobalSetting.getProjenv()));
	private final int acceptSeconds = 900;// 15分钟

	// @Inject // ServiceLoad 加载的，无法ioc
	private LoginInfoService loginInfoService;

	/**
	 * IP白名单源内容以及解析之后的列表
	 */
	private String ipWhiteString;
	private Set<String> ipWhiteSet;

	/*
	 * （非 Javadoc）
	 * 
	 * @see com.mazing.core.web.server.container.secure.auth.Authenticator#authenticate(javax.servlet.http.HttpServletRequest)
	 */
	public void authenticate(HttpServletRequest request) {

		if (Boolean.parseBoolean(System.getProperty("test")))// BaseResourceTest 中会设置 test=true
			return;

		Map<String, byte[]> fileMap = null;
		// try {
		// fileMap = readUploadFile(request);// 这里读完输入流之后，Resource中再解析的时候会出错
		// } catch (FileUploadException e) {
		// logger.error("api#interceptor | read UploadFile error | msg: {}", e.getMessage(), e);
		// }

		// 在这里取ip，避免到处取ip
		String ip = IpAddressUtils.getClientIpAddr(request);

		// APP语言
		// modify by sky 已经在RestForwardFilter中 设置(before authenticate)
		// String language = request.getParameter("lang");
		//logger.info("apiAuth#authenticate | 开始校验封签 | authenticate");
		// AppLanguage al = AppLanguage.giveLanguageOrDef(language);
		// ThreadLocalContext.putThreadValue(SecurityContext.BASE_APP_LANGUAGE, al);

		checkMultipartFileHash(fileMap, request);// 检验文件内容byte[]的合法性
		checkToken(fileMap, request, ip);// 校验token
		checkWhiteIPList(request, ip);
		checkSecurityPath(request, ip);
	}

	// ####
	// ## 校验方法
	// ####

	/**
	 * 读取request中上传的文件内容
	 */
	protected Map<String, byte[]> readUploadFile(HttpServletRequest request) throws FileUploadException {
		Map<String, byte[]> map = null;
		if (ServletFileUpload.isMultipartContent(request)) {
			map = new HashMap<String, byte[]>();
			// init
			FileItemFactory factory = new DiskFileItemFactory();
			ServletFileUpload upload = new ServletFileUpload(factory);
			// read upload
			List<FileItem> items = upload.parseRequest(request);
			for (FileItem item : items)
				if (!(item.isFormField())) {// regular form field
					String name = item.getFieldName();
					byte[] file = item.get();
					map.put(name, file);
				}
		}
		return map;
	}

	/**
	 * 如果有提交附件，那么检测附件的合法性（不合法则抛出异常）<br>
	 * 检测方法：1检测hash值是否存在，2检测hash值是否正确<br>
	 * 文件对应的hash参数为：追加Hash，例如：参数pic表示上传图片，那么picHash则是图片内容的hash值
	 */
	private void checkMultipartFileHash(Map<String, byte[]> fileMap, HttpServletRequest request) {
		if (null == fileMap)
			return;
		for (Entry<String, byte[]> entry : fileMap.entrySet()) {
			String param = entry.getKey();
			String fileName = param + "Hash";
			String fileHash = request.getParameter(fileName);
			if (StringUtils.isBlank(fileHash))
				throw new AuthenticationException(ResultCode.PARAMETER_ERROR, "'" + fileName + "' must not be empty.");

			String md5 = MD5Util.getMD5String(entry.getValue());
			if (!(fileHash.equalsIgnoreCase(md5)))
				throw new AuthenticationException(ResultCode.PARAMETER_ERROR, "'" + param + "' hash Incorrect.");
		}
	}

	/**
	 * 校验本次请求
	 */
	private void checkToken(Map<String, byte[]> fileMap, HttpServletRequest request, String ip) {
		//logger.info("apiAuth#authenticate | 开始校验封签 | checkToken");
		// 当前请求什么类型的资源
		String path = RequestUtils.getRelativePath(request);
		ApiReqinfoType type = ApiReqinfoType.pathOf(path);
		ThreadLocalContext.putThreadValue(SecurityContext.API_TYPE, type);

		// 对状态检查的URL，不做处理 2015/11/21 ten
		if ("/open/health/status".equals(path)) {
			return;
		}
		// 增加IP输出，用于查证来源 2015/11/11 ten
		logger.debug("api#interceptor | check token | path: {}, ip: {}", path, ip);

		if (null == type) {
			logger.warn("api#interceptor | Unknown Request: '{}'.", path);
			return;
		}

		// 请求需要验签
		if (type.isCheck()) {
			// 装载基础参数 -- 通用参数
			readBaseParams(request);

			// 启动信息接口不做校验，2016/02/19 jfan
			if ("/p/v1/config/app/start".equals(path) || "/p/v1/config/upgrade/check".equals(path)
					|| "/p/v1/config/operation/getBar".equals(path) || "/p/v1/config/operation/getActivity".equals(path))
				return;

			// 验签
			String sign = getSign(request);
			// 校验t参数
			boolean skip = (skipT);
			checkTime(request, skip);

			if (StringUtils.isBlank(sign))
				throw new AuthenticationException(ResultCode.PARAMETER_ERROR, "'sign' must not be empty.");
			if (type.isCheckUT() && StringUtils.isBlank(request.getParameter("ut")))
				throw new AuthenticationException(ResultCode.PARAMETER_ERROR, "'ut' must not be empty.", false);

			Map<String, String[]> params = readHeaderParams(request);
			Map<String, String[]> pm = new HashMap<String, String[]>(request.getParameterMap());
			if (null != fileMap)
				for (String exclude : fileMap.keySet())
					pm.remove(exclude);
			params.putAll(pm);
			params.remove("sign");

			if (!(check(request, params, sign, type)))
				throw new AuthenticationException(ResultCode.PARAMETER_ERROR, "sign Incorrect.");
		}else{
			String lngStr = request.getParameter("lng");
			String latStr = request.getParameter("lat");
			if (StringUtils.isNotBlank(lngStr) && StringUtils.isNotBlank(latStr)) {
				try {
					double lng = Double.parseDouble(lngStr);
					double lat = Double.parseDouble(latStr);
					ThreadLocalContext.putThreadValue(SecurityContext.BASE_POINT, new Point(lng, lat));
				} catch (NumberFormatException e) {
					throw new AuthenticationException(ResultCode.PARAMETER_ERROR, "not correct coordinate '" + lngStr + "','" + latStr + "'");
				}
			}
		}
	}

	/**
	 * 检测请求者IP是否在白名单当中（api接口才检测）<br>
	 * 不是则抛出异常
	 */
	private void checkWhiteIPList(HttpServletRequest request, String uip) {
		//logger.info("apiAuth#authenticate | 开始校验封签 | checkWhiteIPList");
		if (SecurityContext.getApiType() == ApiReqinfoType.API) {
			// 读取IP
			if (uip.startsWith("10.0.0")) {
				return;
			}
			// 校验
			Map<String, String> map = ConfigRemoteService.getConfigMap(ConfigGroupConstants.DOMAIN_IP_WHITE);
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

				// 检测是否在白名单内
				if (!(ipWhiteSet.contains(uip))) {
					String uri = request.getRequestURI();
					String query = request.getQueryString();
					logger.info("api#interceptor | 非法的 IP | ip: {}, uri: {}, query: {}", uip, uri, query);
					throw new AuthenticationException(ResultCode.PARAMETER_ERROR, "You are not in the permit IP list. IP: " + uip);
				}
			}
		}
	}

	/**
	 * 检查是不是 /api/security/，如果是，使用web验签的方式
	 * 
	 * @param request
	 * @param ip
	 */
	private void checkSecurityPath(HttpServletRequest request, String ip) {
		UrlPathHelper helper = new UrlPathHelper();
		String uri = helper.getOriginatingRequestUri(request);
		String ctxPath = helper.getOriginatingContextPath(request);
		//logger.info("apiAuth#authenticate | 开始校验封签 | checkSecurityPath");
		uri = uri.replaceFirst(ctxPath, "");
		if (uri.startsWith("/api/security/")) {
			Map<String, String[]> params = readHeaderParams(request);
			Map<String, String[]> pm = new HashMap<>(request.getParameterMap());
			params.putAll(pm);
			params.remove("sign");
			String sign = getSign(request);
			if (StringUtils.isBlank(sign)) {
				throw new AuthenticationException(ResultCode.PARAMETER_ERROR, "sign is empty.");
			}

			// 校验appKey
			String appKey = SignUtils.getParam(params, "akey", "");
			// 获取appSecret
			String secret = (appKey == null ? null : ConfigRemoteService.getConfig(ConfigGroupConstants.APP_KEY, appKey));

			if (secret == null) {
				logger.error("api#interceptor | secret=null | apikey: {}", LogUtil.hideAppKey(appKey));
			}
			String source = SignUtils.signSource(params);

			String tmpSign = HMacSHA1.getSignature(source, secret);
			if (tmpSign == null || !tmpSign.equals(sign)) {
				logger.error("api#interceptor | 签名失败 | queryString: " + request.getQueryString());
				logger.error("api#interceptor | 签名失败 | ip: {}, source: {}, sign: {}, tmpSign: {}", ip, source, sign, tmpSign);
				throw new AuthenticationException(ResultCode.PARAMETER_ERROR, "sign is incorrect.");
			}
		}
	}

	// ####

	private String getSign(HttpServletRequest request) {
		String sign = request.getHeader("mz-sign");
		if (StringUtils.isBlank(sign))
			sign = request.getParameter("sign");
		return sign;
	}

	/**
	 * 读取header参数
	 */
	private Map<String, String[]> readHeaderParams(HttpServletRequest request) {
		String startStr = "mz-";
		int startLen = startStr.length();

		Enumeration<String> headerNames = request.getHeaderNames();
		Map<String, String[]> params = new HashMap<String, String[]>();
		while (headerNames.hasMoreElements()) {
			// read keyName
			String key = headerNames.nextElement();
			if (!(key.startsWith(startStr)) || key.length() <= startLen)
				continue;
			String name = key.substring(startLen);
			name = name.trim();
			// read values
			List<String> vs = new LinkedList<String>();
			Enumeration<String> headers = request.getHeaders(key);
			while (headers.hasMoreElements())
				vs.add(headers.nextElement());
			// put
			params.put(name, vs.toArray(new String[vs.size()]));
		}
		return params;
	}

	/**
	 * 根据参数，对比封签值
	 */
	private boolean check(HttpServletRequest request, Map<String, String[]> params, String sign, ApiReqinfoType type) {
		// 校验appKey
		String appKey = SignUtils.getParam(params, "akey", "");
		// 获取appSecret
		String secret = ConfigRemoteService.getConfig(ConfigGroupConstants.APP_KEY, appKey);
		if (null == secret)
			throw new AuthenticationException(ResultCode.PARAMETER_ERROR, "'akey' is invalid.");

		// 校验userToken
		String userToken = SignUtils.getParam(params, "ut", null);
		if (null != userToken) {
			String userSecret = getUserSecret(userToken);
			if (null == userSecret) {
				String query = request.getQueryString();
				logger.error("api#interceptor | ut 参数非法 | ut: {}, uri: {}, queryString: {}", userToken, request.getRequestURI(), query);
				throw new AuthenticationException(ResultCode.TOKEN_ERROR, AuthMessages.AUTH_APIAUTH_CHECK_USERTOKEN_EMPTY(), false);
			}
			if (type.isCheckUT())
				secret = StringUtils.join(secret, userSecret);
		}

		// 计算签名摘要
		String source = SignUtils.signSource(params);
		logger.debug("api#interceptor | sign source: {}", source);
		logger.debug("api#interceptor | sign secret: {}", secret);
		String ssign = SignUtils.signHmacSha1(source, secret);
		logger.debug("api#interceptor | sign encrypt: {}", ssign);

		// if (skipSign) {
		// logger.info("api#interceptor | skip sign");
		// return true;
		// }
		return sign.equals(ssign);
	}

	/**
	 * 根据userToken，获取userSecret，如果不正确，返回null
	 */
	private String getUserSecret(String userToken) {
		// LoginInfoStore store = new LoginInfoStore();
		String secret = loginInfoService().getSecretByToken(userToken);
		if (StringUtils.isNotBlank(secret)) {
			long uid = loginInfoService().getUidByToken(userToken, 0L);
			if (uid <= 0)
				throw UserErrorCode.notLogin();
			ThreadLocalContext.putThreadValue(SecurityContext.USER_ID, uid);// 读取请使用SecurityContext
			ThreadLocalContext.putThreadValue(SecurityContext.USER_TOKEN, userToken);
		}
		return secret;
	}

	/**
	 * 从request中读取基础通用参数，并设定到对象中<br>
	 * 如果缺少 基础必要参数，则抛异常
	 */
	private void readBaseParams(HttpServletRequest request) {
		// base
		String sysVersion = getNe(request, "sver");
		String appVersion = getNe(request, "aver");
		String sysName = getNe(request, "sname");
		String appName = getNe(request, "aname");
		String appKey = getNe(request, "akey");
		String did = getNe(request, "did");

		// new add for ios, 2015-11-26 by sky 用于区分ios线上及org版本
		String target = request.getParameter("target");
		if (StringUtils.isBlank(target))
			target = "";

		BaseParams base = new BaseParams();
		// base.setT(t);
		base.setDid(did);
		base.setAppKey(appKey);
		base.setAppName(appName);
		base.setSysName(sysName);
		base.setAppVersion(appVersion);
		base.setSysVersion(sysVersion);
		base.setTarget(target);
		ThreadLocalContext.putThreadValue(SecurityContext.BASE_PARAMS, base);

		// point 两个都存在，才会“收录”
		String lngStr = request.getParameter("lng");
		String latStr = request.getParameter("lat");
		if (StringUtils.isNotBlank(lngStr) && StringUtils.isNotBlank(latStr)) {
			try {
				double lng = Double.parseDouble(lngStr);
				double lat = Double.parseDouble(latStr);
				ThreadLocalContext.putThreadValue(SecurityContext.BASE_POINT, new Point(lng, lat));
			} catch (NumberFormatException e) {
				throw new AuthenticationException(ResultCode.PARAMETER_ERROR, "not correct coordinate '" + lngStr + "','" + latStr + "'");
			}
		}

		// cityCode
		String cityCode = request.getParameter("citycode");
		if (StringUtils.isBlank(cityCode))
			throw new AuthenticationException(ResultCode.PARAMETER_ERROR, AuthMessages.AUTH_APIAUTH_CHECK_BASEPARAM_CITYCODE_EMPTY(), false);
		// // check
		// Area city = areaRemoteService().getAreaByCode(cityCode);
		// if (null == city)
		// throw new AuthenticationException(ResultCode.PARAMETER_ERROR, "not found city '" + cityCode + "'");
		ThreadLocalContext.putThreadValue(SecurityContext.CITY_CODE, cityCode);
	}

	/**
	 * 读取必填参数，没有则异常
	 */
	private String getNe(HttpServletRequest request, String p) {
		String v = request.getParameter(p);
		if (StringUtils.isBlank(v))
			throw new AuthenticationException(ResultCode.PARAMETER_ERROR, "'" + p + "' must not be empty.");
		return v;
	}

	/**
	 * 检测 t 参数的有效性
	 */
	private void checkTime(HttpServletRequest request, boolean skip) {
		// 是否跳过检测t合法性
		if (skip)
			return;

		// 请求参数中的t时间
		String tstr = getNe(request, "t");
		long time = Long.parseLong(tstr);

		long ntime = System.currentTimeMillis() / 1000;
		// 有可能是 +- 的，这里互换位置
		if (time > ntime) {
			long tmp = time;
			time = ntime;
			ntime = tmp;
		}
		// 't' is invalid.
		if ((ntime - time) > acceptSeconds) {// 失效了
			String now = DateUtils.formatDate(new Date(), DateUtils.YYYYMMDDHHMM_CH_24);
			String ip = IpAddressUtils.getClientIpAddr(request);
			String sname = request.getParameter("sname");
			String aver = request.getParameter("aver");
			String did = request.getParameter("did");
			logger.error("api#interceptor | t参数非法 | t: {}, ip: {}, did: {}, sname: {}, aver: {}", tstr, ip, did, sname, aver);
			throw new AuthenticationException(ResultCode.PARAMETER_ERROR, AuthMessages.AUTH_APIAUTH_CHECK_BASEPARAM_TIME_ILLEGAL(now), false);
		}
	}

	/**
	 * 使用时装载
	 */
	private LoginInfoService loginInfoService() {
		if (null == loginInfoService)
			loginInfoService = MainframeBeanFactory.getBean(LoginInfoService.class);
		return loginInfoService;
	}

}
