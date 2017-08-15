/**
 * 
 */
package com.yogu.commons.server.clean;

/**
 * 清理器 <br>
 * <br>
 * 请按照Services规范来实现适配<br>
 * 举例：假如你有一个实现类x.y.z.XxxImpl<br>
 * 那么你只需要在/META-INF/services目录下创建一个文本文件com.vip.commons.server.clean.CleanProvider，其内容中只包含一行文字：x.y.z.XxxImpl。<br>
 * 
 * JFan 2015年2月4日 下午1:37:49
 */
public interface CleanProvider {

    public void cleanUp();

}
