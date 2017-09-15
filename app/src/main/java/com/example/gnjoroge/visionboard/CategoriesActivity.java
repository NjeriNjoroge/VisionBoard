package com.example.gnjoroge.visionboard;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class CategoriesActivity extends AppCompatActivity {
    private static final String TAG = CategoriesActivity.class.getSimpleName();
    @Bind(R.id.listView)
    ListView mListView;
    private String[] categories = new String[]{"Finances", "Relationships", "Career", "Health", "Travel", "Personal growth"};


    private void getPhotos(String category) {
        final FlickrService flickrService = new FlickrService();
        flickrService.findPhotos(category, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException{
                try {
                    String jsonData = response.body().string();
                    Log.v(TAG, jsonData);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        String category = intent.getStringExtra("category");
        getPhotos(category);

        //displaying the categories
        mListView = (ListView) findViewById(R.id.listView);
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, categories);
        mListView.setAdapter(adapter);

        //adding a toast for each suggested category
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String categories = ((TextView) view).getText().toString();
                Toast.makeText(CategoriesActivity.this, categories, Toast.LENGTH_SHORT).show();
            }
        });
    }


}//end