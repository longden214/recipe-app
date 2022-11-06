package com.quanglong.recipeapp.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Switch;

import com.quanglong.recipeapp.R;

public class SettingActivity extends AppCompatActivity {
    LinearLayout linearLayoutaccount;
    LinearLayout linearLayoutpassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        Switch simpleSwitch = (Switch) findViewById(R.id.iconnewsletters);
        doInitialization();

        this.linearLayoutaccount.setOnClickListener(view -> {
            Intent intent = new Intent(this, AccountActivity.class);
            startActivity(intent);
        });
        this.linearLayoutpassword.setOnClickListener(view -> {
            Intent intent = new Intent(this, PasswordActivity.class);
            startActivity(intent);
        });

    }

    public void doInitialization(){
        this.linearLayoutaccount = (LinearLayout) findViewById(R.id.account_setting);
        this.linearLayoutpassword = (LinearLayout) findViewById(R.id.password_setting);
    }
}