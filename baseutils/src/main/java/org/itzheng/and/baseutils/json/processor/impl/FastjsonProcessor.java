package org.itzheng.and.baseutils.json.processor.impl;


import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;

import org.itzheng.and.baseutils.json.processor.IJsonProcessor;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

/**
 * 参考：https://github.com/alibaba/fastjson
 * <p>
 * compile 'com.alibaba:fastjson:1.2.31'
 * Created by daniel on 17-4-27.
 */

public class FastjsonProcessor implements IJsonProcessor {
    private static final String TAG = "FastjsonProcessor";

    @Override
    public String toJson(Object obj) {
        String json = null;
        try {
            json = JSON.toJSONString(obj);
        } catch (JSONException e) {
            e.printStackTrace();
            Log.e(TAG, e.toString());
        } finally {
            return json;
        }
    }

    @Override
    public <T> T fromJson(String str, Type type) {
        T t = null;
        try {
            t = JSON.parseObject(str, type);
        } catch (JSONException e) {
            e.printStackTrace();
            Log.e(TAG, e.toString());
        } finally {
            return t;
        }
    }

    @Override
    public <T> T fromJson(String str, Class<T> type) {
        T t = null;
        try {
            t = JSON.parseObject(str, type);
        } catch (JSONException e) {
            e.printStackTrace();
            Log.e(TAG, e.toString());
        } finally {
            return t;
        }
    }

    @Override
    public <T> String mapToJson(Map<String, T> map) {
        String json = null;
        try {
            json = JSON.toJSONString(map);
        } catch (JSONException e) {
            e.printStackTrace();
            Log.e(TAG, e.toString());
        } finally {
            return json;
        }
    }

    @Override
    public <T> List<T> jsonToList(String str, Class<T> type) {
        List<T> t = null;
        try {
            t = JSON.parseArray(str, type);
        } catch (JSONException e) {
            e.printStackTrace();
            Log.e(TAG, e.toString());
        } finally {
            return t;
        }
    }
}
