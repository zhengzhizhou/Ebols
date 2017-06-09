package com.example.administrator.ebols;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by Administrator on 2017/5/30.
 */

public class Fragment_e extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_e, container, false);
        TextView textView = (TextView)view.findViewById(R.id.textView_b);
        return view;
    }
}
