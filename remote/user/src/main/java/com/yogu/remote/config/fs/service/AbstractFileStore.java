package com.yogu.remote.config.fs.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.yogu.core.web.ParameterUtil;
import com.yogu.core.web.ResultCode;
import com.yogu.core.web.exception.ServiceException;
import com.yogu.remote.config.fs.api.FileCategory;
import com.yogu.remote.config.fs.api.FileStore;

/**
 * 文件存储基类，主要做一些log的工作
 * @author linyi 2015/6/1.
 */
public abstract class AbstractFileStore implements FileStore {

    protected Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * FileStore实现的名称
     */
    private final String fsName;

    public AbstractFileStore(String fsName) {
        this.fsName = fsName;
    }

    @Override
    final public void storeFile(String filename, FileCategory fileCategory, byte[] content) {
        validateParameters(filename, fileCategory, content, false);

        String result = "fail";
        long t1 = System.currentTimeMillis();
        long costTime = 0L;
        try {
            doStoreFile(filename, fileCategory, content);
            result = "success";
            costTime = System.currentTimeMillis() - t1;
        } finally {
            logStoreFile(filename, fileCategory, content, result, costTime);
        }
    }

    /**
     * 写日志到日志系统，包括文件内容
     * @param filename - 保存到文件系统的文件名，不能为空
     * @param fileCategory - 文件目录，不能为空
     * @param content - 文件内容，不能为空
     * @param result - 操作结果
     * @param costTime - 耗时，单位：毫秒
     */
    protected void logStoreFile(String filename, FileCategory fileCategory, byte[] content, String result, long costTime) {
        logger.info("#fs#" + fsName + "|filename=" + filename + "|bucket=" + fileCategory.getId()
                + "|result=" + result + "|time=" + costTime + "|contentLength=" + content.length);
    }

    /**
     * 校验参数
     * @param filename - 保存到文件系统的文件名，不能为空
     * @param fileCategory - 文件目录，不能为空
     * @param content - 文件内容，不能为空
     * @param isImage - 是否为图片
     */
    protected void validateParameters(String filename, FileCategory fileCategory, byte[] content,
                                      boolean isImage) {
        ParameterUtil.assertNotBlank(filename, "文件名错误");
        ParameterUtil.assertNotNull(fileCategory, "文件目录为null");
        ParameterUtil.assertNotNull(content, "文件内容不能为null");
        ParameterUtil.assertGreaterThanZero(content.length, "文件内容长度为0");

        if (!(fileCategory.isImage() == isImage)) {
            throw new ServiceException(ResultCode.PARAMETER_ERROR, "不支持文件目录：" + fileCategory);
        }
    }

    @Override
    public void storeImage(String filename, FileCategory fileCategory, byte[] content) {
        validateParameters(filename, fileCategory, content, true);

        String result = "success";
        long t1 = System.currentTimeMillis();
        long costTime = 0L;
        try {
            doStoreImage(filename, fileCategory, content);
            result = "fail";
            costTime = System.currentTimeMillis() - t1;
        } finally {
            logStoreFile(filename, fileCategory, content, result, costTime);
        }
    }

    /**
     * 存储文件，失败时抛出异常
     * @param filename - 保存到文件系统的文件名，不能为空
     * @param fileCategory - 文件目录，不能为空
     * @param content - 文件内容，不能为空
     */
    abstract protected void doStoreFile(String filename, FileCategory fileCategory, byte[] content);

    /**
     * 存储图片，失败时抛出异常
     * @param filename - 保存到文件系统的文件名，不能为空
     * @param fileCategory - Bucket，不能为空
     * @param content - 文件内容，不能为空
     */
    abstract protected void doStoreImage(String filename, FileCategory fileCategory, byte[] content);

    /**
     * Not implemented
     * @param filename - 文件名
     * @return
     */
    abstract public String getHttpUrl(String filename);
}
