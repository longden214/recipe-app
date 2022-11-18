package com.quanglong.recipeapp.repositories;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.quanglong.recipeapp.apiServices.RecipeService;
import com.quanglong.recipeapp.model.RecipeRequest;
import com.quanglong.recipeapp.apiServices.UserService;
import com.quanglong.recipeapp.model.LoginRequest;
import com.quanglong.recipeapp.model.RecipeDataRequest;
import com.quanglong.recipeapp.model.User;
import com.quanglong.recipeapp.network.ApiClient;
import com.quanglong.recipeapp.responses.UserLoginResponse;
import com.quanglong.recipeapp.responses.RecipeResponse;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RecipeRepository {
    private RecipeService apiService;

    public RecipeRepository() {
        apiService = ApiClient.getRetrofit().create(RecipeService.class);
    }

    public LiveData<RecipeResponse> getAllNewRecipe(RecipeRequest newRequest){
        MutableLiveData<RecipeResponse> data = new MutableLiveData<>();
        apiService.newrecipe(newRequest).enqueue(new Callback<RecipeResponse>() {
            @Override
            public void onResponse(Call<RecipeResponse> call, Response<RecipeResponse> response) {
                data.setValue(response.body());
            }

            @Override
            public void onFailure(Call<RecipeResponse> call, Throwable t) {
                data.setValue(null);
            }
        });
        return data;
    }

    public LiveData<String> RecipeInsert(RecipeDataRequest dataRequest, MultipartBody.Part file ){
        MutableLiveData<String> data = new MutableLiveData<>();

        apiService.RecipeInsert(dataRequest,file).enqueue(new Callback<String>() {
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
