package com.example.gnjoroge.visionboard.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.gnjoroge.visionboard.Constants;
import com.example.gnjoroge.visionboard.R;
import com.example.gnjoroge.visionboard.models.Category;
import com.example.gnjoroge.visionboard.ui.CategoryDetailActivity;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

import java.util.ArrayList;

import butterknife.Bind;

/**
 * Created by gnjoroge on 9/22/17.
 */

public class FirebaseCategoryViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    private static final int MAX_WIDTH = 200;
    private static final int MAX_HEIGHT = 200;

    View mView;
    Context mContext;

    public FirebaseCategoryViewHolder(View itemView) {
        super(itemView);
        mView = itemView;
        mContext = itemView.getContext();
        itemView.setOnClickListener(this);
    }

    public void bindCategory(Category category) {
       ImageView restaurantImageView = (ImageView) mView.findViewById(R.id.restaurantImageView);
//        TextView cameraTextView = (TextView) mView.findViewById(R.id.cameraTextView);
//        TextView savePictureButton = (TextView)
//        Text(R.id.savePictureButton) TextView mSavePictureButton;
    Log.d("image url", category.getImage());
        Picasso.with(mContext)
                .load(category.getImage())
                .resize(MAX_WIDTH, MAX_HEIGHT)
                .centerCrop()
                .into(restaurantImageView);
//        cameraTextView.setText(category.getTitle());

    }

    @Override
    public void onClick(View view) {
        final ArrayList<Category> categories = new ArrayList<>();
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference(Constants.FIREBASE_CHILD_CATEGORY);
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    categories.add(snapshot.getValue(Category.class));
                }

                int itemPosition = getLayoutPosition();
                Intent intent = new Intent(mContext, CategoryDetailActivity.class);
                intent.putExtra("position", itemPosition + "");
                intent.putExtra("categories", Parcels.wrap(categories));
                mContext.startActivity(intent);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
