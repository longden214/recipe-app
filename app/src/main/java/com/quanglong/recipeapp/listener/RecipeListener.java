package com.quanglong.recipeapp.listener;

import com.quanglong.recipeapp.model.Recipe;

public interface RecipeListener {
    void onRecipeClicked(Recipe recipe);
    void onRecipeClickedById(int id);
}
