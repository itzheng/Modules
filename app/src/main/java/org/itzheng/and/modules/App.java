package org.itzheng.and.modules;

import android.app.Application;

import org.itzheng.and.baseutils.ui.UIUtils;

/**
 * Title:<br>
 * Description: <br>
 *
 * @email ItZheng@ZoHo.com
 * Created by itzheng on 2018-2-1.
 */
public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        initUtils();
    }

    private void initUtils() {
        UIUtils.init(this);
    }
}
