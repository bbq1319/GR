package com.qhshef.gr.FoodDeli;


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
import android.widget.ImageView;
import android.widget.TextView;

import com.qhshef.gr.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragDelivery extends Fragment implements View.OnClickListener {


    public FragDelivery() {
        // Required empty public constructor
    }

    TextView del_txt;

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_delivery, container, false);

        del_txt = v.findViewById(R.id.del_txt);
        Typeface typeface = Typeface.createFromAsset(getActivity().getAssets(), "BMJUA_ttf.ttf");
        del_txt.setTypeface(typeface);

        ImageView ggoggo = v.findViewById(R.id.ggoggo);
        ImageView ddorae = v.findViewById(R.id.ddorae);
        ImageView aura = v.findViewById(R.id.aura);
        ImageView kentucky = v.findViewById(R.id.kentucky);
        ImageView tonky = v.findViewById(R.id.tonky);
        ImageView dondaegi = v.findViewById(R.id.dondaegi);
        ImageView mirac = v.findViewById(R.id.mirac);

        TextView txt_ggoggo = v.findViewById(R.id.txt_ggoggo);
        txt_ggoggo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent intent = new Intent(Intent.ACTION_DIAL);
                    intent.setData(Uri.parse("tel:041-881-1277"));
                    startActivity(intent);
                } catch (Exception e){
                    e.printStackTrace();
                }
            }
        });
        TextView txt_ddorae = v.findViewById(R.id.txt_ddorae);
        txt_ddorae.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent intent = new Intent(Intent.ACTION_DIAL);
                    intent.setData(Uri.parse("tel:041-852-8585"));
                    startActivity(intent);
                } catch (Exception e){
                    e.printStackTrace();
                }
            }
        });
        TextView txt_aura = v.findViewById(R.id.txt_aura);
        txt_aura.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent intent = new Intent(Intent.ACTION_DIAL);
                    intent.setData(Uri.parse("tel:041-734-4461"));
                    startActivity(intent);
                } catch (Exception e){
                    e.printStackTrace();
                }
            }
        });
        TextView txt_kentucky = v.findViewById(R.id.txt_kentucky);
        txt_kentucky.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent intent = new Intent(Intent.ACTION_DIAL);
                    intent.setData(Uri.parse("tel:041-855-0488"));
                    startActivity(intent);
                } catch (Exception e){
                    e.printStackTrace();
                }
            }
        });
        TextView txt_tonky = v.findViewById(R.id.txt_tonky);
        txt_tonky.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent intent = new Intent(Intent.ACTION_DIAL);
                    intent.setData(Uri.parse("tel:041-857-1300"));
                    startActivity(intent);
                } catch (Exception e){
                    e.printStackTrace();
                }
            }
        });
        TextView txt_dondaegi = v.findViewById(R.id.txt_dondaegi);
        txt_dondaegi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent intent = new Intent(Intent.ACTION_DIAL);
                    intent.setData(Uri.parse("tel:041-854-5488"));
                    startActivity(intent);
                } catch (Exception e){
                    e.printStackTrace();
                }
            }
        });
        TextView txt_mirac = v.findViewById(R.id.txt_mirac);
        txt_mirac.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent intent = new Intent(Intent.ACTION_DIAL);
                    intent.setData(Uri.parse("tel:041-852-9497"));
                    startActivity(intent);
                } catch (Exception e){
                    e.printStackTrace();
                }
            }
        });

        ggoggo.setOnClickListener(this);
        ddorae.setOnClickListener(this);
        aura.setOnClickListener(this);
        kentucky.setOnClickListener(this);
        tonky.setOnClickListener(this);
        dondaegi.setOnClickListener(this);
        mirac.setOnClickListener(this);

        return v;
    }

    @Override
    public void onClick(View v) {

        Dialog dialog = new Dialog(getActivity());
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        switch (v.getId()){
            case R.id.ggoggo: dialog.setContentView(R.layout.fragment_deli_ggoggo);
                break;
            case R.id.ddorae: dialog.setContentView(R.layout.fragment_deli_ddorae);
                break;
            case R.id.aura: dialog.setContentView(R.layout.fragment_deli_aura);
                break;
            case R.id.kentucky: dialog.setContentView(R.layout.fragment_deli_kentucky);
                break;
            case R.id.tonky: dialog.setContentView(R.layout.fragment_deli_tonky);
                break;
            case R.id.dondaegi: dialog.setContentView(R.layout.fragment_deli_dondaegi);
                break;
            case R.id.mirac: dialog.setContentView(R.layout.fragment_deli_mirac);
                break;
        }

        WindowManager.LayoutParams wm = dialog.getWindow().getAttributes();

        wm.width = 900;
        wm.height = 1500;
        dialog.getWindow().setAttributes(wm);

        dialog.show();

    }
}
