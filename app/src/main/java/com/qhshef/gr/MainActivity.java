package com.qhshef.gr;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.TextView;

import com.qhshef.gr.FoodDeli.FragDelivery;
import com.qhshef.gr.FragBus.FragSchoolBus;
import com.qhshef.gr.FragDuksung.FragDuksungMain;
import com.qhshef.gr.FragFood.FragFood;
import com.qhshef.gr.FragNotice.FragNoticeMain;

import static com.qhshef.gr.R.id.frame;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // toolbar setting
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // navigation setting
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        // 첫 화면 setting
        FragHome fragment = new FragHome();
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.setCustomAnimations(R.anim.delta_in, R.anim.delta_out);
        ft.replace(frame, fragment);
        ft.commit();

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    // 사이드 네비게이션 설정
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        // 글꼴 설정
        TextView head_txt1 = findViewById(R.id.nav_head_txt1);
        TextView head_txt2 = findViewById(R.id.nav_head_txt2);
        Typeface typeface = Typeface.createFromAsset(getAssets(), "BMJUA_ttf.ttf");
        head_txt1.setTypeface(typeface);
        head_txt2.setTypeface(typeface);

        Fragment fragment = null;

        if (id == R.id.nav_home) {
            fragment = new FragHome();
        } else if (id == R.id.nav_ggu) {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://ggu.ac.kr"));
            startActivity(intent);
        } else if (id == R.id.nav_notice) {
            fragment = new FragNoticeMain();
        } else if (id == R.id.nav_food) {
            fragment = new FragFood();
        } else if (id == R.id.nav_delivery) {
            fragment = new FragDelivery();
        } else if (id == R.id.nav_schoolbus) {
            fragment = new FragSchoolBus();
        } else if (id == R.id.nav_duk) {
            fragment = new FragDuksungMain();
        } else if (id == R.id.nav_info) {
            Dialog dialog = new Dialog(this);
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            //dialog.getWindow().setBackgroundDrawable(getResources().getDrawable(R.drawable.info));
            dialog.setContentView(R.layout.pop);
            WindowManager.LayoutParams wm =dialog.getWindow().getAttributes();

            wm.width = 900;
            wm.height = 1500;
            dialog.getWindow().setAttributes(wm);

            dialog.show();
        }

        if(fragment != null){
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.setCustomAnimations(R.anim.delta_in, R.anim.delta_out);
            ft.replace(frame, fragment);
            ft.addToBackStack(null);
            ft.commit();
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

}
