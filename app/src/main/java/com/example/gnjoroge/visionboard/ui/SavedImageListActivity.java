package com.example.gnjoroge.visionboard.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.gnjoroge.visionboard.Constants;
import com.example.gnjoroge.visionboard.R;
import com.example.gnjoroge.visionboard.adapters.FirebaseCategoryViewHolder;
import com.example.gnjoroge.visionboard.models.Category;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import butterknife.Bind;
import butterknife.ButterKnife;

public class SavedImageListActivity extends AppCompatActivity {

    private DatabaseReference mCategoryReference;
    private FirebaseRecyclerAdapter mFirebaseAdapter;

    @Bind(R.id.recyclerView) RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories);
        ButterKnife.bind(this);

        mCategoryReference = FirebaseDatabase.getInstance().getReference(Constants.FIREBASE_CHILD_CATEGORY);
        setUpFirebaseAdapter();
    }

    private void setUpFirebaseAdapter() {
        mFirebaseAdapter = new FirebaseRecyclerAdapter<Category, FirebaseCategoryViewHolder>(Category.class, R.layout.category_list_item, FirebaseCategoryViewHolder.class, mCategoryReference) {
            @Override
            protected void populateViewHolder(FirebaseCategoryViewHolder viewHolder, Category model, int position) {

                viewHolder.bindCategory(model);

            }
        };

        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mFirebaseAdapter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mFirebaseAdapter.cleanup();
    }
}
