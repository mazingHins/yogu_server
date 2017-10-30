package com.yogu.services.order.pay.service.impl;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Date;
import java.util.SortedMap;
import java.util.TreeMap;

import javax.inject.Inject;
import javax.inject.Named;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.yogu.alarm.AlarmSender;
import com.yogu.cfg.GlobalSetting;
import com.yogu.commons.utils.OSUtil;
import com.yogu.commons.utils.StringUtils;
import com.yogu.commons.utils.VOUtil;
import com.yogu.core.SysType;
import com.yogu.core.constant.PayResultCode;
import com.yogu.core.enums.BooleanConstants;
import com.yogu.core.enums.CurrencyType;
import com.yogu.core.enums.pay.NotifyEnum;
import com.yogu.core.enums.pay.NotifyEnum.AlipayTradeStatus;
import com.yogu.core.enums.pay.NotifyEnum.PayRecordStatus;
import com.yogu.core.enums.pay.PayMode;
import com.yogu.core.enums.pay.PayStatus;
import com.yogu.core.enums.pay.PayType;
import com.yogu.core.utils.ComputeUtils;
import com.yogu.core.web.OrderErrorCode;
import com.yogu.core.web.ParameterUtil;
import com.yogu.core.web.PayErrorCode;
import com.yogu.core.web.ResultCode;
import com.yogu.core.web.exception.ServiceException;
import com.yogu.language.PayMessages;
import com.yogu.remote.config.id.IdGenRemoteService;
import com.yogu.remote.user.provider.UserRemoteService;
import com.yogu.services.order.base.service.OrderPayService;
import com.yogu.services.order.pay.dto.AlipayPayNotify;
import com.yogu.services.order.pay.dto.PayRecord;
import com.yogu.services.order.pay.dto.WechatPayNotify;
import com.yogu.services.order.pay.dto.WechatPayRecord;
import com.yogu.services.order.pay.service.AlipayPayNotifyService;
import com.yogu.services.order.pay.service.PayRecordService;
import com.yogu.services.order.pay.service.PayService;
import com.yogu.services.order.pay.service.WechatPayNotifyService;
import com.yogu.services.order.pay.service.WechatPayRecordService;
import com.yogu.services.order.pay.service.params.PayReqParams;
import com.yogu.services.order.pay.service.params.UpdataPayRecordMode;
import com.yogu.services.order.resource.vo.pay.AlipayPayVO;
import com.yogu.services.order.resource.vo.pay.PayVO;
import com.yogu.services.order.resource.vo.pay.WechatPayVO;
import com.yogu.services.order.utils.GenerateUtils;
import com.yogu.services.order.utils.TerraceUtils;
import com.yogu.services.order.utils.WechatApiReqHandler;
import com.yogu.services.order.utils.protocol.WechatUnifiedOrderResData;
import com.yogu.services.order.utils.sign.common.RSAUtil;
import com.yogu.services.order.utils.sign.wechat.WechatNotifyUtils;
import com.yogu.services.order.utils.sign.wechat.WechatSubmitUtils;

/**
 * 
 * @Description: PayService的实现类
 * @author Hins
 * @date 2015年8月6日 上午11:27:10
 */
@Named
public class PayServiceImpl implements PayService {

	private static final Logger logger = LoggerFactory.getLogger(PayServiceImpl.class);

	@Inject
	private IdGenRemoteService idGenRemoteService;

	@Inject
	private PayRecordService payRecordService;

	@Inject
	private AlipayPayNotifyService alipayPayNotifyService;

	@Inject
	private WechatPayRecordService wechatPayRecordService;

	@Inject
	private WechatPayNotifyService wechatPayNotifyService;
	
	@Inject
	private OrderPayService orderPayService;

	@Override
	public PayVO createPay(PayReqParams params) {
		/*
		 * 在开始设计的时候，就完全相信order域请求发送过来的数据!
		 * 可能情况：一张订单请求2接口，假设返回了数据A,B。会因为网络问题，B先返回给order域，A后返回。
		 * 为了防止order域记录了B的支付数据，但是下发A的数据给客户端，所以同一张订单多次请求接口，都只生成一张支付记录。
		 * 
		 */

		// 1. 验证请求参数
		validateGetPay(params);

		// 2. 查看order域请求时对应的订单号是否存在支付记录
		// 2016-04-12 将处理的逻辑整理到方法 processPayRequest 当中
		PayRecord record = processPayRequest(params);

		// 4. 初始化支付SDK参数信息
		return initAppPay(record.getPayId(), record.getPayNo(), params);
	}

	/**
	 * 将原有支付请求的处理逻辑整合在此, 包括以下内容 查看order域请求时对应的订单号是否存在支付记录, 若不存在支付记录，则新增
	 * 
	 * @param params 请求支付参数
	 * @return
	 */
	private PayRecord processPayRequest(PayReqParams params) {
		// 2. 查看order域请求时对应的订单号是否存在支付记录
		PayRecord record = payRecordService.getByOrderNo(params.getOrderNo());

		// 3. 若不存在支付记录，则新增
		if (record == null) {
			record = initPayRecord(params);
			payRecordService.save(record);
		} else {
			// 2016-10-08 modify by hins 内容：更换了支付方式/实付金额/是否使用了优惠券，都修改record数据
			if (record.getPayMode() != params.getPayMode() || record.getTotalFee() != params.getTotalFee()) {
				UpdataPayRecordMode modeParams = VOUtil.from(params, UpdataPayRecordMode.class);
				modeParams.setNewPayMode(params.getPayMode());
				payRecordService.changePayMode(record.getPayNo(), modeParams);
			}
			logger.info("pay#service#createPay | 同一张订单多次调用 | orderNo: {}, payNo: {}", params.getOrderNo(), record.getPayNo());
		}
		// 2016-10-08 modify by hins end. 备注：创建支付方式的方法，不涉及mz_pay_express_record，这个表只在开始配送等阶段才会操作

		return record;
	}


	/**
	 * 初始化待保存的支付记录信息
	 * 
	 * @author Hins
	 * @date 2015年12月28日 下午5:31:04
	 * 
	 * @param params - 获取支付信息service请求参数
	 * @return 待保存的支付记录对象
	 */
	private PayRecord initPayRecord(PayReqParams params) {
		Date now = new Date();
		long payId = idGenRemoteService.getNextPayId();
		long payNo = GenerateUtils.nextPayNo(params.getBuyerUid(), params.getOrderNo());
		PayRecord record = VOUtil.from(params, PayRecord.class);
		record.setPayId(payId);
		record.setPayNo(payNo);
		record.setOrderNo(params.getOrderNo());
		record.setPayStatus(params.getTotalFee() == 0 ? PayRecordStatus.SUCCESS_PAY.getValue() : PayRecordStatus.NOT_PAY.getValue());
		record.setCreateTime(now);
		record.setUpdateTime(now);
		record.setUseCoupon(params.getCouponFee() > 0 ? BooleanConstants.TRUE : BooleanConstants.FALSE);
		return record;
	}


	@Override
	public void alipayNotify(AlipayPayNotify notify) {
		// 1. 验证参数支付记录payNo是否存在，回调的交易状态是否合法。
		// 1.1 若该支付记录支付状态是已支付，则不做处理返回（防止当订单已支付后，支付宝继续传入关闭交易等状态）
		// 2. 新增或修改第三方回调记录数据
		// 2.1 支付宝回调的交易状态是待支付，若不存在第三方回调记录数据，则新增，若存在则不做处理返回（可能因为网络传输原因，等待支付回调比支付成功回调更晚到达服务器）
		// 2.2 若支付宝回调的交易状态是交易取消，则不做处理返回
		// 2.3 若支付宝回调的交易状态是已支付，则判断是否存在第三方回调记录数据，存在则修改，不存在则新增
		PayRecord record = payRecordService.getByPayNo(notify.getPayNo());
		if (record == null) {
			logger.error("pay#service#alipayNotify | 支付记录不存在 | payNo: {}", notify.getPayNo());
			throw new ServiceException(PayErrorCode.PAY_RECORD_NOT_EXIST, PayMessages.STOREBALANCE_LOGBALANCEDETAILS_PAYRECORD_NOTEXIST);
		}

		logger.info("pay#service#alipayNotify | 支付宝回调处理start |  payNo: {}, orderNo: {}", record.getPayNo(), record.getOrderNo());

		// 验证订单金额跟回调金额是否一致
		if (notify.getTotalFee() != record.getTotalFee()) {
			logger.error("pay#service#alipayNotify | 订单金额跟回调金额不一致 | payTotal: {}, notifyTotal: {}", record.getTotalFee(), notify.getTotalFee());
			throw new ServiceException(ResultCode.PARAMETER_ERROR, PayMessages.PAY_ALIPAYNOTIFY_TOTALFEE_ERROR);
		}

		// 验证交易状态
		AlipayTradeStatus status = AlipayTradeStatus.valueOf(notify.getTradeStatus());
		if (status == null) {
			logger.error("pay#service#alipayNotify | 交易状态错误 | payNo: {}, tradeStatus: {}", notify.getPayNo(), notify.getTradeStatus());
			throw new ServiceException(PayErrorCode.PAY_RECORD_NOT_EXIST, PayMessages.PAY_ALIPAYNOTIFY_ALIPAYTRADESTATUS_ILLEGAL);
		}

		// 支付记录数据中支付状态为成功,防止支付宝继续传入关闭交易等状态
		if (record.getPayStatus() == PayRecordStatus.SUCCESS_PAY.getValue()) {
			logger.warn("pay#servic#alipayNotify | 支付宝回调重复调用 | payNo: {}", notify.getPayNo());
			if (record.getPayMode() != PayMode.ALIPAY.getValue()) {
				AlarmSender.sendWarn(StringUtils.trimToEmpty(GlobalSetting.getProjenv()), "[" + OSUtil.getLocalIp() + "] 系统异常：", "支付记录状态=已支付，但支付记录的类型不是支付宝 | payNo: " + record.getPayNo() + "，tradeStatus: " + notify.getTradeStatus());
			}
			return;
		}

		notify.setPayId(record.getPayId());
		notify.setCurrencyType(CurrencyType.CNY.getValue());
		notify.setRemark("");

		boolean tradeStatus = true;// 是否支付成功(回调内部第三方支付结果)
		long notifyId = 0; // 回调记录ID
		switch (status.getValue()) {
		case 1: // 交易状态是待支付
			try {
				notifyId = alipayPayNotifyService.createNotify(notify);
			} catch (ServiceException e) {
				if (e.getCode() == PayErrorCode.PAY_RECORD_IS_EXIST) {
					// 因为网络传输原因，等待支付回调比支付成功回调更晚到达服务器
					// 则不做任何处理
					logger.info("pay#PayService#alipayNotify | alipay notify record is exist | payNo: {}", notify.getPayNo());
					return;
				}
				throw e;
			}
			return;
		case 2: // 交易状态为已取消付款
			tradeStatus = false;
			payRecordService.payFail(notify.getPayNo(), notifyId);
			break;
		case 3: // 交易成功，且可对该交易做操作，如：多级分润、退款等。
			notifyId = alipayPayNotifyService.successNotify(notify);
			payRecordService.paySuccess(notify.getPayNo(), notifyId);
			break;
		case 4: // 交易成功且结束，即不可再做任何操作。
			notifyId = alipayPayNotifyService.successNotify(notify);
			payRecordService.paySuccess(notify.getPayNo(), notifyId);
			break;
		}

		// 回调内部第三方
		callBack(record.getPayId(), record.getPayNo(), record.getOrderNo(), tradeStatus);

		logger.info("pay#service#alipayNotify | 支付宝回调处理成功 |  payNo: {}, orderNo: {}", record.getPayNo(), record.getOrderNo());
	}

	/**
	 * 初始化调用SDK支付信息
	 * 
	 * @param payNo - 支付编号
	 * @param params - getPay参数
	 * @return 调用SDK支付信息
	 */
	private PayVO initAppPay(long payId, long payNo, PayReqParams params) {
		PayVO result = new PayVO();
		result.setTotalFee(params.getTotalFee());
		result.setPayNo(payNo);
		if (params.getTotalFee() == 0) {
			logger.info("pay#service#getPay | 实际支付金额=0，不需要获取支付SDK | payNo: {}, orderNo: {}", payNo, params.getOrderNo());
			result.setPayCode(PayResultCode.PAY_SUCCESS);
			return result;
		}

		if (params.getPayMode() == PayMode.ALIPAY.getValue()) {// 支付宝SDK
			AlipayPayVO alipay = initAppAliPay(payNo, params);
			result.setPayMode(params.getPayMode());
			result.setAlipay(alipay);
			result.setPayCode(PayResultCode.PAY_WAITING_BY_ALIPAY);
		} else if (params.getPayMode() == PayMode.WECHAT.getValue()) {// 微信接口
			WechatPayVO wechat = initAppWechatPay(payId, payNo, params);
			result.setPayMode(params.getPayMode());
			result.setWechat(wechat);
			result.setPayCode(PayResultCode.PAY_WAITING_BY_WECHAT);
		} else {
			logger.error("pay#service#getPay | payMode error | payNo: {}, payMode: {}", payNo, params.getPayMode());
			throw new ServiceException(OrderErrorCode.PAYMODE_NOT_EXIST, PayMessages.LOCALBALANCEAPI_GETPAY_PARAMS_PAYMODE_ILLEGAL);
		}
		logger.info("pay#service#getPay | 返回pay信息成功 | payNo: {}, orderNo: {}", payNo, params.getOrderNo());
		return result;
	}

	/**
	 * 初始化支付宝支付SDK参数VO
	 * 
	 * @author Hins
	 * @date 2016年1月30日 下午5:12:55
	 * 
	 * @param payNo - 支付编号
	 * @param params - getPay参数
	 * @return
	 */
	private AlipayPayVO initAppAliPay(long payNo, PayReqParams params) {
		AlipayPayVO alipay = new AlipayPayVO();
		// 服务接口名称， 固定值
		alipay.setService(TerraceUtils.INSTANCE.getAlipay().getService());
		// 签约合作者身份ID
		alipay.setPartner(TerraceUtils.INSTANCE.getAlipay().getPartner());
		// 参数编码， 固定值
		alipay.setInputCharset(TerraceUtils.INSTANCE.getAlipay().getCharset());
		// 签名方式， 固定值
		alipay.setSignType(TerraceUtils.INSTANCE.getAlipay().getSignType());
		// 服务器异步通知页面路径
		alipay.setNotifyUrl(TerraceUtils.INSTANCE.getAlipay().getNotifyUrl());
		// 支付类型， 固定值
		alipay.setPaymentType("1");
		// 签约卖家支付宝账号
		alipay.setSellerId(TerraceUtils.INSTANCE.getAlipay().getSellerId());
		// 商品详情
		alipay.setBody(params.getBody());
		// 商品名称
		alipay.setSubject(params.getSubject());
		// 签名
		alipay.setSign(createAlipaySign(payNo, params));
		// 设置未付款交易的超时时间
		// 默认30分钟，一旦超时，该笔交易就会自动被关闭。
		// 取值范围：1m～15d。
		// m-分钟，h-小时，d-天，1c-当天（无论交易何时创建，都在0点关闭）。
		// 该参数数值不接受小数点，如1.5h，可转换为90m。
		alipay.setItBPay(TerraceUtils.INSTANCE.getAlipay().getItBPay());

		return alipay;
	}


	/**
	 * 初始化微信支付SDK参数VO<br>
	 * 如果此订单已经请求过微信“统一下单”api，则不在请求，直接装在对应的参数VO<br>
	 * 如果此订单没请求过微信“统一下单”api，则请求，成功后将结果保存到微信支付请求记录表，并装载对应的参数VO，请求失败，抛出serviceException
	 * 
	 * @author Hins
	 * @date 2016年1月30日 下午5:59:29
	 * 
	 * @param payId - 支付记录id
	 * @param payNo - 支付记录编号
	 * @param params - getPay参数
	 * @return
	 */
	private WechatPayVO initAppWechatPay(long payId, long payNo, PayReqParams params) {
		WechatPayVO result = new WechatPayVO();
		// 如果当前支付已经请求过微信“统一下单”，则不在请求
		WechatPayRecord record = wechatPayRecordService.getById(payId);
		String target = params.getTarget();
		if (record != null && org.apache.commons.lang3.StringUtils.isNotBlank(record.getPrepayId())) {
			result.setPrepayId(record.getPrepayId());
			result.setAppid(TerraceUtils.INSTANCE.getWechat().getAppid());
			result.setPartnerId(TerraceUtils.INSTANCE.getWechat().getMchId());
			result.setPackageStr(TerraceUtils.INSTANCE.getWechat().getPackageStr());
			result.setTimestamp(String.valueOf(record.getTimestamp()));
			result.setNoncestr(record.getNonceStr());
		} else {
			// 首次请求微信“统一下单”api，记录返回的结果
			WechatUnifiedOrderResData data = WechatApiReqHandler.createUnified(payNo, params);
			logger.info("pay#service#initWechatPay | 微信统一下单结果 | response: {}", WechatApiReqHandler.wechatApiLog(data));
			// WechatUnifiedHandler.builder().createUnifiedorder(payNo, params);
			if (WechatNotifyUtils.valiteApiResponseResult(data)) { // 统一下单成功
				result.setPrepayId(data.getPrepay_id());
				result.setAppid(TerraceUtils.INSTANCE.getWechat().getAppid());
				result.setPartnerId(TerraceUtils.INSTANCE.getWechat().getMchId());
				result.setPackageStr(TerraceUtils.INSTANCE.getWechat().getPackageStr());
				result.setTimestamp(String.valueOf(System.currentTimeMillis() / 1000));
				result.setNoncestr(String.valueOf(System.currentTimeMillis())); 
				if (null == record) {
					record = new WechatPayRecord();
					record.setPayId(payId);
					record.setNonceStr(result.getNoncestr());
					record.setSpbillCreateIp(params.getUserIp());
					record.setPrepayId(result.getPrepayId());
					record.setMpPrepayId("");
					record.setTimestamp(Integer.valueOf(result.getTimestamp()));
					record.setCreateTime(new Date());
					wechatPayRecordService.save(record);
				} else {
					record.setPrepayId(result.getPrepayId());
					wechatPayRecordService.update(record);
				}

			} else {
				// “统一下单”api调用失败，打印原因
				logger.error("pay#service#initWechatPay | 微信“统一下单”api请求失败 | payNo: {}, return_code {}, return_msg {}, result_code {}, err_code {}, err_code_des {}", payNo, data.getReturn_code(), data.getReturn_msg(), data.getResult_code(), data.getErr_code(), data.getErr_code_des());

				throw new ServiceException(ResultCode.PARAMETER_ERROR, PayMessages.PAY_CREATEWECHATUNIFIEDORDERRESDATA_ERROR);
			}
		}

		// 转载返回的参数sign，用于客户端请求“调起支付”api的参数
		SortedMap<String, String> parameters = new TreeMap<String, String>();
		parameters.put("appid", result.getAppid());
		parameters.put("partnerid", result.getPartnerId());
		parameters.put("prepayid", result.getPrepayId());
		parameters.put("package", result.getPackageStr());
		parameters.put("noncestr", result.getNoncestr());
		parameters.put("timestamp", result.getTimestamp());

		String sign = WechatSubmitUtils.createSign(parameters, TerraceUtils.INSTANCE.getWechat().getKey(target, params.getSysType()));
		result.setSign(sign);
		return result;
	}

	/**
	 * 生成支付宝签名
	 * 
	 * @author Hins
	 * @date 2015年8月31日 下午5:13:56
	 * 
	 * @param params
	 * @return
	 */
	private String createAlipaySign(long payNo, PayReqParams params) {
		// 签约合作者身份ID
		String orderInfo = "partner=" + "\"" + TerraceUtils.INSTANCE.getAlipay().getPartner() + "\"";

		// 签约卖家支付宝账号
		orderInfo += "&seller_id=" + "\"" + TerraceUtils.INSTANCE.getAlipay().getSellerId() + "\"";

		// 商户网站唯一订单号
		orderInfo += "&out_trade_no=" + "\"" + String.valueOf(payNo) + "\"";

		// 商品名称
		orderInfo += "&subject=" + "\"" + params.getSubject() + "\"";

		// 商品详情
		orderInfo += "&body=" + "\"" + params.getBody() + "\"";

		// 商品金额
		orderInfo += "&total_fee=" + "\"" + ComputeUtils.fenToYuan(params.getTotalFee()) + "\"";

		// 服务器异步通知页面路径
		orderInfo += "&notify_url=" + "\"" + TerraceUtils.INSTANCE.getAlipay().getNotifyUrl() + "\"";

		// 服务接口名称， 固定值
		orderInfo += "&service=" + "\"" + TerraceUtils.INSTANCE.getAlipay().getService() + "\"";

		// 支付类型， 固定值
		orderInfo += "&payment_type=\"1\"";

		// 参数编码， 固定值
		orderInfo += "&_input_charset=" + "\"" + TerraceUtils.INSTANCE.getAlipay().getCharset() + "\"";

		// 设置未付款交易的超时时间
		// 默认30分钟，一旦超时，该笔交易就会自动被关闭。
		// 取值范围：1m～15d。
		// m-分钟，h-小时，d-天，1c-当天（无论交易何时创建，都在0点关闭）。
		// 该参数数值不接受小数点，如1.5h，可转换为90m。
		orderInfo += "&it_b_pay=" + "\"" + TerraceUtils.INSTANCE.getAlipay().getItBPay() + "\"";

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
	 * 验证getPay
	 * 
	 * @author Hins
	 * @date 2015年8月31日 上午11:58:09
	 * 
	 * @param params - 支付请求参数
	 */
	private static void validateGetPay(PayReqParams params) {
		ParameterUtil.assertNotNull(params, PayMessages.LOCALBALANCEAPI_GETPAY_PARAMS_EMPTY());
		PayMode mode = PayMode.valueOf(params.getPayMode());
		ParameterUtil.assertNotNull(mode, PayMessages.LOCALBALANCEAPI_GETPAY_PARAMS_PAYMODE_ILLEGAL());

		short sysType = params.getSysType();
		SysType sys = SysType.valueOf(sysType);
		ParameterUtil.assertNotNull(sys, PayMessages.PAY_CREATEPAY_SYSTYPE_EMPTY());
		// 2016-04-01 modify by hins 内容：ios只有微信支付，才需要需要判断版本号
		if (sys.getValue() == SysType.IOS.getValue() && mode.getValue() == PayMode.WECHAT.getValue()) {
			ParameterUtil.assertNotBlank(params.getTarget(), PayMessages.PAY_CREATEPARAMS_TARGET_EMPTY());
		}

		CurrencyType type = CurrencyType.valueOf(params.getCurrencyType());
		ParameterUtil.assertNotNull(type, PayMessages.LOCALBALANCEAPI_GETPAY_PARAMS_CURRENCYTYPE_ILLEGAL());
		ParameterUtil.assertGreaterThanOrEqual(params.getOrderNo(), 1, PayMessages.LOCALBALANCEAPI_GETPAY_PARAMS_TRADENO_ILLEGAL());
		ParameterUtil.assertGreaterThanOrEqual(params.getTotalFee(), 0, PayMessages.LOCALBALANCEAPI_GETPAY_PARAMS_TOTALFEE_ILLEGAL());
		ParameterUtil.assertGreaterThanOrEqual(params.getBuyerUid(), 1, PayMessages.LOCALBALANCEAPI_GETPARAMS_BUYERID_ILLEGAL());
		ParameterUtil.assertGreaterThanOrEqual(params.getCouponFee(), 0, PayMessages.LOCALBALANCEAPI_GETPARAMS_COUPONFEE_ILLEGAL());

		ParameterUtil.assertNotBlank(params.getUserIp(), PayMessages.LOCALBALANCEAPI_GETPARAMS_USERIP_EMPTY());
		ParameterUtil.assertMaxLength(params.getUserIp(), 16, PayMessages.LOCALBALANCEAPI_GETPARAMS_USERIP_LENGTH_MAX());
	}


	@Override
	public void wechatNotify(WechatPayNotify notify) {
		logger.info("pay#service#wechatNotify | 微信回调处理start |  payNo: {}", notify.getPayNo());

		PayRecord record = payRecordService.getByPayNo(notify.getPayNo());
		if (record == null) {
			logger.error("pay#service#wechatNotify | 支付记录不存在 | payNo: {}", notify.getPayNo());
			throw new ServiceException(PayErrorCode.PAY_RECORD_NOT_EXIST, PayMessages.STOREBALANCE_LOGBALANCEDETAILS_PAYRECORD_NOTEXIST());
		}

		// 验证订单金额跟回调金额是否一致
		if (notify.getTotalFee() != record.getTotalFee()) {
			logger.error("pay#service#wechatNotify | 订单金额跟回调金额不一致 | payTotal: {}, notifyTotal: {}", record.getTotalFee(), notify.getTotalFee());
			throw new ServiceException(ResultCode.PARAMETER_ERROR, PayMessages.PAY_ALIPAYNOTIFY_TOTALFEE_ERROR());
		}
		if (record.getPayStatus() == PayRecordStatus.SUCCESS_PAY.getValue()) {
			logger.info("pay#service#wechatNotify | 支付记录重复处理 | payNo: {}", record.getPayNo());
			if (record.getPayMode() != PayMode.WECHAT.getValue()) {
				AlarmSender.sendWarn(StringUtils.trimToEmpty(GlobalSetting.getProjenv()), "[" + OSUtil.getLocalIp() + "] 系统异常：", "支付记录状态=已支付，支付记录类型不是微信 | payNo: " + record.getPayNo());
			}
			return;
		}

		notify.setPayId(record.getPayId());
		boolean tradeStatus = true;// 是否支付成功(回调内部第三方支付结果)
		if (notify.getResultCode() == BooleanConstants.TRUE) { // 支付回调业务结果：成功
			logger.info("pay#service#wechatNotify | 微信支付回调业务代码成功 | payNo: {}", record.getPayNo());
			notify.setStatus(NotifyEnum.WechatPayNotifyStatus.SUCCESS_PAY.getValue());
			long notifyId = wechatPayNotifyService.createNotify(notify);
			payRecordService.paySuccess(notify.getPayNo(), notifyId);
		} else {
			logger.error("pay#service#wechatNotify | 微信支付回调业务代码错误 | payNo: {}, err_code: {}, err_code_des: {}", record.getPayNo(), notify.getErrCode(), notify.getErrCodeDes());
			tradeStatus = false;
			notify.setStatus(NotifyEnum.WechatPayNotifyStatus.FAIL_PAY.getValue());
			long notifyId = wechatPayNotifyService.createNotify(notify);
			payRecordService.payFail(notify.getPayNo(), notifyId);
		}

		// 回调内部第三方
		callBack(record.getPayId(), record.getPayNo(), record.getOrderNo(), tradeStatus);

	}

	/**
	 * pay域回调支付接口给order域。 若回调获得的相应结果非success或者出现异常，则往回调任务表新增记录，等待定时任务回调。
	 * 
	 * @author Hins
	 * @date 2015年9月23日 上午11:42:35
	 * 
	 * @param payId - 支付ID
	 * @param payNo - 支付编号
	 * @param insideTradeNo - 内部订单号
	 * @param tradeFlag - 支付是否成功
	 * @param notifyUrl - 回调order域的URL
	 */
	private void callBack(long payId, long payNo, long orderNo, boolean tradeFlag) {
		orderPayService.payNotify(orderNo, payNo, tradeFlag ? PayStatus.TRADE_SUCCESS.getValue() : PayStatus.TRADE_FAIL.getValue(), StringUtils.EMPTY);
		logger.info("pay#service#callback | 回调order结果 | success: {}");
	}

}
