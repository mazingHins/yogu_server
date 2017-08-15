package com.yogu.commons.utils.jar;

import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.util.*;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

/**
 * 检查重复、冲突的 class
 * @author ten 2015/12/14.
 */
public class CheckDupClasses {

    public static class DupClass {
        private String jarFile;

        private String className;

        public DupClass(String jarFile, String className) {
            this.jarFile = jarFile;
            this.className = className;
        }

        public String getJarFile() {
            return jarFile;
        }

        public void setJarFile(String jarFile) {
            this.jarFile = jarFile;
        }

        public String getClassName() {
            return className;
        }

        public void setClassName(String className) {
            this.className = className;
        }
    }

    /**
     * 检测是否有冲突的类，如果有冲突，返回冲突的类名
     * @param path 要检测的 jar 的路径
     * @return 返回冲突的类列表，如果没有数据，返回size=0的List
     */
    public static List<DupClass> check(String path) throws IOException {
        if (StringUtils.isBlank(path)) {
            return Collections.emptyList();
        }
        File src = new File(path);
        if (src.exists() && src.canRead()) {
            File[] jarFiles = src.listFiles(new FileFilter() {
                @Override
                public boolean accept(File pathname) {
                    return pathname.isFile() && pathname.getName().endsWith(".jar");
                }
            });
            Map<String, DupClass> map = new HashMap<>(4096);
            List<DupClass> list = new LinkedList<>();
            for (File file : jarFiles) {
                JarFile jarFile = new JarFile(file);
                Enumeration files = jarFile.entries();;
                while(files.hasMoreElements()) {
                    JarEntry entry = (JarEntry) files.nextElement();
                    String name = entry.getName();
                    if (name.endsWith(".class")) {
                        if (map.containsKey(name)) {
                            list.add(new DupClass(file.getName(), name));
                            list.add(map.get(name));
                            DupClass tmp = map.get(name);
                            System.out.println(file.getName() + "\t->\t" + name);
                            System.out.println(tmp.getJarFile() + "\t->\t" + tmp.getClassName());
                        }
                        else {
                            map.put(name, new DupClass(file.getName(), name));
                        }
                    }
                }
            }
        }
        return Collections.emptyList();
    }

    public static void main(String[] args) throws Exception {
        //check("F:\\work\\mazing\\code4\\mazing_server\\web\\backend-admin\\target\\mazing-backend-admin\\WEB-INF\\lib");
        check("e:\\temp\\jar");
    }
}
