package com.yogu.commons.log.log4j;

import org.apache.log4j.Layout;
import org.apache.log4j.spi.LoggingEvent;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Log Message format : "'prefix' Level Date LoggerName - message - 'suffix'. <br>
 * <br>
 * 
 * LoggerName: Logger logger = Logger.getLogger(ABC.class); --> 'com.mazing.ABC' <br>
 * LoggerName: Logger logger = Logger.getLogger("DEF"); --> 'DEF'<br>
 * <br>
 * 
 * For example,
 * 
 * <pre>
 *      DEBUG 14-3-21 下午3:36 com.mazing.Action - Hello world
 * </pre>
 *
 * JFan 2014年12月16日 下午2:18:15
 */
public class SimpleLayout extends Layout {

    private StringBuffer sbuf = new StringBuffer(128);
    private DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss,SSS");
    private Date date = new Date();

    private String dateFormat;
    private String prefix;
//    private String suffix;

    /*
     * （非 Javadoc）
     * 
     * @see org.apache.log4j.spi.OptionHandler#activateOptions()
     */
    @Override
    public void activateOptions() {
    }

    /*
     * （非 Javadoc）
     * 
     * @see org.apache.log4j.Layout#format(org.apache.log4j.spi.LoggingEvent)
     */
    @Override
    public String format(LoggingEvent event) {
        date.setTime(event.timeStamp);// 重置时间
        sbuf.setLength(0);// 清空buffered

        // 前缀
        if (null != prefix) {
            sbuf.append(prefix);
            sbuf.append(' ');
        }
        // level
        sbuf.append(event.getLevel().toString());
        // date
        sbuf.append(' ');
        sbuf.append(df.format(date));
        // loggerName
        sbuf.append(' ');
        sbuf.append(event.getLoggerName());
        // threadName
        sbuf.append(' ');
        sbuf.append(event.getThreadName());
        // message
        sbuf.append(" - ");
        sbuf.append(event.getRenderedMessage());
//        // 后缀
//        if (null != suffix) {
//            sbuf.append(" - ");
//            sbuf.append(suffix);
//        }
        // 换行
        sbuf.append(LINE_SEP);

        return sbuf.toString();
    }

    /*
     * （非 Javadoc）
     * 
     * @see org.apache.log4j.Layout#ignoresThrowable()
     */
    @Override
    public boolean ignoresThrowable() {
        return true;
    }

    /**
     * @param dateFormat 要设置的 dateFormat
     */
    public void setDateFormat(String dateFormat) {
        this.dateFormat = dateFormat;
        df = new SimpleDateFormat(this.dateFormat);
    }

    /**
     * @param prefix 要设置的 prefix
     */
    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

//    /**
//     * @param suffix 要设置的 suffix
//     */
//    public void setSuffix(String suffix) {
//        this.suffix = suffix;
//    }

}
