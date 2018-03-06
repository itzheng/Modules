package org.itzheng.and.modules.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.view.View;
import android.view.ViewStub;

import org.itzheng.and.baseutils.log.LogHelper;
import org.itzheng.and.fragment.guide.GuideFragment;
import org.itzheng.and.fragment.main.MainFragment;
import org.itzheng.and.fragment.menu.MenuFragment;
import org.itzheng.and.fragment.splash.OnEndListener;
import org.itzheng.and.fragment.splash.SplashFragment;
import org.itzheng.and.modules.R;
import org.itzheng.and.modules.base.BaseActivity;

/**
 * Title:<br>
 * Description: <br>
 *
 * @email ItZheng@ZoHo.com
 * Created by itzheng on 2018-2-22.
 */
public class FrameMainActivity extends BaseActivity {
    /**
     *
     */
    ViewStub vsMainContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frame_main);
        showSplash();
    }

    /**
     * 显示启动页
     */
    private void showSplash() {
        setHideActionBar(true);
        setTranslucentNavigation(true);
//        setStatusBarDarkMode(true);
        setTranslucentStatus(true);
        setStatusBarDarkMode(false);
        final FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        final SplashFragment splashFragment = new SplashFragment();
        fragmentTransaction.replace(R.id.flFragmentContent, splashFragment);
        fragmentTransaction.commit();
        getWindow().getDecorView().post(new Runnable() {
            @Override
            public void run() {
                splashFragment.start();
            }
        });
        splashFragment.setOnEndListener(new OnEndListener() {
            @Override
            public void onEnd(Fragment fragment) {
                if (false) {
                    //提示更新
                } else {
                    showContent();
                }
                getSupportFragmentManager().beginTransaction().remove(fragment).commit();
            }
        });
    }

    boolean isFirst = true;

    /**
     * 显示需要显示的内容，如引导页，主界面
     */
    private void showContent() {
        if (isFirst) {
            isFirst = false;
            showGuide();
        } else {
            showMain();
        }
    }

    MenuFragment menuFragment = MenuFragment.newFragment();
    MainFragment mainFragment = MainFragment.newFragment();
    DrawerLayout drawerLayout;

    private void showMain() {
        setTranslucentNavigation(false);
        setStatusBarDarkMode(false);
        setNavigationBarColor(getResources().getColor(R.color.colorPrimaryDark));
        setNavigationBarColor(Color.TRANSPARENT);
        vsMainContent = (ViewStub) findViewById(R.id.vsMainContent);
        if (vsMainContent != null) {
            //初始化main主界面的布局
            vsMainContent.inflate();
        }
        drawerLayout = (DrawerLayout) findViewById(R.id.dlMainDrawerLayout);
        drawerLayout.addDrawerListener(new DrawerLayout.DrawerListener() {
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {

            }

            @Override
            public void onDrawerOpened(View drawerView) {
                menuFragment.setTextColor(Color.GREEN);
            }

            @Override
            public void onDrawerClosed(View drawerView) {

            }

            @Override
            public void onDrawerStateChanged(int newState) {

            }
        });
//        //禁用侧拉
//        drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
//        mainFragment.setOnIconClickListener(new MainFragment.OnIconClickListener() {
//            @Override
//            public void onIconClickListener(View view) {
//                //打开侧滑菜单
////                drawerLayout.openDrawer(Gravity.LEFT);
////                SPUtils.put(SpKey.BOOL_SCREEN_ORIENTATION, !SPUtils.getBoolean(SpKey.BOOL_SCREEN_ORIENTATION, false));
////                setScreenOrientation();
//            }
//        });


        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.flMainContent, mainFragment);
        fragmentTransaction.replace(R.id.flMenuContent, menuFragment);
        fragmentTransaction.commit();
    }


    /**
     * 显示引导界面
     */
    private void showGuide() {
        final FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        final GuideFragment fragment = new GuideFragment();
        fragmentTransaction.replace(R.id.flFragmentContent, fragment);
        fragmentTransaction.commit();
        fragment.setOnEndListener(new OnEndListener() {
            @Override
            public void onEnd(Fragment f) {
                getSupportFragmentManager().beginTransaction().remove(f).commit();
                showMain();
            }
        });
        //这个是为了模拟结束
        getWindow().getDecorView().post(new Runnable() {
            @Override
            public void run() {
                fragment.start();
            }
        });
    }

    private static final String TAG = "FrameMainActivity";

    @Override
    public void onBackPressed() {
//        super.onBackPressed();
        LogHelper.d(TAG, "onBackPressed");
        showSplash();
    }
}
