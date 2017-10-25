package com.yogu.services.order.utils;

import java.util.SortedMap;
import java.util.TreeMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.yogu.commons.server.context.MainframeBeanFactory;
import com.yogu.commons.utils.HttpClientUtils;
import com.yogu.commons.utils.JsonUtils;
import com.yogu.commons.utils.VOUtil;
import com.yogu.core.web.ResultCode;
import com.yogu.core.web.exception.ServiceException;
import com.yogu.remote.store.StoreRemoteService;
import com.yogu.services.order.pay.service.params.PayReqParams;
import com.yogu.services.order.utils.protocol.WechatApplyRefundResData;
import com.yogu.services.order.utils.protocol.WechatBaseResData;
import com.yogu.services.order.utils.protocol.WechatQueryRefundResData;
import com.yogu.services.order.utils.protocol.WechatUnifiedOrderResData;
import com.yogu.services.order.utils.sign.wechat.WechatNotifyUtils;
import com.yogu.services.order.utils.sign.wechat.WechatSubmitUtils;

public class WechatApiReqHandler {

	private static final Logger refundLogger = LoggerFactory.getLogger("com.mazing.services.pay.refund.refundLog");

	/**
	 * 查询微信退款结果，并返回结果内容（无论退款成功/失败等）
	 * 
	 * @author Hins
	 * @date 2016年2月15日 下午4:14:26
	 * 
	 * @param appId
	 * @param refundNo - 退款编号
	 * @return
	 */
	public static WechatQueryRefundResData queryRefund(String appId, long refundNo) {
		Builder builder = new Builder();
		// 构造请求参数
		builder.initQueryRefundParamters(appId, refundNo);
		// 调用微信“查询退款api”并返回结果
		return builder.queryRefund(appId);
	}
	
	public static void main(String[] args) {
//		WechatQueryRefundResData data = WechatApiReqHandler.queryRefund("wx7cc235b8985d1466", 1612172880581764L);
//		System.out.println(JsonUtils.toJSONString(data));
		
		SortedMap<String, String> parameters = new TreeMap<String, String>();
		parameters.put("appid", "wx3802b70c1bc8516e");
		parameters.put("mch_id", "1425163602");
		parameters.put("nonce_str", String.valueOf(System.currentTimeMillis()));
		parameters.put("body", "1");
//		parameters.put("detail", "开饭A88号桌");
		parameters.put("out_trade_no", "88888888");
		parameters.put("fee_type", "CNY");
		parameters.put("total_fee", "1");
		parameters.put("spbill_create_ip", "192.168.1.1");
		parameters.put("notify_url", "http://www.baidu.com");
		parameters.put("trade_type", "JSAPI");
		// #TODO 根据buyerUid获取用户的openid
		parameters.put("openid", "ovnn50P35gt4JiCFu4r5fN0h8Wls");
		
		String key = "YwMybG01txRpCdNjusbrJ3ORAK9bh6Ry";
		String sign = WechatSubmitUtils.createSign(parameters, key);
		parameters.put("sign", sign);

		// 将参数转成xml格式字符串
		String xml = WechatSubmitUtils.formatTreeMapToXml(parameters);
		System.out.println(xml);
		System.out.println("=======================================");
		String result = HttpClientUtils.doPost("https://api.mch.weixin.qq.com/pay/unifiedorder", xml);
		System.out.println(result);
		System.out.println("=======================================");
		try {
			// response = WechatUtils.getMapFromXML(result);
			WechatUnifiedOrderResData data = (WechatUnifiedOrderResData) WechatNotifyUtils.getObjectFromXML(result, WechatUnifiedOrderResData.class);
			System.out.println(JsonUtils.toJSONString(data));
		} catch (Exception e) {
		}
	}

	/**
	 * 申请退款，并返回请求api结果<br>
	 * 因为微信支付有不同的appId和key，所以申请退款的时候，需要根据支付时候使用的appId申请退款<br>
	 * 所以参数appId是获取微信回调记录WechatPayNotify的appId，且要解密后的
	 * 
	 * @author Hins
	 * @date 2016年2月3日 下午5:02:01
	 * 
	 * @param appId
	 * @return
	 * @throws Exception
	 */
	public static WechatApplyRefundResData applyRefund(String appId, long payNo, long refundNo, long refundFee) {
		Builder builder = new Builder();
		// 构造请求参数
		builder.initApplyRefundParamters(appId, payNo, refundNo, refundFee);
		refundLogger.info("pay#WechatApiReqHandler#applyRefund | 请求微信退款");
		// 调用微信“申请退款api”并返回结果
		return builder.applyRefund(appId);
	}

	/**
	 * 调用微信“统一下单”api，并解析返回结果
	 * 
	 * @author Hins
	 * @date 2016年1月30日 下午5:00:14
	 * 
	 * @param payNo - 支付编号
	 * @param params - order获取支付sdk的请求参数
	 */
	public static WechatUnifiedOrderResData createUnified(long payNo, PayReqParams params) {
		Builder builder = new Builder();
		// 构造请求参数
		builder.initUnifiedorderParamete(payNo, params);
		// 调用微信“统一下单api”并返回结果
		return builder.createUnifiedorder(payNo, params.getTarget(), params.getSysType());
	}
	
	/**
	 * 输出微信接口请求日志<br>
	 * 只清空appid，mchid，sign等，然后直接JsonUtils.toJSONString输出字符串<br>
	 * 如果传入的参数非WechatBaseResData子类，返回WechatBaseResData相关的日志(appid，mchid，sign清空)
	 * 
	 * @author Hins
	 * @date 2016年3月14日 下午2:22:54
	 * 
	 * @param data
	 * @return
	 */
	public static String wechatApiLog(WechatBaseResData data){
		refundLogger.info("pay#WechatApiReqHandler#wechatApiLog | 打印微信支付api日志");
		if (data == null) {
			refundLogger.info("pay#WechatApiReqHandler#wechatApiLog | 微信支付api相应数据为空");
			return null;
		}

		if (data instanceof WechatUnifiedOrderResData) {
			WechatUnifiedOrderResData tmp = VOUtil.from(data, WechatUnifiedOrderResData.class);
			tmp.setAppid(null);
			tmp.setMch_id(null);
			tmp.setSign(null);
			return JsonUtils.toJSONString(tmp);
		}

		if (data instanceof WechatApplyRefundResData) {
			WechatApplyRefundResData tmp = VOUtil.from(data, WechatApplyRefundResData.class);
			tmp.setAppid(null);
			tmp.setMch_id(null);
			tmp.setSign(null);
			return JsonUtils.toJSONString(tmp);
		}

		if (data instanceof WechatQueryRefundResData) {
			WechatQueryRefundResData tmp = VOUtil.from(data, WechatQueryRefundResData.class);
			tmp.setAppid(null);
			tmp.setMch_id(null);
			tmp.setSign(null);
			return JsonUtils.toJSONString(tmp);
		}

		WechatBaseResData tmp = VOUtil.from(data, WechatBaseResData.class);
		tmp.setAppid(null);
		tmp.setMch_id(null);
		tmp.setSign(null);
		return JsonUtils.toJSONString(tmp);
	}

	public static class Builder {

		/**
		 * 请求参数，用于计算当次请求的sign
		 */
		private SortedMap<String, String> parameters;

		/**
		 * 查询退款api地址
		 */
		private static final String QUERY_REFUND_URL = "https://api.mch.weixin.qq.com/pay/refundquery";

		/**
		 * 申请退款api地址
		 */
		private static final String APPLY_REFUND_URL = "https://api.mch.weixin.qq.com/secapi/pay/refund";

		/**
		 * 统一下单api地址
		 */
		private static final String UNIFIEDORDER_URL = "https://api.mch.weixin.qq.com/pay/unifiedorder";
		
		/**
		 * 初始化查询退款接口的参数
		 * 
		 * @author Hins
		 * @date 2016年1月30日 下午2:52:25
		 *
		 */
		public void initQueryRefundParamters(String appId, long refundNo) {
			parameters = new TreeMap<String, String>();
			parameters.put("appid", appId);
			parameters.put("mch_id", TerraceUtils.INSTANCE.getWechat().getMahIdByAppId(appId));
			parameters.put("out_refund_no", String.valueOf(refundNo));
			parameters.put("nonce_str", String.valueOf(System.currentTimeMillis())); // #TODO 暂时用当前时间毫秒数代替
		}

		/**
		 * 查询微信退款结果，并返回结果内容（无论退款成功/失败等）
		 * 
		 * @author Hins
		 * @date 2016年2月15日 下午4:14:26
		 * 
		 * @param appId
		 * @return
		 */
		public WechatQueryRefundResData queryRefund(String appId) {

			// initQueryRefundParamters(appId, refundNo);

			String sign = WechatSubmitUtils.createSign(this.parameters, TerraceUtils.INSTANCE.getWechat().getKeyByAppId(appId));
			this.parameters.put("sign", sign);

			// 将参数转成xml格式字符串
			String xml = WechatSubmitUtils.formatTreeMapToXml(this.parameters);
			String result = HttpClientUtils.doPost(QUERY_REFUND_URL, xml);
			try {
				// Map<String,Object> map = WechatUtils.getMapFromXML(result);
				// #TODO 微信“查询退款”api的返回结果参数名存在变量refund_status_$n，$n=0,1,2
				// 因为微信支付同一笔订单多次退款，所以如果3次退款会有refund_status_0，refund_status_1，refund_status_2
				// 返回。但是我们只允许一次退款，所以不考虑refund_status_1这些，直接用refund_status_0获取
				WechatQueryRefundResData response = (WechatQueryRefundResData) WechatNotifyUtils.getObjectFromXML(result, WechatQueryRefundResData.class);
				return response;
			} catch (Exception e) {
				throw new ServiceException(ResultCode.PARAMETER_ERROR, "查询失败，请重新尝试");
			}

		}

		/**
		 * 初始化申请退款接口的参数
		 * 
		 * @author Hins
		 * @date 2016年1月30日 下午2:52:25
		 *
		 */
		public void initApplyRefundParamters(String appId, long payNo, long refundNo, long refundFee) {
			parameters = new TreeMap<String, String>();
			parameters.put("appid", appId);
			parameters.put("mch_id", TerraceUtils.INSTANCE.getWechat().getMahIdByAppId(appId));
			parameters.put("nonce_str", String.valueOf(System.currentTimeMillis()));	// #TODO 暂时用当前时间毫秒数代替
			parameters.put("out_trade_no", String.valueOf(payNo));
			parameters.put("total_fee", String.valueOf(refundFee));
			parameters.put("refund_fee", String.valueOf(refundFee));
			parameters.put("refund_fee_type", "CNY");
			parameters.put("op_user_id", TerraceUtils.INSTANCE.getWechat().getMahIdByAppId(appId));
			parameters.put("out_refund_no", String.valueOf(refundNo));
		}

		/**
		 * 申请退款，并返回请求api结果<br>
		 * 因为微信支付有不同的appId和key，所以申请退款的时候，需要根据支付时候使用的appId申请退款<br>
		 * 所以参数appId是获取微信回调记录WechatPayNotify的appId，且要解密后的
		 * 
		 * @author Hins
		 * @date 2016年2月3日 下午5:02:01
		 * 
		 * @param appId
		 * @return
		 * @throws Exception
		 */
		public WechatApplyRefundResData applyRefund(String appId) {

			// initApplyRefundParamters(appId, payNo, refundNo, refundFee);

			String sign = WechatSubmitUtils.createSign(this.parameters, TerraceUtils.INSTANCE.getWechat().getKeyByAppId(appId));
			this.parameters.put("sign", sign);

			String xml = WechatSubmitUtils.formatTreeMapToXml(this.parameters);
			String path = MainframeBeanFactory.configPath + TerraceUtils.INSTANCE.getWechat().getCertPath(appId);
			String pwd = TerraceUtils.INSTANCE.getWechat().getMahIdByAppId(appId);

			String result = HttpClientUtils.doPostBySSL(APPLY_REFUND_URL, xml, path, pwd);
			refundLogger.info("pay#WechatApiReqHandler#applyRefund | 请求微信退款结果 | result: {}", result);
			try {
				// Map response = WechatUtils.getMapFromXML(result);
				return (WechatApplyRefundResData) WechatNotifyUtils.getObjectFromXML(result, WechatApplyRefundResData.class);
			} catch (Exception e) {
				refundLogger.error("pay#WechatApiReqHandler#applyRefund | parse xml 出错", e);
				throw new ServiceException(ResultCode.PARAMETER_ERROR, "申请退款失败，请重新尝试");
			}
		}

		/**
		 * 初始化统一下单接口的参数
		 * 
		 * @author Hins
		 * @date 2016年1月30日 下午2:52:25
		 *
		 */
		public void initUnifiedorderParamete(long payNo, PayReqParams params) {
			parameters = new TreeMap<String, String>();
			String appId = TerraceUtils.INSTANCE.getWechat().getAppid();
			parameters.put("appid", appId);
			parameters.put("mch_id", TerraceUtils.INSTANCE.getWechat().getMchId());
			parameters.put("body", params.getSubject());
			parameters.put("detail", params.getBody());
			parameters.put("out_trade_no", String.valueOf(payNo));
			parameters.put("fee_type", "CNY");
			parameters.put("total_fee", String.valueOf(params.getTotalFee()));
			parameters.put("spbill_create_ip", params.getUserIp());
			parameters.put("notify_url", TerraceUtils.INSTANCE.getWechat().getNotifyUrl());
			parameters.put("trade_type", "APP");
			parameters.put("nonce_str", String.valueOf(System.currentTimeMillis()));// #TODO 暂时用当前时间毫秒数代替
		}
		
		/**
		 * 调用微信“统一下单”api，并解析返回结果
		 * 
		 * @author Hins
		 * @date 2016年1月30日 下午5:00:14
		 * 
		 * @param params - order获取支付sdk的请求参数
		 */
		public WechatUnifiedOrderResData createUnifiedorder(long payNo, String target, short sysType) {
			// 初始化统一下单api接口请求参数
			// initUnifiedorderParamete(payNo, params);

			// 生成统一下单api接口的sign签名，并将签名装载到请求参数
			String sign = WechatSubmitUtils.createSign(this.parameters, TerraceUtils.INSTANCE.getWechat().getKey(target, sysType));
			this.parameters.put("sign", sign);

			// 将参数转成xml格式字符串
			String xml = WechatSubmitUtils.formatTreeMapToXml(this.parameters);
			String result = HttpClientUtils.doPost(UNIFIEDORDER_URL, xml);
			try {
				// response = WechatUtils.getMapFromXML(result);
				WechatUnifiedOrderResData data = (WechatUnifiedOrderResData) WechatNotifyUtils.getObjectFromXML(result, WechatUnifiedOrderResData.class);
				return data;
			} catch (Exception e) {
				throw new ServiceException(ResultCode.PARAMETER_ERROR, "下单失败，请重新尝试");
			}
		}
		
		/**
		 * 调用微信 网页版(公众号)“统一下单”api，并解析返回结果
		 * 
		 * @return
		 */
		public WechatUnifiedOrderResData createUnifiedOrderH5Pay(String key) {
			String sign = WechatSubmitUtils.createSign(this.parameters, key);
			this.parameters.put("sign", sign);

			// 将参数转成xml格式字符串
			String xml = WechatSubmitUtils.formatTreeMapToXml(this.parameters);
			String result = HttpClientUtils.doPost(UNIFIEDORDER_URL, xml);
			try {
				// response = WechatUtils.getMapFromXML(result);
				WechatUnifiedOrderResData data = (WechatUnifiedOrderResData) WechatNotifyUtils.getObjectFromXML(result, WechatUnifiedOrderResData.class);
				return data;
			} catch (Exception e) {
				throw new ServiceException(ResultCode.PARAMETER_ERROR, "下单失败，请重新尝试");
			}
		}
		
		private StoreRemoteService storeRemoteService = new StoreRemoteService();

	}

}
