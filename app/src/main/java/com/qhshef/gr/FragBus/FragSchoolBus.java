package com.qhshef.gr.FragBus;


import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.qhshef.gr.R;

import uk.co.senab.photoview.PhotoViewAttacher;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragSchoolBus extends Fragment{

    PhotoViewAttacher mAttacher;

    public FragSchoolBus() {
        // Required empty public constructor
    }

    Button bus_nonsan;
    Button bus_daejeon;
    Button bus_gongju;
    TextView school_txt;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_school_bus, container, false);

        bus_nonsan = v.findViewById(R.id.bus_nonsan);
        bus_daejeon = v.findViewById(R.id.bus_daejeon);
        bus_gongju = v.findViewById(R.id.bus_gongju);

        school_txt = v.findViewById(R.id.school_txt);

        Typeface typeface = Typeface.createFromAsset(getActivity().getAssets(), "BMJUA_ttf.ttf");
        school_txt.setTypeface(typeface);
        school_txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.ggu.ac.kr/kor/campus_life/regular_run.php"));
                startActivity(intent);
            }
        });

        bus_nonsan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog dialog = new Dialog(getActivity());
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.setContentView(R.layout.pop_nonsan);
                WindowManager.LayoutParams wm = dialog.getWindow().getAttributes();

                wm.width = 900;
                wm.height = 1500;
                dialog.getWindow().setAttributes(wm);
                dialog.show();
            }
        });
        bus_daejeon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog dialog = new Dialog(getActivity());
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.setContentView(R.layout.pop_daejeon);
                WindowManager.LayoutParams wm = dialog.getWindow().getAttributes();

                wm.width = 900;
                wm.height = 1500;
                dialog.getWindow().setAttributes(wm);
                dialog.show();
            }
        });
        bus_gongju.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog dialog = new Dialog(getActivity());
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.setContentView(R.layout.pop_gongju);
                WindowManager.LayoutParams wm = dialog.getWindow().getAttributes();

                wm.width = 900;
                wm.height = 1500;
                dialog.getWindow().setAttributes(wm);
                dialog.show();
            }
        });

        return v;
    }

}
