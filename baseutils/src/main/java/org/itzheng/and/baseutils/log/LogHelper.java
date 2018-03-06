package org.itzheng.and.baseutils.log;

import android.util.Log;

import org.itzheng.and.baseutils.log.processor.ILogProcessor;
import org.itzheng.and.baseutils.log.processor.impl.LoggerProcessor;
import org.itzheng.and.baseutils.log.processor.impl.SysLogProcessor;

/**
 * Created by daniel on 17-4-27.
 */

public class LogHelper {
    private static ILogProcessor mLogProcessor;
    /**
     * 要显示的log等级：
     * Log.VERBOSE为全部显示
     * Log.ERROR + 1为不显示log
     */
    private static int showLogLevel = Log.VERBOSE;

    public static void init(ILogProcessor iLogProcessor) {
        mLogProcessor = iLogProcessor;
    }

    /**
     * 默认处理器
     */
    static {
        mLogProcessor = new LoggerProcessor();
    }

    public static void v(String tag, String msg) {
        if (mLogProcessor == null || showLogLevel > Log.VERBOSE) {

        } else {
            mLogProcessor.v(tag, msg);
        }
    }

    public static void d(String tag, String msg) {
        if (mLogProcessor == null || showLogLevel > Log.DEBUG) {

        } else {
            mLogProcessor.d(tag, msg);
        }
    }

    public static void i(String tag, String msg) {
        if (mLogProcessor == null || showLogLevel > Log.INFO) {

        } else {
            mLogProcessor.i(tag, msg);
        }
    }

    public static void json(String tag, String msg) {
        if (mLogProcessor == null || showLogLevel > Log.INFO) {

        } else {
            mLogProcessor.json(tag, msg);
        }
    }

    public static void w(String tag, String msg) {
        if (mLogProcessor == null || showLogLevel > Log.WARN) {

        } else {
            mLogProcessor.w(tag, msg);
        }
    }

    public static void e(String tag, String msg) {
        if (mLogProcessor == null || showLogLevel > Log.ERROR) {

        } else {
            mLogProcessor.e(tag, msg);
        }
    }

}
