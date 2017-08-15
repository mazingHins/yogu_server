/**
 * 
 */
package com.yogu.commons.log.log4j;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.log4j.FileAppender;
import org.apache.log4j.helpers.LogLog;
import org.apache.log4j.spi.LoggingEvent;

import com.yogu.commons.utils.Args;

/**
 * 扩展每隔X分钟分割一次文件 <br>
 * 
 * JFan 2015年2月11日 下午12:56:50
 */
public class MinuteRollingFileAppender extends FileAppender {

    private int intervalMinute;// 分钟（set进来）

    Calendar now = Calendar.getInstance();
    SimpleDateFormat sdf;// 文件后缀格式
    long intervalMillis;// 毫秒，上面的分钟转换成这里的毫秒
    long nextCheck;// 下一次检测的时间点(毫秒)

    String scheduledFilename;// 当前这个文件（fileName -- 还没有加后缀的）应该使用那个名字（注意是应该啊）
    int type;// 1: 分级 2：时级

    /*
     * （非 Javadoc）
     * 
     * @see org.apache.log4j.FileAppender#activateOptions()
     */
    public void activateOptions() {
        Args.check(1 <= intervalMinute, "'intervalMinute' should be greater than 1m.");
        Args.check(24 * 60 > intervalMinute, "'intervalMinute' should not exceed 1d.");

        super.activateOptions();

        intervalMillis = intervalMinute * 60 * 1000;
        nextCheck = System.currentTimeMillis() - 1;

        String datePattern;
        if (60 > intervalMinute) {// 分钟级
            datePattern = "'.'yyyyMMdd-HHmm";
            type = 1;
        } else {// 时级
            datePattern = "'.'yyyyMMdd-HH";
            type = 2;
        }
        sdf = new SimpleDateFormat(datePattern);

        // 设置当前应该使用的 fileName
        scheduledFilename = giveFileName(new File(fileName).lastModified());
    }

    /*
     * （非 Javadoc）
     * 
     * @see org.apache.log4j.DailyRollingFileAppender#subAppend(org.apache.log4j.spi.LoggingEvent)
     */
    @Override
    protected void subAppend(LoggingEvent event) {
        long n = System.currentTimeMillis();
        if (nextCheck <= n) {
            now.setTimeInMillis(n);
            now.set(Calendar.MILLISECOND, 0);
            now.set(Calendar.SECOND, 0);
            if (2 <= type)
                now.set(Calendar.MINUTE, 0);

            nextCheck = (now.getTimeInMillis() + intervalMillis);
            // System.out.println("下次检测时间：" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(nextCheck)));

            try {
                rollOver(n);
            } catch (IOException ioe) {
                LogLog.error("rollOver() failed.", ioe);
            }
        }
        super.subAppend(event);
    }

    protected String giveFileName(long time) {
        return fileName + sdf.format(new Date(time));
    }

    // 进入到此方法，就表示需要分割文件了
    private void rollOver(long time) throws IOException {
        String datedFilename = giveFileName(time);
        // System.out.println("进入归档判断。。。。" + datedFilename);
        // 需要分割文件，但是文件名又和之前的一样，那么加后缀
        if (scheduledFilename.equals(datedFilename))
            return;

        // close current file, and rename it to datedFilename
        this.closeFile();

        File target = new File(scheduledFilename);
        if (target.exists())
            target.delete();

        File file = new File(fileName);
        boolean result = file.renameTo(target);
        if (result) {
            LogLog.debug(fileName + " -> " + scheduledFilename);
        } else {
            LogLog.error("Failed to rename [" + fileName + "] to [" + scheduledFilename + "].");
        }

        try {
            // This will also close the file. This is OK since multiple
            // close operations are safe.
            this.setFile(fileName, true, this.bufferedIO, this.bufferSize);
        } catch (IOException e) {
            errorHandler.error("setFile(" + fileName + ", true) call failed.");
        }
        scheduledFilename = datedFilename;
    }

    /**
     * @param intervalMinute 要设置的 intervalMinute
     */
    public void setIntervalMinute(int intervalMinute) {
        this.intervalMinute = intervalMinute;
    }

}
