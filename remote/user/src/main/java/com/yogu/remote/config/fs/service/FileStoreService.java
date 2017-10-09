package com.yogu.remote.config.fs.service;

import java.io.IOException;
import java.util.Calendar;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.yogu.commons.utils.DateUtils;
import com.yogu.commons.utils.img.ImageSizeHelper;
import com.yogu.commons.utils.math.SixtyTwoCountUtil;
import com.yogu.core.web.ResultCode;
import com.yogu.core.web.exception.ServiceException;
import com.yogu.remote.config.fs.api.FileCategory;
import com.yogu.remote.config.fs.api.FileStoreConstant;
import com.yogu.remote.config.fs.api.FileStoreFactory;
import com.yogu.remote.config.fs.api.FileType;
import com.yogu.remote.config.fs.utils.FileStoreHelper;

/**
 * 文件存储服务
 * @author linyi 2015/5/28.
 */
@Service
public class FileStoreService {

    private static final Logger logger = LoggerFactory.getLogger(FileStoreService.class);

	/**
	 * 保存图片到首页的目录下，成功返回生成的文件名。<br>
	 *
	 * @param uid 上传者的ID（一般是adminId）
	 * @param type 保存的类型（最终会转换成FileCategory-> INDEX 或 ACTIVITY）
	 * @param originalName - 原始文件名ID
	 * @param content - 文件内容
	 * @return 返回生成的图片文件名，文件名组成：$type目录/年份/月份/uid36进制-当前毫秒数62进制_宽x高.$fileType
	 * @throws ServiceException - 如果图片格式不被支持抛出异常
	 */
	public String saveBackendImage(long uid, FileCategory type, String originalName, byte[] content) {
		if (content == null || content.length == 0)
			throw new ServiceException(ResultCode.PARAMETER_ERROR, "图片数据错误");

		if (null == type)
			type = FileCategory.INDEX;

		// 根据图片内容获取文件格式，宽高
		String format = "";
		int width = 0, height = 0;
		try {
			ImageSizeHelper img = new ImageSizeHelper("-", content);
			format = img.getFormat();
			height = img.getHeight();
			width = img.getWidth();
		} catch (IOException e) {
			throw new ServiceException(ResultCode.UNKNOWN_ERROR, "读取图片数据出错，无法生成图片文件名");
		}

		// 识别文件类型
		FileType fileType = FileType.fromFileName(format);
		if (fileType == null)
			throw ResultCode.paramExcp("服务器对图片处理错误，请增加这种文件的处理，originalName: " + originalName + ", format: " + format);

		// 生成最终的存储路径
		// $type目录/年份/2位数月份/uid36进制-当前毫秒数64进制_宽x高.$format
		String filename = giveFinalNameByBackend(type, uid, width, height, fileType);

		// 1. 保存到本地系统
		logger.info("FileStoreService#saveIndexImage | 保存{}图片 | uid: {}, filename: {}, len: {}", type, uid, filename, content.length);
		long time1 = System.currentTimeMillis();
		saveToDatabase(fileType, filename, content);
		long time2 = System.currentTimeMillis();
		FileStoreFactory.createFileStore().storeImage(filename, type, content);

		// 2. 返回文件名
		long costTime = System.currentTimeMillis() - time1;
		long dbCostTime = time2 - time1;
		logger.info("FileStoreService#saveIndexImage | 保存{}图片耗时 | total: {}, db: {}, cloud: {}", type, costTime, dbCostTime, costTime - dbCostTime);
		return getHttpUrl(filename);
	}

	/**
	 * 生成存储的最终地址<br>
	 * $type目录/年份/月份/uid36进制-当前毫秒数62进制_宽x高.$fileType
	 */
	private String giveFinalNameByBackend(FileCategory type, long uid, int width, int height, FileType fileType) {
		long time = System.currentTimeMillis();
		Calendar c = DateUtils.getCalendarFromGlobal(time);
		StringBuilder sb = new StringBuilder();
		sb.append(type.getId()).append(FileStoreConstant.PATH_DELIMITER);
		sb.append(c.get(Calendar.YEAR)).append(FileStoreConstant.PATH_DELIMITER);
		sb.append(DateUtils.twoDigitMonth(c.get(Calendar.MONTH))).append(FileStoreConstant.PATH_DELIMITER);
		sb.append(Long.toString(uid, 36)).append(FileStoreConstant.DELIMITER).append(SixtyTwoCountUtil.gen(time));
		sb.append("_").append(width).append("x").append(height).append(fileType.getSuffix());
		return sb.toString();
	}

    /**
     * 保存商店的图片，成功返回生成的文件名。<br>
     * 门店的图片，一般都是public的，而且有可能上传图片的时候，门店都还没有创建<br>
     * 所以sid改成uid，记录是谁提交的文件
     *
     * @param storeId - 门店ID
     * @param originalName - 原始文件名ID
     * @param content - 文件内容
     * @return 返回生成的图片文件名，文件名组成：云平台-IDC-用户ID-UUID_宽x高.jpg，比如：ali-sz-a39x-24d1b11e27124bc8834a3e619db55705_800x600.jpg
     * @throws ServiceException - 如果图片格式不被支持抛出异常
     */
    public String saveShopImage(long storeId, String originalName, byte[] content) {
        if (content == null || content.length == 0) {
            throw new ServiceException(ResultCode.PARAMETER_ERROR, "图片数据错误");
        }

        String filename = FileStoreHelper.getFileId(FileCategory.STORE, storeId, content);
        FileType fileType = FileType.fromFileName(filename);
        if (fileType == null) {
            throw new ServiceException(ResultCode.PARAMETER_ERROR, "服务器对图片处理错误，请增加这种文件的处理，originalName: " + originalName + ", filename: " + filename);
        }

        // 1. 保存到本地系统
        logger.info("FileStoreService#saveShopImage | 保存商店图片 | storeId: {}, filename: {}, len: {}", storeId, filename, content.length);
        long time1 = System.currentTimeMillis();
        saveToDatabase(fileType, filename, content);
        long time2 = System.currentTimeMillis();
        FileStoreFactory.createFileStore().storeImage(filename, FileCategory.STORE, content);

        // 2. 返回文件名
        long costTime = System.currentTimeMillis() - time1;
        long dbCostTime = time2 - time1;
        logger.info("FileStoreService#saveShopImage | 保存商店图片耗时 | total: {}, db: {}, cloud: {}", costTime, dbCostTime, costTime-dbCostTime);
        return getHttpUrl(filename);
    }

    /**
     * 保存到本地数据库
     * @param fileType
     * @param filename
     * @param content
     */
    private void saveToDatabase(FileType fileType, String filename, byte[] content) {
        /*
        // 去掉保存到 database, 2015/11/12 ten
        FileVO vo = new FileVO();
        vo.setContent(content);
        vo.setFileType(fileType);
        vo.setName(filename);
        fileService.save(vo);
        */
    }

    /**
     * 保存用户的图片，成功返回生成的文件名。
     * @param uid - 用户ID
     * @param originalName - 原始文件名ID
     * @param content - 文件内容
     * @return 返回生成的图片文件名，文件名组成：云平台-IDC-商店ID-UUID_宽x高.jpg，比如：ali-sz-a39x-24d1b11e27124bc8834a3e619db55705_800x600.jpg
     * @throws ServiceException - 如果图片格式不被支持抛出异常
     */
    public String saveProfileImage(long uid, String originalName, byte[] content) {

        if (content == null || content.length == 0) {
            throw new ServiceException(ResultCode.PARAMETER_ERROR, "图片数据错误");
        }

        String filename = FileStoreHelper.getFileId(FileCategory.PROFILE, uid, content);
        FileType fileType = FileType.fromFileName(filename);
        if (fileType == null) {
            throw new ServiceException(ResultCode.PARAMETER_ERROR, "服务器对图片处理错误，请增加这种文件的处理，originalName: " + originalName + ", filename: " + filename);
        }

        // 1. 保存到本地系统
        logger.info("FileStoreService#saveProfileImage | 保存个人图片 | uid: {}, filename: {}, len: {}", uid, filename, content.length);
        long time1 = System.currentTimeMillis();
        saveToDatabase(fileType, filename, content);
        long time2 = System.currentTimeMillis();
        FileStoreFactory.createFileStore().storeImage(filename, FileCategory.PROFILE, content);

        // 2. 返回文件名
        long costTime = System.currentTimeMillis() - time1;
        long dbCostTime = time2 - time1;
        logger.info("FileStoreService#saveProfileImage | 保存个人图片耗时 | total: {}, db: {}, cloud: {}", costTime, dbCostTime, costTime-dbCostTime);
        return getHttpUrl(filename);
    }
    
    /**
     * 保存美食的图片，成功返回生成的文件名。
     * @param dishId - 菜品ID
     * @param originalName - 原始文件名ID
     * @param content - 文件内容
     * @return 返回生成的图片文件名，文件名组成：云平台-IDC-商店ID-UUID_宽x高.jpg，比如：ali-sz-a39x-24d1b11e27124bc8834a3e619db55705_800x600.jpg
     * @throws ServiceException - 如果图片格式不被支持抛出异常
     */
    public String saveDishImage(long dishId, String originalName, byte[] content) {

        if (content == null || content.length == 0) {
            throw new ServiceException(ResultCode.PARAMETER_ERROR, "图片数据错误");
        }

        String filename = FileStoreHelper.getFileId(FileCategory.DISH, dishId, content);
        FileType fileType = FileType.fromFileName(filename);
        if (fileType == null) {
            throw new ServiceException(ResultCode.PARAMETER_ERROR, "服务器对图片处理错误，请增加这种文件的处理，originalName: " + originalName + ", filename: " + filename);
        }

        // 1. 保存到本地系统
        logger.info("FileStoreService#saveDishImage | 保存美食图片 | uid: {}, filename: {}, len: {}", dishId, filename, content.length);
        long time1 = System.currentTimeMillis();
        saveToDatabase(fileType, filename, content);
        long time2 = System.currentTimeMillis();
        FileStoreFactory.createFileStore().storeImage(filename, FileCategory.DISH, content);

        // 2. 返回文件名
        long costTime = System.currentTimeMillis() - time1;
        long dbCostTime = time2 - time1;
        logger.info("FileStoreService#saveDishImage | 保存美食图片耗时 | total: {}, db: {}, cloud: {}", costTime, dbCostTime, costTime-dbCostTime);
        return getHttpUrl(filename);
    }
    
    /**
     * 保存门店相关的图片，成功返回生成的文件名。
     * @param uid - 用户ID
     * @param originalName - 原始文件名ID
     * @param content - 文件内容
     * @return 返回生成的图片文件名，文件名组成：云平台-IDC-商店ID-UUID_宽x高.jpg，比如：ali-sz-a39x-24d1b11e27124bc8834a3e619db55705_800x600.jpg
     * @throws ServiceException - 如果图片格式不被支持抛出异常
     */
    public String saveStoreImage(long uid, String originalName, byte[] content){
    	if (content == null || content.length == 0) {
            throw new ServiceException(ResultCode.PARAMETER_ERROR, "图片数据错误");
        }

        String filename = FileStoreHelper.getFileId(FileCategory.STORE, uid, content);
        FileType fileType = FileType.fromFileName(filename);
        if (fileType == null) {
            throw new ServiceException(ResultCode.PARAMETER_ERROR, "服务器对图片处理错误，请增加这种文件的处理，originalName: " + originalName + ", filename: " + filename);
        }

        // 1. 保存到本地系统
        logger.info("FileStoreService#saveStoreImage | 保存门店相关图片 | uid: {}, filename: {}, len: {}", uid, filename, content.length);
        long time1 = System.currentTimeMillis();
        saveToDatabase(fileType, filename, content);
        long time2 = System.currentTimeMillis();
        FileStoreFactory.createFileStore().storeImage(filename, FileCategory.STORE, content);

        // 2. 返回文件名
        long costTime = System.currentTimeMillis() - time1;
        long dbCostTime = time2 - time1;
        logger.info("FileStoreService#saveStoreImage | 保存门店相关图片耗时 | total: {}, db: {}, cloud: {}", costTime, dbCostTime, costTime-dbCostTime);
        return getHttpUrl(filename);
    }
    
    
    /**
     * 保存用户的图片，成功返回生成的文件名。
     * @param feedbackId - 用户ID
     * @param originalName - 原始文件名ID
     * @param content - 文件内容
     * @return 返回生成的图片文件名，文件名组成：云平台-IDC-商店ID-UUID_宽x高.jpg，比如：ali-sz-a39x-24d1b11e27124bc8834a3e619db55705_800x600.jpg
     * @throws ServiceException - 如果图片格式不被支持抛出异常
     */
    public String saveFeedbackImage(long feedbackId, String originalName, byte[] content) {
        if (content == null || content.length == 0) {
            throw new ServiceException(ResultCode.PARAMETER_ERROR, "图片数据错误");
        }

        String filename = FileStoreHelper.getFileId(FileCategory.PROFILE, feedbackId, content);
        FileType fileType = FileType.fromFileName(filename);
        if (fileType == null) {
            throw new ServiceException(ResultCode.PARAMETER_ERROR, "服务器对图片处理错误，请增加这种文件的处理，originalName: " + originalName + ", filename: " + filename);
        }

        // 1. 保存到本地系统
        logger.info("FileStoreService#saveFeedbackImage | 保存反馈图片 | feedbackId: {}, filename: {}, len: {}", feedbackId, filename, content.length);
        long time1 = System.currentTimeMillis();
        saveToDatabase(fileType, filename, content);
        long time2 = System.currentTimeMillis();
        FileStoreFactory.createFileStore().storeImage(filename, FileCategory.PROFILE, content);

        // 2. 返回文件名
        long costTime = System.currentTimeMillis() - time1;
        long dbCostTime = time2 - time1;
        logger.info("FileStoreService#saveFeedbackImage | 保存反馈图片耗时 | total: {}, db: {}, cloud: {}", costTime, dbCostTime, costTime-dbCostTime);
        return getHttpUrl(filename);
    }
    
    /**
     * 保存文章的图片，成功返回生成的文件名。
     * @param did - 文章ID
     * @param originalName - 原始文件名ID
     * @param content - 文件内容
     * @return 返回生成的图片文件名，文件名组成：云平台-IDC-商店ID-UUID_宽x高.jpg，比如：ali-sz-a39x-24d1b11e27124bc8834a3e619db55705_800x600.jpg
     * @throws ServiceException - 如果图片格式不被支持抛出异常
    public String saveDocImage(long did, String originalName, byte[] content) {
        if (content == null || content.length == 0) {
            throw new ServiceException(ResultCode.PARAMETER_ERROR, "图片数据错误");
        }

        String filename = FileStoreHelper.getFileId(FileCategory.DOC, did, content);
        FileType fileType = FileType.fromFileName(filename);
        if (fileType == null) {
            throw new ServiceException(ResultCode.PARAMETER_ERROR, "服务器对图片处理错误，请增加这种文件的处理，originalName: " + originalName + ", filename: " + filename);
        }

        // 1. 保存到本地系统
        logger.info("FileStoreService#saveDocImage | 保存文章图片 |did: {}, filename: {}, len: {}", did, filename, content.length);
        long time1 = System.currentTimeMillis();
        saveToDatabase(fileType, filename, content);
        long time2 = System.currentTimeMillis();
        FileStoreFactory.createFileStore().storeImage(filename, FileCategory.STORE, content);

        // 2. 返回文件名
        long costTime = System.currentTimeMillis() - time1;
        long dbCostTime = time2 - time1;
        logger.info("FileStoreService#saveDocImage | 保存文章图片耗时 | total: {}, db: {}, cloud: {}", costTime, dbCostTime, costTime-dbCostTime);
        return filename;
    }
     */

    /**
     * 返回文件的完整HTTP地址
     * @param filename - 文件名
     * @return 返回完成的HTTP地址
     */
    public String getHttpUrl(String filename) {
        return FileStoreFactory.createFileStore().getHttpUrl(filename);
    }

//    /**
//     * 缩放图片并保存到系统
//     * @param fsImageName - 通过FileStoreService接口生成的文件名，必须是已经保存到系统的文件
//     * @param width - 新的图片宽度
//     * @param height - 新的图片高度
//     * @return 返回缩放后的文件名，文件名组成：云平台-IDC-商店ID-UUID_宽x高.jpg，比如：ali-sz-a39x-24d1b11e27124bc8834a3e619db55705_800x600.jpg
//     */
//    public String resizeImage(String fsImageName, int width, int height) {
//        FileType fileType = FileType.fromFileName(fsImageName);
//        if (fileType == null) {
//            throw new ServiceException(ResultCode.FAILURE, "不支持此类文件后缀: " + fsImageName);
//        }
//        FileVO file = fileService.getFile("", fsImageName);
//        if (file == null) {
//            throw new ServiceException(ResultCode.RECORD_NOT_EXIST, "文件不存在: " + fsImageName);
//        }
//
//        long t1 = System.currentTimeMillis();
//        try {
//            byte[] content = ImageUtil.resize(file.getContent(), width, height, FileStoreConstant.JPEG_QUALITY);
//            long t2 = System.currentTimeMillis();
//
//            // 生成文件名
//            String filename = new ImageSizeHelper(fsImageName, content).getOriginFileNameWithSize();
//
//            // 保存
//            FileCategory fileCategory = FileStoreHelper.getFileCategoryFromFileId(filename);
//
//            // 保存到本地系统
//            logger.info("FileStoreService#resizeImage | resize图片 |filename: {}, len: {}", filename, content.length);
//            saveToDatabase(fileType, filename, content);
//            long t3 = System.currentTimeMillis();
//            FileStoreFactory.createFileStore().storeImage(filename, fileCategory, content);
//
//            // 计时
//            long t4 = System.currentTimeMillis();
//            long costTime = t4 - t1;  // 总耗时
//            long dbCostTime = t3 - t2; // 数据库时间
//            logger.info("FileStoreService#resizeImage | resize图片 | total: {}, db: {}, cloud: {}", costTime, dbCostTime, t4-t3);
//            // 3. 返回文件名
//            return filename;
//        } catch (IOException e) {
//            logger.error("FileStoreService#resizeImage | 缩放文件出错 | originFile: "
//                    + fsImageName + ", width: " + width + ", height: " + height, e);
//            throw new ServiceException(ResultCode.UNKNOWN_ERROR, "缩放文件出错", e);
//        }
//
//
//    }

//    /**
//     * 返回图片文件的格式名称
//     * @param type FileType
//     * @return 返回 png/jpg 这样的名称
//     */
//    private String getFormatName(FileType type) {
//       return type.getSuffix().substring(1);
//    }
}
