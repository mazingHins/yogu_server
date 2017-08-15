/**
 * 
 */
package com.yogu.commons.concurrent;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

import javax.annotation.PostConstruct;

import com.yogu.commons.utils.Args;

/**
 * <br>
 * 
 * JFan 2015年2月6日 下午6:37:31
 */
//@Named
public final class ExecutorFactory {

    private ExecutorFactory() {
    }

    // @Inject写在set方法上，静态属性无法注入
    private static ExecutorService executorService;

    @PostConstruct
    public void initialCheck() {
        Args.notNull(executorService, "'executorService'");
    }

    /**
     * @param executorService 要设置的 executorService
     */
//    @Inject
    public void setExecutorService(ExecutorService executorService) {
        ExecutorFactory.executorService = executorService;
    }

    /**
     * @return executorService
     */
    public static ExecutorService getExecutorService() {
        return executorService;
    }

    public static <T> Future<T> submit(Callable<T> task) {
        return executorService.submit(task);
    }

    public static <T> Future<T> submit(Runnable task, T result) {
        return executorService.submit(task, result);
    }

    public static Future<?> submit(Runnable task) {
        return executorService.submit(task);
    }

}
