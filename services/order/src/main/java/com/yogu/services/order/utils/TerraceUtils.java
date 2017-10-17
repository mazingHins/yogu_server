package com.yogu.services.order.utils;

import java.util.HashMap;
import java.util.Map;

import com.yogu.CommonConstants;
import com.yogu.TargetConstants;
import com.yogu.commons.utils.StringUtils;
import com.yogu.commons.utils.cfg.DesPropertiesEncoder;
import com.yogu.core.SysType;
import com.yogu.core.remote.config.ConfigGroupConstants;
import com.yogu.core.remote.config.ConfigRemoteService;

/**
 * 支付平台相关配置
 * 
 * @author Hins
 * @date 2015年8月29日 上午10:56:06
 */
public enum TerraceUtils {

	INSTANCE;

	/** 支付宝支付配置信息 */
	AlipayPay alipay = null;

	/** 微信支付配置信息 */
	WeChatPayConfig wechat = null;

	/** 支付宝退款配置信息 */
	AlipayRefund alipayRefund = null;

	/** 支付宝提现配置信息 */
	AlipayWithdraw alipayWithdraw = null;

	/** 新版支付宝支付配置信息 */
	NewAliPay newAlipay = null;

	TerraceUtils() {
		/*
		 * 如果config配置有修改，推荐这个枚举类新增一个功能，重新生成支付宝、微信相关的配置
		 */

		// 从缓存获取
		Map<String, String> alipayMap = ConfigRemoteService.getConfigMap(ConfigGroupConstants.ALIPAY);
		// 解密
		DesPropertiesEncoder ed = new DesPropertiesEncoder();
		for (String key : alipayMap.keySet()) {
			String value = alipayMap.get(key);
			alipayMap.put(key, ed.decode(value));
		}

		// 从缓存获取
		Map<String, String> wechatMap = ConfigRemoteService.getConfigMap(ConfigGroupConstants.WEIXIN_PAY);
		// 解密
		for (String key : wechatMap.keySet()) {
			String value = wechatMap.get(key);
			wechatMap.put(key, ed.decode(value));
		}

		// 从缓存获取
		Map<String, String> newAlipayMap = ConfigRemoteService.getConfigMap(ConfigGroupConstants.ALIPAY2);
		// 解密
		for (String key : newAlipayMap.keySet()) {
			String value = newAlipayMap.get(key);
			newAlipayMap.put(key, ed.decode(value));
		}

		// pay域的域名，根据项目环境的不同，域名也不同
		String payDomain = CommonConstants.PAY_DOMAIN_CALLBACK;

		// 从配置文件获取接口参数值

		// ------------支付宝支付接口相关配置 start-------------------

		String service = alipayMap.get(PayConstants.CONFIG_KEY_ALIPAY_PAY_SERVICE); // 支付宝支付接口名称

		String privateKey = alipayMap.get(PayConstants.CONFIG_KEY_ALIPAY_PRIVATE_KEY); // 支付宝商家私钥

		String publicKey = alipayMap.get(PayConstants.CONFIG_KEY_ALIPAY_PUBLIC_KEY); // 支付宝商家公钥

		String charset = alipayMap.get(PayConstants.CONFIG_KEY_ALIPAY_CHARSET); // 支付宝调用参数编码

		String partner = alipayMap.get(PayConstants.CONFIG_KEY_ALIPAY_PARTENER); // 支付宝合作者id

		String signType = alipayMap.get(PayConstants.CONFIG_KEY_ALIPAY_PAY_SIGN_TYPE); // 加密方式RSA

		String notifyUrl = payDomain + alipayMap.get(PayConstants.CONFIG_KEY_ALIPAY_PAY_NOTIFY_URL); // 支付宝支付回调地址

		String sellerId = alipayMap.get(PayConstants.CONFIG_KEY_ALIPAY_SALLER_ID); // 卖家支付宝账号

		String itBPay = alipayMap.get(PayConstants.CONFIG_KEY_ALIPAY_PAY_ITB_PAY); // 未付款交易的超时时间
		alipay = new AlipayPay(service, privateKey, charset, partner, signType, notifyUrl, sellerId, itBPay, publicKey);
		// ------------支付宝支付接口相关配置 end-------------------

		// ------------新版支付宝支付接口相关配置 start-------------------
		String appId = newAlipayMap.get(PayConstants.CONFIG_KEY_ALIPAY2_APPID); // 开发者id
		String newCharset = newAlipayMap.get(PayConstants.CONFIG_KEY_ALIPAY2_CHARSET); // 支付宝调用参数编码
		String newSignType = newAlipayMap.get(PayConstants.CONFIG_KEY_ALIPAY2_SIGN_TYPE); // 加密方式RSA2
		String newVersion = newAlipayMap.get(PayConstants.CONFIG_KEY_ALIPAY2_VERSION); // 加密方式RSA2
		String newNotifyUrl = payDomain + newAlipayMap.get(PayConstants.CONFIG_KEY_ALIPAY2_NOTIFY_URL); // 支付宝支付回调地址
		String returnUrl = newAlipayMap.get(PayConstants.CONFIG_KEY_ALIPAY2_RETURN_URL); // 支付宝支付回调地址
		String newSellerId = newAlipayMap.get(PayConstants.CONFIG_KEY_ALIPAY2_SELLERID); // 卖家支付宝账号
		String timeoutExpress = newAlipayMap.get(PayConstants.CONFIG_KEY_ALIPAY2_TIMEOUT_EXPRESS); // 未付款交易的超时时间
		String newPrivateKey = newAlipayMap.get(PayConstants.CONFIG_KEY_ALIPAY2_PRIVATE_KEY); // 新版支付宝商家私钥
		String newPublicKey = newAlipayMap.get(PayConstants.CONFIG_KEY_ALIPAY2_PUBLIC_KEY); // 新版支付宝商家公钥
		newAlipay = new NewAliPay(appId, newCharset, newSignType, newVersion, newNotifyUrl, returnUrl, newSellerId,
				timeoutExpress, newPrivateKey, newPublicKey);
		// ------------新版支付宝支付接口相关配置 end-------------------

		// ------------微信支付接口相关配置 start-------------------
		String prodKey = wechatMap.get(PayConstants.CONFIG_KEY_WECHAT_PROD_KEY); // prod版API密钥

		String prodAppid = wechatMap.get(PayConstants.CONFIG_KEY_WECHAT_PROD_APPID); // prod版公众账号ID

		String prodMchId = wechatMap.get(PayConstants.CONFIG_KEY_WECHAT_PROD_MCHID); // prod版商户号

		String orgKey = wechatMap.get(PayConstants.CONFIG_KEY_WECHAT_ORG_KEY); // org版API密钥

		String orgAppid = wechatMap.get(PayConstants.CONFIG_KEY_WECHAT_ORG_APPID); // org版公众账号ID

		String orgMchId = wechatMap.get(PayConstants.CONFIG_KEY_WECHAT_ORG_MCHID); // org版商户号

		String prodMazingKey = wechatMap.get(PayConstants.CONFIG_KEY_WECHAT_PROD_MAZING_KEY); // 4.0版本新的app
																								// prod版API密钥

		String prodMazingAppid = wechatMap.get(PayConstants.CONFIG_KEY_WECHAT_PROD_MAZING_APPID); // 4.0版本新的app
																									// prod版公众账号ID

		String prodMazingMchId = wechatMap.get(PayConstants.CONFIG_KEY_WECHAT_PROD_MAZING_MCHID); // 4.0版本新的app
																									// prod版商户号

		String orgMazingKey = wechatMap.get(PayConstants.CONFIG_KEY_WECHAT_ORG_MAZING_KEY); // 4.0版本新的app
																							// org版API密钥

		String orgMazingAppid = wechatMap.get(PayConstants.CONFIG_KEY_WECHAT_ORG_MAZING_APPID); // 4.0版本新的app
																								// org版公众账号ID

		String orgMMazingchId = wechatMap.get(PayConstants.CONFIG_KEY_WECHAT_ORG_MAZING_MCHID); // 4.0版本新的app
																								// org版商户号

		String packageStr = wechatMap.get(PayConstants.CONFIG_KEY_WECHAT_PACKAGE); // 扩展字段
																					// 暂填写固定值Sign=WXPay

		String mpAppid = wechatMap.get(PayConstants.CONFIG_KEY_WECHAT_MP_APPID); // 公众号版appid

		String mpMchId = wechatMap.get(PayConstants.CONFIG_KEY_WECHAT_MP_MCHID); // 公众号版商户号

		String mpKey = wechatMap.get(PayConstants.CONFIG_KEY_WECHAT_MP_KEY); // 公众号版支付api密钥

		String serviceMpMchId = wechatMap.get(PayConstants.CONFIG_KEY_WECHAT_SERVICE_MP_MCHID); // 公众号版服务商商户号

		String serviceMpKey = wechatMap.get(PayConstants.CONFIG_KEY_WECHAT_SERVICE_MP_KEY); // 公众号版服务商支付api密钥

		notifyUrl = payDomain + wechatMap.get(PayConstants.CONFIG_KEY_WECHAT_PAY_NOTIFY); // 微信回调地址

		//初始化微信小程序支付配置
		String mpappAppId = wechatMap.get(PayConstants.CONFIG_KEY_WECHAT_MPAPP_APPID); //微信小程序支付appId
		String mpappMchId = wechatMap.get(PayConstants.CONFIG_KEY_WECHAT_MPAPP_MCHID); //微信小程序支付商户号
		String mpappKey = wechatMap.get(PayConstants.CONFIG_KEY_WECHAT_MPAPP_KEY); //微信小程序支付密钥
		
		wechat = new WeChatPayConfig(prodKey, prodAppid, orgKey, orgAppid, prodMchId, orgMchId, mpAppid, mpMchId, mpKey,
				prodMazingKey, prodMazingAppid, prodMazingMchId, orgMazingKey, orgMazingAppid, orgMMazingchId,
				packageStr, notifyUrl, serviceMpMchId, serviceMpKey, mpappAppId, mpappMchId, mpappKey);
		// ------------微信支付接口相关配置 end-------------------

		// ------------支付宝退款接口相关配置 start-------------------
		service = alipayMap.get(PayConstants.CONFIG_KEY_ALIPAY_REFUND_SERVICE); // 支付宝退款接口名称

		String key = alipayMap.get(PayConstants.CONFIG_KEY_ALIPAY_KEY); // 支付宝安全校验码（key）

		signType = alipayMap.get(PayConstants.CONFIG_KEY_ALIPAY_REFUND_SIGN_TYPE); // 加密方式MD5

		notifyUrl = payDomain + alipayMap.get(PayConstants.CONFIG_KEY_ALIPAY_REFUND_NOTIFY_URL); // 支付宝退款接口回调地址
		alipayRefund = new AlipayRefund(service, key, charset, partner, signType, notifyUrl, partner, sellerId);
		// ------------支付宝退款接口相关配置 end-------------------

		// ------------支付宝提现接口相关配置 start-------------------

		service = alipayMap.get(PayConstants.CONFIG_KEY_ALIPAY_WITHDRAW_SERVICE); // 支付宝提现接口名称

		signType = alipayMap.get(PayConstants.CONFIG_KEY_ALIPAY_WITHDRAW_SIGN_TYPE); // 加密方式RSA

		notifyUrl = payDomain + alipayMap.get(PayConstants.CONFIG_KEY_ALIPAY_WITHDRAW_NOTIFY_URL); // 支付宝提现接口回调地址

		partner = alipayMap.get(PayConstants.CONFIG_KEY_ALIPAY_PARTENER); // 支付宝合作者id

		String accountName = alipayMap.get(PayConstants.CONFIG_KEY_ALIPAY_ACCOUNT_NAME); // 支付宝卖家账户名
		alipayWithdraw = new AlipayWithdraw(service, partner, charset, signType, notifyUrl, sellerId, accountName,
				privateKey, publicKey);
		// ------------支付宝提现接口相关配置 end-------------------
	}

	/** 支付宝支付配置信息(明文) */
	public AlipayPay getAlipay() {
		return alipay;
	}

	/** 微信支付配置信息 */
	public WeChatPayConfig getWechat() {
		return wechat;
	}

	/** 微信支付配置信息 */
	public AlipayRefund getAlipayRefund() {
		return alipayRefund;
	}

	/** 支付宝提现配置信息 */
	public AlipayWithdraw getAlipayWithdraw() {
		return alipayWithdraw;
	}

	/** 新版支付宝支付配置信息(明文) */
	public NewAliPay getNewAlipay() {
		return newAlipay;
	}

	/**
	 * 支付宝支付信息配置(明文)
	 */
	public static class AlipayPay {

		private String service; // 接口名称

		private String privateKey; // 支付宝商家私钥

		private String charset; // 支付宝调用参数编码

		private String partner; // 支付宝合作者id

		private String signType; // 加密方式MD5

		private String notifyUrl; // 支付宝回调地址

		private String sellerId; // 卖家支付宝账号，加密后的

		private String itBPay; // 未付款交易的超时时间

		private String publicKey; // 公钥

		public AlipayPay(String service, String privateKey, String charset, String partner, String signType,
				String notifyUrl, String sellerId, String itBPay, String publicKey) {
			this.service = service;
			this.privateKey = privateKey;
			this.partner = partner;
			this.charset = charset;
			this.signType = signType;
			this.notifyUrl = notifyUrl;
			this.sellerId = sellerId;
			this.itBPay = itBPay;
			this.publicKey = publicKey;
		}

		public String getService() {
			return service;
		}

		public String getPrivateKey() {
			return privateKey;
		}

		public String getPublicKey() {
			return publicKey;
		}

		public String getCharset() {
			return charset;
		}

		public String getPartner() {
			return partner;
		}

		public String getSignType() {
			return signType;
		}

		public String getNotifyUrl() {
			return notifyUrl;
		}

		public String getSellerId() {
			return sellerId;
		}

		public String getItBPay() {
			return itBPay;
		}

	}

	/**
	 * 微信支付信息配置
	 */
	public static class WeChatPayConfig {

		private static final String orgCertPath = "wechat.apiclient.cert.org.p12";

		private static final String prodCertPath = "wechat.apiclient.cert.prod.p12";

		private static final String mpCertPath = "wechat.apiclient.cert.mp.p12";

		private static final String orgMazingCertPath = "wechat.apiclient.cert.org.mazing.p12";

		private static final String prodMazingCertPath = "wechat.apiclient.cert.prod.mazing.p12";
		
		private static final String mpAppCertPath = "wechat.apiclient.cert.mpapp.p12";

		private String prodKey; // prod版API密钥

		private String prodAppid; // prod版公众账号ID

		private String prodMchId;// prod版商户号

		private String orgKey; // org版API密钥

		private String orgAppid; // org版公众账号ID

		private String orgMchId;// org版商户号

		private String mpAppid; // 公众号版的appid

		private String mpMchId; // 公众号版的商户号

		private String mpKey; // 公众号版的支付API密钥

		private String prodMazingKey; // 4.0版本新的app prod版API密钥

		private String prodMazingAppid; // 4.0版本新的app prod版公众账号ID

		private String prodMazingMchId; // 4.0版本新的app prod版商户号

		private String orgMazingKey; // 4.0版本新的app org版API密钥

		private String orgMazingAppid; // 4.0版本新的app org版公众账号ID

		private String orgMazingMchId; // 4.0版本新的app org版商户号

		private String packageStr;// 扩展字段 暂填写固定值Sign=WXPay

		private String notifyUrl;// 支付回调地址

		private String serviceMpMchId;// 公众号版服务商商户号

		private String serviceMpKey;// 公众号版服务商支付api密钥

		private String mpappAppId; //小程序appid
		
		private String mpappMchId; //小程序商户号
		
		private String mpappKey; //小程序支付api密钥
		
		private Map<String, String> keyMap; // key-公众账号ID，value-API密钥的集合，用于支付回调时根据appId获取密钥

		private Map<String, String> mchIdMap; // key-公众账号ID，value-商户号的集合，用于支付回调时根据appId获取商户号

		public WeChatPayConfig(String prodKey, String prodAppid, String orgKey, String orgAppid, String prodMchId,
				String orgMchId, String mpAppid, String mpMchId, String mpKey, String prodMazingKey,
				String prodMazingAppid, String prodMazingMchId, String orgMazingKey, String orgMazingAppid,
				String orgMazingMchId, String packageStr, String notifyUrl, String serviceMpMchId, String serviceMpKey,
				String mpappAppId, String mpappMchId, String mpappKey) {
			this.prodKey = prodKey;
			this.prodAppid = prodAppid;
			this.prodMchId = prodMchId;
			this.orgKey = orgKey;
			this.orgAppid = orgAppid;
			this.orgMchId = orgMchId;
			this.mpAppid = mpAppid;
			this.mpMchId = mpMchId;
			this.mpKey = mpKey;
			this.prodMazingKey = prodMazingKey;
			this.prodMazingAppid = prodMazingAppid;
			this.prodMazingMchId = prodMazingMchId;
			this.orgMazingKey = orgMazingKey;
			this.orgMazingAppid = orgMazingAppid;
			this.orgMazingMchId = orgMazingMchId;
			this.packageStr = packageStr;
			this.notifyUrl = notifyUrl;
			this.serviceMpMchId = serviceMpMchId;
			this.serviceMpKey = serviceMpKey;
			this.mpappAppId = mpappAppId;
			this.mpappMchId = mpappMchId;
			this.mpappKey = mpappKey;

			keyMap = new HashMap<String, String>(6);
			keyMap.put(prodAppid, prodKey);
			keyMap.put(orgAppid, orgKey);
			keyMap.put(mpAppid, mpKey);
			keyMap.put(prodMazingAppid, prodMazingKey);
			keyMap.put(orgMazingAppid, orgMazingKey);
			keyMap.put(mpappAppId, mpappKey);
			
			mchIdMap = new HashMap<String, String>(4);
			mchIdMap.put(prodAppid, prodMchId);
			mchIdMap.put(orgAppid, orgMchId);
			mchIdMap.put(mpAppid, mpMchId);
			mchIdMap.put(prodMazingAppid, prodMazingMchId);
			mchIdMap.put(orgMazingAppid, orgMazingMchId);
			mchIdMap.put(mpappAppId, mpappMchId);
		}

		/**
		 * 根据客户端版本获取微信支付密钥<br>
		 * 如果传入不正确的客户端版本，会返回null
		 * 
		 * @author Hins
		 * @date 2016年2月6日 上午11:32:47
		 * 
		 * @param target
		 *            - 客户端版本，参照IosTargetConstants
		 * @return
		 */
		public String getKey(String target, short sysType) {
			if (SysType.Android.getValue() == sysType) {
				return TargetConstants.ONLINE_NEW.equals(target) ? prodMazingKey : prodKey;
			}

			if (TargetConstants.ORG.equals(target)) {
				return orgKey;
			} else if (TargetConstants.ONLINE.equals(target)) {
				return prodKey;
			} else if (TargetConstants.ORG_NEW.equals(target)) {
				return orgMazingKey;
			} else if (TargetConstants.ONLINE_NEW.equals(target)) {
				return prodMazingKey;
			}
			return null;
		}

		/**
		 * 根据客户端版本获取微信支付appId<br>
		 * 如果是Android版本，直接返回prod的appId<br>
		 * 如果传入不正确的客户端版本，会返回null
		 * 
		 * @author Hins
		 * @date 2016年2月6日 上午11:33:07
		 * 
		 * @param target
		 *            - 客户端版本，参照IosTargetConstants
		 * @param sysType
		 *            - 操作系统类型
		 * @return
		 */
		public String getAppid(String target, short sysType) {

			if (SysType.Android.getValue() == sysType) {
				return TargetConstants.ONLINE_NEW.equals(target) ? prodMazingAppid : prodAppid;
			}

			if (TargetConstants.ORG.equals(target)) {
				return orgAppid;
			} else if (TargetConstants.ONLINE.equals(target)) {
				return prodAppid;
			} else if (TargetConstants.ORG_NEW.equals(target)) {
				return orgMazingAppid;
			} else if (TargetConstants.ONLINE_NEW.equals(target)) {
				return prodMazingAppid;
			}
			return null;
		}

		/**
		 * 根据公众账号ID获取对应的密钥<br>
		 * 如果传入不正确的公众账号ID，返回null
		 * 
		 * @author Hins
		 * @date 2016年2月6日 上午11:36:38
		 * 
		 * @param appId
		 * @return
		 */
		public String getKeyByAppId(String appId) {
			return keyMap.get(appId);
		}

		/**
		 * 根据公众账号ID获取对应的商户号<br>
		 * 如果传入不正确的公众账号ID，返回null
		 * 
		 * @author Hins
		 * @date 2016年2月15日 上午11:05:40
		 * 
		 * @param appId
		 * @return
		 */
		public String getMahIdByAppId(String appId) {
			return mchIdMap.get(appId);
		}

		/**
		 * 根据客户端版本获取微信支付商户号<br>
		 * 如果传入不正确的客户端版本，会返回null
		 * 
		 * @author Hins
		 * @date 2016年2月15日 上午10:43:08
		 * 
		 * @param target
		 *            - 客户端版本，参照IosTargetConstants
		 * @return
		 */
		public String getMchId(String target, short sysType) {
			if (SysType.Android.getValue() == sysType) {
				return TargetConstants.ONLINE_NEW.equals(target) ? prodMazingMchId : prodMchId;
			}

			if (TargetConstants.ORG.equals(target)) {
				return orgMchId;
			} else if (TargetConstants.ONLINE.equals(target)) {
				return prodMchId;
			} else if (TargetConstants.ORG_NEW.equals(target)) {
				return orgMazingMchId;
			} else if (TargetConstants.ONLINE_NEW.equals(target)) {
				return prodMazingMchId;
			}
			return null;
		}

		/**
		 * 根据不同的版本的微信appId，获取对应的证书文件名（相对地址）<br>
		 * 如果传入不正确的appId，返回null
		 * 
		 * @author Hins
		 * @date 2016年2月16日 下午3:07:26
		 * 
		 * @param appId
		 * @return
		 */
		public String getCertPath(String appId) {
			if (StringUtils.isBlank(appId)) {
				return null;
			}

			if (appId.equals(orgAppid)) {
				return orgCertPath;
			}

			if (appId.equals(prodAppid)) {
				return prodCertPath;
			}

			if (appId.equals(mpAppid)) {
				return mpCertPath;
			}

			if (appId.equals(orgMazingAppid)) {
				return orgMazingCertPath;
			}

			if (appId.equals(prodMazingAppid)) {
				return prodMazingCertPath;
			}
			
			if (appId.equals(mpappAppId)) {
				return mpAppCertPath;
			}
			return null;
		}

		public String getMpAppid() {
			return mpAppid;
		}

		public String getMpMchId() {
			return mpMchId;
		}

		public String getMpKey() {
			return mpKey;
		}

		public String getPackageStr() {
			return packageStr;
		}

		public String getNotifyUrl() {
			return notifyUrl;
		}

		public String getServiceMpMchId() {
			return serviceMpMchId;
		}

		public String getServiceMpKey() {
			return serviceMpKey;
		}
		public String getMpappAppId() {
			return mpappAppId;
		}

		public String getMpappMchId() {
			return mpappMchId;
		}

		public String getMpappKey() {
			return mpappKey;
		}

	}

	public static class AlipayRefund {
		private String service; // 接口名称

		private String key; // 支付宝商家key

		private String charset; // 支付宝调用参数编码

		private String partner; // 支付宝合作者id

		private String signType; // 加密方式MD5

		private String notifyUrl; // 支付宝回调地址

		private String sellerId; // 卖家支付宝ID，加密后的

		private String sellerEmail; // 卖家支付宝账号，加密后的

		public AlipayRefund(String service, String key, String charset, String partner, String signType,
				String notifyUrl, String sellerId, String sellerEmail) {
			this.service = service;
			this.key = key;
			this.partner = partner;
			this.charset = charset;
			this.signType = signType;
			this.notifyUrl = notifyUrl;
			this.sellerId = sellerId;
			this.sellerEmail = sellerEmail;
		}

		public String getService() {
			return service;
		}

		public String getKey() {
			return key;
		}

		public String getCharset() {
			return charset;
		}

		public String getPartner() {
			return partner;
		}

		public String getSignType() {
			return signType;
		}

		public String getNotifyUrl() {
			return notifyUrl;
		}

		public String getSellerId() {
			return sellerId;
		}

		public String getSellerEmail() {
			return sellerEmail;
		}

	}

	/**
	 * 支付宝提现信息配置
	 */
	public static class AlipayWithdraw {

		public AlipayWithdraw(String service, String partner, String charset, String signType, String notifyUrl,
				String sellerId, String accountName, String privateKey, String publicKey) {
			this.service = service;
			this.partner = partner;
			this.charset = charset;
			this.signType = signType;
			this.notifyUrl = notifyUrl;
			this.sellerId = sellerId;
			this.accountName = accountName;
			this.privateKey = privateKey;
			this.publicKey = publicKey;
		}

		/** 支付宝提现接口 */
		private String service;

		/** 支付宝提现接口 */
		private String partner;

		/** 支付宝调用参数编码 */
		private String charset;

		/** 加密方式RSA */
		private String signType;

		/** 支付宝回调地址 */
		private String notifyUrl;

		/** 卖家支付宝ID，加密后的 */
		private String sellerId;

		/** 卖家支付宝账户名，加密后的 */
		private String accountName;

		private String privateKey; // 支付宝商家私钥

		private String publicKey; // 支付宝商家公钥

		public String getService() {
			return service;
		}

		public String getPartner() {
			return partner;
		}

		public String getCharset() {
			return charset;
		}

		public String getSignType() {
			return signType;
		}

		public String getNotifyUrl() {
			return notifyUrl;
		}

		public String getSellerId() {
			return sellerId;
		}

		public String getAccountName() {
			return accountName;
		}

		public String getPrivateKey() {
			return privateKey;
		}

		public String getPublicKey() {
			return publicKey;
		}
	}

	/**
	 * 新版支付宝支付信息配置(明文)
	 */
	public static class NewAliPay {

		private String appId; // 支付宝分配给开发者的应用ID

		private String charset; // 请求使用的编码格式，如utf-8,gbk,gb2312等, 默认utf-8

		private String signType; // 商户生成签名字符串所使用的签名算法类型，目前支持RSA2和RSA，推荐使用RSA2,
									// 默认RSA2

		private String version = "1.0";// 调用的接口版本，固定为：1.0

		private String notifyUrl; // 支付宝服务器主动通知商户服务器里指定的页面http/https路径。

		private String returnUrl; // 支付宝服务器主动通知商户服务器里指定的页面http/https路径。

		private String sellerId; // 收款支付宝用户ID。 如果该值为空，则默认为商户签约账号对应的支付宝用户ID

		private String timeoutExpress;// 未支付订单超时时间

		private String privateKey;

		private String publicKey;

		public NewAliPay(String appId, String charset, String signType, String version, String notifyUrl,
				String returnUrl, String sellerId, String timeoutExpress, String privateKey, String publicKey) {
			super();
			this.appId = appId;
			this.charset = charset;
			this.signType = signType;
			this.version = version;
			this.notifyUrl = notifyUrl;
			this.returnUrl = returnUrl;
			this.sellerId = sellerId;
			this.timeoutExpress = timeoutExpress;
			this.privateKey = privateKey;
			this.publicKey = publicKey;
		}

		public String getAppId() {
			return appId;
		}

		public void setAppId(String appId) {
			this.appId = appId;
		}

		public String getCharset() {
			return charset;
		}

		public void setCharset(String charset) {
			this.charset = charset;
		}

		public String getSignType() {
			return signType;
		}

		public void setSignType(String signType) {
			this.signType = signType;
		}

		public String getVersion() {
			return version;
		}

		public void setVersion(String version) {
			this.version = version;
		}

		public String getNotifyUrl() {
			return notifyUrl;
		}

		public void setNotifyUrl(String notifyUrl) {
			this.notifyUrl = notifyUrl;
		}

		public String getReturnUrl() {
			return returnUrl;
		}

		public void setReturnUrl(String returnUrl) {
			this.returnUrl = returnUrl;
		}

		public String getSellerId() {
			return sellerId;
		}

		public void setSellerId(String sellerId) {
			this.sellerId = sellerId;
		}

		public String getTimeoutExpress() {
			return timeoutExpress;
		}

		public void setTimeoutExpress(String timeoutExpress) {
			this.timeoutExpress = timeoutExpress;
		}

		public String getPrivateKey() {
			return privateKey;
		}

		public void setPrivateKey(String privateKey) {
			this.privateKey = privateKey;
		}

		public String getPublicKey() {
			return publicKey;
		}

		public void setPublicKey(String publicKey) {
			this.publicKey = publicKey;
		}

	}
}
