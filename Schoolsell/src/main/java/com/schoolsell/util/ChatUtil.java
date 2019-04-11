package com.schoolsell.util;

import com.schoolsell.entity.Chatreport;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * 聊天工具类
 */
public class ChatUtil {

    public static Map SortBySender(List<Chatreport> list) {

        TreeMap<String, List<Chatreport>> map = new TreeMap<>();
        for (int i = 0; i < list.size(); i++) {
            Chatreport c = list.get(i);
            if (map.containsKey(c.getSendid())) {
                List<Chatreport> al = map.get(c.getSendid());
                al.add(c);
            } else {
                List<Chatreport> arrayList = new ArrayList<>();
                arrayList.add(c);
                map.put(c.getSendid(), arrayList);
            }

        }
        return map;

    }


}