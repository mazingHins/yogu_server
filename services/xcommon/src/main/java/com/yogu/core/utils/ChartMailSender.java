package com.yogu.core.utils;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeUtility;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import com.yogu.commons.utils.cfg.DesPropertiesEncoder;
import com.yogu.commons.utils.cfg.EncryptionPropertyPlaceholderConfigurer;
import com.yogu.commons.utils.cfg.PropertiesEncoder;

/**
 * 发送图表的email，默认加载邮件配置 classpath:mail.properties
 * 
 * @author linyi 2013/5/10
 */
public class ChartMailSender {

	private static final Logger logger = LoggerFactory.getLogger(ChartMailSender.class);

	public static final String EMAIL_FROM = "alarm@mazing.com";

	private JavaMailSender javaMailSender;

	private StringBuilder content = new StringBuilder();

	private int fileCount = 0;

	private String[] to;

	private String from = EMAIL_FROM;

	private String subject;

	private List<File> imageFiles = new ArrayList<File>();

	private List<String> imageFileIds = new ArrayList<String>();

	private List<File> attachments = new ArrayList<File>();
	
	//是否使用字节码数组上传附件
	private boolean isByte = false;
	
	//附件的字节码数组
	private byte[] byteArray;

	private String fileName;
	
	/** 线程池管理器 */
	private static ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();

	// private ExecutorService executor = Executors.newFixedThreadPool(20, new CustomizableThreadFactory("javaMail"));

	public ChartMailSender(JavaMailSender javaMailSender) {
		this.javaMailSender = javaMailSender;
	}

	static {
		executorSetAttributes();
	}

	/**
	 * 设置线程池的属性
	 *
	 * @author hubing
	 * @date 2014-9-22 下午2:59:29
	 * @version V1.0
	 */
	private static void executorSetAttributes() {
		// 线程池维护线程的最少数量
		executor.setCorePoolSize(5);
		// 线程池所使用的缓冲队列
		executor.setQueueCapacity(20);
		// 线程池维护线程的最大数量
		executor.setMaxPoolSize(40);
		executor.initialize();
	}

	/**
	 * 邮件服务器配置
	 */
	public static class MailConfig {
		public String host;

		public int port = 25;

		public String username;

		public String password;

		public String encoding = "UTF-8";

		public String getHost() {
			return host;
		}

		public void setHost(String host) {
			this.host = host;
		}

		public int getPort() {
			return port;
		}

		public void setPort(int port) {
			this.port = port;
		}

		public String getUsername() {
			return username;
		}

		public void setUsername(String username) {
			this.username = username;
		}

		public String getPassword() {
			return password;
		}

		public void setPassword(String password) {
			this.password = password;
		}

		public String getEncoding() {
			return encoding;
		}

		public void setEncoding(String encoding) {
			this.encoding = encoding;
		}

		@Override
		public String toString() {
			return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
		}

		public String toShortString() {
			return host + ":" + port;
		}
	}

	/** 配置，从 CONFIG_FILE读取 */
	private static MailConfig defaultConfig = null;

	/** 默认配置文件路径 */
	private static final String CONFIG_FILE = "META-INF/mazing-mail.properties";

	/**
	 * 缓存sender
	 */
	private static Map<String, JavaMailSender> senderMap = new HashMap<>();

	/**
	 * 加载默认的配置文件 CONFIG_FILE
	 */
	private static void loadConfig(String path) {
		if (defaultConfig == null) {
			if (StringUtils.isNotBlank(path)) {
				try {
					Properties props = new Properties();
					props.load(new FileInputStream(new File(path)));

					PropertiesEncoder encoder = new DesPropertiesEncoder();

					EncryptionPropertyPlaceholderConfigurer.convertProp(encoder, props);
				} catch (Exception e) {
				}
			}

			// org.springframework.core.io.Resource fileResource = new ClassPathResource(CONFIG_FILE);
			// Properties p = new Properties();
			// p.load(fileResource.getInputStream());
			// DesPropertiesEncoder ed = new DesPropertiesEncoder();
			defaultConfig = new MailConfig();
			// defaultConfig.username = ed.decode(p.getProperty("mail.username"));
			// defaultConfig.password = ed.decode(p.getProperty("mail.password"));
			// defaultConfig.host = p.getProperty("mail.host");

			defaultConfig.username = (String) EncryptionPropertyPlaceholderConfigurer.getConfig().get("mail.username");
			defaultConfig.password = (String) EncryptionPropertyPlaceholderConfigurer.getConfig().get("mail.password");
			defaultConfig.host = (String) EncryptionPropertyPlaceholderConfigurer.getConfig().get("mail.host");

			// String sPort = p.getProperty("mail.port");
			String sPort = (String) EncryptionPropertyPlaceholderConfigurer.getConfig().get("mail.port");

			defaultConfig.port = (sPort == null ? 25 : Integer.parseInt(sPort));

			logger.info("mail config=" + defaultConfig.toShortString());
		}
	}

	/**
	 * 创建一个默认的 JavaMailSender
	 */
	public static JavaMailSender createJavaMailSender() {
		String path = null;
		return createJavaMailSender(path);
	}

	/**
	 * 创建一个默认的 JavaMailSender
	 */
	public static JavaMailSender createJavaMailSender(String path) {
		loadConfig(path);
		if (defaultConfig == null) {
			logger.error("Default configuration is null, reset configuration");
			throw new IllegalStateException("请先配置好文件：" + CONFIG_FILE);
		}
		return createJavaMailSender(defaultConfig);
	}

	/**
	 * 创建 JavaMailSender
	 */
	public static synchronized JavaMailSender createJavaMailSender(MailConfig config) {
		JavaMailSender sender = senderMap.get(config.toShortString());
		if (sender == null) {
			Properties properties = new Properties();
			// properties.setProperty("mail.debug", "true");// 是否显示调试信息(可选)
			// properties.setProperty("mail.smtp.socketFactory.class",
			// "javax.net.ssl.SSLSocketFactory");
			properties.setProperty("mail.smtp.auth", "true");
			// 增加 timeout, 2014/8/22
			properties.setProperty("mail.smtp.timeout", "25000");

			JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();
			javaMailSender.setJavaMailProperties(properties);
			javaMailSender.setUsername(config.username);
			javaMailSender.setPassword(config.password);
			javaMailSender.setHost(config.host);
			javaMailSender.setPort(config.port);
			javaMailSender.setDefaultEncoding(config.encoding);

			senderMap.put(config.toShortString(), javaMailSender);

			sender = javaMailSender;
		}
		return sender;
	}

	private String getFileId() {
		++fileCount;
		return "file" + fileCount;
	}

	public ChartMailSender append(int n) {
		content.append(n);
		return this;
	}

	public ChartMailSender append(String message) {
		if (message == null) {
			throw new IllegalArgumentException("message cannot be null");
		}
		content.append(message);
		return this;
	}

	public ChartMailSender appendImage(File file) {
		if (file == null) {
			throw new IllegalArgumentException("file cannot be null");
		}
		String fileId = getFileId();
		this.imageFileIds.add(fileId);
		this.imageFiles.add(file);
		content.append("<img src='cid:").append(fileId).append("'/>");
		return this;
	}

	public ChartMailSender appendAttachment(File file) {
		if (file == null) {
			throw new IllegalArgumentException("file cannot be null");
		}
		attachments.add(file);
		return this;
	}

	public ChartMailSender setTo(String to) {
		if (to == null) {
			throw new IllegalArgumentException("to cannot be null");
		}
		this.to = new String[] { to };
		return this;
	}

	public ChartMailSender setTo(String[] to) {
		if (to == null) {
			throw new IllegalArgumentException("to cannot be null");
		}
		this.to = to;
		return this;
	}

	public ChartMailSender setFrom(String from) {
		if (from != null) {
			this.from = from;
		}
		return this;
	}

	public ChartMailSender setSubject(String subject) {
		this.subject = subject;
		return this;
	}

	public ChartMailSender setByte(boolean isByte) {
		this.isByte = isByte;
		return this;
	}

	public ChartMailSender setByteArray(byte[] byteArray) {
		if (byteArray == null) {
			throw new IllegalArgumentException("byteArray cannot be null");
		}
		this.byteArray = byteArray;
		return this;
	}

	public ChartMailSender setFileName(String fileName) {
		if (fileName == null) {
			throw new IllegalArgumentException("fileName cannot be null");
		}
		this.fileName = fileName;
		return this;
	}

	/**
	 * 发送邮件，失败时抛出异常
	 * 
	 * @throws Exception
	 */
	public void send() throws Exception {
		MimeMessage mimeMessage = javaMailSender.createMimeMessage();
		// 第二个参数设置为true
		MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
		helper.setTo(to);
		helper.setFrom(from);
		helper.setSubject(subject);
		// 第二个参数为true表示需要内容为HTML格式
		helper.setText(content.toString(), true);
		int imageCount = imageFileIds.size();
		if (imageCount > 0) {
			for (int i = 0; i < imageCount; i++) {
				helper.addInline(imageFileIds.get(i), imageFiles.get(i));
			}
		}
		//新增是否字节码数组发送附件 2016-11-25 east
		if( !isByte){
			if (attachments.size() > 0) {
				for (File file : attachments) {
					helper.addAttachment(MimeUtility.encodeWord(file.getName()), file);
				}
			}
		}else{
			if (byteArray != null) {
				helper.addAttachment(MimeUtility.encodeWord(fileName), new ByteArrayResource(byteArray));
			}
		}
		javaMailSender.send(mimeMessage);
	}

	/**
	 * 异步方式发送邮件
	 *
	 * @author hubing
	 * @date 2014-9-19 下午2:19:24
	 * @version V1.0
	 */
	public void asyncSend() {
		executor.execute(new Runnable() {
			@Override
			public void run() {
				try {
					send();
				} catch (Exception e) {
					logger.error("发送邮件出现异常" + e);
				}
			}
		});
	}

	public static void main(String[] args) throws Exception {
		loadConfig((String) null);
		String[] emails = (args.length >= 1 ? args[0].split(",") : new String[] { "ten@mazing.com" });
		JavaMailSender jms = ChartMailSender.createJavaMailSender();
		ChartMailSender sender = new ChartMailSender(jms);
		sender.setSubject("test");
		sender.setTo(emails);
		sender.append("This is a test.");
		sender.send();
	}
}
