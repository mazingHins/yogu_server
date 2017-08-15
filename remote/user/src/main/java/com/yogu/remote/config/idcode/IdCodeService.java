package com.yogu.remote.config.idcode;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import javax.inject.Named;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.yogu.cfg.GlobalSetting;
import com.yogu.commons.cache.redis.Redis;
import com.yogu.commons.server.context.MainframeBeanFactory;
import com.yogu.commons.utils.LogUtil;
import com.yogu.core.enums.SmsConstant;
import com.yogu.core.remote.config.AppLanguage;
import com.yogu.core.remote.config.ConfigRemoteService;
import com.yogu.core.sms.SmsConfig;
import com.yogu.core.sms.SmsServiceFactory;
import com.yogu.core.web.ParameterUtil;
import com.yogu.core.web.ResultCode;
import com.yogu.core.web.context.SecurityContext;

/**
 * 验证码服务。 暂时只在本地实现，不通过远程接口。
 * 
 * @author linyi 2015/5/18.
 */
@Named
public class IdCodeService {

	private final Logger logger = LoggerFactory.getLogger(IdCodeService.class);

	public static final String FUNC_REG = "reg"; // 注册
	public static final String FUNC_LOGIN = "login"; // 登录
	public static final String FUNC_FP = "fp"; // forgot password
	public static final String FUNC_CH = "ch"; // change password
	public static final String FUNC_BD = "bd"; // 绑定邮箱, 绑定支付宝账号等 bind 操作
	public static final String FUNC_LOGIN_NO_PWD = "login2"; // 无密登录
	@Deprecated
	public static final String FUNC_REG_NO_PWD = "reg2"; // 无密注册

	private static final int CODE_VALID_TIME = 6000; // 验证码有效时间 10分钟

	private static final int NO_PWD_CODE_VALID_TIME = 600; // 无密注册/登录的验证码有效时间为 10 分钟

	private Redis redis;// TODO 应该改成API接口进行访问（包括获取、交验），而不是访问其他域的业务数据

	private static final Set<String> FUNC_SET = new HashSet<>();

	static {
		FUNC_SET.add(FUNC_REG);
		FUNC_SET.add(FUNC_LOGIN);
		FUNC_SET.add(FUNC_FP);
		FUNC_SET.add(FUNC_CH);
		FUNC_SET.add(FUNC_BD);
		FUNC_SET.add(FUNC_REG_NO_PWD);
		FUNC_SET.add(FUNC_LOGIN_NO_PWD);
	}

	// /**
	// * 验证码保存在Redis
	// */
	// @Inject
	// protected Redis redis;

	/**
	 * 发送短信验证码。不判断mobile是否合法。
	 * 
	 * @param country - 国家，暂时无用，可以为空
	 * @param mobile - 手机号码，不能为空
	 * @param func - 哪个功能发短信验证码，不能为空，用来查找短信模版
	 * @return true=发送成功
	 */
	public boolean sendSmsIdCode(String country, String mobile, String func) {

		logger.info("IdCodeService#sms#code | 发送验证码  | countryCode: {}, mobile: {}, func: {}", country, LogUtil.hidePassport(mobile), func);

		if (!(FUNC_SET.contains(func)))
			throw ResultCode.notExistExcp("不正确的验证码类型：" + func);

		String smsCode = getSmsCode(mobile, func);// 一次发送,10分钟内有效

		// 不是生产环境,不发验证码,但也会存起来
		String env = GlobalSetting.getProjenv();
		boolean smsOK = false;
		
		//目前什么环境都发验证码了， 所以保存提前  add by sky 2016-10-16
		saveToRedis(mobile, func, smsCode);

		if (reallySend(env, func)) {

			//add by east 2017-03-14 区分语言版本，发送不同语音的短信
			String smsType = getSmsType(SmsConstant.SMS_CODE, SecurityContext.getAppLanguage().getCode());
			if (reachLimit(func, mobile))// 达到限制,切换渠道

				smsOK = SmsServiceFactory.getAnotherCodeServiceInstance().mazingTemplate(smsType).mobile(mobile)
						.param("code", smsCode).send();
			else
				smsOK = SmsServiceFactory.getCodeInstance().mazingTemplate(smsType).mobile(mobile).param("code", smsCode)
						.send();
			
			
			logger.info("IdCodeService#sms#code | 发送验证码 end | success: " + smsOK);
			
			
		} else {

			smsOK = true;
			logger.info("IdCodeService#sms#code | {}环境的验证码（{}）短信不会发出去，请到admin中查询验证码", env, func);
		}

		// 4. 成功了，就存储到redis中
	//	if (smsOK)
	//		saveToRedis(mobile, func, smsCode);
		return smsOK;
	}

	/**
	 * 是否已达到给定的限制（该手机在func下, 在限定的时间内 连续发验证码超过n次）
	 * 
	 * @param func 发验证码的标识函数
	 * @param mobile 手机号码
	 * @return
	 * @author sky 2016-09-27
	 */
	private boolean reachLimit(String func, String mobile) {

		// 后台限制的 发送次数
		String numStr = ConfigRemoteService.getConfig(SmsConfig.SMS_SETTING_GROUP,
				SmsConfig.SMS_SETTING_SERVICE_CHANGE_LIMIT_CODE_SEND_COUNT);
		// 后台限制的 时间数,单位 s
		String secondStr = ConfigRemoteService.getConfig(SmsConfig.SMS_SETTING_GROUP, SmsConfig.SMS_SETTING_SERVICE_CHANGE_VALID_TIME);

		// 没有配置，不做切换
		if (StringUtils.isBlank(numStr) || StringUtils.isBlank(secondStr))
			return false;

		final int num = Integer.parseInt(numStr);// 同一func,同一手机号, 短时间内发送了3次
		final int second = Integer.parseInt(secondStr);// 7分钟内, 若发送超过3次, 进行渠道切换, 切换后,
														// 持续3分钟(一分钟只能发一个验证码,所以一个渠道用3分钟)
		// 配置了0，不做切换
		if (num == 0 || second == 0)
			return false;

		String key = StringUtils.join(new String[] { "smsFailLimit", func, mobile }, '_');

		int total = NumberUtils.toInt(redis().get(key));

		logger.info("IdCodeService#reachLimit | 当前该用户发送的次数 | func: {}, mobile: {}, total: {}", func, LogUtil.hidePassport(mobile), total);

		if (total <= 0)
			redis().del(key);// 有可能是之前访问留下的痕迹

		redis().incr(key);// 增加key的值

		if (total < num) {// 小于限制值,继续计数

			if (total <= 0)
				redis().expire(key, second);// 限定时间内的第一次访问设置过期时间
			return false;
		} else {
			return true;
		}

	}

	/**
	 * 获取func 下 用户 mobile 的验证码, 如果之前发过且之前的验证码还有效, 直接返回之前生成的验证码; 若不存在,生成一个新的
	 * 
	 * @param mobile 用户手机号码
	 * @param func 验证码func
	 * @return
	 * @author sky 2016-09-27
	 */
	private String getSmsCode(String mobile, String func) {
		String key = getKeyWithFunc(mobile, func);// 获取缓存key
		String code = redis().get(key);// 缓存中是否有

		if (StringUtils.isBlank(code))
			code = (new Random().nextInt(900000) + 100000) + "";
		return code;
	}

	/**
	 * 决定是否真的发出短信<br>
	 * <strong>注意：不发出短信，并不表示不存入redis（比如test环境的验证码短信，不会发出去，但是要存到redis中）</strong>
	 */
	private boolean reallySend(String env, String func) {
		// 生产环境一定会发
		if (GlobalSetting.PROJENV_PROD.equals(env))
			return true;

		// 不是生产环境的，只有 【验证码】 不发
		// if (FUNC_LOGIN_NO_PWD.equals(func)//
		// // || FUNC_REG.equals(func)//
		// // || FUNC_LOGIN.equals(func)//
		// || FUNC_REG_NO_PWD.equals(func))
		// return false;
		return true;
	}

	/**
	 * 保存验证码到 REDIS，只保存10分钟
	 * 
	 * @param mobile - 手机
	 * @param func - 功能
	 * @param code - 验证码
	 */
	private void saveToRedis(String mobile, String func, String code) {
		// 只保存20分钟
		redis().setex(getKey(mobile), CODE_VALID_TIME, code);
		// felix 2016-03-10, 无论所有func都保存
		saveToRedisWithFunc(mobile, func, code);
	}

	/**
	 * 保存验证码到 REDIS，只保存10分钟, 其中func作为key的一部分
	 * 
	 * @param mobile - 手机
	 * @param func - 功能
	 * @param code - 验证码
	 * @author felix 2016-03-10
	 */
	private void saveToRedisWithFunc(String mobile, String func, String code) {

		String key = getKeyWithFunc(mobile, func);
		// 没缓存过,新缓存; 缓存过了, 不再缓存 --> 从生成到失效, 真的只有10分钟,不做时间延长
		if (StringUtils.isBlank(redis().get(key)))
			redis().setex(getKeyWithFunc(mobile, func), NO_PWD_CODE_VALID_TIME, code);

	}

	/**
	 * 返回在 redis 的key
	 * 
	 * @param mobile
	 * @return 跟手机相关的一个key
	 */
	private String getKey(String mobile) {
		return "IdCodeService.code_" + mobile;
	}

	/**
	 * 返回在 redis 的key
	 * 
	 * @param mobile
	 * @param code
	 * @return 跟手机和验证码相关的一个key
	 */
	private String getKeyWithFunc(String mobile, String func) {
		return "IdCodeService.code_" + func + "_" + mobile;
	}

	/**
	 * 校验短信验证码是否正确，如果正确，删除验证码，验证码不能再次使用
	 * 
	 * @param mobile - 手机号码，不能为空
	 * @param func - 哪个功能发短信验证码，不能为空
	 * @param code - 用户输入的验证码
	 * @return true=验证码正确
	 */
	public boolean validateSmsIdCode(String mobile, String func, String code) {
		ParameterUtil.assertNotBlank(mobile, "手机号码不能为空");
		ParameterUtil.assertNotBlank(code, "验证码不能为空");
		// 1. 查找 func 对应的验证码
		String key = getKeyWithFunc(mobile, func);
		String current = redis().get(key);

		// 2. 比较验证码, 返回结果
		if (current != null && current.equals(code.trim())) {
			// 不删除验证码
			return true;
		}

		// 若验证失败, 则再次验证新的验证码key
		key = getKey(mobile);
		current = redis().get(key);

		// 3. 比较验证码, 返回结果
		if (current != null && current.equals(code.trim())) {
			// 删除验证码
			redis().del(key);
			return true;
		}

		return false;
	}

	/**
	 * 返回验证码
	 * 
	 * @param mobile 手机号码
	 * @return 返回验证码，如果没有验证码或已经过期，返回 ""
	 */
	public String getSmsCode(String mobile) {
		ParameterUtil.assertNotBlank(mobile, "mobile不能为空");

		String current = getSmsCodeAllFunc(mobile);
		if (StringUtils.isBlank(current)) {
			current = redis().get(getKey(mobile));
		}
		return StringUtils.trimToEmpty(current);
	}

	public String getSmsCodeAllFunc(String mobile) {
		ParameterUtil.assertNotBlank(mobile, "mobile不能为空");
		StringBuilder sbd = new StringBuilder();
		for (String func : FUNC_SET) {
			String key = getKeyWithFunc(mobile, func);
			String current = redis().get(key);
			if (StringUtils.isNotBlank(current)) {
				sbd.append(",").append(func).append(":").append(StringUtils.trimToEmpty(current));
			}
		}
		String result = sbd.toString();
		result = result.length() > 0 ? result.substring(1) : result;
		return result;
	}

	/**
	 * 是否为有效的 func
	 * 
	 * @param func - 功能名
	 * @return true=有效
	 */
	public boolean isValidFunc(String func) {
		return FUNC_SET.contains(func);
	}

	private Redis redis() {
		if (null == redis)
			redis = (Redis) MainframeBeanFactory.getBean("redis");
		return redis;
	}
	
	/**
	 * 根据模版id和lang，获取对应语言的模版id
	 * @param smsType
	 * @param lang
	 * @return    
	 * @author east
	 * @date 2017年3月13日 下午4:10:53
	 */
	private String getSmsType(String smsType, String lang){
		if(StringUtils.isBlank(lang))
			return smsType;
		
		//如果是英文的，拼接英文后缀xxx_en
		if(AppLanguage.en.getCode().equals(lang)){
			return smsType + "_" + lang;
		}
		return smsType;
	}
}
