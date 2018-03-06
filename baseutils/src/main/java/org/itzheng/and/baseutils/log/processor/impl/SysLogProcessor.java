package org.itzheng.and.baseutils.log.processor.impl;

import android.util.Log;

import org.itzheng.and.baseutils.log.processor.ILogProcessor;


/**
 * Created by daniel on 17-4-27.
 */

public class SysLogProcessor implements ILogProcessor {
    @Override
    public void v(String tag, String msg) {
        Log.v(tag, msg);
    }

    @Override
    public void d(String tag, String msg) {
        Log.d(tag, msg);
    }

    @Override
    public void i(String tag, String msg) {
        Log.i(tag, msg);
    }

    @Override
    public void w(String tag, String msg) {
        Log.w(tag, msg);
    }

    @Override
    public void e(String tag, String msg) {
        Log.e(tag, msg);
    }

    @Override
    public void json(String tag, String msg) {
        i(tag, msg);
    }
}
