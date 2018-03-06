/*
 * Copyright (C) 2011 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.itzheng.and.modules.activity;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Toolbar;

import org.itzheng.and.activity.window.IWindowStatus;
import org.itzheng.and.activity.window.helper.WindowStatusHelper;
import org.itzheng.and.modules.R;

/**
 * This activity demonstrates some of the available ways to reduce the size or visual contrast of
 * the system decor, in order to better focus the user's attention or use available screen real
 * estate on the task at hand.
 */
public class MySystemUIModesActivity extends AppCompatActivity implements IWindowStatus {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_sysytem_ui_modes);
//        Window.FEATURE_SUPPORT_ACTION_BAR
        getWindow().addFlags(android.support.v7.app.AppCompatDelegate.FEATURE_SUPPORT_ACTION_BAR);
        android.support.v7.widget.Toolbar toolbar = findViewById(R.id.toolBar);
        setSupportActionBar(toolbar);
        initView();
    }

    private void initView() {
        CheckBox cbFullScreen = findViewById(R.id.cbFullScreen);
        cbFullScreen.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                setFullScreen(isChecked);
            }
        });
        CheckBox cbHideActionBar = findViewById(R.id.cbHideActionBar);
        cbHideActionBar.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                setHideActionBar(isChecked);
            }
        });
        CheckBox cbTranslucentStatus = findViewById(R.id.cbTranslucentStatus);
        cbTranslucentStatus.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                setTranslucentStatus(isChecked);
            }
        });
        CheckBox cbTranslucentNav = findViewById(R.id.cbTranslucentNav);
        cbTranslucentNav.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                setTranslucentNavigation(isChecked);
            }
        });
        final CheckBox cbHintNav = findViewById(R.id.cbHintNav);
        cbHintNav.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                setHideNavigation(isChecked);
            }
        });
        setOnSystemUiVisibilityChangeListener(new View.OnSystemUiVisibilityChangeListener() {
            @Override
            public void onSystemUiVisibilityChange(int visibility) {
                cbHintNav.setChecked(isHintNavigation());
            }
        });
        CheckBox cbStatusBarHasShadow = findViewById(R.id.cbStatusBarHasShadow);
        cbStatusBarHasShadow.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                setStatusBarDarkMode(isChecked);
            }
        });

        CheckBox cbNavigationBarColor = findViewById(R.id.cbNavigationBarColor);
        cbNavigationBarColor.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    setNavigationBarColor(Color.RED);
                } else {
                    setNavigationBarColor(Color.BLACK);
                }
            }
        });
        CheckBox cbStatusBarColor = findViewById(R.id.cbStatusBarColor);
        cbStatusBarColor.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    setStatusBarColor(Color.RED);
                } else {
                    setStatusBarColor(getResources().getColor(R.color.colorPrimaryDark));
                }
            }
        });
    }

    @Override
    public void setFullScreen(boolean on) {
        getWindowStatusHelper().setFullScreen(on);
    }

    @Override
    public void setHideActionBar(boolean on) {
        getWindowStatusHelper().setHideActionBar(on);
    }

    @Override
    public void setTranslucentStatus(boolean on) {
        getWindowStatusHelper().setTranslucentStatus(on);
    }

    @Override
    public void setTranslucentStatus(boolean on, boolean isFullTranslucent) {
        getWindowStatusHelper().setTranslucentStatus(on, isFullTranslucent);
    }

    @Override
    public void setTranslucentNavigation(boolean on) {
        getWindowStatusHelper().setTranslucentNavigation(on);
    }

    @Override
    public void setHideNavigation(boolean on, boolean isSticky) {
        getWindowStatusHelper().setHideNavigation(on, isSticky);
    }

    @Override
    public void setHideNavigation(boolean on) {
        getWindowStatusHelper().setHideNavigation(on);
    }

    @Override
    public boolean isHintNavigation() {
        return getWindowStatusHelper().isHintNavigation();
    }

    @Override
    public void setNavigationBarColor(int color) {
        getWindowStatusHelper().setNavigationBarColor(color);
    }


    public void setOnSystemUiVisibilityChangeListener(View.OnSystemUiVisibilityChangeListener l) {
//        getWindowStatusHelper().setOnSystemUiVisibilityChangeListener(l);
        getWindow().getDecorView().setOnSystemUiVisibilityChangeListener(l);
    }

    IWindowStatus mIWindowStatus;

    IWindowStatus getWindowStatusHelper() {
        if (mIWindowStatus == null) {
            mIWindowStatus = WindowStatusHelper.newInstance(this);
        }
        return mIWindowStatus;
    }

    @Override
    public void setStatusBarDarkMode(boolean on) {
        getWindowStatusHelper().setStatusBarDarkMode(on);
//        super.setStatusBarDarkMode(on);
//        if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.LOLLIPOP) {
//            if (on) {
//                setStatusBarColor(0x0fff0000);
//            } else {
//                setStatusBarColor(Color.TRANSPARENT);
//            }
//
//        }
    }

    @Override
    public void setStatusBarColor(int color) {
        getWindowStatusHelper().setStatusBarColor(color);
    }

    @Override
    public void setKeepScreenOn(boolean on) {
        getWindowStatusHelper().setKeepScreenOn(on);
    }
}
