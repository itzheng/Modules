package org.itzheng.and.recyclerview.decoration;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.support.annotation.ColorRes;
import android.support.annotation.DimenRes;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

/**
 * https://blog.piasy.com/2016/03/26/Insight-Android-RecyclerView-ItemDecoration/
 */
public class LinearLayoutDivider extends RecyclerView.ItemDecoration {
    private Drawable mDivider;
    private int mSize;

    /**
     * 如果需要自定义背景，可以使用
     *
     * @param divider
     */
    public void setDivider(Drawable divider) {
        mDivider = divider;
    }


    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        Log.d(TAG, "onDraw:");
        int left;
        int right;
        int top;
        int bottom;
        final int childCount = parent.getChildCount();
        if (getOrientation(parent) == LinearLayoutManager.HORIZONTAL) {
            top = parent.getPaddingTop() + mMarginTop;
            bottom = parent.getHeight() - parent.getPaddingBottom() - mMarginBottom;

            for (int i = 0; i < childCount - 1; i++) {
                final View child = parent.getChildAt(i);
                final RecyclerView.LayoutParams params =
                        (RecyclerView.LayoutParams) child.getLayoutParams();
                left = child.getRight() + params.rightMargin;
                right = left + mSize;
                mDivider.setBounds(left, top, right, bottom);
                mDivider.draw(c);
            }
        } else {
            left = parent.getPaddingLeft() + mMarginLeft;
            right = parent.getWidth() - parent.getPaddingRight() - mMarginRight;
            //当这个返回true时，如果有padding，分割线会显示在padding上面，需要进行细节处理
            boolean clipToPadding = parent.getClipToPadding();
            int paddingTop = parent.getPaddingTop();
            for (int i = 0; i < childCount; i++) {
                final View child = parent.getChildAt(i);
                final RecyclerView.LayoutParams params =
                        (RecyclerView.LayoutParams) child.getLayoutParams();
                //添加顶部分割线
                if (i == 0) {
                    if ((mShowDivider & DecorationManager.ShowDivider.SHOW_DIVIDER_BEGINNING) != 0) {
                        //如果是显示顶部的，那么第一条就显示
                        //应该可以兼容多机型
                        top = child.getBottom() - child.getHeight() - mSize - mMarginBottom;//-底部间距，就是距离底部，顶部不用管
                        bottom = top + mSize;
//                        Log.d(TAG, " parent.getPaddingTop():" + parent.getPaddingTop() + " top:" + top);
                        top = getNewTop(top, paddingTop, clipToPadding);
                        mDivider.setBounds(left, top, right, bottom);
                        mDivider.draw(c);
                    }
                }
                //底部
                if (i == childCount - 1) {
                    if ((mShowDivider & DecorationManager.ShowDivider.SHOW_DIVIDER_END) != 0) {
                        top = child.getBottom() + params.bottomMargin + mMarginTop;//+距离顶部
                        bottom = top + mSize;
                        top = getNewTop(top, paddingTop, clipToPadding);
                        mDivider.setBounds(left, top, right, bottom);
                        mDivider.draw(c);
                    }
                } else
                    //中间
                    if ((mShowDivider & DecorationManager.ShowDivider.SHOW_DIVIDER_MIDDLE) != 0) {
                        top = child.getBottom() + params.bottomMargin + mMarginTop;//+距离顶部
                        bottom = top + mSize;
                        top = getNewTop(top, paddingTop, clipToPadding);
                        mDivider.setBounds(left, top, right, bottom);
                        mDivider.draw(c);
                    }
            }
        }
    }

    /**
     * 重新计算top绘制范围，如果clipToPadding，因为Chaild在padding里面，不可见，所以，分割线也应该隐藏
     *
     * @param top
     * @param paddingTop
     * @param clipToPadding
     * @return
     */
    private int getNewTop(int top, int paddingTop, boolean clipToPadding) {
        if (!clipToPadding) {
            return top;
        }
        return top < paddingTop ? paddingTop : top;
    }

    private int getOrientation(RecyclerView recyclerView) {
        int defOrientation = 0;
        if (recyclerView == null) {
            return defOrientation;
        }
        RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
        if (layoutManager instanceof LinearLayoutManager) {
            LinearLayoutManager linearLayoutManager = (LinearLayoutManager) layoutManager;
            return linearLayoutManager.getOrientation();
        }
        return defOrientation;
    }

    private static final String TAG = "LinearLayoutDivider";

    /**
     * 当itemPosition==0时，说明是第一个item，但是没办法判断总共有几个item，
     * parent.getChildCount()，得到的是当前绘制的时候总共有几个，得到的结果永远都是最后一个
     * 所以，目前就会有这么一个bug，
     * 就是说，中间的间距和底部的间距是同时存在的
     *
     * @param outRect
     * @param itemPosition
     * @param parent
     */
    @Override
    public void getItemOffsets(Rect outRect, int itemPosition, RecyclerView parent) {
        //这个count并不是正确的数字，因为每次填充一个，所以，count会递增
//        final int childCount = parent.getChildCount();
        RecyclerView.Adapter adapter = parent.getAdapter();
        final int childCount = adapter.getItemCount();
        if (getOrientation(parent) == LinearLayoutManager.HORIZONTAL) {
            int left = 0, top = 0, right = mSize, bottom = 0;
            if (itemPosition == 0) {
                if ((mShowDivider & DecorationManager.ShowDivider.SHOW_DIVIDER_BEGINNING) != 0) {
                    //如果顶部有横线，需要设置间距
                    left = mSize;
                }
                if ((mShowDivider & DecorationManager.ShowDivider.SHOW_DIVIDER_MIDDLE) == 0) {
                    //如果顶部有横线，需要设置间距
                    right = 0;
                } else {
                    right = mSize;
                }
            } else if (itemPosition == childCount - 1) {

            }
            outRect.set(left, top, right, bottom);
        } else {
            Log.d(TAG, "itemPosition:" + itemPosition);
            Log.d(TAG, "childCount:" + childCount);
            int left = 0, top = 0, right = 0, bottom = mSize;
            if (itemPosition == 0) {//顶部
                if ((mShowDivider & DecorationManager.ShowDivider.SHOW_DIVIDER_BEGINNING) != 0) {
                    //如果顶部有横线，需要设置间距
                    top = mSize;
                }
                if ((mShowDivider & DecorationManager.ShowDivider.SHOW_DIVIDER_MIDDLE) == 0) {
                    //如果顶部有横线，需要设置间距
                    bottom = 0;
                } else {
                    bottom = mSize;
                }
            } else if (itemPosition == childCount - 1) {
                //底部
                if ((mShowDivider & DecorationManager.ShowDivider.SHOW_DIVIDER_END) == 0) {
//                    //如果顶部有横线，需要设置间距
                    bottom = 0;
                } else {
                    bottom = mSize;
                }
            } else {
                //中间
                if ((mShowDivider & DecorationManager.ShowDivider.SHOW_DIVIDER_MIDDLE) == 0) {
//                    //如果顶部有横线，需要设置间距
                    bottom = 0;
                } else {
                    bottom = mSize;
                }
            }
            Log.d(TAG, "top:" + top + ",bottom:" + bottom);
            //添加实际的高度，要加上边距
            if (top != 0) {
                top = top + mMarginTop + mMarginBottom;
            }
            if (bottom != 0) {

                bottom = bottom + mMarginTop + mMarginBottom;
            }
//            //当这个返回true时，如果有padding，分割线会显示在padding上面，需要进行细节处理
//            boolean clipToPadding = parent.getClipToPadding();
//            if (clipToPadding) {
//                View view = parent.getChildAt(itemPosition);
//                int paddingTop = parent.getPaddingTop();
//                float y = view.getY();
//                Log.d(TAG, "paddingTop:" + paddingTop + " " + "y:" + y);
//                outRect.set(left, top, right, bottom);
//            } else {
//                outRect.set(left, top, right, bottom);
//            }
            outRect.set(left, top, right, bottom);
        }
        //默认都是0
//        super.getItemOffsets(outRect, itemPosition, parent);
    }

    /**
     * 间距
     */
    private int mMarginLeft, mMarginTop, mMarginRight, mMarginBottom;

    /**
     * 设置外边距
     *
     * @param left
     * @param top
     * @param right
     * @param bottom
     */
    public void setMargin(int left, int top, int right, int bottom) {
        mMarginLeft = left;
        mMarginTop = top;
        mMarginRight = right;
        mMarginBottom = bottom;
    }

    private int mShowDivider;

    /**
     * 显示类型 {@link DecorationManager.ShowDivider}
     *
     * @param showDivider
     */
    public void setShowDivider(int showDivider) {
        mShowDivider = showDivider;
    }

    public void setDividerSize(int size) {
        mSize = size;
    }

    public void setDividerColor(int color) {
        mDivider = new ColorDrawable(color);
    }
}
