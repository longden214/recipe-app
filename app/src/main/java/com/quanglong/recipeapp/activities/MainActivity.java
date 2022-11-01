package com.quanglong.recipeapp.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.quanglong.recipeapp.R;
import com.quanglong.recipeapp.utilities.StatusBarConfig;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        StatusBarConfig.StatusBarCustom(this);
    }
}