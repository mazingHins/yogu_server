package com.yogu.services.store.resource.app;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class FileUtils {

	public static void write(String path, String content, String encoding) throws IOException {
		File file = new File(path);
		file.delete();
		file.createNewFile();
		BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), encoding));
		writer.write(content);
		writer.close();
	}

	public static String read(String path, String encoding) throws IOException {
		String content = "";
		File file = new File(path);
		BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file), encoding));
		String line = null;
		while ((line = reader.readLine()) != null) {
			content += line + "\n";
		}
		reader.close();
		return content;
	}

	public static void main(String[] args) throws IOException {
//		String content = "中文内容";
		String path = "E:\\BUNGA_WEBSITE_2018_01_18\\SQLDUMP.sql";
		String newpath = "E:\\BUNGA_WEBSITE_2018_01_18\\sql.txt";
		String encoding = "utf-8";
//		FileUtils.write(path, content, encoding);
		String content = FileUtils.read(path, encoding);
		FileUtils.write(newpath, content, encoding);
	}

}
