package org.itzheng.and.modules;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import org.itzheng.and.baseutils.ui.UIUtils;
import org.itzheng.and.modules.activity.BleActivity;
import org.itzheng.and.modules.activity.DemoSystemUIModes;
import org.itzheng.and.modules.activity.FrameMainActivity;
import org.itzheng.and.modules.activity.MySystemUIModesActivity;
import org.itzheng.and.modules.activity.RecyclerViewActivity;
import org.itzheng.and.modules.activity.SystemUIModes;
import org.itzheng.and.modules.adapter.MainAdapter;
import org.itzheng.and.modules.base.BaseActivity;
import org.itzheng.and.modules.bean.ActItem;
import org.itzheng.and.modules.test.FirstActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity {
    RecyclerView rcvContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rcvContent = findViewById(R.id.rcvContent);
        initData();
//        UIUtils.startActivity(FirstActivity.class);
    }

    private void initData() {
        List<ActItem> actItems = new ArrayList<>();
        ActItem item = null;
        item = new ActItem();
        item.name = "Ble";
        item.activity = BleActivity.class;
        actItems.add(item);
        //
        item = new ActItem();
        item.name = "RecyclerView";
        item.activity = RecyclerViewActivity.class;
        actItems.add(item);
        //
        item = new ActItem();
        item.name = "Frame";
        item.activity = FrameMainActivity.class;
        actItems.add(item);
        //SystemUIModes
        item = new ActItem();
        item.name = "SystemUIModes";
        item.activity = SystemUIModes.class;
        actItems.add(item);
        //
        item = new ActItem();
        item.name = "MySystemUIModesActivity";
        item.activity = MySystemUIModesActivity.class;
        actItems.add(item);
        //DemoSystemUIModes
        item = new ActItem();
        item.name = "DemoSystemUIModes";
        item.activity = DemoSystemUIModes.class;
        actItems.add(item);
        //
        setAdapter(actItems);
    }

    private void setAdapter(List<ActItem> actItems) {
        MainAdapter adapter = new MainAdapter();
        adapter.setItems(actItems);
        rcvContent.setLayoutManager(new LinearLayoutManager(this));
        rcvContent.setAdapter(adapter);


    }
}
