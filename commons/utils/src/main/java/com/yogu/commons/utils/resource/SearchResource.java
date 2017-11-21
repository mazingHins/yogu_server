package com.yogu.commons.utils.resource;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.Path;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;
import org.springframework.core.type.classreading.SimpleMetadataReaderFactory;

/**
 * 生成菜单数据
 * 
 * @author linyi 2014/2/25
 */
public class SearchResource {

	private static final Logger logger = LoggerFactory
			.getLogger(SearchResource.class);

	/**
	 * 默认的资源路径
	 */
	public static final String DEFAULT_RESOURCE_PATH = "com/mazing/services/backend/**/*.class";

	/**
	 * 菜单描述
	 * 
	 * @author linyi 2014年2月25日
	 */
	private static class MenuDesc {
		private String name;

		/** 父菜单名字 */
		private String parent;
		
		/**
		 * @author chenjianhong
		 * 排列顺序
		 */
		private int sequence;

		public MenuDesc(String name, String parent, int sequence) {
			this.name = name;
			this.parent = parent;
			this.sequence = sequence;
		}

		public String getName() {
			return name;
		}

		public String getParent() {
			return parent;
		}
		
		public int getSequence() {
			return sequence;
		}

		public String toString() {
			return "[name=" + name + ", parent=" + parent + "]";
		}
	}

	/**
	 * 菜单项描述，带有URL
	 * 
	 * @author linyi 2014年2月25日
	 */
	private static class MenuResourceDesc {
		private String name;
		private String url;
		/**
		 * 属于哪个菜单
		 */
		private String menuName;

		public MenuResourceDesc(String name, String url, String menuName) {
			this.name = name;
			this.url = url;
			this.menuName = menuName;
		}

		public String getName() {
			return name;
		}

		public String getUrl() {
			return url;
		}

		public String getMenuName() {
			return menuName;
		}

	}

	// 排除的方法列表
	static Map<String, String> excute = new HashMap<String, String>();
	static {
		excute.put("getObjectFromReqeust", "excuteMethod");
		excute.put("setServletResponse", "excuteMethod");
		excute.put("setServletRequest", "excuteMethod");
		excute.put("getMessageStream", "excuteMethod");
		excute.put("setMessageStream", "excuteMethod");
		excute.put("getParamFromReqeust", "excuteMethod");
		excute.put("getModel", "excuteMethod");
		excute.put("getUserName", "excuteMethod");
		excute.put("clearMessages", "excuteMethod");
		excute.put("hasKey", "excuteMethod");
		excute.put("setActionErrors", "excuteMethod");
		excute.put("getActionErrors", "excuteMethod");
		excute.put("setActionMessages", "excuteMethod");
		excute.put("getActionMessages", "excuteMethod");
		excute.put("getErrorMessages", "excuteMethod");
		excute.put("getErrors", "excuteMethod");
		excute.put("setFieldErrors", "excuteMethod");
		excute.put("getFieldErrors", "excuteMethod");
		excute.put("getTexts", "excuteMethod");
		excute.put("getTexts", "excuteMethod");
		excute.put("addActionError", "excuteMethod");
		excute.put("addActionMessage", "excuteMethod");
		excute.put("addFieldError", "excuteMethod");
		excute.put("doDefault", "excuteMethod");
		excute.put("hasActionErrors", "excuteMethod");
		excute.put("hasActionMessages", "excuteMethod");
		excute.put("hasErrors", "excuteMethod");
		excute.put("hasFieldErrors", "excuteMethod");
		excute.put("clearFieldErrors", "excuteMethod");
		excute.put("clearActionErrors", "excuteMethod");
		excute.put("clearErrors", "excuteMethod");
		excute.put("clearErrorsAndMessages", "excuteMethod");
		excute.put("pause", "excuteMethod");
		excute.put("clone", "excuteMethod");
		excute.put("execute", "excuteMethod");
		excute.put("getLocale", "excuteMethod");
		excute.put("validate", "excuteMethod");
		excute.put("getText", "excuteMethod");
		excute.put("input", "excuteMethod");
		excute.put("wait", "excuteMethod");
		excute.put("hashCode", "excuteMethod");
		excute.put("getClass", "excuteMethod");
		excute.put("equals", "excuteMethod");
		excute.put("toString", "excuteMethod");
		excute.put("notify", "excuteMethod");
		excute.put("notifyAll", "excuteMethod");
		excute.put("setMessageSource", "excuteMethod");
		excute.put("handleServiceException", "excuteMethod");
		excute.put("handleException", "excuteMethod");
		excute.put("getPageCount", "excuteMethod");
		excute.put("initBinder", "excuteMethod");
		excute.put("getRealIp", "excuteMethod");
		excute.put("isLicitIp", "excuteMethod");
	}
	private static final ResourcePatternResolver RESOLVER = new PathMatchingResourcePatternResolver();
	/* Meta信息Reader Factory.用于创建MetaReader */
	private static final MetadataReaderFactory READER_FACTORY = new SimpleMetadataReaderFactory();

	/**
	 * 获取指定的资源文件
	 * 
	 * @param path
	 * @return
	 * @throws java.io.IOException
	 */
	public static Resource[] searchResource(String path) throws IOException {
		String packageSearchPath = ResourcePatternResolver.CLASSPATH_ALL_URL_PREFIX
				+ path;
		// 根据正则表达式,得到资源列表
		Resource[] resources = RESOLVER.getResources(packageSearchPath);
		return resources;
	}

	/**
	 * 扫描系统中所有的菜单，组成菜单树
	 * 
	 * @return
	 */
	public static MenuItem scanMenuTree(String[] resourcePaths) {
		// 保存所有的权限资源数据
		List<MenuDesc> menuList = new ArrayList<MenuDesc>();
		List<MenuResourceDesc> resourceList = new ArrayList<MenuResourceDesc>();
		try {
			for (String resourcePath : resourcePaths) {
				Resource[] resources = searchResource(resourcePath);
				for (Resource resource : resources) {
					// 通过 MetadataReader得到ClassMeta信息,打印类名
					MetadataReader meta = READER_FACTORY
							.getMetadataReader(resource);
					if (meta != null) {
						try {
							Class<?> classZ = Class.forName(meta
									.getClassMetadata().getClassName(), false,
									SearchResource.class.getClassLoader());
							// 如果类是资源类，则加载信息
							if (classZ.isAnnotationPresent(Menu.class)) {
								Menu menu = classZ.getAnnotation(Menu.class);
								Path classPath = classZ.getAnnotation(Path.class);

								String name = menu.name();
								menuList.add(new MenuDesc(menu.name(), menu
										.parent(), menu.sequence()));

								Method[] methods = classZ.getMethods();
								String classPathValue = "";
								if (classPath != null) {
									// 前后加上路径分隔符  /
									classPathValue = classPath.value();
									if (!classPathValue.startsWith("/")) {
										classPathValue = "/" + classPathValue;
									}
									if (!classPathValue.endsWith("/")) {
										classPathValue = classPathValue + "/";
									}
								}
								for (Method method : methods) {
									if (method
											.isAnnotationPresent(MenuResource.class)) {
										MenuResource res = method
												.getAnnotation(MenuResource.class);
										Path path = method
												.getAnnotation(Path.class);

										if (classPath != null && path != null) {
											String resName = res.value();
											String urlPath = (classPathValue + path.value()).replace("//", "/");
											logger.info("resourceList add, urlPath: {}, resName: {}, name: {}", urlPath, resName, name);
											resourceList.add(new MenuResourceDesc(
													resName, urlPath, name));
										}

									} else {
										if (excute.get(method.getName()) == null) {
											// ignore
											;
											// System.out.println(meta
											// .getClassMetadata()
											// .getClassName()
											// + "的方法:"
											// + method.getName()
											// + "没有配置权限.");
										}
									}
								}
							}
						} catch (Exception e) {
							logger.error("读取controller属性出错", e);
						}
					}
				}
			}
		} catch (Exception e) {
			logger.error("扫描controller出错", e);
		}

		// 根元素
		MenuItem root = new MenuItem("root", "", true);
		// 临时map，保存菜单信息: <菜单名, 菜单数据>
		Map<String, MenuItem> tmpMap = new HashMap<String, MenuItem>(
				menuList.size() * 4 / 3 + 16);
		int appendCount = 0; // 记录菜单移到队尾的情况，正常不应该超过menuList.size()
		for (int i = 0; i < menuList.size();) {
			MenuDesc menu = menuList.get(i);
			if (StringUtils.isEmpty(menu.getParent())) {
				if (tmpMap.containsKey(menu.getName())) {
					logger.error("ERROR: 重复的菜单=" + menu.getName());
					throw new IllegalStateException("重复的菜单名, name="
							+ menu.getName());
				} else {
					MenuItem item = new MenuItem(menu.getName(), "");
					item.setSequence(menu.getSequence());
					root.createChildren().add(item);
					tmpMap.put(menu.getName(), item);
				}
				i++;
			} else {
				MenuItem parent = tmpMap.get(menu.getParent());
				if (parent == null) {
					// 找不到父菜单，放到队列后面处理
					if (i == menuList.size() - 1) {
						// 如果已经跑到队列最后，终止，避免死循环
						logger.error("ERROR: 找不到父菜单=" + menu.getParent());
						// break;
						throw new IllegalStateException("找不到父菜单: menu="
								+ menu.getName() + ", parent="
								+ menu.getParent());
					}
					menuList.remove(i);
					menuList.add(menu); // 放到最后
					appendCount++;
					if (appendCount > menuList.size()) {
						throw new IllegalStateException("找不到父菜单，菜单移到队尾错误！menu=" + menu.toString());
					}
				} else {
					// 增加一个子菜单
					MenuItem item = new MenuItem(menu.getName(), "");
					item.setSequence(menu.getSequence());
					parent.createChildren().add(item);
					tmpMap.put(menu.getName(), item);
					i++; // next
				}
			}
		}

		for (MenuResourceDesc res : resourceList) {
			MenuItem parent = tmpMap.get(res.getMenuName());
			if (parent == null) {
				logger.error("ERROR: 菜单找不到=" + res.getMenuName());
				throw new IllegalStateException("资源[" + res.getName()
						+ "] 找不到所属菜单: [" + res.getMenuName() + "]");
			} else {
				MenuItem item = new MenuItem(res.getName(), res.getUrl());
				parent.createChildren().add(item);
			}
		}

		return root;
	}

	/**
	 * 打印整颗菜单树
	 * 
	 * @param item
	 * @param level
	 */
	public static void printMenu(MenuItem item, int level) {
		printSpace(level);
		List<MenuItem> children = item.getChildren();
		if (children == null || children.isEmpty()) {
			System.out.println(item.getName() + ", " + item.getUrl());
		} else {
			System.out.println(item.getName());
			for (MenuItem mi : children) {
				printMenu(mi, level + 1);
			}
		}
	}

	private static void printSpace(int level) {
		for (int i = 0; i < level; i++) {
			System.out.print("\t");
		}
	}

	public static void main(String[] args) {
		MenuItem root = scanMenuTree(new String[] {DEFAULT_RESOURCE_PATH});
		printMenu(root, 0);
	}
}
