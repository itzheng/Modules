package org.itzheng.and.baseutils.json.processor.impl;


import org.itzheng.and.baseutils.json.processor.IJsonProcessor;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

/**
 * Created by daniel on 17-4-27.
 */

public class ObjctJsonProcessor implements IJsonProcessor {
    @Override
    public String toJson(Object obj) {
        return null;
    }

    @Override
    public <T> T fromJson(String str, Type type) {
        return null;
    }

    @Override
    public <T> T fromJson(String str, Class<T> type) {
        return null;
    }

    @Override
    public <T> String mapToJson(Map<String, T> map) {
        return null;
    }

    @Override
    public <T> List<T> jsonToList(String str, Class<T> type) {
        return null;
    }
}
