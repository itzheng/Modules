package org.itzheng.and.baseutils.log.processor;

/**
 * Created by daniel on 17-4-27.
 */

public interface ILogProcessor {
    void v(String tag, String msg);

    void d(String tag, String msg);

    void i(String tag, String msg);

    void w(String tag, String msg);

    void e(String tag, String msg);

    void json(String tag, String msg);

}
