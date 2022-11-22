package com.quanglong.recipeapp.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.quanglong.recipeapp.R;
import com.quanglong.recipeapp.adapter.NewAdapter;
import com.quanglong.recipeapp.adapter.RecipeAdapter;
import com.quanglong.recipeapp.listener.RecipeDetailListener;
import com.quanglong.recipeapp.model.Recipe;
import com.quanglong.recipeapp.model.RecipeRequest;
import com.quanglong.recipeapp.responses.RecipeResponse;
import com.quanglong.recipeapp.utilities.StatusBarConfig;
import com.quanglong.recipeapp.viewmodels.RecipeViewModel;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class RecipeByCategoryActivity extends AppCompatActivity implements View.OnClickListener, RecipeDetailListener {
    private Toolbar toolbar;
    private TextView tv_title;
    private TextView cateName;
    private RecyclerView recyclerView;
    private ArrayList<Recipe> mlistreRecipes = new ArrayList<>() ;
    private RecipeViewModel viewModel;
    private RecipeAdapter adapter;
    private int currentPage = 1;
    private int totalAvailablePages = 1;
    private ProgressBar progressBar_loading;
    private ProgressBar progressBar_more;
    private boolean isLoading =false;
    private boolean isLoadingMore = false;
    private int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recype_by_category);
        doInitialization();
        StatusBarConfig.StatusBarCustom(this);
        customActionBar();
        Intent intent = getIntent();
        id = intent.getIntExtra("cateId", 0);
        String name = intent.getStringExtra("cateName");

        setNewRecipe(mlistreRecipes);
        getNewRecipe();

        this.cateName.setText(name);

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if(!recyclerView.canScrollVertically(1)){
                    if(currentPage<totalAvailablePages){
                        currentPage +=1;
                        getNewRecipe();
                    }
                }
            }
        });
    }

    private void customActionBar() {
        setSupportActionBar(toolbar);
        this.tv_title.setText("");
        getSupportActionBar().setTitle("");

        // Customize the back button
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_left);
        // showing the back button in action bar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void setNewRecipe(ArrayList<Recipe> recipesList) {
        recyclerView.setLayoutManager(new GridLayoutManager(this,2));
        adapter = new RecipeAdapter(recipesList,this,this);
        recyclerView.setAdapter(adapter);
    }

    private void getNewRecipe() {
        toggleLoading();
        RecipeRequest newRequest = new RecipeRequest();
        newRequest.setKeyword("");
        newRequest.setListCatId(new int[]{id});
        newRequest.setAuthorId(0);
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
        newRequest.setStatus(-1);
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
        newRequest.setPageIndex(currentPage);
        newRequest.setPageSize(10);
        viewModel.getAllNewRecipe(newRequest).observe(this, new Observer<RecipeResponse>() {
            @Override
            public void onChanged(RecipeResponse recipeResponse) {
                if (recipeResponse.getNewRecipeShow()!= null) {
                    totalAvailablePages = recipeResponse.getTotalPage();
                    if (recipeResponse.getNewRecipeShow().size() > 0) {
                        if(currentPage==1){
                            isLoading=true;
                        }else {
                            isLoadingMore=true;
                        }
                        toggleLoading();
                        int oldCount = mlistreRecipes.size();
                        mlistreRecipes.addAll(recipeResponse.getNewRecipeShow());
                        adapter.notifyItemRangeInserted(oldCount,mlistreRecipes.size());
                    }
                }
            }
        });

    }
    private void toggleLoading(){
        if(currentPage ==1){
            if(isLoading){
                progressBar_loading.setVisibility(View.GONE);
                isLoading=false;
            }else {
                progressBar_loading.setVisibility(View.VISIBLE);
            }
        }else {
            if(isLoadingMore){
                progressBar_more.setVisibility(View.GONE);
                isLoadingMore = false;
            }else {
                progressBar_more.setVisibility(View.VISIBLE);
            }
        }
    }

    private void doInitialization() {
        this.toolbar = findViewById(R.id.toolbar);
        this.tv_title = findViewById(R.id.toolbar_title);
        this.cateName = findViewById(R.id.category_name);
        recyclerView = findViewById(R.id.rvMain1);
        viewModel = new ViewModelProvider(this).get(RecipeViewModel.class);
        progressBar_loading = findViewById(R.id.progressBar_loading);
        progressBar_more = findViewById(R.id.progressBar_more);
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
        Intent intent = new Intent(RecipeByCategoryActivity.this,RecipeDetailActivity.class);
        intent.putExtra("recipeId",recipe_id);
        intent.putExtra("recipeName",recipe_name);
        startActivity(intent);
    }
}