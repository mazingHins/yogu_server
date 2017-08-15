package com.yogu.remote.config.base;

import com.yogu.cfg.GlobalSetting;
import com.yogu.core.remote.config.ConfigGroupConstants;
import com.yogu.core.remote.config.ConfigRemoteService;

/**
 * 关于云的配置（阿里云、AWS）。
 * 根据 mz_config.mz_config 表的配置返回相应的值
 * @author linyi 2015/5/30.
 */
public class CloudSettings {

	private static final String cloud = GlobalSetting.getCloud();

    /**
     * 获取 AccessKey
     * @return
     */
    public static String getCloudAccessKey() {
        String key = cloud + "_AccessKey";
        return ConfigRemoteService.getConfig(ConfigGroupConstants.CLOUD, key);
    }

    /**
     * 获取 AccessSecret
     * @return
     */
    public static String getCloudAccessSecret() {
        String key = cloud + "_AccessSecret";
        return ConfigRemoteService.getConfig(ConfigGroupConstants.CLOUD, key);
    }

    /**
     * 获取文件系统API的URL，阿里是oss，阿里云是s3
     * @return
     */
    public static String getFileStoreApiUrl() {
        String key = cloud + "_fs_url_" + GlobalSetting.getIdc();
        return ConfigRemoteService.getConfig(ConfigGroupConstants.CLOUD, key);
    }

    /**
     * 读取文件系统 bucket 在 阿里云或AWS 中的 Bucket 名称。
     * @param id - com.mazing.fs.api.Bucket 定义的id
     * @return
     */
    public static String getFileStoreBucket(String id) {
        String key = cloud + "_bucket_" + id;
        return ConfigRemoteService.getConfig(ConfigGroupConstants.CLOUD, key);
    }

    /**
     * 访问bucket资源的公开的http地址
     * @param id - com.mazing.fs.api.Bucket 定义的id
     * @return
     */
    public static String getFileStoreBucketPublicUrl(String id) {
        String key = cloud + "_bucket_" + id + "_url";
        return ConfigRemoteService.getConfig(ConfigGroupConstants.CLOUD, key);
    }

    /**
     * MQ的producerId
     * @return
     */
    public static String getProducerId() {
        return ConfigRemoteService.getConfig(ConfigGroupConstants.CLOUD, cloud + "_producer_id");
    }

    /**
     * MQ的consumerId
     * @return
     */
    public static String getConsumerId() {
        return ConfigRemoteService.getConfig(ConfigGroupConstants.CLOUD, cloud + "_consumer_id");
    }
}
