package org.itzheng.and.recyclerview.decoration;

import android.content.Context;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.widget.LinearLayout;

/**
 * Title:<br>
 * Description: <br>
 *
 * @email ItZheng@ZoHo.com
 * Created by itzheng on 2018-2-1.
 */
public class DecorationManager {
    /**
     * 显示类型
     */
    public static class ShowDivider {
        /**
         * Don't show any dividers.
         */
        public static final int SHOW_DIVIDER_NONE = 0b0 & 0xff;
        /**
         * Show a divider at the beginning of the group.
         */
        public static final int SHOW_DIVIDER_BEGINNING = 0b1 & 0xff;
        /**
         * Show dividers between each item in the group.
         */
        public static final int SHOW_DIVIDER_MIDDLE = 0b10 & 0xff;
        /**
         * Show a divider at the end of the group.
         */
        public static final int SHOW_DIVIDER_END = 0b100 & 0xff;
    }


    public class StaggeredGridLayoutManager {

    }

    /**
     * 线性布局的分割线
     */
    public static class LinearLayoutManager {
        //不用设置 Orientation 方向，因为可以从 RecyclerView 中去获取
        public static RecyclerView.ItemDecoration newItemDecoration(int showDivider, int size, int color) {
            return newItemDecoration(showDivider, size, color, 0, 0, 0, 0);
        }

        public static RecyclerView.ItemDecoration newItemDecoration(int showDivider, int size, int color,
                                                                    int left, int top, int right, int bottom) {
            LinearLayoutDivider divider = new LinearLayoutDivider();
            divider.setMargin(left, top, right, bottom);
            divider.setShowDivider(showDivider);
            divider.setDividerSize(size);
            divider.setDividerColor(color);
            return divider;
        }
    }

    /**
     * 网格布局的分割线
     */
    public static class GridLayoutManager {

    }

}
