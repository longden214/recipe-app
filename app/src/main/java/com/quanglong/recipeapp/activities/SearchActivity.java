package com.quanglong.recipeapp.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.quanglong.recipeapp.R;
import com.quanglong.recipeapp.fragments.FilterBottomSheetFragment;
import com.quanglong.recipeapp.model.Category;
import com.quanglong.recipeapp.utilities.StatusBarConfig;
import com.quanglong.recipeapp.viewmodels.CategoryViewModel;

import java.util.ArrayList;

public class SearchActivity extends AppCompatActivity {
    private RelativeLayout btn_filter;
    private Toolbar toolbar;
    private TextView tv_title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        doInitialization();
        StatusBarConfig.StatusBarCustom(this);
        customActionBar();

        btn_filter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FilterBottomSheetFragment bottomSheetFragment = new FilterBottomSheetFragment();
                bottomSheetFragment.show(getSupportFragmentManager(), bottomSheetFragment.getTag());
            }
        });
    }

    private void doInitialization() {
        btn_filter = (RelativeLayout) findViewById(R.id.btn_search_filter);
        this.toolbar = findViewById(R.id.toolbar);
        this.tv_title = findViewById(R.id.toolbar_title);
    }

    private void customActionBar() {

        setSupportActionBar(toolbar);
        this.tv_title.setText("Search recipes");
        getSupportActionBar().setTitle("");

        // Customize the back button
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_left);
        // showing the back button in action bar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

    @Override
    public boolean onOptionsItemSelected( @NonNull MenuItem item ) {

        switch (item.getItemId()){
            case android.R.id.home:
                startActivity(new Intent(this, MainActivity.class));
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}