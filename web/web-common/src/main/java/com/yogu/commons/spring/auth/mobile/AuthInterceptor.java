package com.yogu.commons.spring.auth.mobile;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.UrlPathHelper;

import com.yogu.commons.utils.Args;
import com.yogu.commons.utils.ServiceLoaderUtils;
import com.yogu.commons.utils.ThreadLocalContext;
import com.yogu.commons.utils.encrypt.AESUtil;
import com.yogu.commons.wx.AccessTokenCollector;
import com.yogu.core.base.BaseParams;
import com.yogu.core.server.auth.Authenticator;
import com.yogu.core.web.LoginInfoService;
import com.yogu.core.web.context.SecurityContext;

/**
 * mobile HTML5 项目的基础校验，转发到configapi去做校验 <br>
 * 只有指定开头的接口才做这样的校验<br>
 * <br>
 * 校验通过则写入cookie <br>
 * <br>
 * 其他接口则直接读取cookie信息
 *
 * @author JFan 2015年12月9日 下午8:35:49
 */
public class AuthInterceptor implements HandlerInterceptor {

	private static final Logger logger = LoggerFactory.getLogger(AuthInterceptor.class);

	/** cookie加密解密的密匙 */
	private final String encryptKey = "Husfj(&*8sd29234238WR*6D*2HH((-_WFDCasdf0a8sd=-13";

	/** 存储基在cookie数据的key名称 */
	private final String COOKIE_BASE_PARAM_KEY = "mbp";
	private final String COOKIE_USER_TOKEN_KEY = "mut";

	// /** cookie使用的域名 */
	// private final String domain = "m.mazing.com";
	// private final boolean secure = false;

	@Autowired
	private LoginInfoService loginInfoService;

	/**
	 * 不需要验签的路径，除了这些，其他所有都要验签。 处于安全考虑，默认全部验签。 不需要验签的，放在一个固定的路径下面，例如: /open/test/index 对于分享，可能是处于中间状态，分享的时候，查看不需要登录，比如：
	 * /open/share/store/1234 /open/share/dish/2345 如果发生订单结算，需要登录: /order/settle 2015/12/12 by ten
	 */
	private List<String> excludePaths;

	/** 权限校验器列表 */
	private List<Authenticator> authenticatorList;

	private volatile AccessTokenCollector accessTokenCollector;

	@PostConstruct
	public void initial() {
		loadAuthImpl();// 加载authenticatorList
		Args.notNull(authenticatorList, "没有装载到任何权限校验器");
		// 加载微信公众号token, 2016/1/7 ten
		accessTokenCollector = new AccessTokenCollector();
		accessTokenCollector.start();

	}

	@PreDestroy
	public void destroy() {
		// destroy
		if (accessTokenCollector != null) {
			accessTokenCollector.stopRunning();
		}
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object arg2, Exception e) throws Exception {
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object arg2, ModelAndView arg3) throws Exception {
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		// 由app封签之后转到 html 5的请求
		// 需要基础验签
		// 默认加载 cookie
		// 只有 cookie 里没有登录数据的时候，才检测是否需要验签
		// 2015/12/12 by ten
		boolean cookeLoaded = readCookie(request);
		if (!cookeLoaded && needAuth(request)) {
			// 验签
			for (Authenticator auth : authenticatorList)
				auth.authenticate(request);

			// 只要经过一次 [app封签之后转到 html 5页面] 就将最新的 BaseParams 写入到cookie中
			writeCookie(response);
		}

		return true;
	}

	/**
	 * 设置不需要验签的 path
	 * 
	 * @param excludePaths
	 */
	public void setExcludePaths(String excludePaths) {
		if (StringUtils.isNotBlank(excludePaths)) {
			String[] array = excludePaths.split(",");
			this.excludePaths = new ArrayList<>(array.length);
			for (String path : array) {
				this.excludePaths.add(path.trim().replace("*", ""));
			}
		}
	}

	/**
	 * 读取 cookie
	 * 
	 * @param request
	 * @return 读取成功返回true
	 */
	private boolean readCookie(HttpServletRequest request) {
		int loadCookieCount = 0;
		// 解读基础参数
		String bpStr = getCookie(request, COOKIE_BASE_PARAM_KEY);
		if (StringUtils.isNotBlank(bpStr)) {
			bpStr = decrypt(bpStr);
			if (StringUtils.isNotBlank(bpStr)) {
				BaseParams baseParams = string2BaseParam(bpStr);
				ThreadLocalContext.putThreadValue(SecurityContext.BASE_PARAMS, baseParams);
				loadCookieCount++;
			}
		}

		// 解读userToken（如果存在的话）
		String utStr = getCookie(request, COOKIE_USER_TOKEN_KEY);
		if (StringUtils.isNotBlank(utStr)) {
			utStr = decrypt(utStr);
			if (org.apache.commons.lang3.StringUtils.isNotBlank(utStr)) {
				// 校验ut的有效性
				// LoginInfoStore store = new LoginInfoStore();
				long uid = loginInfoService.getUidByToken(utStr, 0L);
				if (0 < uid) {
					ThreadLocalContext.putThreadValue(SecurityContext.USER_ID, uid);
					ThreadLocalContext.putThreadValue(SecurityContext.USER_TOKEN, utStr);
				} else {
					logger.warn("auth#readCookie | 用户登录状态已经失效 | ut: {}", utStr);
					// throw new ServiceException(UserErrorCode.USER_NOT_LOGIN, "用户登录状态已经失效");
				}
				loadCookieCount++;
			}
		}
		return loadCookieCount >= 2;
	}

	/**
	 * 将SecurityContext中的一些信息写入到cookie中；写入的内容有：<br>
	 * getBaseParams()<br>
	 * getUserId()；如果有<br>
	 * getUserToken()；如果有<br>
	 */
	private void writeCookie(HttpServletResponse response) {
		// 将基础参数 json化之后存储到cookie中
		BaseParams bp = SecurityContext.getBaseParams();
		String bpStr = baseParam2String(bp);
		bpStr = encrypt(bpStr);
		setCookie(response, COOKIE_BASE_PARAM_KEY, bpStr);

		// 如果当前用户登录了，将userToken存储到cookie中
		Long uid = SecurityContext.getUserId();
		String enStr = "";
		if (null != uid) {
			String userToken = SecurityContext.getUserToken();
			enStr = encrypt(userToken);
		}
		setCookie(response, COOKIE_USER_TOKEN_KEY, enStr);
		logger.info("webmobile#auth | 用户登录成功 | uid: {}", uid);
	}

	/**
	 * 将BaseParam对象中的属性按|分隔，拼接
	 */
	private String baseParam2String(BaseParams bp) {
		return new StringBuilder(StringUtils.trimToEmpty(bp.getAppKey()))//
				.append('|').append(StringUtils.trimToEmpty(bp.getAppName()))//
				.append('|').append(StringUtils.trimToEmpty(bp.getAppVersion()))//
				.append('|').append(StringUtils.trimToEmpty(bp.getDid()))//
				.append('|').append(StringUtils.trimToEmpty(bp.getSysName()))//
				.append('|').append(StringUtils.trimToEmpty(bp.getSysVersion()))//
				.append('|').append(StringUtils.trimToEmpty(bp.getTarget()))//
				.toString();
	}

	/**
	 * 将|分隔的信息解读到BaseParam对象中
	 */
	private BaseParams string2BaseParam(String text) {
		BaseParams bp = new BaseParams();
		String[] ss = StringUtils.split(text, '|');
		bp.setAppKey(readIndex(ss, 0));
		bp.setAppName(readIndex(ss, 1));
		bp.setAppVersion(readIndex(ss, 2));
		bp.setDid(readIndex(ss, 3));
		bp.setSysName(readIndex(ss, 4));
		bp.setSysVersion(readIndex(ss, 5));
		bp.setTarget(readIndex(ss, 6));
		return bp;
	}

	private String readIndex(String[] ss, int index) {
		if (ss.length > index) {
			String str = ss[index];
			return StringUtils.trimToNull(str);
		}
		return null;
	}

	/**
	 * 加密
	 */
	private String encrypt(String text) {
		try {
			return AESUtil.encrypt(encryptKey, text);
		} catch (Exception e) {
			logger.error("auth#encrypt | 加密cookie数据时发生错误 | text: '{}', message: {}", text, e.getMessage());
		}
		return "";
	}

	/**
	 * 解密
	 */
	private String decrypt(String text) {
		try {
			return AESUtil.decrypt(encryptKey, text);
		} catch (Exception e) {
			logger.error("auth#decrypt | 解密cookie数据时发生错误 | text: '{}', message: {}", text, e.getMessage());
		}
		return "";
	}

	/**
	 * 设置 cookie
	 *
	 * @param name 名称
	 * @param value 值
	 */
	private void setCookie(HttpServletResponse response, String name, String value) {
		Cookie cookie = new Cookie(name, StringUtils.trimToEmpty(value));
		cookie.setMaxAge(-1);
		cookie.setPath("/");
		cookie.setHttpOnly(true); // 禁止js读取cookie
		// cookie.setDomain(domain);
		// cookie.setSecure(secure); // 如果为true，https下才传输cookie，解决cookie劫持的问题
		// 注：防止 cookie 拷贝没有意义，如果黑客能拷贝目标用户的 cookie，他实际上可以做任何事情
		response.addCookie(cookie);
	}

	/**
	 * 读取cookie的值，自动解密
	 *
	 * @param name cookie名字
	 * @return - cookie值，不存在时为null
	 */
	private String getCookie(HttpServletRequest request, String name) {
		if (StringUtils.isBlank(name))
			return null;
		Cookie[] cookies = request.getCookies();
		if (ArrayUtils.isEmpty(cookies))
			return null;

		for (Cookie cookie : cookies) {
			if (null == cookie)
				continue;
			if (name.equals(cookie.getName()))
				return cookie.getValue();
		}

		return null;
	}

	/**
	 * 判断是否需要验签<br>
	 * 符合path配置的才需要做
	 */
	private boolean needAuth(HttpServletRequest request) {
		if (!excludePaths.isEmpty()) {
			String uri = getURI(request);
			if (StringUtils.isNotEmpty(uri)) {
				for (String path : excludePaths) {
					if (uri.startsWith(path)) {
						return false;
					}
				}
			}
		}
		return true;
	}

	/**
	 * 获取系统资源地址
	 */
	private String getURI(HttpServletRequest request) {
		UrlPathHelper helper = new UrlPathHelper();
		String uri = helper.getOriginatingRequestUri(request);
		String ctxPath = helper.getOriginatingContextPath(request);
		return uri.replaceFirst(ctxPath, "");
	}

	/**
	 * 启动时，有可能实例还没有被装载，所以走异步（需要时装载）的形式，但只初始化一次
	 */
	private List<Authenticator> loadAuthImpl() {
		if (null == authenticatorList) {
			logger.debug("Loading 'Authenticator' ......");
			authenticatorList = ServiceLoaderUtils.orderly(Authenticator.class);

			// 打印有那些校验器被装载了
			boolean one = true;
			StringBuffer str = new StringBuffer("[");
			for (Authenticator auth : authenticatorList) {
				if (!one)
					str.append(", ");
				str.append(auth.getClass());
				one = false;
			}
			str.append("]");
			logger.info("Loading 'Authenticator' size: {}, impl: {}", authenticatorList.size(), str.toString());
		}
		return authenticatorList;
	}

}
