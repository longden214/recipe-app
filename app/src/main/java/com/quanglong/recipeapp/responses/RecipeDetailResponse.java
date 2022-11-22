package com.quanglong.recipeapp.responses;

import com.google.gson.annotations.SerializedName;
import com.quanglong.recipeapp.model.Ingredient;
import com.quanglong.recipeapp.model.Recipe;
import com.quanglong.recipeapp.model.Step;

import java.util.List;

public class RecipeDetailResponse {
    @SerializedName("listIngredients")
    private List<Ingredient> listIngredients;

    @SerializedName("listSteps")
    private List<Step> listSteps;

    @SerializedName("recipesViewModel")
    private Recipe recipesViewModel;

    public  List<Ingredient> getListIngredients(){
        return listIngredients;
    }

    public  List<Step> getListSteps(){
        return listSteps;
    }

    public Recipe getRecipeDetail(){
        return recipesViewModel;
    }
}
