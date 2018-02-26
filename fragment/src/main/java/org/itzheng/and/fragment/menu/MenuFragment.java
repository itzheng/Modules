package org.itzheng.and.fragment.menu;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.itzheng.and.fragment.R;

import java.util.ArrayList;

/**
 * Created by root on 17-5-7.
 */

public class MenuFragment extends Fragment {
    private View rootView;

    public static MenuFragment newFragment() {
        return new MenuFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (rootView == null) {
            rootView = View.inflate(getActivity(), R.layout.fragment_menu, null);
//            TextView textView = new TextView(getActivity());
//            textView.setTextColor(Color.RED);
//            textView.setText("Menu I am !");
//            textView.setPadding(100, 200, 0, 0);
//            rootView = textView;
        }
        return rootView;
    }


    public void setTextColor(int color) {
        if (false)
            ((TextView) rootView).setTextColor(color);
    }
}
