package com.yogu.language;

/**
 * 域config 下的 多语言提示相关key的常量类<br>
 * 
 * 用于组织config域下的异常提示、注解提示相关的常量key
 * 
 * @author sky 2016-03-25
 *
 */
public class ConfigMessages {

	/**
	 * appid不允许为空！
	 */
	public static final String LEANCLOUD_SIGN_APPID_CAN_NOT_BE_EMPTY = "config.leancloud.sign.appid.empty";

	/**
	 * appid不允许为空！
	 * 
	 * @return
	 */
	public static String LEANCLOUD_SIGN_APPID_CAN_NOT_BE_EMPTY() {
		return MultiLanguageAdapter.fetchMessage(LEANCLOUD_SIGN_APPID_CAN_NOT_BE_EMPTY);
	}

	/**
	 * clientid不允许为空！
	 */
	public static final String LEANCLOUD_SIGN_CLIENTID_CAN_NOT_BE_EMPTY = "config.leancloud.sign.clientid.empty";

	/**
	 * clientid不允许为空！
	 */
	public static String LEANCLOUD_SIGN_CLIENTID_CAN_NOT_BE_EMPTY() {
		return MultiLanguageAdapter.fetchMessage(LEANCLOUD_SIGN_CLIENTID_CAN_NOT_BE_EMPTY);
	}

	/**
	 * 埋点数据缺少封签参数sign
	 */
	public static final String BATCHLOG_SYNC_SIGN_CAN_NOT_BE_EMPTY = "config.batchlog.sync.sign.empty";

	/**
	 * 埋点数据缺少封签参数sign
	 */
	public static final String BATCHLOG_SYNC_SIGN_CAN_NOT_BE_EMPTY() {
		return MultiLanguageAdapter.fetchMessage(BATCHLOG_SYNC_SIGN_CAN_NOT_BE_EMPTY);
	}

	/**
	 * 埋点数据akey不正确
	 */
	public static final String BATCHLOG_SYNC_AKEY_ERROR = "config.batchlog.sync.akey.error";

	/**
	 * 埋点数据akey不正确
	 */
	public static final String BATCHLOG_SYNC_AKEY_ERROR() {
		return MultiLanguageAdapter.fetchMessage(BATCHLOG_SYNC_AKEY_ERROR);
	}

	/**
	 * 埋点数据数据验签失败！<{0}>
	 */
	public static final String BATCHLOG_SYNC_SIGN_FAILURE = "config.batchlog.sync.line.failure";

	/**
	 * 埋点数据数据验签失败！<{0}>
	 */
	public static final String BATCHLOG_SYNC_SIGN_FAILURE(Object o) {
		return MultiLanguageAdapter.fetchMessage(BATCHLOG_SYNC_SIGN_FAILURE, o);
	}

	/**
	 * 请输入手机号码
	 */
	public static final String SMSCODE_CODE_RECEIVER_CAN_NOT_BE_EMPTY = "config.smscode.code.receiver.empty";

	/**
	 * 请输入手机号码
	 */
	public static final String SMSCODE_CODE_RECEIVER_CAN_NOT_BE_EMPTY() {
		return MultiLanguageAdapter.fetchMessage(SMSCODE_CODE_RECEIVER_CAN_NOT_BE_EMPTY);
	}

	/**
	 * 手机号码不正确,请重新输入
	 */
	public static final String SMSCODE_CODE_RECEIVER_ERROR = "config.smscode.code.receiver.error";

	/**
	 * 手机号码不正确,请重新输入
	 */
	public static final String SMSCODE_CODE_RECEIVER_ERROR() {
		return MultiLanguageAdapter.fetchMessage(SMSCODE_CODE_RECEIVER_ERROR);
	}

	/**
	 * 用户不存在, 无法发送验证码
	 */
	public static final String SMSCODE_CODE_RECEIVER_NOT_EXIST = "config.smscode.code.receiver.notexist";

	/**
	 * 用户不存在, 无法发送验证码
	 */
	public static final String SMSCODE_CODE_RECEIVER_NOT_EXIST() {
		return MultiLanguageAdapter.fetchMessage(SMSCODE_CODE_RECEIVER_NOT_EXIST);
	}

	/**
	 * 您今天尝试获取验证码超出限制, 请明日再试
	 */
	public static final String SMSCODE_CODE_RETRY_OVER_LIMIT = "config.smscode.code.trycount.overlimit";

	/**
	 * 您今天尝试获取验证码超出限制, 请明日再试
	 */
	public static final String SMSCODE_CODE_RETRY_OVER_LIMIT() {
		return MultiLanguageAdapter.fetchMessage(SMSCODE_CODE_RETRY_OVER_LIMIT);
	}

	/**
	 * func错误
	 */
	public static final String SMSCODE_CODE_FUNC_ERROR = "config.smscode.code.fun.error";

	/**
	 * func错误
	 */
	public static final String SMSCODE_CODE_FUNC_ERROR() {
		return MultiLanguageAdapter.fetchMessage(SMSCODE_CODE_FUNC_ERROR);
	}

	/**
	 * 发送短信验证码成功
	 */
	public static final String SMSCODE_CODE_SEND_SUCCESS = "config.smscode.code.success";

	/**
	 * 发送短信验证码成功
	 */
	public static final String SMSCODE_CODE_SEND_SUCCESS() {
		return MultiLanguageAdapter.fetchMessage(SMSCODE_CODE_SEND_SUCCESS);
	}

	/**
	 * 发送短信验证码失败
	 */
	public static final String SMSCODE_CODE_SEND_FAILURE = "config.smscode.code.failure";

	/**
	 * 发送短信验证码失败
	 */
	public static final String SMSCODE_CODE_SEND_FAILURE() {
		return MultiLanguageAdapter.fetchMessage(SMSCODE_CODE_SEND_FAILURE);
	}

	/**
	 * 活动跳转链接长度不符合要求
	 */
	public static final String ADVERTISE_ADDACTIVITY_URL_LENGTH_ERROR = "config.advertiseAdminApi.addAdverActivity.url.length.error";

	/**
	 * 活动跳转链接长度不符合要求
	 */
	public static final String ADVERTISE_ADDACTIVITY_URL_LENGTH_ERROR() {
		return MultiLanguageAdapter.fetchMessage(ADVERTISE_ADDACTIVITY_URL_LENGTH_ERROR);
	}

	/**
	 * 保存成功
	 */
	public static final String ADVERTISE_ADDADVERBAR_SUCCESS = "config.advertiseAdminApi.addAdverBar.success";

	/**
	 * 保存成功
	 */
	public static final String ADVERTISE_ADDADVERBAR_SUCCESS() {
		return MultiLanguageAdapter.fetchMessage(ADVERTISE_ADDADVERBAR_SUCCESS);
	}

	/**
	 * 删除成功
	 */
	public static final String ADVERTISE_DELETEACTIVITY_SUCCESS = "config.advertiseAdminApi.deleteAdverActivity.success";

	/**
	 * 删除成功
	 */
	public static final String ADVERTISE_DELETEACTIVITY_SUCCESS() {
		return MultiLanguageAdapter.fetchMessage(ADVERTISE_DELETEACTIVITY_SUCCESS);
	}

	/**
	 * idName不能为空
	 */
	public static final String BASE_GETNEXTID_IDNAME_CAN_NOT_BE_EMPTY = "config.baseApi.getNextId.idName.empty";

	/**
	 * idName不能为空
	 */
	public static final String BASE_GETNEXTID_IDNAME_CAN_NOT_BE_EMPTY() {
		return MultiLanguageAdapter.fetchMessage(BASE_GETNEXTID_IDNAME_CAN_NOT_BE_EMPTY);
	}

	/**
	 * 获取ID失败，有可能是多个客户端一起并发获取，请重试
	 */
	public static final String BASE_GETNEXTID_FAILURE = "config.baseApi.getNextId.failure";

	/**
	 * 获取ID失败，有可能是多个客户端一起并发获取，请重试
	 */
	public static final String BASE_GETNEXTID_FAILURE() {
		return MultiLanguageAdapter.fetchMessage(BASE_GETNEXTID_FAILURE);
	}

	/**
	 * 分组不能为空
	 */
	public static final String BASE_SAVECONFIG_GROUPCODE_CAN_NOT_BE_EMPTY = "config.baseApi.saveConfig.groupCode.empty";

	/**
	 * 分组不能为空
	 */
	public static final String BASE_SAVECONFIG_GROUPCODE_CAN_NOT_BE_EMPTY() {
		return MultiLanguageAdapter.fetchMessage(BASE_SAVECONFIG_GROUPCODE_CAN_NOT_BE_EMPTY);
	}

	/**
	 * key不能为空
	 */
	public static final String BASE_SAVECONFIG_CONFIGKEY_CAN_NOT_BE_EMPTY = "config.baseApi.saveConfig.configKey.empty";

	/**
	 * key不能为空
	 */
	public static final String BASE_SAVECONFIG_CONFIGKEY_CAN_NOT_BE_EMPTY() {
		return MultiLanguageAdapter.fetchMessage(BASE_SAVECONFIG_CONFIGKEY_CAN_NOT_BE_EMPTY);
	}

	/**
	 * value不能为空
	 */
	public static final String BASE_SAVECONFIG_CONFIGVALUE_CAN_NOT_BE_EMPTY = "config.baseApi.saveConfig.configValue.empty";

	/**
	 * value不能为空
	 */
	public static final String BASE_SAVECONFIG_CONFIGVALUE_CAN_NOT_BE_EMPTY() {
		return MultiLanguageAdapter.fetchMessage(BASE_SAVECONFIG_CONFIGVALUE_CAN_NOT_BE_EMPTY);
	}

	/**
	 * 备注不能为空
	 */
	public static final String BASE_SAVECONFIG_REMARK_CAN_NOT_BE_EMPTY = "config.baseApi.saveConfig.remark.empty";

	/**
	 * 备注不能为空
	 */
	public static final String BASE_SAVECONFIG_REMARK_CAN_NOT_BE_EMPTY() {
		return MultiLanguageAdapter.fetchMessage(BASE_SAVECONFIG_REMARK_CAN_NOT_BE_EMPTY);
	}

	/**
	 * title不能为空
	 */
	public static final String BASE_SAVEVERSIONUPGRADE_TITLE_CAN_NOT_BE_EMPTY = "config.baseApi.saveVersionUpgrade.title.empty";

	/**
	 * title不能为空
	 */
	public static final String BASE_SAVEVERSIONUPGRADE_TITLE_CAN_NOT_BE_EMPTY() {
		return MultiLanguageAdapter.fetchMessage(BASE_SAVEVERSIONUPGRADE_TITLE_CAN_NOT_BE_EMPTY);
	}

	/**
	 * title长度不符合要求
	 */
	public static final String BASE_SAVEVERSIONUPGRADE_TITLE_LENGTH_ERROR = "config.baseApi.saveVersionUpgrade.title.length.error";

	/**
	 * title长度不符合要求
	 */
	public static final String BASE_SAVEVERSIONUPGRADE_TITLE_LENGTH_ERROR() {
		return MultiLanguageAdapter.fetchMessage(BASE_SAVEVERSIONUPGRADE_TITLE_LENGTH_ERROR);
	}

	/**
	 * 更新说明不能为空
	 */
	public static final String BASE_SAVEVERSIONUPGRADE_MSG_CAN_NOT_BE_EMPTY = "config.baseApi.saveVersionUpgrade.msg.empty";

	/**
	 * 更新说明不能为空
	 */
	public static final String BASE_SAVEVERSIONUPGRADE_MSG_CAN_NOT_BE_EMPTY() {
		return MultiLanguageAdapter.fetchMessage(BASE_SAVEVERSIONUPGRADE_MSG_CAN_NOT_BE_EMPTY);
	}

	/**
	 * 更新说明长度不符合要求
	 */
	public static final String BASE_SAVEVERSIONUPGRADE_MSG_TITLE_LENGTH_ERROR = "config.baseApi.saveVersionUpgrade.msg.length.error";

	/**
	 * 更新说明长度不符合要求
	 */
	public static final String BASE_SAVEVERSIONUPGRADE_MSG_TITLE_LENGTH_ERROR() {
		return MultiLanguageAdapter.fetchMessage(BASE_SAVEVERSIONUPGRADE_MSG_TITLE_LENGTH_ERROR);
	}

	/**
	 * 版本号不能为空
	 */
	public static final String BASE_SAVEVERSIONUPGRADE_VERION_CAN_NOT_BE_EMPTY = "config.baseApi.saveVersionUpgrade.version.empty";

	/**
	 * 版本号不能为空
	 */
	public static final String BASE_SAVEVERSIONUPGRADE_VERION_CAN_NOT_BE_EMPTY() {
		return MultiLanguageAdapter.fetchMessage(BASE_SAVEVERSIONUPGRADE_VERION_CAN_NOT_BE_EMPTY);
	}

	/**
	 * 版本号长度不符合要求
	 */
	public static final String BASE_SAVEVERSIONUPGRADE_VERION_LENGTH_ERROR = "config.baseApi.saveVersionUpgrade.version.length.error";

	/**
	 * 版本号长度不符合要求
	 */
	public static final String BASE_SAVEVERSIONUPGRADE_VERION_LENGTH_ERROR() {
		return MultiLanguageAdapter.fetchMessage(BASE_SAVEVERSIONUPGRADE_VERION_LENGTH_ERROR);
	}

	/**
	 * 升级下载地址不能为空
	 */
	public static final String BASE_SAVEVERSIONUPGRADE_URL_CAN_NOT_BE_EMPTY = "config.baseApi.saveVersionUpgrade.url.empty";

	/**
	 * 升级下载地址不能为空
	 */
	public static final String BASE_SAVEVERSIONUPGRADE_URL_CAN_NOT_BE_EMPTY() {
		return MultiLanguageAdapter.fetchMessage(BASE_SAVEVERSIONUPGRADE_URL_CAN_NOT_BE_EMPTY);
	}

	/**
	 * 升级下载地址长度不符合要求
	 */
	public static final String BASE_SAVEVERSIONUPGRADE_URL_LENGTH_ERROR = "config.baseApi.saveVersionUpgrade.url.length.error";

	/**
	 * 升级下载地址长度不符合要求
	 */
	public static final String BASE_SAVEVERSIONUPGRADE_URL_LENGTH_ERROR() {
		return MultiLanguageAdapter.fetchMessage(BASE_SAVEVERSIONUPGRADE_URL_LENGTH_ERROR);
	}

}
