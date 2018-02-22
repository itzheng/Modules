package org.itzheng.and.activity;

import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

/**
 * Title:<br>
 * Description: <br>
 *
 * @email ItZheng@ZoHo.com
 * Created by itzheng on 2018-2-1.
 */
public class ItActivity extends AppCompatActivity {
    /**
     * 全屏，即隐藏系统状态栏
     * 此时，看不见时间等系统图标，但是并没有隐藏底部的虚拟按键
     *
     * @param on
     */
    protected void setFullscreen(boolean on) {
        Window win = getWindow();
        WindowManager.LayoutParams winParams = win.getAttributes();
        final int bits = WindowManager.LayoutParams.FLAG_FULLSCREEN;
        if (on) {
            winParams.flags |= bits;
        } else {
            winParams.flags &= ~bits;
        }
        win.setAttributes(winParams);
    }

    /**
     * 隐藏系统标题栏
     *
     * @param on
     */
    protected void setHideActionBar(boolean on) {
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            if (on) {
                actionBar.hide();
            } else {
                actionBar.show();
            }
        }

    }

    /**
     * 设置透明状态栏，
     *
     * @param on
     */
    protected void setTranslucentStatus(boolean on) {
        Window win = getWindow();
        WindowManager.LayoutParams winParams = win.getAttributes();
        final int bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
        if (on) {
            winParams.flags |= bits;
        } else {
            winParams.flags &= ~bits;
        }
        win.setAttributes(winParams);
    }

    /**
     * 设置底部虚拟返回键为透明
     * true 虚拟返回键透明，并且布局沉浸到虚拟返回键上，如果此时没有设置状态栏透明，那么布局将自动沉浸到状态栏，只是被顶层颜色覆盖
     * false 布局没有沉浸到虚拟返回键上
     *
     * @param on
     */
    protected void setTranslucentNavigation(boolean on) {
        Window win = getWindow();
        WindowManager.LayoutParams winParams = win.getAttributes();
        final int bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION;
        if (on) {
            winParams.flags |= bits;
        } else {
            winParams.flags &= ~bits;
        }
        win.setAttributes(winParams);
    }

    /**
     * 隐藏底部虚拟返回键,如果触摸到屏幕会自动显示返回键
     *
     * @param on
     */
    protected void setHintNavigation(boolean on) {
        int hideNavigation = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION;
        View decorView = getWindow().getDecorView();
        int systemUiVisibility = decorView.getSystemUiVisibility();
        if (on) {
            decorView.setSystemUiVisibility(systemUiVisibility | hideNavigation);
        }
    }

    /**
     * 当前是否隐藏返回键
     *
     * @return
     */
    protected boolean isHintNavigation() {
        int hideNavigation = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION;
        View decorView = getWindow().getDecorView();
        int systemUiVisibility = decorView.getSystemUiVisibility();
        return (systemUiVisibility | hideNavigation) == systemUiVisibility;
    }

    /**
     * Ui界面改变的监听
     *
     * @param l
     */
    protected void setOnSystemUiVisibilityChangeListener(View.OnSystemUiVisibilityChangeListener l) {
        getWindow().getDecorView().setOnSystemUiVisibilityChangeListener(l);
    }
}
