package com.yogu.commons.log.test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * @author ten 2016/1/11.
 */
public class StatLog {

    public static String getStoreId(String line) {
        int pos1 = line.indexOf("storeId=");
        if (pos1 < 0) {
            return null;
        }
        int pos2 = line.indexOf("&", pos1);
        if (pos2 > 0) {
            return line.substring(pos1 + "storeId=".length(), pos2);
        }
        else {
            return line.substring(pos1 + "storeId=".length());
        }
    }

    public static String getDid(String line) {
        int pos1 = line.indexOf("did=");
        if (pos1 < 0) {
            return null;
        }
        int pos2 = line.indexOf("&", pos1);
        if (pos2 > 0) {
            return line.substring(pos1 + "did=".length(), pos2);
        }
        else {
            return line.substring(pos1 + "did=".length());
        }
    }

    public static void main(String[] args) throws Exception {
//        SimpleDateFormat sdf = new SimpleDateFormat("dd/MMM/yyyy:HH:mm:ss", Locale.ENGLISH);
//        System.out.println(sdf.format(new Date()));

        BufferedReader reader = new BufferedReader(new FileReader(new File("e:/temp/store_log.txt")));
        String line = null;
        Map<String, StoreStat> map = new HashMap<>(200);
        Map<String, Integer> ipMap = new HashMap<>(20000);
        Map<String, Integer> didMap = new HashMap<>(20000);
        while ((line = reader.readLine()) != null) {
            line = line.trim();
            if (line.length() > 0) {
                String ip = null;
                String storeId = null;
                if (line.indexOf('|') > 0) {
                    // 新格式
                    int pos = line.indexOf('|');
                    ip = line.substring(0, pos).trim();
                }
                else {
                    // 旧格式
                    int pos = line.indexOf(' ');
                    ip = line.substring(0, pos).trim();
                }
                storeId = getStoreId(line);
                String did = getDid(line);
                if (storeId != null && did != null) {
                    StoreStat stat = map.get(storeId);
                    if (stat == null) {
                        stat = new StoreStat();
                        map.put(storeId, stat);
                    }
                    stat.visitCount++;

                    String key = storeId + "_" + ip;
                    Integer count = ipMap.get(key);
                    if (count == null) {
                        ipMap.put(key, 1);
                        stat.ipCount += 1;
                    }

                    key = storeId + "_" + did;
                    Integer didCount = didMap.get(key);
                    if (didCount == null) {
                        didMap.put(key, 1);
                        stat.didCount += 1;
                    }
                }

            }
        }

        BufferedReader reader2 = new BufferedReader(new FileReader(new File("e:/temp/store_name.txt")));
        Map<String, String> nameMap = new HashMap<>(300);
        while ((line = reader2.readLine()) != null) {
            line = line.trim();
            String[] array = line.split("\\|");
            if (array.length >= 3) {
                nameMap.put(array[1].trim(), array[2].trim());
            }
        }

        Iterator<Map.Entry<String, StoreStat>> iter = map.entrySet().iterator();
        while (iter.hasNext()) {
            Map.Entry<String, StoreStat> entry = iter.next();
            StoreStat stat = entry.getValue();
            String storeName = nameMap.get(entry.getKey());
            if (storeName != null) {
                System.out.println(entry.getKey() + "\t" + storeName + "\t" + stat.visitCount + "\t" + stat.ipCount + "\t" + stat.didCount);
            }
        }
        reader.close();
    }

    static class StoreStat {
        public int ipCount = 0;
        public int visitCount = 0;
        public int didCount = 0;
        public String storeName;
    }
}
