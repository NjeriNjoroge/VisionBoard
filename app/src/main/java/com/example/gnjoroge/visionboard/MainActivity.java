package com.example.gnjoroge.visionboard;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private Button mNewVisionBoardButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mNewVisionBoardButton = (Button) findViewById(R.id.newVisionBoard);
        //changes the text on the button to white
        mNewVisionBoardButton.setTextColor(Color.parseColor("white"));
        mNewVisionBoardButton.setPaintFlags(mNewVisionBoardButton.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);

        mNewVisionBoardButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, CategoriesActivity.class);
                startActivity(intent);
            }
        });

    }

}
