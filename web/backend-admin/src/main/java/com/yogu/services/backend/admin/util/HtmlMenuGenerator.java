package com.yogu.services.backend.admin.util;

import java.util.List;

import com.yogu.commons.utils.resource.MenuItem;

/**
 * 生成HTML菜单
 * @author linyi
 * 2014年2月27日
 */
public class HtmlMenuGenerator {
//
//	/**
//	 * 每级菜单的CSS名称，最多3级，2级对应LEVEL_CSS[2]，以此类推
//	 */
//	private static final String[] LEVEL_CSS = new String[] {
//		"",
//		"",
//		"nav-second-level",
//		"nav-third-level",
//		"",
//		""
//	};

	/**
	 * 输出菜单的HTML代码
	 * @param root - 根节点，不会输出
	 * @return 菜单的HTML代码
	 */
	public static String outputHtml(MenuItem root) {
		List<MenuItem> children = root.createChildren();
		StringBuilder buf = new StringBuilder(1024);
		int level = 0;
		for (MenuItem item : children) {
			outputMenu(item, buf, level + 1);
		}
		//buf.append("</ul>\n");
		return buf.toString();
	}

	/**
	 * 递归输出每个菜单的HTML代码
	 * @param menu - 当前菜单项
	 * @param buf - 输出缓存
	 * @param level - 第几级菜单
	 */
	private static void outputMenu(MenuItem menu, StringBuilder buf, int level) {
		List<MenuItem> children = menu.getChildren();
		String jspxUrl = findJspUrl(children);
		//		boolean active = activeUrl.equals(jspxUrl);
		String space = "\t";
		if (level == 2) {
			space = "\t\t\t";
		} else if (level >= 3) {
			space = "\t\t\t\t";
		}

		// 如果这个菜单下面的children，全是 .jspx, .do，说明已经到最里面的菜单了，直接输出 jspx页面的链接
		if (children == null || children.isEmpty() || jspxUrl != null) {
			buf.append(space).append("<li>");
			// 已经到了最底的子页面
			jspxUrl = jspxUrl == null ? "" : jspxUrl;
			if (jspxUrl.indexOf("dashboard.mazing.com") > 0) {
				buf.append("<a href=\"javascript:void(0)\" onClick=\"gotodashboard('" + jspxUrl + "');\"><i class=\"fa fa-link\"></i>").append(menu.getName()).append("</a>");
			} else {
				buf.append("<a href=\"").append(jspxUrl).append("\"><i class=\"fa fa-link\"></i>").append(menu.getName()).append("</a>");
			}
			buf.append("</li>\n");
		} else {
			buf.append(space);
			if (level == 1) {
				buf.append("<li class=\"treeview\">\n");
			} else {
				buf.append("<ul class=\"treeview-menu\">\n");
			}
			buf.append(space).append("\t").append("<a href=\"#\">");
			//if (level > 1) {
			// 有下级菜单输出一个小图标
			buf.append("<i class=\"fa fa-list-ul\"></i>");
			//}

			buf.append(menu.getName()).append("<i class=\"fa fa-angle-left pull-right\"></i></a>\n");
			buf.append(space).append("\t").append("<ul class=\"treeview-menu\">\n");
			for (MenuItem child : children) {
				// render next level
				outputMenu(child, buf, level + 1);
			}
			buf.append(space).append("\t").append("</ul>\n");
			buf.append(space);
			if (level == 1) {
				buf.append("</li>\n");
			} else {
				buf.append("</ul>\n");
			}
		}

	}

	/**
	 * 找出子菜单下的 JSP 页面
	 * @param children
	 * @return jsp页面的URL
	 */
	private static String findJspUrl(List<MenuItem> children) {
		if (children == null) {
			return null;
		}
		String jspxUrl = null;
		for (MenuItem mi : children) {
			if (mi.getUrl().length() == 0) {
				// children下的菜单没有URL，说明还不是最终的JSPX子页面
				return null;
			} else if (mi.getUrl().indexOf(".xhtm") > 0 || mi.getUrl().indexOf("dashboard.mazing.com") > 0) {
				jspxUrl = mi.getUrl();
			}
		}
		return jspxUrl;
	}
}
