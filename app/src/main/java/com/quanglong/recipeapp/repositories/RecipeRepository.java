package com.quanglong.recipeapp.repositories;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.quanglong.recipeapp.apiServices.RecipeAPIService;
import com.quanglong.recipeapp.model.RecipeRequest;
import com.quanglong.recipeapp.network.ApiClient;
import com.quanglong.recipeapp.responses.RecipeResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RecipeRepository {
    private RecipeAPIService apiService;
    public RecipeRepository(){
        apiService = ApiClient.getRetrofit().create(RecipeAPIService.class);
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
}
