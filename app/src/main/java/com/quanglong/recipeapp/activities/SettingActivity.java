package com.quanglong.recipeapp.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;

import com.quanglong.recipeapp.R;
import com.quanglong.recipeapp.utilities.StatusBarConfig;
import com.quanglong.recipeapp.utilities.UserLocalStore;

public class SettingActivity extends AppCompatActivity {
    RelativeLayout rv_account;
    RelativeLayout rv_password;
    Toolbar toolbar;
    TextView tv_title;
    TextView btn_signout;
    UserLocalStore userLocalStore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        StatusBarConfig.StatusBarCustom(this);
        doInitialization();
        customActionBar();

        this.rv_account.setOnClickListener(view -> {
            Intent intent = new Intent(this, AccountActivity.class);
            startActivity(intent);
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
        });

        this.rv_password.setOnClickListener(view -> {
            Intent intent = new Intent(this, PasswordActivity.class);
            startActivity(intent);
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
        });

        this.btn_signout.setOnClickListener(view -> {
            userLocalStore.clearUserData();
            userLocalStore.setUserLoggedIn(false);
            Intent loginIntent = new Intent(this, SignInActivity.class);
            startActivity(loginIntent);
        });

    }

    public void doInitialization(){
        this.rv_account = (RelativeLayout) findViewById(R.id.account_setting);
        this.rv_password = (RelativeLayout) findViewById(R.id.password_setting);
        this.toolbar = findViewById(R.id.toolbar);
        this.tv_title = findViewById(R.id.toolbar_title);
        this.btn_signout = findViewById(R.id.btn_signout);
        this.userLocalStore = new UserLocalStore(this);
    }

    private void customActionBar() {

        setSupportActionBar(toolbar);
        this.tv_title.setText("Setting");
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
                super.onBackPressed();
                overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}