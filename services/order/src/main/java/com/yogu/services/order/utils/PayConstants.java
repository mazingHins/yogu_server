package com.yogu.services.order.utils;

/**
 * pay有关的常量 <br>
 * 
 * @author Hins
 * @date 2015年10月21日 下午6:25:59
 */
public class PayConstants {

	/** configp配置表-conffig_key的值：支付宝支付接口名称 */
	public static final String CONFIG_KEY_ALIPAY_PAY_SERVICE = "payService";

	/** configp配置表-conffig_key的值：支付宝商家私钥 */
	public static final String CONFIG_KEY_ALIPAY_PRIVATE_KEY = "privateKey";
	
	/** configp配置表-conffig_key的值：支付宝商家公钥*/
	public static final String CONFIG_KEY_ALIPAY_PUBLIC_KEY = "publicKey";
	
	/** configp配置表-conffig_key的值：支付宝调用参数编码 */
	public static final String CONFIG_KEY_ALIPAY_CHARSET = "charset";
	
	/** configp配置表-conffig_key的值：支付宝合作者id */
	public static final String CONFIG_KEY_ALIPAY_PARTENER = "partener";
	
	/** configp配置表-conffig_key的值：支付宝支付接口签名方式 */
	public static final String CONFIG_KEY_ALIPAY_PAY_SIGN_TYPE = "paySignType";
	
	/** configp配置表-conffig_key的值：支付宝支付接口回调地址 */
	public static final String CONFIG_KEY_ALIPAY_PAY_NOTIFY_URL = "payNotifyUrl";
	
	/** configp配置表-conffig_key的值：卖家支付宝账号 */
	public static final String CONFIG_KEY_ALIPAY_SALLER_ID = "sellerId";
	
	/** configp配置表-conffig_key的值：支付宝支付-未付款交易的超时时间 */
	public static final String CONFIG_KEY_ALIPAY_PAY_ITB_PAY = "payItBPay";
	
	
	/**************************************新版支付宝支付配置begin******************************************************/
	/** configp配置表-conffig_key的值：新版支付宝支付-appId */
	public static final String CONFIG_KEY_ALIPAY2_APPID = "appId";
	
	/** configp配置表-conffig_key的值：新版支付宝支付-请求编码 */
	public static final String CONFIG_KEY_ALIPAY2_CHARSET = "charset";
	
	/** configp配置表-conffig_key的值：新版支付宝支付-加密方式 */
	public static final String CONFIG_KEY_ALIPAY2_SIGN_TYPE = "signType";
	
	/** configp配置表-conffig_key的值：新版支付宝支付-版本 */
	public static final String CONFIG_KEY_ALIPAY2_VERSION = "version";
	
	/** configp配置表-conffig_key的值：新版支付宝支付-支付成功回调接口 */
	public static final String CONFIG_KEY_ALIPAY2_NOTIFY_URL = "notifyUrl";
	
	/** configp配置表-conffig_key的值：新版支付宝支付-支付成功回调接口 */
	public static final String CONFIG_KEY_ALIPAY2_RETURN_URL = "returnUrl";
	
	/** configp配置表-conffig_key的值：新版支付宝支付-卖家支付宝账号 */
	public static final String CONFIG_KEY_ALIPAY2_SELLERID = "sellerId";
	
	/** configp配置表-conffig_key的值：新版支付宝支付-未付款交易的超时时间 */
	public static final String CONFIG_KEY_ALIPAY2_TIMEOUT_EXPRESS = "timeoutExpress";
	
	/** configp配置表-conffig_key的值：新版支付宝商家私钥 */
	public static final String CONFIG_KEY_ALIPAY2_PRIVATE_KEY = "privateKey";
	
	/** configp配置表-conffig_key的值：新版支付宝商家公钥*/
	public static final String CONFIG_KEY_ALIPAY2_PUBLIC_KEY = "publicKey";
	/**************************************新版支付宝支付配置end******************************************************/
	
	
	
	/** configp配置表-conffig_key的值：支付宝退款接口名称 */
	public static final String CONFIG_KEY_ALIPAY_REFUND_SERVICE = "refundService";
	
	/** configp配置表-conffig_key的值：支付宝安全校验码 */
	public static final String CONFIG_KEY_ALIPAY_KEY = "key";
	
	/** configp配置表-conffig_key的值：支付宝退款接口签名方式 */
	public static final String CONFIG_KEY_ALIPAY_REFUND_SIGN_TYPE = "refundSignType";
	
	/** configp配置表-conffig_key的值：支付宝退款接口回调地址 */
	public static final String CONFIG_KEY_ALIPAY_REFUND_NOTIFY_URL = "refundNotifyUrl";
	
	/** configp配置表-conffig_key的值：支付宝提现接口名称 */
	public static final String CONFIG_KEY_ALIPAY_WITHDRAW_SERVICE = "withdrawService";
	
	/** configp配置表-conffig_key的值：支付宝提现接口签名方式 */
	public static final String CONFIG_KEY_ALIPAY_WITHDRAW_SIGN_TYPE = "withdrawSignType";
	
	/** configp配置表-conffig_key的值：支付宝提现接口回调地址 */
	public static final String CONFIG_KEY_ALIPAY_WITHDRAW_NOTIFY_URL = "withdrawNotifyUrl";
	
	/** configp配置表-conffig_key的值：卖家支付宝账号名 */
	public static final String CONFIG_KEY_ALIPAY_ACCOUNT_NAME = "accountName";
	
	/** configp配置表-conffig_key的值：用户确认订单到商家可提现的缓冲时间 */
	public static final String CONFIG_KEY_WITHDRAWABLE_BUFFER = "withdrawableBufferTime";
	
	/** configp配置表-conffig_key的值：待入账账户金钱转到可提现账户的定时任务处理的流水时间范围 */
	public static final String CONFIG_KEY_WITHDRAWABLE_RANGE = "withdrawableRangeTime";
	
	/** callback时 order相应pay的成功结果 */
	public static final String ORDER_NOTIFY_ACK_SUCCESS = "success";
	
	/** callback时 order相应pay的重复处理结果 */
	public static final String ORDER_NOTIFY_ACK_HAS_DEAL = "has_deal";
	
	/** callback时 order相应pay的失败结果 */
	public static final String ORDER_NOIFY_ACK_FAIL = "fail";
	
	/** 最小提现金额配置key */
	public static final String WITHDRAW_MIN_VALUE = "withdraw_min_value";
	
	/** configp配置表-conffig_key的值：微信支付-prod版密钥 */
	public static final String CONFIG_KEY_WECHAT_PROD_KEY = "prodPayKey";
	
	/** configp配置表-conffig_key的值：微信支付-prod版公众账号ID */
	public static final String CONFIG_KEY_WECHAT_PROD_APPID = "prodAppid";
	
	/** configp配置表-conffig_key的值：微信支付-prod版商户号 */
	public static final String CONFIG_KEY_WECHAT_PROD_MCHID = "prodMchId";
	
	/** configp配置表-conffig_key的值：微信支付-org版密钥 */
	public static final String CONFIG_KEY_WECHAT_ORG_KEY = "orgPayKey";
	
	/** configp配置表-conffig_key的值：微信支付-org版公众账号ID */
	public static final String CONFIG_KEY_WECHAT_ORG_APPID = "orgAppid";
	
	/** configp配置表-conffig_key的值：微信支付-org版商户号 */
	public static final String CONFIG_KEY_WECHAT_ORG_MCHID = "orgMchId";
	
	/** configp配置表-conffig_key的值：微信支付-扩展字段 */
	public static final String CONFIG_KEY_WECHAT_PACKAGE = "packageStr";
	
	/** configp配置表-conffig_key的值：微信支付-回调地址 */
	public static final String CONFIG_KEY_WECHAT_PAY_NOTIFY = "payNotify";
	
	/** configp配置表-conffig_key的值：微信支付-公众号版appID */
	public static final String CONFIG_KEY_WECHAT_MP_APPID = "mpAppid";
	
	/** configp配置表-conffig_key的值：微信支付-公众号版商户号 */
	public static final String CONFIG_KEY_WECHAT_MP_MCHID = "mpMchId";
	
	/** configp配置表-conffig_key的值：微信支付-公众号版服务商商户号 */
	public static final String CONFIG_KEY_WECHAT_SERVICE_MP_MCHID = "serviceMpMchId";
	
	/** configp配置表-conffig_key的值：微信支付-公众号版支付api密钥 */
	public static final String CONFIG_KEY_WECHAT_MP_KEY = "mpPayKey";
	
	/** configp配置表-conffig_key的值：微信支付-公众号版服务商支付api密钥 */
	public static final String CONFIG_KEY_WECHAT_SERVICE_MP_KEY = "serviceMpPayKey";
	
	/** configp配置表-conffig_key的值：微信支付-4.0版本新的app org版密钥 */
	public static final String CONFIG_KEY_WECHAT_ORG_MAZING_KEY = "orgMazingKey";
	
	/** configp配置表-conffig_key的值：微信支付-4.0版本新的app org版公众账号ID */
	public static final String CONFIG_KEY_WECHAT_ORG_MAZING_APPID = "orgMazingAppid";
	
	/** configp配置表-conffig_key的值：微信支付-4.0版本新的app org版商户号 */
	public static final String CONFIG_KEY_WECHAT_ORG_MAZING_MCHID = "orgMazingMchId";
	
	/** configp配置表-conffig_key的值：微信支付-4.0版本新的app prod版密钥 */
	public static final String CONFIG_KEY_WECHAT_PROD_MAZING_KEY = "prodMazingKey";
	
	/** configp配置表-conffig_key的值：微信支付-4.0版本新的app prod版公众账号ID */
	public static final String CONFIG_KEY_WECHAT_PROD_MAZING_APPID = "prodMazingAppid";
	
	/** configp配置表-conffig_key的值：微信支付-4.0版本新的app prod版商户号 */
	public static final String CONFIG_KEY_WECHAT_PROD_MAZING_MCHID = "prodMazingMchId";
	
	/**
	 * 微信小程序支付appId
	 */
	public static final String CONFIG_KEY_WECHAT_MPAPP_APPID = "mpappAppId";
	
	/**
	 * 微信小程序支付商户号
	 */
	public static final String CONFIG_KEY_WECHAT_MPAPP_MCHID = "mpappMchId";
	
	/**
	 * 微信小程序支付密钥
	 */
	public static final String CONFIG_KEY_WECHAT_MPAPP_KEY = "mpappKey";
	
	
	
}
