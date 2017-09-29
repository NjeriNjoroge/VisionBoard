package com.example.gnjoroge.visionboard.adapters;

import android.content.Context;
import android.support.v4.view.MotionEventCompat;
import android.view.MotionEvent;
import android.view.View;
import com.example.gnjoroge.visionboard.util.OnStartDragListener;

import com.example.gnjoroge.visionboard.models.Category;
import com.example.gnjoroge.visionboard.util.ItemTouchHelperAdapter;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;

/**
 * Created by gnjoroge on 9/29/17.
 */

public class FirebaseCategoryListAdapter extends FirebaseRecyclerAdapter<Category, FirebaseCategoryViewHolder> implements ItemTouchHelperAdapter {

    private DatabaseReference mRef;
    private OnStartDragListener mOnStartDragListener;
    private Context mContext;

    public FirebaseCategoryListAdapter(Class<Category> modelClass, int modelLayout,
                                       Class<FirebaseCategoryViewHolder> viewHolderClass,
                                       Query ref, OnStartDragListener onStartDragListener, Context context) {

        super(modelClass, modelLayout, viewHolderClass, ref);
        mRef = ref.getRef();
        mOnStartDragListener = onStartDragListener;
        mContext = context;
    }

    //overriding the methods from the interfaces being implemented


    @Override
    public boolean onItemMove(int fromPosition, int toPosition) {
        notifyItemMoved(fromPosition, toPosition);
        return false;
    }

    @Override
    public void onItemDismiss(int position) {

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
}
