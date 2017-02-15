package com.cs342.myproj1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class val extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_val);
    }

    public void BreakfastMenu(View view) {
        Intent intent = new Intent(this, breakfast0.class);
        startActivity(intent);
    }

    public void LunchMenu(View view) {
        Intent intent = new Intent(this, lunch0.class);
        startActivity(intent);
    }

    public void DinnerMenu(View view) {
        Intent intent = new Intent(this, dinner0.class);
        startActivity(intent);
    }
}
