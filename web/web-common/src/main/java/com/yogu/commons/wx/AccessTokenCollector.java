package com.yogu.commons.wx;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.yogu.cfg.GlobalSetting;
import com.yogu.commons.utils.HttpClientUtils;

/**
 * 获取微信 access token。
 * 文档见：http://mp.weixin.qq.com/wiki/15/54ce45d8d30b6bf6758f68d2e95bc627.html
 * 接口：https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET
 * 每天最多 2000 次
 * @author ten 2016/1/7.
 */
public class AccessTokenCollector extends Thread {

    private static final Logger logger = LoggerFactory.getLogger(AccessTokenCollector.class);
	// true：不需要读取wx token（线程直接结束）
	private final boolean skip = Boolean.parseBoolean(GlobalSetting.getSysCongfig("noTokenWX"));

    private boolean stopped = false;

    /**
     * 间隔
     */
    private static final int INTERVAL = 2 * 60 * 1000;

    // 读取 access token 的地址
    public static final String URL = "http://121.40.81.176:20090/";

    public AccessTokenCollector() {
        super("accessTokenCollector");
    }

    public void stopRunning() {
        this.stopped = true;
        interrupt();
    }

    @Override
    public void run() {
		if (skip) {
			logger.info("mobile#wx#AccessTokenCollector | 不需要读取微信公众号Token信息");
			return;
		}

    	logger.info("mobile#wx#AccessTokenCollector | 读取微信公众号 token 线程启动 ...");
        while (!stopped) {

            try {
                String str = HttpClientUtils.doGet(URL);
                str = StringUtils.trimToEmpty(str);
                if (StringUtils.isNotEmpty(str)) {
                    String[] array = str.split("\\|");
                    WeixinAccessToken.ACCESS_TOKEN = array[0];
                    WeixinAccessToken.JSAPI_TICKET = array[1];
                    WeixinAccessToken.WXAPP_ACCESS_TOKEN = array[2];
                    logger.info("mobile#wx#AccessTokenCollector | 读取微信公众号 token 成功");
                } else {
                    logger.error("mobile#wx#AccessTokenCollector | 读取微信公众号 token 出错，没有结果");
                }
            } catch (Exception e) {
                logger.error("mobile#wx#AccessTokenCollector | 出错", e);
            }

            try {
                Thread.sleep(INTERVAL);
            } catch (InterruptedException e) {
                // ignore
                break;
            }
        }
        logger.info("mobile#wx#AccessTokenCollector | 读取微信公众号 token 线程结束 ...");
    }
}
