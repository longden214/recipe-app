package com.quanglong.recipeapp.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.tabs.TabLayout;
import com.quanglong.recipeapp.R;
import com.quanglong.recipeapp.adapter.RecipeDetailVPAdapter;
import com.quanglong.recipeapp.fragments.RateDialog;
import com.quanglong.recipeapp.fragments.ShareDialog;
import com.quanglong.recipeapp.utilities.StatusBarConfig;

public class RecipeDetailActivity extends AppCompatActivity {

    TabLayout tabLayout;
    ViewPager viewPager;
    RecipeDetailVPAdapter viewPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_detail);
        StatusBarConfig.StatusBarCustom(this);
        doInitialization();
        customActionBar();

        viewPager = findViewById(R.id.viewPager);
        tabLayout = findViewById(R.id.tabLayout);

        viewPagerAdapter = new RecipeDetailVPAdapter(
                getSupportFragmentManager());
        viewPager.setAdapter(viewPagerAdapter);

        // It is used to join TabLayout with ViewPager.
        tabLayout.setupWithViewPager(viewPager);
    }

    private void customActionBar() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle(null);
        actionBar.setElevation(0);

        // Customize the back button
        actionBar.setHomeAsUpIndicator(R.drawable.ic_arrow_left);
        // showing the back button in action bar
        actionBar.setDisplayHomeAsUpEnabled(true);

    }

    private void doInitialization() {
    }

    @Override
    public boolean onCreateOptionsMenu( Menu menu ) {
        getMenuInflater().inflate(R.menu.recipe_header_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected( @NonNull MenuItem item ) {

        switch (item.getItemId()){
            case R.id.menu_share:
                ShowShareDialog();

                break;
            case R.id.menu_rate:
                ShowRateDialog();

                break;
            case R.id.menu_unsave:
                Toast.makeText(this, "Unsave Clicked", Toast.LENGTH_SHORT).show();
                break;
            case android.R.id.home:
                super.onBackPressed();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void ShowRateDialog() {
        FragmentManager fm = getSupportFragmentManager();
        RateDialog rateDialog = new RateDialog();
        rateDialog.show(fm,"RateDialog");
    }

    private void ShowShareDialog() {
        FragmentManager fm = getSupportFragmentManager();
        ShareDialog shareDialog = new ShareDialog();
        shareDialog.show(fm,"ShareDialog");
    }
}