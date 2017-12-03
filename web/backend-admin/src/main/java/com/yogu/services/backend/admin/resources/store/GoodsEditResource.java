package com.yogu.services.backend.admin.resources.store;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.yogu.commons.utils.JsonUtils;
import com.yogu.commons.utils.StringUtils;
import com.yogu.commons.utils.VOUtil;
import com.yogu.commons.utils.img.ImageUtil;
import com.yogu.commons.utils.resource.Menu;
import com.yogu.commons.utils.resource.MenuResource;
import com.yogu.core.constant.ImageConstants;
import com.yogu.core.web.RestResult;
import com.yogu.core.web.ResultCode;
import com.yogu.core.web.exception.ServiceException;
import com.yogu.remote.config.fs.api.FileCategory;
import com.yogu.remote.config.fs.api.FileStoreFactory;
import com.yogu.remote.config.fs.api.FileType;
import com.yogu.remote.config.fs.utils.FileStoreHelper;
import com.yogu.remote.store.GoodsRemoteService;
import com.yogu.services.backend.admin.context.AdminContext;
import com.yogu.services.backend.admin.resources.form.GoodsForm;
import com.yogu.services.store.Goods;



@Controller
@RequestMapping("/admin/goods/")
@Menu(name = "新增/编辑商品", parent = "商家管理", sequence = 3200000)
public class GoodsEditResource {
	
	private static final Logger logger = LoggerFactory.getLogger(GoodsEditResource.class);
	
	@Autowired
	private GoodsRemoteService goodsRemoteService;
	
	 /**
     * 门店列表主页，xhtm 仅用于展示页面，ajax 调用 接口获取参数
     * @return
     */
	@RequestMapping("editGoods.xhtm")
	@MenuResource("编辑优惠券主页")
	public String index() {
		return ("/store/edit_goods");
	}
	
	/**
	 * 保存优惠券，成功返回true
	 * 
	 * @param form 优惠券信息
	 * @return result.success = true 为成功
	 */
	@ResponseBody
	@RequestMapping(value = "saveGoods.do", method = RequestMethod.POST)
	@MenuResource("保存商品")
	public RestResult<Integer> saveGoods(@Valid @ModelAttribute GoodsForm form) {
		logger.info("admin#coupon#saveCoupon | 保存商品 start | adminId: {}, coupon: {}", AdminContext.getAccountId(),
				JsonUtils.toJSONString(form));
		String content = form.getContent();
		Map<String, String> contentMap = new HashMap<>(2);
		contentMap.put("pic", JsonUtils.toJSONString(toList(content)));
		goodsRemoteService.saveGoods(VOUtil.from(form, Goods.class));
		logger.info("admin#coupon#saveCoupon | 保存商品 end ");
		return new RestResult<Integer>(1);
	}
	
	private List<String> toList(String str) {
		if (StringUtils.isBlank(str)) {
			return Collections.emptyList();
		}
		String[] array = StringUtils.split(str.trim(), ',');
		List<String> list = new ArrayList<>(array.length);
		for (String s : array) {
			list.add(s);
		}
		return list;
	}
	
	/**
	 * 修改瀑布流主图。 （1）图片必须 >= 346 x 346； （2）图片必须是正方形； （3）服务端自动缩放到 346x346
	 *
	 * @param newTopicImage 新图片的数据
	 * @return 成功返回 图片地址（完整URL）
	 */
	@RequestMapping(value = "modifyCardImage.do", method = RequestMethod.POST)
	@ResponseBody
	@MenuResource("修改瀑布流图片")
	public RestResult<String> modifyTopicImage(@RequestParam("upfile") MultipartFile newTopicImage) {
		if (newTopicImage == null || !isUploadNameOK(newTopicImage.getOriginalFilename())) {
			logger.error("admin#store#modifyTopicImage | 修改瀑布流图片，上传的文件错误 | file: {}", newTopicImage == null ? "null" : newTopicImage.getOriginalFilename());
			return new RestResult<>(ResultCode.PARAMETER_ERROR, "请上传 jpeg/png/gif 格式的图片文件");
		}

		String path = "";
		try {
			String originalName = newTopicImage.getOriginalFilename();
			byte[] bytes = newTopicImage.getBytes();
			int[] rect = ImageUtil.getImageSize(bytes);
			if (rect[0] != rect[1]) {
				return new RestResult<>(ResultCode.PARAMETER_ERROR, "图片不是正方形，目前的宽高是：" + rect[0] + " x " + rect[1]);
			}

			// 上传至oss并更新相应字段
			byte[] cardImgBytes = ImageUtil.ensureWidthHeightAndResize(bytes, ImageConstants.STORE_TOPIC_IMG_WIDTH,
					ImageConstants.STORE_TOPIC_IMG_HEIGHT);
			path = saveImage(originalName, cardImgBytes);

		} catch (IOException e) {
			logger.error("读取文件内容失败", e);
			return new RestResult<>(ResultCode.PARAMETER_ERROR, "读取图片文件失败，请检查文件是否有效或重试");
		}
		return new RestResult<String>(path);
	}
	
	private String saveImage(String originalName, byte[] content) {
		if (content == null || content.length == 0) {
			throw new ServiceException(ResultCode.PARAMETER_ERROR, "图片数据错误");
		}

		String fileName = FileStoreHelper.getFileId(FileCategory.DISH, 0, content);

		FileType fileType = FileType.fromFileName(fileName);
		if (fileType == null) {
			throw new ServiceException(ResultCode.PARAMETER_ERROR, "服务器对图片处理错误，请增加这种文件的处理，originalName: " + originalName + ", filename: "
					+ fileName);
		}

		// 1, 保存到oss
		FileStoreFactory.createFileStore().storeImage(fileName, FileCategory.STORE, content);

		return FileStoreFactory.createFileStore().getHttpUrl(fileName);
	}
	
	private boolean isUploadNameOK(String name) {
		boolean ok = false;
		if (StringUtils.isNotBlank(name)) {
			name = name.toLowerCase();
			if (name.indexOf(".jpg") > 0 || name.indexOf(".jpeg") > 0 || name.indexOf(".png") > 0 || name.indexOf(".gif") > 0) {
				ok = true;
			}
		}
		return ok;
	}
	

}
