package com.yogu.services.order.utils.sign.alipay;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.yogu.core.web.ResultCode;
import com.yogu.core.web.exception.ServiceException;
import com.yogu.services.order.resource.vo.pay.AlipayRefundReqData;
import com.yogu.services.order.resource.vo.pay.AlipayWithdrawReqData;
import com.yogu.services.order.utils.TerraceUtils;
import com.yogu.services.order.utils.protocol.AlipayCreatePayReqData;
import com.yogu.services.order.utils.sign.common.CoreUtils;
import com.yogu.services.order.utils.sign.common.MD5;
import com.yogu.services.order.utils.sign.common.RSAUtil;

/**
 * 请求支付宝接口相关操作工具类
 * 
 * @author Hins
 * @date 2016年2月18日 下午2:48:09
 */
public class AlipaySubmitUtils {
	
	private static final Logger logger = LoggerFactory.getLogger(AlipaySubmitUtils.class);
	
	/**
	 * 根据退款参数，生成支付宝sign
	 * 
	 * @author Hins
	 * @date 2015年9月16日 上午11:24:57
	 * 
	 * @param reqData - 配置参数
	 * @return sign字符串
	 */
	public static String createRefundSign(AlipayRefundReqData reqData) {
		Map<String, String> req = new HashMap<String, String>(14);
		// 接口名称
		req.put("service", reqData.getService());

		// 合作者身份ID
		req.put("partner", reqData.getPartner());

		// 卖家用户ID
		req.put("seller_user_id", reqData.getSellerUserId());

		// 参数编码字符集
		req.put("_input_charset", reqData.getInputCharset());

		// 服务器异步通知页面路径
		req.put("notify_url", reqData.getNotifyUrl());

		// 卖家支付宝账号
		req.put("seller_email", reqData.getSellerEmail());

		// 退款请求时间
		req.put("refund_date", reqData.getRefundDate());

		// 退款批次号
		req.put("batch_no", reqData.getBatchNo());

		// 总笔数
		req.put("batch_num", reqData.getBatchNum());

		// 单笔数据集
		req.put("detail_data", reqData.getDetailData());

		return buildRequestMysign(req);
	}
	
	/**
	 * 生成支付宝请求支付签名
	 * 
	 * @author Hins
	 * @date 2015年8月31日 下午5:13:56
	 * 
	 * @param params
	 * @return
	 */
	public static String createPaySign(long payNo, AlipayCreatePayReqData data) {
		// 签约合作者身份ID
		String orderInfo = "partner=" + "\"" + TerraceUtils.INSTANCE.getAlipay().getPartner() + "\"";

		// 签约卖家支付宝账号
		orderInfo += "&seller_id=" + "\"" + TerraceUtils.INSTANCE.getAlipay().getSellerId() + "\"";

		// 商户网站唯一订单号
		orderInfo += "&out_trade_no=" + "\"" + String.valueOf(payNo) + "\"";

		// 商品名称
		orderInfo += "&subject=" + "\"" + data.getSubject() + "\"";

		// 商品详情
		orderInfo += "&body=" + "\"" + data.getBody() + "\"";

		// 商品金额
		orderInfo += "&total_fee=" + "\"" + data.getTotal_fee() + "\"";

		// 服务器异步通知页面路径
		orderInfo += "&notify_url=" + "\"" + data.getNotify_url() + "\"";

		// 服务接口名称， 固定值
		orderInfo += "&service=" + "\"" + data.getService() + "\"";

		// 支付类型， 固定值
		orderInfo += "&payment_type=\"" + data.getPayment_type() + "\"";

		// 参数编码， 固定值
		orderInfo += "&_input_charset=" + "\"" + data.get_input_charset() + "\"";

		// 设置未付款交易的超时时间
		// 默认30分钟，一旦超时，该笔交易就会自动被关闭。
		// 取值范围：1m～15d。
		// m-分钟，h-小时，d-天，1c-当天（无论交易何时创建，都在0点关闭）。
		// 该参数数值不接受小数点，如1.5h，可转换为90m。
		orderInfo += "&it_b_pay=" + "\"" + data.getIt_b_pay() + "\"";

		// 除去数组中的空值和签名参数
		// Map<String, String> sPara = AlipayCore.paraFilter(signMap);
		// 生成签名结果
		try {
			return URLEncoder.encode(RSAUtil.sign(orderInfo, TerraceUtils.INSTANCE.getAlipay().getPrivateKey()), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			logger.error("pay#service#createAlipaySign | URLEncoder出错 | e: {}", e.getMessage());
			throw new ServiceException(ResultCode.PARAMETER_ERROR, "URLEncoder出错");
		}
	}
	
	/**
	 * 根据提现参数, 生成支付宝sign
	 * 
	 * @author felix
	 * @date 2015/9/21
	 * 
	 * @param reqData - 提现参数
	 * @return sign 字符串
	 * */
	public static String createWithdrawSign(AlipayWithdrawReqData reqData) {
		Map<String, String> tmp = new HashMap<String, String>();
		// 接口名称
		tmp.put("service", reqData.getService());
		// 合作者身份ID
		tmp.put("partner", reqData.getPartner());
		// 参数编码字符集
		tmp.put("_input_charset", reqData.getInputCharset());
		// 服务器异步通知页面路径
		tmp.put("notify_url", reqData.getNotifyUrl());
		// 付款账户名
		tmp.put("account_name", reqData.getAccountName());
		// 付款详细数据
		tmp.put("detail_data", reqData.getDetailData());
		// 批量付款批次号
		tmp.put("batch_no", reqData.getBatchNo());
		// 付款总笔数
		tmp.put("batch_num", reqData.getBatchNum());
		// 付款总金额
		tmp.put("batch_fee", reqData.getBatchFee());
		// 付款账号
		tmp.put("email", reqData.getEmail());
		// 支付日期
		tmp.put("pay_date", reqData.getPayDate());

		return buildRequestMysign(tmp);
	}
	
	/**
     * 生成签名结果<br>
     * 微信和支付宝对于密钥key的处理是不同的，微信是要在签名的字符串后面加上&key=密钥，支付宝是后面直接加上&密钥<br>
     * 为了清晰点，不同平台生成生成sign的方法不同
     * 
     * @param sPara 要签名的数组
     * @return 签名结果字符串
     */
	private static String buildRequestMysign(Map<String, String> sPara) {
    	String prestr = CoreUtils.createAlipayLinkString(sPara); //把数组所有元素，按照“参数=参数值”的模式用“&”字符拼接成字符串
        String mysign = MD5.sign(prestr, TerraceUtils.INSTANCE.getAlipayRefund().getKey(), TerraceUtils.INSTANCE.getAlipay().getCharset());
        return mysign;
    }

}
