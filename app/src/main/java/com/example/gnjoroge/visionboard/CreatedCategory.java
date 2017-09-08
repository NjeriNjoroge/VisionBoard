package com.example.gnjoroge.visionboard;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class CreatedCategory extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_created_category);
        //getting the name and description from CategoriesActivity
        Intent intent = getIntent();
        String newVision = intent.getStringExtra("newVision");
    }
}
