/**
 * 
 */
package com.yogu.services.user.resource.base;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.apache.commons.io.IOUtils;
import org.glassfish.jersey.media.multipart.FormDataBodyPart;
import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataMultiPart;
import org.glassfish.jersey.media.multipart.FormDataParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.yogu.CommonConstants;
import com.yogu.commons.utils.ArrayUtils;
import com.yogu.commons.utils.FileUtils;
import com.yogu.commons.utils.StringUtils;
import com.yogu.commons.utils.encrypt.Base64;
import com.yogu.commons.validation.constraints.InStrings;
import com.yogu.core.web.RestResult;
import com.yogu.core.web.ResultCode;
import com.yogu.core.web.UserErrorCode;
import com.yogu.core.web.context.SecurityContext;
import com.yogu.core.web.exception.ServiceException;
import com.yogu.language.UserMessages;
import com.yogu.remote.config.fs.service.FileStoreService;
import com.yogu.services.user.base.service.UserProfileService;

/**
 * 用户上传图片相关接口 <br>
 */
@Named
@Path("a")
@Singleton
@Produces("application/json;charset=UTF-8")
public class UserUploadResource {

	private static final Logger logger = LoggerFactory.getLogger(UserUploadResource.class);

	@Inject
	private UserProfileService userProfileService;

	@Inject
	private FileStoreService fileStoreService;

	/**
	 * 修改个人头像
	 * 
	 * @param pic
	 * @param picForm
	 * @return
	 */
	@POST
	@Path("v1/user/profile/changePic.do")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	public RestResult<Map<String, Object>> changePic(@FormDataParam("pic") InputStream pic,
			@FormDataParam("pic") FormDataContentDisposition picForm) {
		long uid = SecurityContext.getUid();
		logger.info("user#changePic |change user profilePic | uid: {}, fileName: {}", uid, picForm.getFileName());

		// 读取图片内容
		String picFilename = null;
		byte[] picContent = null;
		if (pic != null) {// && picForm.getSize()>0
			try {
				picContent = FileUtils.readStream(pic);
				picFilename = picForm.getFileName();
				if (picContent != null && picContent.length > CommonConstants.UPLOAD_MAX_PIC_SIZE) {
					return new RestResult<>(UserErrorCode.FILE_TOO_LARGE, UserMessages.USER_CHANGEPIC_PIC_OVER_SIZE());
				}
				if (picContent != null && picContent.length == 0) {
					return new RestResult<>(UserErrorCode.FILE_DATA_ERROR, UserMessages.USER_CHANGEPIC_PIC_SIZE_ZERO());
				}
			} catch (IOException e) {
				logger.error("读取头像数据失败，将忽略头像", e);
			}
		}

		String profilePic = null;
		// 保存头像
		try {
			if (picContent != null) {
				profilePic = userProfileService.saveProfilePic(uid, picFilename, picContent);
			}
		} catch (ServiceException e) {
			// TODO alarm
			logger.error("保存图像失败", e);
			logger.error("user profile pic|uid=" + uid + "|filename=" + picFilename + "|pic=" + Base64.encode(picContent));
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("profileUrl", profilePic);
		return new RestResult<>(map);
	}

	/**
	 * 上传用户图片<br>
	 * 目前只有实名认证上传图片使用
	 * 
	 * @param form 图片数据内容
	 * @param type 上传的图片是干什么用的，详情参考参数的注解（区分大小写）
	 * @return 图片相对路径
	 */
	@POST
	@Path("v1/user/upload/pic.do")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	public RestResult<Map<String, String>> pic(
			FormDataMultiPart form,
			@QueryParam("type") @InStrings(value = { "audit" }, message = "未知的图片类别！", mkey = UserMessages.USER_UPLOAD_PIC_TYPE_ILLEGAL) String type) {

		// 当前用户
		long uid = SecurityContext.getUid();

		// 读取内容
		FormDataBodyPart field = form.getField("pic");
		String fileName = field.getContentDisposition().getFileName();
		InputStream is = field.getValueAs(InputStream.class);
		byte[] pic = null;
		try {
			pic = IOUtils.toByteArray(is);
		} catch (IOException e) {
			throw new ServiceException(ResultCode.UNKNOWN_ERROR, UserMessages.USER_UPLOAD_PIC_ERROR(e.getMessage()));
		}

		// check
		if (StringUtils.isBlank(fileName) || ArrayUtils.isEmpty(pic))
			throw new ServiceException(ResultCode.PARAMETER_ERROR, "No file data is received.");
		logger.info("user#upload#pic | 用户上传图片{} | uid: {}, fileSize: {}, fileName: '{}'", type, uid, pic.length, fileName);

		// 保存图片
		String imgPath = fileStoreService.saveProfileImage(uid, fileName, pic);

		// result
		Map<String, String> result = new HashMap<>();
		result.put("imgPath", imgPath);
		return new RestResult<>(result);
	}

}
