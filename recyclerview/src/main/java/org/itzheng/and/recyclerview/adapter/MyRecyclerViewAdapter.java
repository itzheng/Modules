package org.itzheng.and.recyclerview.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

/**
 * Title:RecyclerView的适配器,虽然是针对侧滑菜单的,但是普通recyclerView使用效果一样,建议使用<br>
 * Description: <br>
 * 需要添加依赖
 * compile 'com.yanzhenjie:recyclerview-swipe:1.0.1'
 * Company: <br>
 *
 * @author ZhengYongdong
 * @email ItZheng@ZoHo.com
 * @date 2016/8/25 0025
 */
public abstract class MyRecyclerViewAdapter<VH extends RecyclerView.ViewHolder>
        extends BaseRecyclerAdapter<VH> {
}
