package com.quanglong.recipeapp.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.quanglong.recipeapp.R;
import com.quanglong.recipeapp.adapter.RecipeAdapter;
import com.quanglong.recipeapp.fragments.FilterBottomSheetFragment;
import com.quanglong.recipeapp.listener.RecipeDetailListener;
import com.quanglong.recipeapp.model.Recipe;
import com.quanglong.recipeapp.model.RecipeRequest;
import com.quanglong.recipeapp.responses.RecipeResponse;
import com.quanglong.recipeapp.utilities.StatusBarConfig;
import com.quanglong.recipeapp.viewmodels.RecipeViewModel;

import java.util.ArrayList;

public class SearchActivity extends AppCompatActivity implements RecipeDetailListener {
    private RelativeLayout btn_filter;
    private Toolbar toolbar;
    private TextView tv_title;
    private EditText search_recipe;
    private RecyclerView recyclerView;
    private ArrayList<Recipe> mlistreRecipes = new ArrayList<>();
    private RecipeViewModel viewModel;
    private RecipeAdapter adapter;
    private int currentPage = 1;
    private int totalAvailablePages = 1;
    private ProgressBar progressBar_loading;
    private ProgressBar progressBar_more;
    private boolean isLoading = false;
    private boolean isLoadingMore = false;
    private SharedPreferences userLocalDatabase;
    private String time = "";
    private int rate = 0;
    private int[] cateId = new int[]{};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        doInitialization();
        StatusBarConfig.StatusBarCustom(this);
        customActionBar();
        setNewRecipe(mlistreRecipes);

        btn_filter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FilterBottomSheetFragment bottomSheetFragment = new FilterBottomSheetFragment(time, rate, cateId);
                ((FilterBottomSheetFragment) bottomSheetFragment).setCallback(new FilterBottomSheetFragment.Callback() {
                    @Override
                    public void onFilterClick(String _time, int _star, int[] _cateId) {
                        if (search_recipe.getText().toString().length() > 0 && (!_time.equals("") || _star != 0 || _cateId.length != 0)) {
                            adapter.notifyItemRangeRemoved(0, mlistreRecipes.size());
                            mlistreRecipes.clear();

                            currentPage = 1;
                            time = _time;
                            rate = _star;
                            cateId = _cateId;
                            getNewRecipe(search_recipe.getText().toString(), time, rate, cateId);
                        }
                    }
                });
                bottomSheetFragment.show(getSupportFragmentManager(), bottomSheetFragment.getTag());
            }
        });

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (recyclerView.canScrollVertically(1)) {
                    if (currentPage < totalAvailablePages) {
                        currentPage += 1;
                        getNewRecipe(search_recipe.getText().toString(), time, rate, cateId);
                    }
                }
            }
        });

        search_recipe.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                adapter.notifyItemRangeRemoved(0, mlistreRecipes.size());
                mlistreRecipes.clear();
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (search_recipe.getText().toString().length() > 0) {
                    currentPage = 1;
                    time = "All";
                    rate = 0;
                    cateId = new int[]{};
                    getNewRecipe(search_recipe.getText().toString(), time, rate, cateId);
                }
            }
        });
    }

    private void setNewRecipe(ArrayList<Recipe> recipesList) {
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        adapter = new RecipeAdapter(recipesList, this, this,this);
        recyclerView.setAdapter(adapter);
    }

    private void getNewRecipe(String search, String time, int rate, int[] cateId) {
        toggleLoading();
        RecipeRequest newRequest = new RecipeRequest();
        newRequest.setKeyword(search);
        newRequest.setListCatId(cateId);
        newRequest.setAuthorId(0);
        newRequest.setName("");
        newRequest.setOrigin("");
        newRequest.setIngredient("");
        newRequest.setMinServer(0);
        newRequest.setMaxServer(0);
        newRequest.setMinTotalViews(0);
        newRequest.setMaxTotalViews(0);
        newRequest.setMinTotalRating(rate);
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

        if (time.equals("All") || time.equals("")) {
            newRequest.setSortByIdDESC(false);
            newRequest.setSortByTotalViewDESC(false);
            newRequest.setSortByAvgRatingDESC(false);
        } else {
            if (time.equals("Newest")) {
                newRequest.setSortByIdDESC(true);
            } else if (time.equals("Newest")) {
                newRequest.setSortByIdDESC(false);
            } else if (time.equals("Popularity")) {
                newRequest.setSortByTotalViewDESC(true);
                newRequest.setSortByAvgRatingDESC(true);
            }
        }

        newRequest.setSortByNameASC(false);
        newRequest.setSortByServesASC(false);
        newRequest.setSortByServesDESC(false);
        newRequest.setSortByTotalRatingDESC(false);
        newRequest.setSortByCaloriesDESC(false);
        newRequest.setSortByFatDESC(false);
        newRequest.setSortByProteinDESC(false);
        newRequest.setSortByCarbo(false);
        newRequest.setPageIndex(currentPage);
        newRequest.setPageSize(10);
        newRequest.setLoginUserId(userLocalDatabase.getInt("id", -1));
        viewModel.getAllNewRecipe(newRequest).observe(this, new Observer<RecipeResponse>() {
            @Override
            public void onChanged(RecipeResponse recipeResponse) {
                if (recipeResponse.getNewRecipeShow() != null) {
                    totalAvailablePages = recipeResponse.getTotalPage();
                    if (currentPage == 1) {
                        isLoading = true;
                    } else {
                        isLoadingMore = true;
                    }
                    toggleLoading();
                    if (recipeResponse.getNewRecipeShow().size() > 0) {
                        int oldCount = mlistreRecipes.size();
                        mlistreRecipes.addAll(recipeResponse.getNewRecipeShow());
                        adapter.notifyItemRangeInserted(oldCount, mlistreRecipes.size());
                    }
                }
            }
        });

    }

    private void toggleLoading() {
        if (currentPage == 1) {
            if (isLoading) {
                progressBar_loading.setVisibility(View.GONE);
                isLoading = false;
            } else {
                progressBar_loading.setVisibility(View.VISIBLE);
            }
        } else {
            if (isLoadingMore) {
                progressBar_more.setVisibility(View.GONE);
                isLoadingMore = false;
            } else {
                progressBar_more.setVisibility(View.VISIBLE);
            }
        }
    }

    private void doInitialization() {
        btn_filter = (RelativeLayout) findViewById(R.id.btn_search_filter);
        this.toolbar = findViewById(R.id.toolbar);
        this.tv_title = findViewById(R.id.toolbar_title);
        recyclerView = findViewById(R.id.category_list);
        search_recipe = findViewById(R.id.search_recipe);
        viewModel = new ViewModelProvider(this).get(RecipeViewModel.class);
        progressBar_loading = findViewById(R.id.progressBar_loading);
        progressBar_more = findViewById(R.id.progressBar_more);
        this.userLocalDatabase = getSharedPreferences("userDetails", 0);
    }

    private void customActionBar() {

        setSupportActionBar(toolbar);
        this.tv_title.setText("Search recipes");
        getSupportActionBar().setTitle("");

        // Customize the back button
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_left);
        // showing the back button in action bar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case android.R.id.home:
                startActivity(new Intent(this, MainActivity.class));
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onRecipeDetailListener(int recipe_id, String recipe_name) {
        Intent intent = new Intent(SearchActivity.this, RecipeDetailActivity.class);
        intent.putExtra("recipeId", recipe_id);
        intent.putExtra("recipeName", recipe_name);
        startActivity(intent);
    }
}