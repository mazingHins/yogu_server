package com.yogu.remote.config.fs.service;

import java.io.ByteArrayInputStream;

import org.apache.commons.lang3.StringUtils;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.OSSException;
import com.aliyun.oss.model.ObjectMetadata;
import com.aliyun.oss.model.PutObjectResult;
import com.yogu.core.web.ResultCode;
import com.yogu.core.web.exception.ServiceException;
import com.yogu.remote.config.base.CloudSettings;
import com.yogu.remote.config.fs.api.FileCategory;
import com.yogu.remote.config.fs.api.FileStoreConstant;

/**
 * 阿里云OSS存储实现。<br/>
 * <pre>
 * endpoint地址：http://help.aliyun.com/knowledge_detail.htm?knowledgeId=5974206
 * 杭州内网地址：oss-cn-hangzhou-internal.aliyuncs.com
 * 深圳内网地址：oss-cn-shenzhen-internal.aliyuncs.com
 * 北京内网地址：oss-cn-beijing-internal.aliyuncs.com
 * </pre>
 * @author linyi 2015/5/28.
 */
public class AliyunOssStore extends AbstractFileStore {

    private OSSClient client;

    public AliyunOssStore(String endpoint, String appKey, String appSecret) {
        super("aliyun");
        client = new OSSClient(endpoint, appKey, appSecret);
    }

    @Override
    protected void doStoreFile(String filename, FileCategory fileCategory, byte[] content) {
        logger.info("aliyun store file|filename=" + filename + "|bucket=" + fileCategory);
        // 获取云上的 bucket name
        String bucketName = CloudSettings.getFileStoreBucket(FileStoreConstant.BUCKET_STATIC);
        if (StringUtils.isEmpty(bucketName)) {
            throw new ServiceException(ResultCode.SYSTEM_CONFIG_ERROR,
                    "系统配置错误，bucket " + fileCategory.getId() + " 不存在！");
        }
        doAliyunStore(filename, bucketName, content);
/*
        ByteArrayInputStream inputStream = new ByteArrayInputStream(content);
        // 创建上传Object的Metadata
        ObjectMetadata meta = new ObjectMetadata();

        // 必须设置ContentLength
        meta.setContentLength(content.length);
        // 获取云上的 bucket name
        String bucketName = CloudSettings.getFileStoreBucket(FileStoreConstant.BUCKET_STATIC);
        if (StringUtils.isEmpty(bucketName)) {
            throw new ServiceException(ResultCode.SYSTEM_CONFIG_ERROR,
                    "系统配置错误，bucket " + fileCategory.getId() + " 不存在！");
        }
        logger.info("aliyun store setting|bucketName=" + bucketName);

        // 上传Object.
        try {
            PutObjectResult result = client.putObject(bucketName, filename, inputStream, meta);
            logger.info("aliyun oss put file success|etag=" + result.getETag());
        } catch (OSSException e) {
            logger.error("oss put file error", e);
            throw new ServiceException(ResultCode.FAILURE, e.getMessage());
        } catch (Exception e) {
            logger.error("client put file error", e);
            throw new ServiceException(ResultCode.UNKNOWN_ERROR, e.getMessage());
        }
        */
    }

    /**
     * 存储到阿里云
     * @param filename 文件名
     * @param bucketName - 在阿里云的 bucket 名称
     * @param content - 内容
     */
    protected void doAliyunStore(String filename, String bucketName, byte[] content) {
        ByteArrayInputStream inputStream = new ByteArrayInputStream(content);
        // 创建上传Object的Metadata
        ObjectMetadata meta = new ObjectMetadata();

        // 必须设置ContentLength
        meta.setContentLength(content.length);
        meta.setContentType(StoreContentType.getContentType(filename));
        meta.setContentEncoding("utf-8");

        logger.info("aliyun store setting|bucketName=" + bucketName);

        // 上传Object.
        try {
            PutObjectResult result = client.putObject(bucketName, filename, inputStream, meta);
            logger.info("aliyun oss put file success|etag=" + result.getETag());
        } catch (OSSException e) {
            logger.error("oss put file error", e);
            throw new ServiceException(ResultCode.FAILURE, e.getMessage());
        } catch (Exception e) {
            logger.error("client put file error", e);
            throw new ServiceException(ResultCode.UNKNOWN_ERROR, e.getMessage());
        }
    }

    @Override
    protected void doStoreImage(String filename, FileCategory fileCategory, byte[] content) {
        logger.info("aliyun store file|filename=" + filename + "|bucket=" + fileCategory);

        // 获取云上的 bucket name
        String bucketName = "yg-img-test";
        if (StringUtils.isEmpty(bucketName)) {
            throw new ServiceException(ResultCode.SYSTEM_CONFIG_ERROR,
                    "系统配置错误，bucket " + fileCategory.getId() + " 不存在！");
        }
        doAliyunStore(filename, bucketName, content);
    }

    /**
     * 把文件名转成阿里云OSS的地址，见
     * http://help.aliyun.com/knowledge_detail.htm?spm=5176.775991734.3.3.TKLK0T&knowledgeId=5993073&keyWords=&categoryId=8315693
     *
     * @param filename - 文件名
     * @return 如果配置了aliyun/aws上的bucket地址，返回http地址，否则返回原文件名
     */
    @Override
    public String getHttpUrl(String filename) {
        String[] arr = filename.split(FileStoreConstant.DELIMITER);
//        if (arr.length > 3) {
            // arr[2] = Bucket.id
//            String url = CloudSettings.getFileStoreBucketPublicUrl(arr[2]);
        String url = CloudSettings.getFileStoreBucketPublicUrl(arr[0]);
        logger.info("img url : {}", url);
            if (url != null) {
                if (url.endsWith("/")) {
                    url = url + filename;
                }
                else {
                    url = url + "/" + filename;
                }
                return url;
            }
//        }
        return filename;
    }

    public static void main(String[] args) {
        System.out.println(StoreContentType.getContentType("ali-sz-13-8ace75ce0b0b4650bddf9aaf53ea356b_750x350.jpg"));
    }
}
