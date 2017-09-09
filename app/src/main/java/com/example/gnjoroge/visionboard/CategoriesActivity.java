package com.example.gnjoroge.visionboard;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.Bind;
import butterknife.ButterKnife;

public class CategoriesActivity extends AppCompatActivity {
    private static final String TAG = CategoriesActivity.class.getSimpleName();
    @Bind(R.id.Addbutton) Button mAddbutton;
    @Bind(R.id.editText) EditText mEditText;
    @Bind(R.id.editText2) EditText mEditText2;
    @Bind(R.id.listView) ListView mListView;
    private String[] categories = new String[] {"Finances", "Relationships", "Career", "Health", "Travel", "Personal growth"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories);
        ButterKnife.bind(this);
        //collecting input from user
        mEditText = (EditText) findViewById(R.id.editText);
        //validating that input is not left blank
        if (mEditText.getText().toString().length()==0)
            mEditText.setError("Category name is required!");

        mEditText2 = (EditText) findViewById(R.id.editText2);

        mAddbutton = (Button) findViewById(R.id.Addbutton);
        mAddbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String newName = mEditText.getText().toString();
                String newDescription = mEditText2.getText().toString();
                String newVision = newName + newDescription;
                Intent intent = new Intent(CategoriesActivity.this, CreatedCategory.class);
                intent.putExtra("newVision", newVision);
                startActivity(intent);
            }
        });

        //displaying the categories
       mListView = (ListView) findViewById(R.id.listView);
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, categories);
        mListView.setAdapter(adapter);

        //adding a toast for each suggested category
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String categories = ((TextView)view).getText().toString();
                Toast.makeText(CategoriesActivity.this, categories, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
