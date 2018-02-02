package org.itzheng.and.recyclerview.decoration;

import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.support.annotation.ColorRes;
import android.support.annotation.DimenRes;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * https://blog.piasy.com/2016/03/26/Insight-Android-RecyclerView-ItemDecoration/
 */
public class LinearLayoutDividerTopCenterBottom extends RecyclerView.ItemDecoration {
    private final Drawable mDivider;
    private final int mSize;
    private final int mMarginStart;
    private final int mMarginEnd;
    private final int mOrientation;

    /**
     * 没有间距的直线
     *
     * @param resources
     * @param color
     * @param size
     * @param orientation
     */
    public LinearLayoutDividerTopCenterBottom(Resources resources, @ColorRes int color,
                                              @DimenRes int size, int orientation) {
        mDivider = new ColorDrawable(resources.getColor(color));
        mSize = resources.getDimensionPixelSize(size);
        mMarginStart = 0;
        mMarginEnd = 0;
        mOrientation = orientation;
    }

    /**
     * @param resources
     * @param color
     * @param size
     * @param marginStart 间距，如果方向为垂直，则是左边间距，如果方向为水平则是距离顶部
     * @param marginEnd   间距，如果方向为垂直，则是右边间距，如果方向为水平则是距离底部
     * @param orientation
     */
    public LinearLayoutDividerTopCenterBottom(Resources resources, @ColorRes int color,
                                              @DimenRes int size, int marginStart, int marginEnd, int orientation) {
        mDivider = new ColorDrawable(resources.getColor(color));
        mSize = resources.getDimensionPixelSize(size);
        mMarginStart = resources.getDimensionPixelSize(marginStart);
        mMarginEnd = resources.getDimensionPixelSize(marginEnd);
        mOrientation = orientation;
    }

//    @Override
//    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
//        int left;
//        int right;
//        int top;
//        int bottom;
//        if (mOrientation == LinearLayoutManager.HORIZONTAL) {
//            top = parent.getPaddingTop() + mMarginStart;
//            bottom = parent.getHeight() - parent.getPaddingBottom() - mMarginEnd;
//            final int childCount = parent.getChildCount();
//            for (int i = 0; i < childCount - 1; i++) {
//                final View child = parent.getChildAt(i);
//                final RecyclerView.LayoutParams params =
//                        (RecyclerView.LayoutParams) child.getLayoutParams();
//                left = child.getRight() + params.rightMargin;
//                right = left + mSize;
//                mDivider.setBounds(left, top, right, bottom);
//                mDivider.draw(c);
//            }
//        } else {
//            left = parent.getPaddingLeft() + mMarginStart;
//            right = parent.getWidth() - parent.getPaddingRight() - mMarginEnd;
//            final int childCount = parent.getChildCount();
//            for (int i = 0; i < childCount; i++) {
//                final View child = parent.getChildAt(i);
//                final RecyclerView.LayoutParams params =
//                        (RecyclerView.LayoutParams) child.getLayoutParams();
//                //添加顶部分割线
//                if (i == i) {
//                    top = child.getBottom() - child.getHeight() - mSize;
//                    bottom = top + mSize;
//                    mDivider.setBounds(left, top, right, bottom);
//                    mDivider.draw(c);
//                }
////                top = child.getBottom() + params.bottomMargin;
////                bottom = top + mSize;
////                mDivider.setBounds(left, top, right, bottom);
////                mDivider.draw(c);
//            }
//        }
//    }


    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        int left;
        int right;
        int top;
        int bottom;
        if (mOrientation == LinearLayoutManager.HORIZONTAL) {
            top = parent.getPaddingTop() + mMarginStart;
            bottom = parent.getHeight() - parent.getPaddingBottom() - mMarginEnd;
            final int childCount = parent.getChildCount();
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
            left = parent.getPaddingLeft() + mMarginStart;
            right = parent.getWidth() - parent.getPaddingRight() - mMarginEnd;
            final int childCount = parent.getChildCount();
            for (int i = 0; i < childCount; i++) {
                final View child = parent.getChildAt(i);
                final RecyclerView.LayoutParams params =
                        (RecyclerView.LayoutParams) child.getLayoutParams();
                //添加顶部分割线
                if (i == 0) {
                    //部分机型不能用
//                    top = child.getTop() - params.topMargin;
//                    bottom = top - mSize;
//                    mDivider.setBounds(left, top, right, bottom);
//                    mDivider.draw(c);
                    //应该可以兼容多机型
                    top = child.getBottom() - child.getHeight() - mSize;
                    bottom = top + mSize;
                    mDivider.setBounds(left, top, right, bottom);
                    mDivider.draw(c);
                }
                top = child.getBottom() + params.bottomMargin;
                bottom = top + mSize;
                mDivider.setBounds(left, top, right, bottom);
                mDivider.draw(c);
            }
        }
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent,
                               RecyclerView.State state) {
        if (mOrientation == LinearLayoutManager.HORIZONTAL) {
            outRect.set(0, 0, mSize, 0);
        } else {
            outRect.set(0, 0, 0, mSize);
        }
    }

}
