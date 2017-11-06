package com.yogu.services.user.base.service;

import java.util.List;
import java.util.Map;

import com.yogu.core.base.BaseParams;
import com.yogu.core.base.Point;
import com.yogu.remote.user.dto.User;
import com.yogu.remote.user.dto.UserProfile;
import com.yogu.services.user.base.entry.UserPO;

/**
 * 账号表 <br>
 * 的操作接口
 * 
 * @author JFan 2015-07-13
 */
public interface UserService {
	
	/**
	 * 用户通过手机注册，如果成功，数据被保存到数据库和缓存，失败则抛出异常
	 * 
	 * @param user - 用户信息，不能为空
	 * @param ip - 用户IP
	 * @throws java.lang.IllegalArgumentException 参数错误时抛出异常
	 * @return 返回用户ID
	 */
	long registerSale(User user, String ip);

	/**
	 * 用户通过手机注册，如果成功，数据被保存到数据库和缓存，失败则抛出异常
	 * 
	 * @param user - 用户信息，不能为空
	 * @param ip - 用户IP
	 * @throws java.lang.IllegalArgumentException 参数错误时抛出异常
	 * @return 返回用户ID
	 */
	long register(User user, String inviteCode, String ip);

	/**
	 * 判断app登录是否成功
	 * 
	 * @param countryCode - 国家代码，比如+86表示中国
	 * @param passport - 通行证
	 * @param password - 密码，明文
	 * @param base - 基础封签参数
	 * @param point - 坐标值
	 * @param ip - 用户的登陆IP
	 * @return 返回不为null的数据表示成功，否则失败
	 */
	User appLogin(String countryCode, String passport, String password, BaseParams base, Point point,
				  String ip, String ut);
	
	/**
	 * web登录逻辑
	 * @param countryCode 国家代码
	 * @param passport 通行证
	 * @param password 密码
	 * @param ip 用户IP
	 * @return 登录成功，返回用户信息
	 * @author ten 2016/3/11
	 */
	User webLogin(String countryCode, String passport, String password, String ip);

	/**
	 * 判断登录是否成功, 无密登录, 验证码在上层已经校验
	 * 
	 * @param countryCode - 国家代码，比如+86表示中国
	 * @param passport - 通行证
	 * @param base - 基础封签参数
	 * @param point - 坐标值
	 * @param ip - 用户的登陆IP
	 * @return 返回不为null的数据表示成功，否则失败
	 */
	User appLoginWithoutPwd(String countryCode, String passport, BaseParams base, Point point, String ip, String ut);
	
	/**
	 * 根据用户id获取用户信息
	 * 
	 * @param uid
	 * 
	 * @return
	 */
	UserProfile getUserProfile(long uid);
	
	/**
	 * 根据用户passport获取用户
	 * 
	 * @param countryCode - 国家编号
	 * @param passport - 手机号/邮箱
	 * @return
	 */
	User getUser(String countryCode, String passport);

	/**
	 * 
	 * @Description: 根据多个用户id获取用户信息
	 * @author Hins
	 * @date 2015年8月17日 上午11:59:22
	 * 
	 * @param uid
	 * @return 返回用户信息列表，若无，返回empty
	 */
	List<UserProfile> listUserProfile(long... uid);

	/**
	 * 找回密码/重置密码<br>
	 * 参数都是明文形式<br>
	 * 
	 * 重置密码的流程：重置密码是在没有登录的状态下执行的. 填写两遍新密码, 然后发送手机验证码, 接收到验证码输入验证码,开始验证并执行修改密码
	 * 
	 * 重置后：数据库密码被修改,没有登录状态的变化(因为重置密码一般是找回密码时使用,用户没有登录)
	 * 
	 * @param password 新密码
	 * @param mobile 用户手机号
	 * @param countryCode 国家代码
	 * @author sky
	 * @date 2015-10-02 22:10:11
	 */
	void resetPassword(String password, String mobile, String countryCode);

	/**
	 * 修改密码<br>
	 * 参数都是明文形式<br>
	 * 修改密码流程：用户在登录状态下,执行修改密码, 需要输入旧密码和 新密码, <br>
	 * 并且需要发送手机验证码 ,在验证通过后,才执行修改数据库数据,并将其他设备该账号踢下线(登录失效,而修改密码的机器保持登录状态) <br>
	 * 
	 * @param uid 用户id
	 * @param password 新的密码
	 * @param oldpassword 旧密码
	 * @param countryCode 国家代码
	 * @param mobile 手机号
	 * @return
	 * @author sky
	 * @date 2015-10-02 22:20:21
	 */
	Map<String, Object> updatePassword(long uid, String password, String oldpassword, String countryCode, String mobile);

	/**
	 * 管理员重置用户密码接口
	 * 
	 * @param adminUid 操作的管理员uid
	 * @param password 新的密码
	 * @param mobile 被修改用户的手机号
	 * @param countryCode 国家代码
	 * @return 不抛异常即为修改成功
	 */
	void adminResetPassword(long adminUid, String password, String mobile, String countryCode);
	

	/**
	 * 分表操作，临时使用的，其他地方请勿调用
	 * @author ben
	 * @date 2015年11月16日 上午10:36:31 
	 * @return
	 */
	List<UserPO> listAll();

}
