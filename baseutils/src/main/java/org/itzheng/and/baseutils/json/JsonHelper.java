package org.itzheng.and.baseutils.json;

import org.itzheng.and.baseutils.json.processor.IJsonProcessor;
import org.itzheng.and.baseutils.json.processor.impl.FastjsonProcessor;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

/**
 * JSON解析工具
 * Created by daniel on 17-4-27.
 */

public class JsonHelper {
    private static final String TAG = "JsonHelper";
    private static IJsonProcessor mJsonProcessor;

    public static void init(IJsonProcessor iJsonProcessor) {
        mJsonProcessor = iJsonProcessor;
    }

    /**
     * 默认处理器
     *
     */
    static {
        mJsonProcessor = new FastjsonProcessor();
    }

    public static String toJson(Object obj) {
        if (mJsonProcessor != null) {
            return mJsonProcessor.toJson(obj);
        }
        return null;
    }


    public static <T> T fromJson(String str, Type type) {
        if (mJsonProcessor != null) {
            return mJsonProcessor.fromJson(str, type);
        }
        return null;
    }


    public static <T> T fromJson(String str, Class<T> type) {
        if (mJsonProcessor != null) {
            return mJsonProcessor.fromJson(str, type);
        }
        return null;
    }


    public static <T> String mapToJson(Map<String, T> map) {
        if (mJsonProcessor != null) {
            return mJsonProcessor.mapToJson(map);
        }
        return null;
    }


    public static <T> List<T> jsonToList(String str, Class<T> type) {
        if (mJsonProcessor != null) {
            return mJsonProcessor.jsonToList(str, type);
        }
        return null;
    }
}
