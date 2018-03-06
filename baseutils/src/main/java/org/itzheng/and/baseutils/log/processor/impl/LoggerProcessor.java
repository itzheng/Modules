package org.itzheng.and.baseutils.log.processor.impl;

import com.orhanobut.logger.Logger;

import org.itzheng.and.baseutils.log.processor.ILogProcessor;


/**
 * 参考：https://github.com/orhanobut/logger
 * compile 'com.orhanobut:logger:1.15'
 * Created by WL001 on 2017/5/4.
 */

public class LoggerProcessor implements ILogProcessor {
    static {
//        Logger.addLogAdapter(new AndroidLogAdapter());
        Logger.init("LoggerProcessor")        // default PRETTYLOGGER or use just init()
                .methodCount(1)                 // default 2
//                .hideThreadInfo()               // default shown
//                .logLevel(LogLevel.NONE)        // default LogLevel.FULL
                .methodOffset(2);
    }

    @Override
    public void v(String tag, String msg) {
        Logger.t(tag).v(msg);
    }

    @Override
    public void d(String tag, String msg) {
        Logger.t(tag).d(msg);
    }

    @Override
    public void i(String tag, String msg) {
        Logger.t(tag).i(msg);
    }

    @Override
    public void w(String tag, String msg) {
        Logger.t(tag).w(msg);
    }

    @Override
    public void e(String tag, String msg) {
        Logger.t(tag).e(msg);
    }

    @Override
    public void json(String tag, String msg) {
        Logger.t(tag).json(msg);
    }
}
