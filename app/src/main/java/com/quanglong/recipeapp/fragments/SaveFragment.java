package com.quanglong.recipeapp.fragments;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.res.ResourcesCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.quanglong.recipeapp.R;
import com.quanglong.recipeapp.activities.RecipeByCategoryActivity;
import com.quanglong.recipeapp.activities.RecipeDetailActivity;
import com.quanglong.recipeapp.activities.UserProfileActivity;
import com.quanglong.recipeapp.adapter.RecipeSaveAdptar;
import com.quanglong.recipeapp.listener.RecipeDetailListener;
import com.quanglong.recipeapp.model.Category;
import com.quanglong.recipeapp.model.Recipe;
import com.quanglong.recipeapp.model.RecipeRequest;
import com.quanglong.recipeapp.responses.RecipeResponse;
import com.quanglong.recipeapp.viewmodels.CategoryViewModel;
import com.quanglong.recipeapp.viewmodels.PopularChefsViewModel;
import com.quanglong.recipeapp.viewmodels.RecipeViewModel;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * create an instance of this fragment.
 */
public class SaveFragment extends Fragment implements View.OnClickListener, RecipeDetailListener {

    Toolbar toolbar;
    TextView tv_title;
    private RecyclerView recyclerView;
    private ArrayList<Recipe> mlistreRecipes = new ArrayList<>() ;
    private RecipeViewModel viewModel;
    private RecipeSaveAdptar recipeSaveAdptar;
    private int id ;
    private SharedPreferences userLocalDatabase;

    public SaveFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_save, container, false);

        return view;

    }
    private void setNewRecipe(ArrayList<Recipe> RecipeSaveList) {
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(),1));
        recipeSaveAdptar = new RecipeSaveAdptar(getActivity(),RecipeSaveList,this);
        recyclerView.setAdapter(recipeSaveAdptar);
    }

    private void getNewRecipe() {
        RecipeRequest newRequest = new RecipeRequest();
        newRequest.setKeyword("");
        newRequest.setListCatId(new int[]{});
        newRequest.setAuthorId(id);
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
        newRequest.setPageIndex(1);
        newRequest.setPageSize(10);
        viewModel.getAllNewRecipe(newRequest).observe(getViewLifecycleOwner(), new Observer<RecipeResponse>() {
            @Override
            public void onChanged(RecipeResponse recipeResponse) {
                if (recipeResponse.getNewRecipeShow() != null) {
                    if (recipeResponse.getNewRecipeShow().size() > 0) {
                        int oldCount = mlistreRecipes.size();
                        mlistreRecipes.addAll(recipeResponse.getNewRecipeShow());
                        recipeSaveAdptar.notifyItemRangeInserted(oldCount, mlistreRecipes.size());
                    }
                }
            }
        });
    }
    private void doInitialization(View view) {
        recyclerView = view.findViewById(R.id.idMav);
        viewModel = new ViewModelProvider(this).get(RecipeViewModel.class);
        this.userLocalDatabase = getActivity().getSharedPreferences("userDetails", 0);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        toolbar = view.findViewById(R.id.toolbar);
        this.tv_title = view.findViewById(R.id.toolbar_title);
        AppCompatActivity activity = (AppCompatActivity) getActivity();

        activity.setSupportActionBar(toolbar);
        activity.getSupportActionBar().setTitle("");
        this.tv_title.setText("Save recipes");
        doInitialization(view);
        id = userLocalDatabase.getInt("id", -1);
        setNewRecipe(mlistreRecipes);
        getNewRecipe();

//        // Customize the back button
//        activity.getSupportActionBar().setHomeAsUpIndicator( R.drawable.ic_arrow_left);
//        // showing the back button in action bar
//        activity.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void onRecipeDetailListener(int recipe_id, String recipe_name) {
        Intent intent = new Intent(getActivity(), RecipeDetailActivity.class);
        intent.putExtra("recipeId",recipe_id);
        intent.putExtra("recipeName",recipe_name);
        startActivity(intent);
    }
}