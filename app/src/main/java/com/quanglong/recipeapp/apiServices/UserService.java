package com.quanglong.recipeapp.apiServices;

import com.quanglong.recipeapp.model.LoginRequest;
import com.quanglong.recipeapp.model.User;
import com.quanglong.recipeapp.responses.UserLoginResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface UserService {

    @POST("users/loginUser")
    Call<UserLoginResponse> login(@Body LoginRequest loginRequest);

    @POST("users/insert")
    Call<String> addUser(@Body User user);
}
