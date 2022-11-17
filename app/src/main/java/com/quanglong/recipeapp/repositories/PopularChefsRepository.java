package com.quanglong.recipeapp.repositories;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.quanglong.recipeapp.apiServices.PopularChefsAPIService;
import com.quanglong.recipeapp.model.ChefRequest;
import com.quanglong.recipeapp.network.ApiClient;
import com.quanglong.recipeapp.responses.PopularChefResponses;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PopularChefsRepository {
    private PopularChefsAPIService apiService;
    public PopularChefsRepository(){
        apiService = ApiClient.getRetrofit().create(PopularChefsAPIService.class);
    }

    public LiveData<PopularChefResponses> getallPopularChef(ChefRequest chefRequest){
        MutableLiveData<PopularChefResponses> data = new MutableLiveData<>();
        apiService.popularchef(chefRequest).enqueue(new Callback<PopularChefResponses>() {
            @Override
            public void onResponse(Call<PopularChefResponses> call, Response<PopularChefResponses> response) {
                data.setValue(response.body());
            }

            @Override
            public void onFailure(Call<PopularChefResponses> call, Throwable t) {
                data.setValue(null);
            }
        });
        return data;
    }
}

