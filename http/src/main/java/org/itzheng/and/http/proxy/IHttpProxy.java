package org.itzheng.and.http.proxy;

import org.itzheng.and.http.callback.ICallback;

import java.io.File;

/**
 * Title:网络请求的规范<br>
 * Description: <br>
 *
 * @email ItZheng@ZoHo.com
 * Created by itzheng on 2018-2-7.
 */
public interface IHttpProxy {
    /**
     * 发送post请求
     *
     * @param url
     * @param callback
     */
    void post(String url, ICallback callback);

    /**
     * 发送get请求
     *
     * @param url
     * @param callback
     */
    void get(String url, ICallback callback);

    /**
     * 添加请求参数
     *
     * @param key
     * @param name
     * @return
     */
    IHttpProxy addParam(String key, Object name);

    /**
     * 添加请求头部
     *
     * @param key
     * @param name
     * @return
     */
    IHttpProxy addHeader(String key, Object name);

    /**
     * 上传文件
     *
     * @param url
     * @param file
     * @param httpCallBack
     */
    void upload(String url, File file, String fileName, IHttpProxy httpCallBack);

    /**
     * 下载文件
     *
     * @param url
     * @param file
     * @param fileDir
     * @param fileName
     * @param httpCallBack
     */
    void download(String url, File file, String fileDir, String fileName, IHttpProxy httpCallBack);

    /**
     * 设置tag对象。即请求的实例
     *
     * @param tag
     * @return
     */
    IHttpProxy setTag(Object tag);

    /**
     * 添加json请求
     *
     * @param json
     */
    void addJson(String json);

    /**
     * 取消当前请求
     */
    void cancel();

}
