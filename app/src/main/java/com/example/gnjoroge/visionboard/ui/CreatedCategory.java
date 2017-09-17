package com.example.gnjoroge.visionboard.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.gnjoroge.visionboard.R;

import butterknife.Bind;
import butterknife.ButterKnife;

public class CreatedCategory extends AppCompatActivity {
    @Bind(R.id.newVisiontextView) TextView mNewVisionTextView;
    @Bind(R.id.editText) EditText mEditText;
    @Bind(R.id.editText2) EditText mEditText2;
    @Bind(R.id.Addbutton) Button mAddbutton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_created_category);
        ButterKnife.bind(this);

        //collecting input from user
        mEditText = (EditText) findViewById(R.id.editText);
        //validating that input is not left blank
        if (mEditText.getText().toString().length()==0)
            mEditText.setError("Category name is required!");

        mEditText2 = (EditText) findViewById(R.id.editText2);

        mAddbutton = (Button) findViewById(R.id.Addbutton);
        mAddbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String newName = mEditText.getText().toString();
                String newDescription = mEditText2.getText().toString();
                String newVision = newName + newDescription;
                Intent intent = new Intent(CreatedCategory.this, CreatedCategory.class);
                intent.putExtra("newVision", newVision);
                startActivity(intent);
            }
        });
        //getting the name and description from CategoriesActivity
        mNewVisionTextView = (TextView) findViewById(R.id.newVisiontextView);
        Intent intent = getIntent();
        String newVision = intent.getStringExtra("newVision");
        mNewVisionTextView.setText(newVision);
    }
}