package com.quanglong.recipeapp.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.quanglong.recipeapp.R;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class EditProfileActivity extends AppCompatActivity {
    TextView txtsettingAccount;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        doInitialization();

        this.txtsettingAccount.setOnClickListener(view -> {
            Intent intent = new Intent(this, AccountActivity.class);
            startActivity(intent);
        });
    }
    public void doInitialization(){
        this.txtsettingAccount = (TextView) findViewById(R.id.btn_setting_account);
    }


}