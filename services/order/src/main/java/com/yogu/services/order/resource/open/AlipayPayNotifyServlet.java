package com.yogu.services.order.resource.open;

import java.io.IOException;
import java.text.ParseException;
import java.util.Iterator;
import java.util.Map;
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
import com.yogu.core.enums.pay.NotifyEnum;
import com.yogu.core.enums.pay.NotifyEnum.AlipayNotifyType;
import com.yogu.core.enums.pay.NotifyEnum.AlipayTradeStatus;
import com.yogu.core.utils.ComputeUtils;
import com.yogu.core.web.ResultCode;
import com.yogu.core.web.encrypt.StaticKeyHelper;
import com.yogu.core.web.exception.ServiceException;
import com.yogu.services.order.pay.dto.AlipayPayNotify;
import com.yogu.services.order.pay.service.PayService;
import com.yogu.services.order.utils.TerraceUtils;
import com.yogu.services.order.utils.sign.alipay.AlipayNotifyUtils;


/**
 * 接收支付宝支付回调接口
 * 
 * @author Hins
 * @date 2015年9月9日 下午1:04:38
 */
@WebServlet("/open/notify/pay/alipay.do")
public class AlipayPayNotifyServlet extends HttpServlet {

	private static final long serialVersionUID = -1107679591424014354L;

	private static final Logger logger = LoggerFactory.getLogger(AlipayPayNotifyServlet.class);

	private PayService payService;

	/**
	 * log 参数
	 * 
	 * @param reqParams
	 * @author ten 2015/11/10
	 */
	private void logParams(Map<String, String[]> reqParams) {
		Iterator<Map.Entry<String, String[]>> iter = reqParams.entrySet().iterator();
		StringBuilder buf = new StringBuilder(reqParams.size() * 16 + 16);
		while (iter.hasNext()) {
			Map.Entry<String, String[]> entry = iter.next();
			if (entry.getKey().indexOf("buyer") < 0 && entry.getKey().indexOf("seller") < 0) {
				buf.append(entry.getKey()).append(": ");
				if (entry.getValue() == null) {
					buf.append("null");
				} else {
					buf.append(org.apache.commons.lang3.StringUtils.join(entry.getValue(), ','));
				}
				buf.append(", ");
			}
		}
		logger.info("api#pay#alipay#payNotify | 支付宝充值回调 | params: {}", buf.toString());
	}

	/**
	 * 将回调请求的参数装载到parameters
	 * 
	 * @author Hins
	 * @date 2016年2月1日 下午3:17:00
	 * 
	 * @param reqParams
	 */
	private SortedMap<String, String> initRequestParams(Map<String, String[]> reqParams) {
		SortedMap<String, String> parameters = new TreeMap<String, String>();
		Iterator it = reqParams.keySet().iterator();
		while (it.hasNext()) {
			String k = (String) it.next();
			String v = ((String[]) reqParams.get(k))[0];
			if (StringUtils.isNotBlank(v)) {
				parameters.put(k, v.trim());
			}
		}
		return parameters;
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
	 * 接收支付宝支付异步回调接口 验证是否支付宝合法回调： 1. 在通知参数中，获取notify_id参数，获取远程服务器https://mapi.alipay.com/gateway.do结果,若为true，则是支付宝请求 2.
	 * 在服务器异步通知参数列表中，除去sign、sign_type两个参数外，凡是通知返回回来的参数皆是要验签的参数 通过验证后，执行处理支付记录流程（回调内部第三方等），处理完成后，输出success，否则支付宝回继续回调。
	 * 
	 * @return 成功 输出success；失败输出fail
	 */
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		/*
		 * 支付宝回调，ContentType：application/x-www-form-urlencoded; text/html; charset=utf-8
		 * 
		 * 这是一个不标准的内容；致使jersey无法正常解析内容
		 */
		Map<String, String[]> reqParams = req.getParameterMap();
		if (reqParams != null) {
			logParams(reqParams);
		} else {
			logger.error("api#pay#alipay#payNotify | 支付宝充值回调错误，没有参数");
			resp.getWriter().write("false");
			resp.getWriter().flush();
			return;
		}

		// 2. 将返回的参数初始化到parameters，用于校验是否合法的支付宝回调等
		SortedMap<String, String> parameters = initRequestParams(reqParams);
		try {
			validateParams(parameters);
		} catch (Exception e) {
			resp.getWriter().write("false");
			resp.getWriter().flush();
			return;
		}

		// 验证是否支付宝合法回调
		// 1. 获取通知参数列表，判断是否支付宝合法回调。若不是则输出fail
		// 2. 执行处理支付记录流程（回调内部第三方），并输出success（必须是success）
		try {
			if (AlipayNotifyUtils.verify(reqParams, TerraceUtils.INSTANCE.getAlipay().getSignType())) {
				AlipayPayNotify notify = initNotifyDto(parameters);
				getService().alipayNotify(notify);
				resp.getWriter().write("success");
			} else {
				logger.error("api#pay#alipay#payNotify | 支付宝验证不通过 | out_trade_no: {}, trade_status: {}", reqParams.get("out_trade_no")[0],
						reqParams.get("trade_status")[0]);
				resp.getWriter().write("false");
			}
		} catch (Exception e) {
			logger.error("api#pay#alipay#payNotify | e: {}", e.getMessage());
			resp.getWriter().write("false");
		} finally {
			resp.getWriter().flush();
		}

	}

	/**
	 * 支付宝回调校验通过后，初始化回调通知dto
	 * 
	 * @author Hins
	 * @date 2016年2月2日 下午4:40:54
	 * 
	 * @param parameters
	 * @return
	 * @throws ParseException
	 */
	private AlipayPayNotify initNotifyDto(SortedMap<String, String> parameters) throws ParseException {
		AlipayPayNotify notify = new AlipayPayNotify();
		// 通知校验ID
		notify.setCheckId(getParameters(parameters, "notify_id"));
		// 商户网站唯一订单号
		notify.setPayNo(Long.valueOf(getParameters(parameters, "out_trade_no")));
		// 交易金额
		String totalFee = getParameters(parameters, "total_fee");
		notify.setTotalFee(ComputeUtils.yuanToFen(totalFee));
		// 通知类型：trade_status_sync（固定值）
		AlipayNotifyType notifyType = AlipayNotifyType.valueOf(getParameters(parameters, "notify_type"));
		notify.setNotifyType(notifyType.getValue());
		// 买家支付宝用户号(加密)
		notify.setBuyerId(StaticKeyHelper.encryptKey(getParameters(parameters, "buyer_id")));
		// 买家支付宝账号(加密)
		notify.setBuyerEmail(StaticKeyHelper.encryptKey(getParameters(parameters, "buyer_email")));
		// 交易状态
		AlipayTradeStatus status = NotifyEnum.AlipayTradeStatus.valueOf(getParameters(parameters, "trade_status"));
		notify.setTradeStatus(status.getValue());
		// 支付宝交易号
		notify.setTradeNo(getParameters(parameters, "trade_no"));
		// 商品名称
		notify.setBody(getParameters(parameters, "body"));
		// 交易创建时间
		String gmtCreate = getParameters(parameters, "gmt_create");
		// 交易付款时间
		String gmtPayment = getParameters(parameters, "gmt_payment");
		notify.setTradeCreateTime(DateUtils.getDateFormat(DateUtils.YYYY_MM_DD_HH_mm_ss).parse(gmtCreate));
		if (StringUtils.isNotBlank(gmtPayment)) {
			notify.setTradePayTime(DateUtils.getDateFormat(DateUtils.YYYY_MM_DD_HH_mm_ss).parse(gmtPayment));
		}
		return notify;
	}

	/**
	 * 校验回调通知参数
	 * 
	 * @author Hins
	 * @date 2016年2月2日 下午4:36:56
	 * 
	 * @param parameters
	 */
	private void validateParams(SortedMap<String, String> parameters) {
		AlipayTradeStatus status = NotifyEnum.AlipayTradeStatus.valueOf(getParameters(parameters, "trade_status"));
		if (status == null) { // 校验支付宝交易状态
			logger.error("api#pay#alipay#payNotify | trade status illegal | payNo: {}, tradeStatus: {}",
					getParameters(parameters, "out_trade_no"), getParameters(parameters, "trade_status"));
			throw new ServiceException(ResultCode.PARAMETER_ERROR, "支付宝回调结果-交易状态不合法");
		}

		AlipayNotifyType notifyType = AlipayNotifyType.valueOf(getParameters(parameters, "notify_type"));
		if (notifyType == null) { // 校验支付宝通知类型
			logger.error("api#pay#alipay#payNotify | notify_type illegal | payNo: {}, notify_type: {}",
					getParameters(parameters, "out_trade_no"), getParameters(parameters, "notify_type"));
			throw new ServiceException(ResultCode.PARAMETER_ERROR, "支付宝回调结果-通知类型不合法");
		}
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
