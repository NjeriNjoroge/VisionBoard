package com.example.gnjoroge.visionboard.ui;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;


import com.example.gnjoroge.visionboard.adapters.CategoryListAdapter;
import com.example.gnjoroge.visionboard.services.FlickrService;
import com.example.gnjoroge.visionboard.R;
import com.example.gnjoroge.visionboard.models.Category;

import java.io.IOException;
import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;



public class CategoryListActivity extends AppCompatActivity {
    public static final String TAG = CategoryListActivity.class.getSimpleName();
    //@Bind(R.id.listView) ListView mListView;
    @Bind(R.id.recyclerView) RecyclerView mRecyclerView;
    private CategoryListAdapter mAdapter;

    public ArrayList<Category> mCategories = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories);
        ButterKnife.bind(this);


        Intent intent = getIntent();
        String category = intent.getStringExtra("category");
        getPhotos(category);

    }

    //creating a callback for the Flickr service
    private void getPhotos(String category){

        final FlickrService flickrService = new FlickrService();
        flickrService.findPhotos(category, new Callback(){

            @Override
            public void onFailure(Call call, IOException e){
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) {
                mCategories = flickrService .processResults(response);
                CategoryListActivity.this.runOnUiThread(new Runnable(){
                    @Override
                    public void run(){
                        mAdapter = new CategoryListAdapter(getApplicationContext(), mCategories);
                        mRecyclerView.setAdapter(mAdapter);
                        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(CategoryListActivity.this);
                        mRecyclerView.setLayoutManager(layoutManager);
                        mRecyclerView.setHasFixedSize(true);
                    }
                });
            }
        });
    }




}//end