package com.quanglong.recipeapp.apiServices;

import com.quanglong.recipeapp.model.RecipeRequest;
import com.quanglong.recipeapp.responses.RecipeDetailResponse;
import com.quanglong.recipeapp.responses.RecipeResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface RecipeAPIService {
    @POST("recipes/filterData")
    Call<RecipeResponse> newrecipe(@Body RecipeRequest newRequest);
}
