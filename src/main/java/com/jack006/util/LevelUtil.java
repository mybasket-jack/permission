package com.jack006.util;

import org.apache.commons.lang3.StringUtils;

/**
 * TODO
 *
 * @Author jack
 * @Since 1.0 2020/1/26 15:16
 */
public class LevelUtil {

    public final static String SEPARATOR = ".";

    public final static String ROOT = "0";

    // 0
    // 0.1
    // 0.1.2
    public static String calculateLevel(String parentLevel, int parentId){
        // 是否为首层
        if (StringUtils.isBlank(parentLevel)) {
            return ROOT;
        } else {
            return StringUtils.join(parentLevel, SEPARATOR, parentId);
        }
    }
}
