/**
 * 
 */
package com.yogu.commons.api.accept;

import com.yogu.commons.api.ApiStorage;

/**
 * 拿到response数据之后，如果不符合条件（例如：code ！= 200 | 不是一个json） <br>
 * 那么没有必要做存储<br>
 * 
 * JFan 2015年2月11日 下午7:47:22
 */
public interface StorageAccept {

    /**
     * 决定这个数据是否“合法”<br>
     * 不合格，请返回null<br>
     * 请不要throw异常
     */
    public ApiStorage accept(ApiStorage storage);

}
