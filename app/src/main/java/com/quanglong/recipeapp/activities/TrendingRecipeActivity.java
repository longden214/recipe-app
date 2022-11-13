package com.quanglong.recipeapp.activities;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.quanglong.recipeapp.R;
import com.quanglong.recipeapp.utilities.StatusBarConfig;

public class TrendingRecipeActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private TextView tv_title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_trending);
        doInitialization();
        StatusBarConfig.StatusBarCustom(this);
        customActionBar();

    }

    private void doInitialization() {
        this.toolbar = findViewById(R.id.toolbar);
        this.tv_title = findViewById(R.id.toolbar_title);
    }

    private void customActionBar() {

        setSupportActionBar(toolbar);
        this.tv_title.setText("Trending Now");
        getSupportActionBar().setTitle("");

        // Customize the back button
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_left);
        // showing the back button in action bar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

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