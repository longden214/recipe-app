package com.quanglong.recipeapp.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.quanglong.recipeapp.model.RecipeRequest;
import com.quanglong.recipeapp.repositories.RecipeRepository;
import com.quanglong.recipeapp.responses.RecipeResponse;

public class RecipeViewModel extends ViewModel {
    private RecipeRepository newRecipeRepository;
    public RecipeViewModel(){
        newRecipeRepository = new RecipeRepository();
    }

    public LiveData<RecipeResponse> getAllNewRecipe(RecipeRequest newRequest){
        return  newRecipeRepository.getAllNewRecipe(newRequest);
    }
}
