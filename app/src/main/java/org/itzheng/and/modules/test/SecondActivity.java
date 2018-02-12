package org.itzheng.and.modules.test;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import org.itzheng.and.modules.MainActivity;
import org.itzheng.and.modules.base.BaseActivity;

import java.util.ArrayList;

/**
 * Title:<br>
 * Description: <br>
 *
 * @email ItZheng@ZoHo.com
 * Created by itzheng on 2018-2-11.
 */
public class SecondActivity extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ArrayList<String> stringList = getStringList(getIntent());
        stringList.add("end");
        TextView textView = new TextView(this);
        textView.setText(stringList.toString());
        setContentView(textView);
    }

    public static void putIntent(Intent intent, ArrayList<String> stringList) {
        Bundle bundle = new Bundle();
        bundle.putStringArrayList("ArrayList", stringList);
        intent.putExtra("bundle", bundle);
    }

    public static ArrayList<String> getStringList(Intent intent) {
        Bundle bundle = intent.getBundleExtra("bundle");
        return bundle.getStringArrayList("ArrayList");

    }
}
