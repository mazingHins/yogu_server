/**
 * 
 */
package com.mazing.catalina.startup;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Locale;

import org.apache.catalina.Host;
import org.apache.catalina.core.StandardContext;
import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.apache.tomcat.util.buf.UriUtil;

/**
 * tomcat部署的时候一定会吧 war包解压到 appBase/xxx 目录下<br>
 * 为了迎合resin的解压方式（没有xxx目录），这里做一下改动:<br>
 * 如果docBase是一个war包绝对路径 && appBase是一个目录绝对路径，那么将war中的内容直接解压到appBase中
 * 
 * @author jfan 2016年10月9日 下午5:27:05
 */
public class ContextConfig extends org.apache.catalina.startup.ContextConfig {

	private static final Log log = LogFactory.getLog(ContextConfig.class);

	@Override
	protected void fixDocBase() throws IOException {
		Host host = (Host) context.getParent();
		File appBase = host.getAppBaseFile();
		String docBase = context.getDocBase();
		File docBaseFile = null;

		/** 自定义解压 */
		if (null != appBase && appBase.isAbsolute() && appBase.isDirectory()// appBase是一个绝对目录
				&& isNotEmpty(docBase) && docBase.toLowerCase(Locale.ENGLISH).endsWith(".war")// docBase是一个war文件绝对路径
				&& null != (docBaseFile = new File(docBase)) && docBaseFile.isAbsolute() && !docBaseFile.isDirectory()) {
			String origDocBase = docBase;

			//
			log.info(">>>>>>>> Deploying War: " + docBase);
			URL war = UriUtil.buildJarUrl(docBaseFile);
			docBase = ExpandWar.expand(host, war);

			//
			docBaseFile = new File(docBase);
			docBase = docBaseFile.getCanonicalPath();
			if (context instanceof StandardContext) {
				((StandardContext) context).setOriginalDocBase(origDocBase);
			}

			//
			boolean docBaseInAppBase = docBase.startsWith(appBase.getPath() + File.separatorChar);
			if (docBaseInAppBase) {
				docBase = docBase.substring(appBase.getPath().length());
				docBase = docBase.replace(File.separatorChar, '/');
				if (docBase.startsWith("/")) {
					docBase = docBase.substring(1);
				}
			} else {
				docBase = docBase.replace(File.separatorChar, '/');
			}
			context.setDocBase(docBase);

			/** 使用原来的方式处理 */
		} else {
			// fixDocBase0();
		}
	}

	protected void fixDocBase0() throws IOException {
		super.fixDocBase();
	}

	private boolean isNotEmpty(CharSequence cs) {
		return cs != null && cs.length() > 0;
	}

}
