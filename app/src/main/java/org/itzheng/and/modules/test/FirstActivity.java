package org.itzheng.and.modules.test;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import org.itzheng.and.baseutils.common.ArrayUtils;
import org.itzheng.and.baseutils.ui.UIUtils;
import org.itzheng.and.modules.MainActivity;
import org.itzheng.and.modules.base.BaseActivity;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/**
 * Title:<br>
 * Description: <br>
 *
 * @email ItZheng@ZoHo.com
 * Created by itzheng on 2018-2-11.
 */
public class FirstActivity extends BaseActivity {
    ArrayList<String> stringList;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        stringList = getStringList();
        textView = new TextView(this);
        textView.setText(stringList.toString());
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UIUtils.getContext(), SecondActivity.class);
                SecondActivity.putIntent(intent, stringList);
                UIUtils.startActivity(intent);
            }
        });
        textView.setTextColor(Color.RED);
        setContentView(textView);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (textView != null) {
            textView.setText(stringList.toString());
        }
    }

    private ArrayList<String> getStringList() {
        ArrayList<String> strings = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            strings.add(i + "");
        }
        return strings;
    }
}
