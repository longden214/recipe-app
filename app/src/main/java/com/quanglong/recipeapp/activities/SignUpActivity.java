package com.quanglong.recipeapp.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.quanglong.recipeapp.R;
import com.quanglong.recipeapp.utilities.StatusBarConfig;

public class SignUpActivity extends AppCompatActivity {

    TextView txt_sign_in;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        StatusBarConfig.StatusBarCustom(this);

        doInitialization();

        this.txt_sign_in.setOnClickListener(view -> {

            Intent intent = new Intent(this, SignInActivity.class);
            startActivity(intent);
        });
    }

    private void doInitialization() {
        this.txt_sign_in = findViewById(R.id.txt_sign_in);

    }
}