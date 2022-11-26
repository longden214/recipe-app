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
import com.quanglong.recipeapp.model.PopularChef;
import com.quanglong.recipeapp.model.Recipe;
import com.quanglong.recipeapp.model.RecipeRequest;
import com.quanglong.recipeapp.responses.RecipeResponse;
import com.quanglong.recipeapp.utilities.StatusBarConfig;
import com.quanglong.recipeapp.viewmodels.RecipeViewModel;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

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
    private CircleImageView avatar;
    private RecyclerView recyclerView;
    private ArrayList<Recipe> mlistreRecipes = new ArrayList<>() ;
    private RecipeViewModel viewModel;
    private RecipeSaveAdptar recipeSaveAdptar;
    private SharedPreferences userLocalDatabase;
    private int id ;


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
            startActivity(new Intent(this, FollowingActivity.class));
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

    }

    private void setNewRecipe(ArrayList<Recipe> RecipeSaveList) {
        recyclerView.setLayoutManager(new GridLayoutManager(this,1));
        recipeSaveAdptar = new RecipeSaveAdptar(this,RecipeSaveList,this);
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
        this.description = findViewById(R.id.description);
        total_item = findViewById(R.id.total_item);
        recyclerView = findViewById(R.id.idMain);
        viewModel = new ViewModelProvider(this).get(RecipeViewModel.class);
        this.userLocalDatabase = UserProfileActivity.this.getSharedPreferences("userDetails", 0);
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