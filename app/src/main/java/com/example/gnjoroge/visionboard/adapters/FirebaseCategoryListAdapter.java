package com.example.gnjoroge.visionboard.adapters;

import android.content.Context;
import android.support.v4.view.MotionEventCompat;
import android.view.MotionEvent;
import android.view.View;

import com.example.gnjoroge.visionboard.ui.SavedImageListFragment;
import com.example.gnjoroge.visionboard.util.OnStartDragListener;

import com.example.gnjoroge.visionboard.models.Category;
import com.example.gnjoroge.visionboard.util.ItemTouchHelperAdapter;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by gnjoroge on 9/29/17.
 */

public class FirebaseCategoryListAdapter extends FirebaseRecyclerAdapter<Category, FirebaseCategoryViewHolder> implements ItemTouchHelperAdapter {

    private DatabaseReference mRef;
    private OnStartDragListener mOnStartDragListener;
    private Context mContext;

    private ChildEventListener mChildEventListener;
    private ArrayList<Category> mCategory = new ArrayList<>();

    public FirebaseCategoryListAdapter(Class<Category> modelClass, int modelLayout,
                                       Class<FirebaseCategoryViewHolder> viewHolderClass,
                                       Query ref, OnStartDragListener onStartDragListener, Context context) {

        super(modelClass, modelLayout, viewHolderClass, ref);
        mRef = ref.getRef();
        mOnStartDragListener = onStartDragListener;
        mContext = context;

        mChildEventListener = mRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                mCategory.add(dataSnapshot.getValue(Category.class));

            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    //overriding the methods from the interfaces being implemented


    @Override
    public boolean onItemMove(int fromPosition, int toPosition) {
        Collections.swap(mCategory, fromPosition, toPosition);
        notifyItemMoved(fromPosition, toPosition);
        return false;
    }

    @Override
    public void onItemDismiss(int position) {

        mCategory.remove(position);
        getRef(position).removeValue();

    }

    @Override
    protected void populateViewHolder(final FirebaseCategoryViewHolder viewHolder, Category model, int position) {
        viewHolder.bindCategory(model);
        viewHolder.mCategoryImageView.setOnTouchListener(new View.OnTouchListener(){
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (MotionEventCompat.getActionMasked(event) == MotionEvent.ACTION_DOWN) {
                    mOnStartDragListener.onStartDrag(viewHolder);
                }
                return false;
            }
        });
    }

    //re-assigns the index property for each category object in our array list

    private void setIndexInFirebase() {
        for (Category category : mCategory) {
            int index = mCategory.indexOf(category);
            DatabaseReference ref = getRef(index);
            category.setIndex(Integer.toString(index));
            ref.setValue(category);
        }
    }

    @Override
    public void cleanup() {
        super.cleanup();
        setIndexInFirebase();
        mRef.removeEventListener(mChildEventListener);
    }
}