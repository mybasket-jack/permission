package com.jack006.util;


import lombok.extern.slf4j.Slf4j;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.ser.impl.SimpleFilterProvider;
import org.codehaus.jackson.type.TypeReference;

import java.io.IOException;

/**
 * json对象转换工具类
 *
 * @Author jack
 * @Since 1.0 2020/1/26 13:45
 */
@Slf4j
public class JsonMapper {

    private static ObjectMapper objectMapper = new ObjectMapper();

    static {
        // config 排除空的字段
        objectMapper.disable(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES);
        objectMapper.configure(SerializationConfig.Feature.FAIL_ON_EMPTY_BEANS,false);
        objectMapper.setFilters(new SimpleFilterProvider().setFailOnUnknownId(false));
        objectMapper.setSerializationInclusion(JsonSerialize.Inclusion.NON_EMPTY);
    }

    /**
     * 对象转字符串
     * @param src
     * @param <T>
     * @return
     */
    public static <T> String obj2String(T src) {
        if (src == null ) {
            return null;
        }
        try {
            return src instanceof String ? (String) src : objectMapper.writeValueAsString(src);
        } catch (IOException e) {
            log.warn("parse object to  String exception, error:{}",e);
            return null;
        }
    }

    /**
     * 字符串转对象
     * @param src
     * @param tTypeReference
     * @param <T>
     * @return
     */
    public static <T> T string2Obj (String src, TypeReference<T> tTypeReference) {
        if (src == null || tTypeReference == null) {
            return null;
        }
        try {
            return (tTypeReference.getType()).equals(String.class) ? (T) src : objectMapper.readValue(src, tTypeReference);
        } catch (IOException e) {
            log.warn("parse String to Object exception, String: {}, TypeRefernce<T>:{}, error:{}",src,tTypeReference.getType(),e);
            return null;
        }
    }
}
