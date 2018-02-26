package org.itzheng.and.fragment.main;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.itzheng.and.fragment.R;

/**
 * Created by root on 17-5-7.
 */

public class MainFragment extends Fragment {
    private View rootView;

    public static MainFragment newFragment() {
        return new MainFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (rootView == null) {
            rootView = View.inflate(getActivity(), R.layout.fragment_main, null);
//            TextView textView = new TextView(getActivity());
//            textView.setTextColor(Color.RED);
//            textView.setText("Main Fragment");
//            textView.setPadding(100, 200, 0, 0);
//            rootView = textView;
        }
        return rootView;
    }


}
