package org.itzheng.and.modules.activity;

import android.graphics.Color;
import android.graphics.LinearGradient;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import org.itzheng.and.baseutils.ui.UIUtils;
import org.itzheng.and.modules.R;
import org.itzheng.and.modules.adapter.MainAdapter;
import org.itzheng.and.modules.base.BaseActivity;
import org.itzheng.and.modules.bean.ActItem;
import org.itzheng.and.recyclerview.decoration.DecorationManager;
import org.itzheng.and.recyclerview.decoration.LinearLayoutDivider;

import java.util.ArrayList;
import java.util.List;

/**
 * Title:<br>
 * Description: <br>
 *
 * @email ItZheng@ZoHo.com
 * Created by itzheng on 2018-2-1.
 */
public class RecyclerViewActivity extends BaseActivity {
    RecyclerView rcvContent;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);
        rcvContent = findViewById(R.id.rcvContent);
        setAdapter();
    }

    MainAdapter adapter;

    private void setAdapter() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rcvContent.setLayoutManager(layoutManager);
        adapter = new MainAdapter();
        adapter.setItems(getDatas());
        rcvContent.setAdapter(adapter);
        RecyclerView.ItemDecoration decoration = DecorationManager.LinearLayoutManager.
                newItemDecoration(
                        DecorationManager.ShowDivider.SHOW_DIVIDER_BEGINNING |
                                DecorationManager.ShowDivider.SHOW_DIVIDER_MIDDLE |
                                DecorationManager.ShowDivider.SHOW_DIVIDER_END |
                                DecorationManager.ShowDivider.SHOW_DIVIDER_NONE
                        ,
                        UIUtils.dip2px(5), Color.RED
                        //左上右下
                        , UIUtils.dip2px(10), UIUtils.dip2px(120), UIUtils.dip2px(30), UIUtils.dip2px(10)
                );
        LinearLayoutDivider divider = (LinearLayoutDivider) decoration;
        divider.setDivider(UIUtils.getDrawable(R.drawable.shape_gradient));
        rcvContent.addItemDecoration(divider);

    }

    private List<ActItem> getDatas() {
        List<ActItem> items = new ArrayList<>();
        ActItem item = null;
        item = new ActItem();
        item.name = "kkkkkkk";
        items.add(item);
        item = new ActItem();
        item.name = "kkkkkkk";
        items.add(item);
        item = new ActItem();
        item.name = "kkkkkkk";
        items.add(item);
        item = new ActItem();
        item.name = "kkkkkkk";
        items.add(item);
        return items;
    }
}
