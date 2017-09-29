package com.example.gnjoroge.visionboard.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

import com.example.gnjoroge.visionboard.Constants;
import com.example.gnjoroge.visionboard.R;
import com.example.gnjoroge.visionboard.adapters.FirebaseCategoryListAdapter;
import com.example.gnjoroge.visionboard.adapters.FirebaseCategoryViewHolder;
import com.example.gnjoroge.visionboard.models.Category;
import com.example.gnjoroge.visionboard.util.ItemTouchHelperAdapter;
import com.example.gnjoroge.visionboard.util.OnStartDragListener;
import com.example.gnjoroge.visionboard.util.SimpleItemTouchHelperCallback;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import butterknife.Bind;
import butterknife.ButterKnife;

public class SavedImageListActivity extends AppCompatActivity implements OnStartDragListener {

    private DatabaseReference mCategoryReference;
    private FirebaseCategoryListAdapter mFirebaseAdapter;
    private ItemTouchHelper mItemTouchHelper;

    @Bind(R.id.recyclerView) RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories);
        ButterKnife.bind(this);
        setUpFirebaseAdapter();

//        mCategoryReference = FirebaseDatabase.getInstance().getReference(Constants.FIREBASE_CHILD_CATEGORY);
//        setUpFirebaseAdapter();
    }

    private void setUpFirebaseAdapter() {

        mCategoryReference = FirebaseDatabase
                .getInstance()
                .getReference(Constants.FIREBASE_CHILD_CATEGORY);

        mFirebaseAdapter = new FirebaseCategoryListAdapter(Category.class, R.layout.category_list_item_drag, FirebaseCategoryViewHolder.class, mCategoryReference, this, this);

//        mFirebaseAdapter = new FirebaseRecyclerAdapter<Category, FirebaseCategoryViewHolder>(Category.class, R.layout.category_list_item_drag, FirebaseCategoryViewHolder.class, mCategoryReference) {
//            @Override
//            protected void populateViewHolder(FirebaseCategoryViewHolder viewHolder, Category model, int position) {
//
//                viewHolder.bindCategory(model);
//
//            }
//        };

        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mFirebaseAdapter);
        ItemTouchHelper.Callback callback = new SimpleItemTouchHelperCallback(mFirebaseAdapter);
        mItemTouchHelper = new ItemTouchHelper(callback);
        mItemTouchHelper.attachToRecyclerView(mRecyclerView);
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        mFirebaseAdapter.cleanup();
    }

    @Override
    public void onStartDrag(RecyclerView.ViewHolder viewHolder) {
        mItemTouchHelper.startDrag(viewHolder);
    }
}
