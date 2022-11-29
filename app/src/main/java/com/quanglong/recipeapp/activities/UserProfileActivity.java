package com.quanglong.recipeapp.activities;

import static com.quanglong.recipeapp.utilities.BindingAdapter.setImageURL;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.quanglong.recipeapp.R;
import com.quanglong.recipeapp.adapter.RecipeAdapter;
import com.quanglong.recipeapp.adapter.RecipeSaveAdptar;
import com.quanglong.recipeapp.listener.RecipeDetailListener;
import com.quanglong.recipeapp.model.FollowRequest;
import com.quanglong.recipeapp.model.PopularChef;
import com.quanglong.recipeapp.model.Recipe;
import com.quanglong.recipeapp.model.RecipeRequest;
import com.quanglong.recipeapp.responses.RecipeAddResponse;
import com.quanglong.recipeapp.responses.RecipeResponse;
import com.quanglong.recipeapp.utilities.StatusBarConfig;
import com.quanglong.recipeapp.viewmodels.FollowerViewModel;
import com.quanglong.recipeapp.viewmodels.RecipeViewModel;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class UserProfileActivity extends AppCompatActivity implements View.OnClickListener, RecipeDetailListener {
    private Toolbar toolbar;
    private TextView tv_title;
    private TextView itemFollwer;
    private TextView itemFollwing;
    private TextView itemrecipe;
    private TextView itemUsername;
    private TextView job;
    private TextView description;
    private PopularChef chef;
    private TextView total_item;
    private Button btn_follow;
    private CircleImageView avatar;
    private RecyclerView recyclerView;
    private ArrayList<Recipe> mlistreRecipes = new ArrayList<>() ;
    private RecipeViewModel viewModel;
    private RecipeSaveAdptar recipeSaveAdptar;
    private SharedPreferences userLocalDatabase;
    private int id ;
    private FollowerViewModel followerViewModel;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);
        doInitialization();
        StatusBarConfig.StatusBarCustom(this);
        customActionBar();
        Intent intent = getIntent();
        chef = (PopularChef) intent.getSerializableExtra("chef");


        itemFollwer.setOnClickListener(view -> {
            Intent itent1 = new Intent(UserProfileActivity.this,FollowerActivity.class);
            itent1.putExtra("id",chef.getId());
            startActivity(itent1);
        });

        itemFollwing.setOnClickListener(view -> {
            Intent itent2 = new Intent(UserProfileActivity.this,FollowingActivity.class);
            itent2.putExtra("id",chef.getId());
            startActivity(itent2);
        });
        btn_follow.setOnClickListener(view ->{
            if(btn_follow.getText().toString().equals("Following")){
                followerViewModel.unFollow(userLocalDatabase.getInt("id",-1),chef.getId()).observe(this, new Observer<String>() {
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
                FollowRequest followRequest = new FollowRequest(userLocalDatabase.getInt("id",-1),chef.getId());
                followerViewModel.saveFollow(followRequest).observe(this, new Observer<RecipeAddResponse>() {
                    @Override
                    public void onChanged(RecipeAddResponse recipeAddResponse) {
                        if (recipeAddResponse.getMessage().equals("Success!")){
                            btn_follow.setText("Following");
                            btn_follow.setBackgroundResource(R.drawable.bg_following);
                            btn_follow.setTextColor(Color.parseColor("#121212"));
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
        id = userLocalDatabase.getInt("id", -1);
        setNewRecipe(mlistreRecipes);
        getNewRecipe();
        setUserDetail();

    }

    private void setUserDetail() {
        setImageURL(avatar, chef.getAvatar());
        this.itemrecipe.setText(Integer.toString(chef.getTotalRecipe()));
        this.itemFollwer.setText(Integer.toString(chef.getTotalFollowedByOthersUser()));
        this.itemFollwing.setText(Integer.toString(chef.getTotalFollowOtherUser()));
        this.itemUsername.setText(chef.getUserName());
        this.job.setText(chef.getJob());
        this.description.setText(chef.getDescription());
        this.total_item.setText(chef.getTotalRecipe() + " items");

        if (chef.isFollowerUser()){
            this.btn_follow.setText("Following");
            this.btn_follow.setBackground(getResources().getDrawable(R.drawable.bg_following));
            this.btn_follow.setTextColor(getResources().getColor(R.color.text_color));
        }else{
            this.btn_follow.setText("Follow");
            this.btn_follow.setBackground(getResources().getDrawable(R.drawable.bg_button_follow));
            this.btn_follow.setTextColor(getResources().getColor(R.color.white));
        }

    }

    private void setNewRecipe(ArrayList<Recipe> RecipeSaveList) {
        recyclerView.setLayoutManager(new GridLayoutManager(this,1));
        recipeSaveAdptar = new RecipeSaveAdptar(this,RecipeSaveList,this,this);
        recyclerView.setAdapter(recipeSaveAdptar);
    }

    private void getNewRecipe() {
        RecipeRequest newRequest = new RecipeRequest();
        newRequest.setKeyword("");
        newRequest.setListCatId(new int[]{});
        newRequest.setAuthorId(chef.getId());
        newRequest.setName("");
        newRequest.setOrigin("");
        newRequest.setIngredient("");
        newRequest.setMinServer(0);
        newRequest.setMaxServer(0);
        newRequest.setMinTotalViews(0);
        newRequest.setMaxTotalViews(0);
        newRequest.setMinTotalRating(0);
        newRequest.setMaxTotalRating(0);
        newRequest.setMinCalories(0);
        newRequest.setMaxCalories(0);
        newRequest.setMinFat(0);
        newRequest.setMaxFat(0);
        newRequest.setMinProtein(0);
        newRequest.setMaxProtein(0);
        newRequest.setMinCarbo(0);
        newRequest.setMaxCarbo(0);
        newRequest.setMinAvgRating(0);
        newRequest.setMaxAvgRating(5);
        newRequest.setCookTime("");
        newRequest.setStatus(0);
        newRequest.setSortByIdDESC(true);
        newRequest.setSortByNameASC(false);
        newRequest.setSortByServesASC(false);
        newRequest.setSortByServesDESC(false);
        newRequest.setSortByTotalViewDESC(false);
        newRequest.setSortByAvgRatingDESC(false);
        newRequest.setSortByTotalRatingDESC(false);
        newRequest.setSortByCaloriesDESC(false);
        newRequest.setSortByFatDESC(false);
        newRequest.setSortByProteinDESC(false);
        newRequest.setSortByCarbo(false);
        newRequest.setPageIndex(1);
        newRequest.setPageSize(10);
        newRequest.setLoginUserId(userLocalDatabase.getInt("id", -1));
        viewModel.getAllNewRecipe(newRequest).observe(this, new Observer<RecipeResponse>() {
            @Override
            public void onChanged(RecipeResponse recipeResponse) {
                if (recipeResponse.getNewRecipeShow()!= null) {
                    if (recipeResponse.getNewRecipeShow().size() > 0) {
                        int oldCount = mlistreRecipes.size();
                        mlistreRecipes.addAll(recipeResponse.getNewRecipeShow());
                        recipeSaveAdptar.notifyItemRangeInserted(oldCount,mlistreRecipes.size());
                    }
                }
            }
        });

    }

    private void customActionBar() {
        setSupportActionBar(toolbar);
        this.tv_title.setText("Profile");
        getSupportActionBar().setTitle("");

        // Customize the back button
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_left);
        // showing the back button in action bar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void doInitialization() {
        this.toolbar = findViewById(R.id.toolbar);
        this.tv_title = findViewById(R.id.toolbar_title);
        this.itemFollwer = findViewById(R.id.itemFollwer);
        this.itemFollwing = findViewById(R.id.itemFollwing);
        this.avatar = findViewById(R.id.profile_image);
        this.itemrecipe = findViewById(R.id.itemrecipe);
        this.itemUsername = findViewById(R.id.itemUsername);
        this.job = findViewById(R.id.job);
        this.btn_follow = findViewById(R.id.btn_follow);
        this.description = findViewById(R.id.description);
        total_item = findViewById(R.id.total_item);
        recyclerView = findViewById(R.id.idMain);
        viewModel = new ViewModelProvider(this).get(RecipeViewModel.class);
        this.userLocalDatabase = UserProfileActivity.this.getSharedPreferences("userDetails", 0);
        followerViewModel = new ViewModelProvider(this).get(FollowerViewModel.class);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case android.R.id.home:
                super.onBackPressed();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void onRecipeDetailListener(int recipe_id, String recipe_name) {
        Intent intent = new Intent(UserProfileActivity.this, RecipeDetailActivity.class);
        intent.putExtra("recipeId",recipe_id);
        intent.putExtra("recipeName",recipe_name);
        startActivity(intent);
    }
}