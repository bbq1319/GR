package com.qhshef.gr.FragFood;


import android.app.ProgressDialog;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

import com.qhshef.gr.R;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragFoodAll extends Fragment implements View.OnClickListener {

    ListView listView;
    ListViewAdapter adapter;
    Button food_back;


    public FragFoodAll() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_food_all, container, false);

        // Adapter 생성
        adapter = new ListViewAdapter();

        // 리스트뷰 참조 및 Adapter 달기
        listView = v.findViewById(R.id.food_list);

        food_back = v.findViewById(R.id.food_back);
        food_back.setOnClickListener(this);

        Typeface typeface = Typeface.createFromAsset(getActivity().getAssets(), "BMJUA_ttf.ttf");
        food_back.setTypeface(typeface);

        GetResult task = new GetResult();
        task.execute();

        return v;
    }

    @Override
    public void onClick(View view) {
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        fragmentManager.beginTransaction().remove(FragFoodAll.this).commit();
        fragmentManager.popBackStack();
    }

    private class GetResult extends AsyncTask<Void, Void, Map<String,String>>{

        ProgressDialog asyncDialog = new ProgressDialog(getActivity());

        @Override
        protected void onPreExecute(){
            asyncDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            asyncDialog.setMessage("잠시만 기다려주세요...");

            asyncDialog.show();
            super.onPreExecute();
        }

        @Override
        protected Map<String, String> doInBackground(Void... voids) {
            Map<String, String> result = new HashMap<String, String>();

            try{
                // 파싱을 url 설정
                String url = "http://www.ggu.ac.kr/kor/campus_life/public_space_1.php";
                Document doc = Jsoup.connect(url).get();

                // 파싱 해오기
                Elements date = doc.select(".tb_style02 > tbody > tr > th");
                Elements day = doc.select(".tb_style02 > tbody > tr > td");

                // 날짜 전달
                for(int i=3;i<date.size()-1;i++){
                    result.put("date_size", Integer.toString(date.size()));
                    result.put("date"+i, date.get(i).text());
                    result.put("meal_break", date.get(0).text());
                    result.put("meal_lunch", date.get(1).text());
                    result.put("meal_dinner", date.get(2).text());
                    result.put("main_break"+i, day.get(i*3-3).text());
                    result.put("main_lunch"+i, day.get(i*3-2).text());
                    result.put("main_dinner"+i, day.get(i*3-1).text());
                }

                // 로딩 중
                for(int i=0;i<1;i++){
                    Thread.sleep(500);
                }

            } catch (IOException e){
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return result;
        }

        @Override
        protected void onPostExecute(Map<String, String> map){

            asyncDialog.dismiss();

            listView.setAdapter(adapter);

            for(int i=3;i<Integer.parseInt(map.get("date_size"))-1;i++){
                adapter.addItem(map.get("date"+i),
                        map.get("meal_break"), map.get("meal_lunch"), map.get("meal_dinner"),
                        map.get("main_break"+i), map.get("main_lunch"+i), map.get("main_dinner"+i));
            }

        }
    }

}
