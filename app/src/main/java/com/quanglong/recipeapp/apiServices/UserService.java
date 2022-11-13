package com.quanglong.recipeapp.apiServices;

import com.quanglong.recipeapp.model.User;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface UserService {
    @POST("users/insert")
    Call<String> addUser(@Body User user);
}
