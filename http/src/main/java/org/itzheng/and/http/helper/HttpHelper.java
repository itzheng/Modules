package org.itzheng.and.http.helper;

import org.itzheng.and.http.callback.ICallback;
import org.itzheng.and.http.proxy.IHttpProxy;

import java.io.File;

/**
 * Title:<br>
 * Description: <br>
 *
 * @email ItZheng@ZoHo.com
 * Created by itzheng on 2018-2-7.
 */
public class HttpHelper implements IHttpProxy {
    /**
     * 默认的http代理
     */
    private IHttpProxy mDefProxy;
    private IHttpProxy mPostProxy;

    /**
     * 设置默认的网络代理，后期会添加设置不同请求方式的请求
     *
     * @param proxy
     */
    public void setDefaultProxy(IHttpProxy proxy) {
        mDefProxy = proxy;
    }

    /**
     * 单例对象
     */
    private static HttpHelper _instance;

    /**
     * 获取一个实例
     *
     * @return
     */
    public static HttpHelper getInstance() {
        if (_instance == null) {
            //单例，双重验证，提高效率
            synchronized (HttpHelper.class) {
                if (_instance == null) {
                    _instance = new HttpHelper();
                }
            }
        }
        return _instance;
    }

    @Override
    public void post(String url, ICallback callback) {
        mDefProxy.post(url, callback);
    }

    @Override
    public void get(String url, ICallback callback) {
        mDefProxy.get(url, callback);
    }

    @Override
    public IHttpProxy addParam(String key, Object name) {
        return this;
    }

    @Override
    public IHttpProxy addHeader(String key, Object name) {
        return this;
    }

    @Override
    public void upload(String url, File file, String fileName, IHttpProxy httpCallBack) {

    }

    @Override
    public void download(String url, File file, String fileDir, String fileName, IHttpProxy httpCallBack) {

    }

    @Override
    public IHttpProxy setTag(Object tag) {
        return this;
    }

    @Override
    public void addJson(String json) {

    }

    @Override
    public void cancel() {

    }
}
