package com.example.gnjoroge.visionboard.ui;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.gnjoroge.visionboard.Constants;
import com.example.gnjoroge.visionboard.R;
import com.example.gnjoroge.visionboard.adapters.FirebaseCategoryListAdapter;
import com.example.gnjoroge.visionboard.adapters.FirebaseCategoryViewHolder;
import com.example.gnjoroge.visionboard.models.Category;
import com.example.gnjoroge.visionboard.util.OnStartDragListener;
import com.example.gnjoroge.visionboard.util.SimpleItemTouchHelperCallback;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class SavedImageListFragment extends Fragment implements OnStartDragListener {

    @Bind(R.id.recyclerView) RecyclerView mRecyclerView;

    private DatabaseReference mCategoryReference;
    private FirebaseCategoryListAdapter mFirebaseAdapter;
    private ItemTouchHelper mItemTouchHelper;


    public SavedImageListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_saved_image_list, container, false);
        ButterKnife.bind(this, view);
        setUpFirebaseAdapter();
        return view;
    }

    private void setUpFirebaseAdapter() {

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String uid = user.getUid();

        Query query = FirebaseDatabase
                .getInstance()
                .getReference(Constants.FIREBASE_CHILD_CATEGORY)
                .child(uid)
                .orderByChild(Constants.FIREBASE_QUERY_INDEX);
//
//        mCategoryReference = FirebaseDatabase
//                .getInstance()
//                .getReference(Constants.FIREBASE_CHILD_CATEGORY);

        mFirebaseAdapter = new FirebaseCategoryListAdapter(Category.class, R.layout.category_list_item_drag, FirebaseCategoryViewHolder.class, query, this, getActivity());

//        mFirebaseAdapter = new FirebaseRecyclerAdapter<Category, FirebaseCategoryViewHolder>(Category.class, R.layout.category_list_item_drag, FirebaseCategoryViewHolder.class, mCategoryReference) {
//            @Override
//            protected void populateViewHolder(FirebaseCategoryViewHolder viewHolder, Category model, int position) {
//
//                viewHolder.bindCategory(model);
//
//            }
//        };

        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setAdapter(mFirebaseAdapter);
//        ItemTouchHelper.Callback callback = new SimpleItemTouchHelperCallback(mFirebaseAdapter);
//        mItemTouchHelper = new ItemTouchHelper(callback);
//        mItemTouchHelper.attachToRecyclerView(mRecyclerView);
        mFirebaseAdapter.registerAdapterDataObserver(new RecyclerView.AdapterDataObserver() {
            @Override
            public void onItemRangeInserted(int positionStart, int itemCount) {
                super.onItemRangeInserted(positionStart, itemCount);
                mFirebaseAdapter.notifyDataSetChanged();
            }
        });
    }


    @Override
    public void onStartDrag(RecyclerView.ViewHolder viewHolder) {
        mItemTouchHelper.startDrag(viewHolder);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mFirebaseAdapter.cleanup();
    }


}