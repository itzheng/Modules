package org.itzheng.and.recyclerview.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Title:<br>
 * Description: <br>
 *
 * @email ItZheng@ZoHo.com
 * Created by itzheng on 2017/8/21.
 */
public abstract class BaseRecyclerAdapter<VH extends RecyclerView.ViewHolder>
        extends RecyclerView.Adapter<VH> {
    private OnItemClickListener onItemClickListener;
    private OnItemClickListener onItemLongClickListener;
    /**
     * 当前类型为空
     */
    public static final int ITEM_VIEW_TYPE_EMPTY = 0x000011;
    public static final int ITEM_VIEW_TYPE_CHECKED = 0x000012;

    /**
     * 创建真实的view
     *
     * @param parent
     * @param viewType 根据不同的类型进行不同的view创建
     * @return
     */
    public abstract View onCreateView(ViewGroup parent, int viewType);

    @Override
    public void onBindViewHolder(VH holder, final int position, List<Object> payloads) {
        super.onBindViewHolder(holder, position, payloads);
        if (onItemClickListener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onItemClickListener.onItemClick(view, position);
                }
            });
        }
        if (onItemLongClickListener != null) {
            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    onItemLongClickListener.onItemClick(view, position);
                    return true;
                }
            });
        }
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public void setOnItemLongClickListener(OnItemClickListener onItemLongClickListener) {
        this.onItemLongClickListener = onItemLongClickListener;
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }
}
