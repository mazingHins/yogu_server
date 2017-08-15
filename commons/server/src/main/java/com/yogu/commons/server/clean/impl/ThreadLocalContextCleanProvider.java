/**
 * 
 */
package com.yogu.commons.server.clean.impl;

import com.yogu.commons.server.clean.CleanProvider;

/**
 * 清理ThreadLocalContext的public、private存储的内容 <br>
 * 
 * JFan 2015年2月4日 下午1:49:10
 */
public class ThreadLocalContextCleanProvider implements CleanProvider {

    /*
     * （非 Javadoc）
     * 
     * @see com.vip.commons.server.clean.CleanProvider#cleanUp()
     */
    @Override
    public void cleanUp() {
//        try {
//            ThreadLocalContext.clearThreadValue();
//        } catch (Exception e) {
//            // ignore
//        }

//        try {
//            ThreadLocalContext.cleanPublic();
//        } catch (Exception e) {
//            // ignore
//        }
    }

}
