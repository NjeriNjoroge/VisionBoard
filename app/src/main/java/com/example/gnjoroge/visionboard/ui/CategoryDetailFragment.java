package com.example.gnjoroge.visionboard.ui;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.gnjoroge.visionboard.R;
import com.example.gnjoroge.visionboard.models.Category;
import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class CategoryDetailFragment extends Fragment implements View.OnClickListener {

    private static final int MAX_WIDTH = 400;
    private static final int MAX_HEIGHT = 300;

    @Bind(R.id.categoryImageView) ImageView mImageLabel;
    @Bind(R.id.cameraTextView) TextView mCameraLabel;
    @Bind(R.id.savePictureButton) TextView mSaveRestaurantButton;

    private Category mCategory;


    public static CategoryDetailFragment newInstance(Category category) {

        CategoryDetailFragment categoryDetailFragment = new CategoryDetailFragment();
        Bundle args = new Bundle();
        args.putParcelable("category", Parcels.wrap(category));
        categoryDetailFragment.setArguments(args);
        return categoryDetailFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        mCategory = Parcels.unwrap(getArguments().getParcelable("category"));
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_category_detail, container, false);
        ButterKnife.bind(this, view);
        mCameraLabel.setOnClickListener(this);

        Picasso.with(view.getContext())
                .load(mCategory.getImage())
                .resize(MAX_WIDTH, MAX_HEIGHT)
                .centerCrop()
                .into(mImageLabel);
        mCameraLabel.setText("Take a picture");

        return view;

    }

    @Override
    public void onClick(View v) {
        if (v == mCameraLabel) {
            Intent cameraIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.parse(mCategory.getImage()));
            startActivity(cameraIntent);
        }
    }

}
