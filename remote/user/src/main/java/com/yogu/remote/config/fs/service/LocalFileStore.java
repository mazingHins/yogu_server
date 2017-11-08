package com.yogu.remote.config.fs.service;

import java.io.File;

import org.apache.commons.io.FileUtils;

import com.yogu.core.web.ResultCode;
import com.yogu.core.web.exception.ServiceException;
import com.yogu.remote.config.fs.api.FileCategory;
import com.yogu.remote.config.fs.api.FileStoreConstant;

/**
 * 本地文件储存
 * @author linyi 2015/5/29.
 */
public class LocalFileStore extends AbstractFileStore {

    /**
     * 基础目录
     */
    public static final String BASE_PATH = "/data/webapps/img.mazing.com/";

    public LocalFileStore() {
        super("local");
    }

    @Override
    protected void doStoreFile(String filename, FileCategory fileCategory, byte[] content) {
        // filename里可能包含目录信息
        String fullname = BASE_PATH + "/" + filename;
        String path = fullname.substring(0, fullname.lastIndexOf('/'));
        try {
            File tmpPath = new File(path);
            if (!tmpPath.exists()) {
                boolean created = tmpPath.mkdirs();
                logger.info(path + " 目录不存在, 创建目录结果=" + created);
            }
            FileUtils.writeByteArrayToFile(new File(fullname), content);
        } catch (Exception e) {
            logger.error("保存到本地文件出错", e);
            throw new ServiceException(ResultCode.SYSTEM_CONFIG_ERROR, "无法写入本地文件，请查看系统配置或目录权限");
        }
    }

    @Override
    protected void doStoreImage(String filename, FileCategory fileCategory, byte[] content) {
        this.doStoreFile(filename, fileCategory, content);
    }

    @Override
    public String getHttpUrl(String filename) {
//        String[] arr = filename.split(FileStoreConstant.DELIMITER);
//        if (arr.length > 3) {
            // arr[2] = Bucket.id
    		logger.info("local file {}", filename);
            String url = "http://img.yogubc.com/" + filename;
            return url;
//        }
//        return filename;
    }
}
