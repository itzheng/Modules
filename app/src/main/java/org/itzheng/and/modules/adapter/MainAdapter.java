package org.itzheng.and.modules.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.itzheng.and.baseutils.common.ArrayUtils;
import org.itzheng.and.baseutils.ui.UIUtils;
import org.itzheng.and.modules.R;
import org.itzheng.and.modules.bean.ActItem;
import org.itzheng.and.recyclerview.adapter.MyRecyclerViewAdapter;

import java.util.List;

/**
 * Title:<br>
 * Description: <br>
 *
 * @email ItZheng@ZoHo.com
 * Created by itzheng on 2018-2-1.
 */
public class MainAdapter extends MyRecyclerViewAdapter<MainAdapter.ViewHolder> {

    @Override
    public View onCreateView(ViewGroup parent, int viewType) {
        return UIUtils.inflateAdapterView(R.layout.adapter_item_main, parent);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(onCreateView(parent, viewType));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final ActItem item = mItems.get(position);
        (holder.tvName).setText(item.name);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (item.activity == null) {
                    return;
                }
                UIUtils.startActivity(item.activity);
            }
        });
    }

    @Override
    public int getItemCount() {
        return ArrayUtils.size(mItems);
    }

    List<ActItem> mItems;

    public void setItems(List<ActItem> items) {
        mItems = items;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvName;

        public ViewHolder(View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvName);
        }
    }
}
