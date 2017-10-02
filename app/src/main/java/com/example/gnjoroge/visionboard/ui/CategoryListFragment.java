package com.example.gnjoroge.visionboard.ui;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.example.gnjoroge.visionboard.Constants;
import com.example.gnjoroge.visionboard.R;
import com.example.gnjoroge.visionboard.adapters.CategoryListAdapter;
import com.example.gnjoroge.visionboard.models.Category;
import com.example.gnjoroge.visionboard.services.FlickrService;

import java.io.IOException;
import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class CategoryListFragment extends Fragment {

    @Bind(R.id.recyclerView) RecyclerView mRecyclerView;

    public ArrayList<Category> mCategories = new ArrayList<>();

    private CategoryListAdapter mAdapter;
    private SharedPreferences mSharedPreferences;
    private SharedPreferences.Editor mEditor;
    private String mRecentAddress;


    public CategoryListFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
        mEditor = mSharedPreferences.edit();

        // Instructs fragment to include menu options:
        setHasOptionsMenu(true);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_category_list, container, false);
        ButterKnife.bind(this, view);

        mRecentAddress = mSharedPreferences.getString(Constants.PREFERENCE_CATEGORY_KEY, null);
        if (mRecentAddress != null) {
            getPhotos(mRecentAddress);
        }

        return view;
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
                getActivity().runOnUiThread(new Runnable(){
                    @Override
                    public void run(){
                        mAdapter = new CategoryListAdapter(getActivity(), mCategories);
                        mRecyclerView.setAdapter(mAdapter);
                        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
                        mRecyclerView.setLayoutManager(layoutManager);
                        mRecyclerView.setHasFixedSize(true);
                    }
                });
            }
        });
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);

       // MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_search, menu);
        MenuItem menuItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(menuItem);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener(){

            @Override
            public boolean onQueryTextSubmit(String query) {
                addToSharedPreferences(query);
                getPhotos(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }


        });


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    private void addToSharedPreferences(String category) {
        mEditor.putString(Constants.PREFERENCE_CATEGORY_KEY, category).apply();
    }

}
