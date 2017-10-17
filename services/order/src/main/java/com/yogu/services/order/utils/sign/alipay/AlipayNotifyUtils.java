package com.yogu.services.order.utils.sign.alipay;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.yogu.commons.utils.JsonUtils;
import com.yogu.core.web.encrypt.StaticKeyHelper;
import com.yogu.services.order.utils.TerraceUtils;
import com.yogu.services.order.utils.sign.common.CoreUtils;
import com.yogu.services.order.utils.sign.common.MD5;
import com.yogu.services.order.utils.sign.common.RSAUtil;

/**
 * 支付宝回调通知相关工具类
 * 
 * @author Hins
 * @date 2016年2月18日 下午2:47:44
 */
public class AlipayNotifyUtils {
	
	private static final Logger logger = LoggerFactory.getLogger(AlipayNotifyUtils.class);
	
	/**
	 * 支付宝消息验证地址
	 */
	private static final String HTTPS_VERIFY_URL = "https://mapi.alipay.com/gateway.do?service=notify_verify&";
	
	/**
	 * 验证消息是否是支付宝发出的合法消息
	 * 
	 * @param params 通知返回来的参数数组
	 * @param signType 签名方式（支付回调RSA，退款回调MD5, 提现转账回调MD5）
	 * @return 验证结果
	 */
	public static boolean verify(Map<String, String[]> params, String signType) {
		logger.info("api#pay#alipay#verify | 转换前: {}", StaticKeyHelper.encryptKey(JsonUtils.toJSONString(params)));
		Map<String, String> paramsMap = new HashMap<String, String>(params.size() * 4 / 3 + 1);
		for (Map.Entry<String, String[]> entry : params.entrySet()) {
			paramsMap.put(entry.getKey(), StringUtils.join(entry.getValue()));
		}
		logger.info("api#pay#alipay#verify | 转换后: {}", StaticKeyHelper.encryptKey(JsonUtils.toJSONString(paramsMap)));

		// 判断responsetTxt是否为true，isSign是否为true
		// responsetTxt的结果不是true，与服务器设置问题、合作身份者ID、notify_id一分钟失效有关
		// isSign不是true，与安全校验码、请求时的参数格式（如：带自定义参数等）、编码格式有关
		String responseTxt = "true";
		if (params.get("notify_id") != null) {
			String notify_id = paramsMap.get("notify_id");
			responseTxt = verifyResponse(notify_id);
		}
		String sign = "";
		if (params.get("sign") != null) {
			sign = paramsMap.get("sign");
		}
		boolean isSign = checkSignVeryfy(paramsMap, sign, signType);
		logger.info("api#pay#alipay#verify | 验证结果 | responseTxt: {}, isSign: {}", responseTxt, isSign);

		// 写日志记录（若要调试，请取消下面两行注释）
		// String sWord = "responseTxt=" + responseTxt + "\n isSign=" + isSign + "\n 返回回来的参数：" + AlipayCore.createLinkString(params);
		// AlipayCore.logResult(sWord);

		if (isSign && responseTxt.equals("true")) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * 根据反馈回来的信息，生成签名结果，跟参数sign比较<br>
	 * 用于判断支付宝回调的签名是否正确
	 * 
	 * @param params 通知返回来的参数数组
	 * @param sign 比对的签名结果
	 * @param signType 签名方式（支付回调RSA，退款回调MD5）
	 * @return 生成的签名结果
	 */
	private static boolean checkSignVeryfy(Map<String, String> params, String sign, String signType) {
		// 过滤空值、sign与sign_type参数
		Map<String, String> sParaNew = CoreUtils.paraFilter(params);
		// 获取待签名字符串
		String preSignStr = CoreUtils.createAlipayLinkString(sParaNew);
		// 获得签名验证结果
		boolean isSign = false;
		if (signType.equals("RSA")) {
			isSign = RSAUtil.verify(preSignStr, sign, TerraceUtils.INSTANCE.getAlipay().getPublicKey(), TerraceUtils.INSTANCE.getAlipay()
					.getCharset());
		} else if (signType.equals("MD5")) {
			isSign = MD5.verify(preSignStr, sign, TerraceUtils.INSTANCE.getAlipayRefund().getKey(), TerraceUtils.INSTANCE.getAlipayRefund()
					.getCharset());
		}
		return isSign;
	}
	
	/**
	 * 获取远程服务器ATN结果,验证返回URL
	 * 
	 * @param notify_id 通知校验ID
	 * @return 服务器ATN结果 验证结果集： invalid命令参数不对 出现这个错误，请检测返回处理中partner和key是否为空 true 返回正确信息 false 请检查防火墙或者是服务器阻止端口问题以及验证时间是否超过一分钟
	 */
	public static String verifyResponse(String notify_id) {
		// 获取远程服务器ATN结果，验证是否是支付宝服务器发来的请求
		String partner = TerraceUtils.INSTANCE.getAlipay().getPartner();
		String veryfy_url = HTTPS_VERIFY_URL + "partner=" + partner + "&notify_id=" + notify_id;

		String inputLine = "";

		try {
			URL url = new URL(veryfy_url);
			HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
			BufferedReader in = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
			inputLine = in.readLine().toString();
		} catch (Exception e) {
			e.printStackTrace();
			inputLine = "";
		}

		return inputLine;
	}

	/**
	 * 解析支付宝回调的明细结果。
	 * 
	 * @author Hins
	 * @date 2015年9月25日 下午4:00:24
	 * 
	 * @param resultDetail
	 * @return 交易号对应退款结果map集合（key-交易号，value-退款结果String类型）
	 */
	public static Map<String, String> splitResultDetail(String resultDetail) {
		// 每一笔退款结果数组： 每个元素的结构：交易号^退款金额^处理结果$退费账号^退费账户ID^退费金额^处理结果（一般不会有$后面的，除非我们跟支付签订了退款时候退手续费协议）
		String[] resultArray = resultDetail.split("#");
		// 交易号对应退款结果map集合（key-交易号，value-退款结果String类型）
		Map<String, String> result = new HashMap<String, String>(resultArray.length * 4 / 3 + 1);
		for (String rs : resultArray) {
			String[] infoArray = rs.split("\\^"); // 截取"交易号^退款金额^处理结果"格式字符串
			String tradeNo = infoArray[0];// 交易号
			String rfrs = infoArray[2]; // 处理结果
			result.put(tradeNo, rfrs);
		}
		return result;
	}
	
}
