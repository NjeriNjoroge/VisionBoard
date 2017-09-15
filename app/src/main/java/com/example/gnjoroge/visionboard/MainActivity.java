package com.example.gnjoroge.visionboard;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
    @Bind(R.id.textView) TextView mTitleTextView;
    @Bind(R.id.newVisionBoard) Button mNewVisionBoardButton;
    @Bind(R.id.Searchbutton) Button mSearchbutton;
    @Bind(R.id.categoryEditText) EditText mcategoryEditText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        //changing the title font
        mTitleTextView = (TextView) findViewById(R.id.textView);
        Typeface caviarFont = Typeface.createFromAsset(getAssets(), "fonts/CaviarDreams.ttf");
        mTitleTextView.setTypeface(caviarFont);

        mNewVisionBoardButton = (Button) findViewById(R.id.newVisionBoard);
        //changes the text on the button to white
        mNewVisionBoardButton.setTextColor(Color.parseColor("white"));
        //displays the activity for creating a new category
        mNewVisionBoardButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, CreatedCategory.class);
                startActivity(intent);
            }
        });

        //displaying the list view activity
        mSearchbutton = (Button) findViewById(R.id.Searchbutton);
        mSearchbutton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                String category = mcategoryEditText.getText().toString();
                Intent intent = new Intent(MainActivity.this, CategoriesActivity.class);
                intent.putExtra("category", category);
                startActivity(intent);
            }
        });


    }

}
