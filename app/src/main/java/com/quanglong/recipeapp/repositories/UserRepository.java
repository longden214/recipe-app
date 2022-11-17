package com.quanglong.recipeapp.repositories;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.quanglong.recipeapp.apiServices.UserService;
import com.quanglong.recipeapp.model.User;
import com.quanglong.recipeapp.network.ApiClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserRepository {
    private UserService apiService;

    public UserRepository() {
        apiService = ApiClient.getRetrofit().create(UserService.class);
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
}
