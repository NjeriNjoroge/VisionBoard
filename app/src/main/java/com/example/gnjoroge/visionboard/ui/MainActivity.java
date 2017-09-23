package com.example.gnjoroge.visionboard.ui;

import android.content.Intent;
        import android.content.SharedPreferences;
        import android.graphics.Color;
        import android.graphics.Typeface;
        import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
import android.util.Log;
import android.view.View;
        import android.widget.Button;
        import android.widget.EditText;
        import android.widget.TextView;

        import com.example.gnjoroge.visionboard.Constants;
        import com.example.gnjoroge.visionboard.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
        import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import butterknife.Bind;
        import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private DatabaseReference mSearchedCategoryReference;

    private ValueEventListener mSearchedCategoryReferenceListener;

    @Bind(R.id.textView) TextView mTitleTextView;
    @Bind(R.id.newVisionBoard) Button mNewVisionBoardButton;
    @Bind(R.id.Searchbutton) Button mSearchbutton;
//    @Bind(R.id.categoryEditText) EditText mcategoryEditText;
    @Bind(R.id.savedImagesButton) Button mSavedImagesButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

//        mSearchedCategoryReference = FirebaseDatabase
//                .getInstance()
//                .getReference()
//                .child(Constants.FIREBASE_CHILD_SEARCHED_CATEGORY);
//
//        mSearchedCategoryReferenceListener = mSearchedCategoryReference.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//
//                for (DataSnapshot categorySnapShot : dataSnapshot.getChildren()) {
//                    String category = categorySnapShot.getValue().toString();
//                    Log.d("Categories updated", "category: " + category);
//                }
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//
//            }
//        });

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        //changing the title font
        //mTitleTextView = (TextView) findViewById(R.id.textView);
        Typeface caviarFont = Typeface.createFromAsset(getAssets(), "fonts/CaviarDreams.ttf");
        mTitleTextView.setTypeface(caviarFont);

        mNewVisionBoardButton = (Button) findViewById(R.id.newVisionBoard);
        //changes the text on the button to white
        mNewVisionBoardButton.setTextColor(Color.parseColor("white"));
        //displays the activity for creating a new category
        mNewVisionBoardButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, CreatedCategory.class);
                startActivity(intent);
            }
        });

        //displaying the list view activity
        //        mSearchbutton = (Button) findViewById(R.id.Searchbutton);
        mSearchbutton.setOnClickListener(this);
        mSavedImagesButton.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        if(v == mSearchbutton){
//            String category = mcategoryEditText.getText().toString();
//            saveCategoryToFirebase(category);
            Intent intent = new Intent(MainActivity.this, CategoryListActivity.class);
            //intent.putExtra("category", category);
            startActivity(intent);
        }

        if(v == mSavedImagesButton) {
            Intent intent = new Intent(MainActivity.this, SavedImageListActivity.class);
            startActivity(intent);
        }
    }
//
//    public void saveCategoryToFirebase(String category) {
//        mSearchedCategoryReference.push().setValue(category);
//    }
//
//    @Override
//    protected void onDestroy() {
//        super.onDestroy();
//        mSearchedCategoryReference.removeEventListener(mSearchedCategoryReferenceListener);
//    }

}

