package com.jack006.util;

import com.google.common.base.Splitter;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 字符串处理类
 *
 * @Author jack
 * @Since 1.0 2020/1/31 16:05
 */
public class StringUtil {

    /**
     * 处理逗号分隔的字符
     * @param str
     * @return
     */
    public static List<Integer> splitToListInt(String str) {
        List<String> strList = Splitter.on(",").trimResults().omitEmptyStrings().splitToList(str);
        List<Integer> list = strList.stream().map(strItem -> Integer.parseInt(strItem)).collect(Collectors.toList());
        return list;
    }
}
