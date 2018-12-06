package com.qhshef.gr.FragNotice;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TabHost;

import com.qhshef.gr.R;

import static com.qhshef.gr.R.id.frame1;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragNoticeMain extends Fragment implements TabHost.OnTabChangeListener{

    ListView listview;
    ListViewAdapter adapter;
    Fragment fragment = null;

    public FragNoticeMain() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_notice_main, container, false);

        TabHost tabHost1 = v.findViewById(R.id.tabHost1);
        tabHost1.setup();

        // 첫 번째 Tab. (탭 표시 텍스트:"TAB 1"), (페이지 뷰:"content1")
        TabHost.TabSpec ts1 = tabHost1.newTabSpec("Tab1");
        ts1.setContent(R.id.content1);
        ts1.setIndicator("학사공지");
        tabHost1.addTab(ts1);
        tabHost1.setOnTabChangedListener(this);

        // 두 번째 Tab. (탭 표시 텍스트:"TAB 2"), (페이지 뷰:"content2")
        TabHost.TabSpec ts2 = tabHost1.newTabSpec("Tab2");
        ts2.setContent(R.id.content2);
        ts2.setIndicator("TAB 2");
        tabHost1.addTab(ts2);
        tabHost1.setOnTabChangedListener(this);
0
        // 세 번째 Tab. (탭 표시 텍스트:"TAB 3"), (페이지 뷰:"content3")
        TabHost.TabSpec ts3 = tabHost1.newTabSpec("Tab3");
        ts3.setContent(R.id.content3);
        ts3.setIndicator("TAB 3");
        tabHost1.addTab(ts3);
        tabHost1.setOnTabChangedListener(this);

        adapter = new ListViewAdapter();
        listview = v.findViewById(R.id.listView);

        fragment = new FragNoticeBechelor();

        if (fragment != null) {
            FragmentTransaction ft = getFragmentManager().beginTransaction();
            ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
            ft.replace(frame1, fragment);
            ft.addToBackStack(null);
            ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE);
            ft.commit();
        }

        return v;
    }

    @Override
    public void onTabChanged(String tabId) {
        if(tabId.equals("Tab1")) {
            fragment = new FragNoticeBechelor();
        }

        if (fragment != null) {
            FragmentTransaction ft = getFragmentManager().beginTransaction();
            ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
            ft.replace(frame1, fragment);
            ft.addToBackStack(null);
            ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE);
            ft.commit();
        }
    }
}
