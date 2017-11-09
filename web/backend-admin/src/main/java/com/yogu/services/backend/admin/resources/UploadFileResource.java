/**
 * 
 */
package com.yogu.services.backend.admin.resources;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.yogu.commons.utils.ArrayUtils;
import com.yogu.commons.utils.StringUtils;
import com.yogu.core.web.RestResult;
import com.yogu.core.web.ResultCode;
import com.yogu.core.web.exception.ServiceException;
import com.yogu.remote.config.fs.api.FileCategory;
import com.yogu.remote.config.fs.service.FileStoreService;
import com.yogu.services.backend.admin.context.AdminContext;


/**
 * 上传文件资源对象 <br>
 *
 * @author JFan 2016年1月18日 下午8:09:21
 */
@Controller
@RequestMapping("/admin/system/")
public class UploadFileResource {

	private static final Logger logger = LoggerFactory.getLogger(UploadFileResource.class);

	@Inject
	private FileStoreService fileStoreService;

	/**
	 * 上传图片，并得到存储的相对路径<br>
	 * 不截图，原图保存
	 */
	@ResponseBody
	@RequestMapping("uploadPic.do")
	public RestResult<String> uploadPic(HttpServletRequest request) {
		long adminId = AdminContext.getAccountId();
		String typeStr = request.getParameter("type");

		// 转型为MultipartHttpRequest(重点的所在)
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		// 根据前台的name名称得到上传的文件
		MultipartFile file = multipartRequest.getFile("picFile");
		// 获取文件名
		String fileName = file.getOriginalFilename();

		// 读取内容
		byte[] pic = null;
		try {
			pic = file.getBytes();
		} catch (IOException e) {
			throw new ServiceException(ResultCode.UNKNOWN_ERROR, "读取图片数据失败：" + e.getMessage());
		}

		// check
		if (StringUtils.isBlank(fileName) || ArrayUtils.isEmpty(pic))
			throw new ServiceException(ResultCode.PARAMETER_ERROR, "请上传正确的图片.");
		logger.info("admin#upload#pic | 上传图片 | adminId: {}, type: {}, fileName: '{}', fileSize: {}", adminId, typeStr, fileName, pic.length);

		FileCategory type = null;
		if (StringUtils.isNotBlank(typeStr))
			type = FileCategory.giveBackendCategory(typeStr);

		// 暂时只有首页用到了 后台上传图片功能，如果有其他地方用到，请留意存储目录（这里是index）
		// 保存门店图片
		String imgPath = fileStoreService.saveBackendImage(adminId, type, fileName, pic);
		return new RestResult<>(imgPath);
	}
	
	@ResponseBody
	@RequestMapping("uploadMultPic.do")
	public RestResult<String> uploadMultPic(HttpServletRequest request, @RequestParam MultipartFile file) {
		long adminId = AdminContext.getAccountId();
		String typeStr = request.getParameter("type");

		// 获取文件名
		String fileName = file.getOriginalFilename();

		// 读取内容
		byte[] pic = null;
		try {
			pic = file.getBytes();
		} catch (IOException e) {
			throw new ServiceException(ResultCode.UNKNOWN_ERROR, "读取图片数据失败：" + e.getMessage());
		}

		// check
		if (StringUtils.isBlank(fileName) || ArrayUtils.isEmpty(pic))
			throw new ServiceException(ResultCode.PARAMETER_ERROR, "请上传正确的图片.");
		logger.info("admin#upload#pic | 上传图片 | adminId: {}, fileName: '{}', fileSize: {}", adminId, fileName,
				pic.length);

		FileCategory type = null;
		type = FileCategory.giveBackendCategory(typeStr);

		// 暂时只有首页用到了 后台上传图片功能，如果有其他地方用到，请留意存储目录（这里是index）
		// 保存门店图片
		String imgPath = fileStoreService.saveBackendImage(adminId, type, fileName, pic);
		return new RestResult<>(imgPath);
	}

}
