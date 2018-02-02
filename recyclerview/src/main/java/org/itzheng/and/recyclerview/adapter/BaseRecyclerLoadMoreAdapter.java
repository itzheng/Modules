package org.itzheng.and.recyclerview.adapter;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * 一个加载更多的适配器，有顶部和底部两个监听
 * 后期如果可以，会加入顶部显示和隐藏监听，如果可以再集成动画
 *
 * @param <VH>
 */
public abstract class BaseRecyclerLoadMoreAdapter<VH extends RecyclerView.ViewHolder>
        extends MyRecyclerViewAdapter<VH> {
    /**
     * 设置 RecyclerView 到达顶部和底部的监听，虽然可以根据Adapter去获取，但是比较麻烦，直接让调用者传入
     *
     * @param recyclerView
     * @param onTopOrBottomListener
     */
    public void setOnTopOrBottomListener(RecyclerView recyclerView, OnTopOrBottomListener onTopOrBottomListener) {
        if (recyclerView != null) {
            recyclerView.addOnScrollListener(new RecyclerViewScrollListener(onTopOrBottomListener));
        }
    }

    public void setOnScrollShowOrGoneListener(RecyclerView recyclerView, OnScrollShowOrGoneListener onScrollShowOrGoneListener) {
        if (recyclerView != null) {
            recyclerView.addOnScrollListener(new RecyclerViewScrollListener(onScrollShowOrGoneListener));
        }
    }

    /**
     * Recycleview滚动到顶部或者底部的监听，
     * Adapter需要自己去测量监听
     */
    public interface OnTopOrBottomListener {
        void onScrollToTop(RecyclerView recyclerView);

        void onScrollToBottom(RecyclerView recyclerView);
    }

    /**
     * 滚动监听，显示或隐藏顶部view
     */
    public interface OnScrollShowOrGoneListener {
        void onShow();

        void onGone();

    }

    private class RecyclerViewScrollListener extends RecyclerView.OnScrollListener {

        private static final int THRESHOLD = 20;
        private int distance = 0;
        private boolean visible = true;//是否可见

        private static final String TAG = "GoodsRecyclerViewScroll";
        int firstVisibleItemPosition = -1;
        /**
         * 顶部或底部监听
         */
        private OnTopOrBottomListener mOnTopOrBottomListener;

        public RecyclerViewScrollListener(OnTopOrBottomListener onTopOrBottomListener) {
            mOnTopOrBottomListener = onTopOrBottomListener;
        }

        private OnScrollShowOrGoneListener mOnScrollShowOrGoneListener;

        public RecyclerViewScrollListener(OnScrollShowOrGoneListener onScrollShowOrGoneListener) {
            mOnScrollShowOrGoneListener = onScrollShowOrGoneListener;
        }

        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);
//        !(ViewCompat.canScrollVertically(recyclerView, -1)  顶部是否可以滚动
//        !ViewCompat.canScrollVertically(recyclerView, 1)  底部是否可以滚动
            if (mOnTopOrBottomListener != null) {
                if (!ViewCompat.canScrollVertically(recyclerView, -1)) {
                    mOnTopOrBottomListener.onScrollToTop(recyclerView);
                } else if (!ViewCompat.canScrollVertically(recyclerView, 1)) {
                    mOnTopOrBottomListener.onScrollToBottom(recyclerView);
                }
            }

            //
            RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
            if (layoutManager instanceof GridLayoutManager) {
                firstVisibleItemPosition = ((GridLayoutManager) layoutManager).findFirstVisibleItemPosition();

            } else if (layoutManager instanceof LinearLayoutManager) {
                firstVisibleItemPosition = ((LinearLayoutManager) layoutManager).findFirstVisibleItemPosition();
            }
            if (mOnScrollShowOrGoneListener == null) {
                return;
            }
            if (firstVisibleItemPosition == 0) {
                mOnScrollShowOrGoneListener.onShow();
                return;
            }

            /**
             * dy:Y轴方向的增量
             * 有正和负
             * 当正在执行动画的时候，就不要再执行了
             */
//        if (distance > THRESHOLD && visible) {
//            //隐藏动画
//            visible = false;
//            mRecyclerViewScrollListener.onGone();
//            distance = 0;
//        } else if (distance < -20 && !visible) {
//            //显示动画
//            visible = true;
//            mRecyclerViewScrollListener.onShow();
//            distance = 0;
//        }
//        if (visible && dy > 0 || (!visible && dy < 0)) {
//            distance += dy;
//        }
            if (distance > THRESHOLD) {
                //隐藏动画
                visible = false;
                mOnScrollShowOrGoneListener.onGone();
                distance = 0;
            } else if (distance < -20) {
                //显示动画
                visible = true;
                mOnScrollShowOrGoneListener.onShow();
                distance = 0;
            }
            if (visible && dy > 0 || (!visible && dy < 0)) {
                distance += dy;
            }
        }
    }

    /**
     * 动画显示工具
     */
    public static class ShowOrGoneAnimator {
        /**
         * 显示隐藏动画时长
         */
        private final int ANIMATOR_DURATION = 300;
        /**
         * 动画执行完毕后是否将工具栏隐藏。主要是动画执行有个延时
         */
        private boolean isGone = false;

        public static ShowOrGoneAnimator newInstance() {
            return new ShowOrGoneAnimator();
        }

        public void show(View view) {
            if (view.getVisibility() != View.VISIBLE || isGone) {
                isGone = false;
                view.setAlpha(0);
                view.setVisibility(View.VISIBLE);
                ObjectAnimator anim = ObjectAnimator.ofFloat(view, "alpha", 0f, 0.5f, 1f);
                anim.setDuration(ANIMATOR_DURATION);// 动画持续时间
                anim.start();
            }
        }

        public void gone(final View view) {
            if (view.getVisibility() != View.GONE) {
                isGone = true;
                view.setAlpha(1);
                ObjectAnimator anim = ObjectAnimator.ofFloat(view, "alpha", 1f, 0.5f, 0f);
                anim.setDuration(ANIMATOR_DURATION);// 动画持续时间
                anim.addListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animator animation) {
                        if (isGone) {
                            view.setVisibility(View.GONE);
                        }

                    }

                    @Override
                    public void onAnimationCancel(Animator animation) {

                    }

                    @Override
                    public void onAnimationRepeat(Animator animation) {

                    }
                });
                anim.start();
            }

        }
    }
}