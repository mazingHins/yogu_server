package com.mazing.test.core.http;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.GzipDecompressingEntity;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.client.params.HttpClientParams;
import org.apache.http.conn.routing.HttpRoute;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.PoolingClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;
import org.apache.http.pool.PoolStats;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Devin.Hu
 * @date 2011-6-24
 * @version V1.0
 * @description HttpClientUtils工具类，用于与服务端建立连接，传送或读取数据
 */
public class HttpClientUtils {

	private static final long WARN_TIME = 800;
	private static final String _127_0_0_1 = "127.0.0.1";

	private static final Logger logger = LoggerFactory.getLogger(HttpClientUtils.class);

	public final static String DEFAULT_CHARSET = "UTF-8";

	public static final int DEFAULT_TIMEOUT = 4;
	public static final int DEFAULT_CONNECTION_TIMEOUT = 1000;
	public static final int DEFAULT_SOCKET_TIMEOUT = DEFAULT_TIMEOUT * 1000;

	public static final ThreadLocal<String> ip_session = new ThreadLocal<String>();

	private static PoolingClientConnectionManager cm = null;
	// private static HttpHost miHost = new HttpHost("mi.vipshop.com", 80,
	// "http");
	// private static HttpRoute miRoute = new HttpRoute(miHost);
	// private static HttpHost msgHost = new HttpHost("msg.mm.vipshop.com", 80);
	// private static HttpRoute msgRoute = new HttpRoute(msgHost);

	static {
		SchemeRegistry schemeRegistry = new SchemeRegistry();
		schemeRegistry.register(new Scheme("http", 80, PlainSocketFactory.getSocketFactory()));
		// TODO should we support https?
		schemeRegistry.register(new Scheme("https", 443, SSLSocketFactory.getSocketFactory()));
		cm = new PoolingClientConnectionManager(schemeRegistry);
		// Increase max total connection to 200
		cm.setMaxTotal(3000);
		// Increase default max connection per route to 20
		cm.setDefaultMaxPerRoute(450);
		// Increase max connections for localhost:80 to 50
		// cm.setMaxPerRoute(miRoute, 200);
		// cm.setMaxPerRoute(msgRoute, 200);

	}

	/**
	 * 得到HttpClient方法
	 * 
	 * @return
	 */

	public static HttpClient getHttpClient(int timeOut) {
		HttpParams httpParams = new BasicHttpParams();
		// 连接建立时的毫秒级超时时间
		HttpConnectionParams.setConnectionTimeout(httpParams, DEFAULT_CONNECTION_TIMEOUT);
		// 套接字的毫秒级超时时间 请求超时
		HttpConnectionParams.setSoTimeout(httpParams, timeOut > 0 ? timeOut * 1000 : DEFAULT_SOCKET_TIMEOUT);
		HttpConnectionParams.setSocketBufferSize(httpParams, 8192);

		HttpClientParams.setRedirecting(httpParams, true);

		String userAgent = "Mozilla/5.0 (Windows; U; Windows NT 5.1; zh-CN; rv:1.9.2) Gecko/20100115 Firefox/3.6";
		HttpProtocolParams.setUserAgent(httpParams, userAgent);

		// return new DefaultHttpClient(httpParams);
		// 通过连接池返回
		DefaultHttpClient httpClient = new DefaultHttpClient(cm, httpParams);
		// logConnectionStats();
		return httpClient;
	}

    private static void logIfSlowURLWithConnectionStats(String uri, long start) {
        long offset = System.currentTimeMillis() - start;
        if (offset > WARN_TIME) {
            logSlowURLWithConnectionStats(offset, uri);
        }
    }

    protected static void logSlowURLWithConnectionStats(long offset, String url) {
        PoolStats totalStats = cm.getTotalStats();
        try {
            URL u = new URL(url);
            logger.warn(
                    "HTTP_take much time-{}| {} cost {} millis. ### http connection pool, max: {}, acitve: {}, leased: {}, pending: {}",
                    u.getHost(), url, offset, totalStats.getMax(), totalStats.getAvailable(), totalStats.getLeased(),
                    totalStats.getPending());
        } catch (MalformedURLException e) {
        }
    }
	
	protected static void logConnectionStats(HttpRoute route, String url) {
        if (logger.isDebugEnabled()) {
            PoolStats stats = cm.getStats(route);
            logger.debug("HTTP_connection route pool for-{}| max: {}, acitve: {}, leased: {}, pending: {}",
                    route.getProxyHost(), stats.getMax(), stats.getAvailable(), stats.getLeased(), stats.getPending());
        }
	}

	/**
	 * 执行doGet方法
	 * 
	 * @param url
	 * @return
	 * @throws Exception
	 */
	public static String doGet(String url) throws Exception {
		// 使用工具默认编码
		return doGet(url, DEFAULT_CHARSET);
	}

	public static String doGet(String url, String charset) throws Exception {
		return doGet(url, charset, DEFAULT_TIMEOUT);
	}

	public static String doGet(String url, int timeout) throws Exception {
		return doGet(url, DEFAULT_CHARSET, timeout);
	}

	public static String doGet(String url, String charset, int timeout) throws Exception {
		return doGet(url, charset, timeout, true);
	}

	public static String doGet(String url, String charset, int timeout, boolean isLog) throws Exception {
		String responseString = "";
		HttpResponse httpResponse = null;
		HttpClient httpClient = null;
		HttpGet httpRequest = null;
		/* 发送请求并等待响应 */
		try {
			long start = System.currentTimeMillis();
			httpClient = getHttpClient(timeout);
			httpRequest = new HttpGet(url);
			// specify some common header, like IP, reference, accept
			updateCommonHeader(httpRequest);

			httpResponse = httpClient.execute(httpRequest);
			// 查看响应状态 如果不是正常状态 则主动abort请求并返回
			int httpCode = httpResponse.getStatusLine().getStatusCode();
			if (isSuccess(httpCode) == false) {
				httpRequest.abort();
				String resp = new String(getResponseEntityAsByteArray(httpResponse), charset);
				if (isLog) {
					logger.warn("HTTP_get error status code-{}| the url is: {}, response: {}", httpCode, url, resp);
				}
				System.out.println("非正常响应，httpCode：" + httpCode + "，resp：" + resp + "，url：" + url);
				return null;
			}
			if (httpResponse.getEntity() != null) {
				byte[] bytes = getResponseEntityAsByteArray(httpResponse);
				if (isLog && (bytes.length < 15 || bytes.length > 1024)) {
                    if (logger.isInfoEnabled()) {
                        String responseMinStr = getMinHttpResponeStringForLog(bytes, charset);
                        logger.info("HTTP_get| url: {},length is {}, response: {}", url, (bytes != null ? bytes.length
                                : 0), responseMinStr);
                    }
				}
				responseString = new String(bytes, charset);
				responseString = filterHtml(responseString);

				if (isLog) {
					if (logger.isDebugEnabled()) {
						logger.debug("HTTP_get| {}, response: {}", url, responseString);
					}
				}
			}
			logIfSlowURLWithConnectionStats(url, start);
		}
		catch (Exception e) {
            if (isLog) {
                logger.error("HTTP_get-{}| {}", e.getMessage(), url,
                        (e instanceof IOException || e instanceof ClientProtocolException) ? null : e);
            }
			
			if (httpRequest != null) {
				httpRequest.abort();
			}
			throw e;
		}
		return responseString;
	}

	private static boolean isSuccess(int respStatusCode) {
		if (respStatusCode == HttpStatus.SC_OK || respStatusCode == HttpStatus.SC_CREATED) {
			return true;
		}
		else {
			return false;
		}
	}

	private static void updateCommonHeader(HttpRequestBase httpRequest) {
		httpRequest.addHeader("Referer", "http://perf.mazing.com/");
		httpRequest.addHeader("Accept-Encoding", "gzip, deflate");

		// 请求参数增加客户端ip
		populateRequestIp(httpRequest);
	}

	private static void populateRequestIp(HttpRequestBase httpRequest) {
		// 增加IP转发功能
		String client_id = ip_session.get();
		if (StringUtils.isEmpty(client_id)) {
			client_id = _127_0_0_1;
		}
		httpRequest.setHeader("X-Forwarded-For", client_id);
		if (logger.isDebugEnabled()) {
			logger.debug("HTTP_request ip-{}|", client_id);
		}
	}

	/**
	 * 执行doGet方法，失败返回null
	 * 
	 * @deprecated, since anti-WYSIWYG
	 * 
	 * @param url
	 * @return InputStream
	 */
	public InputStream doGet4stream(String url) throws ClientProtocolException, IOException {
		HttpGet httpRequest = new HttpGet(url);
		HttpResponse httpResponse = getHttpClient(60).execute(httpRequest);

		if (isSuccess(httpResponse.getStatusLine().getStatusCode()) == false) {
			return httpResponse.getEntity().getContent();
		}
		return null;
	}

	/**
	 * 过滤特殊字符
	 * 
	 * @param result
	 * @return
	 */
	private static String filterHtml(String result) {
		if (null != result) {
			result = result.replace("&#039;", "'");
			result = result.replace("&amp;", "&");
			result = result.replace("&nbsp;", " ");
		}
		return result;
	}

	/**
	 * post request without parameters
	 * 
	 * @param url
	 * @return
	 */
	public static String doPost(String url) {
		return doPost(url, DEFAULT_CHARSET, null);
	}

	public static String doPost(String url, String content) {
		return doPost(url, content, DEFAULT_CHARSET, DEFAULT_TIMEOUT);
	}

	public static String doPost(String url, String content, String charset, int timeout) {
		return doPost(url, content, charset, timeout, null);
	}

    /**
     * post request by specified url & parameters
     * 
     * @param url
     * @param parameters
     * @return
     */
    public static String doPost(String url, Map<String, String> parameters) {
        return doPost(url, DEFAULT_CHARSET, parameters);
    }

    /**
     * post request by specified url, charset & parameters
     * 
     * @param url
     * @param charset
     * @param parameters
     * @return
     */
    public static String doPost(String url, String charset, Map<String, String> parameters) {
        return doPost(url, charset, parameters, DEFAULT_TIMEOUT);
    }

    public static String doPost(String url, String charset, Map<String, String> parameters, int timeout) {
        return doPost(url, charset, parameters, timeout, null);
    }

    public static String doPost(String url, String content, String charset, int timeout,
            Map<String, String> headerMap) {
        if (logger.isInfoEnabled())
            logger.info("HTTP_post-{}| content: {}, header: {}", url, content, headerMap);
        HttpClient httpClient = getHttpClient(timeout);
        HttpPost httpRequest = new HttpPost(url);
        
        try {
            HttpEntity repEntity = new StringEntity(content, charset);
            httpRequest.setEntity(repEntity);
            
            // set request headers
            addHeaderForHttpRequestBase(httpRequest, headerMap);
            
            return doPost(httpClient, httpRequest);
        }
        catch (Exception e) {
            logger.error("HTTP_post-{} - {}| content: {}, header: {}", url, e.getMessage(), content,
                    headerMap, e instanceof UnsupportedEncodingException ? null : e);
        }
        finally {
            // request entity not contain input stream
            /*
             * try { if (repEntity != null) { EntityUtils.consume(repEntity); }
             * } catch (IOException e) {
             * logger.error(" final consume the stream ", e); }
             */
        }
        return null;
    }

    /**
     * post request by specified url, parameters, and headers
     * 
     * @param url
     * @param parameters
     * @param headerMap
     * @return
     */
    public static String doPost(String url, Map<String, String> parameters, Map<String, String> headerMap) {
        return doPost(url, DEFAULT_CHARSET, parameters, DEFAULT_TIMEOUT, headerMap);
    }

	public static String doPost(String url, String charset, Map<String, String> parameters, int timeout,
			Map<String, String> headerMap) {
        if (logger.isInfoEnabled()) {
            logger.info("HTTP_post-{}| param: {}, header: {}", url, parameters, headerMap);
        }
		HttpClient httpClient = getHttpClient(timeout);
		HttpPost httpRequest = new HttpPost(url);
		// set request parameters
		if (parameters != null && !parameters.isEmpty()) {
			List<NameValuePair> params = new ArrayList<NameValuePair>(parameters.size());
			for (String key : parameters.keySet()) {
				params.add(new BasicNameValuePair(key, String.valueOf(parameters.get(key))));
			}
			try {
				httpRequest.setEntity(new UrlEncodedFormEntity(params, DEFAULT_CHARSET));
			}
			catch (UnsupportedEncodingException e) {
                logger.warn("HTTP_post-{} encode parameter error| param: {}, header: {}", url, parameters, headerMap);
            }
		}

		// set request headers
		addHeaderForHttpRequestBase(httpRequest, headerMap);

		try {
			String responseString = doPost(httpClient, httpRequest);
            if (logger.isDebugEnabled())
                logger.debug("HTTP_post-{}| param: {}, header: {}, response: {}", url, parameters, headerMap, responseString);
            return responseString;
		}
		catch (Exception e) {
            logger.error("HTTP_post-{} - {}| param: {}, header: {}", url, e.getMessage(), parameters, headerMap,
                    (e instanceof UnsupportedEncodingException || e instanceof ClientProtocolException) ? null : e);
		}
		return null;
	}

	public static String doPost(HttpClient httpClient, HttpEntityEnclosingRequestBase httpRequest) throws Exception {
		HttpEntity rspEntity = null;
		try {
			long start = System.currentTimeMillis();

			// specify some common header, like IP, reference, accept
			updateCommonHeader(httpRequest);

			HttpResponse httpResponse = httpClient.execute(httpRequest);
			if (isSuccess(httpResponse.getStatusLine().getStatusCode())) {
				rspEntity = httpResponse.getEntity();
				if (rspEntity != null) {
					byte[] bytes = getResponseEntityAsByteArray(httpResponse);

					if (logger.isInfoEnabled() && (bytes.length < 15 || bytes.length > 1024)) {
						String responseMinStr = getMinHttpResponeStringForLog(bytes, DEFAULT_CHARSET);
                        logger.info("HTTP_post| url: {},length is {}, response: {}", httpRequest.getURI(),
                                (bytes != null ? bytes.length : 0), responseMinStr);
					}
					return new String(bytes, DEFAULT_CHARSET);
				}
				logIfSlowURLWithConnectionStats(httpRequest.getURI().toString(), start);
			}
			else {
				// 查看响应状态 如果不是正常状态 则主动abort请求并返回{
				logger.warn("HTTP_post error status code-{}| the url is: {}, response: {}", httpResponse
                        .getStatusLine().getStatusCode(), httpRequest.getURI(), new String(
                        getResponseEntityAsByteArray(httpResponse)), DEFAULT_CHARSET);

				httpRequest.abort();
				return null;
			}
		}
		catch (Exception e) {
			httpRequest.abort();
			throw e;
		}
		finally {
			try {
				if (rspEntity != null) {
					EntityUtils.consume(rspEntity);
				}
			}
			catch (IOException e) {
				logger.error("HTTP_post fail consume the response with Exception {}|", e.getMessage());
			}
		}
		return null;
	}

	private static byte[] getResponseEntityAsByteArray(HttpResponse httpResponse) throws IOException {
		Header contentEncodingHeader = httpResponse.getFirstHeader("Content-Encoding");
		byte[] bytes = null;
		if (contentEncodingHeader != null && contentEncodingHeader.getValue().toLowerCase().indexOf("gzip") > -1) {
			bytes = EntityUtils.toByteArray(new GzipDecompressingEntity(httpResponse.getEntity()));
		}
		else {
			bytes = EntityUtils.toByteArray(httpResponse.getEntity());
		}
		return bytes;
	}

	public static void setClienIp(String ip) {
		ip_session.set(ip);
	}

	public static String getClientIp() {
		return ip_session.get();
	}

	public static String doDelete(String url) {
		return doDelete(url, DEFAULT_CHARSET, null);
	}

	public static String doDelete(String url, Map<String, Object> map) {
		return doDelete(url, DEFAULT_CHARSET, map);
	}

	public static String doDelete(String url, String charset, Map<String, Object> map) {
		return doDelete(url, charset, map, DEFAULT_TIMEOUT, null);
	}

	public static String doDelete(String url, String charset, Map<String, Object> map, int timeout,
			Map<String, String> requestHeaders) {
		HttpClient httpClient = getHttpClient(timeout);
		HttpDelete httpRequest = new HttpDelete(url);
		HttpResponse httpResponse = null;
		try {
			setParameterForHttpRequestBase(httpRequest, map);
			addHeaderForHttpRequestBase(httpRequest, requestHeaders);

			httpResponse = httpClient.execute(httpRequest);

			return getResponseContent(httpRequest, httpResponse);
		}
		catch (Exception ex) {
            logger.error("HTTP_delete fail-{}| exception {} ", url, ex.getMessage(), (ex instanceof IOException) ? null
                    : ex);
			httpRequest.abort();
		}
		finally {
			try {
				if (httpResponse != null) {
					EntityUtils.consume(httpResponse.getEntity());
				}
			}
			catch (IOException e) {
			    logger.error("HTTP_delete fail consume the response with Exception {}|", e.getMessage());
			}
		}
		return null;
	}

	public static String doPut(String url, String charset, Map<String, Object> map, int timeout) {
		return doPut(url, charset, map, timeout, null);
	}
	
	public static String doPut(String url, String charset, Map<String, Object> parameters, int timeout,
			Map<String, String> headerMap) {
		if (logger.isInfoEnabled()) {
			logger.info("put url: " + url + " param:" + parameters + " header: " + headerMap);
		}
		HttpClient httpClient = getHttpClient(timeout);
		HttpPut httpRequest = new HttpPut(url);
		// set request parameters
		if (parameters != null && !parameters.isEmpty()) {
			List<NameValuePair> params = new ArrayList<NameValuePair>(parameters.size());
			for (String key : parameters.keySet()) {
				params.add(new BasicNameValuePair(key, String.valueOf(parameters.get(key))));
			}
			try {
				httpRequest.setEntity(new UrlEncodedFormEntity(params, DEFAULT_CHARSET));
			}
			catch (UnsupportedEncodingException e) {
				logger.warn("encode parameter error, " + " put to " + url + " param:" + parameters + " header: " + headerMap);
			}
		}

		// set request headers
		addHeaderForHttpRequestBase(httpRequest, headerMap);

		try {
			String responseString = doPost(httpClient, httpRequest);
			if (logger.isDebugEnabled())
				logger.debug("put to " + url + " param:" + parameters + " header: "
						+ parameters + ", response: " + responseString);
			return responseString;
		}
		catch (Exception e) {
			if (e instanceof IOException || e instanceof ClientProtocolException)
				logger.warn("post to " + url + " param:" + parameters + " header: "
						+ parameters + ", encountering " + e.getMessage());
			else
				logger.warn(
						"post to " + url + " param:" + parameters + " header: "
								+ parameters, e);
		}
		return null;
	}

	private static String getResponseContent(HttpRequestBase httpRequest, HttpResponse httpResponse)
			throws ParseException, IOException {
		HttpEntity entity = null;
		if (isSuccess(httpResponse.getStatusLine().getStatusCode())) {
			entity = httpResponse.getEntity();
			if (entity != null) {
				return EntityUtils.toString(entity, DEFAULT_CHARSET);
			}
		}
		else {
			// 查看响应状态 如果不是正常状态 则主动abort请求并返回{
			httpRequest.abort();
			logger.warn("target return error status, the url is :" + httpRequest.getURI());
			return null;
		}
		return null;
	}

	/**
	 * set parameter for HttpRequestBase
	 * 
	 * @param httpRequest
	 * @param params
	 */
	private static void setParameterForHttpRequestBase(HttpRequestBase httpRequest, Map<String, Object> params) {
		if (params != null && !params.isEmpty()) {
			for (String key : params.keySet()) {
				httpRequest.getParams().setParameter(key, params.get(key));
			}
		}
	}

	/**
	 * add headers for HttpRequestBase
	 * 
	 * @param httpRequest
	 * @param headerMap
	 */
	private static void addHeaderForHttpRequestBase(HttpRequestBase httpRequest, Map<String, String> headerMap) {
		if (headerMap != null && !headerMap.isEmpty()) {
			for (String key : headerMap.keySet()) {
				httpRequest.addHeader(key, headerMap.get(key));
			}
		}
	}

	/**
	 * 打印HTTP的响应
	 * 
	 * @author jame.xu
	 * @date 2014-4-3
	 * @description
	 * @mail jame.xu@vipshop.com
	 */
	private static String getMinHttpResponeStringForLog(byte[] data, String charset) {
		if (data != null && data.length > 0) {
			int length = data.length >= 16 ? 16 : data.length;
			byte[] dest = new byte[length];
			System.arraycopy(data, 0, dest, 0, length);
			try {
				String result = new String(dest, charset);
				if (null != result) {
					result = result.replace("\r\n", " ");
					return result;
				}
			}
			catch (UnsupportedEncodingException e) {
			}
		}
		return null;
	}

}
