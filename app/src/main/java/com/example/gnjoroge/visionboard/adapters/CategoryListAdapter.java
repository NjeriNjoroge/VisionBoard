package com.example.gnjoroge.visionboard.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.gnjoroge.visionboard.R;
import com.example.gnjoroge.visionboard.models.Category;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

import static com.example.gnjoroge.visionboard.R.id.imageView;

/**
 * Created by gnjoroge on 9/17/17.
 */

public class CategoryListAdapter extends RecyclerView.Adapter<CategoryListAdapter.CategoryViewHolder> {

    private static final String TAG = "CategoryListAdapter";
    private ArrayList<Category> mCategories = new ArrayList<>();
    private Context mContext;

    public CategoryListAdapter(Context context, ArrayList<Category> categories) {
        mContext = context;
        mCategories = categories;
    }

    @Override
    public CategoryListAdapter.CategoryViewHolder onCreateViewHolder(ViewGroup parent, int ViewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.category_list_item, parent, false);
        CategoryViewHolder viewHolder = new CategoryViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(CategoryListAdapter.CategoryViewHolder holder, int position) {

        holder.bindCategory(mCategories.get(position));
    }

    @Override
    public int getItemCount() {
        return mCategories.size();
    }


    public class CategoryViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.restaurantImageView)
        ImageView mRestaurantImageView;
        @Bind(R.id.restaurantNameTextView) TextView mNameTextView;
        private Context mContext;

        public CategoryViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(CategoryViewHolder.this, itemView);
            mContext = itemView.getContext();
        }

        public void bindCategory(Category category) {
            Log.d(TAG, "bindCategory: " + category.getImage());
            Picasso.with(mContext)
                    .load(category.getImage())
                    .resize(130, 130)
                    .centerCrop()
                    .into(mRestaurantImageView);
            mNameTextView.setText(category.getId());
        }
    }
}

