package com.yogu.commons.server.mock;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yogu.commons.server.context.MainframeBeanFactory;
import com.yogu.commons.server.mock.service.MockManageService;
import com.yogu.commons.utils.JsonUtils;
import com.yogu.commons.utils.StringUtils;

public class MockManageServlet extends HttpServlet {

	private static final long serialVersionUID = -7516520273443796101L;

    public static String MOCK_FILE_KEY;

	private MockManageService mockAPIService;

    /*
     * （非 Javadoc）
     * 
     * @see javax.servlet.GenericServlet#init(javax.servlet.ServletConfig)
     */
    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);

        MOCK_FILE_KEY = config.getInitParameter("mock.file.key");
    }

    /*
	 * （非 Javadoc）
	 * 
	 * @see javax.servlet.http.HttpServlet#service(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		MockManageService service = mockAPIService();

		if (!service.isAvailable()) {
			resp.getWriter().write("Unable to use the stuff.");
			return;
		}

		String msg = null;
		String method = req.getParameter("m");
		if (StringUtils.isNotBlank(method)) {
			// add | update
			if ("a".equals(method)) {
				addUpdate(service, req);
				msg = "保存成功。";

				// delete
			} else if ("d".equals(method)) {
				String key = req.getParameter("key");
				service.delete(key);
				msg = "删除成功。";

				// reload
			} else if ("r".equals(method)) {
				service.reload();
				msg = "重载成功！";

			} else {
				resp.getWriter().write("In order to know the operation.");
				return;
			}
		}

		select(service, req, resp, msg);
	}

	private MockManageService mockAPIService() {
		if (null == mockAPIService)
			mockAPIService = MainframeBeanFactory.getBean(MockManageService.class);
		return mockAPIService;
	}

	private void addUpdate(MockManageService service, HttpServletRequest req) {
		String key = req.getParameter("key");
		String value = req.getParameter("value").replaceAll("\n", "").replaceAll("\\:", ":");
		service.add(key, value);
	}

	private void select(MockManageService service, HttpServletRequest req, HttpServletResponse resp, String msg) throws IOException {
		String select = req.getParameter("select");
		Map<String, String> result = service.select(select);

		resp.setHeader("Content-type", "text/html;charset=UTF-8");
		resp.setCharacterEncoding("UTF-8");
		PrintWriter pw = resp.getWriter();

		String path = req.getContextPath() + req.getServletPath();
		Writer writer = new Writer(pw);

		writer.write("<html>");
		writer.write("<head>");
		writer.write("<title>Mock Manage</title>");
		writer.write("<script language=\"javascript\" type=\"text/javascript\">");
		writer.write("	var msg = '", false);
		if (null != msg)
			writer.write(msg, false);
		writer.write("';");
		writer.write("	var data = ", false);
		writer.write(null == result ? "{}" : JsonUtils.toJSONString(result), false);
		writer.write(";");

		writer.write("	function search() {");
		writer.write("		var str = document.getElementById('search').value;");
		writer.write("		subDate('', '', '', str);");
		writer.write("	}");

		writer.write("	function reload() {");
		writer.write("		subDate('r', '', '', '');");
		writer.write("	}");

		writer.write("	function save() {");
		writer.write("		var key = document.getElementById('saveKey').value;");
		writer.write("		var value = document.getElementById('saveValue').value;");
		writer.write("		subDate('a', key, value, '');");
		writer.write("	}");

		writer.write("	function del(key) {");
		writer.write("		subDate('d', key, '', '');");
		writer.write("	}");

		writer.write("	function update(key) {");
		writer.write("		var value = data[key];");
		writer.write("		document.getElementById('saveKey').value = key;");
		writer.write("		document.getElementById('saveValue').value = value;");
		writer.write("	}");

		writer.write("	function subDate(m, key, value, select) {");
		writer.write("		document.getElementById('m').setAttribute('value', m);");
		writer.write("		document.getElementById('key').setAttribute('value', key);");
		writer.write("		document.getElementById('value').setAttribute('value', value);");
		writer.write("		document.getElementById('select').setAttribute('value', select);");
		writer.write("		document.getElementById('myform').submit();");
		writer.write("	}");

		writer.write("	function init() {");
		writer.write("		if (msg)");
		writer.write("			alert(msg);");
		writer.write("		if (data) {");
		writer.write("			var tb = document.getElementById('tb');");
		writer.write("			var length = tb.rows.length;");
		writer.write("			for ( var key in data) {");
		writer.write("				if (typeof (data[key]) != 'function') {");
		writer.write("					var value = data[key];");
		// writer.write("					alert(key + '  >>  ' + value);");// debug

		writer.write("					var newTR = tb.insertRow(length++);");
		writer.write("					var newNameTD = newTR.insertCell(0);");
		writer.write("					newNameTD.innerHTML = key;");
		writer.write("					var newNameTD = newTR.insertCell(1);");
		writer.write("					newNameTD.innerHTML = value;");
		writer.write("					var newNameTD = newTR.insertCell(2);");
		writer.write("					newNameTD.innerHTML = '<input type=\"button\" value=\"修改\" onclick=\"javascript:update(\\'' + key + '\\');\">'");
		writer.write("							+ '<input type=\"button\" value=\"删除\" onclick=\"javascript:del(\\'' + key + '\\');\">';");
		writer.write("				}");
		writer.write("			}");
		writer.write("		}");
		writer.write("	}");
		writer.write("</script>");
		writer.write("</head>");
		writer.write("<body>");
		writer.write("	<div>");
		writer.write("		Key Matcher:");
		writer.write("		<input id=\"search\" value=\"", false);
		if (null != select)
			writer.write(select, false);
		writer.write("\">");
		writer.write("		<input type=\"button\" value=\"查询\" onclick=\"javascript:search();\"> : service.user.*");
		writer.write("	</div>");

		writer.write("	<table id=\"tb\" border=\"1\" style=\"border-collapse: collapse;width: 100%;\">");
		writer.write("		<tr bgcolor=\"eaf1ff\">");
		writer.write("			<th style=\"text-align: center; width: 15%;\">KEY</th>");
		writer.write("			<th style=\"text-align: center; width: 75%;\">VALUE</th>");
		writer.write("			<th style=\"text-align: center; width: 10%;\">操作</th>");
		writer.write("		</tr>");
		writer.write("		<tr>");
		writer.write("			<td><textarea id=\"saveKey\" rows=\"2\" style=\"width: 100%;\"></textarea></td>");
		writer.write("			<td><textarea id=\"saveValue\" rows=\"2\" style=\"width: 100%;\"></textarea></td>");
		writer.write("			<td><input type=\"button\" value=\"保存\" onclick=\"javascript:save();\"><input type=\"button\" value=\"重新加载\" onclick=\"javascript:reload();\"></td>");
		writer.write("		</tr>");
		writer.write("	</table>");

		writer.write("	<form id=\"myform\" method=\"post\" action=\"", false);
		writer.write(path, false);
		writer.write("\" style=\"display: none;\">");
		writer.write("		<input type=\"hidden\" id=\"m\" name=\"m\">");
		writer.write("		<input type=\"hidden\" id=\"key\" name=\"key\">");
		writer.write("		<input type=\"hidden\" id=\"value\" name=\"value\">");
		writer.write("		<input type=\"hidden\" id=\"select\" name=\"select\">");
		writer.write("	</form>");
		writer.write("</body>");
		writer.write("</html>");

		writer.write("<script language=\"javascript\" type=\"text/javascript\">");
		writer.write("	init();");
		writer.write("</script>");

		writer.flush();
	}

}

class Writer {

	BufferedWriter bw;

	public Writer(PrintWriter w) {
		bw = new BufferedWriter(w);
	}

	public void write(String text) throws IOException {
		write(text, true);
	}

	public void write(String text, boolean newLine) throws IOException {
		bw.write(text);
		if (newLine)
			bw.newLine();
	}

	public void flush() throws IOException {
		bw.flush();
	}

}