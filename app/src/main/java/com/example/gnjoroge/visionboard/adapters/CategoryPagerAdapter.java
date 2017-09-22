package com.example.gnjoroge.visionboard.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.gnjoroge.visionboard.models.Category;
import com.example.gnjoroge.visionboard.ui.CategoryDetailFragment;

import java.util.ArrayList;

/**
 * Created by gnjoroge on 9/22/17.
 */

public class CategoryPagerAdapter extends FragmentPagerAdapter {

    private ArrayList<Category> mCategories;

    public CategoryPagerAdapter(FragmentManager fm, ArrayList<Category> categories) {

        super(fm);
        mCategories = categories;
    }

    @Override
    public Fragment getItem(int position) {
        return CategoryDetailFragment.newInstance(mCategories.get(position));
    }

    @Override
    public int getCount(){
        return mCategories.size();
    }

    @Override
    public CharSequence getPageTitle(int postion) {
        return mCategories.get(postion).getTitle();
    }
}

