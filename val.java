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

    public void breakfast1(View view) {
        Intent intent = new Intent(this, breakfast.class);
        startActivity(intent);
    }

    public void lunch1(View view) {
        Intent intent = new Intent(this, lunch.class);
        startActivity(intent);
    }

    public void dinner1(View view) {
        Intent intent = new Intent(this, dinner.class);
        startActivity(intent);
    }
}
