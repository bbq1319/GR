package com.qhshef.gr.FragDuksung;


import android.graphics.Color;
import android.graphics.Typeface;
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
public class FragDuksungMain extends Fragment {

    public FragDuksungMain() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =  inflater.inflate(R.layout.fragment_duksung, container, false);

        final TabHost tabHost1 = v.findViewById(tabhost);
        tabHost1.setup();

        final Typeface typeface = Typeface.createFromAsset(getActivity().getAssets(), "BMJUA_ttf.ttf");

        TabHost.TabSpec ts1 = tabHost1.newTabSpec("Tap Spec1");
        ts1.setContent(R.id.content_to_nonsan);
        ts1.setIndicator("학교 -> 논산");
        tabHost1.addTab(ts1);

        TabHost.TabSpec ts2 = tabHost1.newTabSpec("Tap Spec2");
        ts2.setContent(R.id.content_to_school);
        ts2.setIndicator("논산 -> 학교");
        tabHost1.addTab(ts2);

        TextView tv = tabHost1.getTabWidget().getChildAt(0).findViewById(android.R.id.title);
        TextView tv2 = tabHost1.getTabWidget().getChildAt(1).findViewById(android.R.id.title);
        tv.setTextColor(Color.parseColor("#ffffff"));
        tv.setTypeface(typeface);
        tv2.setTypeface(typeface);

        tabHost1.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            @Override
            public void onTabChanged(String s) {

                for(int i = 0; i < tabHost1.getTabWidget().getChildCount(); i++){
                    TextView tv = tabHost1.getTabWidget().getChildAt(i).findViewById(android.R.id.title);
                    if(i==tabHost1.getCurrentTab()){
                        tv.setTextColor(Color.parseColor("#ffffff"));
                        tv.setTypeface(typeface);
                    }
                    else
                        tv.setTextColor(Color.parseColor("#000000"));
                        tv.setTypeface(typeface);
                }
            }
        });

        return v;
    }

}
