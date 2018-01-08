package com.qhshef.gr.FragFood;


import android.app.ProgressDialog;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.qhshef.gr.R;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static com.qhshef.gr.R.id.frame;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragCamTel extends Fragment implements View.OnClickListener {

    TextView food_date;
    TextView food_meal;
    TextView food_main;
    TextView bewteen;
    Button food_all;

    public FragCamTel() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_cam_tel, container, false);

        food_main = v.findViewById(R.id.food_main);
        food_date = v.findViewById(R.id.food_date);
        food_meal = v.findViewById(R.id.food_meal);
        bewteen = v.findViewById(R.id.between);
        food_all = v.findViewById(R.id.food_all);
        food_all.setOnClickListener(this);

        Typeface typeface = Typeface.createFromAsset(getActivity().getAssets(), "BMJUA_ttf.ttf");
        food_main.setTypeface(typeface);
        food_date.setTypeface(typeface);
        food_meal.setTypeface(typeface);
        bewteen.setTypeface(typeface);
        food_all.setTypeface(typeface);

        GetResult task = new GetResult();
        task.execute();

        return v;
    }

    @Override
    public void onClick(View view) {
        Fragment fragment = null;

        fragment = new FragFoodAll();

        if (fragment != null) {
            FragmentTransaction ft = getFragmentManager().beginTransaction();
            ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
            ft.replace(frame, fragment);
            ft.addToBackStack(null);
            ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE);
            ft.commit();
        }
    }

    private class GetResult extends AsyncTask<Void, Void, Map<String, String>> {

        ProgressDialog asyncDialog = new ProgressDialog(getActivity());

        @Override
        protected void onPreExecute(){
            bewteen.setVisibility(View.GONE);

            asyncDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            asyncDialog.setMessage("잠시만 기다려주세요...");

            asyncDialog.show();
            super.onPreExecute();
        }

        @Override
        protected Map<String, String> doInBackground(Void... params) {
            Map<String, String> result = new HashMap<>();

            try {
                // 파싱을 url 설정
                String url = "http://www.ggu.ac.kr/kor/campus_life/public_space_1.php";
                Document doc = Jsoup.connect(url).get();

                // 파싱 해오기
                Elements date = doc.select(".tb_style02 > tbody > tr > th");
                Elements day = doc.select(".tb_style02 > tbody > tr > td");
                Elements all = doc.select(".tb_style02 > tbody > tr");

                // 시간 설정
                long now = System.currentTimeMillis();
                Date now_date = new Date(now);
                SimpleDateFormat sdf = new SimpleDateFormat("MM/dd");
                SimpleDateFormat dow = new SimpleDateFormat("E");
                SimpleDateFormat meal = new SimpleDateFormat("HH");
                String getTime = sdf.format(now_date);
                String date_of_week = dow.format(now_date);
                String meal_time = meal.format(now_date);

                // 월, 일 구하기
                String[] dates = getTime.split("[/ ]");
                int getMonth;
                int getDay;

                if(dates.length > 1){
                    getMonth = Integer.valueOf(dates[0]);
                    getDay = Integer.valueOf(dates[1]);
                } else {
                    getMonth = 0;
                    getDay = 0;
                }

                // 시간 int형으로 변경
                int int_meal_time = Integer.parseInt(meal_time);

                // 날짜 한국어로 변경
                if(date_of_week.equals("Mon")){
                    date_of_week = "월";
                } else if(date_of_week.equals("Tue")){
                    date_of_week = "화";
                } else if(date_of_week.equals("Wed")){
                    date_of_week = "수";
                } else if(date_of_week.equals("Thu")){
                    date_of_week = "목";
                } else if(date_of_week.equals("Fri")){
                    date_of_week = "금";
                } else if(date_of_week.equals("Sat")){
                    date_of_week = "토";
                } else if(date_of_week.equals("Sun")){
                    date_of_week = "일";
                }

                // 날짜 전달
                for(int i=0;i<date.size();i++){
                    // 오늘 날 찾기
                    if((getMonth+"/"+getDay+" "+"["+date_of_week+"]").equals(date.get(i).text())){

                        // 날짜 설정
                        result.put("date", date.get(i).text());

                        // 조식 / 메뉴 설정
                        if(0 <= int_meal_time && int_meal_time < 10){
                            result.put("meal", date.get(0).text());
                            result.put("main", day.get(i*3-3).text());
                        }

                        // 중식 / 메뉴 설정
                        else if(10 <= int_meal_time && int_meal_time < 14){
                            result.put("meal", date.get(1).text());
                            result.put("main", day.get(i*3-2).text());
                        }

                        // 석식 / 메뉴 설정
                        else if(14 <= int_meal_time && int_meal_time < 24){
                            result.put("meal", date.get(2).text());
                            result.put("main", day.get(i*3-1).text());
                        }
                    }
                }

                // 로딩 중
                for(int i=0;i<1;i++){
                    Thread.sleep(500);
                }

            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            return result;

        }

        @Override
        protected void onPostExecute(Map<String, String> map){

            asyncDialog.dismiss();

            // / 출력
            bewteen.setVisibility(View.VISIBLE);

            // 날짜 / 조,중,석식 출력
            food_date.setText(map.get("date"));
            food_meal.setText(map.get("meal"));

            // 메뉴 출력
            String getFoodMain = map.get("main");
            String[] setFoodMain = getFoodMain.split("\\s");
            for(int n=0;n<setFoodMain.length;n++)
                food_main.append(setFoodMain[n]+"\n");

        }
    }

}
