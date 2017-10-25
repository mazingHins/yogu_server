package com.yogu.services.order.resource.open;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.yogu.commons.server.context.MainframeBeanFactory;
import com.yogu.commons.utils.DateUtils;
import com.yogu.core.enums.BooleanConstants;
import com.yogu.core.web.encrypt.StaticKeyHelper;
import com.yogu.services.order.pay.dto.WechatPayNotify;
import com.yogu.services.order.pay.service.PayService;
import com.yogu.services.order.utils.TerraceUtils;
import com.yogu.services.order.utils.sign.wechat.WechatNotifyUtils;
import com.yogu.services.order.utils.sign.wechat.WechatSubmitUtils;


/**
 * 接收微信支付回调接口
 * 
 * @author Hins
 * @date 2016年2月1日 下午2:41:30
 */
@WebServlet("/open/notify/pay/wechat.do")
public class WechatPayNotifyServlet extends HttpServlet {

	private static final long serialVersionUID = 348356556510341099L;

	private static final Logger logger = LoggerFactory.getLogger(WechatPayNotifyServlet.class);

	private PayService payService;

	private static Set<String> excludeLogParams; // 不需要日志打印参数
	static {
		excludeLogParams = new HashSet<String>(4);
		excludeLogParams.add("appid");
		excludeLogParams.add("mch_id");
		excludeLogParams.add("openid");
	}

	/**
	 * log 参数
	 * 
	 * @param reqParams
	 * @author ten 2015/11/10
	 */
	// private void logParams(Map<String, String[]> reqParams) {
	// Iterator<Map.Entry<String, String[]>> iter = reqParams.entrySet().iterator();
	// StringBuilder buf = new StringBuilder(reqParams.size() * 16 + 16);
	// while (iter.hasNext()) {
	// Map.Entry<String, String[]> entry = iter.next();
	// if (!excludeLogParams.contains(entry.getKey())) {
	// buf.append(entry.getKey()).append(": ");
	// if (entry.getValue() == null) {
	// buf.append("null");
	// } else {
	// buf.append(org.apache.commons.lang3.StringUtils.join(entry.getValue(), ','));
	// }
	// buf.append(", ");
	// }
	// }
	// logger.info("api#pay#wechat#payNotify | 微信充值回调 | params: {}", buf.toString());
	// }

	/**
	 * log参数
	 * 
	 * @author Hins
	 * @date 2016年2月3日 上午11:18:49
	 * 
	 * @param params
	 */
	private void logParams(Map<String, String> params) {
		Iterator<Map.Entry<String, String>> iter = params.entrySet().iterator();
		StringBuilder buf = new StringBuilder(params.size() * 16 + 16);
		while (iter.hasNext()) {
			Map.Entry<String, String> entry = iter.next();
			if (!excludeLogParams.contains(entry.getKey())) {
				buf.append(entry.getKey()).append(": ");
				if (entry.getValue() == null) {
					buf.append("null");
				} else {
					buf.append(org.apache.commons.lang3.StringUtils.join(entry.getValue(), ','));
				}
				buf.append(", ");
			}
		}
		logger.info("api#pay#wechat#payNotify | 微信充值回调 | params: {}", buf.toString());
	}

	/**
	 * 获取回调的参数值<br>
	 * 如果集合中不存在此值，返回“”
	 * 
	 * @author Hins
	 * @date 2016年2月1日 下午3:16:47
	 * 
	 * @param parameters - 参数集合
	 * @param key - 集合key
	 * @return
	 */
	private String getParameters(SortedMap<String, String> parameters, String key) {
		String result = (String) parameters.get(key);
		return (null == result) ? "" : result;
	}

	/**
	 * 接收微信支付异步回调接口 验证是否支付宝合法回调： 1. 在通知参数中，获取notify_id参数，获取远程服务器https://mapi.alipay.com/gateway.do结果,若为true，则是支付宝请求 2.
	 * 在服务器异步通知参数列表中，除去sign、sign_type两个参数外，凡是通知返回回来的参数皆是要验签的参数 通过验证后，执行处理支付记录流程（回调内部第三方等），处理完成后，输出success，否则支付宝回继续回调。
	 * 
	 * @return 成功 输出success；失败输出fail
	 */
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			// 1. 获取微信回调内容
			String requestXml = getRequestContent(req);
			// 2. 将xml格式的内容转成map
			Map<String, String> parameters = WechatNotifyUtils.getMapFromXML(requestXml);
			// 3. 打印日志
			logParams(parameters);

			// 4. 将无序的参数map转成有序
			SortedMap<String, String> sortedMap = new TreeMap<String, String>();
			sortedMap.putAll(parameters);
			
			if (!getParameters(sortedMap, "return_code").equals("SUCCESS")) {
				logger.error("api#pay#wechat#payNotify | 微信回调通信结果-支付失败 | return_msg: {}", getParameters(sortedMap, "return_msg"));
				// 返回响应结果
				String xml = initResponseXml("SUCCESS", "OK");
				resp.getWriter().write(xml);
				return;
			}
			
			boolean flag = false;
			// 5. 判断是否合法微信回调
			String appId = parameters.get("appid");
			
			String subAppid = parameters.get("sub_appid");
			//不subAppid=null, 普通商户模式
			if (StringUtils.isBlank(subAppid)) {
				if(WechatNotifyUtils.isWechatPaySign(sortedMap, TerraceUtils.INSTANCE.getWechat().getKeyByAppId(appId))){
					flag = true;
				}
			}else{
				//不subAppid!=null, 服务商模式
				if(WechatNotifyUtils.isWechatPaySign(sortedMap, TerraceUtils.INSTANCE.getWechat().getServiceMpKey())){
					flag = true;
				}
			}
			
			if(flag){
				WechatPayNotify notify = initNotifyDto(sortedMap);
				getService().wechatNotify(notify);
				
				// 返回响应结果
				String xml = initResponseXml("SUCCESS", "OK");
				resp.getWriter().write(xml);
			}else{
				logger.error("api#pay#wechat#payNotify | 微信回调通信结果- 签名失败 | return_msg: {}", getParameters(sortedMap, "return_msg"));
				String xml = initResponseXml("FAIL", "签名失败");
				resp.getWriter().write(xml);
				resp.getWriter().write(xml);
			}
		} catch (Exception e) {
			logger.error("api#pay#wechat#payNotify | 解析微信回调结果失败 | e: {}", e.getMessage());
			String xml = initResponseXml("FAIL", "参数格式校验错误");
			resp.getWriter().write(xml);
		} finally {
			resp.getWriter().flush();
		}

	}

	/**
	 * 初始化回调返回内容
	 * 
	 * @author Hins
	 * @date 2016年2月3日 上午11:30:25
	 * 
	 * @param returnCode - 返回状态码
	 * @param returnMsg - 返回信息
	 * @return
	 */
	private String initResponseXml(String returnCode, String returnMsg) {
		SortedMap<String, String> responseMap = new TreeMap<String, String>();
		responseMap.put("return_code", returnCode);
		responseMap.put("return_msg", returnMsg);
		String xml = WechatSubmitUtils.formatTreeMapToXml(responseMap);
		return xml;
	}

	/**
	 * 获取回调内容
	 * 
	 * @author Hins
	 * @date 2016年2月3日 上午11:27:21
	 * 
	 * @param req
	 * @return
	 * @throws IOException
	 */
	private String getRequestContent(HttpServletRequest req) throws IOException {
		InputStream in = null;
		try {
			in = req.getInputStream();
			BufferedReader read = new BufferedReader(new InputStreamReader(in, "UTF-8"));
			String result = "";
			String line = "";
			while ((line = read.readLine()) != null) {
				result += line;
			}
			return result;
		} catch (IOException e) {
			logger.error("api#pay#wechat#getRequestContent | e: {}", e.getMessage());
		} finally {
			in.close();
		}

		return null;
	}

	/**
	 * 通过微信支付后，初始化微信回调通知dto
	 * 
	 * @author Hins
	 * @date 2016年2月2日 下午4:42:13
	 * 
	 * @param parameters
	 * @return
	 * @throws ParseException
	 */
	private WechatPayNotify initNotifyDto(SortedMap<String, String> parameters) throws ParseException {
		WechatPayNotify notify = new WechatPayNotify();
		notify.setAppId(StaticKeyHelper.encryptKey(getParameters(parameters, "appid")));
		notify.setBankType(getParameters(parameters, "bank_type"));
		notify.setErrCode(getParameters(parameters, "err_code"));
		notify.setErrCodeDes(getParameters(parameters, "err_code_des"));
		notify.setFeeType(getParameters(parameters, "fee_type"));
		notify.setOpenid(StaticKeyHelper.encryptKey(getParameters(parameters, "openid")));
		Date payTime = DateUtils.parseString(getParameters(parameters, "time_end"), DateUtils.YYYYMMDDHHMMSS);
		notify.setTradePayTime(payTime);
		notify.setResultCode("SUCCESS".equals(getParameters(parameters, "result_code")) ? BooleanConstants.TRUE : BooleanConstants.FALSE);
		notify.setTotalFee(Integer.valueOf(getParameters(parameters, "total_fee")));
		notify.setTransactionId(getParameters(parameters, "transaction_id"));
		notify.setPayNo(Long.valueOf(getParameters(parameters, "out_trade_no")));
		return notify;
	}

	/**
	 * 获取PayService实例
	 */
	private synchronized PayService getService() {
		if (null == payService)
			payService = MainframeBeanFactory.getBean(PayService.class);
		return payService;
	}

}
