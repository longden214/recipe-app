package com.quanglong.recipeapp.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.quanglong.recipeapp.R;
import com.quanglong.recipeapp.fragments.HomeFragment;
import com.quanglong.recipeapp.fragments.NotificationFragment;
import com.quanglong.recipeapp.fragments.ProfileFragment;
import com.quanglong.recipeapp.fragments.SaveFragment;
import com.quanglong.recipeapp.responses.UserLoginResponse;
import com.quanglong.recipeapp.utilities.StatusBarConfig;
import com.quanglong.recipeapp.utilities.UserLocalStore;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    UserLocalStore userLocalStore;
    private SharedPreferences userLocalDatabase;
    BottomNavigationView bottomNavigationView;
    FloatingActionButton btn_add_recipe;

    HomeFragment homeFragment = new HomeFragment();
    SaveFragment saveFragment = new SaveFragment();
    NotificationFragment notificationFragment = new NotificationFragment();
    ProfileFragment profileFragment = new ProfileFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        StatusBarConfig.StatusBarCustom(this);
        doInitialization();

        this.bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottomNavigationView);
        this.bottomNavigationView.setBackground(null);
        this.bottomNavigationView.getMenu().getItem(2).setEnabled(false);

        this.bottomNavigationView.setOnNavigationItemSelectedListener(this);
        getSupportFragmentManager().beginTransaction().replace(R.id.container, homeFragment).commit();

        this.btn_add_recipe.setOnClickListener(view -> {
            Intent intent = new Intent(this, AddRecipeActivity.class);
            startActivity(intent);
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (authenticate() == true) {
            displayUserDetails();
        }
    }

    private void doInitialization() {
        this.btn_add_recipe = findViewById(R.id.btn_add_recipe);
        userLocalStore = new UserLocalStore(this);
        this.userLocalDatabase = getSharedPreferences("userDetails", 0);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_home:
                getSupportFragmentManager().beginTransaction().replace(R.id.container, homeFragment).commit();
                return true;
            case R.id.menu_save:
                getSupportFragmentManager().beginTransaction().replace(R.id.container, saveFragment).commit();
                return true;
            case R.id.menu_notification:
                getSupportFragmentManager().beginTransaction().replace(R.id.container, notificationFragment).commit();
                return true;
            case R.id.menu_Profile:
                getSupportFragmentManager().beginTransaction().replace(R.id.container, profileFragment).commit();
                return true;
        }
        return false;
    }

    private boolean authenticate() {
        int id = userLocalDatabase.getInt("id", -1);
        if (userLocalStore.getLoggedInUser() == null && id == -1) {
            Intent intent = new Intent(this, SignInActivity.class);
            startActivity(intent);

            return false;
        }
        return true;
    }

    private void displayUserDetails() {
        UserLoginResponse user = userLocalStore.getLoggedInUser();
    }
}