package com.quanglong.recipeapp.apiServices;

import com.quanglong.recipeapp.model.RecipeDataRequest;
import com.quanglong.recipeapp.model.User;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface RecipeService {
    @POST("recipes/insert")
    Call<String> RecipeInsert(@Body RecipeDataRequest dataRequest);
}
