package com.qhshef.gr;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.qhshef.gr.FoodDeli.FragDelivery;
import com.qhshef.gr.FragBus.FragSchoolBus;
import com.qhshef.gr.FragDuksung.FragDuksungMain;
import com.qhshef.gr.FragFood.FragFood;
import com.qhshef.gr.FragNotice.FragNoticeMain;

import static com.qhshef.gr.R.id.frame;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragHome extends Fragment implements View.OnClickListener{


    public FragHome() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_home, container, false);

        Button home = v.findViewById(R.id.home);
        home.setOnClickListener(this);
        Button notice = v.findViewById(R.id.notice);
        notice.setOnClickListener(this);
        Button food = v.findViewById(R.id.food);
        food.setOnClickListener(this);
        Button delivery = v.findViewById(R.id.delivery);
        delivery.setOnClickListener(this);
        Button duksung = v.findViewById(R.id.duksung);
        duksung.setOnClickListener(this);
        Button schoolBus = v.findViewById(R.id.schoolbus);
        schoolBus.setOnClickListener(this);

        return v;
    }

    @Override
    public void onClick(View view) {
        Fragment fragment = null;

        if(view.getId() == R.id.home) {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://ggu.ac.kr"));
            startActivity(intent);
        } else if(view.getId() == R.id.notice) {
            fragment = new FragNoticeMain();
        } else if(view.getId() == R.id.food){
            fragment = new FragFood();
        } else if(view.getId() == R.id.delivery) {
            fragment = new FragDelivery();
        } else if(view.getId() == R.id.schoolbus){
            fragment = new FragSchoolBus();
        } else if(view.getId() == R.id.duksung){
            fragment = new FragDuksungMain();
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
