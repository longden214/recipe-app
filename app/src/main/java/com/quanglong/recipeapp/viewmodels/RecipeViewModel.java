package com.quanglong.recipeapp.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.quanglong.recipeapp.model.LoginRequest;
import com.quanglong.recipeapp.model.RecipeDataRequest;
import com.quanglong.recipeapp.model.User;
import com.quanglong.recipeapp.repositories.RecipeRepository;
import com.quanglong.recipeapp.repositories.UserRepository;
import com.quanglong.recipeapp.responses.UserLoginResponse;

public class RecipeViewModel extends ViewModel {
    private RecipeRepository recipeRepository;

    public RecipeViewModel() {
        recipeRepository = new RecipeRepository();
    }

    public LiveData<String> createUser(RecipeDataRequest dataRequest) {
        return recipeRepository.RecipeInsert(dataRequest);
    }
}
