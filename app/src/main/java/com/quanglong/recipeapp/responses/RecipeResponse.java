package com.quanglong.recipeapp.responses;

import com.google.gson.annotations.SerializedName;
import com.quanglong.recipeapp.model.Ingredient;
import com.quanglong.recipeapp.model.Recipe;
import com.quanglong.recipeapp.model.Step;

import java.util.List;

public class RecipeResponse {
    @SerializedName("totalPage")
    private int totalPage;

    @SerializedName("recipesViewModels")
    private List<Recipe> recipesViewModels;

    public int getTotalPage(){
        return totalPage;
    }
    public List<Recipe> getNewRecipeShow(){
        return recipesViewModels;
    }

}
