package com.quanglong.recipeapp.apiServices;

import com.quanglong.recipeapp.model.RecipeDataRequest;
import com.quanglong.recipeapp.model.User;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface RecipeService {
    @Multipart
    @POST("recipes/insert")
    Call<String> RecipeInsert(@Body RecipeDataRequest dataRequest,@Part MultipartBody.Part file);
}
