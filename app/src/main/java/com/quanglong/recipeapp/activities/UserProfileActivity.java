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

public class UserProfileActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private TextView tv_title;
    private TextView itemFollwer;
    private TextView itemFollwing;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);
        doInitialization();
        StatusBarConfig.StatusBarCustom(this);
        customActionBar();

        itemFollwer.setOnClickListener(view -> {
            startActivity(new Intent(this, FollowerActivity.class));
        });

        itemFollwing.setOnClickListener(view -> {
            startActivity(new Intent(this, FollowingActivity.class));
        });
    }

    private void customActionBar() {
        setSupportActionBar(toolbar);
        this.tv_title.setText("Profile");
        getSupportActionBar().setTitle("");

        // Customize the back button
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_left);
        // showing the back button in action bar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void doInitialization() {
        this.toolbar = findViewById(R.id.toolbar);
        this.tv_title = findViewById(R.id.toolbar_title);
        this.itemFollwer = findViewById(R.id.itemFollwer);
        this.itemFollwing = findViewById(R.id.itemFollwing);
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