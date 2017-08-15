/**
 * 
 */
package com.yogu.commons.log;

import org.apache.log4j.FileAppender;
import org.apache.log4j.spi.LoggingEvent;

/**
 * <br>
 * 
 * JFan 2014年12月16日 下午2:24:20
 */
public class AmassDelayFileAppender extends FileAppender {

	/*
	 * （非 Javadoc）
	 * 
	 * @see org.apache.log4j.FileAppender#activateOptions()
	 */
	@Override
	public void activateOptions() {
		super.activateOptions();
	}

	/*
	 * （非 Javadoc）
	 * 
	 * @see org.apache.log4j.WriterAppender#append(org.apache.log4j.spi.LoggingEvent)
	 */
	@Override
	public void append(LoggingEvent event) {
		if (!checkEntryConditions()) {
			return;
		}
//		String format = this.layout.format(event);
		// add queue

	}

	protected void output() {
//		String format = null;
		
		// to server

		// to local file
//		this.qw.write(this.layout.format(event));
//
//		if (layout.ignoresThrowable()) {
//			String[] s = event.getThrowableStrRep();
//			if (s != null) {
//				int len = s.length;
//				for (int i = 0; i < len; i++) {
//					this.qw.write(s[i]);
//					this.qw.write(Layout.LINE_SEP);
//				}
//			}
//		}
//
//		if (shouldFlush(event)) {
//			this.qw.flush();
//		}

	}

}
