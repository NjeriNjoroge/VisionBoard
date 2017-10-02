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
import com.google.firebase.database.Query;

import butterknife.Bind;
import butterknife.ButterKnife;

public class SavedImageListActivity extends AppCompatActivity {





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saved_category_list);

    }


}
