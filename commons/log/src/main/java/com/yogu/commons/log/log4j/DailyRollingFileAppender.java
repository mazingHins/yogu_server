/**
 * 
 */
package com.yogu.commons.log.log4j;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Layout;

/**
 * 扩展log4j中的同名类 <br>
 * 支持手动触发 刷新缓冲
 * 
 * JFan 2014年12月16日 下午2:17:09
 */
public class DailyRollingFileAppender extends org.apache.log4j.DailyRollingFileAppender {

	private static Map<String, BufferedWriter> bwMap = new HashMap<String, BufferedWriter>();
	private static boolean addShutdownHook = false;

    public DailyRollingFileAppender() {
        super();
        addShutdownHook();
    }

    public DailyRollingFileAppender(Layout layout, String filename, String datePattern) throws IOException {
        super(layout, filename, datePattern);
        addShutdownHook();
    }

	/*
	 * （非 Javadoc）
	 * 
	 * @see org.apache.log4j.FileAppender#setQWForFiles(java.io.Writer)
	 */
	protected void setQWForFiles(Writer writer) {
		super.setQWForFiles(writer);
		if (writer instanceof BufferedWriter) {
			bwMap.put(name, (BufferedWriter) writer);
		}
	}

	/*
	 * （非 Javadoc）
	 * 
	 * @see org.apache.log4j.AppenderSkeleton#finalize()
	 */
	public void finalize() {
		super.finalize();
		try {
			bwMap.remove(name);
		} catch (Exception e) {
			// 不处理
		}
	}

    // ## 处理缓冲数据，手动触发写入

	/**
	 * 将指定的logger，缓存日志输出到目的地
	 */
	public static void flush(String name) {
		BufferedWriter bufferedWriter = bwMap.get(name);
		if (null != bufferedWriter)
			synchronized (bufferedWriter) {
				try {
					bufferedWriter.flush();
				} catch (IOException e) {
					// 不处理
				}
			}
	}

    // ## 处理Shutdown时，残余日志的输出

    public static void flushAll() {
        for (BufferedWriter bufferedWriter : bwMap.values())
            synchronized (bufferedWriter) {
                try {
                    bufferedWriter.flush();
                } catch (IOException e) {
                    // 不处理
                }
            }
    }

    private void addShutdownHook() {
        if (!addShutdownHook) {
            Runtime.getRuntime().addShutdownHook(new Thread() {

                /*
                 * （非 Javadoc）
                 * 
                 * @see java.lang.Thread#run()
                 */
                @Override
                public void run() {
                    try {
                        DailyRollingFileAppender.flushAll();
                        bwMap = new HashMap<>();// 释放引用
                    } catch (Exception e) {
                        // ignore
                    }
                }

            });
            addShutdownHook = true;
        }
    }

}
