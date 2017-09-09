package com.example.gnjoroge.visionboard;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private TextView mTitleTextView;
    private Button mNewVisionBoardButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //changing the title font
        mTitleTextView = (TextView) findViewById(R.id.textView);
        Typeface caviarFont = Typeface.createFromAsset(getAssets(), "fonts/CaviarDreams.ttf");
        mTitleTextView.setTypeface(caviarFont);

        mNewVisionBoardButton = (Button) findViewById(R.id.newVisionBoard);
        //changes the text on the button to white
        mNewVisionBoardButton.setTextColor(Color.parseColor("white"));

        mNewVisionBoardButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, CategoriesActivity.class);
                startActivity(intent);
            }
        });

    }

}
