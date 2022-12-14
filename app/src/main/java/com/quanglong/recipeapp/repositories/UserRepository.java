package com.quanglong.recipeapp.repositories;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.quanglong.recipeapp.apiServices.CategoryAPIService;
import com.quanglong.recipeapp.apiServices.UserService;
import com.quanglong.recipeapp.model.AccountSettingRequest;
import com.quanglong.recipeapp.model.Category;
import com.quanglong.recipeapp.model.EditProfileRequest;
import com.quanglong.recipeapp.model.LoginRequest;
import com.quanglong.recipeapp.model.PasswordRequest;
import com.quanglong.recipeapp.model.User;
import com.quanglong.recipeapp.network.ApiClient;
import com.quanglong.recipeapp.responses.UserLoginResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.Query;

public class UserRepository {
    private UserService apiService;

    public UserRepository() {
        apiService = ApiClient.getRetrofit().create(UserService.class);
    }

    public LiveData<UserLoginResponse> userDetail(int id, int loginUserId){
        MutableLiveData<UserLoginResponse> data = new MutableLiveData<>();

        apiService.userDetail(id, loginUserId).enqueue(new Callback<UserLoginResponse>() {
            @Override
            public void onResponse(Call<UserLoginResponse> call, Response<UserLoginResponse> response) {
                data.setValue(response.body());
            }

            @Override
            public void onFailure(Call<UserLoginResponse> call, Throwable t) {
                data.setValue(null);
            }
        });

        return data;
    }

    public LiveData<String> createUser(User user){
        MutableLiveData<String> data = new MutableLiveData<>();

        apiService.addUser(user).enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                data.setValue(response.body());
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                data.setValue(null);
            }
        });

        return data;
    }

    public LiveData<String> editProfile(EditProfileRequest request){
        MutableLiveData<String> data = new MutableLiveData<>();

        apiService.editProfile(request).enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                data.setValue(response.body());
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                data.setValue(null);
            }
        });

        return data;
    }

    public LiveData<String> accountSetting(AccountSettingRequest request){
        MutableLiveData<String> data = new MutableLiveData<>();

        apiService.accountSetting(request).enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                data.setValue(response.body());
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                data.setValue(null);
            }
        });

        return data;
    }

    public LiveData<String> updatePassword(PasswordRequest request){
        MutableLiveData<String> data = new MutableLiveData<>();

        apiService.updatePassword(request).enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                data.setValue(response.body());
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                data.setValue(null);
            }
        });

        return data;
    }

    public LiveData<UserLoginResponse> login(LoginRequest loginRequest){
        MutableLiveData<UserLoginResponse> data = new MutableLiveData<>();

        apiService.login(loginRequest).enqueue(new Callback<UserLoginResponse>() {
            @Override
            public void onResponse(Call<UserLoginResponse> call, Response<UserLoginResponse> response) {
                data.setValue(response.body());
            }

            @Override
            public void onFailure(Call<UserLoginResponse> call, Throwable t) {
                data.setValue(null);
            }
        });

        return data;
    }
}
