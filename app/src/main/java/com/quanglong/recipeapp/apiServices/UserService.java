package com.quanglong.recipeapp.apiServices;

import com.quanglong.recipeapp.model.AccountSettingRequest;
import com.quanglong.recipeapp.model.EditProfileRequest;
import com.quanglong.recipeapp.model.LoginRequest;
import com.quanglong.recipeapp.model.PasswordRequest;
import com.quanglong.recipeapp.model.User;
import com.quanglong.recipeapp.responses.UserLoginResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.PUT;

public interface UserService {

    @POST("users/loginUser")
    Call<UserLoginResponse> login(@Body LoginRequest loginRequest);

    @POST("users/insert")
    Call<String> addUser(@Body User user);

    @PUT("users/updateNameUser")
    Call<String> editProfile(@Body EditProfileRequest editProfileRequest);

    @PUT("users/updateInforUser")
    Call<String> accountSetting(@Body AccountSettingRequest accountSettingRequest);

    @PUT("users/updatePassword")
    Call<String> updatePassword(@Body PasswordRequest passwordRequest);
}
