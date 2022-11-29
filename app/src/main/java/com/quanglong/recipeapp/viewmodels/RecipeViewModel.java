package com.quanglong.recipeapp.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.quanglong.recipeapp.model.FollowRequest;
import com.quanglong.recipeapp.model.LoginRequest;
import com.quanglong.recipeapp.model.RecipeDataRequest;
import com.quanglong.recipeapp.model.SaveRecipeRequest;
import com.quanglong.recipeapp.model.User;
import com.quanglong.recipeapp.repositories.RecipeRepository;
import com.quanglong.recipeapp.repositories.UserRepository;
import com.quanglong.recipeapp.responses.RecipeAddResponse;
import com.quanglong.recipeapp.responses.RecipeDetailResponse;
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

    public LiveData<RecipeResponse> getSaveRecipe(int userId, int pageIndex, int pageSize){
        return  recipeRepository.getSaveRecipe(userId, pageIndex, pageSize);
    }

    public LiveData<RecipeAddResponse> createRecipe(RecipeDataRequest dataRequest) {
        return recipeRepository.RecipeInsert(dataRequest);
    }
    public LiveData<RecipeDetailResponse> getRecipeDetailWithParam(int id,int loginUserId){
        return recipeRepository.getRecipeDetailWithParam(id,loginUserId);
    }

    public LiveData<String> recipeDelete(int recipe_id, int user_id) {
        return recipeRepository.RecipeDelete(recipe_id, user_id);
    }

    public LiveData<String> saveRecipe(SaveRecipeRequest saveRecipeRequest){
        return recipeRepository.saveRecipe(saveRecipeRequest);
    }

    public LiveData<String> unRecipe(int recipeId, int userId){
        return recipeRepository.unRecipe(recipeId,userId);
    }
}
