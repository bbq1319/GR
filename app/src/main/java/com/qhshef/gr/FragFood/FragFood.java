package com.qhshef.gr.FragFood;


import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
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

import com.qhshef.gr.FragHome;
import com.qhshef.gr.R;
import com.qhshef.gr.SSLConnect;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static com.qhshef.gr.R.id.frame;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragFood extends Fragment implements View.OnClickListener {

    TextView food_date;
    TextView food_meal;
    TextView food_main;
    TextView between;
    Button food_all;

    Fragment fragment = null;

    public FragFood() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_food, container, false);

        food_main = v.findViewById(R.id.food_main);
        food_date = v.findViewById(R.id.food_date);
        food_meal = v.findViewById(R.id.food_meal);
        between = v.findViewById(R.id.between);
        food_all = v.findViewById(R.id.food_all);
        food_all.setOnClickListener(this);

        Typeface typeface = Typeface.createFromAsset(getActivity().getAssets(), "BMJUA_ttf.ttf");
        food_main.setTypeface(typeface);
        food_date.setTypeface(typeface);
        food_meal.setTypeface(typeface);
        between.setTypeface(typeface);
        food_all.setTypeface(typeface);

        // ssl 권한을 위해 추가
        SSLConnect ssl = new SSLConnect();
        ssl.postHttps("https://www.ggu.ac.kr/sub0504/", 1000, 1000);

        GetResult task = new GetResult();
        task.execute();

        return v;
    }

    @Override
    public void onClick(View view) {

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
            between.setVisibility(View.GONE);

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
                String strUrl = "https://www.ggu.ac.kr/sub0504/";
                Document doc = Jsoup.connect(strUrl).get();

                // 파싱 해오기
                Elements date = doc.select(".table-wrap > table > tbody > tr > th");
                Elements day = doc.select(".table-wrap > table > tbody > tr > td");

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
                // MM/dd 형식과 M/d 형식으로 구분
                String[] dates = getTime.split("[/ ]");
                int getMonth;
                int getDay;

                // M/d 형식
                if (dates.length > 1) {
                    getMonth = Integer.valueOf(dates[0]);
                    getDay = Integer.valueOf(dates[1]);
                } else {
                    getMonth = 0;
                    getDay = 0;
                }

                // MM/dd 형식
                String getMM = dates[0];
                String getDD = dates[1];

                // 시간 int형으로 변경
                int int_meal_time = Integer.parseInt(meal_time);

                // 날짜 한국어로 변경
                if (date_of_week.equals("Mon")) {
                    date_of_week = "월";
                } else if (date_of_week.equals("Tue")) {
                    date_of_week = "화";
                } else if (date_of_week.equals("Wed")) {
                    date_of_week = "수";
                } else if (date_of_week.equals("Thu")) {
                    date_of_week = "목";
                } else if (date_of_week.equals("Fri")) {
                    date_of_week = "금";
                } else if (date_of_week.equals("Sat")) {
                    date_of_week = "토";
                } else if (date_of_week.equals("Sun")) {
                    date_of_week = "일";
                }

                // 날짜 전달
                for (int i = 0; i < date.size(); i++) {
                    // 오늘 날 찾기( ex) 1/3 )
                    if ((getMonth + "/" + getDay + " " + "[" + date_of_week + "]").equals(date.get(i).text())) {

                        // 날짜 설정
                        result.put("date", date.get(i).text());

                        // 조식 / 메뉴 설정
                        if (0 <= int_meal_time && int_meal_time < 10) {
                            result.put("meal", date.get(0).text());
                            result.put("main", day.get(i * 3 - 3).text());
                        }

                        // 중식 / 메뉴 설정
                        else if (10 <= int_meal_time && int_meal_time < 14) {
                            result.put("meal", date.get(1).text());
                            result.put("main", day.get(i * 3 - 2).text());
                        }

                        // 석식 / 메뉴 설정
                        else if (14 <= int_meal_time && int_meal_time < 24) {
                            result.put("meal", date.get(2).text());
                            result.put("main", day.get(i * 3 - 1).text());
                        }
                    }
                    // 오늘 날 찾기( ex) 01/03 )
                    else if ((getMM + "/" + getDD + " " + "[" + date_of_week + "]").equals(date.get(i).text())) {

                        // 날짜 설정
                        result.put("date", date.get(i).text());

                        // 조식 / 메뉴 설정
                        if (0 <= int_meal_time && int_meal_time < 10) {
                            result.put("meal", date.get(0).text());
                            result.put("main", day.get(i * 3 - 3).text());
                        }

                        // 중식 / 메뉴 설정
                        else if (10 <= int_meal_time && int_meal_time < 14) {
                            result.put("meal", date.get(1).text());
                            result.put("main", day.get(i * 3 - 2).text());
                        }

                        // 석식 / 메뉴 설정
                        else if (14 <= int_meal_time && int_meal_time < 24) {
                            result.put("meal", date.get(2).text());
                            result.put("main", day.get(i * 3 - 1).text());
                        }
                    }

                }

                // 로딩 중
                for (int i = 0; i < 1; i++) {
                    Thread.sleep(500);
                }

            } catch (Exception e){
                e.printStackTrace();

            }

            return result;

        }

        @Override
        protected void onPostExecute(Map<String, String> map){

            asyncDialog.dismiss();

            try {
                // / 출력
                between.setVisibility(View.VISIBLE);

                // 날짜 / 조,중,석식 출력
                food_date.setText(map.get("date"));
                food_meal.setText(map.get("meal"));

                // 메뉴 출력
                String getFoodMain = map.get("main");
                System.out.println(getFoodMain);
                String[] setFoodMain = getFoodMain.split("\\s");

                for(int n=0;n<setFoodMain.length;n++)
                    food_main.append(setFoodMain[n]+"\n");

            } catch (Exception e){
                e.printStackTrace();
                AlertDialog.Builder dialog = new AlertDialog.Builder(getActivity());
                dialog.setTitle("경고");
                dialog.setMessage("예상치 못한 오류로 불러올 수 없습니다.")
                        .setCancelable(true)
                        .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                fragment = new FragHome();

                                if (fragment != null) {
                                    FragmentTransaction ft = getFragmentManager().beginTransaction();
                                    ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                                    ft.replace(frame, fragment);
                                    ft.addToBackStack(null);
                                    ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE);
                                    ft.commit();
                                }
                            }
                        });
                dialog.show();

            }
        }
    }

}
