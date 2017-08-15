package com.yogu.language;

/**
 * 域user 下的 多语言提示相关key的常量类<br>
 * 
 * 用于组织user域下的异常提示、注解提示相关的常量key
 * 
 * @author sky 2016-03-25
 *
 */
/**
 * @author felix
 *
 */
/**
 * @author felix
 *
 */
public class UserMessages {

	/**
	 * 您的地图坐标不是广州市，请把地图坐标移到广州市的区域。
	 */
	public static final String USER_LOCATION_IS_NOT_GZ = "user.address.save.address.error";

	/**
	 * 您的地图坐标不是广州市，请把地图坐标移到广州市的区域。
	 * 
	 * @return
	 */
	public static String USER_LOCATION_IS_NOT_GZ() {
		return MultiLanguageAdapter.fetchMessage(USER_LOCATION_IS_NOT_GZ);
	}

	/**
	 * 参数不合法
	 */
	public static final String USER_ADDRESS_SAVE_ADDRESS_PARAMS_ILLEGAL = "user.address.save.address.params.illegal";

	/**
	 * 参数不合法
	 * 
	 * @return
	 */
	public static String USER_ADDRESS_SAVE_ADDRESS_PARAMS_ILLEGAL() {
		return MultiLanguageAdapter.fetchMessage(USER_ADDRESS_SAVE_ADDRESS_PARAMS_ILLEGAL);
	}

	/**
	 * 区域编码不能为空
	 */
	public static final String USER_ADDRESS_DISTRICT_CODE_CAN_NOT_BE_EMPTY = "user.address.save.address.districtCode.empty";

	/**
	 * 区域编码不能为空
	 * 
	 * @return
	 */
	public static String USER_ADDRESS_DISTRICT_CODE_CAN_NOT_BE_EMPTY() {
		return MultiLanguageAdapter.fetchMessage(USER_ADDRESS_DISTRICT_CODE_CAN_NOT_BE_EMPTY);
	}

	/**
	 * 送餐地址简写不能为空
	 */
	public static final String USER_ADDRESS_NAME_CAN_NOT_BE_EMPTY = "user.address.save.address.name.empty";

	/**
	 * 送餐地址简写不能为空
	 * 
	 * @return
	 */
	public static String USER_ADDRESS_NAME_CAN_NOT_BE_EMPTY() {
		return MultiLanguageAdapter.fetchMessage(USER_ADDRESS_NAME_CAN_NOT_BE_EMPTY);
	}

	// --
	/**
	 * 地址详情不能为空
	 */
	public static final String USER_ADDRESS_DETAIL_CAN_NOT_BE_EMPTY = "user.address.save.address.detail.empty";

	/**
	 * 地址详情不能为空
	 * 
	 * @return
	 */
	public static String USER_ADDRESS_DETAIL_CAN_NOT_BE_EMPTY() {
		return MultiLanguageAdapter.fetchMessage(USER_ADDRESS_DETAIL_CAN_NOT_BE_EMPTY);
	}

	// --
	/**
	 * 收货人不能为空
	 */
	public static final String USER_ADDRESS_CONTACTS_CAN_NOT_BE_EMPTY = "user.address.save.address.contacts.empty";

	/**
	 * 收货人不能为空
	 * 
	 * @return
	 */
	public static String USER_ADDRESS_CONTACTS_CAN_NOT_BE_EMPTY() {
		return MultiLanguageAdapter.fetchMessage(USER_ADDRESS_CONTACTS_CAN_NOT_BE_EMPTY);
	}

	// --
	/**
	 * 联系电话不能为空
	 */
	public static final String USER_ADDRESS_PHONE_CAN_NOT_BE_EMPTY = "user.address.save.address.phone.empty";

	/**
	 * 联系电话不能为空
	 * 
	 * @return
	 */
	public static String USER_ADDRESS_PHONE_CAN_NOT_BE_EMPTY() {
		return MultiLanguageAdapter.fetchMessage(USER_ADDRESS_PHONE_CAN_NOT_BE_EMPTY);
	}

	// --
	/**
	 * 地址简写内容过长
	 */
	public static final String USER_ADDRESS_NAME_TOO_LONG = "user.address.save.address.name.length.max";

	/**
	 * 地址简写内容过长
	 * 
	 * @return
	 */
	public static String USER_ADDRESS_NAME_TOO_LONG() {
		return MultiLanguageAdapter.fetchMessage(USER_ADDRESS_NAME_TOO_LONG);
	}

	// --
	/**
	 * 地址详情内容过长
	 */
	public static final String USER_ADDRESS_DETAIL_TOO_LONG = "user.address.save.address.detail.length.max";

	/**
	 * 地址详情内容过长
	 * 
	 * @return
	 */
	public static String USER_ADDRESS_DETAIL_TOO_LONG() {
		return MultiLanguageAdapter.fetchMessage(USER_ADDRESS_DETAIL_TOO_LONG);
	}

	// --
	/**
	 * 收货人内容过长
	 */
	public static final String USER_ADDRESS_CONTACTS_TOO_LONG = "user.address.save.address.contacts.length.max";

	/**
	 * 收货人内容过长
	 * 
	 * @return
	 */
	public static String USER_ADDRESS_CONTACTS_TOO_LONG() {
		return MultiLanguageAdapter.fetchMessage(USER_ADDRESS_CONTACTS_TOO_LONG);
	}

	// --
	/**
	 * 联系电话内容过长
	 */
	public static final String USER_ADDRESS_PHONE_TOO_LONG = "user.address.save.address.phone.length.max";

	/**
	 * 联系电话内容过长
	 * 
	 * @return
	 */
	public static String USER_ADDRESS_PHONE_TOO_LONG() {
		return MultiLanguageAdapter.fetchMessage(USER_ADDRESS_PHONE_TOO_LONG);
	}

	// --
	/**
	 * 备注内容过长
	 */
	public static final String USER_ADDRESS_REMARK_TOO_LONG = "user.address.save.address.remark.length.max";

	/**
	 * 备注内容过长
	 * 
	 * @return
	 */
	public static String USER_ADDRESS_REMARK_TOO_LONG() {
		return MultiLanguageAdapter.fetchMessage(USER_ADDRESS_REMARK_TOO_LONG);
	}

	// --
	/**
	 * 地址全称过长
	 */
	public static final String USER_ADDRESS_FULL_ADDRESS_TOO_LONG = "user.address.save.address.fullAddress.length.max";

	/**
	 * 地址全称过长
	 * 
	 * @return
	 */
	public static String USER_ADDRESS_FULL_ADDRESS_TOO_LONG() {
		return MultiLanguageAdapter.fetchMessage(USER_ADDRESS_FULL_ADDRESS_TOO_LONG);
	}

	// --
	/**
	 * 送餐地址简写中不能有特殊字符
	 */
	public static final String USER_ADDRESS_NAME_EMOJI_ERROR = "user.address.save.address.name.emoji";

	/**
	 * 送餐地址简写中不能有特殊字符
	 * 
	 * @return
	 */
	public static String USER_ADDRESS_NAME_EMOJI_ERROR() {
		return MultiLanguageAdapter.fetchMessage(USER_ADDRESS_NAME_EMOJI_ERROR);
	}

	// --
	/**
	 * 地址详情中不能有特殊字符
	 */
	public static final String USER_ADDRESS_DETAIL_EMOJI_ERROR = "user.address.save.address.detail.emoji";

	/**
	 * 地址详情中不能有特殊字符
	 * 
	 * @return
	 */
	public static String USER_ADDRESS_DETAIL_EMOJI_ERROR() {
		return MultiLanguageAdapter.fetchMessage(USER_ADDRESS_DETAIL_EMOJI_ERROR);
	}

	// --
	/**
	 * 送餐地址全称中不能有特殊字符
	 */
	public static final String USER_ADDRESS_FULL_ADDRESS_EMOJI_ERROR = "user.address.save.address.fulladdress.emoji";

	/**
	 * 送餐地址全称中不能有特殊字符
	 * 
	 * @return
	 */
	public static String USER_ADDRESS_FULL_ADDRESS_EMOJI_ERROR() {
		return MultiLanguageAdapter.fetchMessage(USER_ADDRESS_FULL_ADDRESS_EMOJI_ERROR);
	}

	// --
	/**
	 * 收货人名中不能有特殊字符
	 */
	public static final String USER_ADDRESS_CONTACTS_EMOJI_ERROR = "user.address.save.address.contacts.emoji";

	/**
	 * 收货人名中不能有特殊字符
	 * 
	 * @return
	 */
	public static String USER_ADDRESS_CONTACTS_EMOJI_ERROR() {
		return MultiLanguageAdapter.fetchMessage(USER_ADDRESS_CONTACTS_EMOJI_ERROR);
	}

	// --
	/**
	 * 备注中不能有特殊字符
	 */
	public static final String USER_ADDRESS_REMARK_EMOJI_ERROR = "user.address.save.address.remark.emoji";

	/**
	 * 备注中不能有特殊字符
	 * 
	 * @return
	 */
	public static String USER_ADDRESS_REMARK_EMOJI_ERROR() {
		return MultiLanguageAdapter.fetchMessage(USER_ADDRESS_REMARK_EMOJI_ERROR);
	}

	// --
	/**
	 * 建议使用6个中文或12个英文以内的名字哦
	 */
	public static final String USER_ADDRESS_CONTACTS_LENGTH_ILLEGAL = "user.address.save.address.contacts.length";

	/**
	 * 建议使用6个中文或12个英文以内的名字哦
	 * 
	 * @return
	 */
	public static String USER_ADDRESS_CONTACTS_LENGTH_ILLEGAL() {
		return MultiLanguageAdapter.fetchMessage(USER_ADDRESS_CONTACTS_LENGTH_ILLEGAL);
	}

	// --
	/**
	 * 反馈内容不能为空
	 */
	public static final String USER_FEEDBACK_CONTENT_CAN_NOT_BE_EMPTY = "user.feedback.save.content.empty";

	/**
	 * 反馈内容不能为空
	 * 
	 * @return
	 */
	public static String USER_FEEDBACK_CONTENT_CAN_NOT_BE_EMPTY() {
		return MultiLanguageAdapter.fetchMessage(USER_FEEDBACK_CONTENT_CAN_NOT_BE_EMPTY);
	}

	// --
	/**
	 * 反馈内容过长
	 */
	public static final String USER_FEEDBACK_CONTENT_TOO_LONG = "user.feedback.save.content.length.max";

	/**
	 * 反馈内容过长
	 * 
	 * @return
	 */
	public static String USER_FEEDBACK_CONTENT_TOO_LONG() {
		return MultiLanguageAdapter.fetchMessage(USER_FEEDBACK_CONTENT_TOO_LONG);
	}

	// --
	/**
	 * 反馈的内容中不能含有特殊字符
	 */
	public static final String USER_FEEDBACK_CONTENT_EMOJI_ERROR = "user.feedback.save.content.emoji";

	/**
	 * 反馈的内容中不能含有特殊字符
	 * 
	 * @return
	 */
	public static String USER_FEEDBACK_CONTENT_EMOJI_ERROR() {
		return MultiLanguageAdapter.fetchMessage(USER_FEEDBACK_CONTENT_EMOJI_ERROR);
	}

	// --
	/**
	 * countryCode must not be empty
	 */
	public static final String USER_LOGIN_COUNTRY_CODE_CAN_NOT_BE_EMPTY = "user.login.login.countryCode.empty";

	/**
	 * countryCode must not be empty
	 * 
	 * @return
	 */
	public static String USER_LOGIN_COUNTRY_CODE_CAN_NOT_BE_EMPTY() {
		return MultiLanguageAdapter.fetchMessage(USER_LOGIN_COUNTRY_CODE_CAN_NOT_BE_EMPTY);
	}

	// --
	/**
	 * 请输入手机号码
	 */
	public static final String USER_LOGIN_MOBILE_CAN_NOT_BE_EMPTY = "user.login.login.mobile.empty";

	/**
	 * 请输入手机号码
	 * 
	 * @return
	 */
	public static String USER_LOGIN_MOBILE_CAN_NOT_BE_EMPTY() {
		return MultiLanguageAdapter.fetchMessage(USER_LOGIN_MOBILE_CAN_NOT_BE_EMPTY);
	}

	// --
	/**
	 * 请输入登录密码
	 */
	public static final String USER_LOGIN_PASSWORD_CAN_NOT_BE_EMPTY = "user.login.login.password.empty";

	/**
	 * 请输入登录密码
	 * 
	 * @return
	 */
	public static String USER_LOGIN_PASSWORD_CAN_NOT_BE_EMPTY() {
		return MultiLanguageAdapter.fetchMessage(USER_LOGIN_PASSWORD_CAN_NOT_BE_EMPTY);
	}

	// --
	/**
	 * 验证码不正确，检查看看吧
	 */
	public static final String USER_LOGIN_IDCODE_INVALID = "user.login.loginNoPwd.idcode.error";

	/**
	 * 验证码不正确，检查看看吧
	 * 
	 * @return
	 */
	public static String USER_LOGIN_IDCODE_INVALID() {
		return MultiLanguageAdapter.fetchMessage(USER_LOGIN_IDCODE_INVALID);
	}

	// --
	/**
	 * 用户帐号或密码错误
	 */
	public static final String USER_LOGIN_NEED_PW_USER_NOT_EXIST = "user.login.dologin.needpw.user.notexist";

	/**
	 * 用户帐号或密码错误
	 * 
	 * @return
	 */
	public static String USER_LOGIN_NEED_PW_USER_NOT_EXIST() {
		return MultiLanguageAdapter.fetchMessage(USER_LOGIN_NEED_PW_USER_NOT_EXIST);
	}

	// --
	/**
	 * 用户帐号错误
	 */
	public static final String USER_LOGIN_NO_PW_USER_NOT_EXIST = "user.login.dologin.nopw.user.notexist";

	/**
	 * 用户帐号错误
	 * 
	 * @return
	 */
	public static String USER_LOGIN_NO_PW_USER_NOT_EXIST() {
		return MultiLanguageAdapter.fetchMessage(USER_LOGIN_NO_PW_USER_NOT_EXIST);
	}

	// --
	/**
	 * 请输入验证码
	 */
	public static final String USER_LOGIN_IDCODE_CAN_NOT_BE_EMPTY = "user.login.idcode.empty";

	/**
	 * 请输入验证码
	 * 
	 * @return
	 */
	public static String USER_LOGIN_IDCODE_CAN_NOT_BE_EMPTY() {
		return MultiLanguageAdapter.fetchMessage(USER_LOGIN_IDCODE_CAN_NOT_BE_EMPTY);
	}

	// --
	/**
	 * 国家代码不能为空
	 */
	public static final String USER_LOGIN_COUNTRY_CODE_CAN_NOT_BE_EMPTY2 = "user.login.countrycode.empty";

	/**
	 * 国家代码不能为空
	 * 
	 * @return
	 */
	public static String USER_LOGIN_COUNTRY_CODE_CAN_NOT_BE_EMPTY2() {
		return MultiLanguageAdapter.fetchMessage(USER_LOGIN_COUNTRY_CODE_CAN_NOT_BE_EMPTY2);
	}

	// --
	/**
	 * 不是合法的邮箱
	 */
	public static final String USER_LOGIN_EMAIL_ILLEGAL = "user.login.email.illegal";

	/**
	 * 不是合法的邮箱
	 * 
	 * @return
	 */
	public static String USER_LOGIN_EMAIL_ILLEGAL() {
		return MultiLanguageAdapter.fetchMessage(USER_LOGIN_EMAIL_ILLEGAL);
	}

	// --
	/**
	 * 帐号不能为空
	 */
	public static final String USER_LOGIN_PASSPORT_CAN_NOT_BE_EMPTY = "user.login.passport.empty";

	/**
	 * 帐号不能为空
	 * 
	 * @return
	 */
	public static String USER_LOGIN_PASSPORT_CAN_NOT_BE_EMPTY() {
		return MultiLanguageAdapter.fetchMessage(USER_LOGIN_PASSPORT_CAN_NOT_BE_EMPTY);
	}

	// --
	/**
	 * 昵称不能为空
	 */
	public static final String USER_LOGIN_NICKNAME_CAN_NOT_BE_EMPTY = "user.login.nickname.empty";

	/**
	 * 昵称不能为空
	 * 
	 * @return
	 */
	public static String USER_LOGIN_NICKNAME_CAN_NOT_BE_EMPTY() {
		return MultiLanguageAdapter.fetchMessage(USER_LOGIN_NICKNAME_CAN_NOT_BE_EMPTY);
	}

	// --
	/**
	 * 手机号码不正确,请重新输入
	 */
	public static final String USER_LOGIN_SMSCODE_RECEIVER_INVALID = "user.login.getSmsCode.receiver.error";

	/**
	 * 手机号码不正确,请重新输入
	 * 
	 * @return
	 */
	public static String USER_LOGIN_SMSCODE_RECEIVER_INVALID() {
		return MultiLanguageAdapter.fetchMessage(USER_LOGIN_SMSCODE_RECEIVER_INVALID);
	}

	// --
	/**
	 * 发送短信验证码失败
	 */
	public static final String USER_LOGIN_SMSCODE_SEND_FAILURE = "user.login.getSmsCode.failure";

	/**
	 * 发送短信验证码失败
	 * 
	 * @return
	 */
	public static String USER_LOGIN_SMSCODE_SEND_FAILURE() {
		return MultiLanguageAdapter.fetchMessage(USER_LOGIN_SMSCODE_SEND_FAILURE);
	}

	// --
	/**
	 * 发送短信验证码成功
	 */
	public static final String USER_LOGIN_SMSCODE_SEND_SUCCESS = "user.login.getSmsCode.success";

	/**
	 * 发送短信验证码成功
	 * 
	 * @return
	 */
	public static String USER_LOGIN_SMSCODE_SEND_SUCCESS() {
		return MultiLanguageAdapter.fetchMessage(USER_LOGIN_SMSCODE_SEND_SUCCESS);
	}

	// --
	/**
	 * 密码不能为空
	 */
	public static final String USER_SERVICE_DOLOGIN_PASSWORD_CAN_NOT_BE_EMPTY = "user.userService.doLogin.password.empty";

	/**
	 * 密码不能为空
	 * 
	 * @return
	 */
	public static String USER_SERVICE_DOLOGIN_PASSWORD_CAN_NOT_BE_EMPTY() {
		return MultiLanguageAdapter.fetchMessage(USER_SERVICE_DOLOGIN_PASSWORD_CAN_NOT_BE_EMPTY);
	}

	// --
	/**
	 * 手机号不能为空
	 */
	public static final String USER_PASSWORD_RESET_MOBILE_CAN_NOT_BE_EMPTY = "user.password.reset.mobile.empty";

	/**
	 * 手机号不能为空
	 * 
	 * @return
	 */
	public static String USER_PASSWORD_RESET_MOBILE_CAN_NOT_BE_EMPTY() {
		return MultiLanguageAdapter.fetchMessage(USER_PASSWORD_RESET_MOBILE_CAN_NOT_BE_EMPTY);
	}

	// --
	/**
	 * 密码不能为空
	 */
	public static final String USER_PASSWORD_RESET_PW_CAN_NOT_BE_EMPTY = "user.password.reset.password.empty";

	/**
	 * 密码不能为空
	 * 
	 * @return
	 */
	public static String USER_PASSWORD_RESET_PW_CAN_NOT_BE_EMPTY() {
		return MultiLanguageAdapter.fetchMessage(USER_PASSWORD_RESET_PW_CAN_NOT_BE_EMPTY);
	}

	// --
	/**
	 * 手机号码错误
	 */
	public static final String USER_PASSWORD_RESET_MOBILE_INVALID = "user.password.reset.mobile.error";

	/**
	 * 手机号码错误
	 * 
	 * @return
	 */
	public static String USER_PASSWORD_RESET_MOBILE_INVALID() {
		return MultiLanguageAdapter.fetchMessage(USER_PASSWORD_RESET_MOBILE_INVALID);
	}

	// --
	/**
	 * 密码错误
	 */
	public static final String USER_PASSWORD_RESET_PASSWORD_INVALID = "user.password.reset.password.error";

	/**
	 * 密码错误
	 * 
	 * @return
	 */
	public static String USER_PASSWORD_RESET_PASSWORD_INVALID() {
		return MultiLanguageAdapter.fetchMessage(USER_PASSWORD_RESET_PASSWORD_INVALID);
	}

	// --
	/**
	 * 验证码错误
	 */
	public static final String USER_PASSWORD_RESET_IDCODE_INVALID = "user.password.reset.idcode.error";

	/**
	 * 验证码错误
	 * 
	 * @return
	 */
	public static String USER_PASSWORD_RESET_IDCODE_INVALID() {
		return MultiLanguageAdapter.fetchMessage(USER_PASSWORD_RESET_IDCODE_INVALID);
	}

	// --
	/**
	 * 旧密码不能为空
	 */
	public static final String USER_PASSWORD_UPDATE_OLD_PASSWORD_CAN_NOT_BE_EMPTY = "user.password.update.oldpassword.empty";

	/**
	 * 旧密码不能为空
	 * 
	 * @return
	 */
	public static String USER_PASSWORD_UPDATE_OLD_PASSWORD_CAN_NOT_BE_EMPTY() {
		return MultiLanguageAdapter.fetchMessage(USER_PASSWORD_UPDATE_OLD_PASSWORD_CAN_NOT_BE_EMPTY);
	}

	// --
	/**
	 * 密码长度不能少于8位
	 */
	public static final String USER_PASSWORD_TOO_SHORT = "user.pw.length.min";

	/**
	 * 密码长度不能少于8位
	 * 
	 * @return
	 */
	public static String USER_PASSWORD_TOO_SHORT() {
		return MultiLanguageAdapter.fetchMessage(USER_PASSWORD_TOO_SHORT);
	}

	// --
	/**
	 * 密码长度不能大于20位
	 */
	public static final String USER_PASSWORD_TOO_LONG = "user.pw.length.max";

	/**
	 * 密码长度不能大于20位
	 * 
	 * @return
	 */
	public static String USER_PASSWORD_TOO_LONG() {
		return MultiLanguageAdapter.fetchMessage(USER_PASSWORD_TOO_LONG);
	}

	// --
	/**
	 * 您的密码过于简单，请重新设置，建议使用不同的字符组合
	 */
	public static final String USER_PASSWORD_TOO_SIMPLE = "user.pw.simple";

	/**
	 * 您的密码过于简单，请重新设置，建议使用不同的字符组合
	 * 
	 * @return
	 */
	public static String USER_PASSWORD_TOO_SIMPLE() {
		return MultiLanguageAdapter.fetchMessage(USER_PASSWORD_TOO_SIMPLE);
	}

	// --
	/**
	 * 昵称中不能有特殊字符
	 */
	public static final String USER_REGISTER_NICKNAME_EMOJI_ERROR = "user.register.reg.nickname.emoji";

	/**
	 * 昵称中不能有特殊字符
	 * 
	 * @return
	 */
	public static String USER_REGISTER_NICKNAME_EMOJI_ERROR() {
		return MultiLanguageAdapter.fetchMessage(USER_REGISTER_NICKNAME_EMOJI_ERROR);
	}

	// --
	/**
	 * 获取用户信息失败
	 */
	public static final String USER_IM_GET_USERINFO_FAILURE = "user.im.getuserinfo.failure";

	/**
	 * 获取用户信息失败
	 * 
	 * @return
	 */
	public static String USER_IM_GET_USERINFO_FAILURE() {
		return MultiLanguageAdapter.fetchMessage(USER_IM_GET_USERINFO_FAILURE);
	}

	// --
	/**
	 * 设备标识长度过长
	 */
	public static final String USER_UPLOADINFO_DEVICETOKEN_TOO_LONG = "user.user.uploadInfo.deviceToken.length.max";

	/**
	 * 设备标识长度过长
	 * 
	 * @return
	 */
	public static String USER_UPLOADINFO_DEVICETOKEN_TOO_LONG() {
		return MultiLanguageAdapter.fetchMessage(USER_UPLOADINFO_DEVICETOKEN_TOO_LONG);
	}

	// --
	/**
	 * 渠道号长度过长
	 */
	public static final String USER_UPLOADINFO_CHANNEL_TOO_LONG = "user.user.uploadInfo.channel.length.max";

	/**
	 * 渠道号长度过长
	 * 
	 * @return
	 */
	public static String USER_UPLOADINFO_CHANNEL_TOO_LONG() {
		return MultiLanguageAdapter.fetchMessage(USER_UPLOADINFO_CHANNEL_TOO_LONG);
	}

	// --
	/**
	 * 手机号或密码错误
	 */
	public static final String USER_GET_FAILURE = "user.user.get.failure";

	/**
	 * 手机号或密码错误
	 * 
	 * @return
	 */
	public static String USER_GET_FAILURE() {
		return MultiLanguageAdapter.fetchMessage(USER_GET_FAILURE);
	}

	// --
	/**
	 * 昵称中不能含有特殊字符
	 */
	public static final String USER_UPDATE_NICKNAME_EMOJI_ERROR = "user.user.update.nickname.emoji";

	/**
	 * 昵称中不能含有特殊字符
	 * 
	 * @return
	 */
	public static String USER_UPDATE_NICKNAME_EMOJI_ERROR() {
		return MultiLanguageAdapter.fetchMessage(USER_UPDATE_NICKNAME_EMOJI_ERROR);
	}

	// --
	/**
	 * 头像图片太大，请重试
	 */
	public static final String USER_CHANGEPIC_PIC_OVER_SIZE = "user.user.changePic.pic.length.max";

	/**
	 * 头像图片太大，请重试
	 * 
	 * @return
	 */
	public static String USER_CHANGEPIC_PIC_OVER_SIZE() {
		return MultiLanguageAdapter.fetchMessage(USER_CHANGEPIC_PIC_OVER_SIZE);
	}

	// --
	/**
	 * 头像图片大小为0，请重试
	 */
	public static final String USER_CHANGEPIC_PIC_SIZE_ZERO = "user.user.changePic.pic.length.0";

	/**
	 * 头像图片大小为0，请重试
	 * 
	 * @return
	 */
	public static String USER_CHANGEPIC_PIC_SIZE_ZERO() {
		return MultiLanguageAdapter.fetchMessage(USER_CHANGEPIC_PIC_SIZE_ZERO);
	}

	// --
	/**
	 * 参数错误
	 */
	public static final String USER_SET_PUSH_PARAM_ERROR = "user.user.setPush.param.error";

	/**
	 * 参数错误
	 * 
	 * @return
	 */
	public static String USER_SET_PUSH_PARAM_ERROR() {
		return MultiLanguageAdapter.fetchMessage(USER_SET_PUSH_PARAM_ERROR);
	}

	// --
	/**
	 * 更新失败,请重试
	 */
	public static final String USER_SET_PUSH_FAILURE = "user.user.setPush.failure";

	/**
	 * 更新失败,请重试
	 * 
	 * @return
	 */
	public static String USER_SET_PUSH_FAILURE() {
		return MultiLanguageAdapter.fetchMessage(USER_SET_PUSH_FAILURE);
	}

	// --
	/**
	 * 更新失败
	 */
	public static final String USER_UPLOAD_INFO_FAILURE = "user.user.uploadInfo.failure";

	/**
	 * 更新失败
	 * 
	 * @return
	 */
	public static String USER_UPLOAD_INFO_FAILURE() {
		return MultiLanguageAdapter.fetchMessage(USER_UPLOAD_INFO_FAILURE);
	}

	// --
	/**
	 * 读取图片数据失败：{0}
	 */
	public static final String USER_UPLOAD_PIC_ERROR = "user.upload.pic.error";

	/**
	 * 读取图片数据失败：{0}
	 * 
	 * @return
	 */
	public static String USER_UPLOAD_PIC_ERROR(String msg) {
		return MultiLanguageAdapter.fetchMessage(USER_UPLOAD_PIC_ERROR, msg);
	}

	// --
	/**
	 * 未知的图片类别！
	 */
	public static final String USER_UPLOAD_PIC_TYPE_ILLEGAL = "user.upload.pic.type.illegal";

	/**
	 * 未知的图片类别！
	 * 
	 * @return
	 */
	public static String USER_UPLOAD_PIC_TYPE_ILLEGAL() {
		return MultiLanguageAdapter.fetchMessage(USER_UPLOAD_PIC_TYPE_ILLEGAL);
	}

	// --
	/**
	 * 设备名错误
	 */
	public static final String USER_TERMINAL_PROFILE_UPLOAD_IOSTOKEN_IOSTYPE_ERROR = "user.terminalProfile.uploadIosToken.iosType.error";

	/**
	 * 设备名错误
	 * 
	 * @return
	 */
	public static String USER_TERMINAL_PROFILE_UPLOAD_IOSTOKEN_IOSTYPE_ERROR() {
		return MultiLanguageAdapter.fetchMessage(USER_TERMINAL_PROFILE_UPLOAD_IOSTOKEN_IOSTYPE_ERROR);
	}

	// --
	/**
	 * 设备ID不能为空
	 */
	public static final String USER_TERMINAL_PROFILE_UPLOAD_IOSTOKEN_CAN_NOT_BE_EMPTY = "user.terminalProfile.uploadIosToken.iosToken.empty";

	/**
	 * 设备ID不能为空
	 * 
	 * @return
	 */
	public static String USER_TERMINAL_PROFILE_UPLOAD_IOSTOKEN_CAN_NOT_BE_EMPTY() {
		return MultiLanguageAdapter.fetchMessage(USER_TERMINAL_PROFILE_UPLOAD_IOSTOKEN_CAN_NOT_BE_EMPTY);
	}

	// --
	/**
	 * 推送的参数不能为空
	 */
	public static final String USER_PUSH_PARAMS_CAN_NOT_BE_EMPTY = "user.localPushApi.push.params.empty";

	/**
	 * 推送的参数不能为空
	 * 
	 * @return
	 */
	public static String USER_PUSH_PARAMS_CAN_NOT_BE_EMPTY() {
		return MultiLanguageAdapter.fetchMessage(USER_PUSH_PARAMS_CAN_NOT_BE_EMPTY);
	}

	// --
	/**
	 * 推送的消息内容不能为空
	 */
	public static final String USER_PUSH_PARAMS_MSG_CAN_NOT_BE_EMPTY = "user.localPushApi.push.params.msg.empty";

	/**
	 * 推送的消息内容不能为空
	 * 
	 * @return
	 */
	public static String USER_PUSH_PARAMS_MSG_CAN_NOT_BE_EMPTY() {
		return MultiLanguageAdapter.fetchMessage(USER_PUSH_PARAMS_MSG_CAN_NOT_BE_EMPTY);
	}

	// --
	/**
	 * 被推送的用户ID不能为空
	 */
	public static final String USER_PUSH_PARAMS_UID_CAN_NOT_BE_EMPTY = "user.localPushApi.push.params.uid.empty";

	/**
	 * 被推送的用户ID不能为空
	 * 
	 * @return
	 */
	public static String USER_PUSH_PARAMS_UID_CAN_NOT_BE_EMPTY() {
		return MultiLanguageAdapter.fetchMessage(USER_PUSH_PARAMS_UID_CAN_NOT_BE_EMPTY);
	}

	// --
	/**
	 * 管理员名称不能为空
	 */
	public static final String USER_PUSH_TO_ALL_ADMINNAME_CAN_NOT_BE_EMPTY = "user.localPushApi.pushToAll.adminName.empty";

	/**
	 * 管理员名称不能为空
	 * 
	 * @return
	 */
	public static String USER_PUSH_TO_ALL_ADMINNAME_CAN_NOT_BE_EMPTY() {
		return MultiLanguageAdapter.fetchMessage(USER_PUSH_TO_ALL_ADMINNAME_CAN_NOT_BE_EMPTY);
	}

	// --
	/**
	 * 标题不能为空
	 */
	public static final String USER_PUSH_TO_ALL_TITLE_CAN_NOT_BE_EMPTY = "user.localPushApi.pushToAll.title.empty";

	/**
	 * 标题不能为空
	 * 
	 * @return
	 */
	public static String USER_PUSH_TO_ALL_TITLE_CAN_NOT_BE_EMPTY() {
		return MultiLanguageAdapter.fetchMessage(USER_PUSH_TO_ALL_TITLE_CAN_NOT_BE_EMPTY);
	}

	// --
	/**
	 * 消息不能为空
	 */
	public static final String USER_PUSH_TO_ALL_MSG_CAN_NOT_BE_EMPTY = "user.localPushApi.pushToAll.message.empty";

	/**
	 * 消息不能为空
	 * 
	 * @return
	 */
	public static String USER_PUSH_TO_ALL_MSG_CAN_NOT_BE_EMPTY() {
		return MultiLanguageAdapter.fetchMessage(USER_PUSH_TO_ALL_MSG_CAN_NOT_BE_EMPTY);
	}

	// --
	/**
	 * 正在发送，是否成功送达，以实际效果为准
	 */
	public static final String USER_PUSH_TO_ALL_SUCCESS = "user.localPushApi.pushToAll.success";

	/**
	 * 正在发送，是否成功送达，以实际效果为准
	 * 
	 * @return
	 */
	public static String USER_PUSH_TO_ALL_SUCCESS() {
		return MultiLanguageAdapter.fetchMessage(USER_PUSH_TO_ALL_SUCCESS);
	}

	// --
	/**
	 * 标题超过了15个字符或内容超过了{0}个字符
	 */
	public static final String USER_PUSH_TO_ALL_TITLE_TOO_LONG = "user.localPushApi.pushToAll.title.length.max";

	/**
	 * 标题超过了15个字符或内容超过了{0}个字符
	 * 
	 * @return
	 */
	public static String USER_PUSH_TO_ALL_TITLE_TOO_LONG(String limit) {
		return MultiLanguageAdapter.fetchMessage(USER_PUSH_TO_ALL_TITLE_TOO_LONG, limit);
	}

	// --
	/**
	 * 管理员名称不能为空
	 */
	public static final String USER_SMS_TO_ALL_ADMINNAME_CAN_NOT_BE_EMPTY = "user.localPushApi.sendSmsToAll.adminName.empty";

	/**
	 * 管理员名称不能为空
	 * 
	 * @return
	 */
	public static String USER_SMS_TO_ALL_ADMINNAME_CAN_NOT_BE_EMPTY() {
		return MultiLanguageAdapter.fetchMessage(USER_SMS_TO_ALL_ADMINNAME_CAN_NOT_BE_EMPTY);
	}

	/**
	 * 模板ID不能为空
	 */
	public static final String USER_SMS_TO_ALL_TEMPLATEID_CAN_NOT_BE_EMPTY = "user.localPushApi.sendSmsToAll.templateId.empty";

	/**
	 * 模板ID不能为空
	 */
	public static String USER_SMS_TO_ALL_TEMPLATEID_CAN_NOT_BE_EMPTY() {
		return MultiLanguageAdapter.fetchMessage(USER_SMS_TO_ALL_TEMPLATEID_CAN_NOT_BE_EMPTY);
	}

	/**
	 * 系统没有配置短信模板：{0}
	 */
	public static final String USER_SMS_TO_ALL_CONFIG_CAN_NOT_BE_EMPTY = "user.localPushApi.sendSmsToAll.config.empty";

	/**
	 * 系统没有配置短信模板：{0}
	 */
	public static String USER_SMS_TO_ALL_CONFIG_CAN_NOT_BE_EMPTY(String template) {
		return MultiLanguageAdapter.fetchMessage(USER_SMS_TO_ALL_CONFIG_CAN_NOT_BE_EMPTY, template);
	}

	/**
	 * 正在发送短信，是否成功送达，以实际效果为准
	 */
	public static final String USER_SMS_TO_ALL_SUCCESS = "user.localPushApi.sendSmsToAll.success";

	/**
	 * 正在发送短信，是否成功送达，以实际效果为准
	 */
	public static String USER_SMS_TO_ALL_SUCCESS() {
		return MultiLanguageAdapter.fetchMessage(USER_SMS_TO_ALL_SUCCESS);
	}

	/**
	 * 用户ID不合法
	 */
	public static final String USER_GET_USER_ACCOUNT_UID_INVALID = "user.localUserAccountApi.getUserAccount.uid.error";

	/**
	 * 用户ID不合法
	 */
	public static String USER_GET_USER_ACCOUNT_UID_INVALID() {
		return MultiLanguageAdapter.fetchMessage(USER_GET_USER_ACCOUNT_UID_INVALID);
	}

	/**
	 * 邮箱不能为空
	 */
	public static final String USER_BIND_EMAIL_EMAIL_CAN_NOT_BE_EMPTY = "user.localUserAccountApi.bindEmail.email.empty";

	/**
	 * 邮箱不能为空
	 */
	public static String USER_BIND_EMAIL_EMAIL_CAN_NOT_BE_EMPTY() {
		return MultiLanguageAdapter.fetchMessage(USER_BIND_EMAIL_EMAIL_CAN_NOT_BE_EMPTY);
	}

	/**
	 * 用户不存在
	 */
	public static final String USER_BIND_EMAIL_EMAIL_USER_NOT_EXIST = "user.localUserAccountApi.bindEmail.user.empty";

	/**
	 * 用户不存在
	 */
	public static String USER_BIND_EMAIL_EMAIL_USER_NOT_EXIST() {
		return MultiLanguageAdapter.fetchMessage(USER_BIND_EMAIL_EMAIL_USER_NOT_EXIST);
	}

	/**
	 * 提现账号不能为空
	 */
	public static final String USER_BIND_ACCOUNT_ACCOUNT_NO_CAN_NOT_BE_EMPTY = "user.localUserAccountApi.bindAccount.accountNo.empty";

	/**
	 * 提现账号不能为空
	 */
	public static String USER_BIND_ACCOUNT_ACCOUNT_NO_CAN_NOT_BE_EMPTY() {
		return MultiLanguageAdapter.fetchMessage(USER_BIND_ACCOUNT_ACCOUNT_NO_CAN_NOT_BE_EMPTY);
	}

	/**
	 * 账户所有者姓名不能为空
	 */
	public static final String USER_BIND_ACCOUNT_ACCOUNT_NAME_CAN_NOT_BE_EMPTY = "user.localUserAccountApi.bindAccount.accountName.empty";

	/**
	 * 账户所有者姓名不能为空
	 */
	public static String USER_BIND_ACCOUNT_ACCOUNT_NAME_CAN_NOT_BE_EMPTY() {
		return MultiLanguageAdapter.fetchMessage(USER_BIND_ACCOUNT_ACCOUNT_NAME_CAN_NOT_BE_EMPTY);
	}

	/**
	 * 系统数据错误，帐号不存在，请修复数据
	 */
	public static final String USER_GET_USER_PROFILE_ERROR = "user.localUserApi.getUserProfile.error";

	/**
	 * 系统数据错误，帐号不存在，请修复数据
	 */
	public static String USER_GET_USER_PROFILE_ERROR() {
		return MultiLanguageAdapter.fetchMessage(USER_GET_USER_PROFILE_ERROR);
	}

	/**
	 * 获取用户信息失败
	 */
	public static final String USER_GET_USER_PROFILE_USER_NOT_EXIST = "user.localUserApi.getUserProfile.user.empty";

	/**
	 * 获取用户信息失败
	 */
	public static String USER_GET_USER_PROFILE_USER_NOT_EXIST() {
		return MultiLanguageAdapter.fetchMessage(USER_GET_USER_PROFILE_USER_NOT_EXIST);
	}

	/**
	 * 帐号长度为4~20个字符
	 */
	public static final String USER_CREATE_USER_PASSPORT_LENGTH_INVALID = "user.localUserApi.createUser.passport.length.max";

	/**
	 * 帐号长度为4~20个字符
	 */
	public static String USER_CREATE_USER_PASSPORT_LENGTH_INVALID() {
		return MultiLanguageAdapter.fetchMessage(USER_CREATE_USER_PASSPORT_LENGTH_INVALID);
	}

	/**
	 * 昵称长度为2~30个字符
	 */
	public static final String USER_CREATE_USER_NICKNAME_LENGTH_INVALID = "user.localUserApi.createUser.nickname.length.max";

	/**
	 * 昵称长度为2~30个字符
	 */
	public static String USER_CREATE_USER_NICKNAME_LENGTH_INVALID() {
		return MultiLanguageAdapter.fetchMessage(USER_CREATE_USER_NICKNAME_LENGTH_INVALID);
	}

	/**
	 * 密码长度为8~16个字符
	 */
	public static final String USER_CREATE_USER_PASSWORD_LENGTH_INVALID = "user.localUserApi.createUser.password.length.max";

	/**
	 * 密码长度为8~16个字符
	 */
	public static String USER_CREATE_USER_PASSWORD_LENGTH_INVALID() {
		return MultiLanguageAdapter.fetchMessage(USER_CREATE_USER_PASSWORD_LENGTH_INVALID);
	}

	/**
	 * 修改密码成功
	 */
	public static final String USER_CHANGE_PASSWORD_SUCCESS = "user.localUserApi.changePassword.success";

	/**
	 * 修改密码成功
	 */
	public static String USER_CHANGE_PASSWORD_SUCCESS() {
		return MultiLanguageAdapter.fetchMessage(USER_CHANGE_PASSWORD_SUCCESS);
	}

	/**
	 * 消息类型不合法
	 */
	public static final String USER_SITE_MESSAGE_TYPE_ILLEGAL = "user.multiSiteMessage.multiSendCustomer.msg.type.illegal";

	/**
	 * 消息类型不合法
	 */
	public static String USER_SITE_MESSAGE_TYPE_ILLEGAL() {
		return MultiLanguageAdapter.fetchMessage(USER_SITE_MESSAGE_TYPE_ILLEGAL);
	}

	/**
	 * 用户账户不存在
	 */
	public static final String USER_BIND_EMAIL_ACCOUNT_NOT_EXIST = "user.accountSerivce.bindEmail.account.notexist";

	/**
	 * 用户账户不存在
	 */
	public static String USER_BIND_EMAIL_ACCOUNT_NOT_EXIST() {
		return MultiLanguageAdapter.fetchMessage(USER_BIND_EMAIL_ACCOUNT_NOT_EXIST);
	}

	/**
	 * 该账户已经绑定过邮箱
	 */
	public static final String USER_BIND_EMAIL_ACCOUNT_ALREADY_BIND = "user.accountSerivce.bindEmail.account.alreadybind";

	/**
	 * 该账户已经绑定过邮箱
	 */
	public static String USER_BIND_EMAIL_ACCOUNT_ALREADY_BIND() {
		return MultiLanguageAdapter.fetchMessage(USER_BIND_EMAIL_ACCOUNT_ALREADY_BIND);
	}

	/**
	 * 提现帐号不能为空
	 */
	public static final String USER_SERVICE_BIND_ACCOUNT_ACCOUNT_NO_CAN_NOT_BE_EMPTY = "user.accountSerivce.bindAccount.accountNo.empty";

	/**
	 * 提现帐号不能为空
	 */
	public static String USER_SERVICE_BIND_ACCOUNT_ACCOUNT_NO_CAN_NOT_BE_EMPTY() {
		return MultiLanguageAdapter.fetchMessage(USER_SERVICE_BIND_ACCOUNT_ACCOUNT_NO_CAN_NOT_BE_EMPTY);
	}

	/**
	 * 姓名不能为空
	 */
	public static final String USER_SERVICE_BIND_ACCOUNT_ACCOUNT_NAME_CAN_NOT_BE_EMPTY = "user.accountSerivce.bindAccount.accountName.empty";

	/**
	 * 姓名不能为空
	 */
	public static String USER_SERVICE_BIND_ACCOUNT_ACCOUNT_NAME_CAN_NOT_BE_EMPTY() {
		return MultiLanguageAdapter.fetchMessage(USER_SERVICE_BIND_ACCOUNT_ACCOUNT_NAME_CAN_NOT_BE_EMPTY);
	}

	/**
	 * 您已经绑定过账号
	 */
	public static final String USER_SERVICE_BIND_ACCOUNT_ACCOUNT_ALREADY_BIND = "user.accountSerivce.bindAccount.account.alreadybind";

	/**
	 * 您已经绑定过账号
	 */
	public static String USER_SERVICE_BIND_ACCOUNT_ACCOUNT_ALREADY_BIND() {
		return MultiLanguageAdapter.fetchMessage(USER_SERVICE_BIND_ACCOUNT_ACCOUNT_ALREADY_BIND);
	}

	/**
	 * 提现帐号不能为空
	 */
	public static final String USER_SERVICE_MODIFY_ACCOUNT_ACCOUNT_NO_CAN_NOT_BE_EMPTY = "user.accountSerivce.modifyAccount.accountNo.empty";

	/**
	 * 提现帐号不能为空
	 */
	public static String USER_SERVICE_MODIFY_ACCOUNT_ACCOUNT_NO_CAN_NOT_BE_EMPTY() {
		return MultiLanguageAdapter.fetchMessage(USER_SERVICE_MODIFY_ACCOUNT_ACCOUNT_NO_CAN_NOT_BE_EMPTY);
	}

	/**
	 * 姓名不能为空
	 */
	public static final String USER_SERVICE_MODIFY_ACCOUNT_ACCOUNT_NAME_CAN_NOT_BE_EMPTY = "user.accountSerivce.modifyAccount.accountName.empty";

	/**
	 * 姓名不能为空
	 */
	public static String USER_SERVICE_MODIFY_ACCOUNT_ACCOUNT_NAME_CAN_NOT_BE_EMPTY() {
		return MultiLanguageAdapter.fetchMessage(USER_SERVICE_MODIFY_ACCOUNT_ACCOUNT_NAME_CAN_NOT_BE_EMPTY);
	}

	/**
	 * 目前只支持支付宝提现
	 */
	public static final String USER_SERVICE_MODIFY_ACCOUNT_ACCOUNT_TYPE_APIPAY_ONLY = "user.accountSerivce.modifyAccount.accountType.alipay";

	/**
	 * 目前只支持支付宝提现
	 */
	public static String USER_SERVICE_MODIFY_ACCOUNT_ACCOUNT_TYPE_APIPAY_ONLY() {
		return MultiLanguageAdapter.fetchMessage(USER_SERVICE_MODIFY_ACCOUNT_ACCOUNT_TYPE_APIPAY_ONLY);
	}

	/**
	 * 原始文件名不能为空
	 */
	public static final String USER_SAVE_PROFILE_FILENAME_CAN_NOT_BE_EMPTY = "user.profileService.saveProfilePic.filename.empty";

	/**
	 * 原始文件名不能为空
	 */
	public static String USER_SAVE_PROFILE_FILENAME_CAN_NOT_BE_EMPTY() {
		return MultiLanguageAdapter.fetchMessage(USER_SAVE_PROFILE_FILENAME_CAN_NOT_BE_EMPTY);
	}

	/**
	 * 用户ID错误
	 */
	public static final String USER_SAVE_PROFILE_UID_INCORRECT = "user.profileService.saveProfilePic.uid.incorrect";

	/**
	 * 用户ID错误
	 */
	public static String USER_SAVE_PROFILE_UID_INCORRECT() {
		return MultiLanguageAdapter.fetchMessage(USER_SAVE_PROFILE_UID_INCORRECT);
	}

	/**
	 * 商店图片数据错误，请重新上传
	 */
	public static final String USER_SAVE_PROFILE_CONTENT_INVALID = "user.profileService.saveProfilePic.content.error";

	/**
	 * 商店图片数据错误，请重新上传
	 */
	public static String USER_SAVE_PROFILE_CONTENT_INVALID() {
		return MultiLanguageAdapter.fetchMessage(USER_SAVE_PROFILE_CONTENT_INVALID);
	}

	/**
	 * 更新头像失败，请重试
	 */
	public static final String USER_SAVE_PROFILE_ERROR = "user.profileService.saveProfilePic.error";

	/**
	 * 更新头像失败，请重试
	 */
	public static String USER_SAVE_PROFILE_ERROR() {
		return MultiLanguageAdapter.fetchMessage(USER_SAVE_PROFILE_ERROR);
	}

	/**
	 * 请输入昵称！
	 */
	public static final String USER_UPDATE_NICKNAME_NICKNAME_CAN_NOT_BE_EMPTY = "user.profileService.updateNickname.nickname.empty";

	/**
	 * 请输入昵称！
	 */
	public static String USER_UPDATE_NICKNAME_NICKNAME_CAN_NOT_BE_EMPTY() {
		return MultiLanguageAdapter.fetchMessage(USER_UPDATE_NICKNAME_NICKNAME_CAN_NOT_BE_EMPTY);
	}

	/**
	 * 昵称最多只能输入15个中文，或者30个英文！
	 */
	public static final String USER_UPDATE_NICKNAME_NICKNAME_LENGTH_INVALID = "user.profileService.updateNickname.nickname.wordcount";

	/**
	 * 昵称最多只能输入15个中文，或者30个英文！
	 */
	public static String USER_UPDATE_NICKNAME_NICKNAME_LENGTH_INVALID() {
		return MultiLanguageAdapter.fetchMessage(USER_UPDATE_NICKNAME_NICKNAME_LENGTH_INVALID);
	}

	/**
	 * 更新用户昵称失败, 请稍后再试
	 */
	public static final String USER_UPDATE_NICKNAME_ERROR = "user.profileService.updateNickname.error";

	/**
	 * 更新用户昵称失败, 请稍后再试
	 */
	public static String USER_UPDATE_NICKNAME_ERROR() {
		return MultiLanguageAdapter.fetchMessage(USER_UPDATE_NICKNAME_ERROR);
	}

	/**
	 * 请输入简介！
	 */
	public static final String USER_UPDATE_DESCRIPTION_DESCRIPTION_CAN_NOT_BE_EMPTY = "user.profileService.updateDescription.description.empty";

	/**
	 * 请输入简介！
	 */
	public static String USER_UPDATE_DESCRIPTION_DESCRIPTION_CAN_NOT_BE_EMPTY() {
		return MultiLanguageAdapter.fetchMessage(USER_UPDATE_DESCRIPTION_DESCRIPTION_CAN_NOT_BE_EMPTY);
	}

	/**
	 * 简介最多只能输入30个中文，或者60个英文！
	 */
	public static final String USER_UPDATE_DESCRIPTION_DESCRIPTION_LENGTH_INVALID = "user.profileService.updateDescription.description.wordcount";

	/**
	 * 简介最多只能输入30个中文，或者60个英文！
	 */
	public static String USER_UPDATE_DESCRIPTION_DESCRIPTION_LENGTH_INVALID() {
		return MultiLanguageAdapter.fetchMessage(USER_UPDATE_DESCRIPTION_DESCRIPTION_LENGTH_INVALID);
	}

	/**
	 * 更新用户简介失败, 请稍后再试
	 */
	public static final String USER_UPDATE_DESCRIPTION_ERROR = "user.profileService.updateDescription.error";

	/**
	 * 更新用户简介失败, 请稍后再试
	 */
	public static String USER_UPDATE_DESCRIPTION_ERROR() {
		return MultiLanguageAdapter.fetchMessage(USER_UPDATE_DESCRIPTION_ERROR);
	}

	/**
	 * 性别不合法
	 */
	public static final String USER_UPDATE_SEX_SEX_ILLEGAL = "user.profileService.updateUserSex.sex.error";

	/**
	 * 性别不合法
	 */
	public static String USER_UPDATE_SEX_SEX_ILLEGAL() {
		return MultiLanguageAdapter.fetchMessage(USER_UPDATE_SEX_SEX_ILLEGAL);
	}

	/**
	 * 更新用户性别错误, 请稍后再试
	 */
	public static final String USER_UPDATE_SEX_ERROR = "user.profileService.updateUserSex.error";

	/**
	 * 更新用户性别错误, 请稍后再试
	 */
	public static String USER_UPDATE_SEX_ERROR() {
		return MultiLanguageAdapter.fetchMessage(USER_UPDATE_SEX_ERROR);
	}

	/**
	 * Parameter 'user' is empty
	 */
	public static final String USER_REGISTER_PARAM_USER_CAN_NOT_BE_EMPTY = "user.userService.register.user.empty";

	/**
	 * Parameter 'user' is empty
	 */
	public static String USER_REGISTER_PARAM_USER_CAN_NOT_BE_EMPTY() {
		return MultiLanguageAdapter.fetchMessage(USER_REGISTER_PARAM_USER_CAN_NOT_BE_EMPTY);
	}

	/**
	 * 帐号已经存在，请登录。
	 */
	public static final String USER_REGISTER_USER_ALREADY_EXIST = "user.userService.register.user.exist";

	/**
	 * 帐号已经存在，请登录。
	 */
	public static String USER_REGISTER_USER_ALREADY_EXIST() {
		return MultiLanguageAdapter.fetchMessage(USER_REGISTER_USER_ALREADY_EXIST);
	}

	/**
	 * 您的帐号已经被封，请联系管理员
	 */
	public static final String USER_REGISTER_USER_IS_BANNED = "user.userService.register.user.ban";

	/**
	 * 您的帐号已经被封，请联系管理员
	 */
	public static String USER_REGISTER_USER_IS_BANNED() {
		return MultiLanguageAdapter.fetchMessage(USER_REGISTER_USER_IS_BANNED);
	}

	/**
	 * index不能小于1
	 */
	public static final String USER_ANDROID_PROFILE_LIST_ALL_INDEX_LESS_THAN_MIN = "user.androidprofile.listAll.index.min";

	/**
	 * index不能小于1
	 */
	public static String USER_ANDROID_PROFILE_LIST_ALL_INDEX_LESS_THAN_MIN() {
		return MultiLanguageAdapter.fetchMessage(USER_ANDROID_PROFILE_LIST_ALL_INDEX_LESS_THAN_MIN);
	}

	/**
	 * pageSize不能小于1
	 */
	public static final String USER_ANDROID_PROFILE_LIST_ALL_PAGESIZE_LESS_THAN_MIN = "user.androidprofile.listAll.pageSize.min";

	/**
	 * pageSize不能小于1
	 */
	public static String USER_ANDROID_PROFILE_LIST_ALL_PAGESIZE_LESS_THAN_MIN() {
		return MultiLanguageAdapter.fetchMessage(USER_ANDROID_PROFILE_LIST_ALL_PAGESIZE_LESS_THAN_MIN);
	}

	/**
	 * 用户设备标识不能为空
	 */
	public static final String USER_ANDROID_PROFILE_UPLOAD_DID_CAN_NOT_BE_EMPTY = "user.androidprofile.upload.did.empty";

	/**
	 * 用户设备标识不能为空
	 */
	public static String USER_ANDROID_PROFILE_UPLOAD_DID_CAN_NOT_BE_EMPTY() {
		return MultiLanguageAdapter.fetchMessage(USER_ANDROID_PROFILE_UPLOAD_DID_CAN_NOT_BE_EMPTY);
	}

	/**
	 * 用户收货地址不存在
	 */
	public static final String USER_SERVICE_SAVE_ADDRESS_ADDRESS_NOT_EXIST = "user.addressService.save.address.address.not.exist";

	/**
	 * 用户收货地址不存在
	 */
	public static String USER_SERVICE_SAVE_ADDRESS_ADDRESS_NOT_EXIST() {
		return MultiLanguageAdapter.fetchMessage(USER_SERVICE_SAVE_ADDRESS_ADDRESS_NOT_EXIST);
	}

	/**
	 * 地址简写不能为空
	 */
	public static final String USER_SAVE_ADDRESS_ADDRESS_NAME_CAN_NOT_BE_EMPTY = "user.addressService.save.address.name.empty";

	/**
	 * 地址简写不能为空
	 */
	public static String USER_SAVE_ADDRESS_ADDRESS_NAME_CAN_NOT_BE_EMPTY() {
		return MultiLanguageAdapter.fetchMessage(USER_SAVE_ADDRESS_ADDRESS_NAME_CAN_NOT_BE_EMPTY);
	}

	/**
	 * 地址详情不能为空
	 */
	public static final String USER_SAVE_ADDRESS_DETAIL_CAN_NOT_BE_EMPTY = "user.addressService.save.address.detail.empty";

	/**
	 * 地址详情不能为空
	 */
	public static String USER_SAVE_ADDRESS_DETAIL_CAN_NOT_BE_EMPTY() {
		return MultiLanguageAdapter.fetchMessage(USER_SAVE_ADDRESS_DETAIL_CAN_NOT_BE_EMPTY);
	}

	/**
	 * 收货人名称不能为空
	 */
	public static final String USER_SAVE_ADDRESS_CONTACTS_CAN_NOT_BE_EMPTY = "user.addressService.save.address.contacts.empty";

	/**
	 * 收货人名称不能为空
	 */
	public static String USER_SAVE_ADDRESS_CONTACTS_CAN_NOT_BE_EMPTY() {
		return MultiLanguageAdapter.fetchMessage(USER_SAVE_ADDRESS_CONTACTS_CAN_NOT_BE_EMPTY);
	}

	/**
	 * 收货人联系方式不能为空
	 */
	public static final String USER_SAVE_ADDRESS_PHONE_CAN_NOT_BE_EMPTY = "user.addressService.save.address.phone.empty";

	/**
	 * 收货人联系方式不能为空
	 */
	public static String USER_SAVE_ADDRESS_PHONE_CAN_NOT_BE_EMPTY() {
		return MultiLanguageAdapter.fetchMessage(USER_SAVE_ADDRESS_PHONE_CAN_NOT_BE_EMPTY);
	}

	/**
	 * 地址简写太长
	 */
	public static final String USER_SAVE_ADDRESS_NAME_TOO_LONG = "user.addressService.save.address.name.length.max";

	/**
	 * 地址简写太长
	 */
	public static String USER_SAVE_ADDRESS_NAME_TOO_LONG() {
		return MultiLanguageAdapter.fetchMessage(USER_SAVE_ADDRESS_NAME_TOO_LONG);
	}

	/**
	 * 地址详情太长
	 */
	public static final String USER_SAVE_ADDRESS_DETAIL_TOO_LONG = "user.addressService.save.address.detail.length.max";

	/**
	 * 地址详情太长
	 */
	public static String USER_SAVE_ADDRESS_DETAIL_TOO_LONG() {
		return MultiLanguageAdapter.fetchMessage(USER_SAVE_ADDRESS_DETAIL_TOO_LONG);
	}

	/**
	 * 收货人名称太长
	 */
	public static final String USER_SAVE_ADDRESS_CONTACTS_TOO_LONG = "user.addressService.save.address.contacts.length.max";

	/**
	 * 收货人名称太长
	 */
	public static String USER_SAVE_ADDRESS_CONTACTS_TOO_LONG() {
		return MultiLanguageAdapter.fetchMessage(USER_SAVE_ADDRESS_CONTACTS_TOO_LONG);
	}

	/**
	 * 收货人号码太长
	 */
	public static final String USER_SAVE_ADDRESS_PHONE_TOO_LONG = "user.addressService.save.address.phone.length.max";

	/**
	 * 收货人号码太长
	 */
	public static String USER_SAVE_ADDRESS_PHONE_TOO_LONG() {
		return MultiLanguageAdapter.fetchMessage(USER_SAVE_ADDRESS_PHONE_TOO_LONG);
	}

	/**
	 * 备注说明太长
	 */
	public static final String USER_SAVE_ADDRESS_REMARK_TOO_LONG = "user.addressService.save.address.remark.lenght.max";

	/**
	 * 备注说明太长
	 */
	public static String USER_SAVE_ADDRESS_REMARK_TOO_LONG() {
		return MultiLanguageAdapter.fetchMessage(USER_SAVE_ADDRESS_REMARK_TOO_LONG);
	}

	/**
	 * 原始文件名不能为空
	 */
	public static final String USER_FEED_BACK_SERVICE_SAVE_PIC_NAME_CAN_NOT_BE_EMPTY = "user.feedbackPicService.savePic.name.empty";

	/**
	 * 原始文件名不能为空
	 */
	public static String USER_FEED_BACK_SERVICE_SAVE_PIC_NAME_CAN_NOT_BE_EMPTY() {
		return MultiLanguageAdapter.fetchMessage(USER_FEED_BACK_SERVICE_SAVE_PIC_NAME_CAN_NOT_BE_EMPTY);
	}

	/**
	 * 反馈ID错误
	 */
	public static final String USER_FEED_BACK_SERVICE_SAVE_PIC_FEEDBACKID_CAN_NOT_BE_EMPTY = "user.feedbackPicService.savePic.feedbackId.empty";

	/**
	 * 反馈ID错误
	 */
	public static String USER_FEED_BACK_SERVICE_SAVE_PIC_FEEDBACKID_CAN_NOT_BE_EMPTY() {
		return MultiLanguageAdapter.fetchMessage(USER_FEED_BACK_SERVICE_SAVE_PIC_FEEDBACKID_CAN_NOT_BE_EMPTY);
	}

	/**
	 * 反馈图片数据错误，请重新上传
	 */
	public static final String USER_FEED_BACK_SERVICE_SAVE_PIC_CONTENT_CAN_NOT_BE_EMPTY = "user.feedbackPicService.savePic.content.empty";

	/**
	 * 反馈图片数据错误，请重新上传
	 */
	public static String USER_FEED_BACK_SERVICE_SAVE_PIC_CONTENT_CAN_NOT_BE_EMPTY() {
		return MultiLanguageAdapter.fetchMessage(USER_FEED_BACK_SERVICE_SAVE_PIC_CONTENT_CAN_NOT_BE_EMPTY);
	}

	/**
	 * 设备对象不能为空
	 */
	public static final String USER_PROFILE_SERVICE_UPLOAD_PROFILE_CAN_NOT_BE_EMPTY = "user.iosProfileService.upload.profile.empty";

	/**
	 * 设备对象不能为空
	 */
	public static String USER_PROFILE_SERVICE_UPLOAD_PROFILE_CAN_NOT_BE_EMPTY() {
		return MultiLanguageAdapter.fetchMessage(USER_PROFILE_SERVICE_UPLOAD_PROFILE_CAN_NOT_BE_EMPTY);
	}

	/**
	 * iosToken不能为空
	 */
	public static final String USER_PROFILE_SERVICE_UPLOAD_PROFILE_IOS_TOKEN_CAN_NOT_BE_EMPTY = "user.iosProfileService.upload.profile.iosToken.empty";

	/**
	 * iosToken不能为空
	 */
	public static String USER_PROFILE_SERVICE_UPLOAD_PROFILE_IOS_TOKEN_CAN_NOT_BE_EMPTY() {
		return MultiLanguageAdapter.fetchMessage(USER_PROFILE_SERVICE_UPLOAD_PROFILE_IOS_TOKEN_CAN_NOT_BE_EMPTY);
	}

	/**
	 * 用户号码不能为空
	 */
	public static final String USER_SITE_MSG_PARAM_PHONES_CAN_NOT_BE_EMPTY = "user.siteMessage.param.phones.empty";

	/**
	 * 用户号码不能为空
	 */
	public static String USER_SITE_MSG_PARAM_PHONES_CAN_NOT_BE_EMPTY() {
		return MultiLanguageAdapter.fetchMessage(USER_SITE_MSG_PARAM_PHONES_CAN_NOT_BE_EMPTY);
	}

	/**
	 * 链接不能超过255个字符
	 */
	public static final String USER_SITE_MSG_PARAM_TARGET_TOO_LONG = "user.siteMessage.param.target.length.max";

	/**
	 * 链接不能超过255个字符
	 */
	public static String USER_SITE_MSG_PARAM_TARGET_TOO_LONG() {
		return MultiLanguageAdapter.fetchMessage(USER_SITE_MSG_PARAM_TARGET_TOO_LONG);
	}

	/**
	 * 消息标题不能为空
	 */
	public static final String USER_SITE_MSG_PARAM_TITLE_CAN_NOT_BE_EMPTY = "user.siteMessage.param.title.empty";

	/**
	 * 消息标题不能为空
	 */
	public static String USER_SITE_MSG_PARAM_TITLE_CAN_NOT_BE_EMPTY() {
		return MultiLanguageAdapter.fetchMessage(USER_SITE_MSG_PARAM_TITLE_CAN_NOT_BE_EMPTY);
	}

	/**
	 * 标题不能超过64个字符
	 */
	public static final String USER_SITE_MSG_PARAM_TITLE_TOO_LONG = "user.siteMessage.param.title.length.max";

	/**
	 * 标题不能超过64个字符
	 */
	public static String USER_SITE_MSG_PARAM_TITLE_TOO_LONG() {
		return MultiLanguageAdapter.fetchMessage(USER_SITE_MSG_PARAM_TITLE_TOO_LONG);
	}

	/**
	 * 消息内容不能为空
	 */
	public static final String USER_SITE_MSG_PARAM_CONTENT_CAN_NOT_BE_EMPTY = "user.siteMessage.param.content.empty";

	/**
	 * 消息内容不能为空
	 */
	public static String USER_SITE_MSG_PARAM_CONTENT_CAN_NOT_BE_EMPTY() {
		return MultiLanguageAdapter.fetchMessage(USER_SITE_MSG_PARAM_CONTENT_CAN_NOT_BE_EMPTY);
	}

	/**
	 * 内容不能超过255个字符
	 */
	public static final String USER_SITE_MSG_PARAM_CONTENT_TOO_LONG = "user.siteMessage.param.content.length.max";

	/**
	 * 内容不能超过255个字符
	 */
	public static String USER_SITE_MSG_PARAM_CONTENT_TOO_LONG() {
		return MultiLanguageAdapter.fetchMessage(USER_SITE_MSG_PARAM_CONTENT_TOO_LONG);
	}

	/**
	 * 管理员名称不能为空
	 */
	public static final String USER_SITE_MSG_PARAM_ADMIN_NAME_CAN_NOT_BE_EMPTY = "user.siteMessage.param.adminName.empty";

	/**
	 * 管理员名称不能为空
	 */
	public static String USER_SITE_MSG_PARAM_ADMIN_NAME_CAN_NOT_BE_EMPTY() {
		return MultiLanguageAdapter.fetchMessage(USER_SITE_MSG_PARAM_ADMIN_NAME_CAN_NOT_BE_EMPTY);
	}

	/**
	 * 昵称长度过长
	 */
	public static final String USER_UPDATE_PARAM_NICKNAME_TOO_LONG = "user.update.param.nickname.length.max";

	/**
	 * 昵称长度过长
	 */
	public static String USER_UPDATE_PARAM_NICKNAME_TOO_LONG() {
		return MultiLanguageAdapter.fetchMessage(USER_UPDATE_PARAM_NICKNAME_TOO_LONG);
	}

	/**
	 * 手机号码格式不正确,请重新输入
	 */
	public static final String USER_ADDRESS_SAVE_ADDRESS_PHONE_INVALID = "user.address.save.address.phone.invalid";

	/**
	 * 手机号码格式不正确,请重新输入
	 */
	public static String USER_ADDRESS_SAVE_ADDRESS_PHONE_INVALID() {
		return MultiLanguageAdapter.fetchMessage(USER_ADDRESS_SAVE_ADDRESS_PHONE_INVALID);
	}

	/**
	 * 系统错误,请重试
	 */
	public static final String USER_ACCOUNT_BIND_SERVER_ERROR = "user.account.bind.server.error";

	/**
	 * 系统错误,请重试
	 */
	public static String USER_ACCOUNT_BIND_SERVER_ERROR() {
		return MultiLanguageAdapter.fetchMessage(USER_ACCOUNT_BIND_SERVER_ERROR);
	}
}
