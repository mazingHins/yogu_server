package com.yogu.services.order.utils.sign.wechat;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;

import com.thoughtworks.xstream.XStream;
import com.yogu.services.order.utils.protocol.WechatBaseResData;

/**
 * 微信回调/接口请求结果相关工具类
 * 
 * @author Hins
 * @date 2016年2月18日 下午2:48:48
 */
public class WechatNotifyUtils {
	
	/**
	 * 判断是否微信支付签名<br>
	 * 规则：参数名称a-z排序,遇到空值的参数不参加签名，排序结果生成sign值，将生成的sign跟参数sign比较<br>
	 * 因为微信有org、prod版的，所以key需要调用者传
	 * 
	 * @author Hins
	 * @date 2016年2月18日 下午2:34:38
	 * 
	 * @param parameters
	 * @param key
	 * @return
	 */
	public static boolean isWechatPaySign(SortedMap<String, String> parameters, String key) {
		String sign = WechatSubmitUtils.createSign(parameters, key);
		String paramsSign = (String) parameters.get("sign");
		if (null == paramsSign) {
			paramsSign = "";
		}
		return paramsSign.equals(sign);
	}
	
	/**
	 * 判断微信api的返回结果最终调用是否成功<br>
	 * 只有return_code和return_msg都eq SUCCESS，才返回true
	 * 
	 * @author Hins
	 * @date 2016年1月30日 下午3:49:02
	 * 
	 * @return
	 */
	public static boolean valiteApiResponseResult(WechatBaseResData data) {
		if (data == null) {
			return false;
		}
		String returnCode = data.getReturn_code();
		String resultCode = data.getResult_code();
		if ("SUCCESS".equals(returnCode) && "SUCCESS".equals(resultCode)) {
			return true;
		}
		return false;
	}
	
	/**
	 * xml字符串转成指定的entity类
	 * 
	 * @author Hins
	 * @date 2016年2月3日 下午5:14:36
	 * 
	 * @param xml
	 * @param entity
	 * @return
	 */
	public static Object getObjectFromXML(String xml, Class entity){
		  //将从API返回的XML数据映射到Java对象
        XStream xStreamForResponseData = new XStream();
        xStreamForResponseData.alias("xml", entity);
        xStreamForResponseData.ignoreUnknownElements();//暂时忽略掉一些新增的字段
        return xStreamForResponseData.fromXML(xml);
	}
	
	/**
	 * 解析xml,返回第一级元素键值对。如果第一级元素有子节点，则此节点的值是子节点的xml数据。<br>
	 * 推荐使用getObjectFromXML解析xml
	 * 
	 * @param strxml
	 * @return
	 * @throws JDOMException
	 * @throws IOException
	 */
	@Deprecated
	public static Map getMapFromXML(String strxml) throws JDOMException, IOException {
		strxml = strxml.replaceFirst("encoding=\".*\"", "encoding=\"UTF-8\"");

		if (null == strxml || "".equals(strxml)) {
			return null;
		}

		Map m = new HashMap();

		InputStream in = new ByteArrayInputStream(strxml.getBytes("UTF-8"));
		SAXBuilder builder = new SAXBuilder();
		Document doc = builder.build(in);
		Element root = doc.getRootElement();
		List list = root.getChildren();
		Iterator it = list.iterator();
		while (it.hasNext()) {
			Element e = (Element) it.next();
			String k = e.getName();
			String v = "";
			List children = e.getChildren();
			if (children.isEmpty()) {
				v = e.getTextNormalize();
			} else {
				v = getChildrenText(children);
			}

			m.put(k, v);
		}

		// 关闭流
		in.close();

		return m;
	}
	
	/**
	 * 获取子结点的xml<br>
	 * 用上递归，如果子节点内还有子节点，则递归
	 * 
	 * @param children
	 * @return 
	 */
	private static String getChildrenText(List children) {
		StringBuffer sb = new StringBuffer();
		if (!children.isEmpty()) {
			Iterator it = children.iterator();
			while (it.hasNext()) {
				Element e = (Element) it.next();
				String name = e.getName();
				String value = e.getTextNormalize();
				List list = e.getChildren();
				sb.append("<" + name + ">");
				if (!list.isEmpty()) {
					sb.append(getChildrenText(list));
				}
				sb.append(value);
				sb.append("</" + name + ">");
			}
		}

		return sb.toString();
	}

}
