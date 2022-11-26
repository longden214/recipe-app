package com.quanglong.recipeapp.fragments;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.quanglong.recipeapp.R;
import com.quanglong.recipeapp.activities.CatagoryActivity;
import com.quanglong.recipeapp.adapter.CategoryAllAdapter;
import com.quanglong.recipeapp.adapter.IngridentDetailAdapter;
import com.quanglong.recipeapp.adapter.RecipeProfileAdptar;
import com.quanglong.recipeapp.model.Category;
import com.quanglong.recipeapp.model.Ingredient;
import com.quanglong.recipeapp.model.Recipe;
import com.quanglong.recipeapp.responses.RecipeDetailResponse;
import com.quanglong.recipeapp.viewmodels.RecipeViewModel;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * create an instance of this fragment.
 */
public class IngridentFragment extends Fragment {
    int id;
    private SharedPreferences userLocalDatabase;
    private RecipeViewModel recipeViewModel;
    private List<Ingredient> listIngredients = new ArrayList<>();
    private IngridentDetailAdapter adapter;
    private RecyclerView recyclerView;
    private TextView total_item;

    public IngridentFragment(int _id) {
        // Required empty public constructor
        id = _id;
    }
    

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_ingrident, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.userLocalDatabase = getActivity().getSharedPreferences("userDetails", 0);
        this.recipeViewModel = new ViewModelProvider(this).get(RecipeViewModel.class);
        recyclerView = (RecyclerView) view.findViewById(R.id.ingredient_rv);
        total_item = (TextView) view.findViewById(R.id.total_item_ingredient);
        setIngredientsRecycler(listIngredients);
        getRecipeDetail();
    }

    private void getRecipeDetail() {
        recipeViewModel.getRecipeDetailWithParam(id, userLocalDatabase.getInt("id", -1)).observe(getViewLifecycleOwner(), new Observer<RecipeDetailResponse>() {
            @Override
            public void onChanged(RecipeDetailResponse recipeDetailResponse) {
                if (recipeDetailResponse != null) {
                    if (recipeDetailResponse.getRecipeDetail() != null) {
                        int oldCount = listIngredients.size();
                        listIngredients.addAll(recipeDetailResponse.getListIngredients());
                        adapter.notifyItemRangeInserted(oldCount, listIngredients.size());
                        total_item.setText(listIngredients.size() + " items");
                    }
                }
            }
        });
    }

    private void setIngredientsRecycler(List<Ingredient> mlistIngredient) {
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(),1));
        adapter = new IngridentDetailAdapter(getActivity(), mlistIngredient);
        recyclerView.setAdapter(adapter);
    }
}