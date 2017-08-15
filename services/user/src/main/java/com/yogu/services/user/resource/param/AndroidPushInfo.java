package com.yogu.services.user.resource.param;

import javax.ws.rs.DefaultValue;
import javax.ws.rs.FormParam;

import com.yogu.commons.validation.constraints.Length;
import com.yogu.commons.validation.constraints.NotEmpty;
import com.yogu.language.UserMessages;

/**
 * Android系统上传push 相关信息接收实体
 * 
 * @author sky
 *
 */
public class AndroidPushInfo {

	/** 设备唯一标识 */
	@FormParam("deviceToken")
	@NotEmpty(message = "设备标识不能为空", mkey = UserMessages.USER_ANDROID_PROFILE_UPLOAD_DID_CAN_NOT_BE_EMPTY)
	@Length(max = 128, message = "设备标识长度过长", mkey = UserMessages.USER_UPLOADINFO_DEVICETOKEN_TOO_LONG)
	private String did;

	/** 渠道号 */
	@FormParam("channel")
	@DefaultValue("")
	@Length(max = 32, message = "渠道号长度过长", canEmpty = true, mkey = UserMessages.USER_UPLOADINFO_CHANNEL_TOO_LONG)
	private String channel;

	public String getDid() {
		return did;
	}

	public void setDid(String did) {
		this.did = did;
	}

	public String getChannel() {
		return channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}

	@Override
	public String toString() {
		return "did:" + did + ", channel:" + channel;
	}
}
