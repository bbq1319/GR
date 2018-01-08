package com.qhshef.gr.FragRule;


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
public class FragDormRule extends Fragment {


    public FragDormRule() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_dorm_rule, container, false);

        final TabHost tabHost1 = v.findViewById(tabhost);
        tabHost1.setup();

        TabHost.TabSpec ts1 = tabHost1.newTabSpec("Tap Spec1");
        ts1.setContent(R.id.content_re1);
        ts1.setIndicator("1st");
        tabHost1.addTab(ts1);

        TabHost.TabSpec ts2 = tabHost1.newTabSpec("Tap Spec2");
        ts2.setContent(R.id.content_re2);
        ts2.setIndicator("2nd");
        tabHost1.addTab(ts2);

        TabHost.TabSpec ts3 = tabHost1.newTabSpec("Tap Spec3");
        ts3.setContent(R.id.content_re3);
        ts3.setIndicator("3rd");
        tabHost1.addTab(ts3);

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
