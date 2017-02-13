package com.cs342.myproj1;

/**
 * Created by ran on 2/12/17.
 */

import java.io.IOException;
        import java.util.ArrayList;

        import org.jsoup.*;
        import org.jsoup.nodes.Document;
        import org.jsoup.nodes.Element;
        import org.jsoup.select.Elements;

public class scraper {

    public ArrayList<ArrayList<ArrayList<String>>> scrapeMenu(String date) throws IOException {

        ArrayList<ArrayList<ArrayList<String>>> menues = new ArrayList<ArrayList<ArrayList<String>>>();
        ArrayList<ArrayList<String>> breakfast = new ArrayList<ArrayList<String>>();
        ArrayList<ArrayList<String>> lunch = new ArrayList<ArrayList<String>>();
        ArrayList<ArrayList<String>> dinner = new ArrayList<ArrayList<String>>();
        menues.add(breakfast);
        menues.add(lunch);
        menues.add(dinner);

        Document doc;
        doc = Jsoup.connect("https://www.amherst.edu/campuslife/housing-dining/dining/menu").get();

        Element day = doc.getElementById("dining-menu-"+date);

        Elements meals = day.getElementsByClass("dining-menu-meal");

        for(int i=0; i<meals.size(); i++){
            Elements courses = meals.get(i).getElementsByClass("dining-course-name");
            Elements content = meals.get(i).select("p");

            for(int j=0; j<courses.size(); j++){
                if(i==0){
                    breakfast.add(new ArrayList<String>());
                    breakfast.get(j).add(courses.get(j).text());
                    String s = content.get(j).text();

                    int start=0;

                    for(int k = 0; k<s.length(); k++){
                        if(s.substring(k, k+1).equals(";")){
                            breakfast.get(j).add(s.substring(start,k));
                            start=k+1;
                        }
                    }
                }
                else if(i==1){
                    lunch.add(new ArrayList<String>());
                    lunch.get(j).add(courses.get(j).text());
                    String s = content.get(j).text();

                    int start=0;

                    for(int k = 0; k<s.length(); k++){
                        if(s.substring(k, k+1).equals(";")){
                            lunch.get(j).add(s.substring(start,k));
                            start=k+1;
                        }
                    }
                }
                else if(i==2){
                    dinner.add(new ArrayList<String>());
                    dinner.get(j).add(courses.get(j).text());
                    String s = content.get(j).text();

                    int start=0;

                    for(int k = 0; k<s.length(); k++){
                        if(s.substring(k, k+1).equals(";")){
                            dinner.get(j).add(s.substring(start,k));
                            start=k+1;
                        }
                    }
                }
            }
        }

        return menues;

    }

}
