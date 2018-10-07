package com.qhshef.gr;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.ExecutionException;

import jp.wasabeef.glide.transformations.BlurTransformation;

public class FragHomeTest extends Fragment implements View.OnClickListener{

    private String apiKey = "1d58b7853f7e7e942b189be948cedc95";
    private String myCity = "Nonsan,KR&cnt=7";
    private String apiURL = "http://api.openweathermap.org/data/2.5/weather?q=" + myCity + "&appid=" + apiKey;
    private TextView mainCurTime, mainCurDay, mainWeather;
    private ImageView mainWeatherImg;
    private String str, receiveMsg, resultText;
    private String[] arrayResult;

    public FragHomeTest() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_home_test, container, false);

        // 메인 날짜, 요일, 날씨 표시
        mainCurDay = v.findViewById(R.id.mainCurDay);
        mainCurTime = v.findViewById(R.id.mainCurTime);
        mainWeather = v.findViewById(R.id.mainWeather);
        mainWeatherImg = v.findViewById(R.id.mainWeatherImg);

        try {
            resultText = new GetWeatherDataTask().execute().get();
            arrayResult = InfoJsonParser(resultText);

            mainWeather.setText(arrayResult[3]);

        } catch (InterruptedException e) {
            e.printStackTrace();

        } catch (ExecutionException e) {
            e.printStackTrace();

        }

        // 이미지 투명도 (gilde blur)
        ImageView sky = v.findViewById(R.id.sky);
        Glide.with(this)
                .load(R.drawable.sky)
                .bitmapTransform(new BlurTransformation(getActivity(), 6))
                .into(sky);

        ImageView ggu_main1 = v.findViewById(R.id.ggu_main1);
        Glide.with(this)
                .load(R.drawable.ggu_main1)
                .bitmapTransform(new BlurTransformation(getActivity(), 6))
                .into(ggu_main1);

        ImageView ggu_main2 = v.findViewById(R.id.ggu_main2);
        Glide.with(this)
                .load(R.drawable.ggu_main2)
                .bitmapTransform(new BlurTransformation(getActivity(), 6))
                .into(ggu_main2);




        return v;
    }


    private class GetWeatherDataTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... urls) {

            InputStreamReader isr = null;
            BufferedReader reader = null;
            StringBuffer buffer;

            try {
                URL url = new URL(apiURL);
                HttpURLConnection conn = (HttpURLConnection)url.openConnection();
                conn.setRequestProperty("Content-type", "UTF-8");

                if (conn.getResponseCode() == conn.HTTP_OK) {
                    isr = new InputStreamReader(conn.getInputStream(), "UTF-8");
                    reader = new BufferedReader(isr);
                    buffer = new StringBuffer();
                    while ((str = reader.readLine()) != null) {
                        buffer.append(str);
                    }
                    receiveMsg = buffer.toString();
                    Log.i("receiveMsg : ", receiveMsg);

                } else {
                    Log.i("통신 결과", conn.getResponseCode() + "에러");
                }

            } catch (Exception e){
                e.printStackTrace();

            } finally {
                try {
                    if(reader != null)
                        reader.close();
                    if(isr != null)
                        isr.close();

                }catch (Exception e){
                    e.printStackTrace();

                }
            }

            return receiveMsg;
        }
    }

    public String[] InfoJsonParser(String jsonString) {
        String today;
        String day;
        String icon;
        String temp;
        String[] arraysum = new String[4];

        try {
            JSONArray jsonImg = new JSONObject(jsonString).getJSONArray("weather");
            JSONObject jsonTemp = new JSONObject(jsonString).getJSONObject("main");

            for(int i=0; i<jsonImg.length(); i++) {
                JSONObject jsonObject = jsonImg.getJSONObject(i);
                icon = jsonObject.optString("icon");
                if(icon != null) {
                    final String imgURL = "http://openweathermap.org/img/w/" + icon + ".png";
                    final Handler handler = new Handler();
                    Thread thread = new Thread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                URL url = new URL(imgURL);
                                InputStream is = url.openStream();
                                final Bitmap bitmap = BitmapFactory.decodeStream(is);
                                handler.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        mainWeatherImg.setImageBitmap(bitmap);
                                    }
                                });
                                mainWeatherImg.setImageBitmap(bitmap);

                            } catch (Exception e) {
                                e.printStackTrace();

                            }
                        }
                    });
                    thread.start();
                }

            }

            for(int i=0; i<jsonTemp.length(); i++) {
                temp = jsonTemp.optString("temp");
                arraysum[3] = String.valueOf(Math.round(Double.valueOf(temp) - 273.15));
            }

        } catch (JSONException e) {
            e.printStackTrace();

        }

        return arraysum;
    }

    @Override
    public void onClick(View view) {


    }

}
