package com.cs342.myproj1;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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



public class dinner0 extends AppCompatActivity {
    private static final String URL2 = "https://www.amherst.edu/campuslife/housing-dining/dining/menu";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dinner0);
        new MyTask3().execute();

    }


    private class MyTask3 extends AsyncTask<Void, Void, Void> {
        String h1, h2, h3, h4, h5, h6;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

        }

        @Override
        protected Void doInBackground(Void... params) {
            try {

                Format formatter = new SimpleDateFormat("yyyy-MM-dd");
                Date date = Calendar.getInstance().getTime();
                String dateStr = formatter.format(date);

                Document document = Jsoup.connect(URL2).get();
/*
                Element link4 = document.select("div#dining-menu-2017-02-14-Lunch-menu-listing > p").first();
                h1 = "";
                h1 = link4.text();
*/
                String bk3 = "div#dining-menu-" + dateStr + "-Dinner-menu-listing";

                int k = 0;
                h1 = "Soup:    ";
                h2 = "Lighter Side:     ";
                h3 = "Traditional:     ";
                h4 = "Pasta:    ";
                h5 = "Pizza:      ";
                h6 = "Dessert:    ";


                Elements link3 = document.select(bk3);
                for (Element i : link3) {
                    Elements paras = i.getElementsByTag("p");
                    for (Element j : paras) {
                        k += 1;
                        String text1 = j.text();
                        if (k == 1) {
                            h6 = h6 + text1; // dessert
                        } else if (k == 2) {  //lighter side
                            h2 = h2 + text1; //
                        } else if (k == 3) {
                            h4 = h4 + text1;  //
                        } else if (k == 4) {
                            h5 = h5 + text1; //
                        } else if (k == 5) {
                            h1 = h1 + text1; //soup
                        } else if (k == 6) {
                            h3 = h3 + text1; // trad
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
            //TextView t20 = (TextView) findViewById(R.id.txtData);
            //t20.setText(websiteTitle2 + "\n" + websiteDescription2);


            TextView l1 = (TextView) findViewById(R.id.l1);

            TextView l2 = (TextView) findViewById(R.id.l2);
            TextView l3 = (TextView) findViewById(R.id.l3);
            TextView l4 = (TextView) findViewById(R.id.l4);
            TextView l5 = (TextView) findViewById(R.id.l5);
            TextView l6 = (TextView) findViewById(R.id.l6);


            l1.setText(h1);
            l2.setText(h2);
            l3.setText(h3);
            l4.setText(h4);
            l5.setText(h5);
            l6.setText(h6);


            //mProgressDialog2.dismiss();
        }
    }
}
