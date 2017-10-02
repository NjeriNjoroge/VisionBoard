package com.example.gnjoroge.visionboard.ui;


import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import android.support.v7.widget.SearchView;


import com.example.gnjoroge.visionboard.Constants;
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





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories);

    }










}//end