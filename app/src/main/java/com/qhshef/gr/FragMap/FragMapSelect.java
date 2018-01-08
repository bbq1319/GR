package com.qhshef.gr.FragMap;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.qhshef.gr.R;

import static com.qhshef.gr.R.id.frame;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragMapSelect extends Fragment implements View.OnClickListener{


    public FragMapSelect() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_map_select, container, false);

        TextView map_main = v.findViewById(R.id.map_hq);
        map_main.setOnClickListener(this);
        TextView map_lib = v.findViewById(R.id.map_lib);
        map_lib.setOnClickListener(this);
        TextView map_dorm = v.findViewById(R.id.map_dorm);
        map_dorm.setOnClickListener(this);

        return v;
    }

    @Override
    public void onClick(View view) {
        Fragment fragment = null;

        if(view.getId() == R.id.map_hq){
            fragment = new FragMapMain();
        } else if(view.getId() == R.id.map_lib){
            fragment = new FragMapLib();
        } else if(view.getId() == R.id.map_dorm) {
            fragment = new FragMapDorm();
        }

        if (fragment != null) {
            FragmentTransaction ft = getFragmentManager().beginTransaction();
            ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
            ft.replace(frame, fragment);
            ft.addToBackStack(null);
            ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE);
            ft.commit();
        }

    }
}
