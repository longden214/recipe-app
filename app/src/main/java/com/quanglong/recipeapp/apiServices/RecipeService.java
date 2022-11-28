package com.quanglong.recipeapp.apiServices;

import com.quanglong.recipeapp.model.RatingRequest;
import com.quanglong.recipeapp.model.RecipeDataRequest;
import com.quanglong.recipeapp.model.RecipeRequest;
import com.quanglong.recipeapp.model.User;
import com.quanglong.recipeapp.responses.RecipeAddResponse;
import com.quanglong.recipeapp.responses.RecipeDetailResponse;
import com.quanglong.recipeapp.responses.RecipeResponse;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface RecipeService {

    @GET("recipes/getRecipe")
    Call<RecipeDetailResponse> getRecipeDetailWithParam(@Query("id") int id,@Query("loginUserId") int loginUserId);

    @GET("recipes/getSaveRecipe")
    Call<RecipeResponse> getSaveRecipe(@Query("userId") int userId,@Query("pageIndex") int pageIndex, @Query("pageSize") int pageSize);

    @POST("recipes/filterData")
    Call<RecipeResponse> newrecipe(@Body RecipeRequest newRequest);

    @POST("recipes/rating")
    Call<RecipeAddResponse> recipeRating(@Body RatingRequest request);

    @POST("recipes/insert")
    Call<RecipeAddResponse> RecipeInsert(@Body RecipeDataRequest dataRequest);

    @PUT("recipes")
    Call<RecipeAddResponse> recipeUpdate(@Body RecipeDataRequest dataRequest);

    @DELETE("recipes/delete")
    Call<String> RecipeDelete(@Query("id") int id,@Query("userId") int userId);
}
