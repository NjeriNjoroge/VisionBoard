package com.example.gnjoroge.visionboard;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class CreatedCategory extends AppCompatActivity {
    private TextView mNewVisionTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_created_category);
        //getting the name and description from CategoriesActivity
        mNewVisionTextView = (TextView) findViewById(R.id.newVisiontextView);
        Intent intent = getIntent();
        String newVision = intent.getStringExtra("newVision");
        mNewVisionTextView.setText(newVision);
    }
}
