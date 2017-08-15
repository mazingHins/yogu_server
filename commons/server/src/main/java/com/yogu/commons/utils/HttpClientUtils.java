package com.yogu.commons.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.net.URL;
import java.net.URLConnection;
import java.net.UnknownHostException;
import java.nio.charset.Charset;
import java.security.KeyStore;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.SSLContext;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.io.IOUtils;
import org.apache.http.ConnectionReuseStrategy;
import org.apache.http.Header;
import org.apache.http.HeaderElement;
import org.apache.http.HttpEntity;
import org.apache.http.HttpException;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpResponse;
import org.apache.http.HttpResponseInterceptor;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.StatusLine;
import org.apache.http.annotation.Immutable;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.DeflateDecompressingEntity;
import org.apache.http.client.entity.GzipDecompressingEntity;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.config.ConnectionConfig;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.ConnectionKeepAliveStrategy;
import org.apache.http.conn.DnsResolver;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.FileEntity;
import org.apache.http.entity.InputStreamEntity;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.ByteArrayBody;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.impl.DefaultConnectionReuseStrategy;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultConnectionKeepAliveStrategy;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.impl.conn.SystemDefaultDnsResolver;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.pool.PoolStats;
import org.apache.http.protocol.HttpContext;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.ssl.SSLContexts;
import org.apache.http.ssl.TrustStrategy;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.yogu.commons.utils.Args;
import com.yogu.commons.utils.ArrayUtils;
import com.yogu.commons.utils.JsonUtils;
import com.yogu.commons.utils.LogUtil;
import com.yogu.commons.utils.OSUtil;
import com.yogu.commons.utils.StringUtils;
import com.yogu.commons.utils.ThreadLocalContext;

/**
 * HTTP CLIENT基础工具类 <br>
 * 
 * JFan 2015年1月20日 下午3:35:47
 */
public final class HttpClientUtils {

	private HttpClientUtils() {
	}

	/**
	 * 通过POST取得HTML资源
	 * 
	 * @param url 目标URL
	 */
	public static String doPost(String url) {
		return call(HCMethod.POST, url, null, null, null, null, null);
	}

	/**
	 * 通过POST取得HTML资源
	 * 
	 * @param url 目标URL
	 * @param timeout 请求超时时间（毫秒）
	 */
	public static String doPost(String url, int timeout) {
		return call(HCMethod.POST, url, timeout, null, null, null, null);
	}

	/**
	 * 通过POST取得HTML资源
	 * 
	 * @param url 目标URL
	 * @param params POST参数列表 Map<s, s> | Map<s, s[]> | Map<s, obj> | byte[] | InputStream | File
	 */
	public static String doPost(String url, Object params) {
		return call(HCMethod.POST, url, null, null, params, null, null);
	}

	/**
	 * 通过POST取得HTML资源
	 * 
	 * @param url 目标URL
	 * @param timeout 请求超时时间（毫秒）
	 * @param params POST参数列表 Map<s, s> | Map<s, s[]> | Map<s, obj> | byte[] | InputStream | File
	 */
	public static String doPost(String url, int timeout, Object params) {
		return call(HCMethod.POST, url, timeout, null, params, null, null);
	}

	/**
	 * 通过POST取得HTML资源
	 * 
	 * @param url 目标URL
	 * @param timeout 请求超时时间（毫秒）
	 * @param params POST参数列表 Map<s, s> | Map<s, s[]> | Map<s, obj> | byte[] | InputStream | File
	 * @param requestEncode 发送请求时的编码
	 * @param responseEncode 返回内容的编码
	 */
	public static String doPost(String url, int timeout, Object params, String requestEncode, String responseEncode) {
		return call(HCMethod.POST, url, timeout, null, params, requestEncode, responseEncode);
	}

	/**
	 * 通过POST取得HTML资源
	 * 
	 * @param url 目标URL
	 * @param headerParam HEADER参数列表
	 * @param params POST参数列表 Map<s, s> | Map<s, s[]> | Map<s, obj> | byte[] | InputStream | File
	 */
	public static String doPost(String url, Map<String, String> headers, Object params) {
		return call(HCMethod.POST, url, null, headers, params, null, null);
	}

	/**
	 * 通过POST取得HTML资源
	 * 
	 * @param url 目标URL
	 * @param timeout 请求超时时间（毫秒）
	 * @param headerParam HEADER参数列表
	 * @param params POST参数列表 Map<s, s> | Map<s, s[]> | Map<s, obj> | byte[] | InputStream | File
	 */
	public static String doPost(String url, int timeout, Map<String, String> headers, Object params) {
		return call(HCMethod.POST, url, timeout, headers, params, null, null);
	}

	/**
	 * 通过POST取得HTML资源
	 * 
	 * @param url 目标URL
	 * @param timeout 请求超时时间（毫秒）
	 * @param headers HEADER参数列表
	 * @param params POST参数列表 Map<s, s> | Map<s, s[]> | Map<s, obj> | byte[] | InputStream | File
	 * @param requestEncode 发送请求时的编码
	 * @param responseEncode 返回内容的编码
	 */
	public static String doPost(String url, int timeout, Map<String, String> headers, Object params, String requestEncode,
			String responseEncode) {
		return call(HCMethod.POST, url, timeout, headers, params, requestEncode, responseEncode);
	}

	// ########################################################################
	// ########################################################################
	// ########################################################################

	/**
	 * 通过GET获取HTML资源
	 * 
	 * @param url 目标URL
	 */
	public static String doGet(String url) {
		return call(HCMethod.GET, url, null, null, null, null, null);
	}

	/**
	 * 通过GET获取HTML资源
	 * 
	 * @param url 目标URL
	 * @param timeout 请求超时时间（毫秒）
	 */
	public static String doGet(String url, int timeout) {
		return call(HCMethod.GET, url, timeout, null, null, null, null);
	}

	/**
	 * 通过GET获取HTML资源
	 * 
	 * @param url 目标URL
	 * @param params 请求的参数
	 */
	public static String doGet(String url, Map<String, String> params) {
		return call(HCMethod.GET, url, null, null, params, null, null);
	}

	/**
	 * 通过GET获取HTML资源
	 * 
	 * @param url 目标URL
	 * @param timeout 请求超时时间（毫秒）
	 * @param params 请求的参数
	 */
	public static String doGet(String url, int timeout, Map<String, String> params) {
		return call(HCMethod.GET, url, timeout, null, params, null, null);
	}

	/**
	 * 通过GET获取HTML资源
	 * 
	 * @param url 目标URL
	 * @param timeout 请求超时时间（毫秒）
	 * @param params 请求的参数
	 * @param requestEncode 发送请求时的编码
	 * @param responseEncode 返回内容的编码
	 */
	public static String doGet(String url, int timeout, Map<String, String> params, String requestEncode, String responseEncode) {
		return call(HCMethod.GET, url, timeout, null, params, requestEncode, responseEncode);
	}

	/**
	 * 通过GET获取HTML资源
	 * 
	 * @param url 目标URL
	 * @param timeout 请求超时时间（毫秒）
	 * @param params 请求的参数
	 * @param headers HEADER参数列表
	 * @param requestEncode 发送请求时的编码
	 * @param responseEncode 返回内容的编码
	 */
	public static String doGet(String url, int timeout, Map<String, String> headers, Map<String, String> params, String requestEncode,
			String responseEncode) {
		return call(HCMethod.GET, url, timeout, headers, params, requestEncode, responseEncode);
	}

	// ########################################################################
	// ########################################################################
	// ########################################################################

	/**
	 * 通过PUT取得HTML资源
	 * 
	 * @param url 目标URL
	 */
	public static String doPut(String url) {
		return call(HCMethod.PUT, url, null, null, null, null, null);
	}

	/**
	 * 通过PUT取得HTML资源
	 * 
	 * @param url 目标URL
	 * @param timeout 请求超时时间（毫秒）
	 */
	public static String doPut(String url, int timeout) {
		return call(HCMethod.PUT, url, timeout, null, null, null, null);
	}

	/**
	 * 通过PUT取得HTML资源
	 * 
	 * @param url 目标URL
	 * @param params PUT参数列表 Map<s, s> | Map<s, s[]> | Map<s, obj> | byte[] | InputStream | File
	 */
	public static String doPut(String url, Object params) {
		return call(HCMethod.PUT, url, null, null, params, null, null);
	}

	/**
	 * 通过PUT取得HTML资源
	 * 
	 * @param url 目标URL
	 * @param timeout 请求超时时间（毫秒）
	 * @param params PUT参数列表 Map<s, s> | Map<s, s[]> | Map<s, obj> | byte[] | InputStream | File
	 */
	public static String doPut(String url, int timeout, Object params) {
		return call(HCMethod.PUT, url, timeout, null, params, null, null);
	}

	/**
	 * 通过PUT取得HTML资源
	 * 
	 * @param url 目标URL
	 * @param timeout 请求超时时间（毫秒）
	 * @param params PUT参数列表 Map<s, s> | Map<s, s[]> | Map<s, obj> | byte[] | InputStream | File
	 * @param requestEncode 发送请求时的编码
	 * @param responseEncode 返回内容的编码
	 */
	public static String doPut(String url, int timeout, Object params, String requestEncode, String responseEncode) {
		return call(HCMethod.PUT, url, timeout, null, params, requestEncode, responseEncode);
	}

	/**
	 * 通过PUT取得HTML资源
	 * 
	 * @param url 目标URL
	 * @param headerParam HEADER参数列表
	 * @param params PUT参数列表 Map<s, s> | Map<s, s[]> | Map<s, obj> | byte[] | InputStream | File
	 */
	public static String doPut(String url, Map<String, String> headers, Object params) {
		return call(HCMethod.PUT, url, null, headers, params, null, null);
	}

	/**
	 * 通过PUT取得HTML资源
	 * 
	 * @param url 目标URL
	 * @param timeout 请求超时时间（毫秒）
	 * @param headerParam HEADER参数列表
	 * @param params PUT参数列表 Map<s, s> | Map<s, s[]> | Map<s, obj> | byte[] | InputStream | File
	 */
	public static String doPut(String url, int timeout, Map<String, String> headers, Object params) {
		return call(HCMethod.PUT, url, timeout, headers, params, null, null);
	}

	/**
	 * 通过PUT取得HTML资源
	 * 
	 * @param url 目标URL
	 * @param timeout 请求超时时间（毫秒）
	 * @param headers HEADER参数列表
	 * @param params PUT参数列表 Map<s, s> | Map<s, s[]> | Map<s, obj> | byte[] | InputStream | File
	 * @param requestEncode 发送请求时的编码
	 * @param responseEncode 返回内容的编码
	 */
	public static String doPut(String url, int timeout, Map<String, String> headers, Object params, String requestEncode,
			String responseEncode) {
		return call(HCMethod.PUT, url, timeout, headers, params, requestEncode, responseEncode);
	}

	// ########################################################################
	// ########################################################################
	// ########################################################################

	/**
	 * 通过DELETE取得HTML资源
	 * 
	 * @param url 目标URL
	 */
	public static String doDelete(String url) {
		return call(HCMethod.DELETE, url, null, null, null, null, null);
	}

	/**
	 * 通过DELETE取得HTML资源
	 * 
	 * @param url 目标URL
	 * @param timeout 请求超时时间（毫秒）
	 */
	public static String doDelete(String url, int timeout) {
		return call(HCMethod.DELETE, url, timeout, null, null, null, null);
	}

	/**
	 * 通过DELETE取得HTML资源
	 * 
	 * @param url 目标URL
	 * @param params PUT参数列表 Map<s, s> | Map<s, s[]> | Map<s, obj> | byte[] | InputDELETEeam | File
	 */
	public static String doDelete(String url, Object params) {
		return call(HCMethod.DELETE, url, null, null, params, null, null);
	}

	/**
	 * 通过DELETE取得HTML资源
	 * 
	 * @param url 目标URL
	 * @param timeout 请求超时时间（毫秒）
	 * @param params DELETE参数列表 Map<s, s> | Map<s, s[]> | Map<s, obj> | byte[] | InputStream | File
	 */
	public static String doDelete(String url, int timeout, Object params) {
		return call(HCMethod.DELETE, url, timeout, null, params, null, null);
	}

	/**
	 * 通过DELETE取得HTML资源
	 * 
	 * @param url 目标URL
	 * @param timeout 请求超时时间（毫秒）
	 * @param params DELETE参数列表 Map<s, s> | Map<s, s[]> | Map<s, obj> | byte[] | InputStream | File
	 * @param requestEncode 发送请求时的编码
	 * @param responseEncode 返回内容的编码
	 */
	public static String doDelete(String url, int timeout, Object params, String requestEncode, String responseEncode) {
		return call(HCMethod.DELETE, url, timeout, null, params, requestEncode, responseEncode);
	}

	/**
	 * 通过DELETE取得HTML资源
	 * 
	 * @param url 目标URL
	 * @param headerParam HEADER参数列表
	 * @param params DELETE参数列表 Map<s, s> | Map<s, s[]> | Map<s, obj> | byte[] | InputStream | File
	 */
	public static String doDelete(String url, Map<String, String> headers, Object params) {
		return call(HCMethod.DELETE, url, null, headers, params, null, null);
	}

	/**
	 * 通过DELETE取得HTML资源
	 * 
	 * @param url 目标URL
	 * @param timeout 请求超时时间（毫秒）
	 * @param headerParam HEADER参数列表
	 * @param params DELETE参数列表 Map<s, s> | Map<s, s[]> | Map<s, obj> | byte[] | InputStream | File
	 */
	public static String doDelete(String url, int timeout, Map<String, String> headers, Object params) {
		return call(HCMethod.DELETE, url, timeout, headers, params, null, null);
	}

	/**
	 * 通过DELETE取得HTML资源
	 * 
	 * @param url 目标URL
	 * @param timeout 请求超时时间（毫秒）
	 * @param headers HEADER参数列表
	 * @param params DELETE参数列表 Map<s, s> | Map<s, s[]> | Map<s, obj> | byte[] | InputStream | File
	 * @param requestEncode 发送请求时的编码
	 * @param responseEncode 返回内容的编码
	 */
	public static String doDelete(String url, int timeout, Map<String, String> headers, Object params, String requestEncode,
			String responseEncode) {
		return call(HCMethod.DELETE, url, timeout, headers, params, requestEncode, responseEncode);
	}

	// ########################################################################
	// ########################################################################
	// ########################################################################

	/**
	 * 通过SSL安全连接POST取得HTML资源
	 * 
	 * @author Hins
	 * @date 2016年2月3日 下午5:34:03
	 * 
	 * @param url 目标URL
	 * @param params POST参数列表 Map<s, s> | Map<s, s[]> | Map<s, obj> | byte[] | InputStream | File
	 * @param sslPath - 证书地址
	 * @param password - 证书密码
	 * 
	 * @return
	 */
	public static String doPostBySSL(String url, Object params, String sslPath, String password) {
		HttpResult result = callHttp(HCMethod.POST, url, null, null, params, null, null, null, sslPath, password);
		return result.getResponse();
	}

	// ########################################################################
	// ####################### base func
	// ########################################################################

	/**
	 * 请求HTTP资源并返回
	 * 
	 * @param req HTTP提交方式
	 * @param url 目标URL
	 * @param timeout 请求超时时间（毫秒）
	 * @param headers HEADER参数列表
	 * @param params POST|PUT|DELETE参数列表(Map,String,byte[],InputStream)；如果是GET时，只支持Map<String, String>
	 * @param requestEncode 发送请求时的编码
	 * @param responseEncode 返回内容的编码
	 * @param psf 协议实现--默认信任所有证书(如果安装出错,就当做没有安装)
	 * @return 响应内容
	 * @throws Exception URL不正确|通讯异常|读取流失败|设置POST参数失败
	 */
	public static String call(HCMethod req, String url, Integer timeout, Map<String, String> headers, Object params, String requestEncode,
			String responseEncode) {
		return call(req, url, timeout, headers, params, requestEncode, responseEncode, null);
	}

	public static String call(HCMethod req, String url, Integer timeout, Map<String, String> headers, Object params, String requestEncode,
			String responseEncode, Integer bodyType) {
		HttpResult result = callHttp(req, url, timeout, headers, params, requestEncode, responseEncode, bodyType, null, null);
		return result.getResponse();
	}

	/**
	 * 请求HTTP资源并返回资源的byte
	 * 
	 * @author Hins
	 * @date 2015年10月8日 下午10:15:11
	 * 
	 * @param url - 目标URL
	 * @return
	 */
	public static byte[] readToByte(String url) {
		return callHttpToByte(url, 30000, null, null);
	}

	/**
	 * 请求HTTP资源并返回byte
	 * 
	 * @param url 目标URL
	 * @param timeout 请求超时时间（毫秒）
	 * @param params 请求参数
	 * @param requestEncode 发送请求时的编码
	 * @return byte数组
	 * @throws Exception 读取失败|关闭输入流失败
	 */
	private static byte[] callHttpToByte(String url, Integer timeout, Map<String, String> params, String requestEncode) {
		InputStream is = null;
		try {
			if (params != null)
				url = url + createLink(params);
			URL theUrl = new URL(url);

			URLConnection connection = theUrl.openConnection();
			connection.setRequestProperty("User-agent", userAgent);
			if (StringUtils.isBlank(requestEncode))
				requestEncode = DEFAULT_CHARSET_STRING;
			connection.setRequestProperty("Accept-Charset", requestEncode);
			if (timeout == null)
				timeout = 30000;
			connection.setConnectTimeout(timeout);

			int length = (int) connection.getContentLength();
			byte[] buff = new byte[length];
			is = connection.getInputStream();
			IOUtils.read(is, buff);
			return buff;
		} catch (IOException e) {
			logger.error("read url exception, e: {}" + e.getMessage());
			throw new RuntimeException(e);
		} finally {
			if (is != null) {
				try {
					is.close();
				} catch (IOException e) {
					logger.error("close inputStream exception, e: {}" + e.getMessage());
					throw new RuntimeException(e);
				}
			}
		}
	}

	/**
	 * 把数组所有元素排序，并按照“参数=参数值”的模式用“&”字符拼接成字符串
	 * 
	 * @param params 需要排序并参与字符拼接的参数组
	 * @return 拼接后字符串
	 */
	private static String createLink(Map<String, String> params) {

		List<String> keys = new ArrayList<String>(params.keySet());
		Collections.sort(keys);

		String prestr = "?";

		for (int i = 0; i < keys.size(); i++) {
			String key = keys.get(i);
			String value = params.get(key);

			if (i == keys.size() - 1) {// 拼接时，不包括最后一个&字符
				prestr = prestr + key + "=" + value;
			} else {
				prestr = prestr + key + "=" + value + "&";
			}
		}

		return prestr;
	}

	/**
	 * 请求HTTP资源并返回
	 * 
	 * @param req HTTP提交方式
	 * @param url 目标URL
	 * @param timeout 请求超时时间（毫秒）
	 * @param headers HEADER参数列表
	 * @param params POST|PUT|DELETE参数列表(Map,String,byte[],InputStream)；如果是GET时，只支持Map<String, String>
	 * @param requestEncode 发送请求时的编码
	 * @param responseEncode 返回内容的编码
	 * @param bodyType body提交模式：9：附件提交、其他：启动选择
	 * @param sslPath 所使用的证书地址
	 * @param passwd 访问存储证书的密码（同时也是key的密码）
	 * @return 响应内容
	 * @throws Exception URL不正确|通讯异常|读取流失败|设置POST参数失败
	 */
	public static HttpResult callHttp(HCMethod req, String url, Integer timeout, Map<String, String> headers, Object params,
			String requestEncode, String responseEncode, Integer bodyType, String sslPath, String passwd) {
		Args.notNull(req, "'HCMethod'");

		String newUrl = url;
		boolean isMazingApi = (newUrl.indexOf(".mazing.com/api/") > 0);

		// 请求编码
		String encode = StringUtils.trimToNull(requestEncode);
		if (null == encode)
			encode = DEFAULT_CHARSET_STRING;

		// 如果是 /api/ 请求，traceId则通过 url 来传递这个值
		String traceId = ThreadLocalContext.getThreadValue(MDC_KEY);
		String tarceIdHeader = null;
		if (StringUtils.isNotBlank(traceId)) {
			if (isMazingApi && -1 == newUrl.indexOf(".mazing.com/api/security/"))// 这个接口需要验签的，KAO
				newUrl = UrlUtils.merge2Url(newUrl, MDC_KEY, traceId);
			else
				tarceIdHeader = traceId;
		}

		// 预处理参数（只有GET需要处理）
		String requestUrl = req.mergeParam2Url(newUrl, params, encode);// 如果是GET请求，那么参数会合并到 URL中
		Object requestParams = req.giveParams(params);// 如果是GET请求，那么得到一个NULL，因为参数已经被合并到URL中了

		// 构造HTTP Method
		HttpRequestBase method = req.method(requestUrl);

		// set traceId 2 header
		if (null != tarceIdHeader)
			method.addHeader(MDC_KEY, tarceIdHeader);

		// 设置超时时间(毫秒)
		if (null != timeout) {
			RequestConfig requestConfig = RequestConfig.copy(defaultRequestConfig)//
					// .setConnectionRequestTimeout(20000)//
					// .setConnectTimeout(20000)
					.setSocketTimeout(timeout).build();
			method.setConfig(requestConfig);
		}

		// ##############
		// #### 设置HEADER

		// public header
		if (null != referer)
			method.addHeader(HttpHeaders.REFERER, referer);

		// 请求者的IP, modified by ten 2015/10/14
		String clientIP = ThreadLocalContext.getThreadValue(ThreadLocalContext.REQ_CLIENT_IP);
		if (StringUtils.isNotBlank(clientIP)) {
			if (isMazingApi)
				clientIP = OSUtil.getLocalIp();
			method.setHeader("X-Forwarded-For", clientIP);
		}

		// set langCode 2 header add by sky 2016-04-01
		String langCode = ThreadLocalContext.getThreadValue(APP_LANGUAGE);
		if (StringUtils.isNotBlank(langCode))
			method.addHeader(APP_LANGUAGE, langCode);

		// default header
		if (MapUtils.isNotEmpty(defaultHeader))
			for (Entry<String, String> entry : defaultHeader.entrySet())
				method.addHeader(entry.getKey(), entry.getValue());
		// client header
		if (MapUtils.isNotEmpty(headers))
			for (Entry<String, String> entry : headers.entrySet())
				method.addHeader(entry.getKey(), entry.getValue());

		// #### 设置HEADER结束
		// #################

		// &&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&
		// &&&& 设置post的body参数（get请求：参数已经被合并到url上了）

		// set params (body)
		if (null != requestParams) {
			HttpEntity entity = toEntity(requestParams, encode, bodyType);
			if (null != entity)
				((HttpEntityEnclosingRequestBase) method).setEntity(entity);
		}

		// &&&& 设置post的body参数 结束
		// &&&&&&&&&&&&&&&&&&&&&&&&

		// ready -- out log > all param
		req.logRequest(newUrl, headers, params);// 内部会屏蔽一些内容的打印

		// execute
		CloseableHttpClient client = giveHttpClient(newUrl, sslPath, passwd);
		CloseableHttpResponse response = null;
		try {
			// >>>>>>>> 请求资源
			long start = System.currentTimeMillis();
			response = client.execute(method);// 真正发起请求的地方
			long offset = System.currentTimeMillis() - start;
			if (offset > warnThresholdMillis)
				logConnectionStats(offset, newUrl);// 打印某个资源耗时的警告

			// <<<<<<<< 检测响应的状态码
			StatusLine status = response.getStatusLine();
			if (HttpStatus.SC_OK != status.getStatusCode())
				logger.warn("The Not normal '{}'; requet: {}", status.getStatusCode(), requestUrl);
			HttpEntity entity = response.getEntity();

			// <<<<<<<< 响应的数据内容
			Charset charset = readCharset(entity, responseEncode);// 响应编码
			byte[] byteArray = EntityUtils.toByteArray(entity);// 响应的数据内容
			String text = bodyToString(byteArray, charset, entity);// 2 string

			// log Response（debug直接打印全部内容；其他级别则只打印部分内容）
			logHttpRespone(newUrl, byteArray, charset);// 只打印一部分

			return new HttpResult(status, text);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new RuntimeException(e);
		} finally {
			if (null != response)
				org.apache.http.client.utils.HttpClientUtils.closeQuietly(response);
		}
	}

	public static void shutdown() {
		try {
			if (null != connectionManager)
				connectionManager.shutdown();
		} catch (Exception e) {
			// ignore
		}
		try {
			if (null != timer)
				timer.cancel();
		} catch (Exception e) {
			// ignore
		}
	}

	/**
	 * 根据是否要求使用证书，来构造CloseableHttpClient 实例
	 * 
	 * @param sslPath 所使用的证书地址
	 * @param passwd 访问存储证书的密码（同时也是key的密码）
	 */
	private static CloseableHttpClient giveHttpClient(String url, String sslPath, String passwd) {
		if (StringUtils.isNotBlank(sslPath)) {
			SSLConnectionSocketFactory sslsf = null;
			try {
				KeyStore keyStore = KeyStore.getInstance("PKCS12");
				FileInputStream instream = new FileInputStream(new File(sslPath));
				try {
					keyStore.load(instream, passwd.toCharArray());
				} finally {
					instream.close();
				}

				// Trust own CA and all self-signed certs
				SSLContext sslcontext = SSLContexts.custom().loadKeyMaterial(keyStore, passwd.toCharArray()).build();
				// Allow TLSv1 protocol only
				sslsf = new SSLConnectionSocketFactory(sslcontext, new String[] { "TLSv1" }, null,
						SSLConnectionSocketFactory.BROWSER_COMPATIBLE_HOSTNAME_VERIFIER);
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				throw new RuntimeException(e);
			}

			return HttpClients.custom().setSSLSocketFactory(sslsf).build();

			/*
			try {
				char[] pwd = passwd.toCharArray();
				SSLContext sslContext = SSLContexts.custom().loadKeyMaterial(new File(sslPath), pwd, pwd).build();
				return HttpClients.custom().setSSLContext(sslContext).build();
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				throw new RuntimeException(e);
			}
			 */
		}

		if (url.indexOf(".internal.mazing.com/") > -1)
			return httpClientBuilderByInternal.build();
		else
			return httpClientBuilder.build();
	}

	// #### 填充请求参数

	/**
	 * 从http响应中读取内容编码<br>
	 * 优先使用调用者要求的编码；<br>
	 * 其次读取http response中的编码；<br>
	 * 如果都没有则使用默认编码 {@link DEFAULT_CHARSET}
	 * 
	 * @param entity http响应内容
	 * @param responseEncode http调用者希望使用的编码
	 */
	private static Charset readCharset(HttpEntity entity, String responseEncode) {
		Charset charset = null;
		String encode = StringUtils.trimToNull(responseEncode);
		if (null == encode) {
			// encode = EntityUtils.getContentCharSet(entity);
			ContentType type = ContentType.get(entity);
			if (null != type)
				charset = type.getCharset();
		} else {
			charset = charset(encode);
		}
		// def
		if (null == charset)
			charset = DEFAULT_CHARSET;
		return charset;
	}

	/**
	 * 将body读取到的byte[]转换成String
	 */
	private static String bodyToString(byte[] byteArray, Charset charset, HttpEntity entity) throws IOException {
		// **** 内部会首先使用 resp中的编码，没有才使用我们指定的charset
		// String text = EntityUtils.toString(entity, charset);

		// **** 自己读取：因为要优先使用我们指定的charset
		// int i = (int) entity.getContentLength();
		// if (i < 0)
		// i = 4096;
		// final ByteArrayInputStream bais = new ByteArrayInputStream(byteArray);
		// final Reader reader = new InputStreamReader(bais, charset);
		// final CharArrayBuffer buffer = new CharArrayBuffer(i);
		// final char[] tmp = new char[1024];
		// int len;
		// while (-1 != (len = reader.read(tmp)))
		// buffer.append(tmp, 0, len);
		// String text = buffer.toString();

		String text = new String(byteArray, charset);

		return text;
	}

	/**
	 * 将参数（Object）转成HttpEntity对象<br>
	 * 
	 * bodyType的作用目前只是 强制使用multipartEntity
	 */
	private static HttpEntity toEntity(Object params, String encode, Integer bodyType) {
		HttpEntity entity = null;

		// 设置POST请求参数
		if (params instanceof Map) {
			@SuppressWarnings("unchecked")
			Map<Object, Object> map = (Map<Object, Object>) params;
			if (!(map.isEmpty())) {
				Charset charset = charset(encode);
				// bodyType = 9 强制使用 附件提交
				if (null != bodyType && 9 == bodyType)
					entity = multipartEntity(map.entrySet(), charset);
				else
					entity = mapEntity(map, charset);
			}
		} else if (params instanceof String) {
			Charset charset = charset(encode);
			entity = new StringEntity((String) params, charset);
		} else if (params instanceof byte[]) {
			entity = new ByteArrayEntity((byte[]) params);
		} else if (params instanceof InputStream) {
			entity = new InputStreamEntity((InputStream) params);
		} else if (params instanceof File) {
			entity = new FileEntity((File) params);
		} else {
			logger.error("Unbeknown Body Type <{}> : {}", params.getClass(), params);
			throw new RuntimeException("Unbeknown Body Type");
		}

		return entity;
	}

	/**
	 * Map参数转换成 HttpEntity对象
	 */
	private static HttpEntity mapEntity(Map<Object, Object> map, Charset charset) {
		HttpEntity entity = null;
		boolean useForm = true;

		Set<Entry<Object, Object>> entrySet = map.entrySet();
		// 这里的目的是：检查Map中的value，是否全部是（if）中的内容。如果是，后面则使用UrlEncodedFormEntity
		for (Entry<Object, Object> entry : entrySet) {
			Object value = entry.getValue();
			if (null != value //
					&& !(value instanceof Long) && !(value instanceof Long[]) //
					&& !(value instanceof String) && !(value instanceof String[]) //
					&& !(value instanceof Integer) && !(value instanceof Integer[])) {
				useForm = false;
				break;
			}
		}

		if (useForm) {
			List<NameValuePair> nvpList = new ArrayList<NameValuePair>();
			for (Entry<Object, Object> entry : entrySet) {
				String key = entry.getKey().toString();
				Object value = entry.getValue();

				if (null == value) {
					logger.warn("The Key '{}' Value Is Null.", key);
					nvpList.add(new BasicNameValuePair(key, StringUtils.EMPTY));
				} else if (value instanceof String[])
					for (String v : (String[]) value)
						nvpList.add(new BasicNameValuePair(key, v));
				else if (value instanceof Long[])
					for (Long v : (Long[]) value)
						nvpList.add(new BasicNameValuePair(key, v.toString()));
				else if (value instanceof Integer[])
					for (Integer v : (Integer[]) value)
						nvpList.add(new BasicNameValuePair(key, v.toString()));
				else
					nvpList.add(new BasicNameValuePair(key, value.toString()));
			}

			entity = new UrlEncodedFormEntity(nvpList, charset);
		} else {
			entity = multipartEntity(entrySet, charset);
		}

		return entity;
	}

	/**
	 * 使用附件的形式装载参数（MultipartEntity）
	 */
	private static HttpEntity multipartEntity(Set<Entry<Object, Object>> entrySet, Charset charset) {
		MultipartEntityBuilder meb = MultipartEntityBuilder.create();
		meb.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);
		for (Entry<Object, Object> entry : entrySet) {
			String key = entry.getKey().toString();
			Object value = entry.getValue();

			if (null == value) {
				logger.warn("The Key '{}' Value Is Null.", key);
				meb.addTextBody(key, StringUtils.EMPTY);
			} else if (value instanceof String)
				meb.addTextBody(key, (String) value);
			else if (value instanceof byte[])
				// meb.addBinaryBody(key, (byte[]) value, "multipart/form-data");
				meb.addPart(key, new ByteArrayBody((byte[]) value, key));
			else if (value instanceof InputStream)
				meb.addBinaryBody(key, (InputStream) value);
			else if (value instanceof File)
				meb.addPart(key, new FileBody((File) value));
			else {
				logger.error("Unbeknown MultipartBody Type <{}> : {}", value.getClass(), value);
				throw new RuntimeException("Unbeknown MultipartBody Type: " + value.getClass());
			}
		}
		meb.setCharset(charset);
		return meb.build();
	}

	/**
	 * 获取字符编码对象信息（生成Charset对象是很慢的，因为要查找系统资源）
	 */
	private static Charset charset(String charsetName) {
		Charset charset = charsetMap.get(charsetName);
		if (null == charset) {
			charset = Charset.forName(charsetName);
			charsetMap.put(charsetName, charset);
		}
		return charset;
	}

	// #### 打印日志

	/**
	 * 打印HTTP的响应，只打印一部分
	 */
	private static void logHttpRespone(String url, byte[] data, Charset charset) {
		// 这个域的响应不输出
		if (url.indexOf("api.weixin.qq.com") >= 0)
			return;

		if (ArrayUtils.isNotEmpty(data)) {
			int outLength = data.length;
			int length = (outLength >= peepingResponseLengthChar ? peepingResponseLengthChar : outLength);
			byte[] dest = new byte[length];
			System.arraycopy(data, 0, dest, 0, length);
			String result = new String(dest, charset);
			result = result.replace("\r\n", " ");

			logger.info("response length {}, content peeping: ~'{}'~", outLength, result);
		} else
			logger.warn("response content is empty");
	}

	/**
	 * 打印当前http连接池中的相关信息，给外部参考（在请求“比较久”的时候会打印）
	 */
	private static void logConnectionStats(long offset, String url) {
		PoolStats totalStats = connectionManager.getTotalStats();
		logger.warn("http take much time {} millis; http connPool max: {}, acitve: {}, leased: {}, pending: {}; url: {}"//
				, offset, totalStats.getMax(), totalStats.getAvailable(), totalStats.getLeased(), totalStats.getPending(), url);
	}

	// ####
	// ## static init
	// ## 有些常量，可以考虑写入到 CC 中去
	// ####

	private static final Logger logger = LoggerFactory.getLogger(HttpClientUtils.class);
	private static final Map<String, Charset> charsetMap = new HashMap<String, Charset>();

	private static long warnThresholdMillis = 500;// 请求超过这个值（毫秒）时，视为“比较久”
	private static int peepingResponseLengthChar = 64;// 打印 response text的前X个字符

	private static Timer timer;// 执行连接池清理任务的调度器
	private static Map<String, String> defaultHeader;
	private static RequestConfig defaultRequestConfig;
	private static HttpClientBuilder httpClientBuilder;
	private static HttpClientBuilder httpClientBuilderByInternal;
	private static PoolingHttpClientConnectionManager connectionManager;

	// 各域之间http请求需要附带的参数（key）
	public static String referer;
	public static final String MDC_KEY = "mazingTraceId";
	public static final String APP_LANGUAGE = "mazingLangCode";

	public static String DEFAULT_CHARSET_STRING = UrlUtils.DEFAULT_HTTP_ENC;
	public static Charset DEFAULT_CHARSET = charset(DEFAULT_CHARSET_STRING);

	public static String userAgent = "Mozilla/5.0 (Windows; U; Windows NT 5.1; zh-CN; rv:1.9.2) Gecko/20100115 Firefox/3.6";

	static {
		defaultRequestConfig = RequestConfig.custom()//
				// .setTargetPreferredAuthSchemes(Arrays.asList(AuthSchemes.NTLM, AuthSchemes.DIGEST))
				// .setProxyPreferredAuthSchemes(Arrays.asList(AuthSchemes.BASIC))
				// .setCookieSpec(CookieSpecs.BEST_MATCH)
				// .setStaleConnectionCheckEnabled(true)
				// .setExpectContinueEnabled(true)
				.setConnectionRequestTimeout(1000)//
				.setConnectTimeout(1000)//
				.setSocketTimeout(5000)//
				.build();

		ConnectionConfig conConfig = ConnectionConfig.custom()//
				// .setUnmappableInputAction(CodingErrorAction.IGNORE)
				// .setMalformedInputAction(CodingErrorAction.IGNORE)
				.setCharset(DEFAULT_CHARSET)//
				.setBufferSize(8192)//
				.build();

		// SocketConfig socketConfig = SocketConfig.custom()//
		// .setTcpNoDelay(true)//
		// .build();

		// ## SSL
		SSLConnectionSocketFactory socketFactory = null;
		SSLContext sslContext = null;
		try {
			// 全部信任ssl证书
			sslContext = SSLContextBuilder.create().loadTrustMaterial(null, new AllTrust()).build();
			// 将sslContext放到socket连接工厂中
			socketFactory = new SSLConnectionSocketFactory(sslContext);
		} catch (Exception e) {
			logger.error("Create SSL Context ERROR. <auto ignore>", e);
		}
		// 如果上面的ssl构造失败了，使用默认的
		if (null == socketFactory)
			socketFactory = SSLConnectionSocketFactory.getSocketFactory();

		// default, copy PoolingHttpClientConnectionManager 97
		Registry<ConnectionSocketFactory> defaultRegistry = RegistryBuilder.<ConnectionSocketFactory> create()//
				.register("http", PlainConnectionSocketFactory.getSocketFactory())//
				.register("https", socketFactory)//
				.build();

		// DnsResolver dnsResolver = new ConfigDnsResolver();
		DnsResolver dnsResolver = SystemDefaultDnsResolver.INSTANCE;// default

		// ## ConnectionManager
		connectionManager = new PoolingHttpClientConnectionManager(defaultRegistry, dnsResolver);
		connectionManager.setMaxTotal(3000);// 线程池总大小
		connectionManager.setDefaultMaxPerRoute(300);// 每一个Host默认的最大线程池大小
		// connectionManager.setDefaultSocketConfig(socketConfig);
		connectionManager.setDefaultConnectionConfig(conConfig);

		// 配置独立的池大小（host） -- 不同的域分配不同的资源“宽松度”
		// Map<URL, Integer> routeMaxMap = null;
		// for (URL url : routeMaxMap.keySet()) {
		// HttpHost host = new HttpHost(url.getHost(), url.getPort(), url.getProtocol());
		// connectionManager.setMaxPerRoute(new HttpRoute(host), routeMaxMap.get(url));
		// }

		// ## HttpClientBuilder
		httpClientBuilder = HttpClients.custom()//
				// .setRetryHandler(new DefaultHttpRequestRetryHandler())// 默认重试3次（查看构造方法，有4中异常不会重试的）
				.setUserAgent(userAgent)//
				// .setRetryHandler(retryHandler) // 设置超时重试次数(策略)
				.setConnectionManager(connectionManager)//
				// .setKeepAliveStrategy(keepAliveStrategy)
				.setDefaultRequestConfig(defaultRequestConfig)

		// 参考 HttpClientBuilder(4.3) 841 行代码，关闭是有原因的，看VipResponseContentEncoding的注释，手动增加Interceptor的方式来开启gzip
		// .disableContentCompression()// 禁用内用压缩（gzip），默认开启的
		// .addInterceptorFirst(new RequestAcceptEncoding())//
		// .addInterceptorFirst(new VipResponseContentEncoding())//
		;

		// 内网api环境的 HttpClientBuilder
		httpClientBuilderByInternal = HttpClients.custom()//
				.setUserAgent(userAgent)//
				// .setRetryHandler(new DefaultHttpRequestRetryHandler(0, false))// 不重试
				.setConnectionManager(connectionManager)//
				.setKeepAliveStrategy(new ApiConnectionKeepAliveStrategy())// 外网使用默认配置（从header中读取长连接timeout，没有则-1永久），内网直接默认12s（slb默认15秒断开）
				// .setConnectionReuseStrategy(new ApiConnectionReuseStrategy())// 负载均衡 长连接 最大复用次数为100，这里检测使用了 95次则不再复用（未实现）
				.setDefaultRequestConfig(RequestConfig.copy(defaultRequestConfig).setSocketTimeout(2000).build());// 外网默认5秒，内网默认2秒

		// HttpRoutePlanner >>>> 必须是线程安全的
		// httpClientBuilder.setRoutePlanner(new HttpRoutePlanner() {
		// @Override
		// public HttpRoute determineRoute(HttpHost target, HttpRequest request, HttpContext context) throws
		// HttpException {
		// boolean r = (1 == System.currentTimeMillis() % 2);
		// String host = "127.0.0.1";
		// int port = 8081;
		// if (r) {
		// host = "192.168.33.214";
		// port = 9090;
		// }
		//
		// System.out.println("TO host: " + host + ", port: " + port);
		// HttpHost hh = new HttpHost(host, port);
		// return new HttpRoute(hh);
		// }
		// });

		// ####
		// 设置连接池清理任务的调度
		timer = new Timer();
		long idleMilli = 10000;// 清理空闲了10秒的连接（slb是空闲15秒就断开）
		timer.scheduleAtFixedRate(new IdleConnectionMonitor(idleMilli), 30000, 10000);// 30秒后开始运行，之后每隔10秒执行一次
		// TODO slb 空闲15秒就断开，但是我们是 10+10 = 最久20秒，所以还是有几率会发生使用了已经断开的连接
	}

	// ####
	// ## 下面几个set方法，是用来动态设置 httpClientUtil 的参数
	// ####

	/**
	 * 设置Http请求的默认Header参数（有可能会被覆盖）<br>
	 * 允许设置null<br>
	 * <br>
	 * 此方法设置的header为全局的<br>
	 */
	protected static void setDefaultHeader(Map<String, String> header) {
		// 为了避免传递进来的header（引用）在其他地方又使用
		// 而导致 httpClient发送的header会变很大，所以这是new一个新的Map
		if (null == header)
			defaultHeader = null;
		else
			defaultHeader = new HashMap<>(header);
	}

	public static void setDefaultCharset(String charset) {
		Charset cs = charset(charset);
		Args.check(null != cs, "'" + charset + "' Charset Not found.");

		logger.info("Change default Encoding; {} -> {}", DEFAULT_CHARSET_STRING, charset);
		DEFAULT_CHARSET_STRING = charset;
		DEFAULT_CHARSET = cs;

		logger.info("Change default Encoding, Set connection pool;");
		ConnectionConfig defConnConfig = connectionManager.getDefaultConnectionConfig();
		defConnConfig = ConnectionConfig.copy(defConnConfig).setCharset(cs).build();
		connectionManager.setDefaultConnectionConfig(defConnConfig);
		logger.debug("Change default Encoding, Set connection pool; Success!");
	}

	public static void setDefaultSocketTimeout(int socketTimeout) {
		Args.check(0 < socketTimeout, "'socketTimeout' Should be greater than zero.");

		logger.info("Change Default SocketTimeout; {} -> {}", defaultRequestConfig.getSocketTimeout(), socketTimeout);
		defaultRequestConfig = RequestConfig.copy(defaultRequestConfig).setSocketTimeout(socketTimeout).build();
	}

	public static void setWarnThresholdMillis(int millis) {
		Args.check(0 < millis, "'warnThresholdMillis' Should be greater than zero.");

		logger.info("Change WarnThresholdMillis; {} -> {}", warnThresholdMillis, millis);
		warnThresholdMillis = millis;
	}

	public static void setPeepingResponseLengthChar(int length) {
		Args.check(0 < length, "'peepingResponseLengthChar' Should be greater than zero.");

		logger.info("Change PeepingResponseLengthChar; {} -> {}", peepingResponseLengthChar, length);
		peepingResponseLengthChar = length;
	}

	public static void setUserAgent(String userAgent) {
		Args.notBlank(userAgent, "'userAgent'");

		logger.info("Change UserAgent; {} -> {}", HttpClientUtils.userAgent, userAgent);
		HttpClientUtils.userAgent = userAgent;
	}

	// ####
	// ##
	// ####

	public static enum HCMethod {

		PUT {
			public HttpRequestBase method(String url) {
				return new HttpPut(url);
			}
		},
		POST {
			public HttpRequestBase method(String url) {
				return new HttpPost(url);
			}
		},
		DELETE {
			public HttpRequestBase method(String url) {
				return new HttpDelete(url);
			}
		},
		GET {
			public String mergeParam2Url(String url, Object params, String encode) {
				String requestUrl = url;
				@SuppressWarnings("unchecked")
				Map<String, String> tmp = (Map<String, String>) params;
				if (null != tmp && !tmp.isEmpty())
					requestUrl = UrlUtils.mergeParam2Url(tmp, requestUrl, encode);
				return requestUrl;
			}

			public Object giveParams(Object params) {
				return null;
			}

			public HttpRequestBase method(String url) {
				return new HttpGet(url);
			}
		};

		/**
		 * 将参数合并到url中（只有GET需要合并）
		 */
		public String mergeParam2Url(String url, Object params, String encode) {
			return url;
		}

		/**
		 * 给予请求的Body参数（只有GET需要return null）
		 */
		public Object giveParams(Object params) {
			return params;
		}

		/**
		 * 实例化HTTP Method
		 */
		public HttpRequestBase method(String url) {
			throw new AbstractMethodError();
		}

		/**
		 * 打印 请求 的url 及 各 参数 <br>
		 * 对一些安全级别比较高的参数进行hide处理
		 * 
		 * @param url 请求的url, 没有拼接参数
		 * @param headers 请求头
		 * @param params 参数
		 * @author sky
		 */
		public void logRequest(String url, Map<String, String> headers, Object params) {
			// params的类型可能有：Map<s, s> | Map<s, s[]> | Map<s, obj> | byte[] | InputStream | File
			if (!(params instanceof Map)) {
				requestLog(url, headers, null);
				return;
			}

			@SuppressWarnings("unchecked")
			Map<String, Object> p = (Map<String, Object>) params;
			Map<String, String> paraMap = new HashMap<>();

			// 处理params中保密性参数
			if (!(paraMap.isEmpty())) {
				for (Entry<String, Object> entry : p.entrySet()) {
					String key = entry.getKey();
					Object value = entry.getValue();
					if (StringUtils.isBlank(key) || null == value)
						continue;

					String value_ = null;
					if (value instanceof String)
						value_ = LogUtil.dealShowValue(key, (String) value);
					else if (value instanceof String[]) {
						String[] vss = (String[]) value;
						String[] vs = new String[vss.length];
						for (int index = 0; index < vss.length; index++)
							vs[index] = LogUtil.dealShowValue(key, vss[index]);
						value_ = Arrays.toString(vs);
					} else
						continue;

					paraMap.put(key, value_);
				}
			}

			// 处理headers 中保密性参数
			if (null != headers && !(headers.isEmpty())) {
				for (Entry<String, String> entry : headers.entrySet()) {
					String key = entry.getKey();
					String value = entry.getValue();
					if (StringUtils.isBlank(key) || null == value)
						continue;

					value = LogUtil.dealShowValue(key, value);
					headers.put(key, value);
				}
			}

			requestLog(url, headers, paraMap);
		}

		/**
		 * 打印请求日志
		 */
		private void requestLog(String requestUrl, Map<String, String> headers, Map<String, String> params) {
			// 这两个域的请求参数 不输出
			if (requestUrl.indexOf("yunpian") > 0 || requestUrl.indexOf("api.weixin.qq.com") > 0) {
				logger.info("{} URL: {}", name(), requestUrl);

				// 输出内容
			} else {
				boolean hempty = MapUtils.isEmpty(headers);
				boolean pempty = MapUtils.isEmpty(params);
				if (hempty && pempty)
					logger.info("{} URL: {}", name(), requestUrl);
				else if (hempty)
					logger.info("{} URL: {}, Params: {}", name(), requestUrl, params);
				else if (pempty)
					logger.info("{} URL: {}, Header: {}", name(), requestUrl, headers);
				else
					logger.info("{} URL: {}, Header: {}, Params: {}", name(), requestUrl, headers, params);
			}
		}
	}

	/**
	 * 内网长连接持续时间策略<br>
	 * SLB 默认 15秒<br>
	 * 这里默认14秒
	 * 
	 * @author jfan 2016年8月29日 上午10:32:44
	 */
	private static class ApiConnectionKeepAliveStrategy implements ConnectionKeepAliveStrategy {

		private long defaultKVT = 14000;// ms

		@Override
		public long getKeepAliveDuration(HttpResponse response, HttpContext context) {
			// TODO nginx增加配置 keepalive_timeout 15 14; header会返回 Keep-Alive: timeout=14
			// http://nginx.org/en/docs/http/ngx_http_core_module.html#keepalive_timeout
			// 似乎没有在response的header中返回内容。。。

//			Object target = context.getAttribute(HttpClientContext.HTTP_TARGET_HOST);
//			if (null != target && target instanceof HttpHost) {
//				HttpHost host = (HttpHost) target;
//				String hostName = host.getHostName();
//				if (null != hostName && hostName.endsWith(".internal.mazing.com")) {
//					logger.debug("Internal API KeepAlive Timeout: {}ms", defaultKVT);
//					return defaultKVT;
//				}
//			}

			// 可以解决问题，保险起见，所有内网域名请求不允许返回-1
			long duration = DefaultConnectionKeepAliveStrategy.INSTANCE.getKeepAliveDuration(response, context);
			if (0 < duration) {
				logger.debug("Response KeepAlive Timeout: {}ms", duration);
				return duration;
			}

			logger.debug("Default KeepAlive Timeout: {}ms", defaultKVT);
			return defaultKVT;
		}

	}

	/**
	 * 任务的形式，定时清理过期的连接、空闲的连接
	 * 
	 * @author jfan 2016年11月7日 上午10:27:17
	 */
	private static final class IdleConnectionMonitor extends TimerTask {

		long idleMilli;

		private IdleConnectionMonitor(long idleMilli) {
			this.idleMilli = idleMilli;
		}

		@Override
		public void run() {
			long time = System.currentTimeMillis();
			logAvailable("IdleConnection Release start", 0);
			connectionManager.closeExpiredConnections();// Close expired connections
			connectionManager.closeIdleConnections(idleMilli, TimeUnit.MILLISECONDS);// Optionally, close connections
			logAvailable("IdleConnection Release end", time);
		}

		/**
		 * 打印当前的连接数，抽成方法主要是吞掉异常<br>
		 * 如果指定了startTime（>0）那么会同时打印耗时
		 */
		private void logAvailable(String msg, long startTime) {
			try {
				PoolStats totalStats = connectionManager.getTotalStats();
				int available = totalStats.getAvailable();
				int leased = totalStats.getLeased();

				if (0 < startTime) {
					long time = System.currentTimeMillis() - startTime;
					logger.debug("{}, leased: {}, available: {}, time: {}", msg, leased, available, time);
				} else
					logger.debug("{}, leased: {}, available: {}", msg, leased, available);
			} catch (Exception e) {
				// ignore
			}
		}

	}

	/**
	 * 用于判定连接是否可复用<br>
	 * 这里只针对内网域名 api 接口进行处理<br>
	 * 调用内网域名，经过阿里云负载均衡的时候，长连接的请求会被复用，但是最多只支持使用100次。据需使用会出错（会Retry）<br>
	 * 
	 * @author jfan 2016年9月9日 下午5:58:12
	 */
	@SuppressWarnings("unused")
	private static class ApiConnectionReuseStrategy implements ConnectionReuseStrategy {

		// private int defaultMax = 95;

		@Override
		public boolean keepAlive(HttpResponse response, HttpContext context) {
			boolean keepAlive = DefaultConnectionReuseStrategy.INSTANCE.keepAlive(response, context);
			// 如果允许复用，那么在判定一下是否超过了最大限制
			if (keepAlive) {
				// Keep-Alive: timeout=5, max=100
				// timeout：过期时间5秒
				// max是最多100次请求，服务器强制断掉连接（Connection: close）

				// TODO 未完成，发现nginx配置：keepalive_requests 13; 可以解决问题
				// http://nginx.org/en/docs/http/ngx_http_core_module.html#keepalive_requests
			}
			return keepAlive;
		}

	}

	/**
	 * 代码来自于 org.apache.http.client.protocol.ResponseContentEncoding 类 <br>
	 * 这个类检测服务端返回的内容 是否标准Http协议。但是有些域返回的内容不是标准的。<br>
	 * 这里做特殊处理，使：不标准也不会抛异常 <br>
	 * <br>
	 * 4.3 会检测协议，user.api返回的内容 不是完全标准的，导致抛了异常。寻找4.3 的解决方案；目前想到的是，更改RequestInterceptor，不做协议检测
	 * 
	 * JFan 2014年12月31日 下午3:41:22
	 */
	@Immutable
	private static class VipResponseContentEncoding implements HttpResponseInterceptor {

		// public static final String UNCOMPRESSED = "http.client.response.uncompressed";

		/**
		 * Handles the following {@code Content-Encoding}s by using the appropriate decompressor to wrap the response
		 * Entity:
		 * <ul>
		 * <li>gzip - see {@link GzipDecompressingEntity}</li>
		 * <li>deflate - see {@link DeflateDecompressingEntity}</li>
		 * <li>identity - no action needed</li>
		 * </ul>
		 * 
		 * @param response the response which contains the entity
		 * @param context not currently used
		 * 
		 * @throws HttpException if the {@code Content-Encoding} is none of the above
		 */
		public void process(final HttpResponse response, final HttpContext context) throws HttpException, IOException {
			final HttpEntity entity = response.getEntity();

			// entity can be null in case of 304 Not Modified, 204 No Content or similar
			// check for zero length entity.
			if (entity != null && entity.getContentLength() != 0) {
				final Header ceheader = entity.getContentEncoding();
				if (ceheader != null) {
					final HeaderElement[] codecs = ceheader.getElements();
					if (ArrayUtils.isEmpty(codecs))// **** add ****
						return;

					boolean uncompressed = false;
					for (final HeaderElement codec : codecs) {
						final String codecname = codec.getName().toLowerCase(Locale.ENGLISH);
						if ("gzip".equals(codecname) || "x-gzip".equals(codecname)) {
							response.setEntity(new GzipDecompressingEntity(response.getEntity()));
							uncompressed = true;
							break;
						} else if ("deflate".equals(codecname)) {
							response.setEntity(new DeflateDecompressingEntity(response.getEntity()));
							uncompressed = true;
							break;
						} else if ("identity".equals(codecname)) {

							/* Don't need to transform the content - no-op */
							return;
							// } else { // **** delete ****
							// throw new HttpException("Unsupported Content-Coding: " + codec.getName());
						}
					}
					if (uncompressed) {
						response.removeHeaders("Content-Length");
						response.removeHeaders("Content-Encoding");
						response.removeHeaders("Content-MD5");
					}
				}
			}
		}

	}

	/**
	 * Http连接的信任策略 <br>
	 * 信任所有（ssl自动接受对方的证书）
	 *
	 * @author JFan 2016年1月21日 下午3:46:04
	 */
	static final class AllTrust implements TrustStrategy {

		@Override
		public boolean isTrusted(X509Certificate[] chain, String authType) throws CertificateException {
			// 信任所有
			return true;
		}

	}

	/**
	 * 主要实现从配置文件中读取hosts映射，指向预定的目标去；没有配置的使用系统dns<br>
	 * 
	 * JFan 2015年2月16日 下午2:39:25
	 */
	static final class ConfigDnsResolver implements DnsResolver {

		/*
		 * （非 Javadoc）
		 * 
		 * @see org.apache.http.conn.DnsResolver#resolve(java.lang.String)
		 */
		@Override
		public InetAddress[] resolve(String host) throws UnknownHostException {
			// String ip;
			// if ((ip = localDnsMap.get(host)) != null)
			// return InetAddress.getAllByName(ip);
			// System DNS
			return InetAddress.getAllByName(host);
		}

	}

	//
	//
	//

	public static void main(String[] args) throws Exception {
//		String sslPath = "";
//		char[] pwd = "".toCharArray();
//		SSLContext sslContext = SSLContexts.custom().loadKeyMaterial(new File(sslPath), pwd, pwd).build();
//		System.out.println(sslContext);
		
//		System.setProperty("org.apache.commons.logging.Log", "org.apache.commons.logging.impl.SimpleLog");
//		System.setProperty("org.apache.commons.logging.simplelog.showdatetime", "true");
//		System.setProperty("org.apache.commons.logging.simplelog.log.httpclient", "debug");
//		System.setProperty("org.apache.commons.logging.simplelog.log.httpclient.wire", "debug");
//		System.setProperty("org.apache.commons.logging.simplelog.log.org.apache.commons.httpclient", "debug");

		// Map<String, String> requestHeaders = new HashMap<String, String>();
		// requestHeaders.put("Content-Type", "application/x-www-form-urlencoded");
		// Map<String, Object> params = new HashMap<>();
		// params.put("gender", "MALE");
		// String resp = HttpClientUtils.doPut("http://user.api.vip.com/users/47162576/info/base",
		// HttpClientUtils.DEFAULT_TIMEOUT,
		// requestHeaders, params);
		// System.out.println(resp);

		// System.out.println(doGet("http://www.baidu.com"));
		// System.out.println("\r\n\r\n***********************************************\r\n");

		// System.out.println(doPost("http://www.qq.com", 1000, null, null, "UTF-16"));
		// System.out.println("\r\n\r\n***********************************************\r\n");

		// System.out.println(doPut("http://www.163.com"));
		// System.out.println("\r\n\r\n***********************************************\r\n");

//		Map<String, String> requestHeaders = new HashMap<String, String>();
//		requestHeaders.put("Content-Type", "application/x-www-form-urlencoded");
//		Map<String, Object> params = new HashMap<>();
//		params.put("gender", "MALE");
//		System.out.println(HttpClientUtils.doPut("http://user.api.vip.com/users/47162576/info/base", requestHeaders, params));
//		System.out.println("\r\n\r\n***********************************************\r\n");

		// String h = "{\"Content-Type\":\"application/x-www-form-urlencoded\"}";
		// String p =
		// "{\"city_id\":\"city_id\",\"birthday\":\"birthday\",\"address\":\"address\",\"post_code\":\"post_code\",\"province_id\":\"province_id\",\"email\":\"email\",\"area_code\":\"area_code\",\"country_id\":\"country_id\",\"gender\":\"MALE\",\"telephone\":\"telephone\",\"mobile\":\"mobile\"}";
		// HashMap ha = JsonUtils.parseObject(h, HashMap.class);
		// HashMap pa = JsonUtils.parseObject(p, HashMap.class);
		// System.out.println(doPut("http://user.api.vip.com/users/123/info/base", ha, pa));
		// System.out.println("\r\n\r\n***********************************************\r\n");
		
		Map<String, Object> keyword1 = new HashMap<String, Object>();
		keyword1.put("value", "米星9.9元水果茶");
		keyword1.put("color", "#173177");
		Map<String, Object> keyword2 = new HashMap<String, Object>();
		keyword2.put("value", "爱茶水果茶");
		keyword2.put("color", "#173177");
		Map<String, Object> keyword3 = new HashMap<String, Object>();
		keyword3.put("value", "9.9");
		keyword3.put("color", "#173177");
		Map<String, Object> keyword4 = new HashMap<String, Object>();
		keyword4.put("value", "2017-05-05 17:00:00");
		keyword4.put("color", "#173177");
		
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("keyword1", keyword1);
		data.put("keyword2", keyword2);
		data.put("keyword3", keyword3);
		data.put("keyword4", keyword4);
		
		
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("touser", "ovnn50CUEcCET0DHwBk6SlLMsNng");
		params.put("template_id", "9IXJadaVhnWcR5iFaQzLzxQ-Gntw-CIO13Eq4pVCJnA");
		params.put("form_id", "wx2017052615321293ee4be6710564718468");
		params.put("data", data);
		
		System.out.println(JsonUtils.toJSONString(params));
		
//		String str = HttpClientUtils.doGet("http://121.40.81.176:20090/");
//		str = StringUtils.trimToEmpty(str);
//		String[] array = str.split("\\|");
//		String ACCESS_TOKEN = array[0];
		String ACCESS_TOKEN = "9iSxm-hW1B4ZPdvJxitHv_AEJ9oSpbAXAbWXTDq55jvYdpROz-LUkIhn7iB4OQ3jemH7T3TcWLEWeWbRyKsNP8kzYq30fBTaUKisGGYPXYBCmMVJEO49nuc2D0l4gMTTJNMfAFASWV";
		System.out.println(ACCESS_TOKEN);
		
		Map<String, String> headers = new HashMap<String, String>();
		headers.put("Content-Type", "application/json");
		String result = HttpClientUtils.doPost("https://api.weixin.qq.com/cgi-bin/message/wxopen/template/send?access_token=" + ACCESS_TOKEN, headers, JsonUtils.toJSONString(params));
		System.out.println(result);
	}

}
