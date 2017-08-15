package com.yogu.commons.utils;


import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;


public class Path {

	public static String getClassPath() {
		return Path.class.getResource("/").getPath();
	}

	public static String getClassPath(String fileName) {
		return Path.class.getResource("/").getPath() + fileName;
	}

	/**
	 * 可读入jar包里的文件,传入的参数如：/1.xml
	 * 
	 * @param relaPathFile
	 * @return
	 */
	public static InputStream getResource(String relaPathFile) {
		return Path.class.getResourceAsStream(relaPathFile);
	}

	public static InputStream getClassPathResource(String fileName)
			throws IOException {

		FileInputStream fin = new FileInputStream(getClassPath(fileName));
		return fin;
	}
	
	 public static void main(String args[]) {
		  
		  System.out.println(getClassPath("/"));
	  }
	

}
