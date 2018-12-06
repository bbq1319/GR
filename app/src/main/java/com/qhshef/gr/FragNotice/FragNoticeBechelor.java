package com.qhshef.gr.FragNotice;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.qhshef.gr.R;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.HashMap;
import java.util.Map;

import static com.qhshef.gr.R.id.frame1;

public class FragNoticeBechelor extends Fragment {

    ListView listview;
    ListViewAdapter adapter;
    Fragment fragment = null;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_notice_bechelor, container, false);

        adapter = new ListViewAdapter();
        listview = v.findViewById(R.id.listView);

        GetResult task = new GetResult();
        task.execute();

        return v;
    }

    private class GetResult extends AsyncTask<Void, Void, Map<String, String>> {

        ProgressDialog asyncDialog = new ProgressDialog(getActivity());
        Elements raw_body;

        @Override
        protected void onPreExecute() {
            asyncDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            asyncDialog.setMessage("잠시만 기다려주세요...");

            asyncDialog.show();
            super.onPreExecute();
        }

        @Override
        protected Map<String, String> doInBackground(Void... voids) {
            Map<String, String> result = new HashMap<>();

            try {
                // 파싱 설정
                String strUrl = "https://www.ggu.ac.kr/sub01080101/";
                Document doc = Jsoup.connect(strUrl).get();

                // 파싱 해오기
                raw_body = doc.select(".bbs > tbody > tr");

            } catch (Exception e) {
                e.printStackTrace();

            }

            return result;
        }

        @Override
        protected void onPostExecute(Map<String, String> map) {

            asyncDialog.dismiss();

            try {
                listview.setAdapter(adapter);

                for(Element arr : raw_body) {
                    adapter.addItem(arr.child(0).text(), arr.child(2).text(), arr.child(4).text());
                }

                listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        fragment = new FragNoticeContent();
                        String href = raw_body.get(position).select("td").get(2).select("a").attr("href");
                        System.out.println(href);

                        Bundle bundle = new Bundle();
                        bundle.putString("href", href);
                        fragment.setArguments(bundle);

                        if (fragment != null) {
                            FragmentTransaction ft = getFragmentManager().beginTransaction();
                            ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                            ft.replace(frame1, fragment);
                            ft.addToBackStack(null);
                            ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE);
                            ft.commit();
                        }

                    }
                });

            } catch (Exception e) {
                e.printStackTrace();

            }
        }

    }
}