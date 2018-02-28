package org.itzheng.and.activity.window.proxy;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;

import org.itzheng.and.activity.window.IWindowStatus;

/**
 * Title:状态设置23或以上<br>
 * Description: <br>
 *
 * @email ItZheng@ZoHo.com
 * Created by itzheng on 2018-2-24.
 */
public class WindowStatus_v19 implements IWindowStatus {
    public static WindowStatus_v19 newInstance(Activity activity) {
        return new WindowStatus_v19(activity);
    }

    /**
     * 高版本的工具类只有功能需要低版本兼容设置，其他的使用高版本的方法就可以
     */
    private IWindowStatus mWindowStatus;
    private Activity mActivity;
    /**
     * 是否设置透明虚拟返回键
     */
    private boolean isTranslucentNavigation = false;
    /**
     * 是否设置全屏
     */
    private boolean isFullScreen = false;
    /**
     * 是否透明状态栏，如果设置透明虚拟导航栏，则状态栏默认也是透明的
     */
    private boolean isTranslucentStatus = false;

    /**
     * 默认的虚拟返回键颜色
     */
    private static final int DEF_NAVIGATION_COLOR = 0x000000;
    /**
     * 当前的虚拟返回键盘的颜色
     */
    private int mNavigationBarColor = DEF_NAVIGATION_COLOR;

    private WindowStatus_v19(Activity activity) {
        mActivity = activity;
        mWindowStatus = WindowStatus_v21.newInstance(activity);
    }

    @Override
    public void setFullScreen(boolean on) {
        mWindowStatus.setFullScreen(on);
        isFullScreen = on;
    }

    @Override
    public void setHideActionBar(boolean on) {
        mWindowStatus.setHideActionBar(on);
    }

    @Override
    public void setTranslucentStatus(boolean on) {
        mWindowStatus.setTranslucentStatus(on);
        isTranslucentStatus = on;
    }

    @Override
    public void setTranslucentNavigation(boolean on) {
//        mWindowStatus.setTranslucentNavigation(on);
        isTranslucentNavigation = on;
        if (on) {
            View navigationBarView = getNavigationBarView(mActivity);
            if (navigationBarView.getParent() != null) {
                ((ViewGroup) navigationBarView.getParent()).removeView(navigationBarView);
            }
            resetContentPadding();
        } else {
            setNavigationBarColor(mNavigationBarColor);
        }
    }

    @Override
    public void setHideNavigation(boolean on, boolean isSticky) {
        mWindowStatus.setHideNavigation(on, isSticky);
    }

    @Override
    public void setHideNavigation(boolean on) {
        mWindowStatus.setHideNavigation(on);
    }

    @Override
    public boolean isHintNavigation() {
        return mWindowStatus.isHintNavigation();
    }

    @Override
    public void setNavigationBarColor(int color) {
//        mWindowStatus.setNavigationBarColor(color);
        resetContentPadding();
        if (isTranslucentNavigation) {
            //如果是透明虚拟导航栏则不设置颜色
            //并且，不设置paddingbottom
//                View contentView = getContentView();
//                getContentView().setPadding(contentView.getPaddingLeft(), contentView.getPaddingTop(), contentView.getPaddingRight(), 0);
            return;
        }
        // android:clipToPadding="false"
        //android:fitsSystemWindows="true"
        //如果设置透明flag，布局会自动移动到虚拟按钮里面
        //如果不设置透明flag，4.4无效

        mActivity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        //设置padding底部就可以
//            View contentView = getContentView();
//            getContentView().setPadding(contentView.getPaddingLeft(), contentView.getPaddingTop(), contentView.getPaddingRight(), getNavigationBarHeight(this));

        ViewGroup decorView = (ViewGroup) mActivity.getWindow().getDecorView();
        View mNavigationBar = getNavigationBarView(mActivity);
        mNavigationBar.setBackgroundColor(color);
        if (mNavigationBar.getParent() == null) {
            FrameLayout.LayoutParams params;
            params = new FrameLayout.
                    LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, getNavigationBarHeight(mActivity));
            params.gravity = Gravity.BOTTOM;
            mNavigationBar.setLayoutParams(params);
            decorView.addView(mNavigationBar);
        }
    }

    @Override
    public void setStatusBarDarkMode(boolean on) {
        mWindowStatus.setStatusBarDarkMode(on);
    }

    @Override
    public void setStatusBarColor(int color) {
//        mWindowStatus.setStatusBarColor(color);
        // android:clipToPadding="false"
        //android:fitsSystemWindows="true"
        mActivity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        ViewGroup decorView = (ViewGroup) mActivity.getWindow().getDecorView();
        View statusBarView = getStatusBarView(mActivity);
        statusBarView.setBackgroundColor(color);
        if (statusBarView.getParent() == null) {
            FrameLayout.LayoutParams params;
            params = new FrameLayout.
                    LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, getStatusBarHeight(mActivity));
            params.gravity = Gravity.TOP;
            statusBarView.setLayoutParams(params);
            decorView.addView(statusBarView);
        }
    }

    @Override
    public void setKeepScreenOn(boolean on) {
        mWindowStatus.setKeepScreenOn(on);
    }

    private View getContentView() {
        return mActivity.findViewById(android.R.id.content);
    }

    /**
     * 状态改变，重新设置内容的padding，4.4 机型适配方案
     */
    private void resetContentPadding() {
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//            return;
//        } else if (Build.VERSION.SDK_INT < Build.VERSION_CODES.KITKAT) {
//            return;
//        }
        if (!isTranslucentNavigation()) {
            return;
        }
        View contentView = getContentView();
        int paddingTop = 0, paddingBottom = 0;
        if (!isTranslucentNavigation) {
            paddingBottom = getNavigationBarHeight(mActivity);
            if (!isTranslucentStatus && !isFullScreen) {
                paddingTop = getStatusBarHeight(mActivity);
            }
        }
        getContentView().setPadding(contentView.getPaddingLeft(), paddingTop, contentView.getPaddingRight(), paddingBottom);
    }

    /**
     * 是否已经设置透明虚拟返回栏
     *
     * @return
     */
    private boolean isTranslucentNavigation() {
        Window win = mActivity.getWindow();
        WindowManager.LayoutParams winParams = win.getAttributes();
        final int bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION;
        return (winParams.flags | bits) == winParams.flags;
    }

    /**
     * 4.4 -- 5.0 的状态栏
     */
    View mStatusBarView;

    /**
     * 获取状态栏 4.4 -- 5.0 的状态栏
     *
     * @param activity
     * @return
     */
    private View getStatusBarView(Activity activity) {
        if (mStatusBarView == null) {
            mStatusBarView = new View(activity);
        }
        return mStatusBarView;
    }

    /**
     * 获取状态栏的高度
     *
     * @param context
     * @return
     */
    protected int getStatusBarHeight(Context context) {
        int height = 0;
        int id = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (id > 0) {
            height = context.getResources().getDimensionPixelSize(id);
        }
        return height;
    }

    /**
     * 4.4 以下的虚拟导航栏
     */
    private View mNavigationBarView;

    /**
     * 创建一个虚拟导航栏
     *
     * @param activity
     * @return
     */
    private View getNavigationBarView(Activity activity) {
        if (mNavigationBarView == null) {
            mNavigationBarView = new View(activity);
        }
        return mNavigationBarView;
    }

    /**
     * 获取虚拟导航栏的高度
     *
     * @param context
     * @return
     */
    protected int getNavigationBarHeight(Context context) {
        int height = 0;
        int id = context.getResources().getIdentifier("navigation_bar_height", "dimen", "android");
        if (id > 0) {
            height = context.getResources().getDimensionPixelSize(id);
        }
        return height;
    }

}
