package com.quanglong.recipeapp.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.quanglong.recipeapp.R;
import com.quanglong.recipeapp.utilities.StatusBarConfig;

public class StartCookingActivity extends AppCompatActivity {
    private Button btn_start;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_cooking);
        StatusBarConfig.StatusBarCustom(this);

        getWindow().getDecorView().setSystemUiVisibility(0);

        doInitialization();

        this.btn_start.setOnClickListener(view -> {
            Intent intent = new Intent(this, SignInActivity.class);
            startActivity(intent);
        });
    }

    public void doInitialization(){
        this.btn_start = (Button) findViewById(R.id.medium_butt);
    }

}