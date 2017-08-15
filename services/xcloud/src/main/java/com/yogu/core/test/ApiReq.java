package com.yogu.core.test;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;

import org.apache.commons.io.FileUtils;

import com.yogu.commons.core.KeyValue;
import com.yogu.commons.utils.CollectionUtils;
import com.yogu.commons.utils.DigestUtil;
import com.yogu.commons.utils.encrypt.SignUtils;
import com.yogu.core.remote.config.AppLanguage;

/**
 * 对API接口请求参数的包装类，所有参数都集中在此类中 <br>
 * 仅用于Test
 * 
 * @author JFan 2015年7月24日 上午10:37:43
 */
public abstract class ApiReq<T> {

	public static String nowUt;
	public static String aver = "1.7.0.181";
	public static String sver = "8.3";
	public static String cityCode = "020";
	public static String langCode = "zh";
	public static String sname = "iPhone_OS";
	public static String aname = "iphone_mixing";
	public static String did = "34AFF59D196241E0B94865512CB5484F";
	public static String defAppKey = "13a9b526b3d44bdd8d1553346ebcf9b8";
	public static String defAppSecret = "D5EFAEE2D37B423AAA43AC67B5EDC3FB";
	private static Random r = new Random();

	protected String uri;
	protected boolean isA;
	protected String appSecret;
	protected String userSecret;
	protected String requestSign;
	protected boolean useHeaderSendSign = true;// sign签名摘要参数，走header还是query参数；true：header

	/** header参数（不参与签名） */
	protected Map<String, String[]> h = new HashMap<String, String[]>();
	/** 各种参数，参与签名 */
	protected Map<String, String[]> headers = new HashMap<String, String[]>();
	protected Map<String, String[]> getparams = new HashMap<String, String[]>();
	protected Map<String, String[]> postparams = new HashMap<String, String[]>();
	protected List<UploadFile> fileList = new ArrayList<>();

	protected ApiReq(boolean isA, String uri, String appKey, String appSecret) {
		this.uri = uri;
		this.isA = isA;
		setAppKey(appKey, appSecret);
	}

	protected void def() {
		setDid(did);
		setSysName(sname);
		setAppName(aname);
		setSysVersion(sver);
		setAppVersion(aver);
		setCityCode(cityCode);
		setLang(langCode);
		randomGzPoint();//113.343106, 23.132001
		signToGet();
	}

	public void setUri(String uri) {
		this.uri = uri;
	}

	// 设置通用参数

	public ApiReq<T> setSysName(String sname) {
		getparams.put("sname", new String[] { sname });
		return this;
	}

	public ApiReq<T> setSysVersion(String sver) {
		getparams.put("sver", new String[] { sver });
		return this;
	}

	public ApiReq<T> setAppName(String aname) {
		getparams.put("aname", new String[] { aname });
		return this;
	}

	public ApiReq<T> setAppVersion(String aver) {
		getparams.put("aver", new String[] { aver });
		return this;
	}

	public ApiReq<T> setCityCode(String cityCode) {
		getparams.put("citycode", new String[] { cityCode });
		return this;
	}

	public ApiReq<T> useEnLang() {
		return setLang(AppLanguage.en.getCode());
	}

	public ApiReq<T> useZhLang() {
		return setLang(AppLanguage.zh.getCode());
	}

	public ApiReq<T> setLang(String langCode) {
		getparams.put("lang", new String[] { langCode });
		return this;
	}

	public ApiReq<T> setDid(String did) {
		getparams.put("did", new String[] { did });
		return this;
	}

	public ApiReq<T> randomGzPoint() {
		// 113.494949 - 113.179092 = 0.315857
		int lng = 179092 + r.nextInt(315857);
		getparams.put("lng", new String[] { "113." + lng });
		// 23.194018 - 22.925512 = 0.268506
		int lat1 = 22;
		int lat2 = 925512 + r.nextInt(268506);
		int l1 = lat2 / 1000000;
		int l2 = lat2 % 1000000;
		int l3 = l2 / 100000;
		getparams.put("lat", new String[] { (lat1 + l1) + "." + (1 <= l3 ? "" : "0") + l2 });
		return this;
	}

	public ApiReq<T> setPoint(double lng, double lat) {
		getparams.put("lng", new String[] { Double.toString(lng) });
		getparams.put("lat", new String[] { Double.toString(lat) });
		return this;
	}

	public ApiReq<T> signToGet() {
		this.useHeaderSendSign = false;
		return this;
	}

	public ApiReq<T> signToHeader() {
		this.useHeaderSendSign = true;
		return this;
	}

	public ApiReq<T> setAppKey(String appKey, String appSecret) {
		getparams.put("akey", new String[] { appKey });
		this.appSecret = appSecret;
		return this;
	}

	// 设置UserToken

	/**
	 * 返回KeyValue对象，key为ut，value为secret
	 */
	public KeyValue<String, String> login() {
		//s 接口登录
		return login("86", "18676198141", "wo495663075.");
		
//		 return login("86", "13926426236", "12345678");
	}

	/**
	 * 返回KeyValue对象，key为ut，value为secret
	 */
	public KeyValue<String, String> login(String countryCode, String mobile, String password) {
		Map<String, String> baseParam = new HashMap<String, String>();
		baseParam.put("akey", getGetParam("akey"));
		baseParam.put("appSecret", this.appSecret);
		baseParam.put("sname", getGetParam("sname"));
		baseParam.put("sver", getGetParam("sver"));
		baseParam.put("aname", getGetParam("aname"));
		baseParam.put("aver", getGetParam("aver"));
		baseParam.put("did", getGetParam("did"));
		baseParam.put("citycode", getGetParam("citycode"));

		KeyValue<String, String> kv = login(countryCode, mobile, password, baseParam);
		userToken2Get(kv.getKey(), kv.getValue());
		return kv;
	}

	/**
	 * 真正完成登录的地方
	 */
	protected abstract KeyValue<String, String> login(String countryCode, String mobile, String password, Map<String, String> baseParam);

	private String getGetParam(String key) {
		String[] strings = getparams.get(key);
		if (null == strings || 0 >= strings.length)
			throw new RuntimeException("没有设置要查询的值：" + key);
		return strings[0];
	}

	public ApiReq<T> userToken2Get(String userToken, String userSecret) {
		getparams.put("ut", new String[] { userToken });
		this.userSecret = userSecret;
		postparams.remove("ut");
		nowUt = userToken;
		return this;
	}

	public ApiReq<T> userToken2Post(String userToken, String userSecret) {
		postparams.put("ut", new String[] { userToken });
		this.userSecret = userSecret;
		getparams.remove("ut");
		nowUt = userToken;
		return this;
	}

	//

	public ApiReq<T> setHttpHeader(String key, String... values) {
		h.put(key, values);
		return this;
	}

	public ApiReq<T> setHeader(String key, String... values) {
		headers.put(key, values);
		return this;
	}

	public ApiReq<T> setGet(String key, String... values) {
		getparams.put(key, values);
		return this;
	}

	public ApiReq<T> setPost(String key, String... values) {
		postparams.put(key, values);
		return this;
	}

	public ApiReq<T> putHttpHeader(String key, String... values) {
		merge(h, key, values);
		return this;
	}

	public ApiReq<T> putHeader(String key, String... values) {
		merge(headers, key, values);
		return this;
	}

	public ApiReq<T> putGet(String key, String... values) {
		merge(getparams, key, values);
		return this;
	}

	public ApiReq<T> putPost(String key, String... values) {
		merge(postparams, key, values);
		return this;
	}

	public ApiReq<T> clearFile() {
		if (CollectionUtils.isNotEmpty(fileList)) {
			for (UploadFile uf : fileList)
				getparams.remove(uf.getName() + "Hash");
			fileList.clear();
		}
		return this;
	}

	public ApiReq<T> putFile(String name, String filePath) {
		return putFile(name, new File(filePath));
	}

	public ApiReq<T> putFile(String name, File file) {
		try {
			byte[] bs = FileUtils.readFileToByteArray(file);
			String md5 = DigestUtil.md5(bs);
			fileList.add(new UploadFile(name, file));
			getparams.put(name + "Hash", new String[] { md5 });
		} catch (IOException e) {
			throw new RuntimeException("读取文件异常：" + file.getAbsolutePath(), e);
		}
		return this;
	}

	//

	public T doGet() {
		return req0(true, false);
	}

	public T doPost() {
		return req0(false, false);
	}

	/**
	 * 强制multipart 请求
	 */
	public T doMultipartPost() {
		return req0(false, true);
	}

	// protected

	protected T req0(boolean get, boolean forceMultipart) {
		getparams.put("t", new String[] { Long.toString(System.currentTimeMillis() / 1000) });
		return req(get, forceMultipart);
	}

	/**
	 * 执行HTTP请求
	 * 
	 * @param get 是否为get请求
	 * @param forceMultipart 是否强制multipart
	 * @return
	 */
	protected abstract T req(boolean get, boolean forceMultipart);

	protected void merge(Map<String, String[]> map, String key, String... values) {
		if (null == values || 0 >= values.length)
			return;
		String[] strings = map.get(key);
		if (null == strings) {
			map.put(key, values);
		} else {
			int l = strings.length;
			strings = Arrays.copyOf(strings, l + values.length);
			for (String value : values)
				strings[l++] = value;
			map.put(key, strings);
		}
	}

	public String sign() {
		if (isA && null == userSecret)
			throw new RuntimeException("用户没有登录。");

		// 计算签名
		String secret = (isA ? appSecret + userSecret : appSecret);
		Map<String, String[]> params = new HashMap<String, String[]>();
		params.putAll(headers);
		params.putAll(getparams);
		params.putAll(postparams);

		String source = SignUtils.signSource(params);
		System.out.println("签名元数据：" + source);
		System.out.println("签名密匙：" + secret);
		String sign = SignUtils.signHmacSha1(source, secret);
		System.out.println("签名值：" + sign);

		requestSign = sign;
		return sign;
	}

	protected String url(boolean get) {
		sign();

		Map<String, String[]> params = new HashMap<String, String[]>(getparams);
		if (get)
			for (Entry<String, String[]> entry : postparams.entrySet())
				merge(params, entry.getKey(), entry.getValue());

		if (!(useHeaderSendSign))
			params.put("sign", new String[] { requestSign });

		int index = (-1 == uri.indexOf('&') ? 1 : 2);
		StringBuilder url = new StringBuilder(uri);
		StringBuilder urlEncode = new StringBuilder(uri);
		if (-1 == uri.indexOf('?')) {
			urlEncode.append('?');
			url.append('?');
		}
		for (Entry<String, String[]> entry : params.entrySet()) {
			String name = entry.getKey();
			for (String value : entry.getValue()) {
				try {
					if (1 < index++) {
						url.append('&');
						urlEncode.append('&');
					}
					url.append(name).append('=').append(value);
					urlEncode.append(name).append('=').append(URLEncoder.encode(value, "UTF-8"));
				} catch (UnsupportedEncodingException e) {
					throw new RuntimeException("URL编码时异常：" + value, e);
				}
			}
		}
		System.out.println(">>请求地址：" + urlEncode.toString());
		return url.toString();
	}

	protected Map<String, String[]> header() {
		// 不参与封签的header信息
		Map<String, String[]> hs = new HashMap<String, String[]>();
		hs.putAll(h);
		// header需要以mz-开头
		for (Entry<String, String[]> entry : headers.entrySet())
			hs.put("mz-" + entry.getKey(), entry.getValue());
		if (useHeaderSendSign)
			hs.put("mz-sign", new String[] { requestSign });
		print("请求数据Header部分", hs);
		return hs;
	}

	protected void print(String msg, Map<String, String[]> map) {
		if (null != map && !map.isEmpty()) {
			System.out.println(msg);
			for (Entry<String, String[]> entry : map.entrySet())
				for (String value : entry.getValue())
					System.out.println("\t" + entry.getKey() + "\t:\t" + value);
		}
	}

	/**
	 * 上传的文件内容
	 * 
	 * @author linyi 2015/7/2
	 */
	public static class UploadFile {

		/** 参数名 */
		private String name;

		/** 文件 */
		private File file;

		public UploadFile(String name, File file) {
			this.name = name;
			this.file = file;
		}

		/**
		 * @return name
		 */
		public String getName() {
			return name;
		}

		/**
		 * @param name 要设置的 name
		 */
		public void setName(String name) {
			this.name = name;
		}

		/**
		 * @return file
		 */
		public File getFile() {
			return file;
		}

		/**
		 * @param file 要设置的 file
		 */
		public void setFile(File file) {
			this.file = file;
		}

	}

}
