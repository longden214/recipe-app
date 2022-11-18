package com.quanglong.recipeapp.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.quanglong.recipeapp.R;
import com.quanglong.recipeapp.utilities.StatusBarConfig;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

public class RecipeByCategoryActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private TextView tv_title;
    private TextView cateName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recype_by_category);
        doInitialization();
        StatusBarConfig.StatusBarCustom(this);
        customActionBar();

        Intent intent = getIntent();
        int id = intent.getIntExtra("cateId", 0);
        String name = intent.getStringExtra("cateName");

        this.cateName.setText(name);
    }

    private void customActionBar() {
        setSupportActionBar(toolbar);
        this.tv_title.setText("");
        getSupportActionBar().setTitle("");

        // Customize the back button
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_left);
        // showing the back button in action bar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void doInitialization() {
        this.toolbar = findViewById(R.id.toolbar);
        this.tv_title = findViewById(R.id.toolbar_title);
        this.cateName = findViewById(R.id.category_name);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case android.R.id.home:
                super.onBackPressed();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}