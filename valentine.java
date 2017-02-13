package com.cs342.myproj1;


import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.cs342.myproj1.Menu;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;


public class valentine extends AppCompatActivity {
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    // URL Address
    //String url = "https://www.amherst.edu/campuslife/housing-dining/dining/menu";

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_valentine);

        Button titlebutton = (Button) findViewById(R.id.d1);

        // Capture button click
        titlebutton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                // Execute Title AsyncTask
                new Title().execute();
            }
        });
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    public Action getIndexApiAction() {
        Thing object = new Thing.Builder()
                .setName("valentine Page") // TODO: Define a title for the content shown.
                // TODO: Make sure this auto-generated URL is correct.
                .setUrl(Uri.parse("http://[ENTER-YOUR-URL-HERE]"))
                .build();
        return new Action.Builder(Action.TYPE_VIEW)
                .setObject(object)
                .setActionStatus(Action.STATUS_TYPE_COMPLETED)
                .build();
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        AppIndex.AppIndexApi.start(client, getIndexApiAction());
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        AppIndex.AppIndexApi.end(client, getIndexApiAction());
        client.disconnect();
    }


    private class Title extends AsyncTask<String, Void, ArrayList<Menu>> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();

        }

        @Override
        protected ArrayList<Menu> doInBackground(String... params) {
            // get the string from params, which is an array
            String date = params[0];

            scraper s1 = new scraper() {
            };
            ArrayList<Menu> items = new ArrayList<>();
            try {
                ArrayList<ArrayList<ArrayList<String>>> result = s1 .scrapeMenu(date);
                for (int list1 = 0; list1 < result.size(); list1++){
                    for (int list2 = 0; list2  < result.get(list1).size(); list2 ++) {
                        for (int list3 = 1; list3 < result.get(list1).get(list2 ).size(); list3++){
                            Menu m1 = new Menu();
                            if (list1 == 0){
                                m1.setType("Breakfast");
                                m1.setSection(result.get(list1).get(list2).get(0));
                                m1.setItems(result.get(list1).get(list2).get(list3));
                                items.add(m1);
                            } else if (list1 == 1){
                                m1.setType("Lunch");
                                m1.setSection(result.get(list1).get(list2).get(0));
                                m1.setItems(result.get(list1).get(list2).get(list3));
                                items.add(m1);
                            } else if (list1 == 2){
                                m1.setType("Dinner");
                                m1.setSection(result.get(list1).get(list2).get(0));
                                m1.setItems(result.get(list1).get(list2).get(list3));
                                items.add(m1);
                            }
                        }
                    }
                }
            } catch (IOException e) {
                Log.e("IOException", "IO exception");
            }

            return items;
        }

        @Override
        protected void onPostExecute(ArrayList<Menu> resul) {
            super.onPostExecute(resul);
        }


    }

    public void bk1(View view) {
        Intent intent = new Intent(this, valentine.class);
        startActivity(intent);
    }

}



