package com.yogu.commons.server.version;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yogu.commons.utils.Args;
import com.yogu.commons.utils.ArrayUtils;

public class VersionServlet extends HttpServlet {

	private static final long serialVersionUID = 5046083495160228881L;

	private String[] accessLines;

	@Override
	public void init() throws ServletException {
		String path = getInitParameter("version.file.path");
		Args.notEmpty(path, "'version file path'");

		InputStream is = null;
		BufferedReader br = null;
		List<String> list = null;
		try {
			is = this.getClass().getResourceAsStream(path);
			br = new BufferedReader(new InputStreamReader(is));

			list = new LinkedList<String>();
			String line;
			while (null != (line = br.readLine()))
				list.add(line);

			accessLines = list.toArray(new String[list.size()]);
		} catch (IOException e) {
			accessLines = ArrayUtils.toArray("Error reading the version information", path, e.getMessage());
		} finally {
			if (null != is)
				try {
					is.close();
				} catch (IOException e) {
				}
			if (null != br)
				try {
					br.close();
				} catch (IOException e) {
				}
		}

		Args.check(!ArrayUtils.isEmpty(accessLines), "Do not read version information");
	}

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setHeader("Content-type", "text/html;charset=UTF-8");
		resp.setCharacterEncoding("UTF-8");
		PrintWriter writer = resp.getWriter();
		for (String line : accessLines) {
			writer.print(line);
			writer.write("<br>");
		}
		writer.flush();
	}

}
