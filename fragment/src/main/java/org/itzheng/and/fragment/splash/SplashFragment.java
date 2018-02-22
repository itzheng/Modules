package org.itzheng.and.fragment.splash;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.ScaleAnimation;

import org.itzheng.and.fragment.R;

/**
 * Created by root on 17-5-7.
 */

public class SplashFragment extends Fragment {
    private View rootView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (rootView == null) {
            rootView = LayoutInflater.from(getContext()).inflate(R.layout.fragment_splash, null);
        }
        return rootView;
    }

    /**
     * 开始执行
     */
    public void start() {
        initAnim();
    }

    /**
     * 图片动画效果
     */
    private void initAnim() {
        AnimationSet set = new AnimationSet(false);

        ScaleAnimation scale = new ScaleAnimation(1, 1, 1, 1, Animation.RELATIVE_TO_SELF, 1f,
                Animation.RELATIVE_TO_SELF, 1f);
        scale.setDuration(1000);
        scale.setFillAfter(true);

        AlphaAnimation alpha = new AlphaAnimation(0, 1);
        alpha.setDuration(100);
        alpha.setFillAfter(true);

        // set.addAnimation(rotate);
        set.addAnimation(scale);
        set.addAnimation(alpha);

        set.setAnimationListener(new Animation.AnimationListener() {

            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                if (mOnEndListener != null) {
                    mOnEndListener.onEnd(SplashFragment.this);
                }
            }
        });
        //开始动画
        rootView.startAnimation(set);
    }

    private OnEndListener mOnEndListener;

    /**
     * 动画执行完毕的回调
     *
     * @param onEndListener
     */
    public void setOnEndListener(OnEndListener onEndListener) {
        mOnEndListener = onEndListener;
    }
}
