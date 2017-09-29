package com.example.gnjoroge.visionboard.ui;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gnjoroge.visionboard.Constants;
import com.example.gnjoroge.visionboard.R;
import com.example.gnjoroge.visionboard.models.Category;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
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
    @Bind(R.id.savePictureButton) Button mSavePictureButton;

    private Category mCategory;
//    private String mSource;


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
        setHasOptionsMenu(true);

    }

//    @Override
//    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
//        super.onCreateOptionsMenu(menu, inflater);
//        if (mSource.equals(Constants.SOURCE_SAVED)) {
//            inflater.inflate(R.menu.menu_photo, menu);
//        } else {
//            inflater.inflate(R.menu.menu_main, menu);
//        }
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        switch (item.getItemId()) {
//            case R.id.action_photo:
//                onLaunchCamera();
//            default:
//                break;
//        }
//        return false;
//    }
//
//    private void onLaunchCamera() {
//    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_category_detail, container, false);
        ButterKnife.bind(this, view);

        mCategory = Parcels.unwrap(getArguments().getParcelable("category"));
        mCameraLabel.setOnClickListener(this);

        Picasso.with(view.getContext())
                .load(mCategory.getImage())
                .resize(MAX_WIDTH, MAX_HEIGHT)
                .centerCrop()
                .into(mImageLabel);
        mCameraLabel.setText("Take a picture");

        mSavePictureButton.setOnClickListener(this);

        return view;

    }

    @Override
    public void onClick(View v) {
        if (v == mCameraLabel) {
            Intent cameraIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.parse(mCategory.getImage()));
            startActivity(cameraIntent);
        }

        if(v == mSavePictureButton) {
            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
            String uid = user.getUid();
            DatabaseReference categoryRef = FirebaseDatabase
                    .getInstance()
                    .getReference(Constants.FIREBASE_CHILD_CATEGORY)
                    .child(uid);

            DatabaseReference pushRef = categoryRef.push();
            String pushId = pushRef.getKey();
            mCategory.setPushId(pushId);
            pushRef.setValue(mCategory);


            categoryRef.push().setValue(mCategory);
            Toast.makeText(getContext(), "Saved", Toast.LENGTH_SHORT).show();
        }
    }

}
