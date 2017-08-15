package com.yogu.services.order.utils;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.yogu.commons.utils.JsonUtils;
import com.yogu.core.web.ParameterUtil;
import com.yogu.core.web.ResultCode;
import com.yogu.core.web.exception.ServiceException;

import freemarker.template.Configuration;
import freemarker.template.Template;

/**
 * FreemarkerUtils工具类，根据模版名称生成相对于的报表
 * @author east
 * @date 2016年11月21日
 */
public class FreemarkerUtils {

	private static final Logger logger = LoggerFactory.getLogger(FreemarkerUtils.class);

	private static Configuration configuration = null;
	
	/**
	 * 单例模式，双层校验锁
	 * @return     
	 * @author east
	 * @date 2016年11月21日
	 */
	private static Configuration getConfiguration(){
		if(configuration == null){
			synchronized (FreemarkerUtils.class) {
				if(configuration == null){
					configuration = new Configuration();
			        configuration.setDefaultEncoding("UTF-8");
			        // 设置模本装置方法和路径,FreeMarker支持多种模板装载方法。可以重servlet，classpath，数据库装载，  
			        // ftl文件存放路径  
			        configuration.setClassForTemplateLoading(FreemarkerUtils.class, "/template");  
				}
			}
		}
		return configuration;
	}
	
	/**
	 * 传入模版名称templateName和模版内容dataMap，生成相对于的报表(excel/word)
	 * @param templateName
	 * @param dataMap
	 * @param writer     
	 * @author east
	 * @date 2016年11月22日
	 */
	public static void createReport(String templateName, Map<String, Object> dataMap, Writer writer) {
		ParameterUtil.assertNotBlank(templateName, "templateName不能为空");
        try {
        	logger.info("FreemarkerUtils#createReport| templateName:{}, dataMap:{}", templateName, JsonUtils.toJSONString(dataMap));
        	Template template = getTemplate(templateName);
        	template.process(dataMap, writer);
        } catch (Exception e) {
        	logger.error("FreemarkerUtils#createReport#模版输出异常 | templateName:{} | dataMap:{}", templateName, 
        			JsonUtils.toJSONString(dataMap), e);
        	throw new ServiceException(ResultCode.FAILURE, templateName + "模版输出异常");
        }
    }
	
	/**
	 * 传入模版名称templateName和模版内容dataMap，生成相对于的报表(excel/word)
	 * @param templateName
	 * @param dataMap
	 * @return 返回字节码数据
	 * @author east
	 * @date 2016年11月22日
	 */
	public static byte[] createReport(String templateName, Map<String, Object> dataMap) {
		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
		Writer writer = new PrintWriter(byteArrayOutputStream);
		try {
			logger.info("FreemarkerUtils#createReport| templateName:{}", templateName);
        	Template template = getTemplate(templateName);
        	template.process(dataMap, writer);
        } catch (Exception e) {
        	logger.error("FreemarkerUtils#createReport#模版输出异常 | templateName:{} | dataMap:{}", templateName, 
        			JsonUtils.toJSONString(dataMap));
        	throw new ServiceException(ResultCode.FAILURE, templateName + "模版输出异常");
        }
        return byteArrayOutputStream.toByteArray();
    }
	
	/**
	 * 根据templateName找到对应的模版
	 * @param templateName
	 * @return     
	 * @author east
	 * @date 2016年11月22日
	 */
	public static Template getTemplate(String templateName){
		ParameterUtil.assertNotBlank(templateName, "templateName不能为空");
		Configuration configuration = getConfiguration();
        Template t = null;  
        try {
            // 装载模版
            t = configuration.getTemplate(templateName + ".ftl");
            t.setEncoding("utf-8");
        } catch (IOException e) {
        	logger.error("FreemarkerUtils#createReport#找不到相应的模版 | templateName:{}", templateName);
        	throw new ServiceException(ResultCode.FAILURE, "找不到" + templateName + "这个模版");
        }
        return t;
	}
	
	public static void main(String[] args) throws Exception {
		OutputStreamWriter out = new OutputStreamWriter(new FileOutputStream(new File("c://a.xls")));
		String templateName = "store_report";
		
		Map<String, Object> dataMap = new HashMap<String, Object>();
		dataMap.put("orderExcelList", Collections.emptyList());
		dataMap.put("total", 10);
		
		byte[] arr = FreemarkerUtils.createReport(templateName, dataMap);
		
	}
}
