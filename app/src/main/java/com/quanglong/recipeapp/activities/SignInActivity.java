package com.quanglong.recipeapp.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.quanglong.recipeapp.R;
import com.quanglong.recipeapp.utilities.StatusBarConfig;

public class SignInActivity extends AppCompatActivity {

    TextView txt_sign_up;
    Button btn_sign_in;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);

        StatusBarConfig.StatusBarCustom(this);

        doInitialization();

        this.txt_sign_up.setOnClickListener(view -> {
            Intent intent = new Intent(this, SignUpActivity.class);
            startActivity(intent);
        });

        this.btn_sign_in.setOnClickListener(view -> {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        });
    }

    private void doInitialization() {
        this.txt_sign_up = findViewById(R.id.txt_sign_up);
        this.btn_sign_in = findViewById(R.id.btn_sign_in);

    }
}