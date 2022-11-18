package com.quanglong.recipeapp.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.quanglong.recipeapp.model.LoginRequest;
import com.quanglong.recipeapp.model.RecipeDataRequest;
import com.quanglong.recipeapp.model.User;
import com.quanglong.recipeapp.repositories.RecipeRepository;
import com.quanglong.recipeapp.repositories.UserRepository;
import com.quanglong.recipeapp.responses.UserLoginResponse;
import com.quanglong.recipeapp.model.RecipeRequest;
import com.quanglong.recipeapp.responses.RecipeResponse;

import okhttp3.MultipartBody;

public class RecipeViewModel extends ViewModel {
    private RecipeRepository recipeRepository;

    public RecipeViewModel() {
        recipeRepository = new RecipeRepository();
    }

    public LiveData<RecipeResponse> getAllNewRecipe(RecipeRequest newRequest){
        return  recipeRepository.getAllNewRecipe(newRequest);
    }

    public LiveData<String> createUser(RecipeDataRequest dataRequest, MultipartBody.Part file) {
        return recipeRepository.RecipeInsert(dataRequest,file);
    }
}
