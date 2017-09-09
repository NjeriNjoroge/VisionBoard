package com.example.gnjoroge.visionboard;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;

public class CreatedCategory extends AppCompatActivity {
    @Bind(R.id.newVisiontextView) TextView mNewVisionTextView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_created_category);
        ButterKnife.bind(this);
        //getting the name and description from CategoriesActivity
        mNewVisionTextView = (TextView) findViewById(R.id.newVisiontextView);
        Intent intent = getIntent();
        String newVision = intent.getStringExtra("newVision");
        mNewVisionTextView.setText(newVision);
    }
}
