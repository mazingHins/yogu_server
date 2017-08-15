/**
 * 
 */
package com.yogu.commons.server.clean.impl;

import com.yogu.commons.server.clean.CleanProvider;

/**
 * 清理配置中心数据（WEB Destroyed） <br>
 * 
 * JFan 2015年2月4日 下午7:07:56
 */
public class ConfigCenterCleanProvider implements CleanProvider {

    /*
     * （非 Javadoc）
     * 
     * @see com.vip.commons.server.clean.CleanProvider#cleanUp()
     */
    @Override
    public void cleanUp() {
        //ConfigCenterFactory.shutdown();
    }

}
