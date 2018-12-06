package com.qhshef.gr.FragFood;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.qhshef.gr.R;

import java.util.ArrayList;

/**
 * Created by qhshe on 2017-12-08.
 */

public class ListViewAdapter extends BaseAdapter {
    // Adapter에 추가된 데이터를 저장하기 위한 ArrayList
    private ArrayList<ListViewItem> listViewItemsList = new ArrayList<ListViewItem>();

    // 생성자
    public ListViewAdapter(){

    }

    // Adapter에 사용되는 데이터의 개수를 리턴 -> 필수
    @Override
    public int getCount(){
        return listViewItemsList.size();
    }

    // position에 위치한 데이터를 화면에 출력하는데 사용할 View를 리턴 -> 필수
    @Override
    public View getView(int position, View view, ViewGroup parent) {
        final int pos = position;
        final Context context = parent.getContext();

        // "listview_item" Layout을 inflate하여 convertView 참조 획득
        if(view == null){
            LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.listview_item, parent, false);
        }

        // 화면에 표시될 View(Layout이 inflate된)으로부터 위젯에 대한 참조 획득
        TextView food_date_all = view.findViewById(R.id.food_date_all);
        TextView food_meal_break = view.findViewById(R.id.food_meal_break);
        TextView food_main_break = view.findViewById(R.id.food_main_break);
        TextView food_meal_lunch = view.findViewById(R.id.food_meal_lunch);
        TextView food_main_lunch = view.findViewById(R.id.food_main_lunch);
        TextView food_meal_dinner = view.findViewById(R.id.food_meal_dinner);
        TextView food_main_dinner = view.findViewById(R.id.food_main_dinner);

        // Data Set(listViewItemList)에서 position에 위치한 데이터 참조 획득
        ListViewItem listViewItem = listViewItemsList.get(position);


        Typeface typeface = Typeface.createFromAsset(view.getContext().getAssets(), "BMJUA_ttf.ttf");
        food_date_all.setTypeface(typeface);
        food_meal_break.setTypeface(typeface);
        food_main_break.setTypeface(typeface);
        food_meal_lunch.setTypeface(typeface);
        food_main_lunch.setTypeface(typeface);
        food_meal_dinner.setTypeface(typeface);
        food_main_dinner.setTypeface(typeface);

        // 아이템 내 각 위젯에 데이터 반영
        food_date_all.setText(listViewItem.getFood_date_all());
        food_meal_break.setText(listViewItem.getFood_meal_break());
        food_main_break.setText(listViewItem.getFood_main_break());
        food_meal_lunch.setText(listViewItem.getFood_meal_lunch());
        food_main_lunch.setText(listViewItem.getFood_main_lunch());
        food_meal_dinner.setText(listViewItem.getFood_meal_dinner());
        food_main_dinner.setText(listViewItem.getFood_main_dinner());

        return view;
    }

    // 지정한 위치(position)에 있는 데이터와 관계된 아이템(row)의 ID를 리턴. : 필수 구현
    @Override
    public long getItemId(int position){
        return position;
    }

    // 지정한 위치(position)에 있는 데이터 리턴 : 필수 구현
    @Override
    public Object getItem(int position){
        return listViewItemsList.get(position);
    }

    // 아이템 데이터 추가를 위한 함수. 개발자가 원하는대로 작성 가능
    public void addItem(String date, String meal_break, String meal_lunch, String meal_dinner, String main_break, String main_lunch, String main_dinner){
        ListViewItem item = new ListViewItem();

        item.setFood_date_all(date);
        item.setFood_main_break(main_break);
        item.setFood_meal_break(meal_break);
        item.setFood_main_lunch(main_lunch);
        item.setFood_meal_lunch(meal_lunch);
        item.setFood_main_dinner(main_dinner);
        item.setFood_meal_dinner(meal_dinner);

        listViewItemsList.add(item);
    }
}
