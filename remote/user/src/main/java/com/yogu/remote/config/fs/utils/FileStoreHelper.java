package com.yogu.remote.config.fs.utils;

import java.io.IOException;
import java.util.UUID;

import com.yogu.cfg.GlobalSetting;
import com.yogu.commons.utils.img.ImageSizeHelper;
import com.yogu.core.web.ResultCode;
import com.yogu.core.web.exception.ServiceException;
import com.yogu.remote.config.fs.api.FileCategory;
import com.yogu.remote.config.fs.api.FileStoreConstant;
import com.yogu.remote.config.fs.api.FileType;

/**
 * 工具类
 * @author linyi 2015/6/1.
 */
public class FileStoreHelper {

	private static final String idc = GlobalSetting.getIdc();
	private static final String cloud = GlobalSetting.getCloud();

    /**
     * 数字转换为36进制
     * @param n - 数字
     * @return 返回36进制的数字
     */
    public static String toRadix36(long n) {
        return Long.toString(n, 36);
    }

    /**
     * 返回一个文件ID
     * @param fileCategory - 文件分类
     * @param fileType - 图片类型
     * @param id - id
     * @param content - 图片内容
     * @return 文件名
     */
    public static String getFileId(FileCategory fileCategory, FileType fileType, long id,
                                   byte[] content) {
        StringBuilder buf = new StringBuilder(64);

        buf.append(fileCategory.getId()).append(FileStoreConstant.PATH_DELIMITER)
                .append(cloud).append(FileStoreConstant.DELIMITER)
                .append(idc)
                .append(FileStoreConstant.DELIMITER)
                .append(toRadix36(id))
                .append(FileStoreConstant.DELIMITER)
                .append(UUID.randomUUID().toString().replaceAll("-", ""))
                .append(fileType.getSuffix());
        String name = null;
        try {
            name = new ImageSizeHelper(buf.toString(), content).getOriginFileNameWithSize();
        } catch (IOException e) {
            throw new ServiceException(ResultCode.UNKNOWN_ERROR, "读取图片数据出错，无法生成图片文件名");
        }
        return name;
    }

    /**
     * 返回一个文件ID
     * @param fileCategory - 文件分类
     * @param id - id
     * @param content - 图片内容
     * @return 文件名
     */
    public static String getFileId(FileCategory fileCategory, long id,
                                   byte[] content) {
        StringBuilder buf = new StringBuilder(64);

        buf.append(fileCategory.getId()).append(FileStoreConstant.PATH_DELIMITER)
                .append(cloud).append(FileStoreConstant.DELIMITER)
                .append(idc)
                .append(FileStoreConstant.DELIMITER)
                .append(toRadix36(id))
                .append(FileStoreConstant.DELIMITER)
                .append(UUID.randomUUID().toString().replaceAll("-", ""));
        String name = null;
        try {
            name = new ImageSizeHelper(buf.toString(), content).getOriginFileNameWithSize();
        } catch (IOException e) {
            throw new ServiceException(ResultCode.UNKNOWN_ERROR, "读取图片数据出错，无法生成图片文件名");
        }
        return name;
    }

    /**
     * 从文件名中解析出 FileCategory
     * @param file FileStoreHelper.getFileId()方法生成的文件名
     * @return 返回 FileCategory 对象，如果没有找到，抛出 IllegalArgumentException
     */
    public static FileCategory getFileCategoryFromFileId(String file) {
        int pos = file.indexOf(FileStoreConstant.PATH_DELIMITER);
        if (pos > 0) {
            return FileCategory.fromId(file.substring(0, pos));
        }
        throw new IllegalArgumentException("不是本系统生成的文件，无法找到 FileCategory: " + file);
    }

    /**
     * 计算字符串的hashcode
     * @param filename
     * @return
     */
    public static int hashCode(String filename) {
        int hash = 0;
        int len = filename.length();

        for (int i = 0; i < len; i++) {
            hash = 31 * hash + filename.charAt(i);
        }
        return hash;
    }
}
