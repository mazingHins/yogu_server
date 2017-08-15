package com.yogu.core.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 支付宝表单请求相关工具类
 * @author Hins
 * @date 2015年9月16日 上午11:28:45
 */
public class AlipayHttpReq {
	
	 /**
     * 支付宝提供给商户的服务接入网关URL(新)
     */
    private static final String ALIPAY_GATEWAY_NEW = "https://mapi.alipay.com/gateway.do";
    
    /**
     * 指定编码集, 当post时使用
     */
    private static final String CHARSET = "?_input_charset=utf-8";
    
	/**
	 * 建立请求，以表单HTML形式构造（默认）
	 * 
	 * @param sPara 请求参数数组
	 * @param strMethod 提交方式。两个值可选：post、get
	 * @param strButtonName 确认按钮显示文字
	 * @return 提交表单HTML文本
	 */
	public static String buildRequest(Map<String, String> sPara, String strMethod, String strButtonName) {
		// 待请求参数数组
		List<String> keys = new ArrayList<String>(sPara.keySet());

		StringBuffer sbHtml = new StringBuffer();

		sbHtml.append("<html xmlns=\"http://www.w3.org/1999/xhtml\"><head><meta http-equiv=\"content-type\" content=\"text/html;charset=UTF-8\"><title>处理中...</title><body><form id=\"alipaysubmit\" name=\"alipaysubmit\" action=\"" + ALIPAY_GATEWAY_NEW + (strMethod.toLowerCase().equals("post") ? CHARSET : "") + "\" method=\"" + strMethod + "\">");

		for (int i = 0; i < keys.size(); i++) {
			String name = (String) keys.get(i);
			String value = (String) sPara.get(name);

			sbHtml.append("<input type=\"hidden\" name=\"" + name + "\" value=\"" + value + "\"/>");
		}

		// submit按钮控件请不要含有name属性
		sbHtml.append("<input type=\"submit\" value=\"" + strButtonName + "\" style=\"display:none;\"></form>");
		sbHtml.append("<script>document.forms['alipaysubmit'].submit();</script></body></html>");

		return sbHtml.toString();
	}
	

   
}
