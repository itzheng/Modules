package org.itzheng.and.baseutils.json.processor;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

/**
 * Created by daniel on 17-4-27.
 */

public interface IJsonProcessor {
    /**
     * 对象转换成json字符串
     *
     * @param obj
     * @return
     */
    String toJson(Object obj);

    /**
     * json字符串转成对象
     *
     * @param str
     * @param type
     * @return
     */
    <T> T fromJson(String str, Type type);

    /**
     * json字符串转成对象
     *
     * @param str
     * @param type
     * @return
     */
    <T> T fromJson(String str, Class<T> type);

    /**
     * 将Map转化为Json
     *
     * @param map
     * @return String
     */
    <T> String mapToJson(Map<String, T> map);

    /**
     * 将json转成List集合
     *
     * @param str  json字符串
     * @param type 要转成的集合类型,如要转成List<String>,参数传String.class
     * @param <T>  泛型
     * @return 转成的list集合, 如List<String> list;
     */
    <T> List<T> jsonToList(String str, Class<T> type);

    /**
     * @param str           json字符串
     * @param typeReference 要转成的list类型,如要转成List<String>,
     *                      传入参数, new TypeReference<List<String>>() {}
     * @param <T>           泛型
     * @return 转成的list集合, 如List<String> list;
     */
//     <T> List<T> jsonToList(String str, TypeReference<List<T>> typeReference);
}
