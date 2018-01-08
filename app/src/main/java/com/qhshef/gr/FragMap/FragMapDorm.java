package com.qhshef.gr.FragMap;


import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TabHost;
import android.widget.TextView;

import com.qhshef.gr.R;

import static android.R.id.tabhost;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragMapDorm extends Fragment {


    public FragMapDorm() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_map_dorm, container, false);

        final TabHost tabHost1 = v.findViewById(tabhost);
        tabHost1.setup();

        TabHost.TabSpec ts0 = tabHost1.newTabSpec("Tap Spec1");
        ts0.setContent(R.id.content_dorm_man);
        ts0.setIndicator("남자기숙사");
        tabHost1.addTab(ts0);

        TabHost.TabSpec ts1 = tabHost1.newTabSpec("Tap Spec1");
        ts1.setContent(R.id.content_dorm_woman);
        ts1.setIndicator("여자기숙사");
        tabHost1.addTab(ts1);

        TextView tv = tabHost1.getTabWidget().getChildAt(0).findViewById(android.R.id.title);
        tv.setTextColor(Color.parseColor("#ffffff"));

        tabHost1.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            @Override
            public void onTabChanged(String s) {

                for(int i = 0; i < tabHost1.getTabWidget().getChildCount(); i++){
                    TextView tv = tabHost1.getTabWidget().getChildAt(i).findViewById(android.R.id.title);
                    if(i==tabHost1.getCurrentTab()){
                        tv.setTextColor(Color.parseColor("#ffffff"));
                    }
                    else
                        tv.setTextColor(Color.parseColor("#000000"));
                }
            }
        });

        return v;
    }

}
