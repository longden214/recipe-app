package com.quanglong.recipeapp.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.databinding.DataBindingUtil;

import android.app.DatePickerDialog;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.quanglong.recipeapp.R;
import com.quanglong.recipeapp.utilities.StatusBarConfig;

import java.util.Calendar;

public class AccountActivity extends AppCompatActivity {
    EditText editDate;
    DatePickerDialog.OnDateSetListener setListener;
    Toolbar toolbar;
    TextView tv_title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);
        StatusBarConfig.StatusBarCustom(this);
        doInitialization();
        customActionBar();

        Calendar calendar = Calendar.getInstance();
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);
        editDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        AccountActivity.this, android.R.style.Theme_Holo_Light_Dialog_MinWidth
                        ,setListener,year,month,day);
                datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                datePickerDialog.show();
            }
        });
        setListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month = month+1;
                String date = day+"/"+month+"/"+year;
                editDate.setText(date);
            }
        };
    }

    private void customActionBar() {
        setSupportActionBar(toolbar);
        this.tv_title.setText("Account");
        getSupportActionBar().setTitle("");

        // Customize the back button
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_left);
        // showing the back button in action bar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

    private void doInitialization() {
        this.editDate = findViewById(R.id.itembirthday);
        this.toolbar = findViewById(R.id.toolbar);
        this.tv_title = findViewById(R.id.toolbar_title);
    }

    @Override
    public boolean onOptionsItemSelected( @NonNull MenuItem item ) {

        switch (item.getItemId()){
            case android.R.id.home:
                super.onBackPressed();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}