package com.qhshef.gr.FragNotice;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.qhshef.gr.R;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.HashMap;
import java.util.Map;


public class FragNoticeContent extends Fragment {

    TextView board_title;
    TextView board_writer;
    TextView board_regdate;
    TextView board_hit;
    TextView board_content;
    TextView department;
    TextView department_tel;
    TextView board_download;
    String content = "";
    String title, writer, regdate, hit, download, download_href, department_text, department_tel_text;


    public FragNoticeContent() {
        // Required empty public constructor

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_notice_content, container, false);

        board_title = v.findViewById(R.id.board_title);
        board_writer = v.findViewById(R.id.board_writer);
        board_regdate = v.findViewById(R.id.board_regdate);
        board_hit = v.findViewById(R.id.board_hit);
        board_content = v.findViewById(R.id.board_content);
        department = v.findViewById(R.id.department);
        department_tel = v.findViewById(R.id.department_tel);
        board_download = v.findViewById(R.id.board_download);

        GetResult task = new GetResult();
        task.execute();

        return v;
    }

    private class GetResult extends AsyncTask<Void, Void, Map<String, String>> {
        ProgressDialog asyncDialog = new ProgressDialog(getActivity());
        Elements board_title_raw, board_head_raw, board_content_raw, board_content_downloader, board_footer;

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
                String href = getArguments().getString("href");

                // 파싱 설정
                String strUrl = "https://www.ggu.ac.kr" + href;
                Document doc = Jsoup.connect(strUrl).get();

                // 파싱 해오기
                board_title_raw = doc.select(".bbs > thead > tr");
                board_head_raw = doc.select(".bbs > tbody > tr");
                board_content_raw = doc.select(".bbs-html > p");
                board_content_downloader = doc.select(".downloader > li");
                board_footer = doc.select(".gray-border-box > ul > li");

            } catch (Exception e) {
                e.printStackTrace();

            }

            return result;
        }

        @Override
        protected void onPostExecute(Map<String, String> map) {

            asyncDialog.dismiss();

            try {
                title = board_title_raw.select("th").text();
                writer = board_head_raw.select("td").not("span").get(0).text();
                regdate = board_head_raw.select("td").get(1).text();
                hit = board_head_raw.select("td").get(2).text();
                download_href = board_content_downloader.select("a").attr("href");
                download = board_content_downloader.select("a").text();
                department_text = board_footer.get(1).select("span").text();
                department_tel_text = board_footer.get(2).select("span").text();


                for(Element arr : board_content_raw) {
                    content += (arr.text() + "\n\n");
                }

                board_title.setText(title);
                board_writer.setText(writer.replaceAll("작성자 ", ""));
                board_regdate.setText(regdate.replaceAll("작성일 ", ""));
                board_hit.setText(hit.replaceAll("조회수 ", ""));
                board_content.setText(content);
                board_download.setText(download);
                board_download.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://ggu.ac.kr" + download_href));
                        startActivity(intent);
                    }
                });
                department.setText(department_text);
                department_tel.setText(department_tel_text);
            } catch (Exception e) {
                e.printStackTrace();

            }
        }
    }

}
