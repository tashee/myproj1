package com.cs342.myproj1;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class breakfast0 extends AppCompatActivity {
    private static final String URL = "https://www.amherst.edu/campuslife/housing-dining/dining/menu";
    ProgressDialog mProgressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_breakfast0);
        new MyTask().execute();


    }


    private class MyTask extends AsyncTask<Void, Void, Void> {
        String websiteTitle, websiteDescription, h2, h3;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            mProgressDialog = new ProgressDialog(breakfast0.this);
            mProgressDialog.setMessage("Loading...");
            mProgressDialog.setIndeterminate(false);
            mProgressDialog.show();
        }

        @Override
        protected Void doInBackground(Void... params) {
            try {

                Format formatter = new SimpleDateFormat("yyyy-MM-dd");
                Date date = Calendar.getInstance().getTime();
                String dateStr = formatter.format(date);

                // Connect to website
                Document document = Jsoup.connect(URL).get();
                // Get the html document title
                websiteTitle = document.title();
                Elements description = document.select("meta[name=description]");
                // Locate the content attribute
                websiteDescription = description.attr("content");


                //Element link1 = document.select("div#dining-menu-2017-02-14-Breakfast-menu-listing > p").first();
                String bk1 = "div#dining-menu-" + dateStr + "-Breakfast-menu-listing > p";
                Element link1 = document.select(bk1).first();


                h2 = link1.text();

                h3 = "";
                String bk2 = "div#dining-menu-" + dateStr + "-Breakfast-menu-listing";
                int k = 0;
                Elements link2 = document.select(bk2);
                for (Element i : link2) {
                    Elements paras = i.getElementsByTag("p");
                    for (Element j : paras) {
                        k += 1;
                        if (k == 2) {
                            String text1 = j.text();
                            h3 = h3 + text1;
                        }
                    }

                }


            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            // Set title into TextView
            TextView txttitle = (TextView) findViewById(R.id.txtData);
            TextView t2 = (TextView) findViewById(R.id.t2);
            txttitle.setText(websiteTitle + "\n" + websiteDescription);

            t2.setText(h2);
            TextView t3 = (TextView) findViewById(R.id.t3);

            t3.setText(h3);
            mProgressDialog.dismiss();
        }
    }
}