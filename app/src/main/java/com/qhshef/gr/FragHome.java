package com.qhshef.gr;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;

import com.qhshef.gr.FoodDeli.FragDelivery;
import com.qhshef.gr.FragBus.FragSchoolBus;
import com.qhshef.gr.FragCounsel.FragCoun;
import com.qhshef.gr.FragDuksung.FragDuksungMain;
import com.qhshef.gr.FragFood.FragFood;
import com.qhshef.gr.FragMap.FragMapSelect;
import com.qhshef.gr.FragRule.FragDormRule;

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

        Button map = v.findViewById(R.id.map);
        map.setOnClickListener(this);
        Button duksung = v.findViewById(R.id.duksung);
        duksung.setOnClickListener(this);
        Button rule = v.findViewById(R.id.dormrule);
        rule.setOnClickListener(this);
        Button food = v.findViewById(R.id.food);
        food.setOnClickListener(this);
        Button schoolBus = v.findViewById(R.id.schoolbus);
        schoolBus.setOnClickListener(this);
        Button coun = v.findViewById(R.id.coun);
        coun.setOnClickListener(this);
        Button delivery = v.findViewById(R.id.delivery);
        delivery.setOnClickListener(this);

        ImageView image1 = v.findViewById(R.id.image1);
        image1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog dialog = new Dialog(getActivity());
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                //dialog.getWindow().setBackgroundDrawable(getResources().getDrawable(R.drawable.info));
                dialog.setContentView(R.layout.pop);
                WindowManager.LayoutParams wm = dialog.getWindow().getAttributes();

                //wm.width=(int)(wm.width*0.7);
                //wm.height=(int)(wm.height*0.6);
                wm.width = 900;
                wm.height = 1500;
                dialog.getWindow().setAttributes(wm);

                dialog.show();
            }
        });
        ImageView image2 = v.findViewById(R.id.image2);
        image2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


            }
        });

        return v;
    }


    @Override
    public void onClick(View view) {
        Fragment fragment = null;

        if(view.getId() == R.id.map){
            fragment = new FragMapSelect();
        } else if(view.getId() == R.id.duksung){
            fragment = new FragDuksungMain();
        } else if(view.getId() == R.id.dormrule){
            fragment = new FragDormRule();
        } else if(view.getId() == R.id.food){
            fragment = new FragFood();
        } else if(view.getId() == R.id.schoolbus){
            fragment = new FragSchoolBus();
        } else if(view.getId() == R.id.coun){
            fragment = new FragCoun();
        } else if(view.getId() == R.id.delivery){
            fragment = new FragDelivery();
        }


        if (fragment != null) {

            FragmentTransaction ft = getFragmentManager().beginTransaction();
            // ft.setCustomAnimations(R.anim.delta_in, R.anim.delta_out);
            ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
            ft.replace(frame, fragment);
            ft.addToBackStack(null);
            ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE);
            ft.commit();

        }

    }
}
