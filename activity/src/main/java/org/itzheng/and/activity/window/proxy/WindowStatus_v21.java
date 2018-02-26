package org.itzheng.and.activity.window.proxy;

import android.app.Activity;
import android.view.View;

import org.itzheng.and.activity.window.IWindowStatus;

/**
 * Title:状态设置21或以上（21,22）<br>
 * Description: <br>
 *
 * @email ItZheng@ZoHo.com
 * Created by itzheng on 2018-2-24.
 */
public class WindowStatus_v21 implements IWindowStatus {
    public static WindowStatus_v21 newInstance(Activity activity) {
        return new WindowStatus_v21(activity);
    }

    private WindowStatus_v21(Activity activity) {

    }

    @Override
    public void setFullScreen(boolean on) {

    }

    @Override
    public void setHideActionBar(boolean on) {

    }

    @Override
    public void setTranslucentStatus(boolean on) {

    }

    @Override
    public void setTranslucentNavigation(boolean on) {

    }

    @Override
    public void setHideNavigation(boolean on, boolean isSticky) {

    }

    @Override
    public void setHideNavigation(boolean on) {

    }

    @Override
    public boolean isHintNavigation() {
        return false;
    }

    @Override
    public void setNavigationBarColor(int color) {

    }

    @Override
    public void setStatusBarDarkMode(boolean on) {

    }

    @Override
    public void setStatusBarColor(int color) {

    }

    @Override
    public void setKeepScreenOn(boolean on) {

    }
}
