package com.quanglong.recipeapp.repositories;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.quanglong.recipeapp.apiServices.CategoryAPIService;
import com.quanglong.recipeapp.model.Category;
import com.quanglong.recipeapp.network.ApiClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CategoryRepository {
    private CategoryAPIService apiService;

    public CategoryRepository() {
        apiService = ApiClient.getRetrofit().create(CategoryAPIService.class);
    }

    public LiveData<List<Category>> getAllCategory(){
        MutableLiveData<List<Category>> data = new MutableLiveData<>();

        apiService.getAllCategory().enqueue(new Callback<List<Category>>() {
            @Override
            public void onResponse(Call<List<Category>> call, Response<List<Category>> response) {
                data.setValue(response.body());
            }

            @Override
            public void onFailure(Call<List<Category>> call, Throwable t) {
                data.setValue(null);
            }
        });

        return data;
    }
}