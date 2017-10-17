package com.yogu.services.order.utils.sign.wechat;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;

import org.apache.commons.lang3.StringUtils;

import com.yogu.services.order.utils.sign.common.CoreUtils;
import com.yogu.services.order.utils.sign.common.MD5;

/**
 * 请求微信接口相关操作工具类
 * 
 * @author Hins
 * @date 2016年2月18日 下午2:48:30
 */
public class WechatSubmitUtils {
	
	/**
	 * 生成微信sign签名，并将小写字母转成大写<br>
	 * 暂时用于微信统一下单sign
	 * 
	 * @author Hins
	 * @date 2016年1月30日 下午2:50:37
	 * 
	 * @param parameters - 接口参数
	 * @param key - 微信支付key
	 * @return 签名sign
	 *
	 */
	public static String createSign(SortedMap<String, String> parameters, String key) {
		// 过滤空值、sign与sign_type参数
		Map<String, String> sParaNew = CoreUtils.paraFilter(parameters);
		
		List<String> keys = new ArrayList<String>(sParaNew.keySet());
		Collections.sort(keys);
		StringBuffer prestr = new StringBuffer();

		for (int i = 0; i < keys.size(); i++) {
			String k = keys.get(i);
			String value = sParaNew.get(k);
			if (StringUtils.isBlank(value) || "sign".equals(k) || "key".equals(k)) {
				continue;
			}
			prestr.append(k).append("=").append(value).append("&");
		}
		
		String sign = MD5.sign(prestr.toString(), "key=" + key, "UTF-8").toUpperCase();
		return sign;
	}

	/**
	 * 根据接口的参数和值，转换对应的xml字符串
	 * 
	 * @author Hins
	 * @date 2016年1月30日 下午2:48:55
	 * 
	 * @param parameters - 接口参数
	 * @return xml完整格式字符串
	 */
	public static String formatTreeMapToXml(SortedMap<String, String> parameters) {
		StringBuffer sb = new StringBuffer("<xml>");
		Set es = parameters.entrySet();
		Iterator it = es.iterator();
		while (it.hasNext()) {
			Map.Entry entry = (Map.Entry) it.next();
			String k = (String) entry.getKey();
			String v = (String) entry.getValue();
			if (!"appkey".equals(k)) {
				v = escapeSpecialChar(v);
				sb.append("<" + k + ">" + v + "</" + k + ">" + "\r\n");
			}
		}
		sb.append("</xml>");
		return sb.toString();
	}
	
	/**
	 * xml特殊字符转义
	 * 
	 * @author Hins
	 * @date 2016年2月18日 下午5:14:35
	 * 
	 * @param value
	 * @return
	 */
	private static String escapeSpecialChar(String value) {
		value = StringUtils.replace(value, "&", "&amp;");
		value = StringUtils.replace(value, "<", "&lt;");
		value = StringUtils.replace(value, ">", "&gt;");
		value = StringUtils.replace(value, "\"", "&quot;");
		value = StringUtils.replace(value, "'", "&apos;");
		return value;
	}

}
