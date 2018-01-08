package com.qhshef.gr.FragMap;


import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTabHost;
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
public class FragMapLib extends Fragment {


    public FragMapLib() {
        // Required empty public constructor
    }

    FragmentTabHost host;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =inflater.inflate(R.layout.fragment_map_lib, container, false);

        final TabHost tabHost1 = v.findViewById(tabhost);
        tabHost1.setup();

        TabHost.TabSpec ts0 = tabHost1.newTabSpec("Tap Spec1");
        ts0.setContent(R.id.content_lib0);
        ts0.setIndicator("B1");
        tabHost1.addTab(ts0);

        TabHost.TabSpec ts1 = tabHost1.newTabSpec("Tap Spec1");
        ts1.setContent(R.id.content_lib1);
        ts1.setIndicator("F1");
        tabHost1.addTab(ts1);

        TabHost.TabSpec ts2 = tabHost1.newTabSpec("Tap Spec2");
        ts2.setContent(R.id.content_lib2);
        ts2.setIndicator("F2");
        tabHost1.addTab(ts2);

        TabHost.TabSpec ts3 = tabHost1.newTabSpec("Tap Spec3");
        ts3.setContent(R.id.content_lib3);
        ts3.setIndicator("F3");
        tabHost1.addTab(ts3);

        TabHost.TabSpec ts4 = tabHost1.newTabSpec("Tap Spec4");
        ts4.setContent(R.id.content_lib4);
        ts4.setIndicator("F4");
        tabHost1.addTab(ts4);

        TabHost.TabSpec ts5 = tabHost1.newTabSpec("Tap Spec5");
        ts5.setContent(R.id.content_lib5);
        ts5.setIndicator("F5");
        tabHost1.addTab(ts5);

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
