package com.quanglong.recipeapp.fragments;

import static com.quanglong.recipeapp.utilities.BindingAdapter.setImageURL;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ImageView;

import com.quanglong.recipeapp.R;
import com.quanglong.recipeapp.activities.NewRecipeActivity;
import com.quanglong.recipeapp.activities.PopularChefsActivity;
import com.quanglong.recipeapp.activities.RecipeByCategoryActivity;
import com.quanglong.recipeapp.activities.SearchActivity;
import com.quanglong.recipeapp.activities.TrendingActivity;
import com.quanglong.recipeapp.adapter.CategoryAdapter;
import com.quanglong.recipeapp.adapter.NewAdapter;
import com.quanglong.recipeapp.adapter.PopularAdapter;
import com.quanglong.recipeapp.adapter.TrendingAdapter;
import com.quanglong.recipeapp.listener.CategoryListener;
import com.quanglong.recipeapp.model.Category;
import com.quanglong.recipeapp.model.ChefRequest;
import com.quanglong.recipeapp.model.Recipe;
import com.quanglong.recipeapp.model.RecipeRequest;
import com.quanglong.recipeapp.model.PopularChef;
import com.quanglong.recipeapp.responses.RecipeResponse;
import com.quanglong.recipeapp.responses.PopularChefResponses;
import com.quanglong.recipeapp.viewmodels.CategoryViewModel;

import java.util.ArrayList;
import java.util.List;
import com.quanglong.recipeapp.activities.CatagoryActivity;
import com.quanglong.recipeapp.viewmodels.RecipeViewModel;
import com.quanglong.recipeapp.viewmodels.PopularChefsViewModel;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * A simple {@link Fragment} subclass.
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment implements View.OnClickListener, CategoryListener {
    private RelativeLayout btn_filter;
    private CategoryViewModel viewModel;
    private List<Category> categoryList;
    private CategoryAdapter categoryAdapter;
    private RecyclerView category_recycler;
    private LinearLayout category_see_all;
    private LinearLayout chef_see_all;
    private LinearLayout new_see_all;
    private LinearLayout trending_see_all;
    private EditText edt_search;
    private CircleImageView avatar;
    private SharedPreferences userLocalDatabase;
    private PopularChefsViewModel popularChefsViewModel;
    private List<PopularChef> popularChefList;
    private PopularAdapter adapter;
    private RecyclerView popularchef_recycler;
    private RecipeViewModel RecipeViewModel;
    private List<Recipe> NewRecipeList;
    private List<Recipe> TrendingRecipeList;
    private NewAdapter newAdapter;
    private RecyclerView newrecipe_recycler;
    private RecyclerView trending_recycler;
    private TrendingAdapter trendingAdapter;

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        doInitialization(view);

        btn_filter.setOnClickListener(this);
        category_see_all.setOnClickListener(this);
        chef_see_all.setOnClickListener(this);
        new_see_all.setOnClickListener(this);
        trending_see_all.setOnClickListener(this);

        setImageURL(avatar, userLocalDatabase.getString("avatar", ""));

        edt_search.setOnTouchListener(new View.OnTouchListener()
        {
            public boolean onTouch(View arg0, MotionEvent arg1)
            {
                Intent intent = new Intent(getActivity(), SearchActivity.class);
                startActivity(intent);

                return false;
            }
        });

        setCategoryRecycler(categoryList);
        getAllCategory();
        setPopularRecycler(popularChefList);
        getPopularChef();
        setNewRecycler(NewRecipeList);
        getNewRecipe();
        setTrending(TrendingRecipeList);
        getTrending();


    }

    private void doInitialization(View view) {
        category_see_all = view.findViewById(R.id.category_see_all);
        chef_see_all = view.findViewById(R.id.chef_see_all);
        new_see_all = view.findViewById(R.id.new_see_all);
        trending_see_all = view.findViewById(R.id.trending_see_all);
        category_recycler = view.findViewById(R.id.category_list);
        edt_search = view.findViewById(R.id.edt_search);
        avatar = view.findViewById(R.id.home_avatar);
        btn_filter = (RelativeLayout) view.findViewById(R.id.btn_search_filter);
        categoryList = new ArrayList<Category>();
        popularChefList = new ArrayList<>();
        viewModel = new ViewModelProvider(this).get(CategoryViewModel.class);
        this.userLocalDatabase = getActivity().getSharedPreferences("userDetails", 0);
        popularChefsViewModel = new  ViewModelProvider(this).get(PopularChefsViewModel.class);
        popularchef_recycler = view.findViewById(R.id.chif_list);
        RecipeViewModel =  new  ViewModelProvider(this).get(RecipeViewModel.class);
        newrecipe_recycler = view.findViewById(R.id.new_recipe_list);
        NewRecipeList = new ArrayList<>();
        TrendingRecipeList = new ArrayList<>();
        trending_recycler = view.findViewById(R.id.trending_list);

    }

    private void setCategoryRecycler(List<Category> categoryList) {
        category_recycler.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), RecyclerView.HORIZONTAL, false);
        category_recycler.setLayoutManager(layoutManager);
        categoryAdapter = new CategoryAdapter(getActivity(), categoryList,this);
        category_recycler.setAdapter(categoryAdapter);
    }

    public void getAllCategory(){
        viewModel.getAllCategory().observe(getViewLifecycleOwner(), res ->{
            if (res != null){
                if (res.size() > 0){
                    int oldCount = categoryList.size();

                    categoryList.addAll(res);
                    categoryAdapter.notifyItemRangeInserted(oldCount,categoryList.size());
                }
            }
        });
    }

    private void setPopularRecycler(List<PopularChef> popularChefList) {
        popularchef_recycler.setHasFixedSize(false);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), RecyclerView.HORIZONTAL, false);
        popularchef_recycler.setLayoutManager(layoutManager);
        adapter = new PopularAdapter(getActivity(), popularChefList);
        popularchef_recycler.setAdapter(adapter);
    }

    private void getPopularChef() {
        ChefRequest chefRequest = new ChefRequest();
        chefRequest.setKeyword("");
        chefRequest.setEmail("");
        chefRequest.setPhoneNumber("");
        chefRequest.setDisplayName("");
        chefRequest.setUserName("");
        chefRequest.setSex(-1);
        chefRequest.setRole(-1);
        chefRequest.setStatus(-1);
        chefRequest.setSortByIdDESC(false);
        chefRequest.setSortByTotalRecipeDESC(true);
        chefRequest.setSortByTotalFollowOtherUserDESC(false);
        chefRequest.setSortByTotalFollowedByOthersUserDESC(true);
        chefRequest.setSortByTotalViewsDESC(true);
        chefRequest.setPageIndex(1);
        chefRequest.setPageSize(10);
        popularChefsViewModel.getAllPopularChef(chefRequest).observe(getViewLifecycleOwner(), new Observer<PopularChefResponses>() {
            @Override
            public void onChanged(PopularChefResponses popularChefResponses) {
                if (popularChefResponses.getPopularShow() != null) {
                    if (popularChefResponses.getPopularShow().size() > 0) {
                        int oldCount = popularChefList.size();

                        popularChefList.addAll(popularChefResponses.getPopularShow());
                        adapter.notifyItemRangeInserted(oldCount,popularChefList.size());
                    }
                }
            }
        });

    }

    private void setNewRecycler(List<Recipe> newRecipeList) {
        newrecipe_recycler.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), RecyclerView.HORIZONTAL, false);
        newrecipe_recycler.setLayoutManager(layoutManager);
        newAdapter = new NewAdapter(getActivity(), newRecipeList);
        newrecipe_recycler.setAdapter(newAdapter);
    }

    private void getNewRecipe() {
        RecipeRequest newRequest = new RecipeRequest();
        newRequest.setKeyword("");
        newRequest.setListCatId(new int[]{});
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
        newRequest.setPageIndex(1);
        newRequest.setPageSize(10);
        RecipeViewModel.getAllNewRecipe(newRequest).observe(getViewLifecycleOwner(), new Observer<RecipeResponse>() {
            @Override
            public void onChanged(RecipeResponse recipeResponse) {
                if (recipeResponse.getNewRecipeShow()!= null) {
                    if (recipeResponse.getNewRecipeShow().size() > 0) {
                        int oldCount = NewRecipeList.size();
                        NewRecipeList.addAll(recipeResponse.getNewRecipeShow());
                        newAdapter.notifyItemRangeInserted(oldCount,NewRecipeList.size());
                    }
                }
            }
        });

    }

    private void setTrending(List<Recipe> RecipeList) {
        trending_recycler.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), RecyclerView.HORIZONTAL, false);
        trending_recycler.setLayoutManager(layoutManager);
        trendingAdapter = new TrendingAdapter(getActivity(), RecipeList);
        trending_recycler.setAdapter(trendingAdapter);
    }

    private void getTrending() {
        RecipeRequest newRequest = new RecipeRequest();
        newRequest.setKeyword("");
        newRequest.setListCatId(new int[]{});
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
        newRequest.setSortByIdDESC(false);
        newRequest.setSortByNameASC(false);
        newRequest.setSortByServesASC(false);
        newRequest.setSortByServesDESC(false);
        newRequest.setSortByTotalViewDESC(true);
        newRequest.setSortByAvgRatingDESC(true);
        newRequest.setSortByTotalRatingDESC(true);
        newRequest.setSortByCaloriesDESC(false);
        newRequest.setSortByFatDESC(false);
        newRequest.setSortByProteinDESC(false);
        newRequest.setSortByCarbo(false);
        newRequest.setPageIndex(1);
        newRequest.setPageSize(10);
        RecipeViewModel.getAllNewRecipe(newRequest).observe(getViewLifecycleOwner(), new Observer<RecipeResponse>() {
            @Override
            public void onChanged(RecipeResponse recipeResponse) {
                if (recipeResponse.getNewRecipeShow()!= null) {
                    if (recipeResponse.getNewRecipeShow().size() > 0) {
                        int oldCount = TrendingRecipeList.size();
                        TrendingRecipeList.addAll(recipeResponse.getNewRecipeShow());
                        trendingAdapter.notifyItemRangeInserted(oldCount,TrendingRecipeList.size());
                    }
                }
            }
        });

    }

    @Override
    public void onClick(View view) {
        int id = view.getId();

        switch (id) {

            case R.id.btn_search_filter:
                Intent intent = new Intent(getActivity(), SearchActivity.class);
                startActivity(intent);
                break;
            case R.id.category_see_all:
                Intent intent2 = new Intent(getActivity(),CatagoryActivity.class);
                startActivity(intent2);
                break;
            case R.id.chef_see_all:
                Intent intent3 = new Intent(getActivity(), PopularChefsActivity.class);
                startActivity(intent3);
                break;
            case R.id.new_see_all:
                Intent intent4 = new Intent(getActivity(), NewRecipeActivity.class);
                startActivity(intent4);
                break;
            case R.id.trending_see_all:
                Intent intent5 = new Intent(getActivity(), TrendingActivity.class);
                startActivity(intent5);
                break;
        }
    }

    @Override
    public void onCategoryClicked(int cate_id, String cate_name) {
        Intent intent = new Intent(getActivity(), RecipeByCategoryActivity.class);
        intent.putExtra("cateId",cate_id);
        intent.putExtra("cateName",cate_name);

        startActivity(intent);
    }
}