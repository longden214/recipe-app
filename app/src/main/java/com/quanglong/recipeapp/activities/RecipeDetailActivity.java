package com.quanglong.recipeapp.activities;

import static com.quanglong.recipeapp.utilities.BindingAdapter.setImageURL;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.tabs.TabLayout;
import com.quanglong.recipeapp.R;
import com.quanglong.recipeapp.adapter.RecipeDetailVPAdapter;
import com.quanglong.recipeapp.fragments.NotificationBottomSheetFragment;
import com.quanglong.recipeapp.fragments.RateDialog;
import com.quanglong.recipeapp.fragments.ShareDialog;
import com.quanglong.recipeapp.model.FollowRequest;
import com.quanglong.recipeapp.model.Notifications;
import com.quanglong.recipeapp.model.RatingRequest;
import com.quanglong.recipeapp.model.Recipe;
import com.quanglong.recipeapp.model.RecipeRequest;
import com.quanglong.recipeapp.model.SaveRecipeRequest;
import com.quanglong.recipeapp.responses.RecipeAddResponse;
import com.quanglong.recipeapp.responses.RecipeDetailResponse;
import com.quanglong.recipeapp.responses.RecipeResponse;
import com.quanglong.recipeapp.utilities.FCMSend;
import com.quanglong.recipeapp.utilities.StatusBarConfig;
import com.quanglong.recipeapp.viewmodels.FollowerViewModel;
import com.quanglong.recipeapp.viewmodels.RecipeViewModel;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class RecipeDetailActivity extends AppCompatActivity {

    TabLayout tabLayout;
    ViewPager viewPager;
    private Recipe recipe;
    RecipeDetailVPAdapter viewPagerAdapter;
    private ImageView imageView;
    private TextView recipeName;
    private TextView time;
    private TextView reating;
    private TextView recipeReview;
    private TextView recipeChef;
    private TextView location;
    private TextView cal;
    private TextView fat;
    private TextView protein;
    private TextView carbo;
    private CircleImageView avatar;
    private int id;
    private RecipeViewModel recipeViewModel;
    private SharedPreferences userLocalDatabase;
    private Button btn_follow;
    private FollowerViewModel followerViewModel;
    private Menu menu;
    private SharedPreferences userlocalData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_detail);
        StatusBarConfig.StatusBarCustom(this);
        doInitialization();
        customActionBar();
        Intent intent = getIntent();
        id = intent.getIntExtra("recipeId", 0);

        viewPager = findViewById(R.id.viewPager);
        tabLayout = findViewById(R.id.tabLayout);

        viewPagerAdapter = new RecipeDetailVPAdapter(
                getSupportFragmentManager(),id);
        viewPager.setAdapter(viewPagerAdapter);

        // It is used to join TabLayout with ViewPager.
        tabLayout.setupWithViewPager(viewPager);
        getRecipeDetail();

        this.btn_follow.setOnClickListener(view -> {
            if(btn_follow.getText().toString().equals("Following")){
                followerViewModel.unFollow(userLocalDatabase.getInt("id",-1),recipe.getAuthorId()).observe(this, new Observer<String>() {
                    @Override
                    public void onChanged(String s) {
                        if (s != null) {
                            if (s.equals("Success!")){
                                btn_follow.setText("Follow");
                                btn_follow.setBackgroundResource(R.drawable.bg_button_follow);
                                btn_follow.setTextColor(Color.WHITE);
                            }else{
                                if (s.equals("Failed!")){
                                    Toast.makeText(view.getContext(), "Follow failed!", Toast.LENGTH_SHORT).show();
                                }else {
                                    Toast.makeText(view.getContext(), s, Toast.LENGTH_LONG).show();
                                }
                            }
                        }
                    }
                });
            }else{
                FollowRequest followRequest = new FollowRequest(userLocalDatabase.getInt("id",-1),recipe.getAuthorId());
                followerViewModel.saveFollow(followRequest).observe(this, new Observer<RecipeAddResponse>() {
                    @Override
                    public void onChanged(RecipeAddResponse recipeAddResponse) {
                        if (recipeAddResponse.getMessage().equals("Success!")){
                            btn_follow.setText("Following");
                            btn_follow.setBackgroundResource(R.drawable.bg_following);
                            btn_follow.setTextColor(Color.parseColor("#121212"));

                            if (recipeAddResponse.getNotificationModels().size() > 0){
                                for (Notifications item : recipeAddResponse.getNotificationModels()) {
                                    if (item.getListTokenDevice().size() > 0){
                                        for (String itemToken: item.getListTokenDevice()) {
                                            FCMSend.pushNotification(
                                                    RecipeDetailActivity.this,
                                                    itemToken,
                                                    item.getNotificationType(),
                                                    item.getDescription()
                                            );
                                        }
                                    }
                                }
                            }
                        }else{
                            if (recipeAddResponse.getMessage().equals("Failed!")){
                                Toast.makeText(view.getContext(), "Follow failed!", Toast.LENGTH_LONG).show();
                            }else{
                                Toast.makeText(view.getContext(), recipeAddResponse.getMessage(), Toast.LENGTH_LONG).show();
                            }
                        }
                    }
                });
            }
        });
    }

    private void SetRecipeDetail(){

        setImageURL(imageView,recipe.getImage());
        this.recipeName.setText(recipe.getName());
        this.time.setText(recipe.getCookTime()+ " mins");
        this.reating.setText(Float.toString(recipe.getAvgRating()));
        this.recipeChef.setText(recipe.getAuthor());
        setImageURL(avatar,recipe.getAuthorAvatar());
        this.location.setText(recipe.getOrigin());
        this.recipeReview.setText("(" + Integer.toString(recipe.getTotalRating()) + " Reviews)");
        this.cal.setText(Float.toString(recipe.getCalories()));
        this.fat.setText(Float.toString(recipe.getFat()));
        this.protein.setText(Float.toString(recipe.getProtein()));
        this.carbo.setText(Float.toString(recipe.getCarbo()));

        if (recipe.isFollowAuthor()){
            this.btn_follow.setText("Following");
            this.btn_follow.setBackground(getResources().getDrawable(R.drawable.bg_following));
            this.btn_follow.setTextColor(getResources().getColor(R.color.text_color));
        }else{
            this.btn_follow.setText("Follow");
            this.btn_follow.setBackground(getResources().getDrawable(R.drawable.bg_button_follow));
            this.btn_follow.setTextColor(getResources().getColor(R.color.white));
        }
    }

    private void getRecipeDetail() {
        recipeViewModel.getRecipeDetailWithParam(id, userLocalDatabase.getInt("id", -1)).observe(this, new Observer<RecipeDetailResponse>() {
            @Override
            public void onChanged(RecipeDetailResponse recipeDetailResponse) {
                if (recipeDetailResponse != null) {
                    if (recipeDetailResponse.getRecipeDetail() != null) {
                        recipe = (Recipe) recipeDetailResponse.getRecipeDetail();
                        SetRecipeDetail();

                    }
                }
            }
        });
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
        this.avatar = findViewById(R.id.profile_image);
        this.imageView = findViewById(R.id.imageView2);
        this.recipeName = findViewById(R.id.recipe_name);
        this.time = findViewById(R.id.textView13);
        this.reating = findViewById(R.id.textView12);
        this.recipeChef=findViewById(R.id.recipe_chef_name);
        this.location=findViewById(R.id.location_name);
        this.recipeReview=findViewById(R.id.recipe_reviews);
        this.cal=findViewById(R.id.textView23);
        this.fat=findViewById(R.id.textView26);
        this.protein=findViewById(R.id.textView29);
        this.carbo = findViewById(R.id.textView32);
        this.btn_follow = findViewById(R.id.btn_follow);
        recipeViewModel = new ViewModelProvider(this).get(RecipeViewModel.class);
        recipe = new Recipe();
        this.userLocalDatabase = getSharedPreferences("userDetails", 0);
        followerViewModel = new ViewModelProvider(this).get(FollowerViewModel.class);
        this.userlocalData = getSharedPreferences("userDetails",0);
    }

    @Override
    public boolean onCreateOptionsMenu( Menu menu ) {
        getMenuInflater().inflate(R.menu.recipe_header_menu, menu);
        this.menu = menu;
        MenuItem bedMenuItem = menu.findItem(R.id.menu_unsave);

        if (recipe.isSaveRecipe()){
            bedMenuItem.setTitle("Unsave");
        }else{
            bedMenuItem.setTitle("Save");
        }

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

                if(recipe.isSaveRecipe()){
                    recipeViewModel.unRecipe(recipe.getId(),userlocalData.getInt("id",-1)).observe(this, new Observer<String>() {
                        @Override
                        public void onChanged(String s) {
                            if(s != null){
                                if(s.equals("Success!")){
                                    MenuItem bedMenuItem = menu.findItem(R.id.menu_unsave);
                                    bedMenuItem.setTitle("Save");
                                    recipe.setSaveRecipe(false);
                                }else {
                                    if(s.equals("Failed!")){
                                        Toast.makeText(RecipeDetailActivity.this, "Save failed!", Toast.LENGTH_SHORT).show();
                                    }else {
                                        Toast.makeText(RecipeDetailActivity.this, s, Toast.LENGTH_LONG).show();
                                    }
                                }
                            }
                        }
                    });
                }else {
                    SaveRecipeRequest saveRecipeRequest = new SaveRecipeRequest(recipe.getId(),userlocalData.getInt("id",-1));
                    recipeViewModel.saveRecipe(saveRecipeRequest).observe(this, new Observer<String>() {
                        @Override
                        public void onChanged(String s) {
                            if(s.equals("Success!")){
                                MenuItem bedMenuItem = menu.findItem(R.id.menu_unsave);
                                bedMenuItem.setTitle("Unsave");
                                recipe.setSaveRecipe(true);
                            }else {
                                if(s.equals("Failed!")){
                                    Toast.makeText(RecipeDetailActivity.this, "Save failed!", Toast.LENGTH_SHORT).show();
                                }else {
                                    Toast.makeText(RecipeDetailActivity.this, s, Toast.LENGTH_LONG).show();
                                }
                            }
                        }
                    });
                }

                break;
            case android.R.id.home:
                super.onBackPressed();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void ShowRateDialog() {
        FragmentManager fm = getSupportFragmentManager();
        RateDialog rateDialog = new RateDialog(recipe.getAvgRating());
        ((RateDialog) rateDialog).setCallback(new RateDialog.Callback() {
            @Override
            public void onActionClick(float rating) {
                RatingRequest request = new RatingRequest();
                request.setRecipeId(recipe.getId());
                request.setRating((int)(Math.round(rating)));
                request.setUserId(userLocalDatabase.getInt("id", -1));

                recipeViewModel.recipeRating(request).observe(RecipeDetailActivity.this, new Observer<RecipeAddResponse>() {
                    @Override
                    public void onChanged(RecipeAddResponse res) {
                        if (res != null) {
                            if (res.getMessage().equals("Success!")){
                                recipe.setTotalRating(recipe.getTotalRating() + 1);
                                recipeReview.setText("(" + Integer.toString(recipe.getTotalRating()) + " Reviews)");

                                if (res.getNotificationModels().size() > 0){
                                    for (Notifications item : res.getNotificationModels()) {
                                        if (item.getListTokenDevice().size() > 0){
                                            for (String itemToken: item.getListTokenDevice()) {
                                                FCMSend.pushNotification(
                                                        RecipeDetailActivity.this,
                                                        itemToken,
                                                        item.getNotificationType(),
                                                        item.getDescription()
                                                );
                                            }
                                        }
                                    }
                                }
                            }else{
                                if (res.getMessage().equals("Failed!")){
                                    Toast.makeText(RecipeDetailActivity.this, "Rating recipe failed!", Toast.LENGTH_LONG).show();
                                }else{
                                    Toast.makeText(RecipeDetailActivity.this, res.getMessage(), Toast.LENGTH_LONG).show();
                                }
                            }
                        }
                    }
                });
            }
        });

        rateDialog.show(fm,"RateDialog");
    }

    private void ShowShareDialog() {
        FragmentManager fm = getSupportFragmentManager();
        ShareDialog shareDialog = new ShareDialog();
        shareDialog.show(fm,"ShareDialog");
    }
}